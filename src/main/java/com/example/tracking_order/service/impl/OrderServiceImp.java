package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ItemProducts;
import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.dto.response.OrderReviewRes;
import com.example.tracking_order.entity.*;
import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.PaymentMethod;
import com.example.tracking_order.enums.PaymentStatus;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.AddressMapper;
import com.example.tracking_order.mapper.OrderMapper;
import com.example.tracking_order.repository.*;
import com.example.tracking_order.service.IInventoryService;
import com.example.tracking_order.service.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImp implements IOrderService {
    private OrderRepository repo;
    private OrderItemRepository orderItemRepo;
    private CartRepository cartRepo;
    private DiscountRepository discountRepo;
    private ProductVariantRepository  variantRepo;
    private UserRepository userRepo;
    private TrackLogRepository trackLogRepo;
    private OrderMapper mapper;
    private AddressMapper addressMapper;
    private IInventoryService inservice;

    @Override
    @Transactional
    public String create(OrderReq req) {
        OrderEntity order = new OrderEntity();
        OrderItemEntity oi = new OrderItemEntity();
        List<ItemProducts> items = req.getItems();
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal feeship = BigDecimal.ZERO;
        BigDecimal dis = BigDecimal.ZERO;
        for(ItemProducts item : items)
        {
            // kiem tra va update quantity in stock
            ProductVariantEntity variant = variantRepo.findById(item.getVariantId())
                    .orElseThrow(()-> new BusinessException("Khong tin thay san pham"));
            inservice.updateStock(item.getVariantId(), item.getQuantity());
            oi.setProductVariant(variant);
            oi.setPrice(item.getPrice());
            oi.setQuantity(item.getQuantity());

            if(variant.getPrice().compareTo(item.getPrice()) != 0){
                throw new BusinessException("order wrong!");
            }
            subtotal = subtotal.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            if(item.getDiscountId() != null){
                DiscountEntity discount = discountRepo.findById(item.getDiscountId()).orElse(null);
                if(discount != null){
                    dis = dis.add(discount.getDiscountValue());
                }
            }
        }
        UserEntity user = userRepo.findById(req.getUserId()).orElseThrow(()-> new BusinessException("User khong ton tai"));
        List<AddressEntity> address = user.getAddress();
        BigDecimal totalPrice = subtotal.subtract(dis).subtract(feeship);

        AddressEntity add = new AddressEntity();
        for(AddressEntity ad : address)
        {
            if (ad.getIsDefault() == true) {
                add = ad;
                break; // nếu chỉ cần lấy địa chỉ mặc định đầu tiên
            }
        }
        order.setUser(user);
        order.setAddress(add);
        order.setSubtotal(subtotal);
        order.setTotal(totalPrice);
        order.setDiscount(dis);
        order.setPriceShippment(feeship);
        if(req.getPaymentMethod() == PaymentMethod.COD){
            order.setPaymentMethod(req.getPaymentMethod());
            order.setPaymentStatus(PaymentStatus.UNPAID);
            // order.orderStatus = "pending"
        }
        else {
            order.setPaymentMethod(req.getPaymentMethod());
            order.setPaymentStatus(PaymentStatus.AWAITING_PAYMENT);
        }
        String trackNumber = "TN-" + System.currentTimeMillis();;
        order.setStatus(OrderStatus.PENDING);
        repo.saveAndFlush(order);

        TrackLogEnitty log = new TrackLogEnitty();
        log.setTrackNumber(trackNumber);
        log.setOldStatus(OrderStatus.PENDING);
        log.setNewStatus(OrderStatus.PENDING);
        log.setOrder(order);
        trackLogRepo.save(log);

        oi.setOrder(order);
        orderItemRepo.save(oi);
        return "TẠO ĐƠN HÀNG THÀNH CÔNG";
    }

    @Override
    @Transactional
    public OrderRes update(UUID id, OrderReq req) {
        OrderEntity order = repo.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, order);
        repo.save(order);
        return mapper.toResponse(order);
    }

    @Override
    public Page<OrderRes> findAll(Pageable pageable) {
        Page<OrderEntity> orders = repo.findByIsDeletedFalse(pageable);
        return orders.map(mapper::toResponse);
    }

    @Override
    public OrderReviewRes review(OrderReviewReq req) {
        // check cart
        CartEntity cart = cartRepo.findById(req.getCartId()).orElseThrow(()-> new BusinessException("Khong tin thay gio hang"));
        // lay cac san pham tinh
        List<ItemProducts> items = req.getItems();
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal feeship = BigDecimal.ZERO;
        BigDecimal dis = BigDecimal.ZERO;
        for(ItemProducts item : items)
        {
            ProductVariantEntity variant = variantRepo.findById(item.getVariantId()).orElseThrow(()-> new BusinessException("Khong tin thay san pham"));
            if(variant.getPrice().compareTo(item.getPrice()) != 0){
                throw new BusinessException("order wrong!");
            }
            subtotal = subtotal.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            if(item.getDiscountId() != null){
                DiscountEntity discount = discountRepo.findById(item.getDiscountId()).orElse(null);
                if(discount != null){
                    dis = dis.add(discount.getDiscountValue());
                }
            }
        }
        UserEntity user = userRepo.findById(req.getUserId()).orElseThrow(()-> new BusinessException("User khong ton tai"));
        List<AddressEntity> address = user.getAddress();
        String fullName = user.getFirstName() + " " + user.getLastName();
        BigDecimal totalPrice = subtotal.subtract(dis).subtract(feeship);

        AddressRes addressRes = new AddressRes();
        for(AddressEntity ad : address)
        {
            if(ad.getIsDefault() == true)
            {
               addressRes = addressMapper.toResponse(ad);
            }
        }
        return new OrderReviewRes(fullName, addressRes, req.getItems(), subtotal, dis, feeship, totalPrice);
    }


}

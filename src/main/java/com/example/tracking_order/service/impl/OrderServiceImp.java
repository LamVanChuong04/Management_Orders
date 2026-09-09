package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.CartDetailReq;
import com.example.tracking_order.dto.request.ItemProducts;
import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.dto.response.OrderReviewRes;
import com.example.tracking_order.entity.*;
import com.example.tracking_order.enums.DiscountStatus;
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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
    private InventoryRepository inventoryRepo;
    private OrderMapper mapper;
    private AddressMapper addressMapper;
    private CartItemRepository cartItemRepo;

//    @Override
//    @Transactional
//    public String create(OrderReq req) {
//        OrderEntity order = new OrderEntity();
//        // danh sách order item
//        List<OrderItemEntity> orderItems = new ArrayList<>();
//
//        List<ItemProducts> items = req.getItems();
//        BigDecimal subtotal = BigDecimal.ZERO;
//        BigDecimal dis = BigDecimal.ZERO;
//        for(ItemProducts item : items)
//        {
//            // kiem tra va update quantity in stock
//            ProductVariantEntity variant = variantRepo.findById(item.getVariantId())
//                    .orElseThrow(()-> new BusinessException("Khong tin thay san pham"));
//            // update stock
//            int updatedRows = inventoryRepo.updateStock(item.getVariantId(), item.getQuantity());
//            if (updatedRows == 0) {
//                // Nếu không có bản ghi nào được update, tức là tồn kho không đủ
//                throw new BusinessException("Sản phẩm trong kho không đủ đáp ứng.");
//            }
//            OrderItemEntity oi = new OrderItemEntity();
//            oi.setProductVariant(variant);
//            oi.setPrice(item.getPrice());
//            oi.setQuantity(item.getQuantity());
//            oi.setColor(variant.getColor());
//            oi.setSize(variant.getSize());
//            oi.setWeight(variant.getWeight());
//
//
//            if(variant.getPrice().compareTo(item.getPrice()) != 0){
//                throw new BusinessException("order wrong!");
//            }
//            subtotal = subtotal.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
//            if(item.getDiscountId() != null){
//                DiscountEntity discount = discountRepo.findById(item.getDiscountId()).orElse(null);
//                if(discount != null){
//                    dis = dis.add(discount.getDiscountValue());
//                }
//            }
//            // gắn order vào item
//            oi.setOrder(order);
//            orderItems.add(oi);
//        }
//        UserEntity user = userRepo.findById(req.getUserId()).orElseThrow(()-> new BusinessException("User khong ton tai"));
//        List<AddressEntity> address = user.getAddress();
//        BigDecimal totalPrice = subtotal.subtract(dis).subtract(req.getFeeship());
//
//        // lay dia chi mac dinh cua user
//        AddressEntity add = new AddressEntity();
//        for(AddressEntity ad : address)
//        {
//            if (ad.getIsDefault() == true) {
//                add = ad;
//                break;
//            }
//        }
//        order.setUser(user);
//        order.setAddress(add);
//        order.setSubtotal(subtotal);
//        order.setTotal(totalPrice);
//        order.setDiscount(dis);
//        order.setPriceShippment(req.getFeeship());
//
//        // set phuong thuc thanh toan
//        if(req.getPaymentMethod() == PaymentMethod.COD){
//            order.setPaymentMethod(req.getPaymentMethod());
//            order.setPaymentStatus(PaymentStatus.UNPAID);
//            // order.orderStatus = "pending"
//        }
//        else {
//            order.setPaymentMethod(req.getPaymentMethod());
//            order.setPaymentStatus(PaymentStatus.AWAITING_PAYMENT);
//        }
//        // sinh ra 1 track number
//        String trackNumber = "TN-" + System.currentTimeMillis();;
//        order.setStatus(OrderStatus.PENDING);
//        repo.saveAndFlush(order);
//
//        TrackLogEnitty log = new TrackLogEnitty();
//        log.setTrackNumber(trackNumber);
//        log.setOldStatus(OrderStatus.PENDING);
//        log.setNewStatus(OrderStatus.PENDING);
//        log.setOrder(order);
//        trackLogRepo.save(log);
//
//        // lưu tất cả order items
//        orderItemRepo.saveAll(orderItems);
//        return "TẠO ĐƠN HÀNG THÀNH CÔNG";
//    }


    @Override
    public Page<OrderRes> findAll(Pageable pageable) {
        Page<OrderEntity> orders = repo.findByIsDeletedFalse(pageable);
        return orders.map(mapper::toResponse);
    }

    @Override
    public OrderReviewRes review(OrderReviewReq req) {
        return null;
    }

    @Override
    public OrderReviewRes sumaryOrder(OrderReviewReq req) {
        CartEntity cart = cartRepo.findById(req.getCartId())
                .orElseThrow(()-> new BusinessException("Khong tin thay gio hang"));

        BigDecimal subtotal = BigDecimal.ZERO;
        long randomValue = ThreadLocalRandom.current().nextLong(1000, 100001);
        BigDecimal feeship = BigDecimal.valueOf(randomValue);
        BigDecimal dis = BigDecimal.ZERO;

        List<CartItemEntity> cartItems = cart.getCartItems();
        for(CartItemEntity ci : cartItems){
            if(ci.getIsSelected()) {
                ProductVariantEntity variant = ci.getProductVariant();
                subtotal = subtotal.add(variant.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
            }
        }
        BigDecimal totalPrice = subtotal.subtract(dis).subtract(feeship);
        return new OrderReviewRes(subtotal, dis, feeship, totalPrice);
    }

    @Override
    @Transactional
    public String checkout(OrderReq req) {
        OrderEntity order = new OrderEntity();
//        // danh sách order item
        List<OrderItemEntity> orderItems = new ArrayList<>();
        UserEntity user = userRepo.findById(req.getUserId()).orElseThrow(()-> new BusinessException("Khong tin thay user"));
        // địa chỉ nhận hàng
        List<AddressEntity> address = user.getAddress();
        AddressEntity add = new AddressEntity();
        for(AddressEntity ad : address)
        {
            if(ad.getIsDefault() == true)
            {
                add = ad;
                break;
            }
        }
        String fullName = user.getFirstName() + " " + user.getLastName();
        String sdt = user.getPhone();
        // logic pricing
        BigDecimal subtotal = BigDecimal.ZERO;
        long randomValue = ThreadLocalRandom.current().nextLong(1000, 100001);
        BigDecimal feeship = BigDecimal.valueOf(randomValue);
        BigDecimal dis = BigDecimal.ZERO;

        List<CartDetailReq> items = req.getItems();
        for(CartDetailReq item : items){
            ProductVariantEntity variant = variantRepo.findById(item.getVariantId())
                    .orElseThrow(()-> new BusinessException("Khong tin thay san pham"));
            // kiem tra ton kho
            int updatedRows = inventoryRepo.updateStock(item.getVariantId(), item.getQuantity());
            if (updatedRows == 0) {
                // Nếu không có bản ghi nào được update, tức là tồn kho không đủ
                throw new BusinessException("Sản phẩm trong kho không đủ đáp ứng.");
            }
            OrderItemEntity oi = new OrderItemEntity();
            oi.setProductVariant(variant);
            oi.setPrice(item.getPrice());
            oi.setQuantity(item.getQuantity());
            oi.setColor(variant.getColor());
            oi.setSize(variant.getSize());
            oi.setWeight(variant.getWeight());

            // validate price
            if(variant.getPrice().compareTo(item.getPrice()) != 0){
                throw new BusinessException("order wrong!");
            }
            BigDecimal price = variant.getPrice();
            // ap voucher
            if (item.getDiscount() != null) {
                DiscountEntity discount = discountRepo.findById(item.getDiscount().getDiscountId())
                        .orElseThrow(() -> new BusinessException("Giảm giá không hợp lệ"));

                if (discount.getStatus() == DiscountStatus.ACTIVE) {
                    dis = discount.getDiscountValue();
                    price = price.subtract(dis);
                    // update quantity discount

                }
            }
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            subtotal = subtotal.add(itemTotal);

            // gắn order vào item
            oi.setOrder(order);
            orderItems.add(oi);
            // xoa item khoi cart
            cartItemRepo.deleteByProductVariantId(variant.getId());
        }
        BigDecimal totalPrice = subtotal.subtract(feeship);
        order.setUser(user);
        order.setAddress(add);
        order.setSubtotal(subtotal);
        order.setTotal(totalPrice);
        order.setDiscount(dis);
        order.setPriceShippment(feeship);

        // set phuong thuc thanh toan
        if(req.getPaymentMethod() == PaymentMethod.COD){
            order.setPaymentMethod(req.getPaymentMethod());
            order.setPaymentStatus(PaymentStatus.UNPAID);
            // order.orderStatus = "pending"
        }
        else {
            order.setPaymentMethod(req.getPaymentMethod());
            order.setPaymentStatus(PaymentStatus.AWAITING_PAYMENT);
        }
        // sinh ra 1 track number
        String trackNumber = "TN-" + System.currentTimeMillis();;
        order.setStatus(OrderStatus.PENDING);
        repo.saveAndFlush(order);

        TrackLogEnitty log = new TrackLogEnitty();
        log.setTrackNumber(trackNumber);
        log.setOldStatus(OrderStatus.PENDING);
        log.setNewStatus(OrderStatus.PENDING);
        log.setOrder(order);
        trackLogRepo.save(log);

        // lưu tất cả order items
        orderItemRepo.saveAll(orderItems);
        return "TẠO ĐƠN HÀNG THÀNH CÔNG";
    }

}

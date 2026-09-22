package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.*;
import com.example.tracking_order.dto.response.*;
import com.example.tracking_order.entity.*;
import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.PaymentMethod;
import com.example.tracking_order.enums.PaymentStatus;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.mapper.AddressMapper;
import com.example.tracking_order.mapper.OrderMapper;
import com.example.tracking_order.repository.*;
import com.example.tracking_order.service.IInventoryService;
import com.example.tracking_order.service.IOrderService;
import com.example.tracking_order.utils.OrderStateMachine;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements IOrderService {
    private OrderRepository repo;
    private OrderItemRepository orderItemRepo;
    private CartRepository cartRepo;
    private ProductVariantRepository  variantRepo;
    private UserRepository userRepo;
    private TrackLogRepository trackLogRepo;
    private InventoryRepository inventoryRepo;
    private OrderMapper mapper;
    private AddressMapper addressMapper;
    private AddressRepository addressRepo;
    private CartItemRepository cartItemRepo;
    private IInventoryService iservice;

    @Override
    public Page<OrderRes> findAll(Pageable pageable) {
        Page<OrderEntity> orders = repo.findByIsDeletedFalse(pageable);
        return orders.map(mapper::toResponse);
    }

    @Override
    public OrderReviewRes sumaryOrder(UUID userId) {
        log.info("----------------------->");
        CartEntity cart = cartRepo.findByUserId(userId)
                .orElseThrow(()-> new BusinessException("Khong tin thay gio hang"));
        log.info("cart: {}", cart);
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal feeship = BigDecimal.ZERO;
        BigDecimal dis = BigDecimal.ZERO;

        //List<CartItemEntity> cartItems = cartItemRepo.findCartItems(cart.getId());

        //List<CartItemEntity> cartItems = cart.getCartItems();

        List<CartItemEntity> cartItems = cartItemRepo.findCartItems(cart.getId());

        for(CartItemEntity ci : cartItems){
            ProductVariantEntity variant = ci.getProductVariant();
            if(ci.getIsSelected()) {
                subtotal = subtotal.add(variant.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()))).subtract(dis);
            }
        }
        BigDecimal totalPrice = subtotal.subtract(dis).add(feeship);
        return new OrderReviewRes(subtotal, dis, feeship, totalPrice);
    }

    @Override
    @Transactional
    public String checkout(UUID userId, OrderReq req) {
        OrderEntity order = new OrderEntity();
        List<OrderItemEntity> orderItems = new ArrayList<>();

        CartEntity cart = cartRepo.findByUserId(userId)
                .orElseThrow(()-> new BusinessException("Khong tin thay gio hang"));
        UserEntity user = cart.getUser();
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
        // logic pricing
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal dis = BigDecimal.ZERO;
        BigDecimal feeship = BigDecimal.ZERO;
        List<CartDetailReq> items = req.getItems();

        List<UUID> variantId = items.stream().map(CartDetailReq::getVariantId).collect(Collectors.toList());

        Map<UUID, ProductVariantEntity> variants = variantRepo.findAllByIds(variantId).stream()
                .collect(Collectors.toMap(ProductVariantEntity::getId, v -> v));

        Map<UUID, InventoryEntity> inventoryMap = inventoryRepo.findByProductVariantIdIn(variantId).stream()
                .collect(Collectors.toMap(inv -> inv.getProductVariant().getId(), inv -> inv));

        for(CartDetailReq item : items){
            // map(k,v) -> v = get(k)
            ProductVariantEntity variant = variants.get(item.getVariantId());
            if(variant == null){
                throw new BusinessException("Khong tin thay san pham");
            }
            // kiem tra ton kho

//            int updatedRows = inventoryRepo.updateStock(item.getVariantId(), item.getQuantity());
//            if (updatedRows == 0) {
//                // Nếu không có bản ghi nào được update, tức là tồn kho không đủ
//                throw new BusinessException("Sản phẩm trong kho không đủ đáp ứng.");
//            }
            InventoryEntity inventory = inventoryMap.get(item.getVariantId());
            if (inventory == null) {
                throw new BusinessException("Khong tim thay ton kho");
            }

            if (inventory.getQuantityInStock() < item.getQuantity()) {
                throw new BusinessException(
                        "San pham trong kho khong du"
                );
            }

            inventory.setQuantityInStock(inventory.getQuantityInStock() - item.getQuantity());
//
            log.info("-----------------------------");
            OrderItemEntity oi = new OrderItemEntity();
            oi.setProductVariant(variant);
            oi.setPrice(variant.getPrice());
            oi.setQuantity(item.getQuantity());
            oi.setColor(variant.getColor());
            oi.setSize(variant.getSize());
            oi.setWeight(variant.getWeight());

            BigDecimal price = variant.getPrice();

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            subtotal = subtotal.add(itemTotal);

            // gắn order vào item
            oi.setOrder(order);
            orderItems.add(oi);

        }
        // xoa item khoi cart
        cartItemRepo.deleteByProductVariantIds(variantId);


        BigDecimal totalPrice = subtotal.add(feeship);
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
        }
        else {
            order.setPaymentMethod(req.getPaymentMethod());
            order.setPaymentStatus(PaymentStatus.COMPLETED);

        }
        // sinh ra 1 track number
        String trackNumber = "TN-" + System.currentTimeMillis();;
        order.setStatus(OrderStatus.PENDING);
        repo.saveAndFlush(order);
        // ghi vào tracklogs
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

    @Override
    @Transactional
    public void orderCancel(UUID orderId) {
        OrderEntity order = repo.findById(orderId)
                .orElseThrow(()-> new BusinessException("Not found order"));
        order.setStatus(OrderStatus.FAILED);
        repo.save(order);

    }

    @Override
    @Transactional
    public void updateStatus(UUID orderId, UpdateStatusReq req) {
        OrderEntity order = repo.findById(orderId)
                .orElseThrow(()-> new BusinessException("Not found order"));
        if (!OrderStateMachine.canTransition(order.getStatus(), req.getNewStatus())) {
            throw new BusinessException("Không thể chuyển từ " + order.getStatus() + " sang " + req.getNewStatus());

        }
        TrackLogEnitty log = new TrackLogEnitty();
        log.setOldStatus(order.getStatus());

        // payment method: cod
        if(order.getPaymentMethod() == PaymentMethod.COD &&
                req.getNewStatus() == OrderStatus.DELIVERED){
            order.setPaymentStatus(PaymentStatus.COMPLETED);
        }
        // set new status for order
        order.setStatus(req.getNewStatus());

        log.setNewStatus(req.getNewStatus());
        log.setOrder(order);
        log.setTrackNumber("TN-1789098721926");
        log.setLocation(req.getLocation());
        log.setNote(req.getNote());
        trackLogRepo.save(log);
    }

    @Override
    public OrderRes getOrderDetail(UUID orderId) {
        // find order by id
        OrderEntity order = repo.findById(orderId)
                .orElseThrow(()-> new BusinessException("Not found order"));
        // get information user
        UserEntity user = order.getUser();
        String fullName = user.getFirstName() + " " + user.getLastName();
        String phone = user.getPhone();
        UUID addressId = order.getAddress().getId();

        AddressEntity add = addressRepo.findById(addressId)
                .orElseThrow(()-> new BusinessException("Not found address"));
        AddressRes res = addressMapper.toResponse(add);

        // find order item by order id
        List<OrderItemEntity> items = orderItemRepo.findByOrderId(orderId);
        List<OrderItemRes> orderDetail = new ArrayList<>();
        for (OrderItemEntity item : items) {
            OrderItemRes oi = new OrderItemRes();
            oi.setColor(item.getColor());
            oi.setProductName(item.getProductVariant().getProduct().getProductName());
            oi.setSize(item.getSize());
            oi.setSize(item.getSize());
            oi.setUnitPrice(item.getPrice());
            orderDetail.add(oi);
        }

        return new OrderRes(fullName, phone, res, order.getSubtotal(), order.getDiscount(),order.getPriceShippment(), order.getTotal(), order.getPaymentMethod(), orderDetail);
    }

    @Override
    public Page<MyOrderRes> getOrderOfMe(UUID userId, Pageable pageable) {
        Page<OrderEntity> orders = repo.findByUserId(userId, pageable);
        return orders.map(mapper::toRes);
    }

    @Override
    public int getOrderIsShipping(UUID userId) {
        return repo.countOrderPending(userId);
    }

    @Override
    public int getOrderIsCompleted(UUID userId) {
        return repo.countOrderCompleted(userId);
    }

    @Override
    public OrderDBRes getDB() {
        int orderFailed = repo.countOrderFailed();
        int orderPending = repo.countOrderPending();
        int sumOrder = repo.sumQuantityOrder();
        int orderShipping = repo.countOrderShipping();
        BigDecimal totalRevenue = repo.totalRevenue();

        return new OrderDBRes(orderFailed, orderPending, orderShipping, sumOrder, totalRevenue);
    }

    @Override
    public Page<OrderRecentRes> getAllRecentOrder(Pageable pageable) {
        Page<OrderEntity> orders = repo.findAll(pageable);

        return orders.map(order -> {
            OrderRecentRes or = new OrderRecentRes();
            or.setOrderId(order.getId());
            or.setOrderStatus(order.getStatus());
            or.setAmount(order.getTotal());
            or.setCreatedAt(order.getCreatedAt());
            or.setCarrier("Express J&T");
            UserEntity user = order.getUser();
            or.setUserName(user.getFullname());

            return or;
        });
    }

    @Override
    public Page<OrderConfirmRes> getAllPendingOrder(Pageable pageable) {
        Page<OrderEntity> orders = repo.findByOrderStatus(pageable, OrderStatus.PENDING);

        return orders.map(order -> {
            OrderConfirmRes or = new OrderConfirmRes();
            UserEntity user = order.getUser();
            String fullName = user.getFirstName() + " " + user.getLastName();

            AddressEntity add = addressRepo.findById(order.getAddress().getId())
                    .orElseThrow(() -> new BusinessException("Not found address"));
            AddressRes res = addressMapper.toResponse(add);

            or.setAddress(res);
            or.setFullName(fullName);
            or.setTotal(order.getTotal());
            or.setMethod(order.getPaymentMethod());
            or.setStatus(order.getPaymentStatus());

            List<OrderItemEntity> items = orderItemRepo.findByOrderId(order.getId());
            List<ItemRes> orderDetail = new ArrayList<>();

            for (OrderItemEntity item : items) {
                ItemRes it = new ItemRes();
                it.setUnitPrice(item.getPrice());
                it.setQuantity(item.getQuantity());

                ProductVariantEntity variant = item.getProductVariant();
                ProductEntity product = variant.getProduct();

                InventoryEntity inventory = inventoryRepo.findByProductVariantId(variant.getId())
                        .orElseThrow(() -> new BusinessException("Không tìm thấy dữ liệu tồn kho"));

                String statusInventory = inventory.getQuantityInStock() < item.getQuantity()
                        ? "SOLD OUT" : "IN STOCK";

                it.setStatusInventory(statusInventory);
                it.setProductName(product.getProductName());

                orderDetail.add(it); // fix: add vào list
            }

            or.setItems(orderDetail); // gắn list item vào response
            return or;
        });
    }

    @Override
    public StatisticRes getStatistic(LocalDate createdAt) {
        int pending = repo.countOrderPendingToday(createdAt);
        int confirmed = repo.countOrderCompletedToday(createdAt);
        int total = repo.countOrderToday(createdAt);
        return new StatisticRes(pending,confirmed,total);
    }


}

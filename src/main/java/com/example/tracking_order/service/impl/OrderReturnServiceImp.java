package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.OrderReturnReq;
import com.example.tracking_order.dto.response.OrderRefundRes;
import com.example.tracking_order.dto.response.ReturnDetailRes;
import com.example.tracking_order.entity.*;
import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.PaymentStatus;
import com.example.tracking_order.enums.ReturnStatus;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.repository.*;
import com.example.tracking_order.service.IOrderReturnService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderReturnServiceImp implements IOrderReturnService {
    private final OrderReturnRepository repo;
    private final UserRepository userRepo;
    private final OrderRepository orderRepo;
    private final OrderItemReturnRepository oirRepo;
    private final OrderItemRepository orderItemRepo;


    private static final int RETURN_WINDOW_DAYS = 7;

    @Override
    @Transactional
    public ReturnDetailRes createOrderReturn(UUID userId,OrderReturnReq req) {
        // find user
        log.info("query 1: ");
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new BusinessException("User not found"));
        log.info("query 2: ");
        OrderEntity order = orderRepo.findById(req.getOrderId()).orElseThrow(()-> new BusinessException("Order not found"));
        // check status order
        if (order.getPaymentStatus() != PaymentStatus.COMPLETED
                || order.getStatus() != OrderStatus.DELIVERED
                ) {
            throw new BusinessException("Chỉ những đơn hàng đã giao hoặc đã thanh toán mới được hoàn trả.");
        }

        // check time returning
        if(order.getUpdatedAt().plusDays(RETURN_WINDOW_DAYS).isBefore(LocalDateTime.now()))
        {
            throw new BusinessException("Đã quá thời hạn 7 ngày đổi trả sản phẩm");
        }
        OrderReturnEntity entity = new OrderReturnEntity();
        String code = "#RET-" + System.currentTimeMillis();
        entity.setCode(code);
        entity.setUser(user);
        entity.setOrder(order);
        entity.setReason(req.getReason());
        entity.setReturnStatus(ReturnStatus.Pending);
        entity.setOriginType(OriginType.CUSTOMER_REQUESTED);


        List<OrderItemReturnEntity> returnItems = new ArrayList<>();
        BigDecimal totalRefundAmount = BigDecimal.ZERO;
        log.info("query -------------: ");
        List<UUID> itemIds = req.getItems().stream().map(item -> item.getOrderItemId()).collect(Collectors.toList());
        log.info("query 3: ");
        Map<UUID, OrderItemEntity> orderItem = orderItemRepo.findAllByIds(itemIds)
                .stream()
                .collect(Collectors.toMap(item -> item.getId(), item -> item ));
        // response
        List<ReturnDetailRes.Item> res = new ArrayList<>();
        // loop
        for(OrderReturnReq.Item item : req.getItems())
        {
            OrderItemEntity orderItemEntity = orderItem.get(item.getOrderItemId());
            ReturnDetailRes.Item temp = new ReturnDetailRes.Item();
            temp.setProductName(orderItemEntity.getProductVariant().getProduct().getProductName());
            temp.setQuantity(item.getQuantity());
            res.add(temp);
            OrderItemReturnEntity returnEntity = new OrderItemReturnEntity();
            returnEntity.setOrderItem(orderItemEntity);
            returnEntity.setOrderReturn(entity);
            // check quantity
            if (item.getQuantity() < 0 || item.getQuantity() > orderItemEntity.getQuantity()) {
                throw new BusinessException("Số lượng trả không hợp lệ");
            }
            returnEntity.setQuantity(item.getQuantity());
            BigDecimal itemRefund = orderItemEntity.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalRefundAmount = totalRefundAmount.add(itemRefund);

            returnItems.add(returnEntity);
        }
        entity.setTotalRefund(totalRefundAmount);
        order.setStatus(OrderStatus.RETURNING);
        repo.save(entity);
        oirRepo.saveAll(returnItems);
        return new ReturnDetailRes(code, req.getReason(), user.getFullname(), res ,entity.getCreatedAt(), totalRefundAmount);
    }

    @Override
    public Page<OrderRefundRes> getOrderReturnsByStatus(Pageable pageable, ReturnStatus status) {
        Page<OrderReturnEntity> orders = repo.findByReturnStatus(pageable, status);
        List<OrderRefundRes> res = new ArrayList<>();
        for(OrderReturnEntity order : orders)
        {
            String codeOrder = "#ORD-" + System.currentTimeMillis();
            OrderRefundRes temp = new OrderRefundRes();
            temp.setCode(order.getCode());
            temp.setOrderId(codeOrder);
            temp.setStatus(order.getReturnStatus());
            temp.setReason(order.getReason());
            temp.setOriginType(order.getOriginType());
            temp.setFullName(order.getUser().getFullname());

            res.add(temp);
        }
        return new PageImpl<>(res, pageable, orders.getTotalElements());
    }

    @Override
    public int countReturnPending(ReturnStatus status) {
        return repo.countReturnStatus(status);
    }

    @Override
    public BigDecimal totalRefund() {
        return repo.totalRefund();
    }

    @Override
    public int countAllReturns() {
        return repo.countAllReturns();
    }

    @Override
    public ReturnDetailRes getReturnDetail(UUID returnId) {
        OrderReturnEntity order = repo.findById(returnId)
                .orElseThrow(()-> new BusinessException("Not found"));

        UserEntity user = order.getUser();
        List<OrderItemReturnEntity> items = order.getItems();
        List<ReturnDetailRes.Item> res = new ArrayList<>();

        for(OrderItemReturnEntity item : items)
        {
            OrderItemEntity or = item.getOrderItem();
            ReturnDetailRes.Item temp = new ReturnDetailRes.Item();
            temp.setProductName(or.getProductVariant().getProduct().getProductName());
            temp.setQuantity(item.getQuantity());

            res.add(temp);
        }
        String code = "#ORD-" + System.currentTimeMillis();
        return new ReturnDetailRes(code, order.getReason(), user.getFullname(), res ,order.getCreatedAt(), order.getTotalRefund());
    }
}

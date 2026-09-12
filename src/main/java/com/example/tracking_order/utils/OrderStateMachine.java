package com.example.tracking_order.utils;

import com.example.tracking_order.enums.OrderStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStateMachine {
    private static final Map<OrderStatus, Set<OrderStatus>> transitions = new HashMap<>();
    static {
        transitions.put(OrderStatus.PENDING, Set.of(OrderStatus.CONFIRMED));
        transitions.put(OrderStatus.CONFIRMED, Set.of(OrderStatus.PICKING));
        transitions.put(OrderStatus.PICKING, Set.of(OrderStatus.SHIPPING));
        transitions.put(OrderStatus.SHIPPING, Set.of(OrderStatus.DELIVERED, OrderStatus.FAILED));
        transitions.put(OrderStatus.FAILED, Set.of(OrderStatus.RETURNING, OrderStatus.REATTEMPT));
        transitions.put(OrderStatus.REATTEMPT, Set.of(OrderStatus.SHIPPING));
        transitions.put(OrderStatus.RETURNING, Set.of()); // kết thúc
        transitions.put(OrderStatus.DELIVERED, Set.of()); // kết thúc
    }
    // Kiểm tra có thể chuyển trạng thái không
    public static boolean canTransition(OrderStatus current, OrderStatus next) {
        return transitions.getOrDefault(current, Collections.emptySet()).contains(next);
    }
}

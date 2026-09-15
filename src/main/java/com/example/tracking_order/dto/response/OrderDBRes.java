package com.example.tracking_order.dto.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderDBRes {
    private int orderFailed;
    private int orderPending;

    public OrderDBRes(int orderFailed, int orderPending, int orderShipping, int sumOrder, BigDecimal totalRevenue) {
        this.orderFailed = orderFailed;
        this.orderPending = orderPending;
        this.orderShipping = orderShipping;
        this.sumOrder = sumOrder;
        this.totalRevenue = totalRevenue;
    }

    private int orderShipping;
    private int sumOrder;
    private BigDecimal totalRevenue;
}

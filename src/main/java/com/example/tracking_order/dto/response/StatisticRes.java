package com.example.tracking_order.dto.response;

import lombok.Data;

@Data
public class StatisticRes {
    private int orderPending;
    private int orderCompleted;
    private int totalOrder;

    public StatisticRes(int orderPending, int orderCompleted, int totalOrder) {
        this.orderPending = orderPending;
        this.orderCompleted = orderCompleted;
        this.totalOrder = totalOrder;
    }
}

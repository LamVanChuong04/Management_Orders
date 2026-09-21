package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class OrderReturnReq {
    private UUID orderId;
    private String reason;
    private List<Item> items;
    @Data
    public static class Item{
        @NotNull(message = "Order Item id không được để trống")
        private UUID orderItemId;

        @NotNull(message = "Số lượng trả không được để trống")
        private Integer quantity;
    }
}

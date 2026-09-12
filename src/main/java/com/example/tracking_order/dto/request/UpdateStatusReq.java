package com.example.tracking_order.dto.request;

import com.example.tracking_order.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStatusReq {
    @NotNull(message = "Order status must not be null")
    private OrderStatus newStatus;
    @NotEmpty(message = "Note name must be not empty")
    @Size(min = 5, max = 100, message = "Note must certain the least 5 characters")
    private String note;
    private String location;
}

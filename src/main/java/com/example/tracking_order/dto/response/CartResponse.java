package com.example.tracking_order.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;
@Data
public class CartResponse {
    private UUID userId;
}

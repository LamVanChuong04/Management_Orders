package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class CartReq {
    private UUID userId;
    private UUID productVariantId;
    private Integer quantity;
}

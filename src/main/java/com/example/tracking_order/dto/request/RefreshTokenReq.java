package com.example.tracking_order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenReq {
    private String refreshToken;
}

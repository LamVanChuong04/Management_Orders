package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;
import lombok.Data;

import java.util.UUID;
@Data
public class OrderRefundRes {
    private String code;
    private String orderId;
    private String fullName;
    private String reason;
    private ReturnStatus status;
    private OriginType originType;
}

package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderReturnRes {
    private String code;
    private String reason;
    private OriginType originType;
    private ReturnStatus returnStatus;
    private String fullname;
    private BigDecimal refundAmount;
}

package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;
import lombok.Data;

@Data
public class OrderReturnRes {
    private String code;
    private String reason;
    private OriginType originType;
    private ReturnStatus returnStatus;
    private String fullname;
}

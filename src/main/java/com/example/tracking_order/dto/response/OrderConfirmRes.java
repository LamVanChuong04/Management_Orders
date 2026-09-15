package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.PaymentMethod;
import com.example.tracking_order.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderConfirmRes {
    private String fullName;
    private AddressRes address;
    private PaymentMethod method;
    private PaymentStatus status;
    private BigDecimal total;
    private List<ItemRes> items;
}

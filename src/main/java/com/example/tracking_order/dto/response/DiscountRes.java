package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.DiscountStatus;
import com.example.tracking_order.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DiscountRes {
    private String name;
    private String description;
    private DiscountStatus status;
    private BigDecimal discountValue;
    private DiscountType discountType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime HSD;
}

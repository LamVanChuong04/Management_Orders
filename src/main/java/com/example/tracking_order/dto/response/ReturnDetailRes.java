package com.example.tracking_order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ReturnDetailRes {
    private String code;
    private String reason;
    private String fullName;
    private List<Item> items;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createdAt;
    @Data
    public static class Item {
        private String productName;
        private Integer quantity;
    }
    private BigDecimal priceRefund;
}

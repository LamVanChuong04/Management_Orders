package com.example.tracking_order.dto.response;

import com.example.tracking_order.dto.request.ItemProducts;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderReviewRes {
    private String fullName;
    private AddressRes addressRes;

    private List<ItemProducts> items;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal shipping;
    private BigDecimal totalPrice;


}

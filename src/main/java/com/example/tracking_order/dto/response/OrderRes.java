package com.example.tracking_order.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRes {
    private UUID userId;
    private AddressRes address;
    private BigDecimal total;
    private BigDecimal shipping;
    private BigDecimal subtotal;
    private BigDecimal itemDiscount;
    private String paymentMethod;

    /* {
    userId:
    subtotal:
    shipping:
    discount:
    total:
    paymentMethod:
    address:
    {
        province:
        district:
        ward:
        street:
    }


    } */
}

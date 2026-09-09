package com.example.tracking_order.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRes {
    private String fullName;
    private String phoneNumber;
    private AddressRes address;
    private BigDecimal total;
    private BigDecimal feeship;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private String paymentMethod;

    /* {
    fullName:
    phone:
    address:
    {
        province:
        district:
        ward:
        street:
    }
    paymentMethod:
    subtotal:
    shipping:
    discount:
    total:

    } */
}

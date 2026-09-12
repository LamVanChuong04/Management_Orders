package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderRes {
    private String fullName;
    private String phoneNumber;
    private AddressRes address;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal feeship;
    private BigDecimal total;
    private String paymentMethod;
    private List<OrderItemRes> orderDetails;

    public OrderRes(String fullName, String phone, AddressRes res, BigDecimal subtotal, BigDecimal discount, BigDecimal priceShippment, BigDecimal total, PaymentMethod paymentMethod, List<OrderItemRes> orderDetail) {
        this.fullName = fullName;
        this.phoneNumber = phone;
        this.address = res;
        this.subtotal = subtotal;
        this.discount = discount;
        this.feeship = priceShippment;
        this.total = total;
        this.paymentMethod = paymentMethod.name();
        this.orderDetails = orderDetail;
    }

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

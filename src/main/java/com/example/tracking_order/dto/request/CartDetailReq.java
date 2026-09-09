package com.example.tracking_order.dto.request;

import com.example.tracking_order.dto.response.CartItemRes;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CartDetailReq {
    private String productName;
    private UUID variantId;
    private String size;
    private BigDecimal price;
    private int quantity;
    private ShopDiscount discount;

    /*
    * methodPayment:
    * cart_item:
    * [
    *   {
    *       variantId:
    *       productName:
    *       price:
    *       quantity:
    *       shop_discount:
    *       {
    *           shop_id:
    *           discount_id:
    *           code:
    *       }
    *
    *   }
    *
    * ]
    *
    *
    * */
}

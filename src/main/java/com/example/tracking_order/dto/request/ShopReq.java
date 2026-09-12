package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShopReq {
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 6, max = 30, message = "Name must certain the least 6 characters")
    private String shopName;
    @Email(message = "Email not blank")
    @Pattern(
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "Email is not correct"
    )
    private String email;
}

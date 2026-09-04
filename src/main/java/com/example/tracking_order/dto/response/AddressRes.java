package com.example.tracking_order.dto.response;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRes {
    private String province;
    private String district;
    private String ward;
    private String street;
    private Boolean isDefault;

}

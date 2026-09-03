package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class WarehouseRequest {
    @NotEmpty(message = "Warehouse name not empty")
    @Length(min = 3, max = 100, message = "Warehouse name must contain 3 characters")
    private String name;
    @NotEmpty(message = "province name not empty")
    @Length(min = 3, max = 100, message = "province must contain 3 characters")
    private String province;
    @NotEmpty(message = "street name not empty")
    @Length(min = 3, max = 100, message = "street name must contain 3 characters")
    private String street;
    @NotEmpty(message = "ward name not empty")
    @Length(min = 3, max = 100, message = "ward name must contain 3 characters")
    private String ward;
    @NotEmpty(message = "district name not empty")
    @Length(min = 3, max = 100, message = "district must contain 3 characters")
    private String district;
}

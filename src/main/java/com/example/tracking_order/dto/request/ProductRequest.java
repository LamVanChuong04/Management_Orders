package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductRequest {
    @NotEmpty(message = "Product name must not be empty")
    @Size(min = 3, max = 150)
    private String productName;

    @NotEmpty
    @NotBlank(message = "Description must not be blank")
    @Size(min = 3, max = 250)
    private String description;

    @NotNull(message = "Category must not be null")
    private UUID categoryId;
}

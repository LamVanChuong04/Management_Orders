package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    @NotEmpty
    @Size(min = 3, max = 50)
//    @Pattern(
//            regexp = "^\\p{L}+$",
//            message = "Category name must contain only letters and be 3-50 characters long"
//    )
    private String name;
    @NotEmpty
    @Size(min = 5, max = 20)
    @Pattern(
            regexp = "^[A-Za-z0-9_ ]+$",
            message = "Parent id must contain only letters and be 5-20 characters long"
    )
    private String parentId;
}

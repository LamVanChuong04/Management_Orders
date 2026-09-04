package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleReq {
    @NotEmpty
    @Length(min = 2, max = 20)
    @Pattern(
            regexp = "^[A-Za-z0-9_]+$",
            message = "Role name must contain only letters and be 3-20 characters long"
    )
    private String name;
}

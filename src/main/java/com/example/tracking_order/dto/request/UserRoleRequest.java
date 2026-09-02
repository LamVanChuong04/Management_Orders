package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {
    @NotEmpty
    @Length(max = 36)
    private UUID userId;

    @NotEmpty
    @Length(max = 36)
    private UUID roleId;

}

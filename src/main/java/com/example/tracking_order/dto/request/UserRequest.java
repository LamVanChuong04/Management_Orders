package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotEmpty
    @Length(min = 2, max = 20)
    @Pattern(
            regexp = "^[A-Za-z0-9]+$",
            message = "First name must contain only letters and be 2-20 characters long"
    )
    private String firstName;
    @NotEmpty
    @Length(min = 2, max = 20)
    @Pattern(
            regexp = "^[A-Za-z0-9]+$",
            message = "Last name must contain only letters and be 2-20 characters long"
    )
    private String lastName;
    @Email(message = "Email not blank")
    @Pattern(
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "Email is not correct"
    )
    private String email;
    @NotEmpty
    @Min(value = 8, message = "Password must contain least 8 characters")
    private String password;
    @NotEmpty
    @Pattern(
            regexp = "^\\d{10}$",
            message = "Phone must contain 10 numbers"
    )
    private String phone;

}

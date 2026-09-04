package com.example.tracking_order.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRes {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String fullName;
}

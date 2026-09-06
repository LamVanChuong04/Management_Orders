package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.Gender;
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
    private String userName;
    private Gender gender;
}

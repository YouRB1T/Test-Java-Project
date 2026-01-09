package com.zhkh.apigateway.api.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegistrationResidentRequest {
    private String userName;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String middleName;
    private UUID apartmentId;
    private UUID userId;
    private String phone;
}

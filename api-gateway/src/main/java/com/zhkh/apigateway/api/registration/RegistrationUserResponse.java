package com.zhkh.apigateway.api.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserResponse {
    private UUID userId;
    private String userName;
    private String email;
    private String password;
}

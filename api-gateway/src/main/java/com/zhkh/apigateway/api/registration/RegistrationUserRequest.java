package com.zhkh.apigateway.api.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserRequest {
    private String userName;
    private String email;
    private String password;
}

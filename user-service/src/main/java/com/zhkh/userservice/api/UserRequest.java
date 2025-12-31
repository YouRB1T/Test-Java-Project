package com.zhkh.userservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String userName;
    private String email;
    private String password;
}

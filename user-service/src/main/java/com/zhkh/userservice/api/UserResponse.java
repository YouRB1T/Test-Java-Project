package com.zhkh.userservice.api;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String userName;
    private String email;
    private String password;
    private Date dateCreate;
    private Date dateUpdate;
}

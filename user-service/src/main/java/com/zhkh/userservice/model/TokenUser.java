package com.zhkh.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TokenUser {
    private UUID userId;
    private String token;
}

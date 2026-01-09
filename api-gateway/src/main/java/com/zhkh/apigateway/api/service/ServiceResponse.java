package com.zhkh.apigateway.api.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {
    private UUID serviceId;
    private String serviceName;
    private String description;
    private Boolean isActive;
}

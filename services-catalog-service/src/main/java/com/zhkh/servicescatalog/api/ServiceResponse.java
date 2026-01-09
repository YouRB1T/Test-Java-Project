package com.zhkh.servicescatalog.api;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private UUID serviceId;

    private String serviceName;

    private String description;

    private Boolean isActive;
}


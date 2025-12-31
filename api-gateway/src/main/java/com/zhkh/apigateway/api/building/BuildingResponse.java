package com.zhkh.apigateway.api.building;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BuildingResponse {
    private UUID id;
    private String address;
}

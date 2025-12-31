package com.zhkh.building.api;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BuildingResponse {
    private UUID id;
    private String address;
}

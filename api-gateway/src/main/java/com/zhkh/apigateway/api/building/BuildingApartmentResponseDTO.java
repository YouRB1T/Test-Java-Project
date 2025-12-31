package com.zhkh.apigateway.api.building;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingApartmentResponseDTO {
    private BuildingResponse building;
    private List<UUID> apartmentIds;
}

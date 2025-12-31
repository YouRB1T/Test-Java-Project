package com.zhkh.building.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingApartmentsResponse {
    private BuildingResponse building;
    private List<UUID> apartmentIds;
}

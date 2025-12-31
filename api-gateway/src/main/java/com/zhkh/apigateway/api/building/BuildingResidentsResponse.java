package com.zhkh.apigateway.api.building;

import com.zhkh.apigateway.api.resident.ResidentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingResidentsResponse {
    private BuildingResponse building;
    private List<ResidentResponse> residents;
}

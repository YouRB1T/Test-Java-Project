package com.zhkh.apigateway.api.building;

import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingApartmentsResponse {
    private BuildingResponse building;
    private List<ApartmentResponse> apartments;
}

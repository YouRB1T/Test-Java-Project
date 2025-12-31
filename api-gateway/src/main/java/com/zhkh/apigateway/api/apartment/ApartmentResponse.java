package com.zhkh.apigateway.api.apartment;

import com.zhkh.apigateway.api.building.BuildingResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentResponse {

    private UUID apartmentId;

    private Integer apartmentNumber;

    private BuildingResponse building;

    private BigDecimal squareMeters;

    private Integer numberOfRooms;
}

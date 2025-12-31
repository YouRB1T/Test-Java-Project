package com.zhkh.apigateway.api.apartment;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ApartmentResponseDTO {
    private UUID apartmentId;

    private Integer apartmentNumber;

    private UUID buildingId;

    private BigDecimal squareMeters;

    private Integer numberOfRooms;
}

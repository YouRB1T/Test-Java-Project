package com.zhkh.apartment.api;

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

    private UUID buildingId;

    private BigDecimal squareMeters;

    private Integer numberOfRooms;
}


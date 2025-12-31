package com.zhkh.apartment.api;

import jakarta.validation.constraints.NotNull;
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
public class ApartmentRequest {

    @NotNull
    private Integer apartmentNumber;

    @NotNull
    private UUID buildingId;

    private BigDecimal squareMeters;

    private Integer numberOfRooms;
}


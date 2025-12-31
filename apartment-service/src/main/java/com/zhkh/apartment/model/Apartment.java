package com.zhkh.apartment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "apartments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {

    @Id
    @Column(name = "apartment_id", nullable = false)
    @GeneratedValue
    private UUID apartmentId;

    @Column(name = "apartment_number", nullable = false)
    private Integer apartmentNumber;

    @Column(name = "building_id", nullable = false)
    private UUID buildingId;

    @Column(name = "square_meters")
    private BigDecimal squareMeters;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;
}


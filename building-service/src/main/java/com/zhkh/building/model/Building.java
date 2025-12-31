package com.zhkh.building.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "building_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "address", nullable = false)
    private String address;
}


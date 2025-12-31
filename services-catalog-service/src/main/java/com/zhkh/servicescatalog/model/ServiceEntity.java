package com.zhkh.servicescatalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {

    @Id
    @Column(name = "service_id", nullable = false)
    private UUID serviceId;

    @Column(name = "service_name", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;
}


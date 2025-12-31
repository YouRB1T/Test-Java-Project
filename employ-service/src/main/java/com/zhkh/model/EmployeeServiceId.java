package com.zhkh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeServiceId implements Serializable {

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "service_id", nullable = false)
    private UUID serviceId;
}


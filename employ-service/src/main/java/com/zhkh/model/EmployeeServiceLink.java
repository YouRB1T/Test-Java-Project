package com.zhkh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeServiceLink {

    @EmbeddedId
    private EmployeeServiceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;
}


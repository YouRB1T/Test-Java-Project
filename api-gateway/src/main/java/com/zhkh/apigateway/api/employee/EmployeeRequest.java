package com.zhkh.apigateway.api.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer experienceYears;
    private UUID officeId;
    private UUID userId;
    private String phone;
    private String email;
    private LocalDate hireDate;
}

package com.zhkh.apigateway.api.employee;

import com.zhkh.apigateway.api.office.OfficeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer experienceYears;
    private UUID officeId;
    private String phone;
    private String email;
    private LocalDate hireDate;
}
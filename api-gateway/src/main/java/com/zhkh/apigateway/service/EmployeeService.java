package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.employee.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeResponse> getEmployeesByOffice(UUID officeId);
}

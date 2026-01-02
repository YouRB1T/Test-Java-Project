package com.zhkh.service;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse getById(UUID id);

    List<EmployeeResponse> getAll();

    List<EmployeeResponse> getByOffice(UUID officeId);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(UUID id, EmployeeRequest request);

    void delete(UUID id);

    Set<UUID> getServices(UUID employeeId);

    Set<UUID> addService(UUID employeeId, UUID serviceId);

    Set<UUID> removeService(UUID employeeId, UUID serviceId);
}


package com.zhkh.apigateway.service;

import com.zhkh.apigateway.api.employee.EmployeeRequest;
import com.zhkh.apigateway.api.employee.EmployeeResponse;

import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EmployeeService {

    Mono<EmployeeResponse> getById(UUID id);

    Mono<List<EmployeeResponse>> getAll();

    Mono<EmployeeResponse> create(EmployeeRequest request);

    Mono<EmployeeResponse> update(UUID id, EmployeeRequest request);

    Mono<Void> delete(UUID id);

    Mono<Set<UUID>> getServices(UUID employeeId);

    Mono<Set<UUID>> addService(UUID employeeId, UUID serviceId);

    Mono<Set<UUID>> removeService(UUID employeeId, UUID serviceId);

    Mono<List<EmployeeResponse>> getEmployeesByOffice(UUID officeId);
}


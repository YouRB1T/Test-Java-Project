package com.zhkh.apigateway.controller;

import com.zhkh.apigateway.api.employee.EmployeeRequest;
import com.zhkh.apigateway.api.employee.EmployeeResponse;
import com.zhkh.apigateway.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeGatewayController {

    private final EmployeeService employeeService;

    @GetMapping
    public Mono<ResponseEntity<List<EmployeeResponse>>> getAll() {
        return employeeService.getAll()
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EmployeeResponse>> getById(@PathVariable UUID id) {
        return employeeService.getById(id)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<EmployeeResponse>> create(
            @RequestBody EmployeeRequest request
    ) {
        return employeeService.create(request)
                .map(response -> ResponseEntity.status(201).body(response));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<EmployeeResponse>> update(
            @PathVariable UUID id,
            @RequestBody EmployeeRequest request
    ) {
        return employeeService.update(id, request)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID id) {
        return employeeService.delete(id)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/office/{officeId}")
    public Mono<ResponseEntity<List<EmployeeResponse>>> getByOffice(
            @PathVariable UUID officeId
    ) {
        return employeeService.getEmployeesByOffice(officeId)
                .map(ResponseEntity::ok);
    }
}


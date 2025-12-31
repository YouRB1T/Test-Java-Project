package com.zhkh.controller;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;
import com.zhkh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employService;

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable UUID id) {
        return employService.getById(id);
    }

    @PostMapping
    public EmployeeResponse create(@RequestBody EmployeeRequest request) {
        return employService.create(request);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable UUID id,
            @RequestBody EmployeeRequest request) {
        return employService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        employService.delete(id);
    }

    @GetMapping("/{id}/services")
    public Set<UUID> getServices(@PathVariable UUID id) {
        return employService.getServices(id);
    }

    @PostMapping("/{id}/services")
    public void addService(
            @PathVariable UUID id,
            @RequestParam UUID serviceId) {
        employService.addService(id, serviceId);
    }

    @DeleteMapping("/{id}/services/{serviceId}")
    public void removeService(
            @PathVariable UUID id,
            @PathVariable UUID serviceId) {
        employService.removeService(id, serviceId);
    }

    @GetMapping("/offices/{officeId}")
    public List<EmployeeResponse> getByOffice(@PathVariable UUID officeId) {
        return employService.getAll()
                .stream()
                .filter(e -> e.getOfficeId().equals(officeId))
                .toList();
    }
}


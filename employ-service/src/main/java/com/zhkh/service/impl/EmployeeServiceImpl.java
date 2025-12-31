package com.zhkh.service.impl;

import com.zhkh.api.EmployResponse;
import com.zhkh.mapper.EmployeeMapper;
import com.zhkh.model.Employ;
import com.zhkh.repository.EmployRepository;
import com.zhkh.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployResponse getById(Long id) {
        Employ employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeMapper.toResponseList(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employeeMapper.updateEntity(request, employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Set<Long> getServices(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"))
                .getServices()
                .stream()
                .map(ServiceEntity::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public void addService(Long employeeId, Long serviceId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        employee.getServices().add(service);
    }

    @Override
    public void removeService(Long employeeId, Long serviceId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employee.getServices().removeIf(s -> s.getId().equals(serviceId));
    }
}



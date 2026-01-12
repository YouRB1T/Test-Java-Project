package com.zhkh.service.impl;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;
import com.zhkh.mapper.EmployeeMapper;
import com.zhkh.model.Employee;
import com.zhkh.model.EmployeeServiceId;
import com.zhkh.model.EmployeeServiceLink;
import com.zhkh.repository.EmployeeRepository;
import com.zhkh.repository.EmployeeServiceLinkRepository;
import com.zhkh.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeServiceLinkRepository employeeServiceLinkRepository;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeResponse getById(UUID id) {
        return mapper.toResponse(
                employeeRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Employee not found")
                )
        );
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return mapper.toResponseList(
                employeeRepository.findAll()
        );
    }

    @Override
    public List<EmployeeResponse> getByOffice(UUID officeId) {
        return mapper.toResponseList(
                employeeRepository.findByOfficeId(officeId)
        );
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        return mapper.toResponse(
                employeeRepository.save(
                        mapper.toEntity(
                                request
                        )
                )
        );
    }

    @Override
    public EmployeeResponse update(UUID id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found")
        );
        mapper.update(request, employee);
        return mapper.toResponse(employee);
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.delete(
                employeeRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Employee not found")
                )
        );
    }

    @Override
    public Set<UUID> getServices(UUID employeeId) {
        return employeeServiceLinkRepository.findByEmployee_Id(employeeId)
                .stream()
                .map(link -> link.getId().getServiceId())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UUID> addService(UUID employeeId, UUID serviceId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employeeServiceLinkRepository
                .existsByIdEmployeeIdAndIdServiceId(employeeId, serviceId)) {
            return getServices(employeeId);
        }

        EmployeeServiceId id = new EmployeeServiceId(employeeId, serviceId);

        EmployeeServiceLink link = new EmployeeServiceLink();
        link.setId(id);
        link.setEmployee(employee);

        employeeServiceLinkRepository.save(link);

        return getServices(employeeId);
    }

    @Override
    public Set<UUID> removeService(UUID employeeId, UUID serviceId) {
        if (!employeeServiceLinkRepository
                .existsByIdEmployeeIdAndIdServiceId(employeeId, serviceId)) {
            return getServices(employeeId);
        }

        employeeServiceLinkRepository
                .deleteByIdEmployeeIdAndIdServiceId(employeeId, serviceId);

        return getServices(employeeId);
    }
}



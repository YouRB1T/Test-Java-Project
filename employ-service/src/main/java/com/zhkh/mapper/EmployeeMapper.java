package com.zhkh.mapper;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;
import com.zhkh.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse toResponse(Employee employ);

    List<EmployeeResponse> toResponseList(List<Employee> employs);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Employee toEntity(EmployeeRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void update(EmployeeRequest request, @MappingTarget Employee employee);
}


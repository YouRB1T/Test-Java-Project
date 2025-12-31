package com.zhkh.mapper;

import com.zhkh.api.EmployeeRequest;
import com.zhkh.api.EmployeeResponse;
import com.zhkh.model.Employ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse toResponse(Employ employ);

    List<EmployeeResponse> toResponseList(List<Employ> employs);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Employ toEntity(EmployeeRequest request);
}


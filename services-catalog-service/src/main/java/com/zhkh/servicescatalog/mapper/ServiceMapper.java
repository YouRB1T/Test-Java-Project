package com.zhkh.servicescatalog.mapper;

import com.zhkh.servicescatalog.api.ServiceRequest;
import com.zhkh.servicescatalog.api.ServiceResponse;
import com.zhkh.servicescatalog.model.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {

    ServiceResponse toResponse(ServiceEntity serviceEntity);

    List<ServiceResponse> toResponse(List<ServiceEntity> services);

    ServiceEntity toEntity(ServiceRequest request);
}


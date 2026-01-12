package com.zhkh.apigateway.mapper;

import com.zhkh.apigateway.api.apartment.ApartmentResponse;
import com.zhkh.apigateway.api.apartment.ApartmentResponseDTO;
import com.zhkh.apigateway.api.building.BuildingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    @Mapping(target = "building", source = "building")
    ApartmentResponse toResponse(
            ApartmentResponseDTO apartment,
            BuildingResponse building
    );
}

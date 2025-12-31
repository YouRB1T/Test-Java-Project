package com.zhkh.office.mapper;

import com.zhkh.office.api.OfficeRequest;
import com.zhkh.office.api.OfficeResponse;
import com.zhkh.office.model.Office;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OfficeMapper {
    OfficeResponse toResponse(Office office);
    List<OfficeResponse> toResponseList(List<Office> offices);
    Office toEntity(OfficeRequest request);
}


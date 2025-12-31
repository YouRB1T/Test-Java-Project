package org.zhkh.resident.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.zhkh.resident.api.request.ResidentRequest;
import org.zhkh.resident.api.response.ResidentResponse;
import org.zhkh.resident.model.Resident;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResidentMapper {
    ResidentResponse toResidentResponse(Resident resident);
    List<ResidentResponse> toResidentResponse(List<Resident> residents);
    Resident toResident(ResidentRequest residentRequest);
}

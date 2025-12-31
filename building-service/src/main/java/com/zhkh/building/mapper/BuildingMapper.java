package com.zhkh.building.mapper;

import com.zhkh.building.api.BuildingResponse;
import com.zhkh.building.model.Building;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BuildingMapper {
    BuildingResponse toBuildingResponse(Building building);
    List<BuildingResponse> toBuildingResponseList(List<Building> buildings);
}

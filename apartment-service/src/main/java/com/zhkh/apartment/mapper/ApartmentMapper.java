package com.zhkh.apartment.mapper;

import com.zhkh.apartment.api.ApartmentRequest;
import com.zhkh.apartment.api.ApartmentResponse;
import com.zhkh.apartment.model.Apartment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApartmentMapper {
    ApartmentResponse toApartmentResponse(Apartment apartment);
    List<ApartmentResponse> toApartmentResponse(List<Apartment> apartment);
    Apartment toApartment(ApartmentRequest apartmentRequest);
}

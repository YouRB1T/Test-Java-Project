package com.zhkh.apartment.service.impl;


import com.zhkh.apartment.api.ApartmentRequest;
import com.zhkh.apartment.api.ApartmentResponse;
import com.zhkh.apartment.mapper.ApartmentMapper;
import com.zhkh.apartment.model.Apartment;
import com.zhkh.apartment.repository.ApartmentRepository;
import com.zhkh.apartment.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    public List<ApartmentResponse> findAll() {
        return apartmentMapper.toApartmentResponse(
                apartmentRepository.findAll()
        );
    }

    public ApartmentResponse findById(UUID id) {
        return apartmentMapper.toApartmentResponse(
                apartmentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException("Apartment not found")
                        )
        );
    }

    public ApartmentResponse create(ApartmentRequest apartment) {
        return apartmentMapper.toApartmentResponse(
                apartmentRepository.save(
                        apartmentMapper.toApartment(apartment)
                )
        );
    }

    public ApartmentResponse update(UUID id, ApartmentRequest updated) {
        Apartment existing = apartmentRepository.findById(id)
                .orElseThrow();

        existing.setApartmentNumber(updated.getApartmentNumber());
        existing.setBuildingId(updated.getBuildingId());
        existing.setSquareMeters(updated.getSquareMeters());
        existing.setNumberOfRooms(updated.getNumberOfRooms());

        return apartmentMapper.toApartmentResponse(
                apartmentRepository.save(existing)
        );
    }

    public void delete(UUID id) {
        apartmentRepository.deleteById(id);
    }
}

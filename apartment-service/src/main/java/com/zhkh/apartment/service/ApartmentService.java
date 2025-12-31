package com.zhkh.apartment.service;

import com.zhkh.apartment.api.ApartmentRequest;
import com.zhkh.apartment.api.ApartmentResponse;
import com.zhkh.apartment.model.Apartment;
import com.zhkh.apartment.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ApartmentService {
    public List<ApartmentResponse> findAll();

    public ApartmentResponse findById(UUID id);

    public ApartmentResponse create(ApartmentRequest apartment);

    public ApartmentResponse update(UUID id, ApartmentRequest updated);

    public void delete(UUID id);
}


package com.zhkh.office.service.impl;

import com.zhkh.office.api.OfficeRequest;
import com.zhkh.office.api.OfficeResponse;
import com.zhkh.office.mapper.OfficeMapper;
import com.zhkh.office.model.Office;
import com.zhkh.office.repository.OfficeRepository;
import com.zhkh.office.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository repository;
    private final OfficeMapper mapper;

    @Override
    public List<OfficeResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public OfficeResponse getById(UUID id) {
        Office office = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found"));

        return mapper.toResponse(office);
    }

    @Override
    public OfficeResponse create(OfficeRequest request) {
        Office office = mapper.toEntity(request);
        return mapper.toResponse(repository.save(office));
    }

    @Override
    public OfficeResponse update(UUID id, OfficeRequest request) {
        Office office = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found"));

        office.setBuildingId(request.getBuildingId());
        office.setPhone(request.getPhone());
        office.setEmail(request.getEmail());

        return mapper.toResponse(repository.save(office));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public OfficeResponse getByBuildingId(UUID buildingId) {
        return mapper.toResponse(repository.findAllByBuildingId(buildingId));
    }
}


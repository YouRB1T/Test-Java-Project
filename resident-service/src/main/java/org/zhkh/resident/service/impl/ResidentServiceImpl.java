package org.zhkh.resident.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhkh.resident.api.request.ResidentRequest;
import org.zhkh.resident.api.response.ResidentResponse;
import org.zhkh.resident.mapper.ResidentMapper;
import org.zhkh.resident.model.Resident;
import org.zhkh.resident.repository.ResidentRepository;
import org.zhkh.resident.service.ResidentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;

    @Override
    public List<ResidentResponse> getAllResidents() {
        return residentRepository.findAll().stream()
                .map(residentMapper::toResidentResponse)
                .toList();
    }

    @Override
    public ResidentResponse getResidentById(UUID id) {
        return residentMapper.toResidentResponse(
                residentRepository.findById(id).orElseThrow()
        );
    }

    @Override
    public ResidentResponse createResident(ResidentRequest resident) {
        return residentMapper.toResidentResponse(
                residentRepository.save(
                        residentMapper.toResident(resident)
                )
        );
    }

    @Override
    public ResidentResponse updateResident(UUID id, ResidentRequest resident) {
        Resident residentEntity = new Resident(
                id,
                resident.getFirstName(),
                resident.getLastName(),
                resident.getMiddleName(),
                resident.getApartmentId(),
                resident.getUserId(),
                resident.getPhone(),
                resident.getEmail(),
                LocalDateTime.now()
        );
        residentRepository.deleteById(id);
        residentRepository.save(residentEntity);

        return residentMapper.toResidentResponse(residentEntity);
    }

    @Override
    public void deleteResident(UUID id) {
        residentRepository.deleteById(id);
    }

    @Override
    public List<ResidentResponse> getAllResidentsInApartments(UUID apartmentId) {
        return residentMapper.toResidentResponse(
                residentRepository.getAllResidentsInApartment(apartmentId)
        );
    }
}

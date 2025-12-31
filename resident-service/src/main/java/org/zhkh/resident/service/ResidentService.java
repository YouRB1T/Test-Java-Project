package org.zhkh.resident.service;

import org.zhkh.resident.api.request.ResidentRequest;
import org.zhkh.resident.api.response.ResidentResponse;

import java.util.List;
import java.util.UUID;

public interface ResidentService {
    List<ResidentResponse> getAllResidents();
    ResidentResponse getResidentById(UUID id);
    ResidentResponse createResident(ResidentRequest resident);
    ResidentResponse updateResident(UUID id, ResidentRequest resident);
    void deleteResident(UUID id);
    List<ResidentResponse> getAllResidentsInApartments(UUID apartmentId);
}

package service;

import api.RequestRequestModel;
import api.ResponseRequestModel;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RequestService {

    ResponseRequestModel create(RequestRequestModel dto, UUID residentId);

    ResponseRequestModel update(UUID requestId, RequestRequestModel dto);

    ResponseRequestModel getById(UUID id);

    List<ResponseRequestModel> getAll();

    void delete(UUID id);

    List<ResponseRequestModel> getByResident(UUID residentId);

    Set<UUID> getServices(UUID requestId);

    Set<UUID> addService(UUID requestId, UUID serviceId);

    Set<UUID> removeService(UUID requestId, UUID serviceId);

}


package service.impl;

import api.RequestRequestModel;
import api.ResponseRequestModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mapper.RequestMapper;
import model.RequestModel;
import model.RequestServiceId;
import model.RequestServiceLink;
import org.springframework.stereotype.Service;
import repository.RequestRepository;
import repository.RequestServiceRepository;
import service.RequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final RequestMapper mapper;
    private final RequestServiceRepository requestServiceRepository;

    @Override
    public ResponseRequestModel create(RequestRequestModel dto, UUID residentId) {
        RequestModel model = mapper.toEntity(dto);
        model.setResidentId(residentId);
        model.setStatusId(1);
        model.setCreatedAt(LocalDateTime.now());

        return mapper.toResponse(repository.save(model));
    }

    @Override
    public ResponseRequestModel update(UUID id, RequestRequestModel dto) {
        RequestModel model = repository.findById(id)
                .orElseThrow();

        model.setContent(dto.getContent());
        model.setPriority(dto.getPriority());
        model.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponse(model);
    }

    @Override
    public ResponseRequestModel getById(UUID id) {
        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ResponseRequestModel> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<ResponseRequestModel> getByResident(UUID residentId) {
        return repository.findByResidentId(residentId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public Set<UUID> getServices(UUID requestId) {
        return requestServiceRepository.findByIdRequestId(requestId)
                .stream()
                .map(rs -> rs.getId().getServiceId())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UUID> addService(UUID requestId, UUID serviceId) {

        if (!requestServiceRepository
                .existsByIdRequestIdAndIdServiceId(requestId, serviceId)) {

            RequestModel model = repository.getById(requestId);

            RequestServiceLink link = new RequestServiceLink(
                    new RequestServiceId(requestId, serviceId),
                    model
            );
            requestServiceRepository.save(link);
        }

        return getServices(requestId);
    }

    @Override
    public Set<UUID> removeService(UUID requestId, UUID serviceId) {

        requestServiceRepository
                .deleteByIdRequestIdAndIdServiceId(requestId, serviceId);

        return getServices(requestId);
    }

}


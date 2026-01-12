package service;

import api.RequestChange;
import api.ResponseChange;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mapper.ChangeMapper;
import model.ChangeModel;
import model.RequestModel;
import org.springframework.stereotype.Service;
import repository.ChangeRepository;
import repository.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangeService {

    private final ChangeRepository changeRepository;
    private final RequestRepository requestRepository;
    private final ChangeMapper mapper;

    public ResponseChange addChange(UUID requestId, RequestChange dto) {

        RequestModel request = requestRepository.findById(requestId)
                .orElseThrow();

        ChangeModel change = new ChangeModel();
        change.setRequestId(requestId);
        change.setOldStatusId(request.getStatusId());
        change.setNewStatusId(dto.getNewStatusId());
        change.setChangeNotes(dto.getNotes());
        change.setChangeDate(LocalDateTime.now());

        request.setStatusId(dto.getNewStatusId());

        return mapper.toResponse(changeRepository.save(change));
    }

    public List<ResponseChange> getByRequest(UUID requestId) {
        return changeRepository.findByRequestId(requestId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}


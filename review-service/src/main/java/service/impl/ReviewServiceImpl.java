package service.impl;

import api.ReviewRequest;
import api.ReviewResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mapper.ReviewMapper;
import model.ReviewModel;
import org.springframework.stereotype.Service;
import repository.ReviewRepository;
import service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    @Override
    public ReviewResponse create(ReviewRequest request) {
        ReviewModel model = mapper.toEntity(request);
        return mapper.toResponse(repository.save(model));
    }

    @Override
    public ReviewResponse update(UUID reviewId, ReviewRequest request) {

        ReviewModel model = repository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        model.setRating(request.getRating());
        model.setContent(request.getContent());
        model.setIsUpdated(true);
        model.setLastDateUpdate(LocalDateTime.now());

        return mapper.toResponse(model);
    }

    @Override
    public ReviewResponse getById(UUID reviewId) {
        return mapper.toResponse(
                repository.findById(reviewId)
                        .orElseThrow(() -> new EntityNotFoundException("Review not found"))
        );
    }

    @Override
    public List<ReviewResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public List<ReviewResponse> getByResident(UUID residentId) {
        return mapper.toResponseList(
                repository.findByResidentId(residentId)
        );
    }

    @Override
    public void delete(UUID reviewId) {
        repository.deleteById(reviewId);
    }
}


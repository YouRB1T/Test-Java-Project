package service;

import api.ReviewRequest;
import api.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponse create(ReviewRequest request);

    ReviewResponse update(UUID reviewId, ReviewRequest request);

    ReviewResponse getById(UUID reviewId);

    List<ReviewResponse> getAll();

    List<ReviewResponse> getByResident(UUID residentId);

    void delete(UUID reviewId);
}


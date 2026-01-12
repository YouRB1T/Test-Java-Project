package repository;

import model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {

    List<ReviewModel> findByResidentId(UUID residentId);

    Optional<ReviewModel> findByRequestId(UUID requestId);
}


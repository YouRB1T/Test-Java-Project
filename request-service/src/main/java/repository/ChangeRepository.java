package repository;

import model.ChangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChangeRepository extends JpaRepository<ChangeModel, UUID> {
    List<ChangeModel> findByRequestId(UUID requestId);
}


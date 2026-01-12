package repository;

import model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<RequestModel, UUID> {
    List<RequestModel> findByResidentId(UUID residentId);
}


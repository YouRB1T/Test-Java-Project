package repository;

import model.RequestServiceId;
import model.RequestServiceLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestServiceRepository
        extends JpaRepository<RequestServiceLink, RequestServiceId> {

    List<RequestServiceLink> findByIdRequestId(UUID requestId);

    boolean existsByIdRequestIdAndIdServiceId(UUID requestId, UUID serviceId);

    void deleteByIdRequestIdAndIdServiceId(UUID requestId, UUID serviceId);
}


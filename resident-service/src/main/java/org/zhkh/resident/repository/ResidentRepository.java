package org.zhkh.resident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zhkh.resident.model.Resident;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, UUID> {
    @Query("select r from Resident r where r.apartmentId" +
            " = ?1")
    List<Resident> getAllResidentsInApartment(UUID apartmentId);
}

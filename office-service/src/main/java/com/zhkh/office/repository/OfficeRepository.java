package com.zhkh.office.repository;

import com.zhkh.office.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {
    @Query("select o from Office o where o.buildingId = ?1")
    Office findAllByBuildingId(UUID buildingId);
}


package com.zhkh.building.repository;

import com.zhkh.building.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<Building, UUID> {
    @Query("SELECT a.apartmentId FROM Apartment a WHERE a.buildingId = :buildingId")
    List<UUID> findApartmentIdsById(UUID buildingId);
}

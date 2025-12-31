package com.zhkh.counterparty.repository;

import com.zhkh.counterparty.model.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CounterpartyRepository extends JpaRepository<Counterparty, UUID> {
    @Query("SELECT s.serviceId FROM CounterpartyServiceEntity s WHERE s.counterpartyId = :counterpartyId")
    List<UUID> findServiceIdsById(UUID counterpartyId);
    @Modifying
    @Query("INSERT INTO CounterpartyServiceEntity (counterpartyId, serviceId) VALUES (:counterpartyId, :serviceId)")
    List<UUID> addServiceToCounterparty(UUID serviceId, UUID counterpartyId);
    @Modifying
    @Query("DELETE FROM CounterpartyServiceEntity cse WHERE cse.counterpartyId = :counterpartyId AND cse.serviceId = :serviceId")
    void deleteServiceFromCounterparty(UUID serviceId, UUID counterpartyId);
}

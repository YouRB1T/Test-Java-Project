package com.zhkh.repository;

import com.zhkh.model.EmployeeServiceId;
import com.zhkh.model.EmployeeServiceLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeServiceLinkRepository extends JpaRepository<EmployeeServiceLink, EmployeeServiceId> {
    List<EmployeeServiceLink> findByEmployee_Id(UUID employeeId);

    boolean existsByIdEmployeeIdAndIdServiceId(UUID employeeId, UUID serviceId);

    void deleteByIdEmployeeIdAndIdServiceId(UUID employeeId, UUID serviceId);
}

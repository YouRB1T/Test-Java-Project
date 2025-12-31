package com.zhkh.repository;

import com.zhkh.model.Employ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployRepository extends JpaRepository<Employ, Long> {
    List<Employ> findByOfficeId(Long officeId);
}

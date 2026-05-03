package com.innovatech.resource_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.innovatech.resource_service.model.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    Optional<Resource> findByProjectId(Long projectId);
    
}

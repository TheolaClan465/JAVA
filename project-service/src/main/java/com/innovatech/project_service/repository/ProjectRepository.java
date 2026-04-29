package com.innovatech.project_service.repository;

import com.innovatech.project_service.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    Optional<Project> findByClientRun(String clientRun);
    
}
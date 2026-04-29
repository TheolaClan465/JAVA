package com.innovatech.project_service.service;

import com.innovatech.project_service.model.dto.ProjectDTO;
import com.innovatech.project_service.model.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();
    Project findById(Long id);
    ProjectDTO findByProjectClientRun (String clientRun);
    Project deleteById(Long id);
    Project update(Long id, ProjectDTO projectDTO);

}

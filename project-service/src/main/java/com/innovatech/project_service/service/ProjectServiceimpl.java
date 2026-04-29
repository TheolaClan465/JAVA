package com.innovatech.project_service.service;

import com.innovatech.project_service.model.dto.ProjectDTO;
import com.innovatech.project_service.model.entity.Project;
import com.innovatech.project_service.exception.ProjectException;
import com.innovatech.project_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceimpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return this.projectRepository.findById(id).orElseThrow(
            () -> new ProjectException("El proyecto con id " + id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public ProjectDTO findByProjectClientRun(String clientRun) {
        String run = clientRun.toUpperCase();

        Project project = projectRepository.findByClientRun(run).orElseThrow(()-> new ProjectException("El cliente con el rut "+ clientRun +" no está en la base de datos"));
        
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setClientRun(project.getClientRun());
        projectDTO.setProjectIndustry(project.getProjectIndustry());
        return projectDTO;
    }

    @Override
    public Project deleteById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectException("El proyecto con id " + id + " no se encuentra en la base de datos");
        }
        return null;
    }

    @Override
    public Project update(Long id, ProjectDTO projectDTO) {
        Project project = this.projectRepository.findById(id).orElseThrow(
            () -> new ProjectException("El proyecto con id " + id + " no se encuentra en la base de datos"));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setStatus(projectDTO.getStatus());
        project.setBudget(projectDTO.getBudget());
        project.setClientRun(projectDTO.getClientRun());
        project.setProjectIndustry(projectDTO.getProjectIndustry());

        Project update = projectRepository.save(project);
        return update;
    }


}
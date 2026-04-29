package com.innovatech.project_service.assamblers;

import com.innovatech.project_service.model.dto.ProjectDTO;
import com.innovatech.project_service.model.entity.Project;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class ProjectDTOModelAssambler implements RepresentationModelAssembler<Project, EntityModel<ProjectDTO>> {
    @Override
    public EntityModel<ProjectDTO> toModel(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setClientRun(project.getClientRun());
        projectDTO.setProjectIndustry(project.getProjectIndustry());

        return EntityModel.of(projectDTO,
            linkTo(methodOn(com.innovatech.project_service.controller.ProjectController.class).findAll()).withRel("projects"),
            linkTo(methodOn(com.innovatech.project_service.controller.ProjectController.class).findById(project.getId())).withSelfRel()
    
        );
    }
}
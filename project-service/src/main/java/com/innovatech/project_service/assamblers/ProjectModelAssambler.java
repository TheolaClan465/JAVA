package com.innovatech.project_service.assamblers;

import com.innovatech.project_service.controller.ProjectController;
import com.innovatech.project_service.model.entity.Project;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProjectModelAssambler implements RepresentationModelAssembler<Project, EntityModel<Project>>{
    
    @Override
    public EntityModel<Project> toModel(Project entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(ProjectController.class).findById(entity.getId())).withSelfRel(),
            Link.of("http://localhost:8023/proyecto/"+entity.getId()).withSelfRel()
        );
    }
}

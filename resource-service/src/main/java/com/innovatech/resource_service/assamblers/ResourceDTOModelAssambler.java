package com.innovatech.resource_service.assamblers;

import com.innovatech.resource_service.model.dto.ResourceDTO;
import com.innovatech.resource_service.model.entity.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ResourceDTOModelAssambler implements RepresentationModelAssembler<Resource, EntityModel<ResourceDTO>> {
    @Override
    public EntityModel<ResourceDTO> toModel(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setName(resource.getName());
        resourceDTO.setType(resource.getType());
        resourceDTO.setCapacity(resource.getCapacity());
        resourceDTO.setUtilizationPercentage(resource.getUtilizationPercentage());
        resourceDTO.setStatus(resource.getStatus());
        resourceDTO.setCost(resource.getCost());
        resourceDTO.setProjectId(resource.getProjectId());

        return EntityModel.of(resourceDTO,
            linkTo(methodOn(com.innovatech.resource_service.controller.ResourceController.class).findAll()).withRel("resources"),
            linkTo(methodOn(com.innovatech.resource_service.controller.ResourceController.class).findById(resource.getId())).withSelfRel()
    
        );
    }
    
}

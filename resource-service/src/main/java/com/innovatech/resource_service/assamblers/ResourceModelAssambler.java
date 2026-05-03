package com.innovatech.resource_service.assamblers;

import com.innovatech.resource_service.controller.ResourceController;
import com.innovatech.resource_service.model.entity.Resource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ResourceModelAssambler implements RepresentationModelAssembler<Resource, EntityModel<Resource>>{
    
    @Override
    public EntityModel<Resource> toModel(Resource entity){
        return EntityModel.of(
            entity,
            linkTo(methodOn(ResourceController.class).findById(entity.getId())).withSelfRel(),
            Link.of("http://localhost:8024/recurso/"+entity.getId()).withSelfRel()
        );
    }
}

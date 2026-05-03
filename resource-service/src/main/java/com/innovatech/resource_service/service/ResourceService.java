package com.innovatech.resource_service.service;

import com.innovatech.resource_service.model.dto.ResourceDTO;
import com.innovatech.resource_service.model.entity.Resource;

import java.util.List;

public class ResourceService {
    
    List<Resource> findAll();
    Resource findById(Long id);
    Resource deleteById(Long id);
    Resource update(Long id, ResourceDTO resourceDTO);
}

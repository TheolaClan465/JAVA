package com.innovatech.resource_service.service;

import com.innovatech.resource_service.model.dto.ResourceDTO;
import com.innovatech.resource_service.model.entity.Resource;
import com.innovatech.resource_service.exception.ResourceException;
import com.innovatech.resource_service.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAll() {
        return this.resourceRepository.findAll();
    }


    @Override
    public Resource findById(Long id) {
        return this.resourceRepository.findById(id).orElseThrow(
            () -> new ResourceException("El recurso con id " + id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public Resource deleteById(Long id) {
        if(!resourceRepository.existsById(id)) {
            throw new ResourceException("El recurso con id " + id + " no se encuentra en la base de datos");
        }
        return null;
    }

    @Override
    public Resource update(Long id, ResourceDTO resourceDTO) {
        Resource resource = this.resourceRepository.findById(id).orElseThrow(
            () -> new ResourceException("El recurso con id " + id + " no se encuentra en la base de datos"));
        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        resource.setCapacity(resourceDTO.getCapacity());
        resource.setCost(resourceDTO.getCost());
        resource.setProjectId(resourceDTO.getProjectId());
        resource.setStatus(resourceDTO.getStatus());
        resource.setUtilizationPercentage(resourceDTO.getUtilizationPercentage());
        return this.resourceRepository.save(resource);
    }

    
}

package com.innovatech.resource_service.client;

import com.innovatech.resource_service.model.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Le indicamos el nombre del servicio y la URL base (Puerto 8023)
@FeignClient(name = "project-service", url = "http://localhost:8023")
public interface ProjectClient {

    // Replicamos la firma del endpoint que queremos consumir
    @GetMapping("/api/projects/id/{id}")
    ProjectDTO getProjectById(@PathVariable("id") Long id);
}
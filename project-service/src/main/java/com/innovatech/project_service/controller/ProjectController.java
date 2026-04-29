package com.innovatech.project_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.innovatech.project_service.service.ProjectService;
import com.innovatech.project_service.model.entity.Project;
import java.util.List;


@RestController
@RequestMapping("/api/projects")
@Validated
@Tag(name = "Cliente HATEOAS", description = "Esta seccion contiene los CRUD a proyectos con HATEOAS")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @GetMapping("/mostrar_proyectos")
    @Operation(
        summary = "Obtener todos los proyectos",
        description = "Devuelve una lista de todos los proyectos disponibles en el sistema, incluyendo enlaces HATEOAS para cada proyecto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyectos obtenidos exitosamente"),
    })
    public ResponseEntity<List<Project>> findAll(){
        
    }
    
}
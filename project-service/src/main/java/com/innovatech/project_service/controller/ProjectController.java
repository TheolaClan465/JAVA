package com.innovatech.project_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.innovatech.project_service.service.ProjectService;
import com.innovatech.project_service.model.dto.ErrorDTO;
import com.innovatech.project_service.model.dto.ProjectDTO;
import com.innovatech.project_service.model.entity.Project;
import com.innovatech.project_service.repository.ProjectRepository;
import java.util.List;



@RestController
@RequestMapping("/api/projects")
@Validated
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project){
        Project savedProject = projectRepository.save(project);

        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/mostrar_proyectos")
    @Operation(
        summary = "Obtener todos los proyectos",
        description = "Devuelve una lista de todos los proyectos disponibles en el sistema, incluyendo enlaces HATEOAS para cada proyecto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyectos obtenidos exitosamente"),
    })
    public ResponseEntity<List<Project>> findAll(){
        List<Project> projects = this.projectService.findAll();
        return ResponseEntity.status(200).body(projects);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve un proyecto respecto a su id",
            description = "Este metodo debe retornar un Proyecto cuando es consultado" +
                    " mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna un proyecto encontrado"),
            @ApiResponse(responseCode = "404", description = "Error - proyecto con id no existe",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDTO.class)))
    })
    @Parameters(value = {
        @Parameter(name = "id", description = "ID del Proyecto a consultar", required = true)
    })
    public ResponseEntity<Project> findById(@PathVariable Long id){

        Project project = this.projectService.findById(id);
        return ResponseEntity.status(200).body(project);

    }

    @GetMapping("/run/{runCliente}")
    @Operation(

            summary = "Devuelve un proyecto con respecto a su rut",
            description = "Este metodo debe retornar un Proyecto cuando es consultado"+
                    " mediante su rut"

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el proyecto encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Proyecto con este rut no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el rut unico de un cliente", required = true)
    })
    public ResponseEntity<ProjectDTO> findByRunCliente(@PathVariable String runCliente){

        ProjectDTO projectDTO=this.projectService.findByProjectClientRun(runCliente);
        return ResponseEntity.status(200).body(projectDTO);

    }

    @PutMapping("/modificar/{id}")
    @Operation(
            summary = "Endpoint que permite modificar un proyecto",
            description = "Este endpoint recibe el ID del proyecto por la URL y un cuerpo en formato ProjectDTO con los datos modificados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Proyecto modificado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida o datos incorrectos")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviar un Json con los datos modificados del cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProjectDTO.class)
            )
    )
    public ResponseEntity<Project> update(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {

        Project projectModificado = projectService.update(id, projectDTO);
        return ResponseEntity.status(202).body(projectModificado);

    }

    @DeleteMapping("/eliminar_proyecto/{id}")
    @Operation(
            summary = "Eliminar un proyecto por ID",
            description = "Este endpoint permite eliminar un proyecto especifico utilizando su Id "+
                    "devuelve el proyecto eliminado si la operacion fue exitosa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Proyecto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado"),
            @ApiResponse(responseCode = "400", description = "id invalido"),

    })
    public ResponseEntity<Project> deleteById(@PathVariable Long id){

        Project project = projectService.deleteById(id);
        return ResponseEntity.status(202).body(project);

    }

   
    
    
}
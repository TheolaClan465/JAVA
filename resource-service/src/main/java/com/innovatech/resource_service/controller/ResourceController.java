package com.innovatech.resource_service.controller;

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
import java.util.List;

import com.innovatech.resource_service.service.ResourceService;
import com.innovatech.resource_service.model.dto.ResourceDTO;
import com.innovatech.resource_service.model.entity.Resource;
import com.innovatech.resource_service.repository.ResourceRepository;
import com.innovatech.resource_service.model.dto.ErrorDTO;

@RestController
@RequestMapping("/api/resources")
@Validated
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @PostMapping
    public ResponseEntity<Resource> createResource(@Valid @RequestBody Resource resource){
        Resource savedResource = resourceRepository.save(resource);

        return new ResponseEntity<>(savedResource, HttpStatus.CREATED);
    }

    @GetMapping("/mostrar_recursos")
    @Operation(
        summary = "Obtener todos los recursos",
        description = "Devuelve una lista de todos los recursos disponibles en el sistema, incluyendo enlaces HATEOAS para cada recurso."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recursos obtenidos exitosamente"),
    })
    public ResponseEntity<List<Resource>> findAll() {
        List<Resource> resources = resourceService.findAll();
        return ResponseEntity.status(200).body(resources);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve un recurso respecto a su id",
            description = "Este metodo debe retornar un Recurso cuando es consultado" +
                    " mediante su id"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso obtenido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDTO.class)))
    })
    @Parameters(value = {
        @Parameter(name = "id", description = "ID único del recurso", required = true)
    })
    public ResponseEntity<Resource> findById(@PathVariable Long id) {

        Resource resource = this.resourceService.findById(id);
        return ResponseEntity.status(200).body(resource);

    }

    @PutMapping("/modificar/{id}")
    @Operation(
            summary = "Modifica un recurso existente",
            description = "Este método actualiza un recurso existente con los datos proporcionados en el cuerpo de la solicitud. El ID del recurso a modificar se especifica en la URL."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso modificado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos del recurso a modificar",
        required = true,
        content = @Content(
            mediaType = "application/json", 
            schema = @Schema(implementation = ResourceDTO.class)
        )
    )
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @Valid @RequestBody ResourceDTO resourceDTO) {
        Resource updatedResource = resourceService.update(id, resourceDTO);
        return ResponseEntity.status(202).body(updatedResource);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(
            summary = "Elimina un recurso existente",
            description = "Este método elimina un recurso existente especificado por su ID en la URL."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Resource> deleteResource(@PathVariable Long id) {
        Resource deletedResource = resourceService.deleteById(id);
        return ResponseEntity.status(202).body(deletedResource);
    }

}

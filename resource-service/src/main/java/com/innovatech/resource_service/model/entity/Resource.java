package com.innovatech.resource_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="resources")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Resource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resource")
    private Long id;

    @Column(name = "resource_name", nullable = false)
    @NotNull(message = "Resource name cannot be null")
    private String name;

    @Column(name = "resource_type", nullable = false)
    @NotNull(message = "Resource type cannot be null")
    private String type;

    @Column(name = "resource_capacity")
    private Integer capacity;

    @Column(name = "resource_utilizationPercentage")
    private Double utilizationPercentage;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status cannot be null")
    private String status;

    @Column(name = "cost", nullable = false)
    @NotNull(message = "Cost cannot be null")
    private Double cost;

    @Column(name = "project_id")
    private Long projectId;
}

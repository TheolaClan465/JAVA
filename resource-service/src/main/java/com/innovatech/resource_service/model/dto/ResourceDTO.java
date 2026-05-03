package com.innovatech.resource_service.model.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResourceDTO {
    private Long id;
    private String name;
    private String type;
    private Integer capacity;
    private Double utilizationPercentage;
    private String status;
    private Double cost;
    private Long projectId;
}

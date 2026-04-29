package com.innovatech.project_service.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Project {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;

    @Column(name = "project_name", nullable = false)
    @NotNull(message = "Project name cannot be null")
    private String name;

    @Column(name = "project_description", length = 1000)
    private String description;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "status")
    @NotNull(message = "Project status cannot be null     ")
    private String status;

    @Column(name = "budget")
    @NotNull(message = "Project budget cannot be null")
    private Double budget;

    @Column(name = "client_run")
    @NotNull(message = "Client run cannot be null")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String clientRun;

    @Column(name = "project_industry")
    private String projectIndustry;
}

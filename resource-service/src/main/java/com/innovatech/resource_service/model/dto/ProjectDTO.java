package com.innovatech.resource_service.model.dto;

import java.time.LocalDate;

public class ProjectDTO {
    
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Double budget;
    private String clientRun;
    private String projectIndustry;

    public ProjectDTO() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getClientRun() { return clientRun; }
    public void setClientRun(String clientRun) { this.clientRun = clientRun; }

    public String getProjectIndustry() { return projectIndustry; }
    public void setProjectIndustry(String projectIndustry) { this.projectIndustry = projectIndustry; }
}
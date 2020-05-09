package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

  private Long referenceOfService;
  private String projectName;
  private String projectDescription;

  public Long getReferenceOfService() {
    return referenceOfService;
  }

  public void setReferenceOfService(Long referenceOfService) {
    this.referenceOfService = referenceOfService;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  @Override
  public String toString() {
    return "Project{" + "referenceOfService='" + referenceOfService + '\'' + ", projectName='"
        + projectName + '\'' + ", projectDescription='" + projectDescription + '\'' + '}';
  }
}

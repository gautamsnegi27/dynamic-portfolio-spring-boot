package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectModel {
  private String projectName;
  private String projectDescription;

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

  public ProjectModel() {
  }

  @Override
  public String toString() {
    return "ProjectModel{" + "projectName='" + projectName + '\'' + ", projectDescription='"
        + projectDescription + '\'' + '}';
  }
}

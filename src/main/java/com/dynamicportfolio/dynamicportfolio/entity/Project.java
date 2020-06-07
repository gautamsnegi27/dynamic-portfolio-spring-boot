package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

  @NotNull(message = "field is mandatory")
  private String projectName;
  private String projectDescription;
  private String projectImage;
  private String projectLink;

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

  public String getProjectImage() {
    return projectImage;
  }

  public void setProjectImage(String projectImage) {
    this.projectImage = projectImage;
  }

  public String getProjectLink() {
    return projectLink;
  }

  public void setProjectLink(String projectLink) {
    this.projectLink = projectLink;
  }

  public Project() {
  }

  @Override
  public String toString() {
    return "Project{" + "projectName='" + projectName + '\'' + ", projectDescription='"
        + projectDescription + '\'' + ", projectImage='" + projectImage + '\'' + ", projectLink='"
        + projectLink + '\'' + '}';
  }
}

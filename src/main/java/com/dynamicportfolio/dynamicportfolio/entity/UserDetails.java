package com.dynamicportfolio.dynamicportfolio.entity;

import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "user_details")
public class UserDetails implements Serializable {
  private static final long serialVersionUID = -2709001;
  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  @Id
  private Long id;
  private String description;

  private AuthDetail authDetail;
  private SocialMediaDetails socialMediaDetails;
  private List<ServiceDetail> serviceDetails;
  private List<Project> projects;
  private List<ExperienceDetail> experienceDetails;
  @NotNull(message = "Should have some role")
  private List<Roles> roles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AuthDetail getAuthDetail() {
    return authDetail;
  }

  public void setAuthDetail(AuthDetail authDetail) {
    this.authDetail = authDetail;
  }

  public SocialMediaDetails getSocialMediaDetails() {
    return socialMediaDetails;
  }

  public void setSocialMediaDetails(SocialMediaDetails socialMediaDetails) {
    this.socialMediaDetails = socialMediaDetails;
  }

  public List<ServiceDetail> getServiceDetails() {
    return serviceDetails;
  }

  public void setServiceDetails(List<ServiceDetail> serviceDetails) {
    this.serviceDetails = serviceDetails;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public List<ExperienceDetail> getExperienceDetails() {
    return experienceDetails;
  }

  public void setExperienceDetails(List<ExperienceDetail> experienceDetails) {
    this.experienceDetails = experienceDetails;
  }

  public List<Roles> getRoles() {
    return roles;
  }

  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }

  public UserDetails() {
  }

  @Override
  public String toString() {
    return "UserDetails{" + "id=" + id + ", description='" + description + '\'' + ", authDetail="
        + authDetail + ", socialMediaDetails=" + socialMediaDetails + ", serviceDetails="
        + serviceDetails + ", projects=" + projects + ", experienceDetails=" + experienceDetails
        + ", roles=" + roles + '}';
  }
}

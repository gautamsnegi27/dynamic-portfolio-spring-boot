package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
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
  @NotNull(message = "name is mandatory")
  private String name;
  private AuthDetail authDetail;
  private SocialMediaDetails socialMediaDetails;
  private List<ServiceDetail> serviceDetails;
  private List<Project> projects;
  private List<ExperienceDetail> experienceDetails;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public UserDetails() {
  }

  public UserDetails(Long id, String description,
      @NotNull(message = "name is mandatory") String name, AuthDetail authDetail,
      SocialMediaDetails socialMediaDetails, List<ServiceDetail> serviceDetails,
      List<Project> projects, List<ExperienceDetail> experienceDetails) {
    this.id = id;
    this.description = description;
    this.name = name;
    this.authDetail = authDetail;
    this.socialMediaDetails = socialMediaDetails;
    this.serviceDetails = serviceDetails;
    this.projects = projects;
    this.experienceDetails = experienceDetails;
  }

  @Override
  public String toString() {
    return "UserDetails{" + "id=" + id + ", description='" + description + '\'' + ", authDetail="
        + authDetail + ", socialMediaDetails=" + socialMediaDetails + ", serviceDetails="
        + serviceDetails + ", projects=" + projects + ", experienceDetails=" + experienceDetails
        + '}';
  }
}

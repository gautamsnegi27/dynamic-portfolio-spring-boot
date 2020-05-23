package com.dynamicportfolio.dynamicportfolio.model;

import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsModel {
  private Long id;

  private String description;
  private AuthDetailModel authDetailModel;
  private SocialMediaDetailsModel socialMediaDetailsModel;
  private List<ServiceDetailModel> serviceDetailModels;
  private List<ProjectModel> projectModels;
  private List<ExperienceDetailModel> experienceDetailModels;
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


  public AuthDetailModel getAuthDetailModel() {
    return authDetailModel;
  }

  public void setAuthDetailModel(AuthDetailModel authDetailModel) {
    this.authDetailModel = authDetailModel;
  }

  public SocialMediaDetailsModel getSocialMediaDetailsModel() {
    return socialMediaDetailsModel;
  }

  public void setSocialMediaDetailsModel(SocialMediaDetailsModel socialMediaDetailsModel) {
    this.socialMediaDetailsModel = socialMediaDetailsModel;
  }

  public List<ServiceDetailModel> getServiceDetailModels() {
    return serviceDetailModels;
  }

  public void setServiceDetailModels(List<ServiceDetailModel> serviceDetailModels) {
    this.serviceDetailModels = serviceDetailModels;
  }

  public List<ProjectModel> getProjectModels() {
    return projectModels;
  }

  public void setProjectModels(List<ProjectModel> projectModels) {
    this.projectModels = projectModels;
  }

  public List<ExperienceDetailModel> getExperienceDetailModels() {
    return experienceDetailModels;
  }

  public void setExperienceDetailModels(List<ExperienceDetailModel> experienceDetailModels) {
    this.experienceDetailModels = experienceDetailModels;
  }

  public List<Roles> getRoles() {
    return roles;
  }

  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }

  public Boolean validate() {
    if (CollectionUtils.isEmpty(roles) || Objects.isNull(authDetailModel) || !authDetailModel
        .validate()) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  public UserDetailsModel() {
  }

  @Override
  public String toString() {
    return "UserDetailsModel{" + "id=" + id + ", description='" + description + '\''
        + ", authDetailModel=" + authDetailModel + ", socialMediaDetailsModel="
        + socialMediaDetailsModel + ", serviceDetailModels=" + serviceDetailModels
        + ", projectModels=" + projectModels + ", experienceDetailModels=" + experienceDetailModels
        + ", roles=" + roles + '}';
  }
}

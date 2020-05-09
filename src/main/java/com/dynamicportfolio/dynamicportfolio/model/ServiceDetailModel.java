package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDetailModel {
  private Long id;
  private String serviceName;
  private String serviceDescription;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  @Override
  public String toString() {
    return "ServiceDetailModel{" + "id=" + id + ", serviceName='" + serviceName + '\''
        + ", serviceDescription='" + serviceDescription + '\'' + '}';
  }
}

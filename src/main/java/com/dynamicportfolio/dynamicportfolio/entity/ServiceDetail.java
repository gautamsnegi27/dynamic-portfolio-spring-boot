package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDetail {

  @NotNull(message = "field is mandatory")
  private String serviceName;
  private String serviceDescription;

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
    return "ServiceDetail{" + "serviceName='" + serviceName + '\'' + ", serviceDescription='"
        + serviceDescription + '\'' + '}';
  }
}

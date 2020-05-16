package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDetailModel {
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

  public ServiceDetailModel() {
  }

  @Override
  public String toString() {
    return "ServiceDetailModel{" + "serviceName='" + serviceName + '\'' + ", serviceDescription='"
        + serviceDescription + '\'' + '}';
  }
}

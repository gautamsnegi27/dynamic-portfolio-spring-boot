package com.dynamicportfolio.dynamicportfolio.model;

import com.dynamicportfolio.dynamicportfolio.common.DynamicProfileStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicProfileResponseObject<T> {
  private T responseObject;
  private DynamicProfileStatusCode status;

  @JsonIgnore
  private HttpStatus statusCode;

  public DynamicProfileResponseObject(DynamicProfileStatusCode status, T responseObject) {
    this(status);
    this.responseObject = responseObject;
  }

  public DynamicProfileResponseObject(DynamicProfileStatusCode status) {
    this();
    this.status = status;
  }

  public DynamicProfileResponseObject() {
    this.statusCode = HttpStatus.OK;
  }

  public T getResponseObject() {
    return responseObject;
  }

  public void setResponseObject(T responseObject) {
    this.responseObject = responseObject;
  }

  public DynamicProfileStatusCode getStatus() {
    return status;
  }

  public void setStatus(DynamicProfileStatusCode status) {
    this.status = status;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(HttpStatus statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public String toString() {
    return "DynamicProfileResponseObject{" + "responseObject=" + responseObject + ", status="
        + status + ", statusCode=" + statusCode + '}';
  }
}
package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDetailModel {
  private Long from;
  private Long to;
  private String companyName;
  private String jobDescription;

  public Long getFrom() {
    return from;
  }

  public void setFrom(Long from) {
    this.from = from;
  }

  public Long getTo() {
    return to;
  }

  public void setTo(Long to) {
    this.to = to;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getJobDescription() {
    return jobDescription;
  }

  public void setJobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
  }

  public ExperienceDetailModel() {
  }

  @Override
  public String toString() {
    return "ExperienceDetailModel{" + "from=" + from + ", to=" + to + ", companyName='"
        + companyName + '\'' + ", jobDescription='" + jobDescription + '\'' + '}';
  }
}

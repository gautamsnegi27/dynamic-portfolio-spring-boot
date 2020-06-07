package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDetailModel {
  private String from;
  private String to;
  private String companyName;
  private String jobDescription;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
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

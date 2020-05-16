package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDetail {

  @NotNull(message = "field is mandatory")
  private Long from;
  @NotNull(message = "field is mandatory")
  private Long to;
  @NotNull(message = "field is mandatory")
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

  public ExperienceDetail() {
  }

  @Override
  public String toString() {
    return "ExperienceDetail{" + "from=" + from + ", to=" + to + ", companyName='" + companyName
        + '\'' + ", jobDescription='" + jobDescription + '\'' + '}';
  }
}


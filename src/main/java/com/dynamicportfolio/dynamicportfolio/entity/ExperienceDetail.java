package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDetail {

  private @NotNull(message = "field is mandatory") String from;
  private @NotNull(message = "field is mandatory") String to;
  @NotNull(message = "field is mandatory") private String companyName;
  private String jobDescription;

  public @NotNull(message = "field is mandatory") String getFrom() {
    return from;
  }

  public void setFrom(@NotNull(message = "field is mandatory") String from) {
    this.from = from;
  }

  public @NotNull(message = "field is mandatory") String getTo() {
    return to;
  }

  public void setTo(@NotNull(message = "field is mandatory") String to) {
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


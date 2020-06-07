package com.dynamicportfolio.dynamicportfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialMediaDetails {
  private String fbUrl;
  private String instaUrl;
  private String linkedinUrl;

  public String getFbUrl() {
    return fbUrl;
  }

  public void setFbUrl(String fbUrl) {
    this.fbUrl = fbUrl;
  }

  public String getInstaUrl() {
    return instaUrl;
  }

  public void setInstaUrl(String instaUrl) {
    this.instaUrl = instaUrl;
  }

  public String getLinkedinUrl() {
    return linkedinUrl;
  }

  public void setLinkedinUrl(String linkedinUrl) {
    this.linkedinUrl = linkedinUrl;
  }

  public SocialMediaDetails() {
  }

  @Override
  public String toString() {
    return "SocialMediaDetails{" + "fbUrl='" + fbUrl + '\'' + ", instaUrl='" + instaUrl + '\''
        + ", linkedinUrl='" + linkedinUrl + '\'' + '}';
  }
}

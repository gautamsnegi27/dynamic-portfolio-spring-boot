package com.dynamicportfolio.dynamicportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialMediaDetailsModel {
  private String fbUrl;
  private String twitterUrl;
  private String instaUrl;

  public String getFbUrl() {
    return fbUrl;
  }

  public void setFbUrl(String fbUrl) {
    this.fbUrl = fbUrl;
  }

  public String getTwitterUrl() {
    return twitterUrl;
  }

  public void setTwitterUrl(String twitterUrl) {
    this.twitterUrl = twitterUrl;
  }

  public String getInstaUrl() {
    return instaUrl;
  }

  public void setInstaUrl(String instaUrl) {
    this.instaUrl = instaUrl;
  }

  public SocialMediaDetailsModel() {
  }

  @Override
  public String toString() {
    return "SocialMediaDetailsModel{" + "fbUrl='" + fbUrl + '\'' + ", twitterUrl='" + twitterUrl
        + '\'' + ", instaUrl='" + instaUrl + '\'' + '}';
  }
}

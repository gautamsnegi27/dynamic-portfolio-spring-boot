package com.dynamicportfolio.dynamicportfolio.model;

public class AuthResponse {
  private String jwtToken;

  public String getJwtToken() {
    return jwtToken;
  }

  public AuthResponse(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}

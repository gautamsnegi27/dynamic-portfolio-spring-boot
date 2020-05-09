package com.dynamicportfolio.dynamicportfolio.model;

import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDetailModel {
  @NotBlank(message = "email is mandatory")
  private String email;
  @NotBlank(message = "userName is mandatory")
  private String userName;
  @NotBlank(message = "password is mandatory")
  private String password;
  @NotBlank(message = "role is mandatory")
  private Roles role;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "AuthDetailModel{" + "email='" + email + '\'' + ", userName='" + userName + '\''
        + ", password='" + password + '\'' + ", role=" + role + '\'' + '}';
  }

  public Boolean validate(){
    if (Stream.of(email, userName, password, role).anyMatch(Objects::isNull)) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
}

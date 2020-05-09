package com.dynamicportfolio.dynamicportfolio.entity;


import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDetail {

  @NotNull(message = "email is mandatory")
  @Indexed(name = "email_index", unique = true)
  private String email;
  @NotNull(message = "userName is mandatory")
  @Indexed(name = "user_name", unique = true)
  private String userName;
  @NotNull(message = "password is mandatory")
  private String password;
  @NotNull(message = "role is mandatory")
  private Roles role;

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

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

  public AuthDetail(String email, String userName, String password, Roles role) {
    this.email = email;
    this.userName = userName;
    this.password = password;
    this.role = role;
  }

  public AuthDetail() {
  }

  @Override
  public String toString() {
    return "AuthDetail{" + "email='" + email + '\'' + ", userName='" + userName + '\''
        + ", password='" + password + '\'' + ", role='" + role + '\'' + '}';
  }
}

package com.dynamicportfolio.dynamicportfolio.entity;


import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

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
  @NotNull(message = "name is mandatory")
  private String firstName;
  private String LastName;
  @NotNull(message = "Should have some role")
  private List<Roles> roles;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public List<Roles> getRoles() {
    return roles;
  }

  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }

  public AuthDetail() {
  }

  @Override
  public String toString() {
    return "AuthDetail{" + "email='" + email + '\'' + ", userName='" + userName + '\''
        + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", LastName='"
        + LastName + '\'' + ", roles=" + roles + '}';
  }
}

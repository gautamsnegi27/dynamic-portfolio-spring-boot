package com.dynamicportfolio.dynamicportfolio.model;

import com.dynamicportfolio.dynamicportfolio.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
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

  public AuthDetailModel() {
  }

  public Boolean validate() {
    if (Stream.of(email, userName, password).anyMatch(Objects::isNull) || CollectionUtils
        .isEmpty(roles)) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  @Override
  public String toString() {
    return "AuthDetailModel{" + "email='" + email + '\'' + ", userName='" + userName + '\''
        + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", LastName='"
        + LastName + '\'' + ", roles=" + roles + '}';
  }
}

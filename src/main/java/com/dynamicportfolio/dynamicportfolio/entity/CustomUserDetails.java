package com.dynamicportfolio.dynamicportfolio.entity;

import com.dynamicportfolio.dynamicportfolio.common.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

  private String userName;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public CustomUserDetails(){
    
  }

  public CustomUserDetails(com.dynamicportfolio.dynamicportfolio.entity.UserDetails userDetails){
    this.userName = userDetails.getAuthDetail().getUserName();
    this.password = userDetails.getAuthDetail().getPassword();
    this.active = true;
    this.authorities = userDetails.getRoles().stream().map(
        (Roles role) -> new SimpleGrantedAuthority(role.toString()))
        .collect(Collectors.toList());
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}

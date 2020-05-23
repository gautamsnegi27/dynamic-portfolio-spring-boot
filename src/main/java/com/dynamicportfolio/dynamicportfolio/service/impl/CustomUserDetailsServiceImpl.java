package com.dynamicportfolio.dynamicportfolio.service.impl;

import com.dynamicportfolio.dynamicportfolio.entity.CustomUserDetails;
import com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("com.dynamicportfolio.dynamicportfolio.service.impl.CustomUserDetailsServiceImpl")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.repo.impl.UserDetailsRepoImpl")
  private UserDetailsRepo userDetailsRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.dynamicportfolio.dynamicportfolio.entity.UserDetails myUserDetails =
        userDetailsRepo.fetchUserByUserName(username);

    if (Objects.nonNull(myUserDetails)) {
      return new CustomUserDetails(myUserDetails);
    } else {
      throw new UsernameNotFoundException("no user with this username");
    }
  }
}

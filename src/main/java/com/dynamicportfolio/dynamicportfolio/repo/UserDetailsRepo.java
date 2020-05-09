package com.dynamicportfolio.dynamicportfolio.repo;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;

public interface UserDetailsRepo {
  UserDetails createUser(UserDetails userDetails);

  UserDetails fetchUser(Long id);
}
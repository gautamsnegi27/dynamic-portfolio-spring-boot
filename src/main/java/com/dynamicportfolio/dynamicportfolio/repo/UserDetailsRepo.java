package com.dynamicportfolio.dynamicportfolio.repo;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;

//@Repository("com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo")
public interface UserDetailsRepo {
  UserDetails createUser(UserDetails userDetails);

  UserDetails fetchUser(Long id);
}
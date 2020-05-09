package com.dynamicportfolio.dynamicportfolio.repo;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import org.springframework.stereotype.Repository;

//@Repository("com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo")
public interface UserDetailsRepo {
  UserDetails createUser(UserDetails userDetails);
}




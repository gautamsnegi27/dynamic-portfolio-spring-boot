package com.dynamicportfolio.dynamicportfolio.repo;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;

import java.util.List;

public interface UserDetailsRepo {
  UserDetails createUser(UserDetails userDetails);

  UserDetails fetchUser(Long id);

  UserDetails findByEmailAndUserNameAndPassword(String email, String userName, String password);

  List<UserDetails> findByAuthDetail_EmailOrAuthDetail_UserName(String email, String userName);

  UserDetails fetchUserByEmail(String email);

  UserDetails fetchUserByUserName(String userName);

  UserDetails save(UserDetails userDetails);
}
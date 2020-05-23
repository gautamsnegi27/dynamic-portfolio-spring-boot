package com.dynamicportfolio.dynamicportfolio.service;

import com.dynamicportfolio.dynamicportfolio.model.AuthDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;

public interface UserDetailsService {
  DynamicProfileResponseObject<UserDetailsModel> createUser(UserDetailsModel userDetailsModel);

  DynamicProfileResponseObject<UserDetailsModel> fetchUser(Long id);

  void getUser(AuthDetailModel authDetailModel);

  DynamicProfileResponseObject<UserDetailsModel> fetchUserByEmail(String email);

  DynamicProfileResponseObject<UserDetailsModel> fetchByUserName(String userName);

  DynamicProfileResponseObject<UserDetailsModel> updateUser(UserDetailsModel userDetailsModel,
      String username, String email);
}

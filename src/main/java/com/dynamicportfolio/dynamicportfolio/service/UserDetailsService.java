package com.dynamicportfolio.dynamicportfolio.service;

import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;

public interface UserDetailsService {
  DynamicProfileResponseObject<UserDetailsModel> createUser(UserDetailsModel userDetailsModel);

  DynamicProfileResponseObject<UserDetailsModel> fetchUser(Long id);

}

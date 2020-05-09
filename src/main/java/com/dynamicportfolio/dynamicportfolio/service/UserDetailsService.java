package com.dynamicportfolio.dynamicportfolio.service;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import com.dynamicportfolio.dynamicportfolio.model.AuthDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;

public interface UserDetailsService {
  DynamicProfileResponseObject<UserDetailsModel> createUser(UserDetailsModel userDetailsModel);

  /*DynamicProfileResponseObject<UserDetailsModel> getUser(AuthDetailModel authDetailModel);

  DynamicProfileResponseObject<UserDetailsModel> updateUser(UserDetailsModel userDetailsModel);*/

}

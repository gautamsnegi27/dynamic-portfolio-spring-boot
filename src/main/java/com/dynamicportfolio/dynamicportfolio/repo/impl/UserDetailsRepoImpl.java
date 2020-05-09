package com.dynamicportfolio.dynamicportfolio.repo.impl;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsRepoImpl implements UserDetailsRepo {

  private final MongoTemplate mongoTemplate;

  @Autowired
  private UserDetailsRepoImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public UserDetails createUser(UserDetails userDetails) {
    return mongoTemplate.save(userDetails);
  }

  @Override
  public UserDetails fetchUser(Long id) {
    return mongoTemplate.findById(id, UserDetails.class);
  }

}

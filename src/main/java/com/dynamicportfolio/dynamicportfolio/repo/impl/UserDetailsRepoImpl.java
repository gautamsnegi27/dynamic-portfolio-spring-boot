package com.dynamicportfolio.dynamicportfolio.repo.impl;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("com.dynamicportfolio.dynamicportfolio.repo.impl.UserDetailsRepoImpl")
public class UserDetailsRepoImpl implements UserDetailsRepo {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public UserDetailsRepoImpl(MongoTemplate mongoTemplate) {
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

  @Override
  public UserDetails findByEmailAndUserNameAndPassword(String email, String userName,
      String password) {
    Query query = new Query();
    query.addCriteria(new Criteria().andOperator(new Criteria()
            .orOperator(Criteria.where("authDetail.email").is(email),
                Criteria.where("authDetail.userName").is(userName)),
        Criteria.where("authDetail.password").is(password)));
    return mongoTemplate.findOne(query, UserDetails.class);
  }

  @Override
  public List<UserDetails> findByAuthDetail_EmailOrAuthDetail_UserName(String email,
      String userName) {
    Query query = new Query();
    query.addCriteria(new Criteria().orOperator(Criteria.where("authDetail.email").is(email),
        Criteria.where("authDetail.userName").is(userName)));
    return mongoTemplate.find(query, UserDetails.class);
  }

  @Override
  public UserDetails fetchUserByEmail(String email) {
    Query query = new Query();
    query.addCriteria(Criteria.where("authDetail.email").is(email));
    return mongoTemplate.findOne(query, UserDetails.class);
  }

  @Override
  public UserDetails fetchUserByUserName(String userName) {
    Query query = new Query();
    query.addCriteria(Criteria.where("authDetail.userName").is(userName));
    return mongoTemplate.findOne(query, UserDetails.class);
  }

  @Override
  public UserDetails save(UserDetails userDetails) {
    return mongoTemplate.save(userDetails);
  }

}

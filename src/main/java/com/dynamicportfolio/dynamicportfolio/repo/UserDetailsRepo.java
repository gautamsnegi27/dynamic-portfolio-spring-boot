package com.dynamicportfolio.dynamicportfolio.repo;

import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo")
public interface UserDetailsRepo extends MongoRepository<UserDetails, Long> {
  UserDetails findByAuthDetail_EmailOrAuthDetail_UserName(String email, String userName);

  @Query("{$and : [{$or : [ \n" + "                         {\"authDetail.email\" : ?0},\n"
      + "                         {\"authDetail.userName\" : ?1}\n" + "                       ]\n"
      + "               },{\"authDetail.password\":?2}\n" + "             ]}")
  UserDetails findByEmailAndUserNameAndPassword(String email, String userName, String password);
}

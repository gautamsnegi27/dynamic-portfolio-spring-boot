package com.dynamicportfolio.dynamicportfolio.controller;


import com.dynamicportfolio.dynamicportfolio.model.AuthDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;
import com.dynamicportfolio.dynamicportfolio.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class PortfolioController {

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.UserDetailsServiceImpl")
  private UserDetailsService userDetailsService;

  private Logger logger = LoggerFactory.getLogger(PortfolioController.class);

  @GetMapping("/")
  String status() {
    return "application started";
  }

  @GetMapping("/id/:id")
  @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> getUser(
      @PathVariable("id") Long id) {
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.fetchUser(id);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @GetMapping("/user/email/:email")
  @RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> validateEmail(
      @PathVariable("email") String email) {
    logger.info("Received request to fetchByEmail email: {}", email);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.fetchUserByEmail(email);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @GetMapping("/user/username/:username")
  @RequestMapping(method = RequestMethod.GET, value = "/user/username/{username}")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> validateUserName(
      @PathVariable("username") String userName) {
    logger.info("Received request to fetchByEmail userName: {}", userName);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.fetchByUserName(userName);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @PostMapping("/create/user")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> createUser(
      @RequestBody UserDetailsModel userDetailsModel) {
    logger.info("Received request to create user for userDetailsModel: {}", userDetailsModel);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.createUser(userDetailsModel);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @PostMapping("/login")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> login(
      @RequestBody AuthDetailModel authDetailModel) {
    logger
        .info("Received request for login with email: {}, userName: {}", authDetailModel.getEmail(),
            authDetailModel.getUserName());
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.getUser(authDetailModel);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @PutMapping("/update/user")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> updateUser(
      @RequestHeader("username") String username, @RequestHeader("email") String email,
      @RequestBody UserDetailsModel userDetailsModel) {
    logger.info("Received request for update with userDetailsModel: {}", userDetailsModel);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        userDetailsService.updateUser(userDetailsModel, username, email);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

}

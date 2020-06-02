package com.dynamicportfolio.dynamicportfolio.controller;


import com.dynamicportfolio.dynamicportfolio.common.DynamicProfileStatusCode;
import com.dynamicportfolio.dynamicportfolio.model.AuthDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.AuthResponse;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;
import com.dynamicportfolio.dynamicportfolio.service.UserDetailsService;
import com.dynamicportfolio.dynamicportfolio.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/dynamicportfolio")
public class PublicController {

  private Logger logger = LoggerFactory.getLogger(PortfolioController.class);

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.UserDetailsServiceImpl")
  private UserDetailsService userDetailsService;

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.CustomUserDetailsServiceImpl")
  private org.springframework.security.core.userdetails.UserDetailsService customUserDetailsService;

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.utils.JwtUtil")
  private JwtUtil jwtUtil;

  @GetMapping
  String status() {
    return "application started";
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
  ResponseEntity<?> login(@RequestBody AuthDetailModel authDetailModel) {
    logger
        .info("Received request for login with email: {}, userName: {}", authDetailModel.getEmail(),
            authDetailModel.getUserName());
    String jwtToken = null;
    if (userDetailsService.getUser(authDetailModel)) {
      UserDetails userDetails =
          customUserDetailsService.loadUserByUsername(authDetailModel.getUserName());
      jwtToken = jwtUtil.generateToken(userDetails);
    }
    return new ResponseEntity<>(new AuthResponse(jwtToken), HttpStatus.OK);
  }

  @GetMapping("/id/:id")
  @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> getUser(
      @PathVariable("id") Long id) {
    DynamicProfileResponseObject<UserDetailsModel> responseObject;
    try {
      responseObject = userDetailsService.fetchUser(id);
      if (Objects.isNull(responseObject)) {
        throw new Exception("user not found with id: " + id);
      }
    } catch (Exception e) {
      logger.error("exception occurred due to: " + e.getLocalizedMessage());
      responseObject = new DynamicProfileResponseObject<>();
      responseObject.setStatus(DynamicProfileStatusCode.USER_DOES_NOT_EXIST);
    }
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  @GetMapping("/user/:user")
  @RequestMapping(method = RequestMethod.GET, value = "/user/{user}")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> getByUserName(
      @PathVariable("user") String user) {
    Boolean status = Boolean.FALSE;
    DynamicProfileResponseObject<UserDetailsModel>
        responseObject = userDetailsService.fetchByUserName(user);
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }
}

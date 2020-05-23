package com.dynamicportfolio.dynamicportfolio.controller;


import com.dynamicportfolio.dynamicportfolio.common.DynamicProfileStatusCode;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;
import com.dynamicportfolio.dynamicportfolio.service.UserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class PortfolioController {

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.UserDetailsServiceImpl")
  private UserDetailsService userDetailsService;

  private Logger logger = LoggerFactory.getLogger(PortfolioController.class);

  @PutMapping("/update/user")
  ResponseEntity<DynamicProfileResponseObject<UserDetailsModel>> updateUser(
      @RequestHeader(value = "username", required = false) String username,
      @RequestHeader(value = "email", required = false) String email,
      @RequestBody UserDetailsModel userDetailsModel) {
    logger.info("Received request for update with userDetailsModel: {}", userDetailsModel);
    DynamicProfileResponseObject<UserDetailsModel> responseObject;
    try {
      validate(username, email);
      responseObject = userDetailsService.updateUser(userDetailsModel, username, email);
    } catch (Exception e) {
      logger.error("exception occurred due to: ", e);
      responseObject = new DynamicProfileResponseObject<>();
      responseObject.setStatus(DynamicProfileStatusCode.INVALID_ACCESS);
    }
    return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
  }

  private void validate(String... headers) throws Exception {
    for (String header : headers) {
      if (StringUtils.isBlank(header)) {
        throw new IllegalAccessException("User not logged in to get this information");
      }
    }

  }
}

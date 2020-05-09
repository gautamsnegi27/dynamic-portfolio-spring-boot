package com.dynamicportfolio.dynamicportfolio.service.impl;


import com.dynamicportfolio.dynamicportfolio.common.DynamicProfileStatusCode;
import com.dynamicportfolio.dynamicportfolio.entity.AuthDetail;
import com.dynamicportfolio.dynamicportfolio.entity.ExperienceDetail;
import com.dynamicportfolio.dynamicportfolio.entity.Project;
import com.dynamicportfolio.dynamicportfolio.entity.ServiceDetail;
import com.dynamicportfolio.dynamicportfolio.entity.SocialMediaDetails;
import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import com.dynamicportfolio.dynamicportfolio.model.AuthDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.DynamicProfileResponseObject;
import com.dynamicportfolio.dynamicportfolio.model.ExperienceDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.ProjectModel;
import com.dynamicportfolio.dynamicportfolio.model.ServiceDetailModel;
import com.dynamicportfolio.dynamicportfolio.model.SocialMediaDetailsModel;
import com.dynamicportfolio.dynamicportfolio.model.UserDetailsModel;
import com.dynamicportfolio.dynamicportfolio.repo.UserDetailsRepo;
import com.dynamicportfolio.dynamicportfolio.service.SequenceGeneratorService;
import com.dynamicportfolio.dynamicportfolio.service.UserDetailsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service("com.dynamicportfolio.dynamicportfolio.service.impl.UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

  private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Autowired
  private UserDetailsRepo userDetailsRepo;

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.SequenceGeneratorServiceImpl")
  private SequenceGeneratorService sequenceGeneratorService;

  /*@Override
  public DynamicProfileResponseObject<UserDetailsModel> createUser(
      UserDetailsModel userDetailsModel) {
    UserDetails userDetails = new UserDetails();
    userDetails = setUserDetails(userDetailsModel,userDetails);

    if (Objects.nonNull(userDetails)) {
      userDetails = userDetailsRepo.createUser(userDetails);
    }
  }*/


  @Override
  public DynamicProfileResponseObject<UserDetailsModel> createUser(
      UserDetailsModel userDetailsModel) {
    logger.info("request came to create new user: {}", userDetailsModel);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        new DynamicProfileResponseObject<>(DynamicProfileStatusCode.PROCESSING_ERROR);
    if (userDetailsModel.validate()) {
      /*UserDetails userDetails = userDetailsRepo.findByAuthDetail_EmailOrAuthDetail_UserName(
          userDetailsModel.getAuthDetailModel().getEmail(),
          userDetailsModel.getAuthDetailModel().getUserName());
      if (Objects.isNull(userDetails)) {*/
      UserDetails userDetails = new UserDetails();

        AuthDetail authDetail = new AuthDetail();
        BeanUtils.copyProperties(userDetailsModel.getAuthDetailModel(), authDetail);
        userDetails.setId(sequenceGeneratorService.generateSequence(UserDetails.SEQUENCE_NAME));
        userDetails.setName(userDetailsModel.getName());
        userDetails.setAuthDetail(authDetail);

        logger.info("sequence number created: {}", userDetails.getId());
        UserDetails userDetailsInDb = userDetailsRepo.createUser(userDetails);
        AuthDetailModel authDetailModel = new AuthDetailModel();
        if (Objects.nonNull(userDetailsInDb) && Objects.nonNull(userDetailsInDb.getAuthDetail())) {
          authDetail = userDetailsInDb.getAuthDetail();
          authDetail.setPassword(null);
          BeanUtils.copyProperties(authDetail, authDetailModel);
          UserDetailsModel userDetailsModelResponse = new UserDetailsModel();
          userDetailsModelResponse.setAuthDetailModel(authDetailModel);
          userDetailsModelResponse.setId(userDetailsInDb.getId());
          userDetailsModelResponse.setName(userDetailsInDb.getName());

          responseObject.setResponseObject(userDetailsModelResponse);
          responseObject.setStatus(DynamicProfileStatusCode.SUCCESS);
        } else {

          responseObject.setStatus(DynamicProfileStatusCode.PROCESSING_ERROR);
        }
      } else {
        logger.error("user already exists for userDetailsModel: {}", userDetailsModel);
        responseObject.setStatus(DynamicProfileStatusCode.USER_ALREADY_EXISTS);
      }
    /*} else {
      logger.error("required field is null for userDetailsModel: {}", userDetailsModel);
      responseObject.setStatus(DynamicProfileStatusCode.NULL_FIELD);
    }*/
    return responseObject;
  }

  @Override
  public DynamicProfileResponseObject<UserDetailsModel> fetchUser(Long id) {
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        new DynamicProfileResponseObject<>(DynamicProfileStatusCode.PROCESSING_ERROR);

    UserDetails userDetails = userDetailsRepo.fetchUser(id);
    UserDetailsModel userDetailsModel = new UserDetailsModel();
    userDetailsModel = setUserDetailsModel(userDetails, userDetailsModel);
    if (Objects.nonNull(userDetailsModel.getAuthDetailModel())){
      userDetailsModel.getAuthDetailModel().setPassword(null);
    }

    responseObject.setResponseObject(userDetailsModel);
    responseObject.setStatus(DynamicProfileStatusCode.SUCCESS);
    return responseObject;

  }

  /*@Override
  public DynamicProfileResponseObject<UserDetailsModel> getUser(AuthDetailModel authDetailModel) {
    logger.info("request came to fetch email: {}, username: {}", authDetailModel.getEmail(),
        authDetailModel.getUserName());
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        new DynamicProfileResponseObject<>(DynamicProfileStatusCode.PROCESSING_ERROR);
    UserDetails userDetails = userDetailsRepo.
        findByEmailAndUserNameAndPassword(authDetailModel.getEmail(), authDetailModel.getUserName(),
            authDetailModel.getPassword());

    if (Objects.nonNull(userDetails)) {
      UserDetailsModel userDetailsModel = new UserDetailsModel();
      userDetailsModel = setUserDetailsModel(userDetails, userDetailsModel);

      responseObject.setResponseObject(userDetailsModel);
      responseObject.setStatus(DynamicProfileStatusCode.SUCCESS);
    } else {
      logger.error("invalid user details for authDetailModel: {}", authDetailModel);
      responseObject.setStatus(DynamicProfileStatusCode.INVALID_DETAILS);
    }
    return responseObject;
  }*/

  private UserDetailsModel setUserDetailsModel(UserDetails userDetails,
      UserDetailsModel userDetailsModel) {
    SocialMediaDetailsModel socialMediaDetailsModel;
    List<ServiceDetailModel> serviceDetailModels;
    List<ProjectModel> projectModels;
    List<ExperienceDetailModel> experienceDetailModels;


    if (CollectionUtils.isNotEmpty(userDetails.getServiceDetails())) {
      serviceDetailModels = new ArrayList<>();
      userDetails.getServiceDetails().forEach(serviceDetail -> {
        ServiceDetailModel serviceDetailModel = new ServiceDetailModel();
        BeanUtils.copyProperties(serviceDetail, serviceDetailModel);
        serviceDetailModels.add(serviceDetailModel);
      });
      userDetailsModel.setServiceDetailModels(serviceDetailModels);
    }

    if (CollectionUtils.isNotEmpty(userDetails.getProjects())) {
      projectModels = new ArrayList<>();
      userDetails.getProjects().forEach(project -> {
        ProjectModel projectModel = new ProjectModel();
        BeanUtils.copyProperties(project, projectModel);
        projectModels.add(projectModel);
      });
      userDetailsModel.setProjectModels(projectModels);
    }

    if (CollectionUtils.isNotEmpty(userDetails.getExperienceDetails())) {
      experienceDetailModels = new ArrayList<>();
      userDetails.getExperienceDetails().forEach(experienceDetail -> {
        ExperienceDetailModel experienceDetailModel = new ExperienceDetailModel();
        BeanUtils.copyProperties(experienceDetail, experienceDetailModel);
        experienceDetailModels.add(experienceDetailModel);
      });
      userDetailsModel.setExperienceDetailModels(experienceDetailModels);
    }
    if (Objects.nonNull(userDetails.getSocialMediaDetails())) {
      socialMediaDetailsModel = new SocialMediaDetailsModel();
      BeanUtils.copyProperties(userDetails.getSocialMediaDetails(), socialMediaDetailsModel);
      userDetailsModel.setSocialMediaDetailsModel(socialMediaDetailsModel);
    }
    userDetailsModel.setName(userDetails.getName());
    userDetailsModel.setDescription(userDetails.getDescription());
    if (Objects.nonNull(userDetails.getId())) {
      userDetailsModel.setId(userDetails.getId());
    }
    return userDetailsModel;
  }

  /*@Override
  public DynamicProfileResponseObject<UserDetailsModel> updateUser(
      UserDetailsModel userDetailsModel) {
    logger.info("request came to update new user: {}", userDetailsModel);
    DynamicProfileResponseObject<UserDetailsModel> responseObject =
        new DynamicProfileResponseObject<>(DynamicProfileStatusCode.PROCESSING_ERROR);
    if (userDetailsModel.validate()) {
      UserDetails userDetails = userDetailsRepo.findByAuthDetail_EmailOrAuthDetail_UserName(
          userDetailsModel.getAuthDetailModel().getEmail(),
          userDetailsModel.getAuthDetailModel().getUserName());
      if (Objects.nonNull(userDetails)) {
        userDetails = setUserDetails(userDetailsModel, userDetails);

        userDetails = userDetailsRepo.save(userDetails);

        userDetailsModel = setUserDetailsModel(userDetails, userDetailsModel);
        if (Objects.nonNull(userDetailsModel.getAuthDetailModel())){
          userDetailsModel.getAuthDetailModel().setPassword(null);
        }

        responseObject.setResponseObject(userDetailsModel);
        responseObject.setStatus(DynamicProfileStatusCode.SUCCESS);
      }
    } else {
      logger.error("user already exists for userDetailsModel: {}", userDetailsModel);
      responseObject.setStatus(DynamicProfileStatusCode.USER_DOES_NOT_EXIST);
    }
    return responseObject;
  }*/

  private UserDetails setUserDetails(UserDetailsModel userDetailsModel, UserDetails userDetails) {
    SocialMediaDetails socialMediaDetails;
    List<ServiceDetail> serviceDetails;
    List<Project> projects;
    List<ExperienceDetail> experienceDetails;

    if (CollectionUtils.isNotEmpty(userDetailsModel.getServiceDetailModels())) {
      serviceDetails = new ArrayList<>();
      userDetailsModel.getServiceDetailModels().forEach(serviceDetailModel -> {
        ServiceDetail serviceDetail = new ServiceDetail();
        BeanUtils.copyProperties(serviceDetailModel, serviceDetail);
        serviceDetails.add(serviceDetail);
      });
      userDetails.setServiceDetails(serviceDetails);
    }

    if (CollectionUtils.isNotEmpty(userDetailsModel.getProjectModels())) {
      projects = new ArrayList<>();
      userDetailsModel.getProjectModels().forEach(projectModel -> {
        Project project = new Project();
        BeanUtils.copyProperties(projectModel, project);
        projects.add(project);
      });
      userDetails.setProjects(projects);
    }

    if (CollectionUtils.isNotEmpty(userDetailsModel.getExperienceDetailModels())) {
      experienceDetails = new ArrayList<>();
      userDetailsModel.getExperienceDetailModels().forEach(experienceDetailModel -> {
        ExperienceDetail experienceDetail = new ExperienceDetail();
        BeanUtils.copyProperties(experienceDetailModel, experienceDetail);
        experienceDetails.add(experienceDetail);
      });
      userDetails.setExperienceDetails(experienceDetails);

      if (Objects.nonNull(userDetailsModel.getSocialMediaDetailsModel())) {
        socialMediaDetails = new SocialMediaDetails();
        BeanUtils.copyProperties(userDetailsModel.getSocialMediaDetailsModel(), socialMediaDetails);
        userDetails.setSocialMediaDetails(socialMediaDetails);
      }
    }

    if (StringUtils.isNotBlank(userDetailsModel.getName())) {
      userDetails.setName(userDetailsModel.getName());
    }

    if (StringUtils.isNotBlank(userDetailsModel.getDescription())) {
      userDetails.setName(userDetailsModel.getDescription());
    }
    return userDetails;
  }


}

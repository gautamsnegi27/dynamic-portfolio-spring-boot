package com.dynamicportfolio.dynamicportfolio.listener;

import com.dynamicportfolio.dynamicportfolio.service.SequenceGeneratorService;
import com.dynamicportfolio.dynamicportfolio.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UserModelListener extends AbstractMongoEventListener<UserDetails> {

  @Autowired
  @Qualifier("com.dynamicportfolio.dynamicportfolio.service.impl.SequenceGeneratorServiceImpl")
  private SequenceGeneratorService sequenceGeneratorService;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<UserDetails> event) {
    if (event.getSource().getId() < 1) {
      event.getSource().setId(sequenceGeneratorService.generateSequence(UserDetails.SEQUENCE_NAME));
    }
  }
}

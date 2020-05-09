package com.dynamicportfolio.dynamicportfolio.service.impl;

import com.dynamicportfolio.dynamicportfolio.entity.DatabaseSequence;
import com.dynamicportfolio.dynamicportfolio.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service("com.dynamicportfolio.dynamicportfolio.service.impl.SequenceGeneratorServiceImpl")
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Long generateSequence(String seqName) {
    DatabaseSequence databaseSequence = mongoTemplate
        .findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
            options().returnNew(true).upsert(true), DatabaseSequence.class);
    return !Objects.isNull(databaseSequence) ? databaseSequence.getSeq() : 1;
  }
}
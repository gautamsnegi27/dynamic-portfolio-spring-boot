package com.dynamicportfolio.dynamicportfolio.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
@ComponentScan(basePackages = "com.dynamicportfolio.dynamicportfolio")
public class ApplicationConfig {
  @Value("${spring.data.mongodb.uri}")
  private String uri;

  public MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoClientDbFactory(uri);
  }

  @Bean
  public MongoTemplate getMongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory());
  }

}

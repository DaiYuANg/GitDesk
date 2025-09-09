package org.git.desk.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;

@Factory
@Slf4j
public class JacksonFactory {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}

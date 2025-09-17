package org.git.desk.bean;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Factory
@Slf4j
public class ServiceFactory {

  @Bean
  ServiceManager serviceManager(List<Service> services) {
    log.atInfo().log("Service:{}", services);
    return new ServiceManager(services);
  }
}

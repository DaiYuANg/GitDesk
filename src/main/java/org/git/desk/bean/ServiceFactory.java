package org.git.desk.bean;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;

import java.util.List;

@Factory
public class ServiceFactory {

  @Bean
  ServiceManager serviceManager(List<Service> services) {
    return new ServiceManager(services);
  }
}

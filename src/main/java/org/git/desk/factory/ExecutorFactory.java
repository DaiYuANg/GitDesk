package org.git.desk.factory;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

@Factory
@Slf4j
@RequiredArgsConstructor
public class ExecutorFactory {

  @Bean
  ListeningScheduledExecutorService listeningScheduledExecutorService() {
    return MoreExecutors.listeningDecorator(
      Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r);
        t.setName("FetchScheduler");
        t.setDaemon(true);
        return t;
      })
    );
  }
}

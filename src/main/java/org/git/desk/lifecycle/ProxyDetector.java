package org.git.desk.lifecycle;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.ProxySelector;
import java.net.URI;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class ProxyDetector extends AbstractExecutionThreadService {
  @Override
  protected void run() throws Exception {
    log.atInfo().log("Setup proxy");
    System.setProperty("java.net.useSystemProxies", "true");
    ProxySelector.getDefault()
      .select(URI.create("https://github.com"))
      .forEach(p -> log.atInfo().log("Detected proxy: " + p));
  }
}

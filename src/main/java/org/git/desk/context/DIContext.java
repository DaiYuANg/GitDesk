package org.git.desk.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.experimental.Delegate;

import static io.avaje.inject.BeanScope.*;

@Getter
public enum DIContext {
  INSTANCE;

  @Delegate
  @SuppressWarnings({"removal", "deprecation"})
  private final BeanScope beanScope = builder()
    .shutdownHook(true)
    .build();
}
package org.git.desk.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.experimental.Delegate;

import static io.avaje.inject.BeanScope.*;

@Getter
@SuppressWarnings({"removal", "deprecation"})
public enum DIContext {
  INSTANCE;

  @Delegate
  private final BeanScope beanScope = builder()
    .shutdownHook(true)
    .build();
}
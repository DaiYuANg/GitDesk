package org.git.desk.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
public enum DIContext {
  INSTANCE;

  @Delegate
  private final BeanScope beanScope = BeanScope.builder().shutdownHook(true).build();
}
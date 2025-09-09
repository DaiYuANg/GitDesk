package org.git.desk.factory;

import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.context.DIContext;

@Slf4j
public class ControllerFactory implements Callback<Class<?>, Object> {
  @Override
  public Object call(Class<?> aClass) {
    return DIContext.INSTANCE.get(aClass);
  }
}

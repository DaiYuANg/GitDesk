package org.git.desk;

import com.google.common.util.concurrent.ServiceManager;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.context.DIContext;

@Slf4j
public class Launcher {

  private static void init() {
    val serviceManager = DIContext.INSTANCE.get(ServiceManager.class);
    serviceManager.startAsync();
  }

  public static void main(String[] args) {
    init();
    Application.launch(GitDeskApplication.class, args);
  }
}

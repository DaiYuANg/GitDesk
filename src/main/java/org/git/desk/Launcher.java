package org.git.desk;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Launcher {
  public static void main(String[] args) {
    Application.launch(GitDeskApplication.class, args);
  }
}

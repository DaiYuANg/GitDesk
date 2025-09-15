package org.git.desk;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.jthemedetecor.OsThemeDetector;
import com.techsenger.stagepro.core.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.controller.LeftStandardStageController;
import org.git.desk.loader.FXMLLoaderFactory;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class GitDeskApplication extends Application {


  @Override
  public void init() {
    val detector = OsThemeDetector.getDetector();
    val isDarkThemeUsed = detector.isDark();
    setUserAgentStylesheet(isDarkThemeUsed ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet());
    log.atInfo().log("current:{}", isDarkThemeUsed);
  }

  private static final double SAMPLE_STAGE_WIDTH = 800;

  private static final double SAMPLE_STAGE_HEIGHT = 600;

  @Override
  public void start(Stage stage) {
    val loaded = FXMLLoaderFactory.load("MainView.fxml");
    val scene = new Scene(loaded, 320, 240);
    var controller = new LeftStandardStageController(stage, SAMPLE_STAGE_WIDTH, SAMPLE_STAGE_HEIGHT);
    setTitle(controller);
    controller.setContent(scene.getRoot());
    stage.show();
  }

  private void setTitle(@NotNull SimpleStageController controller) {
    controller.getTitleLabel().setText("Title");
  }
}

package org.git.desk;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.google.common.util.concurrent.ServiceManager;
import com.jthemedetecor.OsThemeDetector;
import com.techsenger.stagepro.core.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.context.DIContext;
import org.git.desk.controller.LeftStandardStageController;
import org.git.desk.loader.FXMLLoaderFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.techsenger.stagepro.core.MaximizeButton.*;
import static javafx.application.Application.setUserAgentStylesheet;

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

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

import java.util.Objects;

import static com.techsenger.stagepro.core.MaximizeButton.*;
import static javafx.application.Application.setUserAgentStylesheet;

@Slf4j
public class GitDeskApplication extends Application {

  private final ServiceManager serviceManager = DIContext.INSTANCE.get(ServiceManager.class);

  @Override
  public void init() {
    serviceManager.startAsync();
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

  private void setTitle(SimpleStageController controller) {
    controller.getTitleLabel().setText("Title");

  }

  private void setContent(BaseStageController controller) {
    var stage = controller.getStage();
    var gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    var rowIndex = 0;
    if (controller.getClass() == BaseStageController.class) {
      var button = new Button("Close");
      button.setMaxWidth(Double.MAX_VALUE);
      button.setOnAction(e -> stage.close());
      GridPane.setHgrow(button, Priority.ALWAYS);
      GridPane.setColumnSpan(button, 2);
      gridPane.add(button, 0, rowIndex);
      rowIndex++;
    }
    if (controller instanceof StandardStageController) {
      gridPane.add(new Label("Max Button Policy"), 0, rowIndex);
      var polcies = FXCollections.observableArrayList(ResizableStatePolicy.VISIBILITY,
        ResizableStatePolicy.INTERACTIVITY);
      var policyComboBox = new ComboBox<ResizableStatePolicy>(polcies);
      var maxButton = ((StandardStageController) controller).getMaximizeButton();
      policyComboBox.valueProperty().bindBidirectional(maxButton.policyProperty());
      gridPane.add(policyComboBox, 1, rowIndex);
      rowIndex++;
    }

    var resizableCheckBox = new CheckBox("Resizable");
    resizableCheckBox.selectedProperty().bindBidirectional(stage.resizableProperty());
    gridPane.add(resizableCheckBox, 0, rowIndex);
    var darkThemeCheckBox = new CheckBox("Dark Theme");
    darkThemeCheckBox.selectedProperty().addListener((ov, oldV, newV) -> {
      if (newV) {
        controller.getStage().getScene().getRoot().getStyleClass().add("dark");
      } else {
        controller.getStage().getScene().getRoot().getStyleClass().remove("dark");
      }
    });
    gridPane.add(darkThemeCheckBox, 1, rowIndex);

    rowIndex++;
    EventHandler<StageResizeEvent> started = e -> System.out.println("Resize started");
    EventHandler<StageResizeEvent> finished = e -> System.out.println("Resize finished");
    var resizeHandlersCheckBox = new CheckBox("Resize Handlers");
    resizeHandlersCheckBox.selectedProperty().addListener((ov, oldV, newV) -> {
      if (newV) {
        stage.addEventHandler(StageResizeEvent.STAGE_RESIZE_STARTED, started);
        stage.addEventHandler(StageResizeEvent.STAGE_RESIZE_FINISHED, finished);
      } else {
        stage.removeEventHandler(StageResizeEvent.STAGE_RESIZE_STARTED, started);
        stage.removeEventHandler(StageResizeEvent.STAGE_RESIZE_FINISHED, finished);
      }
    });
    gridPane.add(resizeHandlersCheckBox, 0, rowIndex);

    var content = new BorderPane();
    content.setCenter(gridPane);
    controller.setContent(content);
  }
}

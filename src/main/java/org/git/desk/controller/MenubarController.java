package org.git.desk.controller;

import io.avaje.inject.Component;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@RequiredArgsConstructor
@Component
public class MenubarController implements Initializable {
  public HBox titleBar;
  public Button minButton;
  public Button maxButton;
  public Button closeButton;

  private double xOffset = 0;
  private double yOffset = 0;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setupDrag();
    setupButtons();
  }

  private void setupDrag() {
    titleBar.setOnMousePressed((MouseEvent event) -> {
      log.atInfo().log("Mouse press");
      Stage stage = (Stage) titleBar.getScene().getWindow();
      xOffset = stage.getX() - event.getScreenX();
      yOffset = stage.getY() - event.getScreenY();
    });

    titleBar.setOnMouseDragged((MouseEvent event) -> {
      Stage stage = (Stage) titleBar.getScene().getWindow();
      if (!stage.isMaximized()) {  // 最大化状态下不要拖动
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
      }
    });
  }

  private void setupButtons() {

    minButton.setOnAction(e -> {
      Stage stage = (Stage) titleBar.getScene().getWindow();
      stage.setIconified(true);
    });
    maxButton.setOnAction(e -> {

      Stage stage = (Stage) titleBar.getScene().getWindow();
      stage.setMaximized(!stage.isMaximized());
    });
    closeButton.setOnAction(e -> {
      Stage stage = (Stage) titleBar.getScene().getWindow();

      stage.close();
    });
  }

}

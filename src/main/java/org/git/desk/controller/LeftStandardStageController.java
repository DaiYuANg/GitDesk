package org.git.desk.controller;

import com.techsenger.stagepro.core.StandardStageController;
import com.techsenger.toolkit.fx.Spacer;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import org.git.desk.component.menu.FileMenu;

import java.util.List;

public class LeftStandardStageController extends StandardStageController {

  private final Menu fileMenu = new FileMenu();

  private final Menu editMenu = new Menu("_Edit");

  private final Menu helptMenu = new Menu("_Help");

  private final MenuBar menuBar = new MenuBar(fileMenu, editMenu, helptMenu);

  private final List<Node> buttonList = List.of(getMinimizeButton(), getMaximizeButton(), getCloseButton());

  public LeftStandardStageController(Stage stage, double width, double height) {
    super(stage, width, height, false);
    init();
  }

  private void init() {
    getButtonBox().getChildren().addAll(buttonList);
    getTitleBar().getChildren().addAll(getIconView(), menuBar, new Spacer(), getButtonBox());
  }
}
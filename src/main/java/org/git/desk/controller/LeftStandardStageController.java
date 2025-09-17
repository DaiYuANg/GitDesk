package org.git.desk.controller;

import com.techsenger.stagepro.core.StandardStageController;
import com.techsenger.toolkit.fx.Spacer;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import lombok.val;
import org.git.desk.component.FileMenu;
import org.git.desk.component.HelpMenu;

import java.util.Arrays;
import java.util.List;

public class LeftStandardStageController extends StandardStageController {

  private final Menu fileMenu = new FileMenu();
  private final Menu editMenu = new Menu("_Edit");
  private final Menu helpMenu = new HelpMenu();
  private final MenuBar menuBar = new MenuBar(fileMenu, editMenu, helpMenu);

  public LeftStandardStageController(Stage stage, double width, double height) {
    // false 参数表示不使用默认居中
    super(stage, width, height, false);

    // 初始化按钮列表
    val buttonList = Arrays.asList(getMinimizeButton(), getMaximizeButton(), getCloseButton());

    // 添加按钮到 buttonBox
    getButtonBox().getChildren().addAll(buttonList);

    // 添加菜单栏、图标和按钮到 titleBar
    getTitleBar().getChildren().addAll(getIconView(), menuBar, new Spacer(), getButtonBox());
  }
}

package org.git.desk.component;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.git.desk.loader.FXMLLoaderFactory;
import org.kordamp.ikonli.javafx.FontIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMenu extends Menu {

  private static final Logger log = LoggerFactory.getLogger(FileMenu.class);

  private final MenuItem addAccount;
  private final MenuItem addRepository;

  public FileMenu() {
    super("_File", new FontIcon("mdi2f-folder"));

    // ───── 初始化菜单项 ─────
    addAccount = new IconMenuItem("Add Account", "mdi2f-folder-account");
    addRepository = new IconMenuItem("Add Repository", "mdi2s-source-repository"); // 可换图标

    // ───── 设置事件 ─────
    addAccount.setOnAction((ActionEvent event) -> openForm("AddAccountForm.fxml", "Add Account"));
    addRepository.setOnAction((ActionEvent event) -> openForm("AddRepositoryForm.fxml", "Add Repository"));

    // ───── 添加到菜单 ─────
    this.getItems().addAll(addAccount, addRepository);
  }

  // ───── 打开表单窗口 ─────
  private void openForm(String fxmlPath, String title) {
    try {
      var root = FXMLLoaderFactory.load(fxmlPath);
      Stage stage = new Stage();
      stage.setTitle(title);
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      log.error("Failed to open form: {}", fxmlPath, e);
    }
  }
}

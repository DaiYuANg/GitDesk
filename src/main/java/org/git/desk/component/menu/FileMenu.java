package org.git.desk.component.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.loader.FXMLLoaderFactory;
import org.kordamp.ikonli.javafx.FontIcon;

@Slf4j
public class FileMenu extends Menu {

  private final MenuItem addAccount = new MenuItem("Add Account", new FontIcon("mdi2f-folder-account"));
  private final MenuItem addRepository = new MenuItem("Add Repository", new FontIcon("mdi2s-source-repository")); // 这里可以换别的图标

  public FileMenu() {
    super("_File", new FontIcon("mdi2f-folder")); // File 菜单本身也能有图标
    init();
  }

  private void init() {
    addAccount.setOnAction(event -> openForm("AddAccountForm.fxml", "Add Account"));
    addRepository.setOnAction(event -> openForm("AddRepositoryForm.fxml", "Add Repository"));
    this.getItems().addAll(addAccount, addRepository);
  }

  private void openForm(String fxmlPath, String title) {
    try {
      val root = FXMLLoaderFactory.load(fxmlPath);
      Stage stage = new Stage();
      stage.setTitle(title);
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      log.error("Failed to open form: {}", fxmlPath, e);
    }
  }
}

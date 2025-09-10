package org.git.desk.controller;

import io.avaje.inject.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.model.Account;
import org.git.desk.store.AccountKeystore;
import org.kohsuke.github.GitHub;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddAccountController implements Initializable {
  @FXML
  private ComboBox<String> platformComboBox;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  private final AccountKeystore keystore;

  @SneakyThrows
  @FXML
  private void onSubmit() {
    String platform = platformComboBox.getValue();
    String username = usernameField.getText();
    String password = passwordField.getText();

    log.info("Account submitted -> Platform: {}, Username: {}, Password: {}",
      platform, username, password);
    Account acc = new Account("GitHub", null, "myUser", "ghp_123456");
    val github = GitHub.connect();
    keystore.saveAccount(acc);
    // TODO: 保存到数据库 / 配置文件
    platformComboBox.getScene().getWindow().hide(); // 关闭窗口
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
}

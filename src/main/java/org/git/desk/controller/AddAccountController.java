package org.git.desk.controller;

import module javafx.fxml;
import io.avaje.inject.Component;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.constant.GitPlatform;
import org.git.desk.entity.Account;
import org.git.desk.repository.AccountRepository;

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

  private final AccountRepository accountRepository;

  @SneakyThrows
  @FXML
  private void onSubmit() {
    val platform = platformComboBox.getValue();
    val username = usernameField.getText();
    val password = passwordField.getText();

    log.info("Account submitted -> Platform: {}, Username: {}, Password: {}",
      platform, username, password);
    val acc = new Account().setPlatform(GitPlatform.GITHUB).setUsername(username).setPassword(password);
    accountRepository.save(acc);
    platformComboBox.getScene().getWindow().hide(); // 关闭窗口
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
}

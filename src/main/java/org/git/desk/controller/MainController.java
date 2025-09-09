package org.git.desk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avaje.inject.Component;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.GitDeskApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Slf4j
@RequiredArgsConstructor
public class MainController implements Initializable {

  private final ObjectMapper objectMapper;
  @FXML
  private ListView<String> platformList;
  @FXML
  private ListView<String> accountList;
  @FXML
  private TabPane repoTabPane;
  @FXML
  private Label statusLabel;

  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    platformList.getItems().addAll("GitHub", "GitLab", "Gitea", "Bitbucket");
    platformList.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
      loadAccountsForPlatform(val);
    });

    accountList.setOnMouseClicked(e -> {
      if (e.getClickCount() == 2) {
        String account = accountList.getSelectionModel().getSelectedItem();
        if (account != null) openRepository(account);
      }
    });
  }

  private void loadAccountsForPlatform(String platform) {
    accountList.getItems().clear();
    // TODO: 根据 platform 加载账号
    accountList.getItems().addAll("account1", "account2");
  }

  private void openRepository(String account) {
    try {
      FXMLLoader loader = new FXMLLoader(GitDeskApplication.class.getResource("RepositoryView.fxml"));
      Parent repoView = loader.load();
      Tab tab = new Tab(account + " Repo", repoView);
      tab.setClosable(true);
      repoTabPane.getTabs().add(tab);
      repoTabPane.getSelectionModel().select(tab);
      statusLabel.setText("Opened repository for " + account);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
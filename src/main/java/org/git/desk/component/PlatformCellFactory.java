package org.git.desk.component;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.constant.GitPlatform;
import org.kordamp.ikonli.javafx.FontIcon;

@Slf4j
public class PlatformCellFactory implements Callback<ListView<GitPlatform>, ListCell<GitPlatform>> {
  @Override
  public ListCell<GitPlatform> call(ListView<GitPlatform> param) {
    return new ListCell<>() {
      private final FontIcon icon = new FontIcon();

      @Override
      protected void updateItem(GitPlatform item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
          setGraphic(null);
        } else {
          setText(item.getPlatform());
          switch (item) {
            case GITHUB -> icon.setIconLiteral("si-github");
            case GITLAB -> icon.setIconLiteral("si-gitlab");
            case GITEA -> icon.setIconLiteral("si-gitea");
          }
          icon.setIconSize(16);
          setGraphic(icon);
        }
      }
    };
  }
}

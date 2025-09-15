package org.git.desk.component;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.val;
import org.git.desk.constant.GitPlatform;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;

import static org.git.desk.constant.GitPlatform.GITHUB;

public class PlatformComboBox extends ComboBox<GitPlatform> {

  public PlatformComboBox() {
    super();
    init();
  }

  private void init() {
    getItems().addAll(GitPlatform.values());
    val cellFactory = new PlatformCellFactory();
    this.setCellFactory(cellFactory);
    this.setButtonCell(cellFactory.call(null));
    this.getSelectionModel().selectFirst();
  }

  /**
   * 内部类，专门处理 ComboBox 渲染逻辑
   */
  private static class PlatformCellFactory implements Callback<ListView<GitPlatform>, ListCell<GitPlatform>> {
    @Contract("_ -> new")
    @Override
    public @NotNull ListCell<GitPlatform> call(ListView<GitPlatform> listView) {
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
}
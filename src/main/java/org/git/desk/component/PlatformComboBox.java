package org.git.desk.component;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.val;
import org.kordamp.ikonli.javafx.FontIcon;

public class PlatformComboBox extends ComboBox<String> {

  public PlatformComboBox() {
    super();
    init();
  }

  private void init() {
    this.getItems().addAll("GitHub", "GitLab", "Gitea");

    val cellFactory = new PlatformCellFactory();
    this.setCellFactory(cellFactory);
    this.setButtonCell(cellFactory.call(null));
    this.getSelectionModel().selectFirst();
  }
  /**
   * 内部类，专门处理 ComboBox 渲染逻辑
   */
  private static class PlatformCellFactory implements Callback<ListView<String>, ListCell<String>> {
    @Override
    public ListCell<String> call(ListView<String> listView) {
      return new ListCell<>() {
        private final FontIcon icon = new FontIcon();

        @Override
        protected void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
            setGraphic(null);
          } else {
            setText(item);
            switch (item) {
              case "GitHub" -> icon.setIconLiteral("si-github");
              case "GitLab" -> icon.setIconLiteral("si-gitlab");
              case "Gitea" -> icon.setIconLiteral("si-gitea");
            }
            icon.setIconSize(16);
            setGraphic(icon);
          }
        }
      };
    }
  }
}
package org.git.desk.component;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.awt.Desktop;
import java.io.InputStream;
import java.net.URI;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class AboutDialog extends Dialog<Void> {

  public AboutDialog() {
    setTitle("About");
    setHeaderText(null);

    // ───── 顶部标题区 ─────
    Label titleLabel = new Label(get("Implementation-Title") + " " + get("Implementation-Version"));
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    Label groupLabel = new Label("Group: " + get("Implementation-Group"));

    VBox titleBox = new VBox(titleLabel, groupLabel);
    titleBox.setSpacing(6);
    titleBox.setPadding(new Insets(10, 10, 10, 10));

    // ───── 分组内容 ─────
    GridPane buildInfo = infoGrid(
      new String[][]{
        {"Built By", get("Built-By")},
        {"Built Host", get("Built-Host")},
        {"Built Date", get("Built-Date")},
        {"Built OS", get("Built-OS")},
        {"Built JDK", get("Built-JDK")}
      }
    );

    GridPane gitInfo = infoGrid(
      new String[][]{
        {"Repository", get("SCM-Repository")},
        {"Branch", get("SCM-Branch")},
        {"Commit Hash", get("SCM-Commit-Hash")},
        {"Commit Message", get("SCM-Commit-Message")},
        {"Commit Author", get("SCM-Commit-Author")},
        {"Commit Date", get("SCM-Commit-Date")}
      }
    );

    TitledPane buildPane = new TitledPane("Build Information", buildInfo);
    buildPane.setExpanded(true);

    TitledPane gitPane = new TitledPane("Version Control", gitInfo);
    gitPane.setExpanded(true);

    // ───── 总布局 ─────
    VBox contentBox = new VBox(15, titleBox, buildPane, gitPane);
    ScrollPane scrollPane = new ScrollPane(contentBox);
    scrollPane.setFitToWidth(true);
    VBox.setVgrow(scrollPane, Priority.ALWAYS);
    scrollPane.setStyle("-fx-background-color: transparent;");

    getDialogPane().setContent(scrollPane);

    // ───── 底部按钮 ─────
    String repoUrl = get("SCM-Repository");
    ButtonType openRepoButton = new ButtonType("Open Repository", ButtonBar.ButtonData.LEFT);
    ButtonType okButton = ButtonType.OK;

    getDialogPane().getButtonTypes().addAll(openRepoButton, okButton);

    getDialogPane().lookupButton(openRepoButton).addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
      event.consume();
      try {
        if (!"Unknown".equals(repoUrl) && Desktop.isDesktopSupported()) {
          Desktop.getDesktop().browse(new URI(repoUrl));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  // ───── 分组表格函数 ─────
  private GridPane infoGrid(String[][] pairs) {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(6);
    grid.setPadding(new Insets(10));

    for (int row = 0; row < pairs.length; row++) {
      String keyStr = pairs[row][0];
      String valueStr = pairs[row][1];

      Label key = new Label(keyStr + ":");
      key.setStyle("-fx-font-weight: bold;");

      Label value = new Label(valueStr);
      value.setWrapText(true);
      value.setMaxWidth(400);

      grid.addRow(row, key, value);
    }

    return grid;
  }

  private Manifest loadManifest() {
    try {
      InputStream resource = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
      if (resource != null) {
        return new Manifest(resource);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  String get(String key) {
    Manifest manifest = loadManifest();
    Attributes attr = manifest != null ? manifest.getMainAttributes() : null;
    return attr != null ? attr.getValue(key) : "Unknown";
  }
}

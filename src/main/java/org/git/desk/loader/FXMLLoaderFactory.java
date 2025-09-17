package org.git.desk.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.git.desk.GitDeskApplication;
import org.git.desk.factory.ControllerFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class FXMLLoaderFactory {
  private static final ControllerFactory c = new ControllerFactory();

  /**
   * 从 FXML 文件加载 UI，并通过 Avaje DI 注入 Controller
   *
   * @param fxmlPath FXML 文件路径（classpath）
   * @return 根节点
   */
  public static Parent load(String fxmlPath) {
    try {
      val fxmlUrl = GitDeskApplication.class.getResource(fxmlPath);
      requireNonNull(fxmlUrl, "FXML file not found: " + fxmlPath);

      val loader = new FXMLLoader(fxmlUrl);
      loader.setControllerFactory(c);

      return loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
    }
  }

  /**
   * 如果需要拿到 Controller，可以这样用
   */
  public static <T> T loadWithController(String fxmlPath, Class<T> controllerClass) {
    try {
      val fxmlUrl = GitDeskApplication.class.getResource(fxmlPath);
      requireNonNull(fxmlUrl, "FXML file not found: " + fxmlPath);

      val loader = new FXMLLoader(fxmlUrl);
      loader.setControllerFactory(c);

      loader.load();
      return loader.getController();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
    }
  }
}

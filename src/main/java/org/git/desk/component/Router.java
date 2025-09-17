package org.git.desk.component;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import javafx.animation.FadeTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.git.desk.loader.FXMLLoaderFactory;

public class Router extends StackPane {

  private final ObservableMap<String, Route> routes = FXCollections.observableMap(new Object2ObjectOpenHashMap<>());
  private final ObjectProperty<Route> current = new SimpleObjectProperty<>();

  public Router() {
    super();
  }

  // ───── 注册路由 ─────
  public void addRoute(Route route) {
    if (route != null && route.getPath() != null) {
      routes.put(route.getPath(), route);
    }
  }

  // ───── 导航到指定路径 ─────
  public void navigate(String path) {
    Route route = routes.get(path);
    if (route == null) {
      return;
    }

    if (current.isBound()) {
      return;
    }

    Node page = FXMLLoaderFactory.load(route.getFxml());
    if (page != null) {
      page.setOpacity(0.0);
      getChildren().add(page);
      current.set(route);

      // 可选 Fade 动画
      FadeTransition ft = new FadeTransition(Duration.millis(300), page);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      ft.play();
    }
  }

  // ───── 获取当前路由 ─────
  public Route getCurrent() {
    return current.get();
  }

  public ObjectProperty<Route> currentProperty() {
    return current;
  }
}

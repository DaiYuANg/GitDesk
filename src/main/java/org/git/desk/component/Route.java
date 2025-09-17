package org.git.desk.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Route {

  private final StringProperty path = new SimpleStringProperty();
  private final StringProperty fxml = new SimpleStringProperty();

  public Route() {
    // 默认构造器
  }

  public Route(String path, String fxml) {
    this.path.set(path);
    this.fxml.set(fxml);
  }

  // ───── path ─────
  public String getPath() {
    return path.get();
  }

  public void setPath(String path) {
    this.path.set(path);
  }

  public StringProperty pathProperty() {
    return path;
  }

  // ───── fxml ─────
  public String getFxml() {
    return fxml.get();
  }

  public void setFxml(String fxml) {
    this.fxml.set(fxml);
  }

  public StringProperty fxmlProperty() {
    return fxml;
  }
}

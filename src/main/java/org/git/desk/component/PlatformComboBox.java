package org.git.desk.component;

import javafx.scene.control.ComboBox;
import lombok.val;
import org.git.desk.constant.GitPlatform;

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
}
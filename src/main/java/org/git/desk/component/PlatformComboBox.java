package org.git.desk.component;

import javafx.scene.control.ComboBox;
import org.git.desk.constant.GitPlatform;

public class PlatformComboBox extends ComboBox<GitPlatform> {

  public PlatformComboBox() {
    super();
    init();
  }

  private void init() {
    // 添加所有平台枚举到下拉列表
    this.getItems().addAll(GitPlatform.values());

    // 设置自定义 CellFactory
    PlatformCellFactory cellFactory = new PlatformCellFactory();
    this.setCellFactory(cellFactory);
    this.setButtonCell(cellFactory.call(null));

    // 默认选中第一个
    if (!this.getItems().isEmpty()) {
      this.getSelectionModel().selectFirst();
    }
  }
}

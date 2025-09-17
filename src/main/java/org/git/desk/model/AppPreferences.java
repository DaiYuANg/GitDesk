package org.git.desk.model;

import lombok.Builder;
import lombok.Data;

/**
 * @param id     唯一标识（如 repoPath）
 * @param title  Tab 标题
 * @param fxml   对应的 FXML 文件（可选）
 * @param active 是否当前激活
 */
@Builder
@Data
public class AppPreferences {
  private final String id;
  private final String title;
  private final String fxml;
  @Builder.Default
  private boolean active = true;

  public void activate() {
    this.active = true;
  }
}
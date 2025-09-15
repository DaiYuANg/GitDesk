package org.git.desk.component;

import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.constant.GitPlatform;

@Slf4j
public class PlatformListView extends ListView<GitPlatform> {
  public PlatformListView() {
    super();
    setCellFactory(new PlatformCellFactory());
  }
}

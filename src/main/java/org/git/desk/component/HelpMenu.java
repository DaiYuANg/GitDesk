package org.git.desk.component;

import com.dlsc.preferencesfx.PreferencesFx;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.git.desk.context.DIContext;
import org.kordamp.ikonli.javafx.FontIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpMenu extends Menu {

  private static final Logger log = LoggerFactory.getLogger(HelpMenu.class);

  private final MenuItem settings;
  private final MenuItem about;

  public HelpMenu() {
    super("_Help", new FontIcon("mdi2h-help-rhombus"));

    settings = new MenuItem("Settings");
    about = new MenuItem("About");

    // ───── 设置事件 ─────
    settings.setOnAction(_ -> openPreferences());
    about.setOnAction(_ -> {
      AboutDialog dialog = new AboutDialog();
      dialog.showAndWait();
    });

    // ───── 添加菜单项 ─────
    this.getItems().addAll(settings, about);
  }

  private void openPreferences() {
    try {
      PreferencesFx preferencesFx = DIContext.INSTANCE.get(PreferencesFx.class);
      preferencesFx.show(true);
    } catch (Exception e) {
      log.error("Failed to open PreferencesFx", e);
    }
  }
}

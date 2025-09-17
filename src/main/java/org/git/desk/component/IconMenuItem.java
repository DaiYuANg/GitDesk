package org.git.desk.component;

import javafx.scene.control.MenuItem;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconMenuItem extends MenuItem {

  public IconMenuItem(String text, String icon) {
    super(text);
    setGraphic(new FontIcon(icon));
  }
}

package org.git.desk.component

import javafx.scene.control.MenuItem
import org.kordamp.ikonli.javafx.FontIcon

class IconMenuItem(text: String, icon: String) : MenuItem(text) {
  init {
    graphic = FontIcon(icon)
  }
}

package org.git.desk.component

import com.dlsc.preferencesfx.PreferencesFx
import com.dlsc.preferencesfx.model.Category
import com.dlsc.preferencesfx.model.Group
import com.dlsc.preferencesfx.model.Setting
import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.beans.property.BooleanProperty
import javafx.beans.property.DoubleProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem


class AppPreferences {
  val userName = SimpleStringProperty("User")
  val enableNotifications = SimpleBooleanProperty(true)
}

class HelpMenu : Menu() {

  private val logger = KotlinLogging.logger { }
  private val preferences = AppPreferences()
  private val settings = MenuItem("Settings")
  var stringProperty: StringProperty = SimpleStringProperty("String")
  var booleanProperty: BooleanProperty = SimpleBooleanProperty(true)
  var integerProperty: IntegerProperty = SimpleIntegerProperty(12)
  var doubleProperty: DoubleProperty = SimpleDoubleProperty(6.5)

  private val about = MenuItem("About")

  init {
    text = "_Help"
    settings.setOnAction {
      openPreferences()
    }

    about.setOnAction {
      AboutDialog().showAndWait()
    }
    items.addAll(settings, about)
  }

  private fun openPreferences() {
    val preferencesFx = PreferencesFx.of(
      preferences.javaClass,
      Category.of(
        "Category title 1",
        Setting.of("Setting title 1", stringProperty), // creates a group automatically
        Setting.of("Setting title 2", booleanProperty) // which contains both settings
      ),
      Category.of("Category title 2")
        .expand()                                       // Expand the parent category in the tree-view
        .subCategories( // adds a subcategory to "Category title 2"
          Category.of(
            "Category title 3",
            Group.of(
              "Group title 1",
              Setting.of("Setting title 3", integerProperty)
            ),
            Group.of( // group without title
              Setting.of("Setting title 3", doubleProperty)
            )
          )
        )
    )
    preferencesFx.show() // 弹出设置窗口
  }
}

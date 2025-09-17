package org.git.desk.controller

import com.techsenger.stagepro.core.StandardStageController
import com.techsenger.toolkit.fx.Spacer
import javafx.scene.Node
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.stage.Stage
import org.git.desk.component.FileMenu
import org.git.desk.component.HelpMenu
import java.util.List

class LeftStandardStageController(stage: Stage?, width: Double, height: Double) :
  StandardStageController(stage, width, height, false) {
  private val fileMenu: Menu = FileMenu()

  private val editMenu = Menu("_Edit")

  private val helpMenu: Menu = HelpMenu()

  private val menuBar = MenuBar(fileMenu, editMenu, helpMenu)

  private val buttonList: kotlin.collections.List<Node> =
    listOf<Node>(minimizeButton, maximizeButton, closeButton)

  init {
    buttonBox.children.addAll(buttonList)
    titleBar.children.addAll(iconView, menuBar, Spacer(), buttonBox)
  }
}
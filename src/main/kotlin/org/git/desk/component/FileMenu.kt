package org.git.desk.component

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.stage.Stage
import lombok.extern.slf4j.Slf4j
import org.git.desk.loader.FXMLLoaderFactory
import org.kordamp.ikonli.javafx.FontIcon

@Slf4j
class FileMenu : Menu("_File", FontIcon("mdi2f-folder")) {

  private val logger = KotlinLogging.logger { }
  private val addAccount: MenuItem = IconMenuItem("Add Account", "mdi2f-folder-account")
  private val addRepository: MenuItem = IconMenuItem("Add Repository", "mdi2s-source-repository") // 这里可以换别的图标

  init {
    init()
  }

  private fun init() {
    addAccount.onAction = EventHandler { _: ActionEvent? -> openForm("AddAccountForm.fxml", "Add Account") }
    addRepository.onAction = EventHandler { _: ActionEvent? ->
      openForm(
        "AddRepositoryForm.fxml",
        "Add Repository"
      )
    }
    this.items.addAll(addAccount, addRepository)
  }

  private fun openForm(fxmlPath: String?, title: String?) {
    try {
      val root = FXMLLoaderFactory.load(fxmlPath)
      val stage = Stage()
      stage.title = title
      stage.setScene(Scene(root))
      stage.show()
    } catch (e: Exception) {
      logger.atError {
        message = "Failed to open form: $fxmlPath"
        cause = e
      }
    }
  }
}

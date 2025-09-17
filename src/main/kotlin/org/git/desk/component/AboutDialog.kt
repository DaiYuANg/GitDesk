package org.git.desk.component

import javafx.geometry.Insets
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TitledPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import java.awt.Desktop
import java.net.URI
import java.util.jar.Manifest

class AboutDialog : Dialog<Unit>() {

  init {
    title = "About"
    headerText = null

    val manifest = loadManifest()
    val attr = manifest?.mainAttributes
    fun get(key: String) = attr?.getValue(key) ?: "Unknown"

    // ───── 顶部标题区 ─────
    val titleBox = VBox(
      Label("${get("Implementation-Title")} ${get("Implementation-Version")}").apply {
        style = "-fx-font-size: 18px; -fx-font-weight: bold;"
      },
      Label("Group: ${get("Implementation-Group")}")
    ).apply {
      spacing = 6.0
      padding = Insets(10.0, 10.0, 10.0, 10.0)
    }

    // ───── 分组内容 ─────
    val buildInfo = infoGrid(
      "Built By" to get("Built-By"),
      "Built Host" to get("Built-Host"),
      "Built Date" to get("Built-Date"),
      "Built OS" to get("Built-OS"),
      "Built JDK" to get("Built-JDK")
    )

    val gitInfo = infoGrid(
      "Repository" to get("SCM-Repository"),
      "Branch" to get("SCM-Branch"),
      "Commit Hash" to get("SCM-Commit-Hash"),
      "Commit Message" to get("SCM-Commit-Message"),
      "Commit Author" to get("SCM-Commit-Author"),
      "Commit Date" to get("SCM-Commit-Date")
    )

    val buildPane = TitledPane("Build Information", buildInfo).apply { isExpanded = true }
    val gitPane = TitledPane("Version Control", gitInfo).apply { isExpanded = true }

    // ───── 总布局 ─────
    val contentBox = VBox(15.0, titleBox, buildPane, gitPane)
    val scrollPane = ScrollPane(contentBox).apply {
      isFitToWidth = true
      VBox.setVgrow(this, Priority.ALWAYS)
      style = "-fx-background-color: transparent;"
    }

    dialogPane.content = scrollPane

    // ───── 底部按钮 ─────
    val repoUrl = get("SCM-Repository")
    val openRepoButton = ButtonType("Open Repository", ButtonBar.ButtonData.LEFT)
    val okButton = ButtonType.OK

    dialogPane.buttonTypes.addAll(openRepoButton, okButton)

    dialogPane.lookupButton(openRepoButton).addEventFilter(javafx.event.ActionEvent.ACTION) {
      it.consume()
      if (repoUrl != "Unknown" && Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI(repoUrl))
      }
    }
  }

  // ───── 分组表格函数 ─────
  private fun infoGrid(vararg pairs: Pair<String, String>): GridPane {
    val grid = GridPane().apply {
      hgap = 10.0
      vgap = 6.0
      padding = Insets(10.0)
    }
    var row = 0
    pairs.forEach { (k, v) ->
        val key = Label("$k:").apply { style = "-fx-font-weight: bold;" }
      val value = Label(v).apply {
        isWrapText = true
        maxWidth = 400.0
      }
      grid.addRow(row++, key, value)
    }
    return grid
  }
  private fun loadManifest(): Manifest? {
    val resource = javaClass.classLoader.getResource("META-INF/MANIFEST.MF")
    return resource?.openStream()?.use { Manifest(it) }
  }
}
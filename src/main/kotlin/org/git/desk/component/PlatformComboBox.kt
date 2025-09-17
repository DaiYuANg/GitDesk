package org.git.desk.component

import javafx.scene.control.ComboBox
import org.git.desk.constant.GitPlatform

class PlatformComboBox : ComboBox<GitPlatform>() {
  init {
    init()
  }

  private fun init() {
    items.addAll(*GitPlatform.entries.toTypedArray())
    val cellFactory = PlatformCellFactory()
    this.cellFactory = cellFactory
    this.buttonCell = cellFactory.call(null)
    this.selectionModel.selectFirst()
  }
}
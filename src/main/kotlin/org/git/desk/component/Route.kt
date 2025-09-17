package org.git.desk.component

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty


class Route constructor() {
  private val path: StringProperty = SimpleStringProperty()
  private val fxml: StringProperty = SimpleStringProperty()

  constructor(path: String?, fxml: String?) : this() {
    setPath(path)
    setFxml(fxml)
  }

  fun getPath(): String? {
    return path.get()
  }

  fun setPath(path: String?) {
    this.path.set(path)
  }

  fun pathProperty(): StringProperty {
    return path
  }

  fun getFxml(): String? {
    return fxml.get()
  }

  fun setFxml(fxml: String?) {
    this.fxml.set(fxml)
  }

  fun fxmlProperty(): StringProperty {
    return fxml
  }
}
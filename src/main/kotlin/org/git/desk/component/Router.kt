package org.git.desk.component

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import javafx.animation.FadeTransition
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableMap
import javafx.scene.layout.StackPane
import javafx.util.Duration.millis
import org.git.desk.loader.FXMLLoaderFactory


class Router : StackPane() {

  private val routes: ObservableMap<String, Route> by lazy {
    FXCollections.observableMap(Object2ObjectOpenHashMap())
  }

  private val current = SimpleObjectProperty<Route>()

  fun getChildrenRoutes(route: Route) {
    route.let { routes.put(it.getPath(), route) }
  }

  fun navigate(path: String) {
    val route = routes[path]
    val page = route?.let { FXMLLoaderFactory.load(it.getFxml()) }
    if (current.isBound) {
      return;
    }

    page?.let {
      page.opacity = 0.0
      children.add(page)
      current.set(route)


      // 可选动画
      val ft = FadeTransition(millis(300.0), page)
      ft.fromValue = 0.0
      ft.toValue = 1.0
      ft.play()
    }
  }
}
package org.git.desk.manager;

import io.avaje.inject.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lombok.val;
import org.git.desk.model.TabModel;

import java.util.*;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Optional.ofNullable;
import static javafx.collections.FXCollections.emptyObservableMap;

@Component
public class TabManager {

  private final ObservableMap<String, TabModel> tabs = emptyObservableMap();
  @Getter
  private TabModel activeTab;

  public void openTab(String id, String title, String fxml) {
    if (tabs.containsKey(id)) {
      setActiveTab(id);
      return;
    }
    val tab = TabModel.builder()
      .id(id)
      .title(title)
      .fxml(fxml)
      .build();
    tabs.put(id, tab);
    setActiveTab(id);
  }

  public void closeTab(String id) {
    tabs.remove(id);
    if (activeTab != null && activeTab.getId().equals(id)) {
      activeTab = tabs.values().stream().findFirst().orElse(null);
    }
  }

  public void setActiveTab(String id) {
    if (activeTab != null) activeTab.setActive(false);
    ofNullable(activeTab).ifPresent(TabModel::activate);
  }

  public Collection<TabModel> getAllTabs() {
    return unmodifiableCollection(tabs.values());
  }

  public boolean isOpen(String id) {
    return tabs.containsKey(id);
  }
}
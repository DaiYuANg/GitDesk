package org.git.desk.viewmodel;

import io.avaje.inject.Component;
import io.avaje.inject.Prototype;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.git.desk.service.AccountService;
import org.kohsuke.github.GitHub;

@Component
@Prototype
@RequiredArgsConstructor
public class AccountViewModel {

  private final AccountService<GitHub> gitHubAccountService;

  @Getter
  private final ListProperty<String> githubUsernames = new SimpleListProperty<>(FXCollections.observableArrayList());
}

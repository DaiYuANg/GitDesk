package org.git.desk.viewmodel;

import io.avaje.inject.Component;
import io.avaje.inject.Prototype;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.git.desk.constant.GitPlatform;
import org.git.desk.entity.Account;
import org.git.desk.repository.AccountRepository;

@Component
@Prototype
@Slf4j
@RequiredArgsConstructor
public class PlatformViewModel {

  private final AccountRepository accountRepository;

  @Getter
  private final ListProperty<GitPlatform> availablePlatforms = new SimpleListProperty<>(FXCollections.observableArrayList());

  public void loadAvailable() {
    val platforms = accountRepository.find(Account.class).findStream().map(Account::getPlatform).distinct().toList();
    availablePlatforms.setAll(platforms);
  }
}

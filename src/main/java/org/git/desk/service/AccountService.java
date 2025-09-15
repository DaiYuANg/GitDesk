package org.git.desk.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.entity.Account;
import org.git.desk.manager.GithubAccountManager;
import org.git.desk.repository.AccountRepository;
import org.kohsuke.github.GitHub;

public interface AccountService<ClientInstance> {
  void saveAccount(Account account);

  ClientInstance load(Account account);
}

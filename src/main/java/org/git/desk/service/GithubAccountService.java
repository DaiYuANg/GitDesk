package org.git.desk.service;

import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.entity.Account;
import org.git.desk.manager.GithubAccountManager;
import org.git.desk.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GitHub;

@Component
@Slf4j
@RequiredArgsConstructor
public class GithubAccountService implements AccountService<GitHub>{
  private final AccountRepository accountRepository;

  private final GithubAccountManager githubAccountManager;

  @Override
  public void saveAccount(Account account) {
    accountRepository.save(account);
  }

  @SneakyThrows
  @Override
  public GitHub load(@NotNull Account account) {
    return githubAccountManager.getGithub(account.getUsername(), account.getPassword());
  }
}

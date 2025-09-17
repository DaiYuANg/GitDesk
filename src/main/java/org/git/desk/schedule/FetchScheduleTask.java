package org.git.desk.schedule;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.entity.Account;
import org.git.desk.repository.AccountRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class FetchScheduleTask implements ScheduleTask {

  private final AccountRepository accountRepository;

  @Override
  public void run() {
    accountRepository.find(Account.class).findList();
  }
}

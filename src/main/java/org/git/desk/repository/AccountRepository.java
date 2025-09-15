package org.git.desk.repository;

import io.ebean.Database;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.git.desk.entity.Account;
import org.git.desk.entity.query.QAccount;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@RequiredArgsConstructor
@Singleton
public class AccountRepository {

  @Delegate
  private final Database database;

  public Optional<Account> findById(UUID id) {
    return new QAccount().id.eq(id).findOneOrEmpty();
  }
}

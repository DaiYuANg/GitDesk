package org.git.desk.factory;

import dev.dirs.BaseDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.SystemUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

@Factory
@Slf4j
public class DBFactory {

  @SneakyThrows
  @Bean
  DataSourceConfig dataSourceConfig(String storePath) {
    log.atInfo().log("store path:{}", storePath);
    val databasePath = Paths.get(storePath, "store.db");
    val dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setUsername("sa");
    dataSourceConfig.setPassword("MyStrongSecretPassword ");
    dataSourceConfig.setUrl("jdbc:h2:file:" + databasePath.toAbsolutePath() + ";CIPHER=AES;DB_CLOSE_DELAY=-1");
    return dataSourceConfig;
  }

  @Bean
  DatabaseConfig databaseConfig(DataSourceConfig dataSourceConfig) {
    val config = new DatabaseConfig();
    config.setDataSourceConfig(dataSourceConfig);
    config.setBackgroundExecutorSchedulePoolSize(Runtime.getRuntime().availableProcessors());
    config.setDisableL2Cache(true);
    config.setUseJtaTransactionManager(false);
    config.ddlGenerate(true);
    config.ddlRun(true);
    config.setRunMigration(true);
    config.ddlStrictMode(true);
    return config;
  }

  @Bean
  Database database(DatabaseConfig config) {
    return DatabaseFactory.create(config);
  }
}

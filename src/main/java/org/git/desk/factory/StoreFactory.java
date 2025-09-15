package org.git.desk.factory;

import dev.dirs.BaseDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.nio.file.Path;

@Factory
@Slf4j
public class StoreFactory {

  @Bean
  String storeBaseDir() {
    val baseDir = Path.of(BaseDirectories.get().dataDir, "GitDesk");
    return baseDir.toAbsolutePath().toString();
  }
}

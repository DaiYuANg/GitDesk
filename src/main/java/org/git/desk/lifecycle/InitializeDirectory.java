package org.git.desk.lifecycle;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import dev.dirs.BaseDirectories;
import io.avaje.inject.Component;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class InitializeDirectory extends AbstractExecutionThreadService {

  @SneakyThrows
  @Override
  public void run() {
    val baseDir = Path.of(BaseDirectories.get().dataDir, "unigit");
    log.atInfo().log("Check Directory:{}", baseDir);
    val isExists = Files.exists(baseDir);
    if (!isExists) {
      FileUtils.forceMkdir(baseDir.toFile());
    }
  }
}

package org.git.desk.lifecycle;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import dev.dirs.BaseDirectories;
import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitializeDirectory extends AbstractExecutionThreadService {

  private final String storePath;

  @SneakyThrows
  @Override
  public void run() {
    val path = Path.of(storePath);
    log.atInfo().log("Check Directory:{}", storePath);
    val isExists = Files.exists(path);
    if (!isExists) {
      FileUtils.forceMkdir(path.toFile());
    }
  }
}

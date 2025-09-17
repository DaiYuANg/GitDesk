package org.git.desk.lifecycle;

import com.dlsc.preferencesfx.PreferencesFx;
import com.google.common.util.concurrent.AbstractIdleService;
import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.context.DIContext;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveSettings extends AbstractIdleService {
  @Override
  protected void startUp() throws Exception {

  }

  @Override
  protected void shutDown() throws Exception {
    log.atInfo().log("save settings");
    DIContext.INSTANCE.get(PreferencesFx.class).saveSettings();
  }
}

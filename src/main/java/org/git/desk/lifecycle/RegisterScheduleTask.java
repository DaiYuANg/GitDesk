package org.git.desk.lifecycle;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.git.desk.schedule.ScheduleTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterScheduleTask extends AbstractExecutionThreadService {

  private final ListeningScheduledExecutorService executorService;
  private final List<ScheduleTask> tasks;

  @Override
  protected void run() throws Exception {
    tasks.forEach(task -> executorService.scheduleAtFixedRate(task, task.getInitialDelay(), task.getPeriod(), TimeUnit.MILLISECONDS));
  }
}

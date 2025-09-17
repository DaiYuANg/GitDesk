package org.git.desk.schedule;

public interface ScheduleTask extends Runnable{
  /**
   * 任务名称
   */
  default String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * 首次延迟执行时间，单位毫秒
   */
  default long getInitialDelay() {
    return 0L;
  }

  /**
   * 周期执行间隔，单位毫秒
   */
  default long getPeriod() {
    return 10000L;
  }

  /**
   * 任务是否启用
   */
  default boolean isEnabled() {
    return true;
  }
}

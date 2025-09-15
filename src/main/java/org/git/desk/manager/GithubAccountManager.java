package org.git.desk.manager;

import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class GithubAccountManager {

  private final Map<String, GitHub> githubMap = new ConcurrentHashMap<>();

  /**
   * 获取 GitHub 实例，如果内存没有，则创建并缓存
   */
  public GitHub getGithub(String username, String token) throws IOException {
    return githubMap.computeIfAbsent(username, u -> {
      try {
        log.info("Creating GitHub client for {}", username);
        return GitHub.connect(username, token);
      } catch (IOException e) {
        log.error("Failed to create GitHub client for {}", username, e);
        return null;
      }
    });
  }

  /**
   * 移除缓存（例如 token 变更或过期）
   */
  public void remove(String username) {
    githubMap.remove(username);
  }

  /**
   * 清空所有缓存
   */
  public void clear() {
    githubMap.clear();
  }

}

package org.git.desk.model;

import lombok.Data;

@Data
public class Account {
  private String platform;  // GitHub / GitLab / Gitea
  private String url;       // GitLab/Gitea 可能需要
  private String username;
  private String token;     // 或 password

  public Account() {}

  public Account(String platform, String url, String username, String token) {
    this.platform = platform;
    this.url = url;
    this.username = username;
    this.token = token;
  }
}

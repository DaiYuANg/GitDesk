package org.git.desk.constant;

import io.ebean.annotation.DbEnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter(onMethod_ = @DbEnumValue)
@RequiredArgsConstructor
public enum GitPlatform {
  GITHUB("Github"),
  GITLAB("Gitlab"),
  GITEA("Gitea");

  private final String platform;
}

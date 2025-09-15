package org.git.desk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.git.desk.constant.GitPlatform;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
public class Account extends BaseEntity {
  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private GitPlatform platform;
}

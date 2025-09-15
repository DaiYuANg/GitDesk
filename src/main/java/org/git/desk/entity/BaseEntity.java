package org.git.desk.entity;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity extends Model {
  @Id
  @GeneratedValue
  private UUID id;

  @Version
  private Long version;

  @WhenCreated
  private Instant whenCreated;

  @WhenModified
  private Instant whenModified;
}

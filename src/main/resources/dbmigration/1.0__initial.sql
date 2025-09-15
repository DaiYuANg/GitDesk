-- apply changes
create table account (
  id                            varchar(255) not null,
  username                      varchar(255),
  password                      varchar(255),
  email                         varchar(255),
  platform                      varchar(6),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_modified                 timestamp not null,
  constraint ck_account_platform check ( platform in ('Github','Gitlab','Gitea')),
  constraint pk_account primary key (id)
);

create table base_entity (
  id                            varchar(255) not null,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_modified                 timestamp not null,
  constraint pk_base_entity primary key (id)
);


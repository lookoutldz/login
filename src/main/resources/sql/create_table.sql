create table user
(
    `id`           int unsigned      not null primary key auto_increment,
    `username`     varchar(191)      not null default '',
    `password`     varchar(191)      not null default '',
    `phone`        varchar(191)               default null,
    `phone_prefix` varchar(191)               default null,
    `email`        varchar(191)               default null,
    `gender`       smallint unsigned not null default 0,
    `age`          smallint unsigned          default null,
    `description`  varchar(191)               default null,
    `ban`          boolean           not null default false,
    `create_time`  TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`  TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted`      BOOLEAN           NOT NULL DEFAULT FALSE
)
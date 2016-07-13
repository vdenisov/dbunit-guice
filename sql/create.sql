## Drop existing triggers and tables, if any
drop table if exists KEYWORDS;
drop table if exists CATEGORY_TOPICS;
drop table if exists CATEGORIES;
drop table if exists TOPICS;
drop table if exists ENVIRONMENTS;

## Set default encoding to UTF8
alter database default character set utf8
collate utf8_general_ci;

create table ENVIRONMENTS
(
  ENV_ID                      varchar(16)             not null,
  ENV_FULL_NAME               varchar(255)            not null,

  constraint ENV_PK primary key (ENV_ID)
);

create table TOPICS
(
  TP_ID           int           not null auto_increment,
  ENV_ID          varchar(16)   not null,
  TP_UPDATE_DATE  datetime      not null,
  TP_NAME         varchar(50)   not null,

  constraint TP_PK primary key (TP_ID),
  constraint TP_ENV_FK foreign key (ENV_ID) references ENVIRONMENTS (ENV_ID),
  unique index UNIQUE_TP_NAME_ENV_ID (ENV_ID, TP_NAME)
);

create table KEYWORDS
(
  TP_ID   int                     not null,
  KW_TEXT varchar(50)
          character set utf8
          collate utf8_general_ci not null,

  constraint KW_PK primary key (TP_ID, KW_TEXT),
  constraint KW_TP_FK foreign key (TP_ID) references TOPICS (TP_ID)
);

create table CATEGORIES
(
  CAT_ID          int         not null auto_increment,
  ENV_ID          varchar(16) not null,
  CAT_NAME        varchar(64),
  CAT_PARENT_ID   int,

  constraint CAT_PK primary key (CAT_ID),
  constraint CAT_ENV_FK foreign key (ENV_ID) references ENVIRONMENTS (ENV_ID),
  constraint CAT_PARENT_ID_CAT_ID foreign key (CAT_PARENT_ID) references CATEGORIES (CAT_ID)
);

create table CATEGORY_TOPICS
(
  CAT_ID int not null,
  TP_ID  int not null,

  constraint CT_PK primary key (CAT_ID, TP_ID),
  constraint CT_CAT_FK foreign key (CAT_ID) references CATEGORIES (CAT_ID),
  constraint CT_TP_FK foreign key (TP_ID) references TOPICS (TP_ID)
);
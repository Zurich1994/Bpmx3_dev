create table ACT_GE_PROPERTY (
    NAME_ varchar(64),
    VALUE_ varchar(300),
    REV_ int(11),
    primary key (NAME_)
);
 
insert into ACT_GE_PROPERTY
values ('schema.version', '5.15.1', 1);

insert into ACT_GE_PROPERTY
values ('schema.history', 'create(5.10)', 1);

insert into ACT_GE_PROPERTY
values ('next.dbid', '1', 1);

create table ACT_GE_BYTEARRAY (
    ID_ varchar(64),
    REV_ int(11),
    NAME_ varchar(255),
    DEPLOYMENT_ID_ varchar(64),
    BYTES_ BLOB,
    GENERATED_ tinyint(4) DEFAULT NULL,
    primary key (ID_)
);

create table ACT_RE_DEPLOYMENT (
    ID_ varchar(64),
    NAME_ varchar(255),
    DEPLOY_TIME_ datetime,
    primary key (ID_)
);

create table ACT_RU_EXECUTION (
    ID_ varchar(64),
    REV_ int(11),
    PROC_INST_ID_ varchar(64),
    BUSINESS_KEY_ varchar(255),
    PARENT_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),	
    SUPER_EXEC_ varchar(64),
    ACT_ID_ varchar(255),
    IS_ACTIVE_ tinyint(4),
    IS_CONCURRENT_ tinyint(4),
    IS_SCOPE_ tinyint(4),
    IS_EVENT_SCOPE_ tinyint(4),
    SUSPENSION_STATE_ int(11),
    CACHED_ENT_STATE_ int(11),
    UNIQUE KEY ACT_UNIQ_RU_BUS_KEY (PROC_DEF_ID_,BUSINESS_KEY_),
    primary key (ID_)
);
create table ACT_RU_JOB (
    ID_ varchar(64) NOT NULL,
    REV_ int(11),
    TYPE_ varchar(255) NOT NULL,
    LOCK_EXP_TIME_ datetime,
    LOCK_OWNER_ varchar(255),
    EXCLUSIVE_ bit(1),
    EXECUTION_ID_ varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    RETRIES_ int(11),
    EXCEPTION_STACK_ID_ varchar(64),
    EXCEPTION_MSG_ varchar(4000),
    DUEDATE_ datetime,
    REPEAT_ varchar(255),
    HANDLER_TYPE_ varchar(255),
    HANDLER_CFG_ varchar(4000),
    primary key (ID_)
);

create table ACT_RE_PROCDEF (
    ID_ varchar(64),
    REV_ int(11),
    CATEGORY_ varchar(255),
    NAME_ varchar(255),
    KEY_ varchar(255),
    VERSION_ int(11),
    DEPLOYMENT_ID_ varchar(64),
    RESOURCE_NAME_ varchar(4000),
    DGRM_RESOURCE_NAME_ varchar(4000),
    HAS_START_FORM_KEY_ tinyint(4),
    SUSPENSION_STATE_ int(11),
    primary key (ID_)
);

create table ACT_RU_TASK (
    ID_ varchar(64),
    REV_ int(11),
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    NAME_ varchar(255),
    PARENT_TASK_ID_ varchar(64),
    DESCRIPTION_ varchar(4000),
    TASK_DEF_KEY_ varchar(255),
    OWNER_ varchar(64),
    ASSIGNEE_ varchar(64),
    DELEGATION_ varchar(64),
    PRIORITY_ int(11),
    CREATE_TIME_ datetime,
    DUE_DATE_ datetime,
    primary key (ID_)
);

create table ACT_RU_IDENTITYLINK (
    ID_ varchar(64),
    REV_ int(11),
    GROUP_ID_ varchar(64),
    TYPE_ varchar(255),
    USER_ID_ varchar(64),
    TASK_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    primary key (ID_)
);

create table ACT_RU_VARIABLE (
    ID_ varchar(64) not null,
    REV_ int(11),
    TYPE_ varchar(255) not null,
    NAME_ varchar(255) not null,
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    TASK_ID_ varchar(64),
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint(20),
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
);

create table ACT_RU_EVENT_SUBSCR (
    ID_ varchar(64) not null,
    REV_ int(11),
    EVENT_TYPE_ varchar(255) not null,
    EVENT_NAME_ varchar(255),
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    ACTIVITY_ID_ varchar(64),
    CONFIGURATION_ varchar(255),
    CREATED_ datetime not null,
    primary key (ID_)
);

  create table ACT_HI_PROCINST (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    BUSINESS_KEY_ varchar(255),
    PROC_DEF_ID_ varchar(64) not null,
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ bigint(20),
    START_USER_ID_ varchar(255),
    START_ACT_ID_ varchar(255),
    END_ACT_ID_ varchar(255),
    SUPER_PROCESS_INSTANCE_ID_ varchar(64),
    DELETE_REASON_ varchar(4000),
   UNIQUE KEY PROC_INST_ID_ (PROC_INST_ID_),
   primary key (ID_)
);




create table ACT_HI_ACTINST (
    ID_ varchar(64) not null,
    PROC_DEF_ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    EXECUTION_ID_ varchar(64) not null,
    ACT_ID_ varchar(255) not null,
    ACT_NAME_ varchar(255),
    ACT_TYPE_ varchar(255) not null,
    ASSIGNEE_ varchar(64),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    ISSTART tinyint(1) default 0,
    DURATION_ bigint(20),
    primary key (ID_)
);

create table ACT_HI_TASKINST (
    ID_ varchar(64) not null,
    PROC_DEF_ID_ varchar(64),
    TASK_DEF_KEY_ varchar(255),
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    PARENT_TASK_ID_ varchar(64),
    NAME_ varchar(255),
    DESCRIPTION_ varchar(4000),
    OWNER_ varchar(64),
    ASSIGNEE_ varchar(64),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ bigint(20),
    DELETE_REASON_ varchar(4000),
    PRIORITY_ int(11),
    DUE_DATE_ datetime,
    primary key (ID_)
);

create table ACT_HI_DETAIL (
    ID_ varchar(64) not null,
    TYPE_ varchar(255) not null,
    PROC_INST_ID_ varchar(64) not null,
    EXECUTION_ID_ varchar(64) not null,
    TASK_ID_ varchar(64),
    ACT_INST_ID_ varchar(64),
    NAME_ varchar(255) not null,
    VAR_TYPE_ varchar(64),
    REV_ int(11),
    TIME_ datetime not null,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint(20),
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
);

create table ACT_HI_COMMENT (
    ID_ varchar(64) not null,
    TYPE_ varchar(255),
    TIME_ datetime not null,
    USER_ID_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    ACTION_ varchar(255),
    MESSAGE_ varchar(4000),
    FULL_MSG_ BLOB,
    primary key (ID_)
);

create table ACT_HI_ATTACHMENT (
    ID_ varchar(64) not null,
    REV_ int(11),
    USER_ID_ varchar(255),
    NAME_ varchar(255),
    DESCRIPTION_ varchar(4000),
    TYPE_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    URL_ varchar(4000),
    CONTENT_ID_ varchar(64),
    primary key (ID_)
);

commit;


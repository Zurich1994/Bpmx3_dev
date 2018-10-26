create table ACT_GE_PROPERTY (
    NAME_ nvarchar(64),
    VALUE_ nvarchar(300),
    REV_ int,
    primary key (NAME_)
);


insert into ACT_GE_PROPERTY
values ('schema.version', '5.15.1', 1);


insert into ACT_GE_PROPERTY
values ('schema.history', 'create(5.10)', 1);

insert into ACT_GE_PROPERTY
values ('next.dbid', '1', 1);

create table ACT_GE_BYTEARRAY (
    ID_ nvarchar(64) not null,
    REV_ int,
    NAME_ nvarchar(255),
    DEPLOYMENT_ID_ nvarchar(64),
    BYTES_ image,
    GENERATED_ tinyint
);

create table ACT_RE_DEPLOYMENT (
    ID_ nvarchar(64),
    NAME_ nvarchar(255),
    DEPLOY_TIME_ datetime,
    primary key (ID_)
);

create table ACT_RU_EXECUTION (
    ID_ nvarchar(64) not null,
    REV_ int,
    PROC_INST_ID_ nvarchar(64),
    BUSINESS_KEY_ nvarchar(255),
    PARENT_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    SUPER_EXEC_ nvarchar(64),
    ACT_ID_ nvarchar(255),
    IS_ACTIVE_ tinyint,
    IS_CONCURRENT_ tinyint,
    IS_SCOPE_ tinyint,
    IS_EVENT_SCOPE_ tinyint,
    SUSPENSION_STATE_ int,
    CACHED_ENT_STATE_ int
);

create table ACT_RU_JOB (
    ID_ nvarchar(64) NOT NULL,
	REV_ int,
    TYPE_ nvarchar(255) NOT NULL,
    LOCK_EXP_TIME_ datetime,
    LOCK_OWNER_ nvarchar(255),
    EXCLUSIVE_ tinyint,
    EXECUTION_ID_ nvarchar(64),
    PROCESS_INSTANCE_ID_ nvarchar(64),
    RETRIES_ int,
    EXCEPTION_STACK_ID_ nvarchar(64),
    EXCEPTION_MSG_ nvarchar(2000),
    DUEDATE_ datetime,
    REPEAT_ nvarchar(255),
    HANDLER_TYPE_ nvarchar(255),
    HANDLER_CFG_ nvarchar(2000),
    primary key (ID_)
);

create table ACT_RE_PROCDEF (
    ID_ nvarchar(64),
    REV_ int,
    CATEGORY_ nvarchar(255),
    NAME_ nvarchar(255),
    KEY_ nvarchar(255),
    VERSION_ int,
    DEPLOYMENT_ID_ nvarchar(64),
    RESOURCE_NAME_ nvarchar(2000),
    DGRM_RESOURCE_NAME_ varchar(4000),
    HAS_START_FORM_KEY_ tinyint,
    SUSPENSION_STATE_ int,
    primary key (ID_)
);

create table ACT_RU_TASK (
    ID_ nvarchar(64) not null,
    REV_ int,
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    NAME_ nvarchar(255),
    PARENT_TASK_ID_ nvarchar(64),
    DESCRIPTION_ nvarchar(2000),
    TASK_DEF_KEY_ nvarchar(255),
    OWNER_ nvarchar(64),
    ASSIGNEE_ nvarchar(64),
    DELEGATION_ nvarchar(64),
    PRIORITY_ int,
    CREATE_TIME_ datetime,
    DUE_DATE_ datetime,
    SUSPENSION_STATE_ int
);

create table ACT_RU_IDENTITYLINK (
    ID_ nvarchar(64) not null,
    REV_ int,
    GROUP_ID_ nvarchar(64),
    TYPE_ nvarchar(255),
    USER_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64)
);

create table ACT_RU_VARIABLE (
    ID_ nvarchar(64) not null,
    REV_ int,
    TYPE_ nvarchar(255) not null,
    NAME_ nvarchar(255) not null,
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ numeric(19,10),
    LONG_ numeric(19,0),
    TEXT_ nvarchar(2000),
    TEXT2_ nvarchar(2000)
);

create table ACT_RU_EVENT_SUBSCR (
    ID_ nvarchar(64) not null,
    REV_ int,
    EVENT_TYPE_ nvarchar(255) not null,
    EVENT_NAME_ nvarchar(255),
    EXECUTION_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    ACTIVITY_ID_ nvarchar(64),
    CONFIGURATION_ nvarchar(255),
    CREATED_ datetime not null,
    primary key (ID_)
);
 
--create.history.sql
create table ACT_HI_PROCINST (
    ID_ nvarchar(64) not null,
    PROC_INST_ID_ nvarchar(64) not null,
    BUSINESS_KEY_ nvarchar(255),
    PROC_DEF_ID_ nvarchar(64) not null,
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ numeric(19,0),
    START_USER_ID_ nvarchar(255),
    START_ACT_ID_ nvarchar(255),
    END_ACT_ID_ nvarchar(255),
    SUPER_PROCESS_INSTANCE_ID_ nvarchar(64),
    DELETE_REASON_ nvarchar(2000),
    ISSTART smallint
);

create table ACT_HI_ACTINST (
    ID_ nvarchar(64) not null,
    PROC_DEF_ID_ nvarchar(64) not null,
    PROC_INST_ID_ nvarchar(64) not null,
    EXECUTION_ID_ nvarchar(64) not null,
    ACT_ID_ nvarchar(255) not null,
    ACT_NAME_ nvarchar(255),
    ACT_TYPE_ nvarchar(255) not null,
    ASSIGNEE_ nvarchar(64),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    ISSTART smallint default 0,
    DURATION_ numeric(19,0)
);

create table ACT_HI_TASKINST (
    ID_ nvarchar(64) not null,
    PROC_DEF_ID_ nvarchar(64),
    TASK_DEF_KEY_ nvarchar(255),
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    PARENT_TASK_ID_ nvarchar(64),
    NAME_ nvarchar(255),
    DESCRIPTION_ nvarchar(2000),
    OWNER_ nvarchar(64),
    ASSIGNEE_ nvarchar(64),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ numeric(19,0),
    DELETE_REASON_ nvarchar(2000),
    PRIORITY_ int,
    DUE_DATE_ datetime
);

create table ACT_HI_DETAIL (
    ID_ nvarchar(64) not null,
    TYPE_ nvarchar(255) not null,
    PROC_INST_ID_ nvarchar(64) not null,
    EXECUTION_ID_ nvarchar(64) not null,
    TASK_ID_ nvarchar(64),
    ACT_INST_ID_ nvarchar(64),
    NAME_ nvarchar(255) not null,
    VAR_TYPE_ nvarchar(64),
    REV_ int,
    TIME_ datetime not null,
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ bigint,
    LONG_ numeric(19,0),
    TEXT_ nvarchar(2000),
    TEXT2_ nvarchar(2000),
    primary key (ID_)
);

create table ACT_HI_COMMENT (
    ID_ nvarchar(64) not null,
    TYPE_ NVARCHAR(255),
    TIME_ datetime not null,
    USER_ID_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    ACTION_ nvarchar(255),
    MESSAGE_ nvarchar(2000),
    FULL_MSG_ image,
    primary key (ID_)
);

create table ACT_HI_ATTACHMENT (
    ID_ nvarchar(64) not null,
    REV_ int,
    USER_ID_ nvarchar(255),
    NAME_ nvarchar(255),
    DESCRIPTION_ nvarchar(2000),
    TYPE_ nvarchar(255),
    TASK_ID_ nvarchar(64),
    PROC_INST_ID_ nvarchar(64),
    URL_ nvarchar(2000),
    CONTENT_ID_ nvarchar(64),
    primary key (ID_)
);

IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'ACT_HI_ACTINST', 
'COLUMN', N'ISSTART')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否发起节点'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'ACT_HI_ACTINST'
, @level2type = 'COLUMN', @level2name = N'ISSTART'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否发起节点'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'ACT_HI_ACTINST'
, @level2type = 'COLUMN', @level2name = N'ISSTART'

go
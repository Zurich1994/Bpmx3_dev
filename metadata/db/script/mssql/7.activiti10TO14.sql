alter table ACT_RE_PROCDEF drop constraint ACT_UNIQ_PROCDEF;

alter table ACT_RE_PROCDEF alter column KEY_ nvarchar(255) not null;

alter table ACT_RE_PROCDEF alter column VERSION_ int not null;

alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_);

alter table ACT_RE_DEPLOYMENT add CATEGORY_ nvarchar(255);
    
alter table ACT_RE_PROCDEF
    add DESCRIPTION_ nvarchar(4000);
    
update ACT_RU_TASK set SUSPENSION_STATE_ = 1; 

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCDEF 
    foreign key (PROC_DEF_ID_) 
    references ACT_RE_PROCDEF (ID_);

create table ACT_RE_MODEL (
    ID_ nvarchar(64) not null,
    REV_ int,
    NAME_ nvarchar(255),
    KEY_ nvarchar(255),
    CATEGORY_ nvarchar(255),
    CREATE_TIME_ datetime,
    LAST_UPDATE_TIME_ datetime,
    VERSION_ int,
    META_INFO_ nvarchar(4000),
    DEPLOYMENT_ID_ nvarchar(64),
    EDITOR_SOURCE_VALUE_ID_ NUMERIC(18,0),
    EDITOR_SOURCE_EXTRA_VALUE_ID_ NUMERIC(18,0),
    primary key (ID_)
);

alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_SOURCE 
    foreign key (EDITOR_SOURCE_VALUE_ID_) 
    references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_SOURCE_EXTRA 
    foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_) 
    references ACT_GE_BYTEARRAY (ID_);
    
alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_DEPLOYMENT 
    foreign key (DEPLOYMENT_ID_) 
    references ACT_RE_DEPLOYMENT (ID_);   

delete from ACT_GE_PROPERTY where NAME_ = 'historyLevel';

alter table ACT_RU_JOB
    add PROC_DEF_ID_ nvarchar(64);

update ACT_GE_PROPERTY set VALUE_ = '5.14' where NAME_ = 'schema.version';

--历史表
--10 to 11
create table ACT_HI_VARINST (
    ID_ nvarchar(64) not null,
    PROC_INST_ID_ nvarchar(64),
    EXECUTION_ID_ nvarchar(64),
    TASK_ID_ nvarchar(64),
    NAME_ nvarchar(255) not null,
    VAR_TYPE_ nvarchar(100),
    REV_ int,
    BYTEARRAY_ID_ nvarchar(64),
    DOUBLE_ double precision,
    LONG_ numeric(19,0),
    TEXT_ nvarchar(4000),
    TEXT2_ nvarchar(4000),
    primary key (ID_)
);

create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST(PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST(NAME_, VAR_TYPE_);
  
alter table ACT_HI_ACTINST
	add TASK_ID_ nvarchar(64);

alter table ACT_HI_ACTINST
	add CALL_PROC_INST_ID_ nvarchar(64);	

alter table ACT_HI_DETAIL
	alter column PROC_INST_ID_ nvarchar(64) null;

alter table ACT_HI_DETAIL
	alter column EXECUTION_ID_ nvarchar(64) null;
	
create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST(PROC_INST_ID_, ACT_ID_);

--11 to 12
alter table ACT_HI_TASKINST
  add CLAIM_TIME_ datetime;

alter table ACT_HI_TASKINST
  add FORM_KEY_ nvarchar(255);
  
alter table ACT_RU_IDENTITYLINK
  add PROC_INST_ID_ NUMERIC(18,0);
  
alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_IDL_PROCINST
    foreign key (PROC_INST_ID_) 
    references ACT_RU_EXECUTION (ID_);     
  
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST(EXECUTION_ID_, ACT_ID_);  



--12 to 13
create table ACT_HI_IDENTITYLINK (
    ID_ NUMERIC(18,0),
    GROUP_ID_ NUMERIC(18,0),
    TYPE_ nvarchar(255),
    USER_ID_ NUMERIC(18,0),
    TASK_ID_ NUMERIC(18,0),
    PROC_INST_ID_ NUMERIC(18,0),
    primary key (ID_)
);

create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK(USER_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK(TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK(PROC_INST_ID_);
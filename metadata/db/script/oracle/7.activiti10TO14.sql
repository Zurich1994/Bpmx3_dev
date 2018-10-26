alter table ACT_RE_PROCDEF
    modify KEY_ not null;

alter table ACT_RE_PROCDEF
    modify VERSION_ not null;
    
alter table ACT_RE_DEPLOYMENT 
    add CATEGORY_ NVARCHAR2(255);
    
alter table ACT_RE_PROCDEF
    add DESCRIPTION_ NVARCHAR2(2000);
    
alter table ACT_RU_TASK
    add SUSPENSION_STATE_ INTEGER;
    
update ACT_RU_TASK set SUSPENSION_STATE_ = 1; 

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCDEF 
    foreign key (PROC_DEF_ID_) 
    references ACT_RE_PROCDEF (ID_);    

create table ACT_RE_MODEL (
    ID_ NVARCHAR2(64) not null,
    REV_ INTEGER,
    NAME_ NVARCHAR2(255),
    KEY_ NVARCHAR2(255),
    CATEGORY_ NVARCHAR2(255),
    CREATE_TIME_ TIMESTAMP(6),
    LAST_UPDATE_TIME_ TIMESTAMP(6),
    VERSION_ INTEGER,
    META_INFO_ NVARCHAR2(2000),
    DEPLOYMENT_ID_ NVARCHAR2(64),
    EDITOR_SOURCE_VALUE_ID_ NUMBER(18),
    EDITOR_SOURCE_EXTRA_VALUE_ID_ NUMBER(18),
    primary key (ID_)
);

create index ACT_IDX_MODEL_SOURCE on ACT_RE_MODEL(EDITOR_SOURCE_VALUE_ID_);
alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_SOURCE 
    foreign key (EDITOR_SOURCE_VALUE_ID_) 
    references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_MODEL_SOURCE_EXTRA on ACT_RE_MODEL(EDITOR_SOURCE_EXTRA_VALUE_ID_);
alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_SOURCE_EXTRA 
    foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_) 
    references ACT_GE_BYTEARRAY (ID_);
    
create index ACT_IDX_MODEL_DEPLOYMENT on ACT_RE_MODEL(DEPLOYMENT_ID_);    
alter table ACT_RE_MODEL 
    add constraint ACT_FK_MODEL_DEPLOYMENT 
    foreign key (DEPLOYMENT_ID_) 
    references ACT_RE_DEPLOYMENT (ID_);        

delete from ACT_GE_PROPERTY where NAME_ = 'historyLevel';

alter table ACT_RU_JOB
    add PROC_DEF_ID_ NVARCHAR2(64);

update ACT_GE_PROPERTY set VALUE_ = '5.14' where NAME_ = 'schema.version';

--流程历史修改
create table ACT_HI_VARINST (
    ID_ NVARCHAR2(64) not null,
    PROC_INST_ID_ NVARCHAR2(64),
    EXECUTION_ID_ NVARCHAR2(64),
    TASK_ID_ NVARCHAR2(64),
    NAME_ NVARCHAR2(255) not null,
    VAR_TYPE_ NVARCHAR2(100),
    REV_ INTEGER,
    BYTEARRAY_ID_ NVARCHAR2(64),
    DOUBLE_ NUMBER(*,10),
    LONG_ NUMBER(19,0),
    TEXT_ NVARCHAR2(2000),
    TEXT2_ NVARCHAR2(2000),
    primary key (ID_)
);

create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST(PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST(NAME_, VAR_TYPE_);

alter table ACT_HI_ACTINST
add TASK_ID_ NVARCHAR2(64);

alter table ACT_HI_ACTINST
add CALL_PROC_INST_ID_ NVARCHAR2(64);

alter table ACT_HI_DETAIL
	modify PROC_INST_ID_ NVARCHAR2(64) null;

alter table ACT_HI_DETAIL
	modify EXECUTION_ID_ NVARCHAR2(64) null;
	
create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST(PROC_INST_ID_, ACT_ID_);

--11TO12
alter table ACT_HI_TASKINST
  add CLAIM_TIME_ TIMESTAMP(6);

alter table ACT_HI_TASKINST
  add FORM_KEY_ NVARCHAR2(255);
  
alter table ACT_RU_IDENTITYLINK
  add PROC_INST_ID_ NUMBER(18);  
  
create index ACT_IDX_IDL_PROCINST on ACT_RU_IDENTITYLINK(PROC_INST_ID_);
alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_IDL_PROCINST
    foreign key (PROC_INST_ID_) 
    references ACT_RU_EXECUTION (ID_);     
  
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST(EXECUTION_ID_, ACT_ID_);  

--12TO13
create table ACT_HI_IDENTITYLINK (
    ID_ NUMBER(18),
    GROUP_ID_ NUMBER(18),
    TYPE_ NVARCHAR2(255),
    USER_ID_ NUMBER(18),
    TASK_ID_ NUMBER(18),
    PROC_INST_ID_ NUMBER(18),
    primary key (ID_)
);

create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK(USER_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK(TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK(PROC_INST_ID_);

  



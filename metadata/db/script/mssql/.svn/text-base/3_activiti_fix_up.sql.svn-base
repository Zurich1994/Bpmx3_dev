--ACT_GE_BYTEARRAY
ALTER TABLE ACT_GE_BYTEARRAY alter column ID_ numeric(18) not null ;
alter table ACT_GE_BYTEARRAY add constraint PK_ACT_GE_BYTEARRAY primary key (ID_);

--流程任务 
ALTER TABLE ACT_RU_TASK alter column ID_ numeric(18) not null;
alter table ACT_RU_TASK add constraint PK_ACT_RU_TASK primary key (ID_);
ALTER TABLE ACT_RU_TASK alter column EXECUTION_ID_ numeric(18);
ALTER TABLE ACT_RU_TASK alter column PROC_INST_ID_ numeric(18);
ALTER TABLE ACT_RU_TASK alter column PARENT_TASK_ID_ numeric(18);
ALTER TABLE ACT_RU_TASK alter column OWNER_ numeric(18);
ALTER TABLE ACT_RU_TASK alter column ASSIGNEE_ numeric(18);

--流程变量表
ALTER TABLE ACT_RU_VARIABLE alter column ID_ numeric(18) not null;
alter table ACT_RU_VARIABLE add constraint PK_ACT_RU_VARIABLE primary key (ID_);
ALTER TABLE ACT_RU_VARIABLE alter column EXECUTION_ID_ numeric(18);
ALTER TABLE ACT_RU_VARIABLE alter column PROC_INST_ID_ numeric(18);
ALTER TABLE ACT_RU_VARIABLE alter column TASK_ID_ numeric(18);
ALTER TABLE ACT_RU_VARIABLE alter column BYTEARRAY_ID_ numeric(18);

--流程任务候选人
ALTER TABLE ACT_RU_IDENTITYLINK alter column ID_ numeric(18) not null;
alter table ACT_RU_IDENTITYLINK add constraint PK_ACT_RU_IDENTITYLINK primary key (ID_);
ALTER TABLE ACT_RU_IDENTITYLINK alter column GROUP_ID_ numeric(18);
ALTER TABLE ACT_RU_IDENTITYLINK alter column USER_ID_ numeric(18);
ALTER TABLE ACT_RU_IDENTITYLINK alter column TASK_ID_ numeric(18);
--excution
ALTER TABLE ACT_RU_EXECUTION alter column ID_ numeric(18) not null;
alter table ACT_RU_EXECUTION add constraint PK_ACT_RU_EXECUTION primary key (ID_);
ALTER TABLE ACT_RU_EXECUTION alter column PROC_INST_ID_ numeric(18);
ALTER TABLE ACT_RU_EXECUTION alter column PARENT_ID_ numeric(18);
ALTER TABLE ACT_RU_EXECUTION alter column SUPER_EXEC_ numeric(18);
ALTER TABLE ACT_RU_EVENT_SUBSCR alter column EXECUTION_ID_ numeric(18);
ALTER TABLE ACT_RU_JOB alter column EXCEPTION_STACK_ID_ numeric(18);
--ACT_RE_DEPLOYMENT
--ALTER TABLE ACT_RE_DEPLOYMENT alter column ID_ numeric(18);
--流程定义
ALTER TABLE ACT_RE_PROCDEF alter column DEPLOYMENT_ID_ numeric(18);
--历史任务实例
ALTER TABLE ACT_HI_TASKINST alter column ID_ numeric(18) not null;
alter table ACT_HI_TASKINST add constraint PK_ACT_HI_TASKINST primary key (ID_);
ALTER TABLE ACT_HI_TASKINST alter column EXECUTION_ID_ numeric(18);
ALTER TABLE ACT_HI_TASKINST alter column PROC_INST_ID_ numeric(18);
ALTER TABLE ACT_HI_TASKINST alter column PARENT_TASK_ID_ numeric(18);
ALTER TABLE ACT_HI_TASKINST alter column OWNER_ numeric(18);
ALTER TABLE ACT_HI_TASKINST alter column ASSIGNEE_ numeric(18);

--历史流程实例 ACT_HI_PROCINST
ALTER TABLE ACT_HI_PROCINST alter column ID_ numeric(18) not null;
alter table ACT_HI_PROCINST add constraint PK_ACT_HI_PROCINST primary key (ID_);
ALTER TABLE ACT_HI_PROCINST alter column PROC_INST_ID_ numeric(18) not null;
alter table ACT_HI_PROCINST add constraint UQ_ACT_HI_PROCINST unique (PROC_INST_ID_);
ALTER TABLE ACT_HI_PROCINST alter column START_USER_ID_ numeric(18);
ALTER TABLE ACT_HI_PROCINST alter column SUPER_PROCESS_INSTANCE_ID_ numeric(18);
--历史活动节点实例
ALTER TABLE ACT_HI_ACTINST alter column ID_ numeric(18) not null;
alter table ACT_HI_ACTINST add constraint PK_ACT_HI_ACTINST primary key (ID_);
ALTER TABLE ACT_HI_ACTINST alter column PROC_INST_ID_ numeric(18);
ALTER TABLE ACT_HI_ACTINST alter column ASSIGNEE_ numeric(18);
ALTER TABLE ACT_HI_ACTINST alter column EXECUTION_ID_ numeric(18);



create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION(BUSINESS_KEY_);
create index ACT_IDX_TASK_CREATE on ACT_RU_TASK(CREATE_TIME_);
create index ACT_IDX_IDENT_LNK_USER on ACT_RU_IDENTITYLINK(USER_ID_);
create index ACT_IDX_IDENT_LNK_GROUP on ACT_RU_IDENTITYLINK(GROUP_ID_);
create index ACT_IDX_EVENT_SUBSCR_CONFIG_ on ACT_RU_EVENT_SUBSCR(CONFIGURATION_);
create index ACT_IDX_VARIABLE_TASK_ID on ACT_RU_VARIABLE(TASK_ID_);

create index ACT_IDX_BYTEAR_DEPL on ACT_GE_BYTEARRAY(DEPLOYMENT_ID_);
alter table ACT_GE_BYTEARRAY
    add constraint ACT_FK_BYTEARR_DEPL
    foreign key (DEPLOYMENT_ID_) 
    references ACT_RE_DEPLOYMENT (ID_);

alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_);
    
create index ACT_IDX_EXE_PROCINST on ACT_RU_EXECUTION(PROC_INST_ID_);
alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCINST
    foreign key (PROC_INST_ID_) 
    references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_EXE_PARENT on ACT_RU_EXECUTION(PARENT_ID_);
alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PARENT
    foreign key (PARENT_ID_) 
    references ACT_RU_EXECUTION (ID_);
    
create index ACT_IDX_EXE_SUPER on ACT_RU_EXECUTION(SUPER_EXEC_);
alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_SUPER
    foreign key (SUPER_EXEC_) 
    references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_TSKASS_TASK on ACT_RU_IDENTITYLINK(TASK_ID_);
alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_TSKASS_TASK
    foreign key (TASK_ID_) 
    references ACT_RU_TASK (ID_);

create index ACT_IDX_ATHRZ_PROCEDEF  on ACT_RU_IDENTITYLINK(PROC_DEF_ID_);
alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_ATHRZ_PROCEDEF
    foreign key (PROC_DEF_ID_) 
    references ACT_RE_PROCDEF (ID_);

create index ACT_IDX_TASK_EXEC on ACT_RU_TASK(EXECUTION_ID_);
alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_EXE
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION (ID_);
    
create index ACT_IDX_TASK_PROCINST on ACT_RU_TASK(PROC_INST_ID_);
alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_PROCINST
    foreign key (PROC_INST_ID_)
    references ACT_RU_EXECUTION (ID_);
    
create index ACT_IDX_TASK_PROCDEF on ACT_RU_TASK(PROC_DEF_ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCDEF
  foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);
  
create index ACT_IDX_VAR_EXE on ACT_RU_VARIABLE(EXECUTION_ID_);
alter table ACT_RU_VARIABLE 
    add constraint ACT_FK_VAR_EXE
    foreign key (EXECUTION_ID_) 
    references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_VAR_PROCINST on ACT_RU_VARIABLE(PROC_INST_ID_);
alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_PROCINST
    foreign key (PROC_INST_ID_)
    references ACT_RU_EXECUTION(ID_);

create index ACT_IDX_VAR_BYTEARRAY on ACT_RU_VARIABLE(BYTEARRAY_ID_);
alter table ACT_RU_VARIABLE 
    add constraint ACT_FK_VAR_BYTEARRAY 
    foreign key (BYTEARRAY_ID_) 
    references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_JOB_EXCEPTION on ACT_RU_JOB(EXCEPTION_STACK_ID_);
alter table ACT_RU_JOB 
    add constraint ACT_FK_JOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_) 
    references ACT_GE_BYTEARRAY (ID_);
    
create index ACT_IDX_EVENT_SUBSCR on ACT_RU_EVENT_SUBSCR(EXECUTION_ID_);
alter table ACT_RU_EVENT_SUBSCR
    add constraint ACT_FK_EVENT_EXEC
    foreign key (EXECUTION_ID_)
    references ACT_RU_EXECUTION(ID_);

create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST(END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST(BUSINESS_KEY_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST(START_TIME_);
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST(END_TIME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL(PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL(ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL(TIME_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL(NAME_);
create index ACT_IDX_HI_DETAIL_TASK_ID on ACT_HI_DETAIL(TASK_ID_);

go

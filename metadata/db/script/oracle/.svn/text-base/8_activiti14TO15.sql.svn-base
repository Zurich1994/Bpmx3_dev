-- 14TO15 engine
alter table ACT_RU_TASK 
    add CATEGORY_ NVARCHAR2(255);
    
drop index ACT_UNIQ_RU_BUS_KEY;  

alter table ACT_RE_DEPLOYMENT 
    add TENANT_ID_ NVARCHAR2(255) default '';  
    
alter table ACT_RE_PROCDEF 
    add TENANT_ID_ NVARCHAR2(255) default ''; 
    
alter table ACT_RU_EXECUTION
    add TENANT_ID_ NVARCHAR2(255) default '';  
    
alter table ACT_RU_TASK
    add TENANT_ID_ NVARCHAR2(255) default '';
    
alter table ACT_RU_JOB
    add TENANT_ID_ NVARCHAR2(255) default ''; 
    
alter table ACT_RE_MODEL
    add TENANT_ID_ NVARCHAR2(255) default ''; 
    
alter table ACT_RU_EVENT_SUBSCR
   add TENANT_ID_ NVARCHAR2(255) default '';  
   
alter table ACT_RU_EVENT_SUBSCR
   add PROC_DEF_ID_ NVARCHAR2(64);   
    
alter table ACT_RE_PROCDEF
    drop constraint ACT_UNIQ_PROCDEF;
    
alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_, TENANT_ID_);

-- 14TO15 history   
alter table ACT_HI_TASKINST
    add CATEGORY_ NVARCHAR2(255);
    
drop index ACT_UNIQ_HI_BUS_KEY;    
    
alter table ACT_HI_VARINST
    add CREATE_TIME_ TIMESTAMP(6); 
    
alter table ACT_HI_VARINST
    add LAST_UPDATED_TIME_ TIMESTAMP(6);   
    
alter table ACT_HI_PROCINST
    add TENANT_ID_ NVARCHAR2(255) default ''; 
       
alter table ACT_HI_ACTINST
    add TENANT_ID_ NVARCHAR2(255) default ''; 
    
alter table ACT_HI_TASKINST
    add TENANT_ID_ NVARCHAR2(255) default '';    
    
alter table ACT_HI_ACTINST
    modify ASSIGNEE_ NVARCHAR2(255);
    
-- 14TO15.1 engine
update ACT_GE_PROPERTY set VALUE_ = '5.15.1' where NAME_ = 'schema.version';

commit;

alter table ACT_RU_EXECUTION
  drop constraint ACT_FK_EXE_PARENT;
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PARENT foreign key (PARENT_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
alter table ACT_RU_EXECUTION
  drop constraint ACT_FK_EXE_PROCINST;
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
alter table ACT_RU_EXECUTION
  drop constraint ACT_FK_EXE_SUPER;
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_SUPER foreign key (SUPER_EXEC_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
  
  
  alter table ACT_RU_TASK
  drop constraint ACT_FK_TASK_EXE;
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
alter table ACT_RU_TASK
  drop constraint ACT_FK_TASK_PROCINST;
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
  
  
  alter table ACT_RU_VARIABLE
  drop constraint ACT_FK_VAR_BYTEARRAY;
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_BYTEARRAY foreign key (BYTEARRAY_ID_)
  references ACT_GE_BYTEARRAY (ID_) on delete cascade;
alter table ACT_RU_VARIABLE
  drop constraint ACT_FK_VAR_EXE;
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
alter table ACT_RU_VARIABLE
  drop constraint ACT_FK_VAR_PROCINST;
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
  
  
  alter table ACT_RU_IDENTITYLINK
  drop constraint ACT_FK_IDL_PROCINST;
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_IDL_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;
alter table ACT_RU_IDENTITYLINK
  drop constraint ACT_FK_TSKASS_TASK;
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_TSKASS_TASK foreign key (TASK_ID_)
  references ACT_RU_TASK (ID_) on delete cascade;
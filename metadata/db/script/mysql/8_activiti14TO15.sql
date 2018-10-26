-- mysql55 
-- 14To15 engine
alter table ACT_RU_TASK 
    add CATEGORY_ varchar(255);
    
alter table ACT_RU_EXECUTION drop foreign key ACT_FK_EXE_PROCDEF;  	

alter table ACT_RU_EXECUTION drop index ACT_UNIQ_RU_BUS_KEY;  

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCDEF 
    foreign key (PROC_DEF_ID_) 
    references ACT_RE_PROCDEF (ID_);
    
alter table ACT_RE_DEPLOYMENT 
    add TENANT_ID_ varchar(255) default ''; 
    
alter table ACT_RE_PROCDEF 
    add TENANT_ID_ varchar(255) default '';     
    
alter table ACT_RU_EXECUTION
    add TENANT_ID_ varchar(255) default '';    
    
alter table ACT_RU_TASK
    add TENANT_ID_ varchar(255) default '';  
    
alter table ACT_RU_JOB
    add TENANT_ID_ varchar(255) default '';   
    
alter table ACT_RE_MODEL
    add TENANT_ID_ varchar(255) default '';  
    
alter table ACT_RU_EVENT_SUBSCR
   add TENANT_ID_ varchar(255) default ''; 
   
alter table ACT_RU_EVENT_SUBSCR
   add PROC_DEF_ID_ varchar(64);           
    
alter table ACT_RE_PROCDEF
    drop index ACT_UNIQ_PROCDEF;
    
alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_);
    
-- 14To15 history
alter table ACT_HI_TASKINST
    add CATEGORY_ varchar(255);
    
-- alter table ACT_HI_PROCINST drop index ACT_UNIQ_HI_BUS_KEY;   

alter table ACT_HI_VARINST
    add CREATE_TIME_ datetime; 
    
alter table ACT_HI_VARINST
    add LAST_UPDATED_TIME_ datetime; 
    
alter table ACT_HI_PROCINST
    add TENANT_ID_ varchar(255) default ''; 
       
alter table ACT_HI_ACTINST
    add TENANT_ID_ varchar(255) default ''; 
    
alter table ACT_HI_TASKINST
    add TENANT_ID_ varchar(255) default '';
    
alter table ACT_HI_ACTINST
    modify ASSIGNEE_ varchar(255);

-- 15To15.1 engine
update ACT_GE_PROPERTY set VALUE_ = '5.15.1' where NAME_ = 'schema.version';
commit;
  
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PARENT foreign key (PARENT_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;

alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_) on delete cascade;


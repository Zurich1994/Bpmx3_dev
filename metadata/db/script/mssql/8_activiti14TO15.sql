-- 14To15 engine
alter table ACT_RU_TASK 
    add CATEGORY_ nvarchar(255); 

alter table ACT_RE_DEPLOYMENT 
    add TENANT_ID_ nvarchar(255) default '';  
    
alter table ACT_RE_PROCDEF 
    add TENANT_ID_ nvarchar(255) default ''; 
    
alter table ACT_RU_EXECUTION 
    add TENANT_ID_ nvarchar(255) default '';
    
alter table ACT_RU_TASK 
    add TENANT_ID_ nvarchar(255) default '';      
    
alter table ACT_RU_JOB
    add TENANT_ID_ nvarchar(255) default ''; 
    
alter table ACT_RE_MODEL
    add TENANT_ID_ nvarchar(255) default ''; 
    
alter table ACT_RU_EVENT_SUBSCR
    add TENANT_ID_ nvarchar(255) default ''; 
    
alter table ACT_RU_EVENT_SUBSCR
   add PROC_DEF_ID_ nvarchar(64);            
    
alter table ACT_RE_PROCDEF
    drop constraint ACT_UNIQ_PROCDEF;
    
alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_);  
    
-- 14To15 history
alter table ACT_HI_TASKINST
    add CATEGORY_ nvarchar(255); 

alter table ACT_HI_VARINST
    add CREATE_TIME_ datetime; 
    
alter table ACT_HI_VARINST
    add LAST_UPDATED_TIME_ datetime; 
    
alter table ACT_HI_PROCINST
    add TENANT_ID_ nvarchar(255) default ''; 
       
alter table ACT_HI_ACTINST
    add TENANT_ID_ nvarchar(255) default ''; 
    
alter table ACT_HI_TASKINST
    add TENANT_ID_ nvarchar(255) default '';       
    
alter table ACT_HI_ACTINST
    alter column ASSIGNEE_ nvarchar(255);

-- 15To15.1 engine
update ACT_GE_PROPERTY set VALUE_ = '5.15.1' where NAME_ = 'schema.version';

-- 流程状态
CREATE INDEX IDX_BPMPROSTATUS_ACTINSTID ON BPM_PRO_STATUS (ACTINSTID);
-- 流程意见
CREATE INDEX IDX_TASKOPTION_INSTANCE_USER ON BPM_TASK_OPINION (ACTINSTID,EXEUSERID);
CREATE INDEX IDX_TASKOPTION_INSTANCE_INSTID ON BPM_TASK_OPINION (ACTINSTID);
CREATE INDEX IDX_TASKOPTION_INSTANCE_TASK ON BPM_TASK_OPINION (TASKID);
-- 流程堆栈
CREATE INDEX IDX_BPMSTACKINST_DEPTH ON BPM_EXE_STACK (ACTINSTID);
CREATE INDEX IDX_BPMSTACK_PARENTID ON BPM_EXE_STACK (PARENTID);
-- 表单运行时
CREATE INDEX IDX_BPMFORMRUN_INSTANCEID ON BPM_FORM_RUN (ACTINSTANCEID);
CREATE INDEX IDX_BPMFORMRUN_INSTANCENODEID ON BPM_FORM_RUN (ACTINSTANCEID,ACTNODEID);
-- 会签数据
CREATE INDEX IDX_BPMTKSIGNDATA_ACTDEFNODEID ON BPM_TKSIGN_DATA  (ACTINSTID,NODEID);
CREATE INDEX IDX_BPMTKSIGNDATA_TASKID ON BPM_TKSIGN_DATA  (TASKID);
-- 催办任务状态
CREATE INDEX IDX_REMINDERSTATE_TASKUSERID ON BPM_TASK_REMINDERSTATE (TASKID,USERID);
CREATE INDEX IDX_REMINDERSTATE_TASKID ON BPM_TASK_REMINDERSTATE (TASKID);
-- 流程实例扩展
CREATE INDEX IDX_PRORUN_INSTANCEID ON BPM_PRO_RUN (ACTINSTID);
CREATE INDEX IDX_PROCESSRUN_CREATORID ON BPM_PRO_RUN (CREATORID);
--流程实例扩展历史
CREATE INDEX IDX_PRORUN_HIS_INSTANCEID ON BPM_PRO_RUN_HIS (ACTINSTID);
CREATE INDEX IDX_PROCESSRUN_HIS_CREATORID ON BPM_PRO_RUN_HIS (CREATORID);
-- 流程运行日志
CREATE INDEX IDX_RUNLOG_USERID ON BPM_RUN_LOG (USERID);
CREATE INDEX IDX_RUNLOG_RUNID ON BPM_RUN_LOG (RUNID);
-- 邮件设定
CREATE INDEX IDX_MAILUSERSETTING ON OUT_MAIL_USER_SETING (USERID);
-- 邮件联系人
CREATE INDEX IDX_MAILLINKMAN ON OUT_MAIL_LINKMAN (USERID);
-- 邮件
CREATE INDEX IDX_OUTMAIL_USERID ON OUT_MAIL (USERID);
-- 发送消息
CREATE INDEX IDX_MSGSEND_USERID ON SYS_MSG_SEND (USERID);
-- 消息接收人
CREATE INDEX IDX_MSGRECEIVER_MESSAGEID ON SYS_MSG_RECEIVER (MESSAGEID);
CREATE INDEX IDX_MSGRECEIVER_RECEIVERID ON SYS_MSG_RECEIVER (RECEIVERID);
-- 消息读取
CREATE INDEX IDX_MSGREAD_RECEIVERID ON SYS_MSG_READ (RECEIVERID);
CREATE INDEX IDX_MSGREAD_MESSAGEID ON SYS_MSG_READ (MESSAGEID);
-- 消息回复
CREATE INDEX IDX_MSG_REPLY_MESSAGEID ON SYS_MSG_REPLY (MESSAGEID);
CREATE INDEX IDX_MSG_REPLY_REPLYID ON SYS_MSG_REPLY (REPLYID);

-- 角色和资源的映射
CREATE INDEX IDX_ROLERES_RESID ON SYS_ROLE_RES (RESID);
CREATE INDEX IDX_ROLERES_ROLEID ON SYS_ROLE_RES (ROLEID);
-- 资源
CREATE INDEX IDX_SYSRES_SYSTEMID ON SYS_RES  (SYSTEMID);
CREATE INDEX IDX_SYSRES_PARENTID ON SYS_RES  (PARENTID);
CREATE INDEX IDX_RESURL_RESID ON SYS_RESURL  (RESID);
-- 用户参数
CREATE INDEX IDX_USERPARAM_USERID ON SYS_USER_PARAM  (USERID);
CREATE INDEX IDX_USERPARAM_PARAMID ON SYS_USER_PARAM  (PARAMID);
-- 下属
CREATE INDEX IDX_USERUNDER_USERID ON SYS_USER_UNDER  (USERID);
CREATE INDEX IDX_USERUNDER_UNDERUSERID ON SYS_USER_UNDER  (UNDERUSERID);
--组织
CREATE INDEX IDX_SYSORG_ORGSUPID ON SYS_ORG (ORGSUPID);
-- 组织和角色的映射
CREATE INDEX IDX_ORGROLE_ORGID ON SYS_ORG_ROLE  (ORGID);
CREATE INDEX IDX_ORGROLE_ROLE ON SYS_ORG_ROLE  (ROLEID);
-- 表单字段
CREATE INDEX IDX_FORMFIELD_TABLEID ON BPM_FORM_FIELD  (TABLEID);
-- 组织可授权角色
CREATE INDEX IDX_ORGROLEMANAGE_ORGID ON SYS_ORG_ROLEMANAGE  (ORGID);
CREATE INDEX IDX_ORGROLEMANAGE_ROLEID ON SYS_ORG_ROLEMANAGE  (ROLEID);
-- 表单权限
CREATE INDEX IDX_FORMRIGHTS_FORMDEFID ON BPM_FORM_RIGHTS  (FORMKEY);
--代理
CREATE INDEX IDX_AGENTCON_SETTINGID ON BPM_AGENT_CONDITION (SETTINGID);
CREATE INDEX IDX_AGENTDEF_SETTINGID ON BPM_AGENT_DEF (SETTINGID);
CREATE INDEX IDX_AGENTSETTING_AUTHID ON BPM_AGENT_SETTING (AUTHID);
CREATE INDEX IDX_AGENTSETTING_AGENTID ON BPM_AGENT_SETTING(AGENTID);

--业务中间表
CREATE INDEX IDX_BUSLINK_ORGID ON BPM_BUS_LINK (BUS_ORG_ID);
CREATE INDEX IDX_BUSLINK_PK ON BPM_BUS_LINK (BUS_PK);
CREATE INDEX IDX_BUSLINK_PKSTR ON BPM_BUS_LINK (BUS_PKSTR);
CREATE INDEX IDX_BUSLINK_STARTID ON BPM_BUS_LINK (BUS_CREATOR_ID);
--抄送转发
CREATE INDEX IDX_PRO_CPTO_RUNID ON BPM_PRO_CPTO (RUN_ID);
CREATE INDEX IDX_PRO_CPTO_UID ON BPM_PRO_CPTO (CC_UID);
--转办代理
CREATE INDEX IDX_TASKEXE_INSTID ON BPM_TASK_EXE (ACT_INST_ID );
CREATE INDEX IDX_TASKEXE_RUNID ON BPM_TASK_EXE ( RUNID );
--任务是否已读
CREATE INDEX IDX_TASKREAD_INSTTASK ON BPM_TASK_READ (ACTINSTID , TASKID );
CREATE INDEX IDX_TASKREAD_TASKUSER ON BPM_TASK_READ ( TASKID , USERID );
--用户角色关联。
CREATE INDEX IDX_USERROLE_ROLE ON SYS_USER_ROLE (ROLEID);
CREATE INDEX IDX_USERROLE_USER ON SYS_USER_ROLE (USERID);
--流程流转
CREATE INDEX IDX_TRANSTO_TASKID ON BPM_PRO_TRANSTO (TASKID);
CREATE INDEX IDX_TRANSTO_INSTID ON BPM_PRO_TRANSTO (ACTINSTID);
--用户帐号
CREATE INDEX IDX_SYSUSER_ACCOUNT ON SYS_USER (ACCOUNT);
--任务通知人
CREATE INDEX IDX_COMMURECEIVER_OPINIONID ON BPM_COMMU_RECEIVER(OPINIONID);
CREATE INDEX IDX_COMMURECEIVER_TASKID ON BPM_COMMU_RECEIVER(TASKID);
--流程定义
CREATE INDEX IDX_BPMDEF_ACTDEFID ON BPM_DEFINITION( ACTDEFID);
CREATE INDEX IDX_BPMDEF_DEFKEY ON BPM_DEFINITION( ACTDEFKEY);
--监控项目
CREATE INDEX IDX_MONGROUPITEM_GROUP ON  BPM_MON_GROUPITEM(GROUPID);
CREATE INDEX IDX_MONGROUPITEM_FLOWKEY ON  BPM_MON_GROUPITEM(FLOWKEY);

--BPM_MON_ORGROLE
CREATE INDEX IDX_MONORGROLE_GROUPID ON  BPM_MON_ORGROLE(GROUPID);
CREATE INDEX IDX_MONORGROLE_ROLEID ON  BPM_MON_ORGROLE(ROLEID);
CREATE INDEX IDX_MONORGROLE_ORGID ON  BPM_MON_ORGROLE(ORGID);
--BPM_NODE_BTN
CREATE INDEX IDX_NODEBTN_DEFID ON BPM_NODE_BTN(DEFID);
--BPM_NODE_SCRIPT
CREATE INDEX IDX_NODESCRIPT_DEFID ON BPM_NODE_SCRIPT(ACTDEFID);
--节点配置
CREATE INDEX IDX_NODESET_DEFID ON BPM_NODE_SET (DEFID);
--人员
CREATE INDEX IDX_NODEUSER_CONDITIONID ON BPM_NODE_USER(CONDITIONID);
--节点人员条件
CREATE INDEX IDX_USERCONDITION_ACTDEFID ON BPM_USER_CONDITION(ACTDEFID);
CREATE INDEX IDX_USERCONDITION_SETID ON BPM_USER_CONDITION(SETID);
--BPM_TASK_FORK
CREATE INDEX IDX_TASKFORK_ACTINSTID ON BPM_TASK_FORK(ACTINSTID);
--印章权限
CREATE INDEX IDX_SEALRIGHT_SEALID ON SYS_SEAL_RIGHT(SEALID);
CREATE INDEX IDX_SEALRIGHT_RIGHTID ON SYS_SEAL_RIGHT(RIGHTID);
--岗位和个人权限
CREATE INDEX IDX_USERPOS_USERID ON SYS_USER_POS (USERID);
CREATE INDEX IDX_USERPOS_POSID ON SYS_USER_POS (POSID);

--工时设定
CREATE INDEX IDX_WORKTIME_SETTINGID ON SYS_WORKTIME(SETTINGID);

--SYS_ORG_PARAM
CREATE INDEX IDX_ORGPARAM_ORGID ON SYS_ORG_PARAM(ORGID);
CREATE INDEX IDX_ORGPARAM_PARAMID ON SYS_ORG_PARAM(PARAMID);

-- DROP INDEXES 
DROP INDEX ACT_IDX_TASK_PROCDEF;
DROP INDEX ACT_IDX_HI_ACT_INST_END;
DROP INDEX ACT_IDX_HI_ACT_INST_START;
CREATE INDEX IDX_ACTTASK_USERID ON ACT_RU_TASK(ASSIGNEE_);
CREATE INDEX IDX_HIACTINST_EXEID ON ACT_HI_ACTINST (EXECUTION_ID_);
CREATE INDEX IDX_HIACTINST_ASSIGNEE ON ACT_HI_ACTINST (ASSIGNEE_);
CREATE INDEX IDX_BPMDEF_KEY ON BPM_DEFINITION (DEFKEY);



CREATE INDEX IDX_PROCESSRUN_BUSINESSKEY ON BPM_PRO_RUN (BUSINESSKEY);
CREATE INDEX IDX_PROCESSRUN_HIS_BUSINESSKEY ON BPM_PRO_RUN_HIS (BUSINESSKEY);

CREATE UNIQUE INDEX IDX_SYS_POS_CODE ON SYS_POS(POSCODE);

CREATE UNIQUE INDEX IDX_SYS_JOB_CODE ON SYS_JOB(JOBCODE);

CREATE UNIQUE INDEX IDX_SYS_PARAM_KEY ON SYS_PARAM(PARAMKEY);


CREATE INDEX  NODEID_FLOWKEY_INDEX ON  BPM_NEWFLOW_TRIGGER (FLOWKEY, NODEID);



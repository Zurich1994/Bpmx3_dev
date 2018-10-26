/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/7/14 18:05:12                          */
/*==============================================================*/


/*==============================================================*/
/* Table: BPM_AGENT_CONDITION                                   */
/*==============================================================*/
CREATE TABLE BPM_AGENT_CONDITION
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   SETTINGID            BIGINT   COMMENT '流程设定ID',
   con        			VARCHAR(1000) COMMENT '条件',
   MEMO                 VARCHAR(200) COMMENT '备注',
   AGENTID              BIGINT   COMMENT '代理人ID',
   AGENT                VARCHAR(100) COMMENT '代理人'
);

ALTER TABLE BPM_AGENT_CONDITION COMMENT '条件代理的配置';

ALTER TABLE BPM_AGENT_CONDITION
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_AGENT_DEF                                         */
/*==============================================================*/
CREATE TABLE BPM_AGENT_DEF
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   SETTINGID            BIGINT   COMMENT '代理设定ID',
   FLOWKEY              VARCHAR(50) COMMENT '流程KEY',
   FLOWNAME             VARCHAR(200) COMMENT '流程名称'
);

ALTER TABLE BPM_AGENT_DEF COMMENT '代理的流程列表';

ALTER TABLE BPM_AGENT_DEF
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_AGENT_SETTING                                     */
/*==============================================================*/
CREATE TABLE BPM_AGENT_SETTING
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   AUTHID               BIGINT   COMMENT '授权人ID',
   AUTHNAME             VARCHAR(100) COMMENT '授权人',
   CREATETIME           TIMESTAMP COMMENT '授权时间',
   STARTDATE            TIMESTAMP COMMENT '开始时间',
   ENDDATE              TIMESTAMP null COMMENT '结束时间',
   ENABLED              SMALLINT COMMENT '是否启用(0,禁止,1,启用)',
   AUTHTYPE             SMALLINT COMMENT '授权类型(0,全权,1,部分流程,2,条件代理)',
   AGENTID              BIGINT   COMMENT '代理人ID',
   AGENT                VARCHAR(100) COMMENT '代理人',
   FLOWKEY              VARCHAR(100) COMMENT '流程key(条件代理时填写)',
   FLOWNAME             VARCHAR(100) COMMENT '流程名称'
);

ALTER TABLE BPM_AGENT_SETTING COMMENT '代理设定';

ALTER TABLE BPM_AGENT_SETTING
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_APPROVAL_ITEM                                     */
/*==============================================================*/
CREATE TABLE BPM_APPROVAL_ITEM
(

   ITEMID            BIGINT    NOT NULL,
   USERID            BIGINT    COMMENT '用户ID',
   DEFKEY            VARCHAR(128) COMMENT '流程定义Key',
   TYPEID            BIGINT   COMMENT '流程分类ID',
   TYPE           	 SMALLINT    COMMENT '常用语类型
	默认为1=全局
		2=流程分类
		3=流程
		4=个人的常用语',
   EXPRESSION        VARCHAR(3000) COMMENT '流程项名项
            多个项用回车分开'
);

ALTER TABLE BPM_APPROVAL_ITEM COMMENT '任务审批常用语设置';

CREATE TABLE BPM_BUS_LINK
(
   BUS_ID               BIGINT   NOT NULL COMMENT '主键',
   BUS_FORM_TABLE       VARCHAR(255) COMMENT '对应关联表名',
   BUS_PK               BIGINT   COMMENT '对应关联表主键',
   BUS_PKSTR            VARCHAR(50) COMMENT '对应关联表主键(字符串)',
   BUS_DEF_ID           BIGINT   COMMENT '流程定义ID',
   BUS_FLOW_RUNID       BIGINT   COMMENT '流程运行ID',
   BUS_CREATOR_ID       BIGINT   COMMENT '发起人ID',
   BUS_CREATOR          VARCHAR(500) COMMENT '发起人',
   BUS_ORG_ID           BIGINT   COMMENT '发起组织ID',
   BUS_ORG_NAME         VARCHAR(200) COMMENT '组织名称',
   BUS_CREATETIME       TIMESTAMP COMMENT '发起时间',
   BUS_UPDID            BIGINT   COMMENT '最后更新人',
   BUS_UPD              VARCHAR(500) COMMENT '最后更新人',
   BUS_STATUS			SMALLINT   COMMENT '业务数据状态(0,业务数据,1,流程运行,2,流程结束,3,手工结束)',
   BUS_UPDTIME          TIMESTAMP COMMENT '最后更新时间',
   BUS_PROC_INST_ID		BIGINT		 COMMENT'流程实例ID用于关联业务数据和任务'
)
	partition by LIST COLUMNS(BUS_FORM_TABLE)( 
	 	partition P_TABLE_UNCREATED VALUES in ('TABLE_UNCREATED')
	);
ALTER TABLE BPM_BUS_LINK COMMENT '业务数据关联表';
ALTER TABLE BPM_BUS_LINK ADD PRIMARY KEY (BUS_ID,BUS_FORM_TABLE);

/*==============================================================*/
/* Table: BPM_COMMU_RECEIVER                                    */
/*==============================================================*/
CREATE TABLE BPM_COMMU_RECEIVER
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   OPINIONID            BIGINT   COMMENT '意见ID',
   RECEVIERID           BIGINT   COMMENT '接收人ID',
   RECEIVERNAME         VARCHAR(100) COMMENT '接收人姓名',
   STATUS               SMALLINT COMMENT '状态 (0 未读,1,已阅,2,已反馈）',
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   RECEIVETIME          TIMESTAMP COMMENT '接收时间',
   FEEDBACKTIME         TIMESTAMP COMMENT '反馈时间',
   TASKID               BIGINT   COMMENT '任务ID'
);

ALTER TABLE BPM_COMMU_RECEIVER COMMENT '通知任务接收人';

ALTER TABLE BPM_COMMU_RECEIVER
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_DATA_TEMPLATE                                     */
/*==============================================================*/
CREATE TABLE BPM_DATA_TEMPLATE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   TABLEID              BIGINT   COMMENT '自定义表ID',
   FORMKEY              VARCHAR(50)   COMMENT '自定义表单key',
   NAME                 VARCHAR(300) COMMENT '名称',
   ALIAS                VARCHAR(50) COMMENT '别名',
   STYLE                SMALLINT COMMENT '样式',
   NEEDPAGE             SMALLINT COMMENT '是否需要分页',
   PAGESIZE             SMALLINT COMMENT '分页大小',
   TEMPLATEALIAS        VARCHAR(50) COMMENT '数据模板别名',
   TEMPLATEHTML         TEXT COMMENT '数据模板代码',
   DISPLAYFIELD         TEXT COMMENT '显示字段',
   SORTFIELD            VARCHAR(200) COMMENT '排序字段',
   CONDITIONFIELD       TEXT COMMENT '条件字段',
   MANAGEFIELD          VARCHAR(2000) COMMENT '管理字段',
   FILTERFIELD          TEXT COMMENT '过滤条件',
   VARFIELD             VARCHAR(200)  COMMENT '变量字段',
   FILTERTYPE           SMALLINT COMMENT '过滤类型（1.建立条件,2.脚本条件）',
   SOURCE               SMALLINT COMMENT '数据来源',
   DEFID                BIGINT   COMMENT '流程定义ID',
   ISQUERY              SMALLINT default 1 COMMENT '是否查询',
   ISFILTER				SMALLINT default 1 COMMENT '是否过滤',
   EXPORTFIELD			TEXT COMMENT '导出字段',
   PRINTFIELD			TEXT COMMENT '打印字段'
);

ALTER TABLE BPM_DATA_TEMPLATE COMMENT '业务数据模板';

ALTER TABLE BPM_DATA_TEMPLATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_DEFINITION                                        */
/*==============================================================*/
CREATE TABLE BPM_DEFINITION
(
   DEFID                BIGINT   NOT NULL COMMENT '流程定义ID',
   TYPEID               BIGINT   COMMENT '分类ID',
   SUBJECT              VARCHAR(256) NOT NULL COMMENT '流程标题',
   DEFKEY               VARCHAR(128) NOT NULL COMMENT '流程定义Key
            流程定义Key，由设计人员加上，唯一',
   TASKNAMERULE         VARCHAR(512) COMMENT '任务标题生成规则',
   DESCP                VARCHAR(1024) COMMENT '流程描述',
   STATUS               SMALLINT COMMENT '流程状态
            0=草稿
            1=发布
            2=挂起
            ',
   DEFXML               TEXT COMMENT '流程定义XML(设计器)',
   ACTDEPLOYID          BIGINT   COMMENT 'activiti流程定义ID',
   ACTDEFKEY            VARCHAR(255) COMMENT 'act流程定义Key
            act流程定义Key，其生成的规则为流程定义Key+动态生成值，保证其唯一，当流程定义删除时，本表记录会删除，但actiiti的流程定义的数据则不删除，这样发布的流程会跟原来的流程定义的key可能会同样，因此，后续再加动态值，保证其值会activit的流程定义中key也唯一.',
   ACTDEFID             VARCHAR(128),
   CREATETIME           DATETIME COMMENT '创建时间',
   UPDATETIME           DATETIME,
   CREATEBY             BIGINT  ,
   UPDATEBY             BIGINT  ,
   REASON               VARCHAR(512),
   VERSIONNO            BIGINT  ,
   PARENTDEFID          BIGINT   COMMENT '隶属主定义ID
            
            当基于旧版本流程上进行新版本发布时，则生成一份新的记录，并且该记录的该字段则隶属于原来记录的主键id',
   ISMAIN               SMALLINT COMMENT '是否为主版本
            1=主版本
            0=非主版本
            ',
   TOFIRSTNODE          BIGINT   COMMENT '跳转到第一个节点
            0,不跳转
            1,跳转',
   SHOWFIRSTASSIGNEE    SMALLINT COMMENT '是否可以选择执行人',
   CANCHOICEPATH        VARCHAR(500) COMMENT '是否可以选择条件同步路径',
   ISUSEOUTFORM         SMALLINT,
   FORMDETAILURL        VARCHAR(200),
   ALLOWFINISHEDCC      SMALLINT COMMENT '是否允许流程结束时抄送',
   SUBMITCONFIRM        SMALLINT COMMENT '提交是否需要确认',
   ALLOWDIVERT          SMALLINT COMMENT '是否允许转办',
   INFORMSTART          VARCHAR(20) COMMENT '归档时发送消息给发起人 类型',
   INFORMTYPE           VARCHAR(20) COMMENT '通知类型',
   ATTACHMENT           BIGINT   COMMENT '帮助附件',
   SAMEEXECUTORJUMP     SMALLINT COMMENT '相同任务节点是否允许跳转',
   ALLOWREFER           SMALLINT COMMENT '允许参考数',
   INSTANCEAMOUNT       SMALLINT COMMENT '允许实例数量',
   ALLOWFINISHEDDIVERT  SMALLINT COMMENT '是否允许我的办结转发',
   ISPRINTFORM          SMALLINT COMMENT '是否开启打印模版功能',
   DIRECTSTART          SMALLINT COMMENT '直接启动不需要使用表单',
   CCMESSAGETYPE        VARCHAR(100) COMMENT '抄送消息类型 ',
   ALLOWDELDRAF         SMALLINT,
   TESTSTATUSTAG        VARCHAR(100) COMMENT '测试状态',
   ALLOWMOBILE          INT default 1 COMMENT '流程是否支持手机访问 0=不支持，1=支持',
   SKIPSETTING          VARCHAR(50) COMMENT '跳过设置'
);

ALTER TABLE BPM_DEFINITION COMMENT '流程定义扩展
Code:DEFTN';

ALTER TABLE BPM_DEFINITION
   ADD PRIMARY KEY (DEFID);


/*==============================================================*/
/* Table: BPM_DEF_VARS                                          */
/*==============================================================*/
CREATE TABLE BPM_DEF_VARS
(
   VARID                BIGINT   NOT NULL COMMENT '变量ID',
   DEFID                BIGINT   COMMENT '流程定义ID',
   VARNAME              VARCHAR(128) NOT NULL COMMENT '变量名称',
   VARKEY               VARCHAR(128) COMMENT '变量Key',
   VARDATATYPE          VARCHAR(64) COMMENT '变量数据类型
            
            string
            date
            number',
   DEFVALUE             VARCHAR(256) COMMENT '默认值',
   NODENAME             VARCHAR(256) COMMENT '节点名称',
   NODEID               VARCHAR(256),
   ACTDEPLOYID          BIGINT   COMMENT 'actDeployId',
   VARSCOPE             VARCHAR(64) COMMENT '作用域
            
            全局内=global
            任务内=task'
);

ALTER TABLE BPM_DEF_VARS COMMENT '流程变量定义';

ALTER TABLE BPM_DEF_VARS
   ADD PRIMARY KEY (VARID);

/*==============================================================*/
/* Table: BPM_EXE_STACK                                         */
/*==============================================================*/
CREATE TABLE BPM_EXE_STACK
(
   STACKID              BIGINT   NOT NULL,
   ACTDEFID             VARCHAR(64) COMMENT '流程定义ID',
   NODEID               VARCHAR(128) NOT NULL COMMENT '节点ID',
   NODENAME             VARCHAR(256) COMMENT '节点名',
   STARTTIME            TIMESTAMP COMMENT '开始时间',
   ENDTIME              TIMESTAMP COMMENT '结束时间',
   ASSIGNEES            VARCHAR(1024) COMMENT '执行人IDS，如1,2,3',
   ISMULTITASK          SMALLINT COMMENT '1=是
            0=否',
   PARENTID             BIGINT   COMMENT '父ID',
   ACTINSTID            BIGINT  ,
   TASKIDS              VARCHAR(512),
   NODEPATH             VARCHAR(1024) COMMENT '节点路径
            格式如：
            0.1.2.
            其中2则为本行记录的主键值',
   DEPTH                BIGINT   COMMENT '节点层',
   TASKTOKEN            VARCHAR(128) COMMENT '是针对分发任务时，携带的令牌，方便查找其父任务堆栈'
);

ALTER TABLE BPM_EXE_STACK COMMENT '流程执行堆栈树';

ALTER TABLE BPM_EXE_STACK
   ADD PRIMARY KEY (STACKID);

/*==============================================================*/
/* Table: BPM_FORM_DEF                                          */
/*==============================================================*/
CREATE TABLE BPM_FORM_DEF
(
   FORMDEFID            BIGINT   NOT NULL COMMENT '表单ID',
   CATEGORYID           BIGINT   COMMENT '表单分类',
   FORMKEY              VARCHAR(128) COMMENT '表单key',
   SUBJECT              VARCHAR(128) COMMENT '表单标题',
   FORMDESC             VARCHAR(200) COMMENT '描述',
   HTML                 TEXT COMMENT '定义html',
   TEMPLATE             TEXT COMMENT 'freeMaker模版',
   ISDEFAULT            SMALLINT COMMENT '是否缺省',
   VERSIONNO            BIGINT   COMMENT '版本号',
   TABLEID              BIGINT  ,
   ISPUBLISHED          SMALLINT,
   PUBLISHEDBY          VARCHAR(20),
   PUBLISHTIME          DATETIME,
   TABTITLE             VARCHAR(500) COMMENT 'Tab标题',
   DESIGNTYPE           SMALLINT,
   TEMPLATESID          VARCHAR(300) COMMENT '模板表对应ID',
   CREATEBY             BIGINT   COMMENT '创建人ID',
   CREATOR              VARCHAR(50) COMMENT '创建人',
   CREATETIME           TIMESTAMP COMMENT '创建时间'
);

ALTER TABLE BPM_FORM_DEF
   ADD PRIMARY KEY (FORMDEFID);

/*==============================================================*/
/* Table: BPM_FORM_DIALOG                                       */
/*==============================================================*/
CREATE TABLE BPM_FORM_DIALOG
(
   ID                   BIGINT   NOT NULL COMMENT '主键ID',
   NAME                 VARCHAR(50) COMMENT '对话框名称',
   ALIAS                VARCHAR(50) COMMENT '对话框别名
            唯一',
   STYLE                SMALLINT COMMENT '显示样式
            0,列表
            1,树形',
   WIDTH                BIGINT   COMMENT '对话框宽度',
   HEIGHT               BIGINT   COMMENT '高度',
   ISSINGLE             SMALLINT COMMENT '是否单选
            0,多选
            1.单选',
   NEEDPAGE             SMALLINT COMMENT '是否分页',
   ISTABLE              SMALLINT COMMENT '是否为表
            0,视图
            1,数据库表',
   OBJNAME              VARCHAR(50) COMMENT '对象名称',
   DISPLAYFIELD         VARCHAR(2000) COMMENT '需要显示的字段',
   CONDITIONFIELD       VARCHAR(2000) COMMENT '条件字段',
   RESULTFIELD          VARCHAR(1000) COMMENT '显示结果字段',
   DSNAME               VARCHAR(50) COMMENT '数据源名称',
   DSALIAS              VARCHAR(50) COMMENT '数据源别名',
   PAGESIZE             SMALLINT COMMENT '分页大小',
   SORTFIELD            VARCHAR(200) COMMENT '排序字段'
);

ALTER TABLE BPM_FORM_DIALOG COMMENT '通用表单对话框
';

ALTER TABLE BPM_FORM_DIALOG
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_FORM_FIELD                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_FIELD
(
   FIELDID              BIGINT   NOT NULL,
   TABLEID              BIGINT  ,
   FIELDNAME            VARCHAR(128) NOT NULL COMMENT '字段名称',
   FIELDTYPE            VARCHAR(128) NOT NULL COMMENT '字段类型',
   ISREQUIRED           SMALLINT COMMENT '是否必填',
   ISLIST               SMALLINT DEFAULT 1 COMMENT '是否列表显示',
   ISQUERY              SMALLINT DEFAULT 1 COMMENT '是否查询显示',
   FIELDDESC            VARCHAR(128) COMMENT '是否必填',
   CHARLEN              BIGINT   COMMENT '字符长度',
   INTLEN               BIGINT   COMMENT '整数长度',
   DECIMALLEN           BIGINT   COMMENT '小数长度',
   DICTTYPE             VARCHAR(100),
   ISDELETED            SMALLINT COMMENT '是否删除',
   VALIDRULE            VARCHAR(64) COMMENT '验证规则',
   ORIGINALNAME         VARCHAR(128) COMMENT '字段原名',
   SN                   BIGINT   COMMENT '排列顺序',
   VALUEFROM            SMALLINT COMMENT '值来源
            0 - 表单
            1 - 脚本',
   SCRIPT               VARCHAR(1000) COMMENT '默认脚本',
   CONTROLTYPE          SMALLINT COMMENT '0,无特殊控件
            1,数据字典
            2,用户选择器
            3.组织选择器',
   ISHIDDEN             SMALLINT,
   ISFLOWVAR            SMALLINT,
   SERIALNUM            VARCHAR(20),
   OPTIONS              VARCHAR(1000) COMMENT '选项',
   CTLPROPERTY          VARCHAR(2000) COMMENT '控件属性',
   ISALLOWMOBILE        SMALLINT,
   ISWEBSIGN            SMALLINT COMMENT '是否需要签名',
   ISREFERENCE          SMALLINT COMMENT '是否流程引用'
);

ALTER TABLE BPM_FORM_FIELD COMMENT '表单字段';

ALTER TABLE BPM_FORM_FIELD
   ADD PRIMARY KEY (FIELDID);

/*==============================================================*/
/* Table: BPM_FORM_QUERY                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_QUERY
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '名称',
   ALIAS                VARCHAR(50) COMMENT '别名',
   OBJ_NAME             VARCHAR(50) COMMENT '查询对象名称',
   NEEDPAGE             BIGINT   COMMENT '是否分页 0：否，1：是',
   CONDITIONFIELD       VARCHAR(2000) COMMENT '条件字段',
   RESULTFIELD          VARCHAR(2000) COMMENT '返回字段',
   DSNAME               VARCHAR(50) COMMENT '数据源名称',
   DSALIAS              VARCHAR(50) COMMENT '数据源别名',
   PAGESIZE             BIGINT   DEFAULT 10 COMMENT '分页条数',
   ISTABLE              BIGINT   COMMENT '是否为表
            0,视图
            1,数据库表',
   SORTFIELD            VARCHAR(200) COMMENT '排序字段'
);

ALTER TABLE BPM_FORM_QUERY
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_FORM_RIGHTS                                       */
/*==============================================================*/
CREATE TABLE BPM_FORM_RIGHTS
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   FORMKEY              VARCHAR(50)   COMMENT '表单ID',
   NAME                 VARCHAR(128),
   PERMISSION           VARCHAR(2000),
   TYPE                 SMALLINT COMMENT '权限类型(1,字段,2,子表,3,意见)',
   NODEID               VARCHAR(60),
   ACTDEFID             VARCHAR(60) COMMENT 'ACTDEFID',
   PARENTACTDEFID       VARCHAR(128) COMMENT '从属父流程定义ID',
   PLATFORM             INT DEFAULT 1 COMMENT '0表示pc，1表示mobile'
);

ALTER TABLE BPM_FORM_RIGHTS COMMENT '表单权限';

ALTER TABLE BPM_FORM_RIGHTS
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_FORM_RULE                                         */
/*==============================================================*/
CREATE TABLE BPM_FORM_RULE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '规则名',
   REGULATION           VARCHAR(100) COMMENT '规则',
   MEMO                 VARCHAR(100) COMMENT '备注',
   TIPINFO              VARCHAR(100) COMMENT '提示信息'
);

ALTER TABLE BPM_FORM_RULE COMMENT '表单认证规则';

ALTER TABLE BPM_FORM_RULE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_FORM_RUN                                          */
/*==============================================================*/
CREATE TABLE BPM_FORM_RUN
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   FORMDEFID            BIGINT   COMMENT '表单定义ID',
   FORMDEFKEY           VARCHAR(50)   COMMENT '表单定义key',
   ACTINSTANCEID        VARCHAR(64) COMMENT '流程实例ID',
   ACTDEFID             VARCHAR(64),
   ACTNODEID            VARCHAR(64) COMMENT '流程节点id',
   RUNID                BIGINT   COMMENT '流程运行ID',
   SETTYPE              SMALLINT COMMENT '表单类型
            0,任务节点
            1,开始表单
            2,全局表单',
   FORMTYPE             SMALLINT COMMENT '表单类型',
   FORMURL              VARCHAR(255) COMMENT '表单地址',
   MOBILEFORMID         BIGINT COMMENT '手机表单ID',
   MOBILEFORMKEY        BIGINT COMMENT '手机表单KEY',
   MOBILEFORMURL        VARCHAR(255) COMMENT '手机表单URL'
);

ALTER TABLE BPM_FORM_RUN COMMENT '流程表单运行情况';

ALTER TABLE BPM_FORM_RUN
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_FORM_TABLE                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_TABLE
(
   TABLEID              BIGINT   NOT NULL,
   TABLENAME            VARCHAR(128) NOT NULL COMMENT '表名',
   TABLEDESC            VARCHAR(128),
   ISMAIN               SMALLINT COMMENT '是否主表',
   MAINTABLEID          BIGINT  ,
   VERSIONNO            BIGINT  ,
   ISDEFAULT            SMALLINT,
   ISPUBLISHED          SMALLINT,
   PUBLISHEDBY          VARCHAR(100),
   PUBLISHTIME          DATETIME,
   ISEXTERNAL           SMALLINT COMMENT '是否外部数据源',
   DSALIAS              VARCHAR(50) COMMENT '数据源别名',
   DSNAME               VARCHAR(50) COMMENT '数据源名称',
   RELATION             VARCHAR(500) COMMENT '字段关联关系',
   KEYTYPE              SMALLINT COMMENT '键类型',
   KEYVALUE             VARCHAR(20) COMMENT '键值',
   PKFIELD              VARCHAR(20) COMMENT '主键字段',
   LISTTEMPLATE         TEXT,
   DETAILTEMPLATE       TEXT,
   GENBYFORM            SMALLINT,
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   CREATOR              VARCHAR(50) COMMENT '创建人ID',
   CREATEBY             BIGINT   COMMENT '创建人ID',
   KEYDATATYPE          SMALLINT COMMENT '主键数据类型(0,数字,1字符串)',
   TEAM                 TEXT COMMENT '分组',
   CATEGORYID           BIGINT COMMENT '分类'
);

ALTER TABLE BPM_FORM_TABLE COMMENT '表单数据库表';

ALTER TABLE BPM_FORM_TABLE
   ADD PRIMARY KEY (TABLEID);

/*==============================================================*/
/* Table: BPM_FORM_TEMPLATE                                     */
/*==============================================================*/
CREATE TABLE BPM_FORM_TEMPLATE
(
   TEMPLATEID           BIGINT   NOT NULL,
   TEMPLATENAME         VARCHAR(200),
   TEMPLATETYPE         VARCHAR(20),
   MACROTEMPLATEALIAS   VARCHAR(50) COMMENT '使用宏模板别名',
   HTML                 TEXT COMMENT '模版代码',
   TEMPLATEDESC         VARCHAR(400) COMMENT '模版描述',
   CANEDIT              SMALLINT COMMENT '能否编辑',
   ALIAS                VARCHAR(50) COMMENT '别名'
);

ALTER TABLE BPM_FORM_TEMPLATE COMMENT '表单模版';

ALTER TABLE BPM_FORM_TEMPLATE
   ADD PRIMARY KEY (TEMPLATEID);

/*==============================================================*/
/* Table: BPM_GANGED_SET                                        */
/*==============================================================*/
CREATE TABLE BPM_GANGED_SET
(
   ID                   BIGINT   NOT NULL,
   DEFID                BIGINT   COMMENT '流程定义ID',
   NODEID               VARCHAR(100) COMMENT '节点ID',
   NODENAME             VARCHAR(200) COMMENT '节点名称',
   CHOISEFIELD          TEXT COMMENT '选择框字段设置',
   CHANGEFIELD          TEXT COMMENT '变更字段设置'
);

ALTER TABLE BPM_GANGED_SET COMMENT '表单联动设置';

ALTER TABLE BPM_GANGED_SET
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_MON_GROUP                                         */
/*==============================================================*/
CREATE TABLE BPM_MON_GROUP
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   NAME                 VARCHAR(200) COMMENT '分组名',
   GRADE                SMALLINT COMMENT '权限等级',
   ENABLED              SMALLINT COMMENT '是否生效',
   CREATORID            BIGINT   COMMENT '创建人ID',
   CREATOR              VARCHAR(50) COMMENT '创建人',
   CREATETIME           DATETIME COMMENT '创建时间'
);

ALTER TABLE BPM_MON_GROUP COMMENT '流程监控分组';

ALTER TABLE BPM_MON_GROUP
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_MON_GROUPITEM                                     */
/*==============================================================*/
CREATE TABLE BPM_MON_GROUPITEM
(
   ID                   BIGINT   NOT NULL,
   GROUPID              BIGINT  ,
   FLOWKEY              VARCHAR(50)
);

ALTER TABLE BPM_MON_GROUPITEM COMMENT '分组流程定义';

ALTER TABLE BPM_MON_GROUPITEM
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_MON_ORGROLE                                       */
/*==============================================================*/
CREATE TABLE BPM_MON_ORGROLE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   GROUPID              BIGINT   COMMENT '分组ID',
   ROLEID               BIGINT   COMMENT '角色ID',
   ORGID                BIGINT   COMMENT '组织ID'
);

ALTER TABLE BPM_MON_ORGROLE COMMENT '分组角色组织授权';

ALTER TABLE BPM_MON_ORGROLE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_NODE_BTN                                          */
/*==============================================================*/
CREATE TABLE BPM_NODE_BTN
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   ACTDEFID             VARCHAR(64) COMMENT '流程定义ID',
   ISSTARTFORM          SMALLINT COMMENT '流程启动表单
            0,启动表单
            1,任务表单',
   NODEID               VARCHAR(50) COMMENT '节点ID',
   BTNNAME              VARCHAR(50) COMMENT '按钮名称',
   ICONCLSNAME          VARCHAR(50) COMMENT '图标样式',
   OPERATORTYPE         SMALLINT COMMENT '操作类型',
   PREVSCRIPT           VARCHAR(2000) COMMENT '前置脚本',
   AFTERSCRIPT          VARCHAR(2000) COMMENT '后置脚本',
   NODETYPE             SMALLINT COMMENT '节点类型
            0，普通节点
            1，会签节点',
   SN                   BIGINT   COMMENT '排序',
   DEFID                BIGINT  
);

ALTER TABLE BPM_NODE_BTN COMMENT '自定义工具条';

ALTER TABLE BPM_NODE_BTN
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_NODE_MESSAGE                                      */
/*==============================================================*/
CREATE TABLE BPM_NODE_MESSAGE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   ACTDEFID             VARCHAR(64) COMMENT '流程定义ID',
   NODEID               VARCHAR(50) COMMENT '流程节点ID',
   MESSAGETYPE          SMALLINT,
   SUBJECT              VARCHAR(200) COMMENT '消息主题',
   TEMPLATE             TEXT COMMENT '消息模板',
   ISSEND               SMALLINT COMMENT '确认发送'
);

ALTER TABLE BPM_NODE_MESSAGE COMMENT '流程消息节点';

ALTER TABLE BPM_NODE_MESSAGE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_NODE_PRIVILEGE                                    */
/*==============================================================*/
CREATE TABLE BPM_NODE_PRIVILEGE
(
   PRIVILEGEID          BIGINT   NOT NULL,
   ACTDEFID             VARCHAR(128) COMMENT 'Act流程发布ID',
   NODEID               VARCHAR(128) COMMENT ' 流程节点ID',
   PRIVILEGEMODE        SMALLINT COMMENT '0=拥有所有特权
            1=允许直接处理
            2=允许一票制
            3=允许补签',
   USERTYPE             SMALLINT COMMENT '0=发起人
            1=用户
            2=角色
            3=组织
            4=组织负责人
            5=岗位
            6=上下级
            7=用户属性
            8=组织属性
            9=本部门
            10=为某个节点的执行人
            11=动态计算(如来自指定的方法或接口，如可允许来自上个表单的用户选择)',
   CMPNAMES             TEXT COMMENT '特权人',
   CMPIDS               TEXT COMMENT '特权人ID'
);

ALTER TABLE BPM_NODE_PRIVILEGE COMMENT '节点特权';

ALTER TABLE BPM_NODE_PRIVILEGE
   ADD PRIMARY KEY (PRIVILEGEID);

/*==============================================================*/
/* Table: BPM_NODE_RULE                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_RULE
(
   RULEID               BIGINT   NOT NULL COMMENT '主键',
   RULENAME             VARCHAR(128) NOT NULL COMMENT '规则名称',
   CONDITIONCODE        TEXT COMMENT '规则表达式',
   ACTDEFID             VARCHAR(127) COMMENT 'Act流程发布ID',
   NODEID               VARCHAR(50),
   PRIORITY             BIGINT   COMMENT '优先级别',
   TARGETNODE           VARCHAR(20) COMMENT '跳转节点',
   TARGETNODENAME       VARCHAR(255) COMMENT '跳转节点名称',
   MEMO                 VARCHAR(200) COMMENT '备注'
);

ALTER TABLE BPM_NODE_RULE COMMENT '流程节点规则
流程节点规则 是用于设计根据表单或其他流程变量来进行计算，以实现其规则的跳转
CO';

ALTER TABLE BPM_NODE_RULE
   ADD PRIMARY KEY (RULEID);

/*==============================================================*/
/* Table: BPM_NODE_SCRIPT                                       */
/*==============================================================*/
CREATE TABLE BPM_NODE_SCRIPT
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   MEMO                 VARCHAR(50) COMMENT '备注',
   NODEID               VARCHAR(20) COMMENT '流程节点ID',
   ACTDEFID             VARCHAR(64) COMMENT '流程定义ID',
   SCRIPT               TEXT COMMENT '脚本',
   SCRIPTTYPE           BIGINT   COMMENT '脚本类型
                        1.事件前置脚本
                        2.事件后置脚本
                        3.脚本节点
                        4.任务分配脚本'
);

ALTER TABLE BPM_NODE_SCRIPT COMMENT '流程节点事件脚本';

ALTER TABLE BPM_NODE_SCRIPT
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_NODE_SET                                          */
/*==============================================================*/
CREATE TABLE BPM_NODE_SET
(
   SETID                BIGINT   NOT NULL,
   DEFID                BIGINT   COMMENT '流程定义ID',
   NODENAME             VARCHAR(256) COMMENT '节点名',
   NODEORDER            SMALLINT COMMENT '节点顺序编号',
   NODEID               VARCHAR(128) COMMENT 'activiti 节点ID',
   FORMTYPE             SMALLINT DEFAULT 0 COMMENT '0=在线流程表单
            1=URL表单',
   FORMURL              VARCHAR(255) COMMENT '当表单类型为1时，表单对应的url',
   FORMKEY              VARCHAR(50)   COMMENT '表单KEY',
   ACTDEFID             VARCHAR(127) COMMENT 'Act流程发布ID',
   FORMDEFNAME          VARCHAR(255),
   NODETYPE             SMALLINT,
   JOINTASKKEY          VARCHAR(128),
   JOINTASKNAME         VARCHAR(256),
   BEFOREHANDLER        VARCHAR(100) COMMENT '前置处理器',
   AFTERHANDLER         VARCHAR(100) COMMENT '后置处理器',
   JUMPTYPE             VARCHAR(32),
   SETTYPE              SMALLINT COMMENT '节点设置类型
            0.任务节点
            1.开始表单
            2.默认表单',
   ISJUMPFORDEF         SMALLINT,
   ISHIDEOPTION         SMALLINT COMMENT '是否隐藏意见表单',
   ISHIDEPATH           SMALLINT COMMENT '是否隐藏路径',
   DETAILURL            VARCHAR(256) COMMENT 'URL明细',
   ISALLOWMOBILE        SMALLINT COMMENT '是否允许手机访问',
   INFORMTYPE           VARCHAR(20),
   PARENTACTDEFID       VARCHAR(128) COMMENT '从属父流程定义ID',
   MOBILEFORMKEY        BIGINT COMMENT '手机表单key',
   MOBILEFORMURL        VARCHAR(256) COMMENT '手机表单url',
   MOBILEDETAILURL      VARCHAR(256) COMMENT '手机表单url明细',
   ISREQUIRED           SMALLINT DEFAULT 0 COMMENT '是否必填',
   ISPOPUP              SMALLINT DEFAULT 1 COMMENT '是否弹出',
   OPINIONFIELD         VARCHAR(64) COMMENT '意见回填字段',
   OPINIONHTML          SMALLINT DEFAULT 1,
   SCOPE 				VARCHAR(256) COMMENT '人员选择范围',
   SENDMSGTOCREATOR     SMALLINT  COMMENT '启动人流程信息提醒'
);

ALTER TABLE BPM_NODE_SET COMMENT '流程节点设置';

ALTER TABLE BPM_NODE_SET
   ADD PRIMARY KEY (SETID);

/*==============================================================*/
/* Table: BPM_NODE_SIGN                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_SIGN
(
   SIGNID               BIGINT   NOT NULL,
   ACTDEFID             VARCHAR(127) COMMENT 'Act流程发布ID',
   NODEID               VARCHAR(128) COMMENT '流程节点ID',
   VOTEAMOUNT           BIGINT   COMMENT '票数',
   DECIDETYPE           SMALLINT NOT NULL COMMENT '决策方式
            1=pass 通过
            2=reject 拒绝',
   VOTETYPE             SMALLINT COMMENT '1=百分比
            2=绝对票数',
   FLOWMODE             SMALLINT COMMENT '后续处理模式,
            1=满足条件则直接通过;
            2=所有任务结束后再通过.'
);

ALTER TABLE BPM_NODE_SIGN COMMENT '任务会签设置';

ALTER TABLE BPM_NODE_SIGN
   ADD PRIMARY KEY (SIGNID);

/*==============================================================*/
/* Table: BPM_NODE_USER                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_USER
(
   NODEUSERID           BIGINT   NOT NULL,
   CMPIDS               TEXT,
   CMPNAMES             TEXT,
   ASSIGNTYPE           VARCHAR(20) NOT NULL COMMENT '指派人员类型
            0=发起人
            1=用户
            2=角色
            3=组织
            4=组织负责人
            5=岗位
            6=上下级
            7=用户属性
            8=组织属性
            9=本部门
            10=为某个节点的执行人
            11=动态计算(如来自指定的方法或接口，如可允许来自上个表单的用户选择)',
   SN                   BIGINT  ,
   COMPTYPE             SMALLINT COMMENT '计算类型
            0=or
            1=and
            2=exclude',
   CONDITIONID          BIGINT   COMMENT '条件ID',
   EXTRACTUSER          SMALLINT COMMENT '是否抽取用户 
            0:不抽取 
            1:抽取
            2.二级抽取
            3.用户分组'
);

ALTER TABLE BPM_NODE_USER COMMENT '流程节点人员';

ALTER TABLE BPM_NODE_USER
   ADD PRIMARY KEY (NODEUSERID);

/*==============================================================*/
/* Table: BPM_NODE_WEBSERVICE                                   */
/*==============================================================*/
CREATE TABLE BPM_NODE_WEBSERVICE
(
   ID                   BIGINT   NOT NULL,
   ACTDEFID             VARCHAR(128) COMMENT '流程定义ID',
   NODEID               VARCHAR(128) COMMENT '节点Id',
   DOCUMENT             text COMMENT 'webservice地址'
);

ALTER TABLE BPM_NODE_WEBSERVICE COMMENT '流程webservice节点';

ALTER TABLE BPM_NODE_WEBSERVICE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_NODE_WS_PARAMS                                    */
/*==============================================================*/
CREATE TABLE BPM_NODE_WS_PARAMS
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   WEBSERVICEID         BIGINT   COMMENT 'webservice节点ID',
   PARATYPE             BIGINT   COMMENT '参数类型【1：表示输入参数，0：输出参数】',
   VARID                BIGINT   COMMENT '变量id',
   WSNAME               VARCHAR(256) COMMENT 'webservice变量名称',
   TYPE                 VARCHAR(128) COMMENT '变量类型【变量类型，string long date,等】'
);

ALTER TABLE BPM_NODE_WS_PARAMS
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_PRINT_TEMPLATE                                    */
/*==============================================================*/
CREATE TABLE BPM_PRINT_TEMPLATE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   FORM_KEY             VARCHAR(50)   COMMENT '表单key',
   TEMAPALTE_NAME       VARCHAR(200) COMMENT '模版名称',
   IS_DEFAULT           SMALLINT COMMENT '是否默认',
   TABLEID              BIGINT   COMMENT '表ID',
   HTML                 TEXT COMMENT '模版HTML',
   TEMPLATE             TEXT COMMENT '模版'
);

ALTER TABLE BPM_PRINT_TEMPLATE COMMENT '打印模版';

ALTER TABLE BPM_PRINT_TEMPLATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_PRO_CPTO                                          */
/*==============================================================*/
CREATE TABLE BPM_PRO_CPTO
(
   COPY_ID              BIGINT   NOT NULL COMMENT '抄送主键ID',
   ACT_INST_ID          BIGINT   COMMENT 'ACT流程实例ID',
   RUN_ID               BIGINT   COMMENT 'PRO流程实例ID',
   NODE_KEY             VARCHAR(100) COMMENT '节点Key',
   NODE_NAME            VARCHAR(100) COMMENT '节点名称',
   CC_UID               BIGINT   COMMENT '抄送人名ID',
   CC_UNAME             VARCHAR(50) COMMENT '抄送人名',
   CC_TIME              TIMESTAMP COMMENT '抄送时间',
   IS_READED            SMALLINT COMMENT '是否已阅
            0=未读
            1=已读',
   FILL_OPINION         VARCHAR(2000) COMMENT '填写意见',
   SUBJECT              VARCHAR(300) COMMENT '流程标题',
   READ_TIME            TIMESTAMP COMMENT '阅读时间',
   CP_TYPE              SMALLINT COMMENT '1=抄送   在任务处理时，可以指定抄送人员进行任务抄送转发
            2=转发  在流程归档后，进行转发处理',
   CREATE_ID            BIGINT   COMMENT '发起人ID',
   CREATOR              VARCHAR(50) COMMENT '发起人名',
   DEF_TYPEID           BIGINT   COMMENT '流程分类ID'
);

ALTER TABLE BPM_PRO_CPTO COMMENT '流程抄送转发';

ALTER TABLE BPM_PRO_CPTO
   ADD PRIMARY KEY (COPY_ID);

/*==============================================================*/
/* Table: BPM_PRO_RUN                                           */
/*==============================================================*/
CREATE TABLE BPM_PRO_RUN
(
   RUNID                BIGINT   NOT NULL,
   DEFID                BIGINT   COMMENT '流程定义ID',
   BUSINESSKEY_NUM		BIGINT	     NULL,
   PROCESSNAME          VARCHAR(256),
   SUBJECT              VARCHAR(600) COMMENT '流程实例标题',
   CREATORID            BIGINT   COMMENT '创建人ID',
   CREATOR              VARCHAR(128) COMMENT '创建人',
   CREATETIME           DATETIME COMMENT '创建时间',
   BUSDESCP             VARCHAR(3000) COMMENT '业务表单简述',
   ACTINSTID            BIGINT   COMMENT 'ACT流程实例ID',
   STATUS               SMALLINT COMMENT '状态
            0=草稿
            1=启动
            2=正在运行
            3=正常结束
            
            ',
   ACTDEFID             VARCHAR(256) COMMENT 'ACT流程定义ID',
   BUSINESSKEY          VARCHAR(255),
   BUSINESSURL          VARCHAR(255) COMMENT '业务明细url
            
            可相对路径，也可绝对路径
            如:http://192.168.1.8:8080/bpm/fund/get.ht?fundId={0}
            或:/bpm/fund/fundId={0}
            ',
   ENDTIME              DATETIME,
   DURATION             BIGINT  ,
   PKNAME               VARCHAR(50),
   TABLENAME            VARCHAR(50),
   PARENTID             BIGINT   COMMENT '父流程实例ID',
   STARTORGID           BIGINT   COMMENT '发起人所在组织Id',
   STARTORGNAME         VARCHAR(200) COMMENT '发起人所在组织名称',
   FORMDEFID            BIGINT   COMMENT '表单定义',
   TYPEID               BIGINT   COMMENT '分类ID',
   DSALIAS              VARCHAR(50) COMMENT '数据源',
   FLOWKEY              VARCHAR(50) COMMENT '流程定义KEY',
   FORMTYPE             SMALLINT,
   FORMKEYURL           VARCHAR(50),
   LASTSUBMITDURATION   BIGINT   COMMENT '基于最后一次提交，执行持续时间总长',
   ISFORMAL             SMALLINT COMMENT '是否正式流程实例',
   RELRUNID             BIGINT   COMMENT '关联实例ID',
   GLOBALFLOWNO         VARCHAR(256)   COMMENT '全局流程变量', 
   STARTNODE			VARCHAR(50) COMMENT '选择的第一个节点的名称'
);

ALTER TABLE BPM_PRO_RUN COMMENT '流程实例扩展
Code:PRO_RUN';

ALTER TABLE BPM_PRO_RUN
   ADD PRIMARY KEY (RUNID);

/*==============================================================*/
/* Table: BPM_PRO_RUN_HIS                                       */
/*==============================================================*/
CREATE TABLE BPM_PRO_RUN_HIS
(
   RUNID                BIGINT   NOT NULL,
   DEFID                BIGINT   COMMENT '流程定义ID',
    BUSINESSKEY_NUM		BIGINT	     NULL,
   PROCESSNAME          VARCHAR(256),
   SUBJECT              VARCHAR(600) COMMENT '流程实例标题',
   CREATORID            BIGINT   COMMENT '创建人ID',
   CREATOR              VARCHAR(128) COMMENT '创建人',
   CREATETIME           DATETIME COMMENT '创建时间',
   BUSDESCP             VARCHAR(3000) COMMENT '业务表单简述',
   ACTINSTID            BIGINT   COMMENT 'ACT流程实例ID',
   STATUS               SMALLINT COMMENT '状态
            0=草稿
            1=启动
            2=正在运行
            3=正常结束
            
            ',
   ACTDEFID             VARCHAR(256) COMMENT 'ACT流程定义ID',
   BUSINESSKEY          VARCHAR(255),
   BUSINESSURL          VARCHAR(255) COMMENT '业务明细url
            
            可相对路径，也可绝对路径
            如:http://192.168.1.8:8080/bpm/fund/get.ht?fundId={0}
            或:/bpm/fund/fundId={0}
            ',
   ENDTIME              DATETIME,
   DURATION             BIGINT  ,
   PKNAME               VARCHAR(50),
   TABLENAME            VARCHAR(50),
   PARENTID             BIGINT   COMMENT '父流程实例ID',
   STARTORGID           BIGINT   COMMENT '发起人所在组织Id',
   STARTORGNAME         VARCHAR(200) COMMENT '发起人所在组织名称',
   FORMDEFID            BIGINT   COMMENT '表单定义',
   TYPEID               BIGINT   COMMENT '分类ID',
   DSALIAS              VARCHAR(50) COMMENT '数据源',
   FLOWKEY              VARCHAR(50) COMMENT '流程定义KEY',
   FORMTYPE             SMALLINT,
   FORMKEYURL           VARCHAR(50),
   LASTSUBMITDURATION   BIGINT   COMMENT '基于最后一次提交，执行持续时间总长',
   ISFORMAL             SMALLINT COMMENT '是否正式流程实例',
   RELRUNID             BIGINT   COMMENT '关联实例ID',
   GLOBALFLOWNO         VARCHAR(256)   COMMENT '全局流程变量',
   STARTNODE			VARCHAR(50) COMMENT '选择的第一个节点的名称'
);

ALTER TABLE BPM_PRO_RUN_HIS COMMENT '流程实例扩展
Code:PRO_RUN';

ALTER TABLE BPM_PRO_RUN_HIS
   ADD PRIMARY KEY (RUNID);

/*==============================================================*/
/* Table: BPM_PRO_STATUS                                        */
/*==============================================================*/
CREATE TABLE BPM_PRO_STATUS
(
   ID                   BIGINT   COMMENT '主键',
   ACTINSTID            BIGINT   COMMENT '流程实例ID',
   NODEID               VARCHAR(64) COMMENT '节点ID',
   NODENAME             VARCHAR(255) COMMENT '节点名称',
   STATUS               SMALLINT COMMENT '状态',
   LASTUPDATETIME       DATETIME COMMENT '最后更新时间',
   ACTDEFID             VARCHAR(64) COMMENT 'ACT流程定义ID',
   DEFID                BIGINT   COMMENT '流程定义ID'
);

ALTER TABLE BPM_PRO_STATUS COMMENT '流程节点状态';

/*==============================================================*/
/* Table: BPM_PRO_TRANSTO                                       */
/*==============================================================*/
CREATE TABLE BPM_PRO_TRANSTO
(
   ID                   BIGINT   NOT NULL   COMMENT '主键',
   ACTINSTID            BIGINT   COMMENT '流程实例ID',
   TASKID               BIGINT   COMMENT '任务ID',
   TRANSTYPE            SMALLINT   COMMENT '流转类型(1,非会签,2,会签)',
   ACTION               SMALLINT   COMMENT '执行后动作(1,返回,2,会签)',
   CREATEUSERID         BIGINT   COMMENT '发起人ID',
   CREATETIME           TIMESTAMP   COMMENT '创建时间',
   TRANSRESULT          SMALLINT	COMMENT '投票结果',
   ASSIGNEE 			VARCHAR(256) COMMENT '流转人'
);

ALTER TABLE BPM_PRO_TRANSTO COMMENT '流程流转';

ALTER TABLE BPM_PRO_TRANSTO
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_REFER_DEFINITION                                  */
/*==============================================================*/
CREATE TABLE BPM_REFER_DEFINITION
(
   ID                   BIGINT   NOT NULL,
   DEFID                VARCHAR(200) COMMENT '流程定义ID',
   REFER_DEFKEY 		VARCHAR(128) COMMENT '流程定义Key',
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   CREATEID             BIGINT   COMMENT '创建人ID',
   UPDATETIME           TIMESTAMP,
   STATE                SMALLINT COMMENT '流程状态',
   REMARK               VARCHAR(400) COMMENT '备注',
   SUBJECT              VARCHAR(200),
   UPDATEID             BIGINT  
);

ALTER TABLE BPM_REFER_DEFINITION COMMENT '流程引用定义';

ALTER TABLE BPM_REFER_DEFINITION
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_RUN_LOG                                           */
/*==============================================================*/
CREATE TABLE BPM_RUN_LOG
(
   ID                   BIGINT  ,
   USERID               BIGINT  ,
   USERNAME             VARCHAR(50),
   CREATETIME           DATETIME,
   OPERATORTYPE         SMALLINT,
   MEMO                 VARCHAR(300),
   RUNID                BIGINT   COMMENT '流程运行ID',
   PROCESSSUBJECT       VARCHAR(300)
);

ALTER TABLE BPM_RUN_LOG COMMENT '工作流运行日志';

/*==============================================================*/
/* Table: BPM_SUBTABLE_RIGHTS                                   */
/*==============================================================*/
CREATE TABLE BPM_SUBTABLE_RIGHTS
(
   ID                   BIGINT  ,
   ACTDEFID             VARCHAR(100) COMMENT '流程定义ID',
   NODEID               VARCHAR(50) COMMENT '节点ID',
   TABLEID              BIGINT   COMMENT '子表表ID',
   PERMISSIONTYPE       SMALLINT COMMENT '权限类型(1,简单配置,2,脚本)',
   PERMISSIONSETING     VARCHAR(2000) COMMENT '权限配置',
   PARENTACTDEFID       VARCHAR(128) COMMENT '从属父流程定义ID'
);

/*==============================================================*/
/* Table: BPM_TABLE_TEMPLATE                                    */
/*==============================================================*/
CREATE TABLE BPM_TABLE_TEMPLATE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   TABLE_ID             BIGINT   COMMENT '表ID',
   CATEGORY_ID          BIGINT   COMMENT '表单类型',
   HTML_LIST            TEXT COMMENT '列表html模板',
   HTML_DETAIL          TEXT COMMENT '详情的html模板',
   TEMPLATE_NAME        VARCHAR(128) COMMENT '列表模板名',
   AUTHOR_TYPE          BIGINT   COMMENT '查看业务数据的授权类型
            1：查看所有业务数据；2：查看本人业务数据；3：查看本人及下属业务数据',
   FORMKEY              BIGINT   COMMENT '表单KEY',
   MEMO                 VARCHAR(100) COMMENT '备注',
   LISTTEMPLATE         VARCHAR(100),
   DETAILTEMPLATE       VARCHAR(100) NOT NULL
);

ALTER TABLE BPM_TABLE_TEMPLATE COMMENT '自定义表数据模版';

ALTER TABLE BPM_TABLE_TEMPLATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_TASK_DUE                                          */
/*==============================================================*/
CREATE TABLE BPM_TASK_DUE
(
   TASKDUEID            BIGINT   NOT NULL COMMENT '时间设置ID',
   ACTDEFID             VARCHAR(127) COMMENT '流程定义ID',
   NODEID               VARCHAR(50) COMMENT '流程节点ID',
   REMINDERSTART        BIGINT   NOT NULL COMMENT '催办开始时间(相对于任务创建时间，多少工作日)',
   REMINDEREND          BIGINT   COMMENT '催办结束时间(相对于催办开始时间)',
   TIMES                BIGINT   COMMENT '催办次数',
   MAILCONTENT          TEXT COMMENT '催办提醒短信内容',
   MSGCONTENT           TEXT COMMENT '催办邮件模板内容',
   SMSCONTENT           TEXT COMMENT '催办邮件模板内容',
   ACTION               BIGINT   COMMENT '任务到期处理动作
            0=不做任何处理
            1=流程自动往下跳转
            2=结束该流程
            3=调用指定方法 如 service.xxMethod 该字段直接存储该值
            ',
   SCRIPT               VARCHAR(2000),
   COMPLETETIME         BIGINT  ,
   CONDEXP              TEXT,
   NAME                 VARCHAR(50),
   RELATIVENODEID       VARCHAR(100),
   RELATIVENODETYPE     BIGINT  ,
   RELATIVETIMETYPE     BIGINT  ,
   ASSIGNERID           BIGINT  ,
   ASSIGNERNAME         VARCHAR(50),
   WARNINGSETJSON       VARCHAR(2000) COMMENT '任务预警'
);

ALTER TABLE BPM_TASK_DUE COMMENT '任务节点催办时间设置';

ALTER TABLE BPM_TASK_DUE
   ADD PRIMARY KEY (TASKDUEID);

/*==============================================================*/
/* Table: BPM_TASK_EXE                                          */
/*==============================================================*/
CREATE TABLE BPM_TASK_EXE
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   TASKID               BIGINT   COMMENT '任务ID',
   ASSIGNEE_ID          BIGINT   COMMENT '承接人ID',
   ASSIGNEE_NAME        VARCHAR(50) COMMENT '承接人名称',
   OWNER_ID             BIGINT   COMMENT '任务所有人ID',
   OWNER_NAME           VARCHAR(200) COMMENT '任务所有人',
   SUBJECT              VARCHAR(400) COMMENT '实例标题',
   STATUS               SMALLINT COMMENT '状态',
   MEMO                 VARCHAR(4000) COMMENT '原因备注',
   CRATETIME            DATETIME COMMENT '创建时间',
   ACT_INST_ID          BIGINT   COMMENT '流程实例ID',
   TASK_NAME            VARCHAR(400) COMMENT '任务名',
   TASK_DEF_KEY         VARCHAR(64) COMMENT '任务Key',
   EXE_TIME             DATETIME COMMENT '完成时间',
   EXE_USER_ID          BIGINT   COMMENT '执行人id',
   EXE_USER_NAME        VARCHAR(256) COMMENT '执行人名',
   ASSIGN_TYPE          SMALLINT COMMENT '1=代理
            2=转办',
   RUNID                BIGINT   COMMENT '流程runID',
   TYPE_ID              BIGINT   COMMENT '类型ID'
);

ALTER TABLE BPM_TASK_EXE COMMENT '任务转办代理';

ALTER TABLE BPM_TASK_EXE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_TASK_FORK                                         */
/*==============================================================*/
CREATE TABLE BPM_TASK_FORK
(
   TASKFORKID          BIGINT NOT NULL COMMENT '主键',
   ACTINSTID           BIGINT COMMENT '流程实例ID',
   FORKTASKNAME         VARCHAR(256) COMMENT '分发任务名称',
   FORKTASKKEY          VARCHAR(256) COMMENT '分发任务Key',
   FORKSN              BIGINT COMMENT '分发序号',
   FORKCOUNT           BIGINT COMMENT '分发个数',
   FININSHCOUNT        BIGINT COMMENT '完成个数',
   FORKTIME             DATETIME COMMENT '分发时间',
   JOINTASKNAME         VARCHAR(256) COMMENT '汇总任务名称',
   JOINTASKKEY          VARCHAR(256) COMMENT '汇总任务Key',
   FORKTOKENS           VARCHAR(512) COMMENT '分发令牌号
            格式如：
            T_1_1,T_1_2,T_1_3,
            或 
            T_1,T_2,T_3,
            ',
   FORKTOKENPRE         VARCHAR(64) COMMENT '格式为T_
            或格式T_1
            或T_1_2等'
);

ALTER TABLE BPM_TASK_FORK COMMENT '流程任务分发汇总';

ALTER TABLE BPM_TASK_FORK
   ADD PRIMARY KEY (TASKFORKID);

/*==============================================================*/
/* Table: BPM_TASK_OPINION                                      */
/*==============================================================*/
CREATE TABLE BPM_TASK_OPINION
(
   OPINIONID           BIGINT NOT NULL COMMENT '意见ID',
   ACTDEFID             VARCHAR(127) COMMENT '流程定义ID',
   TASKNAME             VARCHAR(255) COMMENT '任务名称',
   TASKKEY              VARCHAR(64),
   TASKID               BIGINT,
   TASKTOKEN            VARCHAR(50),
   ACTINSTID            BIGINT,
   STARTTIME            TIMESTAMP COMMENT '执行开始时间',
   ENDTIME              TIMESTAMP null COMMENT '结束时间',
   DURTIME             BIGINT COMMENT '持续时间',
   EXEUSERID           BIGINT COMMENT '执行人ID',
   EXEFULLNAME          VARCHAR(127) COMMENT '执行人名',
   OPINION              TEXT COMMENT '审批意见',
   CHECKSTATUS          SMALLINT COMMENT '审批状态
            1=同意
            2=反对
            3=驳回
            0=弃权
            4=追回',
   FORMDEFID           BIGINT COMMENT '表单定义ID',
   FIELDNAME            VARCHAR(50) COMMENT '表单名',
   SUPEREXECUTION      BIGINT COMMENT '父流程实例ID'
);

ALTER TABLE BPM_TASK_OPINION COMMENT '流程任务审批意见';

ALTER TABLE BPM_TASK_OPINION
   ADD PRIMARY KEY (OPINIONID);

/*==============================================================*/
/* Table: BPM_TASK_READ                                         */
/*==============================================================*/
CREATE TABLE BPM_TASK_READ
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   ACTINSTID           BIGINT COMMENT '流程实例ID',
   TASKID              BIGINT COMMENT '任务ID',
   USERID              BIGINT COMMENT '用户ID',
   USERNAME             VARCHAR(100) COMMENT '用户名',
   CREATETIME           DATETIME COMMENT '创建时间'
);

ALTER TABLE BPM_TASK_READ COMMENT '任务已读';

ALTER TABLE BPM_TASK_READ
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_TASK_REMINDERSTATE                                */
/*==============================================================*/
CREATE TABLE BPM_TASK_REMINDERSTATE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   ACTDEFID             VARCHAR(127) COMMENT '流程定义ID',
   TASKID              BIGINT COMMENT '任务ID',
   REMINDERTIME         DATETIME COMMENT '催办时间',
   USERID               BIGINT,
   ACTINSTANCEID        BIGINT,
   REMINDTYPE           SMALLINT COMMENT '提醒类型(1，消息提醒,2,完成流程办结的处理动作)'
);

ALTER TABLE BPM_TASK_REMINDERSTATE COMMENT '任务催办执行情况';

ALTER TABLE BPM_TASK_REMINDERSTATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: BPM_TKSIGN_DATA                                       */
/*==============================================================*/
CREATE TABLE BPM_TKSIGN_DATA
(
   DATAID              BIGINT NOT NULL COMMENT '主键',
   ACTDEFID             VARCHAR(127),
   ACTINSTID           BIGINT NOT NULL COMMENT '流程实例ID',
   NODENAME             VARCHAR(128) COMMENT '流程节点名称',
   NODEID               VARCHAR(127) NOT NULL,
   TASKID              BIGINT COMMENT '会签任务Id',
   VOTEUSERID           VARCHAR(1000) NOT NULL COMMENT '投票人ID',
   VOTEUSERNAME         VARCHAR(1000) COMMENT '投票人名',
   VOTETIME             DATETIME COMMENT '投票时间',
   ISAGREE              SMALLINT COMMENT '是否同意
            0=弃权票
            1=同意
            2=拒绝
            
            跟task_sign中的decideType是一样',
   CONTENT              VARCHAR(200) COMMENT '投票意见内容',
   SIGNNUMS            BIGINT DEFAULT 1,
   ISCOMPLETED          SMALLINT DEFAULT 0 COMMENT '是否完成
            1=完成
            0=未完成
            
            会签是否结束',
   BATCH                SMALLINT
);

ALTER TABLE BPM_TKSIGN_DATA COMMENT '任务会签数据';

ALTER TABLE BPM_TKSIGN_DATA
   ADD PRIMARY KEY (DATAID);

/*==============================================================*/
/* Table: BPM_USER_CONDITION                                    */
/*==============================================================*/
CREATE TABLE BPM_USER_CONDITION
(
   ID                   BIGINT,
   SETID               BIGINT COMMENT '节点设置ID',
   CONDITIONNAME        VARCHAR(127) COMMENT '规则名称',
   ACTDEFID             VARCHAR(127) COMMENT '实例Id',
   NODEID               VARCHAR(128) COMMENT '节点名称',
   CONDITIONCODE        TEXT COMMENT '条件规则',
   SN                   BIGINT   COMMENT '批次',
   CONDITIONSHOW        TEXT COMMENT '规则显示字符串',
   CONDITIONTYPE        SMALLINT,
   GROUPNO              SMALLINT,
   FORMIDENTITY         VARCHAR(30),
   PARENTACTDEFID       VARCHAR(128) COMMENT '从属父流程定义ID'
);

ALTER TABLE BPM_USER_CONDITION COMMENT '用户选择条件';

/*==============================================================*/
/* Table: OUT_MAIL                                              */
/*==============================================================*/
CREATE TABLE OUT_MAIL
(
   MAILID              BIGINT NOT NULL COMMENT '自增列',
   TITLE                VARCHAR(512) COMMENT '主题',
   CONTENT              TEXT COMMENT '内容',
   SENDERADDRESSES      VARCHAR(128) COMMENT '发件人地址',
   SENDERNAME           VARCHAR(128) COMMENT '发件人地址别名',
   RECEIVERADDRESSES    VARCHAR(2000) COMMENT '收件人地址',
   RECEIVERNAMES        VARCHAR(2000) COMMENT '收件人地址别名',
   CCADDRESSES          VARCHAR(2000) COMMENT '抄送人地址',
   BCCANAMES            VARCHAR(2000) COMMENT '暗送人地址别名',
   BCCADDRESSES         VARCHAR(2000) COMMENT '暗送人地址',
   CCNAMES              VARCHAR(2000) COMMENT '抄送人地址别名',
   EMAILID              VARCHAR(128) COMMENT '邮件ID',
   TYPES               BIGINT COMMENT ' 邮件类型 1:收件箱;2:发件箱;3:草稿箱;4:垃圾箱',
   USERID              BIGINT COMMENT '用户ID',
   ISREPLY             BIGINT COMMENT '是否回复',
   MAILDATE             DATETIME COMMENT '日期',
   FILEIDS              VARCHAR(512) COMMENT '附件ID',
   ISREAD              BIGINT COMMENT '是否已读',
   SETID                BIGINT
);

ALTER TABLE OUT_MAIL COMMENT '外部邮件';

ALTER TABLE OUT_MAIL
   ADD PRIMARY KEY (MAILID);

/*==============================================================*/
/* Table: OUT_MAIL_LINKMAN                                      */
/*==============================================================*/
CREATE TABLE OUT_MAIL_LINKMAN
(
   LINKID              BIGINT NOT NULL COMMENT '主键',
   USERID              BIGINT COMMENT '用户ID',
   MAILID              BIGINT COMMENT '邮件ID',
   SENDTIME             DATETIME COMMENT '送送时间',
   LINKNAME             VARCHAR(20) COMMENT '联系人名称',
   LINKADDRESS          VARCHAR(2000) COMMENT '联系人地址'
);

ALTER TABLE OUT_MAIL_LINKMAN COMMENT '外部邮件最近联系';

ALTER TABLE OUT_MAIL_LINKMAN
   ADD PRIMARY KEY (LINKID);

/*==============================================================*/
/* Table: OUT_MAIL_USER_SETING                                  */
/*==============================================================*/
CREATE TABLE OUT_MAIL_USER_SETING
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   USERID              BIGINT COMMENT '用户ID',
   USERNAME             VARCHAR(128) COMMENT '用户名称',
   MAILADDRESS          VARCHAR(128) COMMENT '外部邮箱地址',
   MAILPASS             VARCHAR(128) COMMENT '外部邮箱密码',
   SMTPHOST             VARCHAR(128) COMMENT 'smt主机',
   SMTPPORT             VARCHAR(64) COMMENT 'smt端口',
   POPHOST              VARCHAR(128) COMMENT 'pop主机',
   POPPORT              VARCHAR(64) COMMENT 'pop端口',
   IMAPHOST             VARCHAR(128) COMMENT 'imap主机',
   IMAPPORT             VARCHAR(128) COMMENT 'imap端口',
   ISDEFAULT            SMALLINT COMMENT '是否默认',
   MAILTYPE             VARCHAR(50) COMMENT '接收邮件服务器类型',
   USESSL			  SMALLINT DEFAULT 0 COMMENT '是否使用SSL认证。0：否；1：是',
   ISVALIDATE		  SMALLINT DEFAULT 1 COMMENT '是否需要身份验证。0：否；1：是',
   ISDELETEREMOTE	  SMALLINT DEFAULT 0 COMMENT '下载时，是否删除远程邮件。0：否；1：是',
   ISHANDLEATTACH	  SMALLINT DEFAULT 1 COMMENT '是否下载附件。0：否；1：是'
);

ALTER TABLE OUT_MAIL_USER_SETING COMMENT '外部邮件用户设置';

ALTER TABLE OUT_MAIL_USER_SETING
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_ACCEPT_IP                                         */
/*==============================================================*/
CREATE TABLE SYS_ACCEPT_IP
(
   ACCEPTID            BIGINT NOT NULL COMMENT '主键',
   TITLE                VARCHAR(128) COMMENT '名称',
   STARTIP              VARCHAR(20) COMMENT '开始IP',
   ENDIP                VARCHAR(20) COMMENT '结束IP',
   REMARK               VARCHAR(200) COMMENT '备注'
);

ALTER TABLE SYS_ACCEPT_IP
   ADD PRIMARY KEY (ACCEPTID);

/*==============================================================*/
/* Table: SYS_AUDIT                                             */
/*==============================================================*/
CREATE TABLE SYS_AUDIT
(
   AUDITID             BIGINT NOT NULL,
   OPNAME               VARCHAR(128) COMMENT '操作名称',
   EXETIME              DATETIME COMMENT '执行时间',
   EXECUTORID          BIGINT COMMENT '执行人ID',
   EXECUTOR             VARCHAR(64) COMMENT '执行人',
   FROMIP               VARCHAR(64) COMMENT 'IP',
   EXEMETHOD            VARCHAR(128) COMMENT '执行方法',
   REQUESTURI           VARCHAR(256) COMMENT '请求URL',
   REQPARAMS            TEXT COMMENT '请求参数',
   OWNERMODEL           VARCHAR(200),
   EXECTYPE             VARCHAR(200),
   ORGID                BIGINT,
   detail               TEXT
);

ALTER TABLE SYS_AUDIT COMMENT '系统审计';

ALTER TABLE SYS_AUDIT
   ADD PRIMARY KEY (AUDITID);

/*==============================================================*/
/* Table: SYS_CALENDAR                                          */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '日历名称',
   MEMO                 VARCHAR(400) COMMENT '描述',
   ISDEFAULT           BIGINT COMMENT '1=默认日历
            0=非默认'
);

ALTER TABLE SYS_CALENDAR COMMENT '系统日历';

ALTER TABLE SYS_CALENDAR
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_CALENDAR_ASSIGN                                   */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR_ASSIGN
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   CANLENDARID         BIGINT COMMENT '日历ID',
   ASSIGNTYPE           SMALLINT COMMENT '分配者类型
            1,用户
            2.组织',
   ASSIGNID            BIGINT COMMENT '分配者ID'
);

ALTER TABLE SYS_CALENDAR_ASSIGN COMMENT '日历分配';

ALTER TABLE SYS_CALENDAR_ASSIGN
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_CALENDAR_SETTING                                  */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR_SETTING
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   CALENDARID          BIGINT COMMENT '日历ID',
   YEARS                SMALLINT COMMENT '年份',
   MONTHS               SMALLINT COMMENT '月份',
   DAYS                 SMALLINT COMMENT '天数',
   TYPE                 SMALLINT COMMENT '上班类型
            1,上班
            2,休息',
   WORKTIMEID          BIGINT NOT NULL COMMENT '班次ID',
   CALDAY               VARCHAR(20)
);

ALTER TABLE SYS_CALENDAR_SETTING COMMENT '日历设置';

ALTER TABLE SYS_CALENDAR_SETTING
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_CODE_TEMPLATE                                     */
/*==============================================================*/
CREATE TABLE SYS_CODE_TEMPLATE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   TEMPLATE_NAME        VARCHAR(200) COMMENT '模版名称',
   HTML                 TEXT COMMENT '模版文件内容',
   MEMO                 VARCHAR(200) COMMENT '模版描述',
   TEMPLATE_ALIAS       VARCHAR(200) COMMENT '模版别名',
   TEMPLATE_TYPE        SMALLINT COMMENT '模版类型1-系统模版，2自定义模版',
   ISSUBNEED            SMALLINT COMMENT '子表是否生成该模版文件0-不需要生成，1-生成 ，默认不生成',
   FILENAME             VARCHAR(200) COMMENT '文件名',
   FILEDIR              VARCHAR(200) COMMENT '文件路径',
   formEdit				SMALLINT,
   formDetail			SMALLINT
);

ALTER TABLE SYS_CODE_TEMPLATE COMMENT '基于自定义表的代码模版管理表';

ALTER TABLE SYS_CODE_TEMPLATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_CONDITION_SCRIPT                                  */
/*==============================================================*/
CREATE TABLE SYS_CONDITION_SCRIPT
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   CLASS_NAME           VARCHAR(400) COMMENT '脚本所在类的类名',
   CLASS_INS_NAME       VARCHAR(200) COMMENT '类实例名',
   METHOD_NAME          VARCHAR(200) COMMENT '方法名',
   METHOD_DESC          VARCHAR(400) COMMENT '方法描述',
   RETURN_TYPE          VARCHAR(50) COMMENT '返回值类型',
   ARGUMENT             TEXT COMMENT '参数信息',
   ENABLE               SMALLINT COMMENT '是否有效'
);

ALTER TABLE SYS_CONDITION_SCRIPT COMMENT '条件脚本';

ALTER TABLE SYS_CONDITION_SCRIPT
   ADD PRIMARY KEY (ID);

   
/*********xcx 2013-12-25 增加别名脚本表	bpmx3.3 begin 增加了脚本表，通过唯一的别名ALIAS_NAME和参数alias来获取脚本并执行，返回相应数据******************/
CREATE TABLE SYS_ALIAS_SCRIPT
  (
    ID             BIGINT NOT NULL COMMENT 'ID',
    ALIAS_NAME     VARCHAR(100) COMMENT '脚本的别名',
    ALIAS_DESC     VARCHAR(100) COMMENT '脚本的描叙',
    CLASS_NAME     VARCHAR(400) COMMENT '调用类的路径',
    CLASS_INS_NAME VARCHAR(200) COMMENT '调用类的对象名',
    METHOD_NAME    VARCHAR(200) COMMENT '调用的方法名',
    METHOD_DESC    VARCHAR(400) COMMENT '调用的方法的描叙',
    RETURN_TYPE    VARCHAR(50)  COMMENT '方法返回类型',
    SCRIPT_TYPE    VARCHAR(50)  COMMENT '脚本类型',
    SCRIPT_COMTEN  TEXT			COMMENT '自定义脚本内容',
    ARGUMENT 	   TEXT COMMENT '方法相关设置',
    ENABLE		   SMALLINT COMMENT '是否禁用',
    IS_RETURN_VALUE      SMALLINT COMMENT '是否设置返回值格式：0：否；1：是',
    RETURN_PARAM_JSON    VARCHAR(2000) COMMENT '返回值格式设置',
    PRIMARY KEY (ID)
)COMMENT='自定义别名脚本表';

/*==============================================================*/
/* Table: SYS_CUSTOM_DISPLAY                                    */
/*==============================================================*/
CREATE TABLE SYS_CUSTOM_DISPLAY
(
   ID                  BIGINT NOT NULL COMMENT 'ID',
   NAME                 VARCHAR(50) COMMENT '名称',
   TEMPLATE             TEXT COMMENT '模板',
   PAGED               BIGINT COMMENT '是否分页',
   PAGESIZE            BIGINT COMMENT '分页大小',
   DESCRIPTION          VARCHAR(500) COMMENT '描述',
   SCRIPT               TEXT COMMENT '脚本',
   DSNAME               VARCHAR(50) COMMENT '数据源名',
   CONDITIONFIELD       TEXT COMMENT '条件字段',
   FIELDSETTING         TEXT COMMENT 'SELECT字段',
   SETTING              TEXT COMMENT '设置',
   DSPTEMPLATE          TEXT COMMENT '显示模板',
   SCRIPTTYPE          BIGINT COMMENT '脚本类型 1:SQL 2:Groovy'
);

ALTER TABLE SYS_CUSTOM_DISPLAY COMMENT '自定义显示模板';

ALTER TABLE SYS_CUSTOM_DISPLAY
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_CUSTOM_PAGE                                       */
/*==============================================================*/
CREATE TABLE SYS_CUSTOM_PAGE
(
   ID                  BIGINT NOT NULL COMMENT 'ID',
   NAME                 VARCHAR(50) COMMENT '名称',
   TITLE                VARCHAR(200) COMMENT '标题',
   DESCRIPTION          VARCHAR(500) COMMENT '描述',
   TEMPLATE             TEXT COMMENT '模板'
);

ALTER TABLE SYS_CUSTOM_PAGE COMMENT '自定义页面';

ALTER TABLE SYS_CUSTOM_PAGE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_DATASOURCE                                        */
/*==============================================================*/
CREATE TABLE SYS_DATASOURCE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '数据源名称',
   ALIAS                VARCHAR(20) COMMENT '别名',
   DRIVERNAME           VARCHAR(100) COMMENT '驱动名称',
   URL                  VARCHAR(100) COMMENT '数据库URL',
   USERNAME             VARCHAR(20) COMMENT '用户名',
   PASSWORD             VARCHAR(20) COMMENT '密码',
   DBTYPE               VARCHAR(20)
);

ALTER TABLE SYS_DATASOURCE COMMENT '系统数据源管理';

ALTER TABLE SYS_DATASOURCE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_DEMENSION                                         */
/*==============================================================*/
CREATE TABLE SYS_DEMENSION
(
   DEMID               BIGINT NOT NULL COMMENT '维度编号',
   DEMNAME              VARCHAR(128) NOT NULL COMMENT '维度名称',
   DEMDESC              VARCHAR(1024) COMMENT '维度描述'
);

ALTER TABLE SYS_DEMENSION COMMENT '人员维度表';

ALTER TABLE SYS_DEMENSION
   ADD PRIMARY KEY (DEMID);



/*==============================================================*/
/* Table: SYS_DIC                                               */
/*==============================================================*/
CREATE TABLE SYS_DIC
(
   DICID               BIGINT NOT NULL COMMENT '主键',
   TYPEID              BIGINT COMMENT '分类ID',
   ITEMKEY              VARCHAR(64) COMMENT '项Key',
   ITEMNAME             VARCHAR(64) NOT NULL COMMENT '项名',
   ITEMVALUE            VARCHAR(128) NOT NULL COMMENT '项值',
   DESCP                VARCHAR(256) COMMENT '描述',
   SN                  BIGINT COMMENT '序号',
   NODEPATH             VARCHAR(100),
   PARENTID             BIGINT
);

ALTER TABLE SYS_DIC COMMENT '数据字典';

ALTER TABLE SYS_DIC
   ADD PRIMARY KEY (DICID);

/*==============================================================*/
/* Table: SYS_ERROR_LOG                                         */
/*==============================================================*/
CREATE TABLE SYS_ERROR_LOG
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   HASHCODE             VARCHAR(40) COMMENT '错误哈希码',
   ACCOUNT              VARCHAR(50) COMMENT '帐号',
   IP                   VARCHAR(30) COMMENT 'ip',
   ERRORURL             VARCHAR(2000) COMMENT '错误地址',
   ERROR                TEXT COMMENT '错误详细信息',
   ERRORDATE            DATETIME COMMENT '时间'
);

ALTER TABLE SYS_ERROR_LOG COMMENT '错误日志表';

ALTER TABLE SYS_ERROR_LOG
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_EXCEL_IMPRULE                                     */
/*==============================================================*/
CREATE TABLE SYS_EXCEL_IMPRULE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   TABLE_NAME           VARCHAR(30) COMMENT '表名称',
   COLUMN_STR           VARCHAR(200) COMMENT '要填充的字段串逗号分隔',
   MARK                 VARCHAR(200) COMMENT '备注',
   IMP_TYPE             SMALLINT COMMENT '0导入现有表,1导入并生成表',
   BUSI_DATE            DATETIME COMMENT '操作日期'
);

ALTER TABLE SYS_EXCEL_IMPRULE COMMENT 'excel导入规则表';

ALTER TABLE SYS_EXCEL_IMPRULE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_FILE                                              */
/*==============================================================*/
CREATE TABLE SYS_FILE
(
   FILEID              BIGINT NOT NULL COMMENT '主键',
   TYPEID              BIGINT COMMENT '分类ID',
   FILENAME             VARCHAR(128) NOT NULL COMMENT '文件名',
   FILEPATH             VARCHAR(128) NOT NULL COMMENT '文件路径',
   CREATETIME           DATETIME NOT NULL COMMENT '创建时间',
   EXT                  VARCHAR(32) COMMENT '扩展名',
   FILETYPE             VARCHAR(32) NOT NULL COMMENT '附件类型
            如：邮件附件',
   NOTE                 VARCHAR(1024) COMMENT '说明',
   CREATORID           BIGINT COMMENT '上传者ID',
   CREATOR              VARCHAR(32) NOT NULL COMMENT '上传者',
   TOTALBYTES          BIGINT DEFAULT 0 COMMENT '总字节数',
   DELFLAG              SMALLINT COMMENT '1=已删除 0=删除',
   FILEBLOB				LONGBLOB COMMENT '附件内容'
);

ALTER TABLE SYS_FILE COMMENT '附件';

ALTER TABLE SYS_FILE
   ADD PRIMARY KEY (FILEID);

/*==============================================================*/
/* Table: SYS_GL_TYPE                                           */
/*==============================================================*/
CREATE TABLE SYS_GL_TYPE
(
   TYPEID              BIGINT NOT NULL COMMENT '分类ID',
   TYPENAME             VARCHAR(128) NOT NULL COMMENT '分类名称',
   NODEPATH             VARCHAR(200) COMMENT '路径',
   DEPTH               BIGINT NOT NULL COMMENT '层次',
   PARENTID            BIGINT COMMENT '父节点',
   CATKEY               VARCHAR(64) COMMENT '分类Key',
   NODEKEY              VARCHAR(64) NOT NULL COMMENT '节点的分类Key',
   SN                  BIGINT NOT NULL COMMENT '序号',
   USERID              BIGINT COMMENT '所属用户
            当为空则代表为公共分类',
   DEPID               BIGINT COMMENT '所属部门',
   TYPE                BIGINT COMMENT '类型
            (0平铺
            ,1树形)',
   ISLEAF               SMALLINT COMMENT '是否叶结点
            1=是
            0=否',
   NODECODE             VARCHAR(20) COMMENT '子编码',
   NODECODETYPE         SMALLINT COMMENT '子编码类型：0手工录入，1自动生成'
);

ALTER TABLE SYS_GL_TYPE COMMENT '总分类表
用于显示树层次结构的分类
可以允许任何层次结构';

ALTER TABLE SYS_GL_TYPE
   ADD PRIMARY KEY (TYPEID);

/*==============================================================*/
/* Table: SYS_IDENTITY                                          */
/*==============================================================*/
CREATE TABLE SYS_IDENTITY
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '名称',
   ALIAS                VARCHAR(20) COMMENT '别名',
   REGULATION           VARCHAR(100) COMMENT '规则',
   GENTYPE          SMALLINT COMMENT '生成类型',
   NOLENGTH            BIGINT COMMENT '流水号长度',
   CURDATE              VARCHAR(10) COMMENT '当前日期',
   INITVALUE           BIGINT COMMENT '初始值',
   CURVALUE            BIGINT COMMENT '当前值',
   STEP                 SMALLINT COMMENT '步长'
);

ALTER TABLE SYS_IDENTITY COMMENT '流水号生成';

ALTER TABLE SYS_IDENTITY
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_JOBLOG                                            */
/*==============================================================*/
CREATE TABLE SYS_JOBLOG
(
   LOGID               BIGINT NOT NULL COMMENT '主键',
   JOBNAME              VARCHAR(50) COMMENT '执行名称',
   TRIGNAME             VARCHAR(50) COMMENT '触发名称',
   STARTTIME            DATETIME COMMENT '开始时间',
   ENDTIME              DATETIME COMMENT '结束时间',
   CONTENT              TEXT COMMENT '日志内容',
   STATE               BIGINT COMMENT '状态',
   RUNTIME             BIGINT COMMENT '运行时间持续时间'
);

ALTER TABLE SYS_JOBLOG COMMENT '后台任务执行日志';

ALTER TABLE SYS_JOBLOG
   ADD PRIMARY KEY (LOGID);

/*==============================================================*/
/* Table: SYS_LOG_SWITCH                                        */
/*==============================================================*/
CREATE TABLE SYS_LOG_SWITCH
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   MODEL                VARCHAR(50) COMMENT '模块名',
   STATUS               SMALLINT COMMENT '状态',
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   CREATOR              VARCHAR(20) COMMENT '创建人',
   CREATORID           BIGINT COMMENT '创建人ID',
   UPDBY                VARCHAR(20) COMMENT '更新人',
   UPDBYID             BIGINT COMMENT '更新人ID',
   MEMO                 VARCHAR(300) COMMENT '备注',
   LASTUPTIME           TIMESTAMP COMMENT '最后更新时间'
);

ALTER TABLE SYS_LOG_SWITCH COMMENT '日志开关';

ALTER TABLE SYS_LOG_SWITCH
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_MESSAGE_LOG                                       */
/*==============================================================*/
CREATE TABLE SYS_MESSAGE_LOG
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   SUBJECT              VARCHAR(100) COMMENT '主题',
   SENDTIME             DATETIME COMMENT '发送时间',
   RECEIVER             VARCHAR(1000) COMMENT '接收者 （多个人用，分隔）',
   MESSAGETYPE         BIGINT COMMENT '消息类型
            1;邮件信息
            2;手机短信
            3;内部消息',
   STATE               BIGINT COMMENT '发送状态 
            1:成功
            0:不成功'
);

ALTER TABLE SYS_MESSAGE_LOG COMMENT '消息日志';

ALTER TABLE SYS_MESSAGE_LOG
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_MSG_READ                                          */
/*==============================================================*/
CREATE TABLE SYS_MSG_READ
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   MESSAGEID           BIGINT COMMENT '消息ID',
   RECEIVERID          BIGINT COMMENT '接收人Id',
   RECEIVER             VARCHAR(20) COMMENT '接收人',
   RECEIVETIME          DATETIME COMMENT '接收时间'
);

ALTER TABLE SYS_MSG_READ COMMENT '接收状态';

ALTER TABLE SYS_MSG_READ
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_MSG_RECEIVER                                      */
/*==============================================================*/
CREATE TABLE SYS_MSG_RECEIVER
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   MESSAGEID           BIGINT COMMENT '消息ID',
   RECEIVETYPE          SMALLINT COMMENT '接收者类型 
			0,接收人
            1,组织架构',
   RECEIVERID          BIGINT COMMENT '接收人ID',
   RECEIVER             VARCHAR(20) COMMENT '接收人'
);

ALTER TABLE SYS_MSG_RECEIVER COMMENT '消息接收者';

ALTER TABLE SYS_MSG_RECEIVER
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_MSG_REPLY                                         */
/*==============================================================*/
CREATE TABLE SYS_MSG_REPLY
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   MESSAGEID           BIGINT COMMENT '消息id',
   CONTENT              TEXT COMMENT '内容',
   REPLYID             BIGINT COMMENT '回复人ID',
   REPLY                VARCHAR(20) COMMENT '回复人',
   REPLYTIME            DATETIME COMMENT '回复时间',
   ISPRIVATE            SMALLINT COMMENT '私密回复'
);

ALTER TABLE SYS_MSG_REPLY COMMENT '消息回复';

ALTER TABLE SYS_MSG_REPLY
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_MSG_SEND                                          */
/*==============================================================*/
CREATE TABLE SYS_MSG_SEND
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   SUBJECT              VARCHAR(400) COMMENT '标题',
   USERID              BIGINT COMMENT '发送人ID',
   USERNAME             VARCHAR(20) COMMENT '发送人',
   MESSAGETYPE          VARCHAR(50) COMMENT '消息类型(使用数据字典）',
   CONTENT              TEXT COMMENT '内容',
   SENDTIME             DATETIME COMMENT '发送时间',
   CANREPLY             SMALLINT COMMENT '不需回复',
   RECEIVERNAME         TEXT
);

ALTER TABLE SYS_MSG_SEND COMMENT '发送消息';

ALTER TABLE SYS_MSG_SEND
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_OFFICE_TEMPLATE                                   */
/*==============================================================*/
CREATE TABLE SYS_OFFICE_TEMPLATE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   SUBJECT              VARCHAR(20) COMMENT '主题',
   TEMPLATETYPE        BIGINT COMMENT '模版类型',
   MEMO                 VARCHAR(200) COMMENT '备注',
   CREATORID           BIGINT COMMENT '创建人ID',
   CREATOR              VARCHAR(20) COMMENT '创建人',
   CREATETIME           DATETIME COMMENT '创建时间',
   PATH                 VARCHAR(200) COMMENT '路径',
   TEMPLATEBLOB			TEXT COMMENT '模板内容'
);

ALTER TABLE SYS_OFFICE_TEMPLATE COMMENT 'office模版';

ALTER TABLE SYS_OFFICE_TEMPLATE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_ORG                                               */
/*==============================================================*/
CREATE TABLE SYS_ORG
(
   ORGID               BIGINT NOT NULL COMMENT '组织ID',
   DEMID               BIGINT COMMENT '维度编号',
   ORGNAME              VARCHAR(128) NOT NULL COMMENT '名称',
   ORGDESC              VARCHAR(500) COMMENT '描述',
   ORGSUPID            BIGINT COMMENT '上级',
   PATH                 VARCHAR(128) COMMENT '路径',
   DEPTH               BIGINT COMMENT '层次',
   ORGTYPE              BIGINT COMMENT '类型
            0=集团
            1=公司
            2=部门
            3=其他组织',
   CREATORID           BIGINT COMMENT '建立人',
   CREATETIME           DATETIME COMMENT '建立时间',
   UPDATEID            BIGINT COMMENT '修改人',
   UPDATETIME           DATETIME COMMENT '修改时间',
   SN                   BIGINT,
   FROMTYPE             SMALLINT COMMENT '数据来源',
   ORGPATHNAME          VARCHAR(2000) COMMENT '组织名称路径',
   isdelete 			SMALLINT DEFAULT 0 COMMENT '删除标志，0正常，1删除',
   code              VARCHAR(128) NOT NULL COMMENT '组织代码，自动继承父级代码',
   COMPANYID            NUMERIC(18,0) COMMENT '公司',
   COMPANY              VARCHAR(20) COMMENT '分公司',
   ORGSTAFF             SMALLINT    COMMENT  '编制人数'
);

ALTER TABLE SYS_ORG COMMENT '组织架构
SYS_ORG';

ALTER TABLE SYS_ORG
   ADD PRIMARY KEY (ORGID);

/*==============================================================*/
/* Table: SYS_ORG_PARAM                                         */
/*==============================================================*/
CREATE TABLE SYS_ORG_PARAM
(
   VALUEID             BIGINT COMMENT '主键ID',
   ORGID               BIGINT COMMENT '组织ID',
   PARAMID             BIGINT COMMENT '属性ID',
   PARAMVALUE           VARCHAR(200) COMMENT '字符串属性值',
   PARAMDATEVALUE       DATETIME COMMENT '日期型属性值',
   PARAMINTVALUE       numeric(18,2) COMMENT '数值型属性值'
);

ALTER TABLE SYS_ORG_PARAM COMMENT '组织参数值';

/*==============================================================*/
/* Table: SYS_ORG_ROLE                                          */
/*==============================================================*/
CREATE TABLE SYS_ORG_ROLE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   ORGID               BIGINT COMMENT '组织ID',
   ROLEID              BIGINT COMMENT '角色ID',
   CANDEL               SMALLINT COMMENT '是否可以删除(在组织授权的不可以删除0,不可以删除,1,可以删除)'
);

ALTER TABLE SYS_ORG_ROLE COMMENT '组织和角色授权';

ALTER TABLE SYS_ORG_ROLE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_ORG_ROLEMANAGE                                    */
/*==============================================================*/
CREATE TABLE SYS_ORG_ROLEMANAGE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   ORGID               BIGINT COMMENT '组织ID',
   ROLEID              BIGINT COMMENT '角色',
   CANDEL               SMALLINT COMMENT '是否可删除'
);

ALTER TABLE SYS_ORG_ROLEMANAGE COMMENT '组织可以授权的角色范围(用于分级授权)';

ALTER TABLE SYS_ORG_ROLEMANAGE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_ORG_TYPE                                          */
/*==============================================================*/
CREATE TABLE SYS_ORG_TYPE
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   DEMID               BIGINT COMMENT '维度ID',
   NAME                 VARCHAR(50) COMMENT '名称',
   LEVELS               SMALLINT COMMENT '级别',
   MEMO                 VARCHAR(100) COMMENT '备注',
   ICON                 VARCHAR(100)
);

ALTER TABLE SYS_ORG_TYPE COMMENT '组织结构类型';

ALTER TABLE SYS_ORG_TYPE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_OVERTIME                                          */
/*==============================================================*/
CREATE TABLE SYS_OVERTIME
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   SUBJECT              VARCHAR(50) COMMENT '主题',
   USERID              BIGINT COMMENT '用户ID',
   STARTTIME            DATETIME COMMENT '开始时间',
   ENDTIME              DATETIME COMMENT '结束时间',
   WORKTYPE             SMALLINT COMMENT '类型
            1.加班
            2.请假',
   MEMO                 VARCHAR(200) COMMENT '备注'
);

ALTER TABLE SYS_OVERTIME COMMENT '加班情况';

ALTER TABLE SYS_OVERTIME
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_PARAM                                             */
/*==============================================================*/
CREATE TABLE SYS_PARAM
(
   PARAMID             BIGINT NOT NULL COMMENT '主键',
   PARAMKEY             VARCHAR(32) COMMENT '属性key值',
   PARAMNAME            VARCHAR(50) COMMENT '属性名称',
   DATATYPE             VARCHAR(20) COMMENT '数据类型',
   EFFECT               SMALLINT COMMENT '参数类型
            1.个人
            2.组织',
   BELONGDEM            NUMERIC(20,0) COMMENT '所属维度',
   SOURCETYPE           VARCHAR(20) COMMENT '数据来源',
   SOURCEKEY            VARCHAR(100) COMMENT '数据来源key',
   DESCRIPTION          VARCHAR(256) COMMENT '参数描述',
   STATUS_              SMALLINT COMMENT '参数状态',
   CATEGORY             VARCHAR(256) COMMENT '参数分类'
);

ALTER TABLE SYS_PARAM COMMENT 'SYS_PARAM【用户或组织自定义属性】';

ALTER TABLE SYS_PARAM
   ADD PRIMARY KEY (PARAMID);

/*==============================================================*/
/* Table: SYS_PAUR                                              */
/*==============================================================*/
CREATE TABLE SYS_PAUR
(
   PAURID              BIGINT NOT NULL,
   PAURNAME             VARCHAR(30) COMMENT '名称',
   ALIASNAME            VARCHAR(30) COMMENT '别名',
   PAURVALUE            VARCHAR(50) COMMENT '值',
   USERID              BIGINT COMMENT '所属用户'
);

ALTER TABLE SYS_PAUR
   ADD PRIMARY KEY (PAURID);

/*==============================================================*/
/* Table: SYS_PERSON_SCRIPT                                     */
/*==============================================================*/
CREATE TABLE SYS_PERSON_SCRIPT
(
   ID                   BIGINT   NOT NULL COMMENT '主键',
   CLASS_NAME           VARCHAR(200) COMMENT '脚本所在类的类名',
   CLASS_INS_NAME       VARCHAR(400) COMMENT '类实例',
   METHOD_NAME          VARCHAR(200) COMMENT '方法名称',
   METHOD_DESC          VARCHAR(200) COMMENT '方法描述',
   RETURN_TYPE          VARCHAR(200) COMMENT '返回类型',
   ARGUMENT             VARCHAR(500) COMMENT '参数信息',
   ENABLE               TINYINT(1) COMMENT '是否有效'
);

ALTER TABLE SYS_PERSON_SCRIPT COMMENT '用户脚本管理';

ALTER TABLE SYS_PERSON_SCRIPT
   ADD PRIMARY KEY (ID);
   
   
CREATE TABLE SYS_JOB(
  JOBID BIGINT COMMENT '职务ID',
  JOBNAME VARCHAR(100) COMMENT '职务名称',
  JOBCODE VARCHAR(100) COMMENT '职务代码',
  JOBDESC VARCHAR(1000) COMMENT '职务描述',
  SETID BIGINT DEFAULT 0 COMMENT '设置码，对应组织ID，说明是这个组织私有的职务，公有职务SETID=0',
  ISDELETE BIGINT DEFAULT 0 COMMENT '是否删除，0正常 1删除',
  GRADE    BIGINT DEFAULT 0 COMMENT '等级',
  PRIMARY KEY (JOBID)
)COMMENT='职务表';
/*==============================================================*/
/* Table: sys_pos                                          */
/*==============================================================*/
CREATE TABLE SYS_POS
(
   POSID                BIGINT NOT NULL,
   POSCODE 				VARCHAR(200)  NOT NULL,
   POSNAME              VARCHAR(100) NOT NULL,
   POSDESC              VARCHAR(200),
   ORGID                BIGINT,
   JOBID                BIGINT,
   ISDELETE             INT DEFAULT 0
);

ALTER TABLE SYS_POS
   ADD PRIMARY KEY (POSID);



/*==============================================================*/
/* Table: SYS_PROFILE                                           */
/*==============================================================*/
CREATE TABLE SYS_PROFILE
(
   ID                  BIGINT NOT NULL,
   USERID              BIGINT COMMENT '用户ID',
   HOMEPAGE             VARCHAR(50) COMMENT '主页',
   SKIN                 VARCHAR(20) COMMENT '皮肤'
);

ALTER TABLE SYS_PROFILE COMMENT '个人个性化信息';

ALTER TABLE SYS_PROFILE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_REPORT_TEMPLATE                                   */
/*==============================================================*/
CREATE TABLE SYS_REPORT_TEMPLATE
(
   REPORTID             BIGINT   NOT NULL  COMMENT '报表编号',
   TITLE                VARCHAR(128) NOT NULL COMMENT '标题',
   DESCP                VARCHAR(500) NOT NULL COMMENT '描述',
   REPORTLOCATION       VARCHAR(128) NOT NULL COMMENT '报表模块的jasper文件的路径',
   CREATETIME           DATETIME NOT NULL COMMENT '创建时间',
   UPDATETIME           DATETIME NOT NULL COMMENT '修改时间',
   REPORTKEY            VARCHAR(128) COMMENT '标识key',
   ISDEFAULTIN          SMALLINT COMMENT '是否缺省
            1=缺省
            0=非缺省',
   TYPEID              BIGINT COMMENT '报表分类ID'
);

ALTER TABLE SYS_REPORT_TEMPLATE COMMENT '报表模板
report_template';

ALTER TABLE SYS_REPORT_TEMPLATE
   ADD PRIMARY KEY (REPORTID);

/*==============================================================*/
/* Table: SYS_RES                                               */
/*==============================================================*/
CREATE TABLE SYS_RES
(
   RESID               BIGINT NOT NULL COMMENT '资源主键',
   RESNAME              VARCHAR(128) NOT NULL COMMENT '资源名称',
   ALIAS                VARCHAR(128) COMMENT '别名（系统中唯一)',
   SN                  BIGINT COMMENT '同级排序',
   ICON                 VARCHAR(100) COMMENT '图标',
   PARENTID            BIGINT COMMENT '父ID',
   DEFAULTURL           VARCHAR(256) COMMENT '默认地址',
   ISFOLDER             SMALLINT COMMENT '栏目',
   ISDISPLAYINMENU      SMALLINT COMMENT '显示到菜单',
   ISOPEN               SMALLINT COMMENT '默认打开',
   SYSTEMID            BIGINT COMMENT '子系统ID',
   ISNEWOPEN 			SMALLINT COMMENT '是否打开新窗口：0否,1是',
   PATH                 VARCHAR(500) COMMENT '资源路径'
);

ALTER TABLE SYS_RES COMMENT '子系统资源';

ALTER TABLE SYS_RES
   ADD PRIMARY KEY (RESID);

/*==============================================================*/
/* Table: SYS_RESURL                                            */
/*==============================================================*/
CREATE TABLE SYS_RESURL
(
   RESURLID            BIGINT NOT NULL COMMENT '主键',
   RESID               BIGINT COMMENT '资源ID',
   NAME                 VARCHAR(100) COMMENT '名称',
   URL                  VARCHAR(200) COMMENT '资源URL'
);

ALTER TABLE SYS_RESURL COMMENT '资源URL';

ALTER TABLE SYS_RESURL
   ADD PRIMARY KEY (RESURLID);

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
CREATE TABLE SYS_ROLE
(
   ROLEID              BIGINT NOT NULL,
   SYSTEMID            BIGINT COMMENT '系统ID',
   ALIAS                VARCHAR(128) COMMENT '角色别名',
   ROLENAME             VARCHAR(128) NOT NULL COMMENT '角色名',
   MEMO                 VARCHAR(256) COMMENT '备注',
   ALLOWDEL             SMALLINT COMMENT '允许删除',
   ALLOWEDIT            SMALLINT COMMENT '允许编辑(0,不允许,1,允许)',
   ENABLED              SMALLINT COMMENT '是否启用
            (0,禁止,1,启用)'
);

ALTER TABLE SYS_ROLE COMMENT '系统角色表';

ALTER TABLE SYS_ROLE
   ADD PRIMARY KEY (ROLEID);

/*==============================================================*/
/* Table: SYS_ROLE_RES                                          */
/*==============================================================*/
CREATE TABLE SYS_ROLE_RES
(
   ROLERESID           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '角色资源Id',
   ROLEID               BIGINT,
   RESID               BIGINT COMMENT '资源主键',
   SYSTEMID            BIGINT COMMENT '系统ID'
);

ALTER TABLE SYS_ROLE_RES COMMENT '角色资源映射';


/*==============================================================*/
/* Table: SYS_SCRIPT                                            */
/*==============================================================*/
CREATE TABLE SYS_SCRIPT
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '名称',
   SCRIPT               TEXT COMMENT '脚本',
   CATEGORY             VARCHAR(50) COMMENT '脚本分类',
   MEMO                 VARCHAR(200) COMMENT '备注'
);

ALTER TABLE SYS_SCRIPT COMMENT '系统脚本节点';

ALTER TABLE SYS_SCRIPT
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_SEAL                                              */
/*==============================================================*/
CREATE TABLE SYS_SEAL
(
   SEALID              BIGINT NOT NULL COMMENT '主键',
   SEALNAME             VARCHAR(128) COMMENT '印章名',
   SEALPATH             VARCHAR(128) COMMENT '印章路径',
   BELONGID            BIGINT COMMENT '印章持有者ID',
   BELONGNAME           VARCHAR(128) COMMENT '印章持有者姓名',
   ATTACHMENTID        BIGINT COMMENT '印章附件',
   SHOWIMAGEID          BIGINT   COMMENT '印章图片'
);

ALTER TABLE SYS_SEAL COMMENT '电子印章';

ALTER TABLE SYS_SEAL
   ADD PRIMARY KEY (SEALID);

/*==============================================================*/
/* Table: SYS_SEAL_RIGHT                                        */
/*==============================================================*/
CREATE TABLE SYS_SEAL_RIGHT
(
   ID                   BIGINT   NOT NULL,
   SEALID               BIGINT   COMMENT '印章ID',
   RIGHTTYPE            VARCHAR(20) COMMENT '授权类型',
   RIGHTID              BIGINT   COMMENT '被授权者ID',
   RIGHTNAME            VARCHAR(100)   COMMENT '被授权者名称',
   CREATEUSER           BIGINT COMMENT '创建人',
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   CONTROLTYPE 			SMALLINT COMMENT '控件类型 印章控件:0 office控件:1'
);

ALTER TABLE SYS_SEAL_RIGHT COMMENT '印章授权表';

ALTER TABLE SYS_SEAL_RIGHT
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_SUBSYSTEM                                         */
/*==============================================================*/
CREATE TABLE SYS_SUBSYSTEM
(
   SYSTEMID            BIGINT NOT NULL COMMENT '主键',
   SYSNAME              VARCHAR(50) NOT NULL COMMENT '系统名称',
   ALIAS                VARCHAR(20) COMMENT '别名(系统中唯一)',
   LOGO                 VARCHAR(100) COMMENT '系统的图标',
   DEFAULTURL           VARCHAR(50) COMMENT '系统首页地址',
   MEMO                 VARCHAR(200) COMMENT '备注',
   CREATETIME           DATETIME COMMENT '创建时间',
   CREATOR              VARCHAR(20) COMMENT '创建人',
   ALLOWDEL             SMALLINT COMMENT '允许删除',
   NEEDORG              SMALLINT COMMENT '选择组织架构
            (0,不需要选择,1,需要选择)
            如果用户只属于一个组织读取组织后，放到当前上下文。多个的话则需要选择。
            ',
   ISACTIVE             SMALLINT COMMENT '是否激活',
   ISLOCAL              SMALLINT,
   HOMEPAGE             VARCHAR(256)
);

ALTER TABLE SYS_SUBSYSTEM COMMENT '子系统管理';

ALTER TABLE SYS_SUBSYSTEM
   ADD PRIMARY KEY (SYSTEMID);

/*==============================================================*/
/* Table: SYS_TABLE_MANAGE                                      */
/*==============================================================*/
CREATE TABLE SYS_TABLE_MANAGE
(
   ID                  BIGINT NOT NULL,
   NAME                 VARCHAR(50) COMMENT '名称',
   ALIAS                VARCHAR(50) COMMENT '别名',
   STYLE               BIGINT COMMENT '样式：0-列表，1-树形',
   NEEDPAGE            BIGINT COMMENT '是否需要分页：0-不分页，1-分页',
   ISTABLE             BIGINT COMMENT '是否是表：0-视图，1-表',
   OBJNAME              VARCHAR(50) COMMENT '对象名',
   DISPLAYFIELD         TEXT COMMENT '显示字段(JSON)',
   CONDITIONFIELD       TEXT COMMENT '条件字段(JSON)',
   DSNAME               VARCHAR(50) COMMENT '数据源名',
   DSALIAS              VARCHAR(50) COMMENT '数据源别名',
   PAGESIZE            BIGINT COMMENT '分页大小',
   TEMPLATEID          BIGINT COMMENT '数据模板ID',
   DSPTEMPLATE          TEXT COMMENT '显示模板',
   EDITABLE            BIGINT COMMENT '允许编辑 0：不可编辑，1：可编辑',
   PARAMETERS           TEXT COMMENT '自定义参数',
   CONDITIONTYPE        INT
);

ALTER TABLE SYS_TABLE_MANAGE
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_TEMPLATE                                          */
/*==============================================================*/
CREATE TABLE SYS_TEMPLATE
(
   TEMPLATEID          BIGINT NOT NULL COMMENT '主键Id',
   NAME                 VARCHAR(50) COMMENT '模版名称',
   HTMLCONTENT          VARCHAR(500) COMMENT 'html模版',
   PLAINCONTENT           VARCHAR(500) COMMENT '普通模版',
   ISDEFAULT            SMALLINT COMMENT '系统默认',
   USETYPE              SMALLINT COMMENT '用途',
   TITLE                VARCHAR(200) COMMENT '模版标题'
);

ALTER TABLE SYS_TEMPLATE COMMENT '系统模版管理';

ALTER TABLE SYS_TEMPLATE
   ADD PRIMARY KEY (TEMPLATEID);

/*==============================================================*/
/* Table: SYS_TYPE_KEY                                          */
/*==============================================================*/
CREATE TABLE SYS_TYPE_KEY
(
   TYPEID              BIGINT NOT NULL,
   TYPEKEY              VARCHAR(64) NOT NULL,
   TYPENAME             VARCHAR(128),
   FLAG                 BIGINT,
   SN                   BIGINT,
   TYPE                BIGINT COMMENT '0=平铺结构
            1=树型结构'
);

ALTER TABLE SYS_TYPE_KEY COMMENT '系统分类键';

ALTER TABLE SYS_TYPE_KEY
   ADD PRIMARY KEY (TYPEID);

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
CREATE TABLE SYS_USER
(
   USERID              BIGINT NOT NULL,
   FULLNAME             VARCHAR(127) COMMENT '姓名',
   ACCOUNT              VARCHAR(20) NOT NULL COMMENT '帐号',
   PASSWORD             VARCHAR(50) NOT NULL COMMENT '密码',
   ISEXPIRED            SMALLINT COMMENT '是否过期',
   ISLOCK               SMALLINT COMMENT '是否锁定',
   CREATETIME           DATETIME COMMENT '创建时间',
   STATUS               SMALLINT COMMENT '状态
            1=激活
            0=禁用
            -1=删除',
   EMAIL                VARCHAR(128) COMMENT '邮箱',
   MOBILE               VARCHAR(32) COMMENT '手机',
   PHONE                VARCHAR(32) COMMENT '电话',
   SEX                  VARCHAR(2) COMMENT '1=男
            0=女',
   PICTURE              VARCHAR(300),
   ENTRYDATE            DATETIME  COMMENT  '入职日期',
   USERSTATUS           VARCHAR(32) COMMENT '员工状态',
   FROMTYPE             SMALLINT COMMENT '数据来源'
);

ALTER TABLE SYS_USER COMMENT '用户表';

ALTER TABLE SYS_USER
   ADD PRIMARY KEY (USERID);



/*==============================================================*/
/* Table: SYS_USER_PARAM                                        */
/*==============================================================*/
CREATE TABLE SYS_USER_PARAM
(
   VALUEID             BIGINT NOT NULL COMMENT '主键ID',
   USERID              BIGINT COMMENT '用户ID',
   PARAMID             BIGINT COMMENT '属性ID',
   PARAMVALUE           VARCHAR(200) COMMENT '属性值',
   PARAMDATEVALUE       DATETIME COMMENT '日期类型属性值',
   PARAMINTVALUE       numeric(18,2) COMMENT '数值型属性值'
);

ALTER TABLE SYS_USER_PARAM COMMENT '用户自定义参数';

ALTER TABLE SYS_USER_PARAM
   ADD PRIMARY KEY (VALUEID);

/*==============================================================*/
/* Table: SYS_USER_POS                                          */
/*==============================================================*/
CREATE TABLE SYS_USER_POS(
  USERPOSID BIGINT,
  ORGID BIGINT,
  POSID BIGINT,
  USERID BIGINT,
  ISPRIMARY BIGINT,
  ISCHARGE BIGINT,
  ISDELETE BIGINT DEFAULT 0,
  JOBID BIGINT,
  PRIMARY KEY (USERPOSID)
)COMMENT='用户岗位表';
/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
CREATE TABLE SYS_USER_ROLE
(
   USERROLEID          BIGINT NOT NULL COMMENT '用户角色Id',
   ROLEID              BIGINT COMMENT '角色ID',
   USERID              BIGINT COMMENT '用户ID'
);

ALTER TABLE SYS_USER_ROLE COMMENT '用户角色映射表';

ALTER TABLE SYS_USER_ROLE
   ADD PRIMARY KEY (USERROLEID);

/*==============================================================*/
/* Table: SYS_USER_UNDER                                        */
/*==============================================================*/
CREATE TABLE SYS_USER_UNDER
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   USERID              BIGINT COMMENT '用户ID',
   UNDERUSERID         BIGINT COMMENT '下属用户ID',
   UNDERUSERNAME        VARCHAR(50) COMMENT '下属用户名'
);

ALTER TABLE SYS_USER_UNDER COMMENT '下属管理';

ALTER TABLE SYS_USER_UNDER
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_VACATION                                          */
/*==============================================================*/
CREATE TABLE SYS_VACATION
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '假日名称',
   YEARS                SMALLINT COMMENT '年份',
   STATTIME             DATETIME COMMENT '开始时间',
   ENDTIME              DATETIME COMMENT '结束时间'
);

ALTER TABLE SYS_VACATION COMMENT '法定假期设置';

ALTER TABLE SYS_VACATION
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_WORKTIME                                          */
/*==============================================================*/
CREATE TABLE SYS_WORKTIME
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   SETTINGID           BIGINT COMMENT '设置ID',
   STARTTIME            VARCHAR(10) COMMENT '开始时间',
   ENDTIME              VARCHAR(10) COMMENT '结束时间',
   MEMO                 VARCHAR(200) COMMENT '备注'
);

ALTER TABLE SYS_WORKTIME COMMENT '班次时间';

ALTER TABLE SYS_WORKTIME
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_WORKTIME_SETTING                                  */
/*==============================================================*/
CREATE TABLE SYS_WORKTIME_SETTING
(
   ID                  BIGINT NOT NULL COMMENT '主键',
   NAME                 VARCHAR(50) COMMENT '班次名',
   MEMO                 VARCHAR(200) COMMENT '描述'
);

ALTER TABLE SYS_WORKTIME_SETTING COMMENT '班次设置';

ALTER TABLE SYS_WORKTIME_SETTING
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_DB_ID                                             */
/*==============================================================*/
CREATE TABLE SYS_DB_ID
(
   ID                   SMALLINT NOT NULL COMMENT '计算机编号',
   INCREMENTAL         BIGINT COMMENT 'ID增量',
   BOUND               BIGINT COMMENT '边界值'
);

ALTER TABLE SYS_DB_ID COMMENT '系统ID生成表';

ALTER TABLE SYS_DB_ID
   ADD PRIMARY KEY (ID);

create table bpm_common_ws_set
(
  id       BIGINT not null COMMENT '主键',
  alias    varchar(200) not null COMMENT '别名',
  wsdl_url varchar(400) COMMENT 'wsdl地址',
  document text COMMENT 'webservice设置',
  primary key (id)
)COMMENT='通用webservice调用设置';

create table bpm_common_ws_params
(
  id          BIGINT not null COMMENT '主键',
  setid       BIGINT not null COMMENT 'webservice设置ID',
  name        varchar(200) COMMENT '参数名',
  param_type  SMALLINT COMMENT '参数类型',
  description varchar(400) COMMENT '参数说明',
  primary key (id)
)COMMENT='通用webservice调用参数';

/* Table: SYS_REPORT                                            */
/*==============================================================*/
CREATE TABLE SYS_REPORT
(
   REPORTID             BIGINT COMMENT '报表ID',
   TYPEID               BIGINT COMMENT '类型ID',
   TITLE                VARCHAR(128) COMMENT '标题',
   DESCP                VARCHAR(200) COMMENT '描述',
   FILEPATH             VARCHAR(128) COMMENT '报表文件相对路径',
   FILENAME             VARCHAR(128) COMMENT '文件名',
   CREATETIME           TIMESTAMP COMMENT '创建时间',
   STATUS               SMALLINT DEFAULT 0 COMMENT '状态，1：已处理；0：未处理',
   DSNAME               VARCHAR(50) COMMENT '数据源别名',
   PARAMS               VARCHAR(500) DEFAULT '[]' COMMENT '报表参数',
   EXT                  VARCHAR(20) DEFAULT 'jrxml' COMMENT '报表文件后缀名',
   REALSQL              TEXT COMMENT '报表的SQL语句'
);

ALTER TABLE SYS_REPORT COMMENT 'Jasperreport报表';


create table BUS_QUERY_RULE
(
  id           BIGINT not null COMMENT '主键',
  tablename    VARCHAR(128) COMMENT '表名',
  needpage     INTEGER default 1 COMMENT '是否需要分页：0-不分页，1-分页',
  pagesize     BIGINT COMMENT '分页大小',
  isquery      INTEGER default 0 COMMENT '初始是否进行查询：0-是，1-否',
  isFilter     INTEGER default 0,
  displayfield TEXT COMMENT '显示字段',
  filterfield  TEXT COMMENT '过滤器字段',
  sortfield    TEXT COMMENT '排序字段',
  exportfield  TEXT COMMENT '导出字段',
  printfield   TEXT COMMENT '打印字段',
  createtime   TIMESTAMP COMMENT '创建时间',
  createby     BIGINT COMMENT '创建人ID',
  updatetime   TIMESTAMP COMMENT '更新时间',
  updateby     BIGINT COMMENT '更新人ID',
  PRIMARY KEY (ID)
)comment='高级查询规则';

create table BUS_QUERY_FILTER
(
  id             BIGINT not null COMMENT '主键',
  ruleid         BIGINT COMMENT '规则ID',
  tablename      VARCHAR(256) COMMENT '表名',
  filtername     VARCHAR(256) COMMENT '过滤名称',
  filterdesc     TEXT COMMENT '过滤描述',
  filterkey      VARCHAR(256) COMMENT '过滤条件Key',
  queryparameter TEXT COMMENT '查询参数',
  sortparameter  TEXT COMMENT '排序参数',
  isshare        SMALLINT default 0 COMMENT '是否共享',
  createtime     TIMESTAMP COMMENT '创建时间',
  createby       BIGINT COMMENT '创建人ID',
  PRIMARY KEY (ID)
)comment='查询过滤';
  
create table BUS_QUERY_SHARE
(
  id         BIGINT not null COMMENT '主键',
  filterid   BIGINT COMMENT '过滤ID',
  shareright TEXT COMMENT '共享权限',
  sharerid   BIGINT COMMENT '共享人ID',
  createtime TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (ID)
)COMMENT='查询过滤共享';



CREATE TABLE SYS_WS_DATA_TEMPLATE (
	ID 			BIGINT NOT NULL COMMENT '主键',
	NAME 		VARCHAR(500) NULL  COMMENT '列表名称',
	SERVICEID 	BIGINT NULL COMMENT '展示数据的模版',
	TEMPLATE 	TEXT NULL COMMENT '主键',
	SCRIPT 		TEXT NULL COMMENT '解析返回的XML的脚本' ,
    PRIMARY KEY (ID)
)COMMENT='webservice数据模板展示';

CREATE TABLE OUT_MAIL_ATTACHMENT (
	FILEID BIGINT NOT NULL COMMENT '主键,文件ID',
	FILENAME VARCHAR(128) NULL COMMENT '文件名',
	FILEPATH VARCHAR(128) NULL COMMENT '文件存放路径',
	MAILID BIGINT NULL COMMENT '邮件ID',
	PRIMARY KEY (FILEID)
)COMMENT='外部邮件附件表';


CREATE TABLE BPM_DEF_AUTHORIZE  (
   ID  BIGINT NOT NULL COMMENT '主键',
   AUTHORIZE_DESC  VARCHAR(512) COMMENT '流程授权说明',
   PRIMARY KEY (ID)
)COMMENT='流程授权主表';



CREATE TABLE BPM_DEF_USER  (
   ID  BIGINT NOT NULL  COMMENT '主键',
   AUTHORIZE_ID  BIGINT NOT NULL  COMMENT '流程授权主表ID',
   OWNER_ID   BIGINT  NOT NULL  COMMENT '授权对象ID',
   OWNER_NAME  VARCHAR(256) COMMENT '授权对象名称',
   RIGHT_TYPE  VARCHAR(40) COMMENT '授权对象类型',
   PRIMARY KEY (ID)
)COMMENT='流程授权中的用户子表';


CREATE TABLE BPM_DEF_ACT  (
   ID   BIGINT  NOT NULL COMMENT '主键',
   AUTHORIZE_ID  BIGINT NOT NULL COMMENT '流程授权主表ID',
   DEF_KEY   VARCHAR(64)  NOT NULL COMMENT '授权流程KEY',
   DEF_NAME  VARCHAR(256) COMMENT '授权流程名称',
   RIGHT_CONTENT  VARCHAR(512) COMMENT '授权内容',
   PRIMARY KEY (ID)
)COMMENT='流程授权中流程子表';


CREATE TABLE SYS_URL_PERMISSION (
  ID_ BIGINT NOT NULL COMMENT '主键',
  DESCP_ VARCHAR(500)  NULL COMMENT '描述',
  URL_ VARCHAR(2000)  NULL COMMENT '拦截地址',
  PARAMS_ VARCHAR(500)  NULL COMMENT '拦截参数',
  ENABLE_ SMALLINT DEFAULT '1' COMMENT '是否启用；0：禁用，1：启用',
  PRIMARY KEY  (ID_)
)COMMENT='URL地址拦截管理';

CREATE TABLE SYS_URL_RULES (
  ID_ BIGINT(20) NOT NULL COMMENT '主键',
  SCRIPT_ MEDIUMTEXT COMMENT 'groovy脚本',
  ENABLE_ SMALLINT DEFAULT '1' COMMENT '是否启用；0：禁用；1：启用',
  SYS_URL_ID_ BIGINT  NULL COMMENT 'URL地址拦截管理ID',
  DESCP_ VARCHAR(500)  NULL COMMENT '描述',
  SORT_ SMALLINT DEFAULT '1' COMMENT '排序',
  PRIMARY KEY  (ID_)
)COMMENT='URL地址拦截脚本管理';

CREATE TABLE SYS_QUERY_SQL (
  ID bigint NOT NULL COMMENT '主键',
  SQL_  VARCHAR(2000)  COMMENT 'sql语句',
  NAME VARCHAR(100) COMMENT '名称',
  DSALIAS VARCHAR(50) COMMENT '数据源别名',
  URL_PARAMS VARCHAR(2000) COMMENT 'url参数',
  CATEGORYID BIGINT(20) COMMENT '分类id',
  PRIMARY KEY  (ID)
)COMMENT='自定义列表';

CREATE TABLE SYS_QUERY_FIELD
(
   ID                   bigint NOT NULL,
   SQL_ID               bigint NOT NULL COMMENT 'sqlId',
   NAME                 VARCHAR(200) COMMENT '名称',
   TYPE                 VARCHAR(50) COMMENT '类型',
   FIELD_DESC           VARCHAR(2000) COMMENT '字段描述',
   IS_SHOW              INT DEFAULT 1 COMMENT '是否显示',
   IS_SEARCH            INT DEFAULT 0 COMMENT '是否查询字段',
   CONTROL_TYPE         INT COMMENT '控制类型',
   CONTROL_CONTENT      VARCHAR(200) COMMENT '控制内容',
   FORMAT               VARCHAR(400) COMMENT '格式'
);

ALTER TABLE SYS_QUERY_FIELD COMMENT '查询文件';

ALTER TABLE SYS_QUERY_FIELD
   ADD PRIMARY KEY (ID);

CREATE TABLE SYS_QUERY_SETTING
(
   ID                   bigint NOT NULL,
   SQL_ID               bigint,
   NAME                 VARCHAR(50) COMMENT '名称',
   ALIAS                VARCHAR(50) COMMENT '别名',
   STYLE                INT COMMENT '显示类型',
   NEED_PAGE            INT COMMENT '需要分页',
   PAGE_SIZE            INT COMMENT '分页大小',
   IS_QUERY             INT DEFAULT 1 COMMENT '是否默认查询',
   TEMPLATE_ALIAS       VARCHAR(50) COMMENT '模板别名',
   TEMPLATE_HTML        TEXT COMMENT '模板html',
   DISPLAY_FIELD        TEXT COMMENT '显示字段',
   FILTER_FIELD         TEXT COMMENT '过滤字段',
   CONDITION_FIELD      TEXT COMMENT '查询字段',
   SORT_FIELD           TEXT COMMENT '排序字段',
   EXPORT_FIELD         TEXT COMMENT '导出字段',
   MANAGE_FIELD         TEXT COMMENT '管理字段'
);

ALTER TABLE SYS_QUERY_SETTING COMMENT '查询设置';

ALTER TABLE SYS_QUERY_SETTING
   ADD PRIMARY KEY (ID);
   
CREATE TABLE BPM_MOBILE_FORM
(
   ID                   bigint NOT NULL,
   FORMID               bigint,
   FORMKEY              bigint,
   HTML                 TEXT,
   TEMPLATE             TEXT,
   GUID                 VARCHAR(128),
   ISDEFAULT            INT,
   FORMJSON             TEXT
);

ALTER TABLE BPM_MOBILE_FORM
   ADD PRIMARY KEY (ID);
/*==============================================================*/
/* Table: SYS_ORG_AUTH                                          */
/*==============================================================*/
CREATE TABLE SYS_ORG_AUTH
(
   ID                   BIGINT NOT NULL COMMENT '主键',
   USER_ID              BIGINT NOT NULL COMMENT '管理员ID',
   ORG_ID               BIGINT NOT NULL COMMENT '组织ID',
   DIM_ID               BIGINT NOT NULL COMMENT '维度ID',
   ORG_PERMS            VARCHAR(255) COMMENT '针对子用户组的添加、更新和删除',
   USER_PERMS           VARCHAR(255) COMMENT '针对用户与组关系的添加、更新和删除'
);

ALTER TABLE SYS_ORG_AUTH COMMENT '组织授权';

ALTER TABLE SYS_ORG_AUTH
   ADD PRIMARY KEY (ID);

/*==============================================================*/
/* Table: SYS_AUTH_ROLE                                         */
/*==============================================================*/
CREATE TABLE SYS_AUTH_ROLE
(
   ID                   BIGINT NOT NULL COMMENT '主键',
   AUTH_ID              BIGINT COMMENT '授权ID',
   ROLE_ID              BIGINT COMMENT '角色ID'
);

ALTER TABLE SYS_AUTH_ROLE COMMENT '授权角色';

ALTER TABLE SYS_AUTH_ROLE
   ADD PRIMARY KEY (ID);
   
/*==============================================================*/
/* 可配置数据源:SYS_DATA_SOURCE ,SYS_DATA_SOURCE_DEF               */
/*==============================================================*/
CREATE TABLE SYS_DATA_SOURCE (
  ID_ DECIMAL(18,0) NOT NULL,
  NAME_ VARCHAR(64),
  ALIAS_ VARCHAR(64),
  DB_TYPE_ VARCHAR(64),
  SETTING_JSON_ LONGTEXT,
  INIT_ON_START_ SMALLINT(6),
  ENABLED_ SMALLINT(6),
  CLASS_PATH_ VARCHAR(128),
  INIT_METHOD_ VARCHAR(128),
  CLOSE_METHOD_ VARCHAR(128),
  PRIMARY KEY (ID_),
  UNIQUE KEY UNIQUE_COLUMN (NAME_,ALIAS_)
);
CREATE TABLE SYS_DATA_SOURCE_DEF (
  ID_ DECIMAL(18,0) NOT NULL,
  NAME_ VARCHAR(64) NOT NULL,
  CLASS_PATH_ VARCHAR(128) NOT NULL,
  SETTING_JSON_ LONGTEXT,
  INIT_METHOD_ VARCHAR(64),
  IS_SYSTEM_ SMALLINT(6),
  CLOSE_METHOD_ VARCHAR(64),
  PRIMARY KEY (ID_),
  UNIQUE KEY UNIQUE_COLUMNS (CLASS_PATH_,NAME_)
);

CREATE TABLE BPM_DEF_AUTH_TYPE  (
   ID   BIGINT  NOT NULL COMMENT '主键',
   AUTHORIZE_ID  BIGINT NOT NULL COMMENT '流程授权主表ID',
   AUTHORIZE_TYPE  VARCHAR(68)  NOT NULL COMMENT '流程授权类型',
   PRIMARY KEY (ID)
);
ALTER TABLE BPM_DEF_AUTH_TYPE COMMENT'流程授权类型表';
  

CREATE TABLE BPM_FORM_DEF_HI
(
   HISID                BIGINT NOT NULL COMMENT '主键',
   FORMDEFID            BIGINT NOT NULL COMMENT '表单ID',
   CATEGORYID           BIGINT COMMENT '表单分类',
   FORMKEY              VARCHAR(200) COMMENT '表单KEY',
   SUBJECT              VARCHAR(128) COMMENT '表单标题',
   FORMDESC             VARCHAR(200) COMMENT '描述',
   HTML                 TEXT COMMENT '定义HTML',
   TEMPLATE             TEXT COMMENT 'FREEMAKER模版',
   ISDEFAULT            SMALLINT COMMENT '是否缺省',
   VERSIONNO            BIGINT COMMENT '版本号',
   TABLEID              BIGINT COMMENT '表ID',
   ISPUBLISHED          SMALLINT COMMENT '是否发布',
   PUBLISHEDBY          VARCHAR(20) COMMENT '发布人',
   PUBLISHTIME          DATETIME COMMENT '发布时间',
   TABTITLE             VARCHAR(500) COMMENT 'TAB标题',
   DESIGNTYPE           SMALLINT COMMENT '设计类型',
   TEMPLATESID          VARCHAR(300) COMMENT '模板表对应ID',
   CREATEBY             BIGINT COMMENT '创建人ID',
   CREATOR              VARCHAR(50) COMMENT '创建人',
   CREATETIME           DATETIME COMMENT '创建时间',
   PRIMARY KEY (HISID)
);

ALTER TABLE BPM_FORM_DEF_HI COMMENT '流程表单定义历史记录';

CREATE TABLE BPM_ASSIGN_USERS
(
   ID                   BIGINT NOT NULL COMMENT '主键',
   RUNID                BIGINT COMMENT '流程执行实例ID',
   DEFKEY               VARCHAR(128) COMMENT '流程定义Key',
   NODEID               VARCHAR(128) COMMENT '节点ID',
   NODENAME             VARCHAR(56) COMMENT '节点名称',
   USERID               VARCHAR(20) COMMENT '执行人ID',
   USERNAME             VARCHAR(127) COMMENT '执行人名称',
   STARTTIME            DATETIME COMMENT '开始时间',
   ENDTIME              DATETIME COMMENT '结束时间',
   PRIMARY KEY (ID)
);

ALTER TABLE BPM_ASSIGN_USERS COMMENT '发起人设置流程执行人表';

CREATE TABLE `bpm_node_sql` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `NAME` varchar(100),
  `DSALIAS` varchar(100) COMMENT '数据源别名',
  `ACTDEFID` varchar(100) COMMENT '流程id',
  `NODEID` varchar(50) COMMENT '节点ID',
  `ACTION_` varchar(50) COMMENT '触发时机：\r\nSubmit\r\nAgree\r\nOpposite\r\nReject\r\ndelete\r\nsave\r\nend\r\n',
  `SQL_` varchar(4000) COMMENT 'SQL语句',
  `DESC_` varchar(400) COMMENT '描叙',
  PRIMARY KEY (`ID`)
);


/*==============================================================*/
/* Table: BPM_SUBTABLE_SORT                                     */
/*==============================================================*/
CREATE TABLE BPM_SUBTABLE_SORT
(
   ID                   bigint(20) NOT NULL COMMENT '主键',
   ACTDEFKEY            VARCHAR(120) COMMENT '流程定义key',
   TABLENAME            VARCHAR(120) COMMENT '子表名称',
   FIELDSORT            VARCHAR(500) COMMENT '字段名，json数据',
   PRIMARY KEY (ID)
);

CREATE TABLE SYS_WORD_TEMPLATE (
  ID_ 			bigint(18) NOT NULL,
  NAME_ 		varchar(200) COMMENT '报表名称',
  ALIAS_ 		varchar(100) COMMENT '报表别名',
  FILE_ID_ 		bigint(18) COMMENT 'word模板附件ID',
  CREATETIME_ 	datetime COMMENT '创建时间',
  TYPE_ 		varchar(20) COMMENT '类型',
  TABLE_ID_ 	bigint(18) COMMENT '表ID',
  TABLE_NAME_ 	varchar(128) COMMENT '表名',
  DS_ALIAS_ 	varchar(50) COMMENT '数据源别名',
  SQL_ 			text COMMENT 'Sql语句',
  PRIMARY KEY (ID_)
);

CREATE TABLE SYS_INDEX_LAYOUT (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	NAME			VARCHAR(100)	 	COMMENT '布局名称',
	MEMO     		VARCHAR(256)     	COMMENT '布局描述',
	TEMPLATE_HTML   LONGTEXT      		COMMENT '模版内容',
	SN				INT					COMMENT '排序',
	PRIMARY KEY (ID)
);
ALTER TABLE SYS_INDEX_LAYOUT COMMENT '首页布局';


CREATE TABLE SYS_INDEX_LAYOUT_MANAGE (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	NAME			VARCHAR(100)	 	COMMENT '布局名称',
	MEMO     		VARCHAR(256)     	COMMENT '布局描述',
	ORG_ID			BIGINT	  		 	COMMENT '组织ID',
	TEMPLATE_HTML   LONGTEXT      			COMMENT '模版内容',
	DESIGN_HTML  	LONGTEXT				COMMENT '设计模版',
	IS_DEF  		SMALLINT			COMMENT '是否是默认',
	PRIMARY KEY (ID)
);

ALTER TABLE SYS_INDEX_LAYOUT_MANAGE COMMENT '布局管理';

CREATE TABLE SYS_INDEX_MY_LAYOUT (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	USER_ID			BIGINT	  		 	COMMENT '用户ID',
	TEMPLATE_HTML   LONGTEXT      			COMMENT '模版内容',
	DESIGN_HTML  	LONGTEXT				COMMENT '设计模版',
	LAYOUT_ID  		BIGINT				COMMENT '布局管理ID',
	PRIMARY KEY (ID)
);

ALTER TABLE SYS_INDEX_MY_LAYOUT COMMENT '我的布局';

CREATE TABLE SYS_ORG_TACTIC (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	ORG_TACTIC		SMALLINT	  		COMMENT '策略（0、无策略1、按照级别 2、手工选择，3、按照级别+手工选择）',
	DEM_ID  		BIGINT			COMMENT '维度ID',
	ORG_LEVEL  		BIGINT			COMMENT '组织级别',
	ORG_SELECT_ID 	LONGTEXT			COMMENT '组织选择ID',
	PRIMARY KEY (ID)
);

ALTER TABLE SYS_ORG_TACTIC COMMENT '组织策略';


/*==============================================================*/
/* Table: SYS_INDEX_COLUMN                                      */
/*==============================================================*/
CREATE TABLE SYS_INDEX_COLUMN (
  ID 			BIGINT NOT NULL	COMMENT '主键',
  NAME 			VARCHAR(100)  	COMMENT '栏目名称',
  ALIAS 		VARCHAR(100)  	COMMENT '栏目别名',
  CATALOG 		BIGINT	  		COMMENT '栏目分类',
  COL_TYPE 		SMALLINT  		COMMENT '栏目类型（0：一般栏目、1：图表栏目、2、滚动栏目）',
  DATA_MODE 	SMALLINT  		COMMENT '数据加载方式(0:服务方法;1:自定义查询)',
  DATA_FROM 	VARCHAR(100)  	COMMENT '数据来源',
  DS_ALIAS 		VARCHAR(100)  	COMMENT '数据别名',
  DS_NAME 		VARCHAR(100)  	COMMENT '数据源名称',
  COL_HEIGHT 	INT  			COMMENT '栏目高度',
  COL_URL 		VARCHAR(100)  	COMMENT '栏目URL',
  TEMPLATE_HTML LONGTEXT 		COMMENT '栏目模版',
  IS_PUBLIC 	SMALLINT  		COMMENT '是否公共栏目',
  ORG_ID         BIGINT 			COMMENT '所属组织ID',
  SUPPORT_REFESH SMALLINT  		COMMENT '是否支持刷新',
  REFESH_TIME 	INT  			COMMENT '刷新时间',
  SHOW_EFFECT 	SMALLINT  		COMMENT '展示效果',
  MEMO 			VARCHAR(256)  	COMMENT '描述',
  DATA_PARAM           VARCHAR(2000) COMMENT '数据参数',
  NEEDPAGE      SMALLINT        COMMENT  '首页分页',
  PRIMARY KEY (ID)
);
ALTER TABLE SYS_INDEX_COLUMN COMMENT '首页布局';

/*==============================================================*/
/* Table: SYS_POPUP_REMIND                            */
/*==============================================================*/
CREATE TABLE sys_popup_remind (
	ID bigint(20) NOT NULL,
	SUBJECT varchar(200) COMMENT '主题 弹框时的信息',
	URL varchar(200) COMMENT '点击该提醒的跳转URL',
	SQL_ varchar(2000) COMMENT 'SQL 返回数据大小',
	DSALIAS varchar(20) COMMENT '数据源别名',
	SN decimal(18,0) COMMENT '排序',
	ENABLED int(10) unsigned zerofill COMMENT '是否启用',
  	CREATETIME datetime,
  	CREATOR varchar(50) COMMENT '创建人 json.id,json.name',
  	DESC_ varchar(300),
	PRIMARY KEY (ID)
);
/*==============================================================*/
/* Table: SYS_OBJ_RIGHTS                            */
/*==============================================================*/
CREATE TABLE sys_obj_rights (
  	ID bigint(20) NOT NULL COMMENT '主键',
  	OBJ_TYPE varchar(20)  COMMENT '对象类型',
  	OBJECT_ID bigint(20)  COMMENT '权限对象ID',
  	OWNER_ID bigint(20)  COMMENT '授权人ID',
  	OWNER varchar(50)  COMMENT '授权人',
  	RIGHT_TYPE varchar(10)  COMMENT '权限类型',
  	PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: SYS_PROPERTY                                          */
/*==============================================================*/
CREATE TABLE SYS_PROPERTY
(
   ID                   BIGINT NOT NULL COMMENT '主键',
   NAME_                VARCHAR(128) COMMENT '名字',
   ALIAS_               VARCHAR(128) COMMENT '别名',
   VALUE_               VARCHAR(256) COMMENT '值',
   GROUP_               VARCHAR(128) COMMENT '分组',
   SN_                  SMALLINT,
   MEMO                 VARCHAR(200) COMMENT '增加参数描述',
   PRIMARY KEY (ID)
);


CREATE TABLE BPM_BATCH_APPROVAL (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	DEF_KEY			varchar(128)	  	COMMENT '流程定义Key',
	NODE_ID   		varchar(128)      	COMMENT '节点ID',
	TABLE_ID  		BIGINT				COMMENT '自定义表ID',
	FIELD_JSON  	LONGTEXT			COMMENT '字段设置',
	PRIMARY KEY (ID)
) COMMENT='流程批量审批定义设置';			

CREATE TABLE sys_bulletin_column (
    ID                          bigint(20) NOT NULL COMMENT '主键',
    Name                        varchar(100) DEFAULT NULL COMMENT '栏目名',
    Alias varchar(20)           DEFAULT NULL COMMENT '别名',
    isPublic smallint(6)        DEFAULT NULL COMMENT '是否集团',
    TenantId bigint(20)         DEFAULT NULL COMMENT '组织ID',
    creatorId bigint(20)        DEFAULT NULL COMMENT '创建人ID',
    Creator varchar(50)         DEFAULT NULL COMMENT '创建人',
    createTime datetime         DEFAULT NULL COMMENT '创建时间',
    Status  smallint(6)         DEFAULT NULL COMMENT '是否可用1、可用，2不可用',
    PRIMARY KEY (ID),
    UNIQUE KEY IDX_SYS_BULLETIN_COLUMN (Alias) USING BTREE
)COMMENT='公告栏目';

CREATE TABLE sys_bulletin (
    ID                          bigint(20) NOT NULL COMMENT '主键',
    Subject                     varchar(200) DEFAULT NULL COMMENT '主题',
    COLUMNID                    bigint(20) DEFAULT NULL COMMENT '栏目ID',
    CONTENT                     longtext COMMENT '内容',
    creatorId                   bigint(20) DEFAULT NULL COMMENT '创建人id',
    Creator                     varchar(50) DEFAULT NULL COMMENT '创建人',
    createTime                  datetime DEFAULT NULL COMMENT '创建时间',
    attachMent                  varchar(400) DEFAULT NULL COMMENT '附件',
    PRIMARY KEY (ID)
) COMMENT='公告表';


CREATE TABLE SYS_PLAN (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	TASKNAME		VARCHAR(200)	  	COMMENT '任务名称',
	SUBMITID  		BIGINT	    		COMMENT '提交人ID',
	SUBMITOR  		VARCHAR(50)			COMMENT '提交人',
	CHARGEID  		BIGINT			    COMMENT '负责人ID',
	CHARGE  		VARCHAR(50)			COMMENT '负责人',
	STARTTIME 	    TIMESTAMP			    COMMENT '开始时间',
	ENDTIME 	    TIMESTAMP			    COMMENT '结束时间',
	PROJECTNAME     VARCHAR(200)	  	COMMENT '项目名称',
	DOC             VARCHAR(400)	  	COMMENT '相关文档',
	CUSTOMERID  	BIGINT		        COMMENT '相关客户ID',
	CUSTOMER  		VARCHAR(200)		COMMENT '相关客户',
	RUNID  	     	BIGINT			    COMMENT '相关工单ID',
	RUNNAME         VARCHAR(200)	  	COMMENT '工单名称',
	RATE  	     	BIGINT      		COMMENT '完成进度',
	CREATETIME      TIMESTAMP        	  	COMMENT '创建时间',
	DESCRIPTION     VARCHAR(2000)		COMMENT '任务内容',
	PRIMARY KEY (ID)
) ;

ALTER TABLE SYS_PLAN COMMENT '日程表';


/*==============================================================*/
/* Table: SYS_PLAN_PARTICIPANTS  日程参与表                                  */
/*==============================================================*/
CREATE TABLE SYS_PLAN_PARTICIPANTS (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	PLANID  		BIGINT	    		COMMENT '日程ID',
	PARTICIPANTID  	BIGINT			    COMMENT '参与者ID',
	PARTICIPANT  	VARCHAR(50)			COMMENT '参与者',
	PRIMARY KEY (ID)
) ;

ALTER TABLE SYS_PLAN_PARTICIPANTS COMMENT '日程参与表';


/*==============================================================*/
/* Table: SYS_PLAN_SUBSCRIBE  日程订阅表                                  */
/*==============================================================*/
CREATE TABLE SYS_PLAN_SUBSCRIBE (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	PLANID  		BIGINT	    		COMMENT '日程ID',
	SUBSCRIBEID  	BIGINT			    COMMENT '订阅者ID',
	SUBSCRIBE   	VARCHAR(50)		    COMMENT '订阅者',
	SUBSCRIBETIME   TIMESTAMP        	COMMENT '订阅时间',
	PRIMARY KEY (ID)
) ;

ALTER TABLE SYS_PLAN_SUBSCRIBE COMMENT '日程订阅表';


/*==============================================================*/
/* Table: SYS_PLAN_EXCHANGE  日程交流表                                  */
/*==============================================================*/
CREATE TABLE SYS_PLAN_EXCHANGE (
	ID          	BIGINT   NOT NULL	COMMENT '主键',
	PLANID  		BIGINT	    		COMMENT '日程ID',
	SUBMITID  		BIGINT	    		COMMENT '提交人ID',
	SUBMITOR  		VARCHAR(50)			COMMENT '提交人',
	DOC             VARCHAR(400)	  	COMMENT '相关文档',
	CONTENT  		VARCHAR(1000)		COMMENT '交流内容',
	CREATETIME      TIMESTAMP        	COMMENT '提交时间',
	PRIMARY KEY (ID)
) ;
ALTER TABLE SYS_PLAN_EXCHANGE COMMENT '日程交流表';


/*==============================================================*/
/* Table: SYS_TRANS_DEF                                         */
/*==============================================================*/
CREATE TABLE SYS_TRANS_DEF
(
   ID                   NUMERIC(18,0) NOT NULL,
   NAME                 VARCHAR(100),
   SELECTSQL            TEXT,
   UPDATESQL            TEXT,
   STATE                SMALLINT,
   CREATORID            NUMERIC(18,0),
   CREATOR              VARCHAR(50),
   CREATETIME           DATETIME,
   LOGCONTENT           VARCHAR(500),
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_TRANS_DEF COMMENT '批量权限转移';

/*==============================================================*/
/* Table: SYS_OBJ_LOG                                           */
/*==============================================================*/
CREATE TABLE SYS_OBJ_LOG
(
   ID                   NUMERIC(18,0) NOT NULL,
   OPERATOR_ID          NUMERIC(18,0),
   OPERATOR             VARCHAR(255),
   CREATE_TIME          DATETIME,
   NAME                 VARCHAR(255),
   CONTENT              TEXT,
   OBJ_TYPE             VARCHAR(255),
   PARAM                VARCHAR(255),
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_OBJ_LOG COMMENT '常用日志';


CREATE TABLE bpm_form_dialog_combinate (
  ID bigint(20) NOT NULL DEFAULT '0',
  NAME varchar(200) ,
  ALIAS varchar(100) ,
  WIDTH int(11) ,
  HEIGHT int(11) ,
  TREE_DIALOG_ID bigint(20) ,
  TREE_DIALOG_NAME varchar(200) ,
  LIST_DIALOG_ID bigint(20) ,
  LIST_DIALOG_NAME varchar(200) ,
  FIELD varchar(1000)  COMMENT '树数据返回数据对应列表数据的查询条件',
  PRIMARY KEY (ID),
  UNIQUE KEY alias_unique (ALIAS)
);

CREATE TABLE form_def_tree (
  ID bigint(20) NOT NULL DEFAULT '0',
  FORM_DEF_ID bigint(20) ,
  NAME varchar(100) ,
  ALIAS varchar(50) ,
  TREE_ID varchar(100) ,
  PARENT_ID varchar(100) ,
  DISPLAY_FIELD varchar(100) ,
  LOAD_TYPE smallint(6) ,
  ROOT_ID varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID),
  UNIQUE KEY ALIAS_UNIQUE (ALIAS)
);
ALTER TABLE SYS_OBJ_LOG COMMENT '常用日志';


CREATE TABLE form_def_combinate (
  ID bigint(20) NOT NULL DEFAULT '0',
  NAME varchar(200) ,
  ALIAS varchar(100) ,
  TREE_DIALOG_ID bigint(20) COMMENT '树对话框ID',
  TREE_DIALOG_NAME varchar(200) ,
  FORM_DEF_ID bigint(20) COMMENT '表单ID 注意 表单需要有业务数据模板才行',
  FORM_DEF_NAME varchar(200) ,
  FIELD longtext COMMENT '树数据返回数据对应列表数据的查询条件',
  PRIMARY KEY (ID),
  UNIQUE KEY alias_unique (ALIAS)
);

ALTER TABLE form_def_combinate COMMENT '表单组合';

CREATE TABLE BPM_NEWFLOW_TRIGGER
(
   ID                   NUMERIC(20,0) NOT NULL,
   NAME                 VARCHAR(255),
   NODEID               VARCHAR(255),
   ACTION               VARCHAR(50),
   FLOWKEY              VARCHAR(255),
   TRIGGERFLOWKEY       VARCHAR(255),
   TRIGGERFLOWNAME      VARCHAR(500),
   JSONMAPING           TEXT,
   TRIGGERJSON          TEXT,
   NOTE                 VARCHAR(2000),
   PRIMARY KEY (ID)
);

ALTER TABLE BPM_NEWFLOW_TRIGGER COMMENT '触发新流程';

CREATE TABLE SYS_BUS_EVENT
(
   ID                   INT NOT NULL DEFAULT 0 COMMENT '主键',
   FORMKEY              VARCHAR(50) DEFAULT NULL COMMENT '表单key',
   JS_PRE_SCRIPT        VARCHAR(4000) DEFAULT '' COMMENT 'JS前置脚本',
   JS_AFTER_SCRIPT      VARCHAR(2000) DEFAULT '' COMMENT 'js后置脚本',
   PRE_SCRIPT           VARCHAR(2000) DEFAULT NULL COMMENT 'JAVA前置脚本',
   AFTER_SCRIPT         VARCHAR(2000) DEFAULT NULL COMMENT 'java后置脚本',
   PRIMARY KEY (ID)
);
ALTER TABLE SYS_BUS_EVENT COMMENT '业务数据按钮保存配置';

CREATE TABLE BPM_NODEMSG_TEMPLATE
(
   ID                   NUMERIC(20,0) NOT NULL,
   DEFID                NUMERIC(20,0),
   PARENTDEFID          NUMERIC(20,0),
   NODEID               VARCHAR(20),
   TITLE                VARCHAR(200),
   HTML                 TEXT,
   TEXT                 TEXT,
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: SYS_EXCEL_TEMP                                        */
/*==============================================================*/
CREATE TABLE SYS_EXCEL_TEMP
(
   ID                   NUMERIC(20,0) NOT NULL COMMENT '主键',
   TEMP_CODE            VARCHAR(100) NOT NULL COMMENT '模板代码',
   TEMP_NAME            VARCHAR(100) COMMENT '模板名称',
   TEMP_DES             VARCHAR(500) COMMENT '模板填写说明',
   TEMP_DES_HEIGHT      NUMERIC(4,0) COMMENT '填写说明行高',
   MEMO                 VARCHAR(500) COMMENT '备注',
   TEMP_DATA_SAMPLE     TEXT COMMENT '模板样例数据',
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: SYS_EXCEL_TEMP_DETAIL                                 */
/*==============================================================*/
CREATE TABLE SYS_EXCEL_TEMP_DETAIL
(
   ID                   NUMERIC(20,0) NOT NULL COMMENT '主键',
   TEMP_ID              NUMERIC(20,0) COMMENT '模板ID',
   COLUMN_NAME          VARCHAR(100) COMMENT '列名称',
   COLUMN_TYPE          NUMERIC(4,0) COMMENT '数据类型',
   COLUMN_LEN           NUMERIC(4,0) COMMENT '列长',
   SHOW_INDEX           NUMERIC(4,0) COMMENT '显示顺序',
   PRIMARY KEY (ID)
);


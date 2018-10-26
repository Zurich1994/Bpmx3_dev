CREATE TABLE BPM_AGENT_CONDITION  (
   ID                   NUMBER(18)                      NOT NULL,
   SETTINGID            NUMBER(18),
   CON                 VARCHAR2(1000),
   MEMO                 VARCHAR2(200),
   AGENTID              NUMBER(18),
   AGENT                VARCHAR2(100),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_AGENT_CONDITION IS
'条件代理的配置';

/*==============================================================*/
/* TABLE: BPM_AGENT_DEF                                         */
/*==============================================================*/
CREATE TABLE BPM_AGENT_DEF  (
   ID                   NUMBER(18)                      NOT NULL,
   SETTINGID            NUMBER(18),
   FLOWKEY              VARCHAR2(50),
   FLOWNAME             VARCHAR2(200),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_AGENT_DEF IS
'代理的流程列表';

/*==============================================================*/
/* TABLE: BPM_AGENT_SETTING                                     */
/*==============================================================*/
CREATE TABLE BPM_AGENT_SETTING  (
   ID                   NUMBER(18)                         NOT NULL,
   AUTHID               NUMBER(18),
   AUTHNAME             VARCHAR2(100),
   CREATETIME           TIMESTAMP,
   STARTDATE            TIMESTAMP,
   ENDDATE              TIMESTAMP null,
   ENABLED              SMALLINT,
   AUTHTYPE             SMALLINT,
   AGENTID              NUMBER(18),
   AGENT                VARCHAR2(100),
   FLOWKEY              VARCHAR2(100),
   FLOWNAME             VARCHAR2(100),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_AGENT_SETTING IS
'代理设定';

/*==============================================================*/
/* TABLE: BPM_APPROVAL_ITEM                                     */
/*==============================================================*/
CREATE TABLE BPM_APPROVAL_ITEM  (
   ITEMID            NUMBER(18)                         NOT NULL,
   USERID              NUMBER(18),
   DEFKEY           VARCHAR2(128),
   TYPEID             NUMBER(18),
   TYPE           		SMALLINT,
   EXPRESSION           VARCHAR2(3000),
   PRIMARY KEY (ITEMID)
);

COMMENT ON TABLE BPM_APPROVAL_ITEM IS
'任务审批常用语设置';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.ITEMID IS 
'常用语主键';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.USERID IS 
'用户ID';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.DEFKEY IS 
'流程定义Key';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.TYPEID IS 
'流程分类ID';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.TYPE IS 
'常用语类型';
COMMENT ON COLUMN BPM_APPROVAL_ITEM.EXPRESSION IS 
'常用语内容';

/*==============================================================*/
/* TABLE: BPM_BUS_LINK                                          */
/*==============================================================*/
CREATE TABLE BPM_BUS_LINK
(
  BUS_ID         NUMBER(18) NOT NULL,
  BUS_FORM_TABLE VARCHAR2(255),
  BUS_PK         NUMBER(18),
  BUS_PKSTR      VARCHAR2(50),
  BUS_DEF_ID     NUMBER(18),
  BUS_FLOW_RUNID NUMBER(18),
  BUS_CREATOR_ID NUMBER(18),
  BUS_CREATOR    NVARCHAR2(50),
  BUS_ORG_ID     NUMBER(18),
  BUS_ORG_NAME   NVARCHAR2(200),
  BUS_CREATETIME TIMESTAMP(6),
  BUS_UPDID      NUMBER(18),
  BUS_UPD        NVARCHAR2(50),
  BUS_STATUS     NUMBER(1),
  BUS_UPDTIME    TIMESTAMP(6),
  BUS_PROC_INST_ID		NUMBER(18)
)
	PARTITION BY LIST(BUS_FORM_TABLE)( 
	PARTITION P_TABLE_UNCREATED VALUES ('TABLE_UNCREATED')
)
;

comment on table BPM_BUS_LINK  is '业务数据关联表';
comment on column BPM_BUS_LINK.bus_id  is '主键';
comment on column BPM_BUS_LINK.bus_form_table  is '对应关联表名'; 
comment on column BPM_BUS_LINK.bus_pk  is '对应关联表主键';
comment on column BPM_BUS_LINK.bus_pkstr  is '对应关联表主键(字符串)';
comment on column BPM_BUS_LINK.bus_def_id  is '流程定义ID';
comment on column BPM_BUS_LINK.bus_flow_runid  is '流程运行ID';
comment on column BPM_BUS_LINK.bus_creator_id  is '发起人ID';
comment on column BPM_BUS_LINK.bus_creator  is '发起人';
comment on column BPM_BUS_LINK.bus_org_id  is '发起组织';
comment on column BPM_BUS_LINK.bus_org_name  is '组织名称';
comment on column BPM_BUS_LINK.bus_createtime  is '发起时间';
comment on column BPM_BUS_LINK.bus_updid  is '最后更新人';
comment on column BPM_BUS_LINK.bus_upd  is '最后更新人';
comment on column BPM_BUS_LINK.bus_updtime  is '最后更新时间';
alter table BPM_BUS_LINK  add constraint PK_BUS_LINK primary key (BUS_ID);


/*==============================================================*/
/* TABLE: BPM_COMMU_RECEIVER                                    */
/*==============================================================*/
CREATE TABLE BPM_COMMU_RECEIVER  (
   ID                   NUMBER(18)                         NOT NULL,
   OPINIONID            NUMBER(18),
   RECEVIERID           NUMBER(18),
   RECEIVERNAME         VARCHAR2(100),
   STATUS               SMALLINT,
   CREATETIME           TIMESTAMP,
   RECEIVETIME          TIMESTAMP,
   FEEDBACKTIME         TIMESTAMP,
   TASKID               NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_COMMU_RECEIVER IS
'通知任务接收人';

/*==============================================================*/
/* TABLE: BPM_DEFINITION                                        */
/*==============================================================*/
CREATE TABLE BPM_DEFINITION  (
   DEFID              NUMBER(18)                         NOT NULL,
   TYPEID             NUMBER(18),
   SUBJECT            VARCHAR2(256)                   NOT NULL,
   DEFKEY             VARCHAR2(128)                   NOT NULL,
   TASKNAMERULE       VARCHAR2(512),
   DESCP              VARCHAR2(1024),
   STATUS             SMALLINT,
   DEFXML             CLOB,
   ACTDEPLOYID        NUMBER(18),
   ACTDEFKEY          VARCHAR2(255),
   ACTDEFID           VARCHAR2(128),
   CREATETIME         DATE,
   UPDATETIME         DATE,
   CREATEBY           NUMBER(18),
   UPDATEBY           NUMBER(18),
   REASON             VARCHAR2(512),
   VERSIONNO          NUMBER(18),
   PARENTDEFID        NUMBER(18),
   ISMAIN             SMALLINT,
   TOFIRSTNODE        NUMBER(18),
   SHOWFIRSTASSIGNEE  SMALLINT,
   CANCHOICEPATH        VARCHAR2(500),
   ISUSEOUTFORM       SMALLINT,
   FORMDETAILURL        VARCHAR2(200),
   ALLOWFINISHEDCC    SMALLINT,
   SUBMITCONFIRM      SMALLINT,
   ALLOWDIVERT        SMALLINT,
   INFORMSTART        VARCHAR2(20),
   INFORMTYPE         VARCHAR2(20),
   ATTACHMENT         NUMBER(18),
   SAMEEXECUTORJUMP   SMALLINT,
   ALLOWREFER         SMALLINT,
   INSTANCEAMOUNT     SMALLINT,
   ALLOWFINISHEDDIVERT SMALLINT,
   ISPRINTFORM        SMALLINT,
   DIRECTSTART        SMALLINT,
   CCMESSAGETYPE      VARCHAR2(100),
   TESTSTATUSTAG      VARCHAR2(100),
   ALLOWDELDRAF		SMALLINT,
   ALLOWMOBILE          INTEGER                        DEFAULT 1,
   SKIPSETTING          VARCHAR2(50),
   PRIMARY KEY (DEFID)
);

COMMENT ON TABLE BPM_DEFINITION IS
'流程定义扩展CODE:DEFTN';

/*==============================================================*/
/* TABLE: BPM_DEF_VARS                                          */
/*==============================================================*/
CREATE TABLE BPM_DEF_VARS  (
   VARID              NUMBER(18)                         NOT NULL,
   DEFID              NUMBER(18),
   VARNAME            VARCHAR2(128)                   NOT NULL,
   VARKEY             VARCHAR2(128),
   VARDATATYPE        VARCHAR2(64),
   DEFVALUE           VARCHAR2(256),
   NODENAME           VARCHAR2(256),
   NODEID             VARCHAR2(256),
   ACTDEPLOYID        NUMBER(18),
   VARSCOPE           VARCHAR2(64),
   PRIMARY KEY (VARID)
);

COMMENT ON TABLE BPM_DEF_VARS IS
'流程变量定义';

/*==============================================================*/
/* TABLE: BPM_EXE_STACK                                         */
/*==============================================================*/
CREATE TABLE BPM_EXE_STACK  (
   STACKID            NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(64),
   NODEID             VARCHAR2(128)                   NOT NULL,
   NODENAME           VARCHAR2(256),
   STARTTIME          TIMESTAMP,
   ENDTIME            TIMESTAMP,
   ASSIGNEES          VARCHAR2(1024),
   ISMULTITASK        SMALLINT,
   PARENTID           NUMBER(18),
   ACTINSTID          NUMBER(18),
   TASKIDS            VARCHAR2(512),
   NODEPATH           VARCHAR2(1024),
   DEPTH              NUMBER(18),
   TASKTOKEN          VARCHAR2(128),
   PRIMARY KEY (STACKID)
);

COMMENT ON TABLE BPM_EXE_STACK IS
'流程执行堆栈树';

/*==============================================================*/
/* TABLE: BPM_FORM_DEF                                          */
/*==============================================================*/
CREATE TABLE BPM_FORM_DEF  (
   FORMDEFID          NUMBER(18)                         NOT NULL,
   CATEGORYID         NUMBER(18),
   FORMKEY            VARCHAR2(128),
   SUBJECT            VARCHAR2(128),
   FORMDESC           VARCHAR2(200),
   HTML               CLOB,
   TEMPLATE           CLOB,
   ISDEFAULT          SMALLINT,
   VERSIONNO          NUMBER(18),
   TABLEID              NUMBER(18),
   ISPUBLISHED        SMALLINT,
   PUBLISHEDBY        VARCHAR2(20),
   PUBLISHTIME        DATE,
   TABTITLE           VARCHAR2(500),
   DESIGNTYPE         SMALLINT,
   TEMPLATESID          VARCHAR2(300),
   CREATEBY           NUMBER(18),
   CREATOR            VARCHAR2(50),
   CREATETIME         TIMESTAMP,
   PRIMARY KEY (FORMDEFID)
);

/*==============================================================*/
/* TABLE: BPM_FORM_DIALOG                                       */
/*==============================================================*/
CREATE TABLE BPM_FORM_DIALOG  (
   ID                   NUMBER(18)                         NOT NULL,
   NAME                 VARCHAR2(50),
   ALIAS                VARCHAR2(50),
   STYLE                SMALLINT,
   WIDTH                NUMBER(18),
   HEIGHT               NUMBER(18),
   ISSINGLE             SMALLINT,
   NEEDPAGE             SMALLINT,
   ISTABLE              SMALLINT,
   OBJNAME              VARCHAR2(50),
   DISPLAYFIELD         VARCHAR2(2000),
   CONDITIONFIELD       VARCHAR2(2000),
   RESULTFIELD          VARCHAR2(1000),
   DSNAME               VARCHAR2(50),
   DSALIAS              VARCHAR2(50),
   PAGESIZE             SMALLINT,
   SORTFIELD             VARCHAR2(200),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_FORM_DIALOG IS
'通用表单对话框';

/*==============================================================*/
/* TABLE: BPM_FORM_FIELD                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_FIELD  (
   FIELDID            NUMBER(18)                         NOT NULL,
   TABLEID            NUMBER(18),
   FIELDNAME          VARCHAR2(128)                   NOT NULL,
   FIELDTYPE          VARCHAR2(128)                   NOT NULL,
   ISREQUIRED         SMALLINT,
   ISLIST             SMALLINT                       DEFAULT 1,
   ISQUERY            SMALLINT                       DEFAULT 1,
   FIELDDESC          VARCHAR2(128),
   CHARLEN            NUMBER(18),
   INTLEN             NUMBER(18),
   DECIMALLEN         NUMBER(18),
   DICTTYPE           VARCHAR2(100),
   ISDELETED          SMALLINT,
   VALIDRULE          VARCHAR2(64),
   ORIGINALNAME       VARCHAR2(128),
   SN                 NUMBER(18),
   VALUEFROM          SMALLINT,
   SCRIPT             VARCHAR2(1000),
   CONTROLTYPE        SMALLINT,
   ISHIDDEN           SMALLINT,
   ISFLOWVAR          SMALLINT,
   SERIALNUM          VARCHAR2(20),
   OPTIONS            VARCHAR2(1000),
   CTLPROPERTY        VARCHAR2(2000),
   ISALLOWMOBILE      SMALLINT,
   ISWEBSIGN            SMALLINT,
   ISREFERENCE			SMALLINT,
   PRIMARY KEY (FIELDID)
);

COMMENT ON TABLE BPM_FORM_FIELD IS
'表单字段';

/*==============================================================*/
/* TABLE: BPM_FORM_QUERY                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_QUERY  (
   ID                   NUMBER(18)                         NOT NULL,
   NAME                 VARCHAR2(50),
   ALIAS                VARCHAR2(50),
   OBJ_NAME             VARCHAR2(50),
   NEEDPAGE             NUMBER(18),
   CONDITIONFIELD       VARCHAR2(2000),
   RESULTFIELD          VARCHAR2(2000),
   DSNAME               VARCHAR2(50),
   DSALIAS              VARCHAR2(50),
   PAGESIZE             NUMBER(18)                        DEFAULT 10,
   ISTABLE              NUMBER(18),
   SORTFIELD			VARCHAR2(2000),
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* TABLE: BPM_FORM_RIGHTS                                       */
/*==============================================================*/
CREATE TABLE BPM_FORM_RIGHTS  (
   ID                 NUMBER(18)                         NOT NULL,
   FORMKEY            VARCHAR(50),
   NAME               VARCHAR2(128),
   PERMISSION         VARCHAR2(2000),
   TYPE               SMALLINT,
   NODEID             VARCHAR2(60),
   ACTDEFID           VARCHAR2(60),
   PARENTACTDEFID     VARCHAR2(128),
   PLATFORM             NUMBER(18)                      DEFAULT 1,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_FORM_RIGHTS IS
'表单权限';

/*==============================================================*/
/* TABLE: BPM_FORM_RULE                                         */
/*==============================================================*/
CREATE TABLE BPM_FORM_RULE  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   REGULATION         VARCHAR2(100),
   MEMO               VARCHAR2(100),
   TIPINFO            VARCHAR2(100),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_FORM_RULE IS
'表单认证规则';

/*==============================================================*/
/* TABLE: BPM_FORM_RUN                                          */
/*==============================================================*/
CREATE TABLE BPM_FORM_RUN  (
   ID                 NUMBER(18)                         NOT NULL,
   FORMDEFID          NUMBER(18),
   FORMDEFKEY         VARCHAR2(50),
   ACTINSTANCEID      VARCHAR2(64),
   ACTDEFID           VARCHAR2(64),
   ACTNODEID          VARCHAR2(64),
   RUNID              NUMBER(18),
   SETTYPE            SMALLINT,
   FORMTYPE           SMALLINT,
   FORMURL            VARCHAR2(255),
   MOBILEFORMID         NUMBER(18),
   MOBILEFORMKEY        NUMBER(18),
   MOBILEFORMURL        VARCHAR2(255),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_FORM_RUN IS
'流程表单运行情况';

/*==============================================================*/
/* TABLE: BPM_FORM_TABLE                                        */
/*==============================================================*/
CREATE TABLE BPM_FORM_TABLE  (
   TABLEID            NUMBER(18)                         NOT NULL,
   TABLENAME          VARCHAR2(128)                   NOT NULL,
   TABLEDESC          VARCHAR2(128),
   ISMAIN             SMALLINT,
   MAINTABLEID        NUMBER(18),
   VERSIONNO          NUMBER(18),
   ISDEFAULT          SMALLINT,
   ISPUBLISHED        SMALLINT,
   PUBLISHEDBY        VARCHAR2(100),
   PUBLISHTIME        DATE,
   ISEXTERNAL         SMALLINT,
   DSALIAS            VARCHAR2(50),
   DSNAME             VARCHAR2(50),
   RELATION           VARCHAR2(500),
   KEYTYPE            SMALLINT,
   KEYVALUE           VARCHAR2(20),
   PKFIELD            VARCHAR2(20),
   LISTTEMPLATE       CLOB,
   DETAILTEMPLATE     CLOB,
   GENBYFORM          SMALLINT,
   CREATETIME         TIMESTAMP,
   CREATOR            VARCHAR2(50),
   CREATEBY           NUMBER(18),
   TEAM                 CLOB,
   KEYDATATYPE          SMALLINT,
   CATEGORYID         NUMBER(18),
   PRIMARY KEY (TABLEID)
);

COMMENT ON TABLE BPM_FORM_TABLE IS
'表单数据库表';

/*==============================================================*/
/* TABLE: BPM_FORM_TEMPLATE                                     */
/*==============================================================*/
CREATE TABLE BPM_FORM_TEMPLATE  (
   TEMPLATEID         NUMBER(18)                         NOT NULL,
   TEMPLATENAME       VARCHAR2(200),
   TEMPLATETYPE       VARCHAR2(20),
   MACROTEMPLATEALIAS VARCHAR2(50),
   HTML               CLOB,
   TEMPLATEDESC       VARCHAR2(400),
   CANEDIT            SMALLINT,
   ALIAS              VARCHAR2(50),
   PRIMARY KEY (TEMPLATEID)
);

COMMENT ON TABLE BPM_FORM_TEMPLATE IS
'表单模版';

/*==============================================================*/
/* TABLE: BPM_GANGED_SET                                        */
/*==============================================================*/
CREATE TABLE BPM_GANGED_SET  (
   ID                 NUMBER(18)                         NOT NULL,
   DEFID              NUMBER(18),
   NODEID             VARCHAR2(100),
   NODENAME           VARCHAR2(200),
   CHOISEFIELD        CLOB,
   CHANGEFIELD        CLOB,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_GANGED_SET IS
'表单联动设置';

/*==============================================================*/
/* TABLE: BPM_MON_GROUP                                         */
/*==============================================================*/
CREATE TABLE BPM_MON_GROUP  (
   ID                   NUMBER(18)                         NOT NULL,
   NAME                 VARCHAR2(200),
   GRADE                SMALLINT,
   ENABLED              SMALLINT,
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(50),
   CREATETIME           DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_MON_GROUP IS
'流程监控分组';

/*==============================================================*/
/* TABLE: BPM_MON_GROUPITEM                                     */
/*==============================================================*/
CREATE TABLE BPM_MON_GROUPITEM  (
   ID                   NUMBER(18)                         NOT NULL,
   GROUPID              NUMBER(18),
   FLOWKEY              VARCHAR2(50),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_MON_GROUPITEM IS
'分组流程定义';

/*==============================================================*/
/* TABLE: BPM_MON_ORGROLE                                       */
/*==============================================================*/
CREATE TABLE BPM_MON_ORGROLE  (
   ID                   NUMBER(18)                        NOT NULL,
   GROUPID              NUMBER(18),
   ROLEID               NUMBER(18),
   ORGID                NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_MON_ORGROLE IS
'分组角色组织授权';

/*==============================================================*/
/* TABLE: BPM_NODE_BTN                                          */
/*==============================================================*/
CREATE TABLE BPM_NODE_BTN  (
   ID                 NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(64),
   ISSTARTFORM        SMALLINT,
   NODEID             VARCHAR2(50),
   BTNNAME            VARCHAR2(50),
   ICONCLSNAME        VARCHAR2(50),
   OPERATORTYPE       SMALLINT,
   PREVSCRIPT         VARCHAR2(2000),
   AFTERSCRIPT        VARCHAR2(2000),
   NODETYPE           SMALLINT,
   SN                 NUMBER(18),
   DEFID                NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_NODE_BTN IS
'自定义工具条';

/*==============================================================*/
/* TABLE: BPM_NODE_MESSAGE                                      */
/*==============================================================*/
CREATE TABLE BPM_NODE_MESSAGE  (
   ID                 NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(64),
   NODEID             VARCHAR2(50),
   MESSAGETYPE          SMALLINT,
   SUBJECT              VARCHAR2(200),
   TEMPLATE             CLOB,
   ISSEND             SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_NODE_MESSAGE IS
'流程消息节点';

/*==============================================================*/
/* TABLE: BPM_NODE_PRIVILEGE                                    */
/*==============================================================*/
CREATE TABLE BPM_NODE_PRIVILEGE  (
   PRIVILEGEID        NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(128),
   NODEID             VARCHAR2(128),
   PRIVILEGEMODE        SMALLINT,
   USERTYPE           SMALLINT,
   CMPNAMES           CLOB,
   CMPIDS             CLOB,
   PRIMARY KEY (PRIVILEGEID)
);

COMMENT ON TABLE BPM_NODE_PRIVILEGE IS
'节点特权';

/*==============================================================*/
/* TABLE: BPM_NODE_RULE                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_RULE  (
   RULEID             NUMBER(18)                         NOT NULL,
   RULENAME           VARCHAR2(128)                   NOT NULL,
   CONDITIONCODE      CLOB,
   ACTDEFID           VARCHAR2(127),
   NODEID             VARCHAR2(50),
   PRIORITY           NUMBER(18),
   TARGETNODE         VARCHAR2(20),
   TARGETNODENAME     VARCHAR2(255),
   MEMO               VARCHAR2(200),
   PRIMARY KEY (RULEID)
);

COMMENT ON TABLE BPM_NODE_RULE IS
'流程节点规则流程节点规则 是用于设计根据表单或其他流程变量来进行计算，以实现其规则的跳转CODE: NODE_RULE';

/*==============================================================*/
/* TABLE: BPM_NODE_SCRIPT                                       */
/*==============================================================*/
CREATE TABLE BPM_NODE_SCRIPT  (
   ID                 NUMBER(18)                         NOT NULL,
   MEMO               VARCHAR2(50),
   NODEID             VARCHAR2(20),
   ACTDEFID           VARCHAR2(64),
   SCRIPT             CLOB,
   SCRIPTTYPE         NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_NODE_SCRIPT IS
'流程节点事件脚本';

/*==============================================================*/
/* TABLE: BPM_NODE_SET                                          */
/*==============================================================*/
CREATE TABLE BPM_NODE_SET  (
   SETID              NUMBER(18)                         NOT NULL,
   DEFID              NUMBER(18),
   NODENAME           VARCHAR2(256),
   NODEORDER            SMALLINT,
   NODEID             VARCHAR2(128),
   FORMTYPE           SMALLINT                       DEFAULT 0,
   FORMURL            VARCHAR2(255),
   FORMKEY            VARCHAR(50),
   ACTDEFID           VARCHAR2(127),
   FORMDEFNAME        VARCHAR2(255),
   NODETYPE           SMALLINT,
   JOINTASKKEY        VARCHAR2(128),
   JOINTASKNAME       VARCHAR2(256),
   BEFOREHANDLER      VARCHAR2(100),
   AFTERHANDLER       VARCHAR2(100),
   JUMPTYPE           VARCHAR2(32),
   SETTYPE            SMALLINT,
   ISJUMPFORDEF         SMALLINT,
   ISHIDEOPTION       SMALLINT,
   ISHIDEPATH         SMALLINT,
   DETAILURL          VARCHAR2(256),
   ISALLOWMOBILE        SMALLINT,
   INFORMTYPE         VARCHAR(50),
   PARENTACTDEFID     VARCHAR2(128),
   MOBILEFORMKEY        NUMBER(18),
   MOBILEFORMURL        VARCHAR2(256),
   MOBILEDETAILURL      VARCHAR2(256),
   SCOPE 				VARCHAR(256),
   ISREQUIRED           SMALLINT             DEFAULT 0,
   ISPOPUP              SMALLINT             DEFAULT 1,
   OPINIONFIELD         VARCHAR2(64),
   OPINIONHTML          SMALLINT             DEFAULT 1,
   SENDMSGTOCREATOR     SMALLINT,
   PRIMARY KEY (SETID)
);

COMMENT ON TABLE BPM_NODE_SET IS
'流程节点设置';

/*==============================================================*/
/* TABLE: BPM_NODE_SIGN                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_SIGN  (
   SIGNID             NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(127),
   NODEID             VARCHAR2(128),
   VOTEAMOUNT         NUMBER(18),
   DECIDETYPE         SMALLINT                        NOT NULL,
   VOTETYPE           SMALLINT,
   FLOWMODE           SMALLINT,
   PRIMARY KEY (SIGNID)
);

COMMENT ON TABLE BPM_NODE_SIGN IS
'任务会签设置';

/*==============================================================*/
/* TABLE: BPM_NODE_USER                                         */
/*==============================================================*/
CREATE TABLE BPM_NODE_USER  (
   NODEUSERID         NUMBER(18)                         NOT NULL,
   CMPIDS             CLOB,
   CMPNAMES           CLOB,
   ASSIGNTYPE         VARCHAR2(20)                        ,
   SN                 NUMBER(18),
   COMPTYPE           SMALLINT,
   CONDITIONID          NUMBER(18),
   EXTRACTUSER          SMALLINT,
   PRIMARY KEY (NODEUSERID)
);

COMMENT ON TABLE BPM_NODE_USER IS
'流程节点人员';

/*==============================================================*/
/* TABLE: BPM_NODE_WEBSERVICE                                   */
/*==============================================================*/
CREATE TABLE BPM_NODE_WEBSERVICE  (
   ID                   NUMBER(18)                         NOT NULL,
   ACTDEFID             VARCHAR2(128),
   NODEID               VARCHAR2(128),
   DOCUMENT             clob,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_NODE_WEBSERVICE IS
'流程WEBSERVICE节点';

/*==============================================================*/
/* TABLE: BPM_NODE_WS_PARAMS                                    */
/*==============================================================*/
CREATE TABLE BPM_NODE_WS_PARAMS  (
   ID                   NUMBER(18)                         NOT NULL,
   WEBSERVICEID         NUMBER(18),
   PARATYPE             NUMBER(18),
   VARID                NUMBER(18),
   WSNAME               VARCHAR2(256),
   TYPE                 VARCHAR2(128),
   PRIMARY KEY (ID)
);

/*==============================================================*/
/* TABLE: BPM_PRINT_TEMPLATE                                    */
/*==============================================================*/
CREATE TABLE BPM_PRINT_TEMPLATE  (
   ID                 NUMBER(18)                         NOT NULL,
   FORM_KEY             VARCHAR2(50),
   TEMAPALTE_NAME       VARCHAR2(200),
   IS_DEFAULT           SMALLINT,
   TABLEID              NUMBER(18),
   HTML                 CLOB,
   TEMPLATE           CLOB,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_PRINT_TEMPLATE IS
'打印模版';

/*==============================================================*/
/* TABLE: BPM_PRO_RUN                                           */
/*==============================================================*/
CREATE TABLE BPM_PRO_RUN  (
   RUNID              NUMBER(18)                         NOT NULL,
   DEFID              NUMBER(18),
   BUSINESSKEY_NUM    NUMBER(18),
   PROCESSNAME        VARCHAR2(256),
   SUBJECT            VARCHAR2(600),
   CREATORID          NUMBER(18),
   CREATOR            VARCHAR2(128),
   CREATETIME         DATE,
   BUSDESCP           VARCHAR2(3000),
   ACTINSTID          NUMBER(18),
   STATUS             SMALLINT,
   ACTDEFID           VARCHAR2(256),
   BUSINESSKEY        VARCHAR2(255),
   BUSINESSURL        VARCHAR2(255),
   ENDTIME            DATE,
   DURATION           NUMBER(18),
   PKNAME             VARCHAR2(50),
   TABLENAME          VARCHAR2(50),
   PARENTID             NUMBER(18),
   STARTORGID           NUMBER(18),
   STARTORGNAME         VARCHAR2(200),
   FORMDEFID            NUMBER(18),
   TYPEID               NUMBER(18),
   DSALIAS              VARCHAR2(50),
   FLOWKEY              VARCHAR2(50),
   FORMTYPE           NUMBER(1),
   FORMKEYURL         VARCHAR2(50),
   LASTSUBMITDURATION   NUMBER(18),
   ISFORMAL           SMALLINT,
   STARTNODE		  VARCHAR2(50),
   RELRUNID NUMBER(18),
   GLOBALFLOWNO       VARCHAR2(256),
   PRIMARY KEY (RUNID)
);

COMMENT ON TABLE BPM_PRO_RUN IS
'流程实例扩展CODE:PRO_RUN';

/*==============================================================*/
/* TABLE: BPM_PRO_RUN_HIS                                       */
/*==============================================================*/
CREATE TABLE BPM_PRO_RUN_HIS  (
   RUNID              NUMBER(18)                         NOT NULL,
   DEFID              NUMBER(18),
   BUSINESSKEY_NUM    NUMBER(18),
   PROCESSNAME        VARCHAR2(256),
   SUBJECT            VARCHAR2(600),
   CREATORID          NUMBER(18),
   CREATOR            VARCHAR2(128),
   CREATETIME         DATE,
   BUSDESCP           VARCHAR2(3000),
   ACTINSTID          NUMBER(18),
   STATUS             SMALLINT,
   ACTDEFID           VARCHAR2(256),
   BUSINESSKEY        VARCHAR2(255),
   BUSINESSURL        VARCHAR2(255),
   ENDTIME            DATE,
   DURATION           NUMBER(18),
   PKNAME             VARCHAR2(50),
   TABLENAME          VARCHAR2(50),
   PARENTID             NUMBER(18),
   STARTORGID           NUMBER(18),
   STARTORGNAME         VARCHAR2(200),
   FORMDEFID            NUMBER(18),
   TYPEID               NUMBER(18),
   DSALIAS              VARCHAR2(50),
   FLOWKEY              VARCHAR2(50),
   FORMTYPE           NUMBER(1),
   FORMKEYURL         VARCHAR2(50),
   LASTSUBMITDURATION   NUMBER(18),
   ISFORMAL           SMALLINT,
   STARTNODE		  VARCHAR2(50),
   RELRUNID NUMBER(18),
   GLOBALFLOWNO       VARCHAR2(256),
   PRIMARY KEY (RUNID)
);

COMMENT ON TABLE BPM_PRO_RUN_HIS IS
'流程实例扩展CODE:PRO_RUN';

/*==============================================================*/
/* TABLE: BPM_PRO_STATUS                                        */
/*==============================================================*/
CREATE TABLE BPM_PRO_STATUS  (
   ID                   NUMBER(18),
   ACTINSTID            NUMBER(18),
   NODEID               VARCHAR2(64),
   NODENAME             VARCHAR2(255),
   STATUS               SMALLINT,
   LASTUPDATETIME       DATE,
   ACTDEFID             VARCHAR2(64),
   DEFID                NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_PRO_STATUS IS
'流程节点状态';

/*==============================================================*/
/* TABLE: BPM_REFER_DEFINITION                                  */
/*==============================================================*/
CREATE TABLE BPM_REFER_DEFINITION  (
   ID                   NUMBER(18)                         NOT NULL,
   DEFID                VARCHAR2(200),
   REFER_DEFKEY         VARCHAR2(128),
   CREATETIME           TIMESTAMP,
   CREATEID             NUMBER(18),
   UPDATETIME           TIMESTAMP,
   STATE                SMALLINT,
   REMARK               VARCHAR2(200),
   SUBJECT              VARCHAR2(200),
   UPDATEID             NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_REFER_DEFINITION IS
'流程引用定义';

/*==============================================================*/
/* TABLE: BPM_RUN_LOG                                           */
/*==============================================================*/
CREATE TABLE BPM_RUN_LOG  (
   ID                   NUMBER(18),
   USERID               NUMBER(18),
   USERNAME             VARCHAR2(50),
   CREATETIME           DATE,
   OPERATORTYPE         SMALLINT,
   MEMO                 VARCHAR2(300),
   RUNID                NUMBER(18),
   PROCESSSUBJECT		VARCHAR2(300),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_RUN_LOG IS
'工作流运行日志';

/*==============================================================*/
/* TABLE: BPM_SUBTABLE_RIGHTS                                   */
/*==============================================================*/
CREATE TABLE BPM_SUBTABLE_RIGHTS  (
   ID                   NUMBER(18),
   ACTDEFID             VARCHAR2(100),
   NODEID               VARCHAR2(50),
   TABLEID              NUMBER(18),
   PERMISSIONTYPE       SMALLINT,
   PERMISSIONSETING     VARCHAR2(2000),
   PARENTACTDEFID       VARCHAR2(128),
   PRIMARY KEY (ID)
);



 

/*==============================================================*/
/* TABLE: BPM_TASK_DUE                                          */
/*==============================================================*/
CREATE TABLE BPM_TASK_DUE  (
   TASKDUEID          NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(127),
   NODEID             VARCHAR2(50),
   REMINDERSTART      NUMBER(18)                         NOT NULL,
   REMINDEREND        NUMBER(18),
   TIMES              NUMBER(18),
   MAILCONTENT        CLOB,
   MSGCONTENT         CLOB,
   SMSCONTENT         CLOB,
   ACTION             NUMBER(18),
   SCRIPT             VARCHAR2(2000),
   COMPLETETIME       NUMBER(18),
   CONDEXP              CLOB,
   NAME                 VARCHAR2(50),
   RELATIVENODEID       VARCHAR2(100),
   RELATIVENODETYPE     NUMBER(18),
   RELATIVETIMETYPE     NUMBER(18),
   ASSIGNERID           NUMBER(18),
   ASSIGNERNAME         VARCHAR2(50),
   WARNINGSETJSON      VARCHAR2(2000),
   PRIMARY KEY (TASKDUEID)
);

COMMENT ON TABLE BPM_TASK_DUE IS
'任务节点催办时间设置';

/*==============================================================*/
/* TABLE: BPM_TASK_EXE                                          */
/*==============================================================*/
CREATE TABLE BPM_TASK_EXE  (
   ID                   NUMBER(18)                         NOT NULL,
   TASKID               NUMBER(18),
   ASSIGNEE_ID          NUMBER(18),
   ASSIGNEE_NAME        VARCHAR2(50),
   OWNER_ID             NUMBER(18),
   OWNER_NAME           VARCHAR2(200),
   SUBJECT              VARCHAR2(400),
   STATUS               SMALLINT,
   MEMO                 VARCHAR2(4000),
   CRATETIME            DATE,
   ACT_INST_ID          NUMBER(18),
   TASK_NAME            VARCHAR2(400),
   TASK_DEF_KEY         VARCHAR2(64),
   EXE_TIME             DATE,
   EXE_USER_ID          NUMBER(18),
   EXE_USER_NAME        VARCHAR2(256),
   ASSIGN_TYPE          SMALLINT,
   RUNID                NUMBER(18),
   TYPE_ID              NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_TASK_EXE IS
'任务转办代理';

/*==============================================================*/
/* TABLE: BPM_TASK_FORK                                         */
/*==============================================================*/
CREATE TABLE BPM_TASK_FORK  (
   TASKFORKID         NUMBER(18)                         NOT NULL,
   ACTINSTID          NUMBER(18),
   FORKTASKNAME       VARCHAR2(256),
   FORKTASKKEY        VARCHAR2(256),
   FORKSN             NUMBER(18),
   FORKCOUNT          NUMBER(18),
   FININSHCOUNT       NUMBER(18),
   FORKTIME           DATE,
   JOINTASKNAME       VARCHAR2(256),
   JOINTASKKEY        VARCHAR2(256),
   FORKTOKENS         VARCHAR2(512),
   FORKTOKENPRE       VARCHAR2(64),
   PRIMARY KEY (TASKFORKID)
);

COMMENT ON TABLE BPM_TASK_FORK IS
'流程任务分发汇总';

/*==============================================================*/
/* TABLE: BPM_TASK_OPINION                                      */
/*==============================================================*/
CREATE TABLE BPM_TASK_OPINION  (
   OPINIONID          NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(127),
   TASKNAME           VARCHAR2(255),
   TASKKEY            VARCHAR2(64),
   TASKID             NUMBER(18),
   TASKTOKEN          VARCHAR2(50),
   ACTINSTID          NUMBER(18),
   STARTTIME          TIMESTAMP,
   ENDTIME            TIMESTAMP null,
   DURTIME            NUMBER(18),
   EXEUSERID          NUMBER(18),
   EXEFULLNAME        VARCHAR2(127),
   OPINION            CLOB,
   CHECKSTATUS        SMALLINT,
   FORMDEFID          NUMBER(18),
   FIELDNAME          VARCHAR2(50),
   SUPEREXECUTION       NUMBER(18),
   PRIMARY KEY (OPINIONID)
);

COMMENT ON TABLE BPM_TASK_OPINION IS
'流程任务审批意见';

/*==============================================================*/
/* TABLE: BPM_TASK_READ                                         */
/*==============================================================*/
CREATE TABLE BPM_TASK_READ  (
   ID                   NUMBER(18)                         NOT NULL,
   ACTINSTID            NUMBER(18),
   TASKID               NUMBER(18),
   USERID               NUMBER(18),
   USERNAME             VARCHAR2(100),
   CREATETIME           DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_TASK_READ IS
'任务已读';

/*==============================================================*/
/* TABLE: BPM_TASK_REMINDERSTATE                                */
/*==============================================================*/
CREATE TABLE BPM_TASK_REMINDERSTATE  (
   ID                 NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(127),
   TASKID             NUMBER(18),
   REMINDERTIME       DATE,
   USERID               NUMBER(18),
   ACTINSTANCEID        NUMBER(18),
   REMINDTYPE           SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_TASK_REMINDERSTATE IS
'任务催办执行情况';

/*==============================================================*/
/* TABLE: BPM_TKSIGN_DATA                                       */
/*==============================================================*/
CREATE TABLE BPM_TKSIGN_DATA  (
   DATAID             NUMBER(18)                         NOT NULL,
   ACTDEFID           VARCHAR2(127),
   ACTINSTID          NUMBER(18)                         NOT NULL,
   NODENAME           VARCHAR2(128),
   NODEID             VARCHAR2(127)                   NOT NULL,
   TASKID             NUMBER(18),
   VOTEUSERID         VARCHAR2(1000)                         NOT NULL,
   VOTEUSERNAME       VARCHAR2(1000),
   VOTETIME           DATE,
   ISAGREE            SMALLINT,
   CONTENT            VARCHAR2(200),
   SIGNNUMS           NUMBER(18)                        DEFAULT 1,
   ISCOMPLETED        SMALLINT                       DEFAULT 0,
   BATCH                SMALLINT,
   PRIMARY KEY (DATAID)
);

COMMENT ON TABLE BPM_TKSIGN_DATA IS
'任务会签数据';

/*==============================================================*/
/* TABLE: BPM_USER_CONDITION                                    */
/*==============================================================*/
CREATE TABLE BPM_USER_CONDITION  (
   ID                   NUMBER(18),
   SETID                NUMBER(18),
   CONDITIONNAME        VARCHAR2(127),
   ACTDEFID             VARCHAR2(127),
   NODEID               VARCHAR2(128),
   CONDITIONCODE        CLOB,
   SN                   NUMBER(38,0),
   CONDITIONSHOW        CLOB,
   CONDITIONTYPE        SMALLINT,
   GROUPNO              SMALLINT,
   FORMIDENTITY         VARCHAR2(30),
   PARENTACTDEFID       VARCHAR2(128),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_USER_CONDITION IS
'用户选择条件';


/*==============================================================*/
/* TABLE: OUT_MAIL                                              */
/*==============================================================*/
CREATE TABLE OUT_MAIL  (
   MAILID             NUMBER(18)                         NOT NULL,
   TITLE              VARCHAR2(512),
   CONTENT            CLOB,
   SENDERADDRESSES    VARCHAR2(128),
   SENDERNAME         VARCHAR2(128),
   RECEIVERADDRESSES  VARCHAR2(4000),
   RECEIVERNAMES      VARCHAR2(4000),
   CCADDRESSES        VARCHAR2(4000),
   BCCANAMES          VARCHAR2(4000),
   BCCADDRESSES       VARCHAR2(4000),
   CCNAMES            VARCHAR2(4000),
   EMAILID            VARCHAR2(128),
   TYPES              NUMBER(18),
   USERID             NUMBER(18),
   ISREPLY            NUMBER(18),
   MAILDATE           DATE,
   FILEIDS            VARCHAR2(512),
   ISREAD             NUMBER(18),
   SETID              NUMBER(18),
   PRIMARY KEY (MAILID)
);

COMMENT ON TABLE OUT_MAIL IS
'外部邮件';

/*==============================================================*/
/* TABLE: OUT_MAIL_LINKMAN                                      */
/*==============================================================*/
CREATE TABLE OUT_MAIL_LINKMAN  (
   LINKID             NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   MAILID             NUMBER(18),
   SENDTIME           DATE,
   LINKNAME           VARCHAR2(20),
   LINKADDRESS        VARCHAR2(2000),
   PRIMARY KEY (LINKID)
);

COMMENT ON TABLE OUT_MAIL_LINKMAN IS
'外部邮件最近联系';

/*==============================================================*/
/* TABLE: OUT_MAIL_USER_SETING                                  */
/*==============================================================*/
CREATE TABLE OUT_MAIL_USER_SETING  (
   ID                 NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   USERNAME           VARCHAR2(128),
   MAILADDRESS        VARCHAR2(128),
   MAILPASS           VARCHAR2(128),
   SMTPHOST           VARCHAR2(128),
   SMTPPORT           VARCHAR2(64),
   POPHOST            VARCHAR2(128),
   POPPORT            VARCHAR2(64),
   IMAPHOST           VARCHAR2(128),
   IMAPPORT           VARCHAR2(128),
   ISDEFAULT          SMALLINT,
   MAILTYPE           VARCHAR2(50),
   USESSL			  SMALLINT DEFAULT 0,
   ISVALIDATE		  SMALLINT DEFAULT 1,
   ISDELETEREMOTE	  SMALLINT DEFAULT 0,
   ISHANDLEATTACH	  SMALLINT DEFAULT 1,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE OUT_MAIL_USER_SETING IS
'外部邮件用户设置';

/*==============================================================*/
/* TABLE: SYS_ACCEPT_IP                                         */
/*==============================================================*/
CREATE TABLE SYS_ACCEPT_IP  (
   ACCEPTID           NUMBER(18)                         NOT NULL,
   TITLE              VARCHAR2(128),
   STARTIP            VARCHAR2(20),
   ENDIP              VARCHAR2(20),
   REMARK             VARCHAR2(200),
   PRIMARY KEY (ACCEPTID)
);

/*==============================================================*/
/* TABLE: SYS_AUDIT                                             */
/*==============================================================*/
CREATE TABLE SYS_AUDIT  (
   AUDITID            NUMBER(18)                         NOT NULL,
   OPNAME             VARCHAR2(128),
   EXETIME            DATE,
   EXECUTORID         NUMBER(18),
   EXECUTOR           VARCHAR2(64),
   FROMIP             VARCHAR2(64),
   EXEMETHOD          VARCHAR2(128),
   REQUESTURI         VARCHAR2(256),
   REQPARAMS          CLOB,
   OWNERMODEL         VARCHAR2(200),
   EXECTYPE           VARCHAR2(200),
   ORGID              NUMBER(18),
   DETAIL                 CLOB,
   PRIMARY KEY (AUDITID)
);

COMMENT ON TABLE SYS_AUDIT IS
'系统审计';

/*==============================================================*/
/* TABLE: SYS_CALENDAR                                          */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   MEMO               VARCHAR2(400),
   ISDEFAULT          NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_CALENDAR IS
'系统日历';

/*==============================================================*/
/* TABLE: SYS_CALENDAR_ASSIGN                                   */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR_ASSIGN  (
   ID                 NUMBER(18)                         NOT NULL,
   CANLENDARID        NUMBER(18),
   ASSIGNTYPE         SMALLINT,
   ASSIGNID           NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_CALENDAR_ASSIGN IS
'日历分配';

/*==============================================================*/
/* TABLE: SYS_CALENDAR_SETTING                                  */
/*==============================================================*/
CREATE TABLE SYS_CALENDAR_SETTING  (
   ID                 NUMBER(18)                         NOT NULL,
   CALENDARID         NUMBER(18),
   YEARS              SMALLINT,
   MONTHS             SMALLINT,
   DAYS               SMALLINT,
   TYPE               SMALLINT,
   WORKTIMEID         NUMBER(18)                         NOT NULL,
   CALDAY             VARCHAR2(20),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_CALENDAR_SETTING IS
'日历设置';

/*==============================================================*/
/* TABLE: SYS_CODE_TEMPLATE                                     */
/*==============================================================*/
CREATE TABLE SYS_CODE_TEMPLATE  (
   ID                   NUMBER(18)                         NOT NULL,
   TEMPLATE_NAME        VARCHAR2(200),
   HTML                 CLOB,
   MEMO                 VARCHAR2(200),
   TEMPLATE_ALIAS       VARCHAR2(200),
   TEMPLATE_TYPE        SMALLINT,
   ISSUBNEED            SMALLINT,
   FILENAME             VARCHAR2(200),
   FILEDIR              VARCHAR2(200),
   formEdit    SMALLINT, 
   formDetail SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_CODE_TEMPLATE IS
'基于自定义表的代码模版管理表';

/*==============================================================*/
/* TABLE: SYS_CONDITION_SCRIPT                                  */
/*==============================================================*/
CREATE TABLE SYS_CONDITION_SCRIPT  (
   ID                   NUMBER(18)                         NOT NULL,
   CLASS_NAME           VARCHAR2(400),
   CLASS_INS_NAME       VARCHAR2(200),
   METHOD_NAME          VARCHAR2(200),
   METHOD_DESC          VARCHAR2(400),
   RETURN_TYPE          VARCHAR2(50),
   ARGUMENT             CLOB,
   ENABLE               SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_CONDITION_SCRIPT IS
'条件脚本';

CREATE TABLE SYS_ALIAS_SCRIPT
  (
    ID             NUMBER(18,0) NOT NULL,
    ALIAS_NAME     VARCHAR2(100),
    ALIAS_DESC     VARCHAR2(100),
    CLASS_NAME     VARCHAR2(400),
    CLASS_INS_NAME VARCHAR2(200),
    METHOD_NAME    VARCHAR2(200),
    METHOD_DESC    VARCHAR2(400),
    RETURN_TYPE    VARCHAR2(50),
    SCRIPT_TYPE	   VARCHAR2(50),
    SCRIPT_COMTEN  CLOB,
    ARGUMENT       CLOB,
    ENABLE         NUMBER(2),
    IS_RETURN_VALUE NUMBER(2),
    RETURN_PARAM_JSON VARCHAR2(2000),
    PRIMARY KEY (ID)
  );
  
COMMENT ON TABLE SYS_ALIAS_SCRIPT IS '自定义别名脚本表';




/*==============================================================*/
/* TABLE: SYS_DATASOURCE                                        */
/*==============================================================*/
CREATE TABLE SYS_DATASOURCE  (
   ID                   NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   ALIAS              VARCHAR2(20),
   DRIVERNAME         VARCHAR2(100),
   URL                VARCHAR2(100),
   USERNAME           VARCHAR2(20),
   PASSWORD           VARCHAR2(20),
   DBTYPE             VARCHAR2(20),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_DATASOURCE IS
'系统数据源管理';

/*==============================================================*/
/* TABLE: SYS_DEMENSION                                         */
/*==============================================================*/
CREATE TABLE SYS_DEMENSION  (
   DEMID              NUMBER(18)                         NOT NULL,
   DEMNAME            VARCHAR2(128)                   NOT NULL,
   DEMDESC            VARCHAR2(1024),
   PRIMARY KEY (DEMID)
);

COMMENT ON TABLE SYS_DEMENSION IS
'人员维度表';

/*==============================================================*/
/* TABLE: SYS_DIC                                               */
/*==============================================================*/
CREATE TABLE SYS_DIC  (
   DICID              NUMBER(18)                         NOT NULL,
   TYPEID             NUMBER(18),
   ITEMKEY            VARCHAR2(64),
   ITEMNAME           VARCHAR2(64)                    NOT NULL,
   ITEMVALUE          VARCHAR2(128)                   NOT NULL,
   DESCP              VARCHAR2(256),
   SN                 NUMBER(18),
   NODEPATH           VARCHAR2(100),
   PARENTID           NUMBER(18),
   PRIMARY KEY (DICID)
);

COMMENT ON TABLE SYS_DIC IS
'数据字典';

/*==============================================================*/
/* TABLE: SYS_ERROR_LOG                                         */
/*==============================================================*/
CREATE TABLE SYS_ERROR_LOG  (
   ID                 NUMBER(18)                         NOT NULL,
   HASHCODE           VARCHAR2(40),
   ACCOUNT            VARCHAR2(50),
   IP                 VARCHAR2(30),
   ERRORURL           VARCHAR2(2000),
   ERROR              CLOB,
   ERRORDATE          DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_ERROR_LOG IS
'错误日志表';

/*==============================================================*/
/* TABLE: SYS_EXCEL_IMPRULE                                     */
/*==============================================================*/
CREATE TABLE SYS_EXCEL_IMPRULE  (
   ID                   NUMBER(18)                         NOT NULL,
   TABLE_NAME           VARCHAR2(30),
   COLUMN_STR           VARCHAR2(200),
   MARK                 VARCHAR2(200),
   IMP_TYPE             SMALLINT,
   BUSI_DATE            DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_EXCEL_IMPRULE IS
'EXCEL导入规则表';

/*==============================================================*/
/* TABLE: SYS_FILE                                              */
/*==============================================================*/
CREATE TABLE SYS_FILE  (
   FILEID             NUMBER(18)                         NOT NULL,
   TYPEID             NUMBER(18),
   FILENAME           VARCHAR2(128)                   NOT NULL,
   FILEPATH           VARCHAR2(128)                   NOT NULL,
   CREATETIME         DATE                            NOT NULL,
   EXT                VARCHAR2(32),
   FILETYPE           VARCHAR2(32)                    NOT NULL,
   NOTE               VARCHAR2(1024),
   CREATORID          NUMBER(18),
   CREATOR            VARCHAR2(32)                    NOT NULL,
   TOTALBYTES         NUMBER(18)                        DEFAULT 0,
   DELFLAG            SMALLINT,
   FILEBLOB			  BLOB,
   PRIMARY KEY (FILEID)
);

COMMENT ON TABLE SYS_FILE IS
'附件';
COMMENT ON COLUMN SYS_FILE.FILEBLOB IS '附件内容';

/*==============================================================*/
/* TABLE: SYS_GL_TYPE                                           */
/*==============================================================*/
CREATE TABLE SYS_GL_TYPE  (
   TYPEID             NUMBER(18)                         NOT NULL,
   TYPENAME           VARCHAR2(128)                   NOT NULL,
   NODEPATH           VARCHAR2(200),
   DEPTH              NUMBER(18)                         NOT NULL,
   PARENTID           NUMBER(18),
   CATKEY             VARCHAR2(64),
   NODEKEY            VARCHAR2(64)                    NOT NULL,
   SN                 NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   DEPID              NUMBER(18),
   TYPE               NUMBER(18),
   ISLEAF             SMALLINT,
   NODECODE             VARCHAR2(20),
   NODECODETYPE         SMALLINT,
   PRIMARY KEY (TYPEID)
);

COMMENT ON TABLE SYS_GL_TYPE IS
'总分类表
用于显示树层次结构的分类
可以允许任何层次结构';

/*==============================================================*/
/* TABLE: SYS_IDENTITY                                          */
/*==============================================================*/
CREATE TABLE SYS_IDENTITY  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   ALIAS              VARCHAR2(20),
   REGULATION         VARCHAR2(100),
   GENTYPE            SMALLINT,
   NOLENGTH           NUMBER(18),
   CURDATE            VARCHAR2(10),
   INITVALUE          NUMBER(18),
   CURVALUE           NUMBER(18),
   STEP               SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_IDENTITY IS
'流水号生成';

/*==============================================================*/
/* TABLE: SYS_JOBLOG                                            */
/*==============================================================*/
CREATE TABLE SYS_JOBLOG  (
   LOGID              NUMBER(18)                         NOT NULL,
   JOBNAME            VARCHAR2(50),
   TRIGNAME           VARCHAR2(50),
   STARTTIME          DATE,
   ENDTIME            DATE,
   CONTENT            CLOB,
   STATE              NUMBER(18),
   RUNTIME            NUMBER(18),
   PRIMARY KEY (LOGID)
);

COMMENT ON TABLE SYS_JOBLOG IS
'后台任务执行日志';

/*==============================================================*/
/* TABLE: SYS_LOG_SWITCH                                        */
/*==============================================================*/
CREATE TABLE SYS_LOG_SWITCH  (
   ID                   NUMBER(18)                         NOT NULL,
   MODEL                VARCHAR2(50),
   STATUS               SMALLINT,
   CREATETIME           TIMESTAMP,
   CREATOR              VARCHAR2(20),
   CREATORID            NUMBER(18),
   UPDBY                VARCHAR2(20),
   UPDBYID              NUMBER(18),
   MEMO                 VARCHAR2(300),
   LASTUPTIME           TIMESTAMP,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_LOG_SWITCH IS
'日志开关';

/*==============================================================*/
/* TABLE: SYS_MESSAGE_LOG                                       */
/*==============================================================*/
CREATE TABLE SYS_MESSAGE_LOG  (
   ID                 NUMBER(18)                         NOT NULL,
   SUBJECT            VARCHAR2(100),
   SENDTIME           DATE,
   RECEIVER           VARCHAR2(1000),
   MESSAGETYPE        NUMBER(18),
   STATE              NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_MESSAGE_LOG IS
'消息日志';

/*==============================================================*/
/* TABLE: SYS_MSG_READ                                          */
/*==============================================================*/
CREATE TABLE SYS_MSG_READ  (
   ID                 NUMBER(18)                         NOT NULL,
   MESSAGEID          NUMBER(18),
   RECEIVERID         NUMBER(18),
   RECEIVER           VARCHAR2(20),
   RECEIVETIME        DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_MSG_READ IS
'接收状态';

/*==============================================================*/
/* TABLE: SYS_MSG_RECEIVER                                      */
/*==============================================================*/
CREATE TABLE SYS_MSG_RECEIVER  (
   ID                 NUMBER(18)                         NOT NULL,
   MESSAGEID          NUMBER(18),
   RECEIVETYPE        SMALLINT,
   RECEIVERID         NUMBER(18),
   RECEIVER           VARCHAR2(20),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_MSG_RECEIVER IS
'消息接收者';

/*==============================================================*/
/* TABLE: SYS_MSG_REPLY                                         */
/*==============================================================*/
CREATE TABLE SYS_MSG_REPLY  (
   ID                 NUMBER(18)                         NOT NULL,
   MESSAGEID          NUMBER(18),
   CONTENT            CLOB,
   REPLYID            NUMBER(18),
   REPLY              VARCHAR2(20),
   REPLYTIME          DATE,
   ISPRIVATE          SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_MSG_REPLY IS
'消息回复';

/*==============================================================*/
/* TABLE: SYS_MSG_SEND                                          */
/*==============================================================*/
CREATE TABLE SYS_MSG_SEND  (
   ID                 NUMBER(18)                         NOT NULL,
   SUBJECT            VARCHAR2(400),
   USERID             NUMBER(18),
   USERNAME           VARCHAR2(20),
   MESSAGETYPE        VARCHAR2(50),
   CONTENT            CLOB,
   SENDTIME           DATE,
   CANREPLY           SMALLINT,
   RECEIVERNAME       CLOB,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_MSG_SEND IS
'发送消息';

/*==============================================================*/
/* TABLE: SYS_OFFICE_TEMPLATE                                   */
/*==============================================================*/
CREATE TABLE SYS_OFFICE_TEMPLATE  (
   ID                   NUMBER(18)                         NOT NULL,
   SUBJECT              VARCHAR2(20),
   TEMPLATETYPE         NUMBER(18),
   MEMO                 VARCHAR2(200),
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(20),
   CREATETIME           DATE,
   PATH                 VARCHAR2(200),
   TEMPLATEBLOB			BLOB,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_OFFICE_TEMPLATE IS
'OFFICE模版';
COMMENT ON COLUMN SYS_OFFICE_TEMPLATE.TEMPLATEBLOB IS '模板内容';

/*==============================================================*/
/* TABLE: SYS_ORG                                               */
/*==============================================================*/
CREATE TABLE SYS_ORG  (
   ORGID              NUMBER(18)                         NOT NULL,
   DEMID              NUMBER(18),
   ORGNAME            VARCHAR2(128)                   NOT NULL,
   ORGDESC            VARCHAR2(500),
   ORGSUPID           NUMBER(18),
   PATH               VARCHAR2(128),
   DEPTH              NUMBER(18),
   ORGTYPE            NUMBER(18),
   CREATORID          NUMBER(18),
   CREATETIME         DATE,
   UPDATEID           NUMBER(18),
   UPDATETIME         DATE,
   SN                 NUMBER(18),
   FROMTYPE             SMALLINT,
   ORGPATHNAME          VARCHAR2(2000),
   isdelete            integer     DEFAULT 0,
   code            VARCHAR2(128)                   NOT NULL,
   COMPANYID            NUMBER(18,0),
   COMPANY              VARCHAR2(20),
   ORGSTAFF             SMALLINT,
   PRIMARY KEY (ORGID)
);

COMMENT ON TABLE SYS_ORG IS
'组织架构SYS_ORG';

/*==============================================================*/
/* TABLE: SYS_ORG_PARAM                                         */
/*==============================================================*/
CREATE TABLE SYS_ORG_PARAM  (
   VALUEID            NUMBER(18),
   ORGID              NUMBER(18),
   PARAMID            NUMBER(18),
   PARAMVALUE         VARCHAR2(200),
   PARAMDATEVALUE     DATE,
   PARAMINTVALUE      NUMBER(18,2),
   PRIMARY KEY (VALUEID)
);

COMMENT ON TABLE SYS_ORG_PARAM IS
'组织参数值';

/*==============================================================*/
/* TABLE: SYS_ORG_ROLE                                          */
/*==============================================================*/
CREATE TABLE SYS_ORG_ROLE  (
   ID                 NUMBER(18)                         NOT NULL,
   ORGID                NUMBER(18),
   ROLEID               NUMBER(18),
   CANDEL               SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_ORG_ROLE IS
'组织和角色授权';

/*==============================================================*/
/* TABLE: SYS_ORG_ROLEMANAGE                                    */
/*==============================================================*/
CREATE TABLE SYS_ORG_ROLEMANAGE  (
   ID                   NUMBER(18)                         NOT NULL,
   ORGID                NUMBER(18),
   ROLEID               NUMBER(18),
   CANDEL             SMALLINT,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_ORG_ROLEMANAGE IS
'组织可以授权的角色范围(用于分级授权)';

/*==============================================================*/
/* TABLE: SYS_ORG_TYPE                                          */
/*==============================================================*/
CREATE TABLE SYS_ORG_TYPE  (
   ID                 NUMBER(18)                         NOT NULL,
   DEMID              NUMBER(18),
   NAME               VARCHAR2(50),
   LEVELS             SMALLINT,
   MEMO               VARCHAR2(100),
   ICON               VARCHAR2(100),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_ORG_TYPE IS
'组织结构类型';

/*==============================================================*/
/* TABLE: SYS_OVERTIME                                          */
/*==============================================================*/
CREATE TABLE SYS_OVERTIME  (
   ID                 NUMBER(18)                         NOT NULL,
   SUBJECT            VARCHAR2(50),
   USERID             NUMBER(18),
   STARTTIME          DATE,
   ENDTIME            DATE,
   WORKTYPE           SMALLINT,
   MEMO               VARCHAR2(200),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_OVERTIME IS
'加班情况';

/*==============================================================*/
/* TABLE: SYS_PARAM                                             */
/*==============================================================*/
CREATE TABLE SYS_PARAM  (
   PARAMID            NUMBER(18)                         NOT NULL,
   PARAMKEY           VARCHAR2(32),
   PARAMNAME          VARCHAR2(50),
   DATATYPE           VARCHAR2(20),
   EFFECT             SMALLINT,
   BELONGDEM            NUMBER(20),
   SOURCETYPE           VARCHAR(20),
   SOURCEKEY            VARCHAR(100),
   DESCRIPTION          VARCHAR(256),
   STATUS_              SMALLINT,
   CATEGORY             VARCHAR2(256),
   PRIMARY KEY (PARAMID)
);

COMMENT ON TABLE SYS_PARAM IS
'SYS_PARAM【用户或组织自定义属性】';

/*==============================================================*/
/* TABLE: SYS_PAUR                                              */
/*==============================================================*/
CREATE TABLE SYS_PAUR  (
   PAURID               NUMBER(18)                         NOT NULL,
   PAURNAME             VARCHAR2(30),
   ALIASNAME            VARCHAR2(30),
   PAURVALUE            VARCHAR2(50),
   USERID               NUMBER(18),
   PRIMARY KEY (PAURID)
);

/*==============================================================*/
/* Table: SYS_POS                                               */
/*==============================================================*/
CREATE TABLE SYS_POS  (
   POSID              NUMBER(18)                      NOT NULL,
   POSCODE 			  VARCHAR(200) 		 			  NOT NULL,
   POSNAME            VARCHAR2(128)                   NOT NULL,
   POSDESC            VARCHAR2(1024),
   ORGID              NUMBER(18),
   JOBID              NUMBER(18),
   ISDELETE           SMALLINT                       DEFAULT 0
);

COMMENT ON TABLE SYS_POS IS
'系统岗位表，实际是部门和职务的对应关系表';

ALTER TABLE SYS_POS
   ADD CONSTRAINT PK_SYS_POS PRIMARY KEY ("POSID");
/*==============================================================*/
/* Table: SYS_JOB                                               */
/*==============================================================*/
CREATE TABLE SYS_JOB  (
   JOBID              NUMBER(18)                      NOT NULL,
   JOBNAME            VARCHAR(100),
   JOBCODE            VARCHAR(100),
   JOBDESC            VARCHAR(1000),
   SETID              NUMBER(18)               DEFAULT 0,
   GRADE              NUMBER(10,0)             DEFAULT 0,
   ISDELETE           SMALLINT                       DEFAULT 0
);

COMMENT ON TABLE SYS_JOB IS '职务表';

ALTER TABLE SYS_JOB
   ADD CONSTRAINT PK_SYS_JOB PRIMARY KEY ("JOBID");

/*==============================================================*/
/* Table: SYS_USER_POS                                          */
/*==============================================================*/
CREATE TABLE SYS_USER_POS  (
   USERPOSID          NUMBER(18)                      NOT NULL,
   ORGID             NUMBER(18),
   POSID              NUMBER(18),
   JOBID                      NUMBER(18),
   USERID             NUMBER(18),
   ISPRIMARY          SMALLINT,
   ISCHARGE           SMALLINT,
   ISDELETE           SMALLINT                       DEFAULT 0
);

COMMENT ON TABLE SYS_USER_POS IS
'用户岗位表';

ALTER TABLE SYS_USER_POS
   ADD CONSTRAINT PK_SYS_USER_POS PRIMARY KEY ("USERPOSID");

/*==============================================================*/
/* TABLE: SYS_PROFILE                                           */
/*==============================================================*/
CREATE TABLE SYS_PROFILE  (
   ID                   NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   HOMEPAGE           VARCHAR2(50),
   SKIN               VARCHAR2(20),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_PROFILE IS
'个人个性化信息';

/*==============================================================*/
/* TABLE: SYS_REPORT_TEMPLATE                                   */
/*==============================================================*/
CREATE TABLE SYS_REPORT_TEMPLATE  (
   REPORTID           NUMBER(18)                       NOT NULL,
   TITLE              VARCHAR2(128)                   NOT NULL,
   DESCP              VARCHAR2(500)                   NOT NULL,
   REPORTLOCATION     VARCHAR2(128)                   NOT NULL,
   CREATETIME         DATE                            NOT NULL,
   UPDATETIME         DATE                            NOT NULL,
   REPORTKEY          VARCHAR2(128),
   ISDEFAULTIN        SMALLINT,
   TYPEID             NUMBER(18),
   PRIMARY KEY (REPORTID)
);

COMMENT ON TABLE SYS_REPORT_TEMPLATE IS
'报表模板REPORT_TEMPLATE';

/*==============================================================*/
/* TABLE: SYS_RES                                               */
/*==============================================================*/
CREATE TABLE SYS_RES  (
   RESID              NUMBER(18)                         NOT NULL,
   RESNAME            VARCHAR2(128)                   NOT NULL,
   ALIAS              VARCHAR2(128),
   SN                 NUMBER(18),
   ICON               VARCHAR2(100),
   PARENTID           NUMBER(18),
   DEFAULTURL         VARCHAR2(256),
   ISFOLDER           SMALLINT,
   ISDISPLAYINMENU    SMALLINT,
   ISOPEN             SMALLINT,
   SYSTEMID           NUMBER(18),
   ISNEWOPEN 			SMALLINT,
   PATH               VARCHAR2(500),
   PRIMARY KEY (RESID)
);

COMMENT ON TABLE SYS_RES IS
'子系统资源';

/*==============================================================*/
/* TABLE: SYS_RESURL                                            */
/*==============================================================*/
CREATE TABLE SYS_RESURL  (
   RESURLID           NUMBER(18)                         NOT NULL,
   RESID              NUMBER(18),
   NAME               VARCHAR2(100),
   URL                VARCHAR2(200),
   PRIMARY KEY (RESURLID)
);

COMMENT ON TABLE SYS_RESURL IS
'资源URL';

/*==============================================================*/
/* TABLE: SYS_ROLE                                              */
/*==============================================================*/
CREATE TABLE SYS_ROLE  (
   ROLEID             NUMBER(18)                         NOT NULL,
   SYSTEMID           NUMBER(18),
   ALIAS              VARCHAR2(128),
   ROLENAME           VARCHAR2(128)                   NOT NULL,
   MEMO               VARCHAR2(256),
   ALLOWDEL           SMALLINT,
   ALLOWEDIT          SMALLINT,
   ENABLED            SMALLINT,
   PRIMARY KEY (ROLEID)
);

COMMENT ON TABLE SYS_ROLE IS
'系统角色表';

/*==============================================================*/
/* TABLE: SYS_ROLE_RES                                          */
/*==============================================================*/
CREATE TABLE SYS_ROLE_RES  (
   ROLERESID          NUMBER(18)                         NOT NULL,
   ROLEID             NUMBER(18),
   RESID              NUMBER(18),
   SYSTEMID           NUMBER(18),
   PRIMARY KEY (ROLERESID)
);

COMMENT ON TABLE SYS_ROLE_RES IS
'角色资源映射';

/*==============================================================*/
/* TABLE: SYS_SCRIPT                                            */
/*==============================================================*/
CREATE TABLE SYS_SCRIPT  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(100),
   SCRIPT             CLOB,
   CATEGORY           VARCHAR2(50),
   MEMO               VARCHAR2(300),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_SCRIPT IS
'系统脚本节点';

/*==============================================================*/
/* TABLE: SYS_SEAL                                              */
/*==============================================================*/
CREATE TABLE SYS_SEAL  (
   SEALID               NUMBER(18)                         NOT NULL,
   SEALNAME             VARCHAR2(128),
   SEALPATH             VARCHAR2(128),
   BELONGID             NUMBER(18),
   BELONGNAME           VARCHAR2(128),
   ATTACHMENTID         NUMBER(18),
   SHOWIMAGEID			NUMBER(18),
   PRIMARY KEY (SEALID)
);

COMMENT ON TABLE SYS_SEAL IS
'电子印章';

/*==============================================================*/
/* TABLE: SYS_SUBSYSTEM                                         */
/*==============================================================*/
CREATE TABLE SYS_SUBSYSTEM  (
   SYSTEMID           NUMBER(18)                         NOT NULL,
   SYSNAME            VARCHAR2(50)                    NOT NULL,
   ALIAS              VARCHAR2(20),
   LOGO               VARCHAR2(100),
   DEFAULTURL         VARCHAR2(50),
   MEMO               VARCHAR2(200),
   CREATETIME         DATE,
   CREATOR            VARCHAR2(20),
   ALLOWDEL           SMALLINT,
   NEEDORG            SMALLINT,
   ISACTIVE           SMALLINT,
   ISLOCAL            SMALLINT,
   HOMEPAGE           VARCHAR2(256),
   PRIMARY KEY (SYSTEMID)
);

COMMENT ON TABLE SYS_SUBSYSTEM IS '子系统管理';



/*==============================================================*/
/* TABLE: SYS_TEMPLATE                                          */
/*==============================================================*/
CREATE TABLE SYS_TEMPLATE  (
   TEMPLATEID         NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   HTMLCONTENT        VARCHAR2(500),
   PLAINCONTENT         VARCHAR2(500),
   ISDEFAULT          SMALLINT,
   USETYPE            SMALLINT,
   TITLE              VARCHAR2(200),
   PRIMARY KEY (TEMPLATEID)
);

COMMENT ON TABLE SYS_TEMPLATE IS
'系统模版管理';

/*==============================================================*/
/* TABLE: SYS_TYPE_KEY                                          */
/*==============================================================*/
CREATE TABLE SYS_TYPE_KEY  (
   TYPEID             NUMBER(18)                         NOT NULL,
   TYPEKEY            VARCHAR2(64)                    NOT NULL,
   TYPENAME           VARCHAR2(128),
   FLAG               NUMBER(18),
   SN                 NUMBER(18),
   TYPE               NUMBER(18),
   PRIMARY KEY (TYPEID)
);

COMMENT ON TABLE SYS_TYPE_KEY IS
'系统分类键';

/*==============================================================*/
/* TABLE: SYS_USER                                              */
/*==============================================================*/
CREATE TABLE SYS_USER  (
   USERID             NUMBER(18)                         NOT NULL,
   FULLNAME           VARCHAR2(127),
   ACCOUNT            VARCHAR2(20)                    NOT NULL,
   PASSWORD           VARCHAR2(50)                    NOT NULL,
   ISEXPIRED          SMALLINT,
   ISLOCK             SMALLINT,
   CREATETIME         DATE,
   STATUS             SMALLINT,
   EMAIL              VARCHAR2(128),
   MOBILE             VARCHAR2(32),
   PHONE              VARCHAR2(32),
   SEX                VARCHAR2(2),
   PICTURE            VARCHAR2(300),
   FROMTYPE             SMALLINT,
   ENTRYDATE            DATE,
   USERSTATUS           VARCHAR2(32),
   PRIMARY KEY (USERID)
);

COMMENT ON TABLE SYS_USER IS
'用户表';



/*==============================================================*/
/* TABLE: SYS_USER_PARAM                                        */
/*==============================================================*/
CREATE TABLE SYS_USER_PARAM  (
   VALUEID            NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   PARAMID            NUMBER(18),
   PARAMVALUE         VARCHAR2(200),
   PARAMDATEVALUE     DATE,
   PARAMINTVALUE      NUMBER(18,2),
   PRIMARY KEY (VALUEID)
);

COMMENT ON TABLE SYS_USER_PARAM IS
'用户自定义参数';



/*==============================================================*/
/* TABLE: SYS_USER_ROLE                                         */
/*==============================================================*/
CREATE TABLE SYS_USER_ROLE  (
   USERROLEID         NUMBER(18)                         NOT NULL,
   ROLEID             NUMBER(18),
   USERID             NUMBER(18),
   PRIMARY KEY (USERROLEID)
);

COMMENT ON TABLE SYS_USER_ROLE IS
'用户角色映射表';

/*==============================================================*/
/* TABLE: SYS_USER_UNDER                                        */
/*==============================================================*/
CREATE TABLE SYS_USER_UNDER  (
   ID                 NUMBER(18)                         NOT NULL,
   USERID             NUMBER(18),
   UNDERUSERID        NUMBER(18),
   UNDERUSERNAME      VARCHAR2(50),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_USER_UNDER IS
'下属管理';

/*==============================================================*/
/* TABLE: SYS_VACATION                                          */
/*==============================================================*/
CREATE TABLE SYS_VACATION  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   YEARS              SMALLINT,
   STATTIME           DATE,
   ENDTIME            DATE,
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_VACATION IS
'法定假期设置';

/*==============================================================*/
/* TABLE: SYS_WORKTIME                                          */
/*==============================================================*/
CREATE TABLE SYS_WORKTIME  (
   ID                 NUMBER(18)                         NOT NULL,
   SETTINGID          NUMBER(18),
   STARTTIME          VARCHAR2(10),
   ENDTIME            VARCHAR2(10),
   MEMO               VARCHAR2(200),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_WORKTIME IS
'班次时间';

/*==============================================================*/
/* TABLE: SYS_WORKTIME_SETTING                                  */
/*==============================================================*/
CREATE TABLE SYS_WORKTIME_SETTING  (
   ID                 NUMBER(18)                         NOT NULL,
   NAME               VARCHAR2(50),
   MEMO               VARCHAR2(200),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_WORKTIME_SETTING IS '班次设置';

/*==============================================================*/
/* TABLE: SYS_DB_ID                                           */
/*==============================================================*/
CREATE TABLE SYS_DB_ID  (
   ID                 SMALLINT                        NOT NULL,
   INCREMENTAL        NUMBER(18),
   BOUND              NUMBER(18),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_DB_ID IS '系统ID记录表';


-- CREATE TABLE
CREATE TABLE BPM_PRO_CPTO
(
  COPY_ID      NUMBER(18) NOT NULL,
  ACT_INST_ID  NUMBER(18) NOT NULL,
  RUN_ID       NUMBER(18) NOT NULL,
  NODE_KEY     VARCHAR2(64),
  NODE_NAME    VARCHAR2(256),
  CC_UID       NUMBER(18),
  CC_UNAME     VARCHAR2(128),
  CC_TIME      DATE,
  IS_READED    NUMBER,
  FILL_OPINION VARCHAR2(2000),
  SUBJECT      VARCHAR2(256),
  READ_TIME    DATE,
  CP_TYPE      NUMBER,
  CREATE_ID    NUMBER(18),
  CREATOR      VARCHAR2(128),
  DEF_TYPEID   NUMBER(18),
  PRIMARY KEY (COPY_ID)
);


CREATE TABLE BPM_PRO_TRANSTO
(
  ID           NUMBER(18),
  ACTINSTID    NUMBER(18),
  TASKID       NUMBER(18),
  TRANSTYPE    NUMBER(1),
  ACTION       NUMBER(1),
  CREATEUSERID NUMBER(18),
  CREATETIME   TIMESTAMP,
  TRANSRESULT  SMALLINT,
  ASSIGNEE 	   VARCHAR2(256),
  PRIMARY KEY (ID)
);
COMMENT ON TABLE BPM_PRO_TRANSTO  IS '流程流转表';

create table SYS_SEAL_RIGHT
(
  ID          NUMBER(18) not null, 
  SEALID      NUMBER(18),
  RIGHTTYPE   VARCHAR2(20),
  RIGHTID     NUMBER(18),
  RIGHTNAME   VARCHAR2(100),
  CREATEUSER  NUMBER(18),
  CREATETIME  DATE,
  CONTROLTYPE SMALLINT DEFAULT '0',
  primary key (id)
);

COMMENT ON TABLE SYS_SEAL_RIGHT IS '印章授权表';

CREATE TABLE BPM_DATA_TEMPLATE(
  ID             NUMBER(18) NOT NULL,
  TABLEID        NUMBER(18),
  FORMKEY        VARCHAR(50),
  NAME           VARCHAR2(300),
  ALIAS          VARCHAR2(50),
  STYLE          INTEGER DEFAULT 0,
  NEEDPAGE       INTEGER DEFAULT 1,
  PAGESIZE       NUMBER(18),
  TEMPLATEALIAS  VARCHAR2(256),
  TEMPLATEHTML   CLOB,
  DISPLAYFIELD   CLOB,
  CONDITIONFIELD  CLOB,
  SORTFIELD      VARCHAR2(2000),
  MANAGEFIELD  VARCHAR2(2000),
  FILTERFIELD    CLOB,
  VARFIELD       VARCHAR2(2000),
  FILTERTYPE     INTEGER DEFAULT 1,
  SOURCE         VARCHAR2(256) DEFAULT '1',
  DEFID          NUMBER(18),
  ISQUERY              SMALLINT    	DEFAULT 1,
  ISFILTER		 INTEGER DEFAULT 1,
  EXPORTFIELD	CLOB,
  PRINTFIELD	CLOB,
  CONSTRAINT PK_BPM_DATA_TEMPLATE PRIMARY KEY (ID)
);
COMMENT ON TABLE BPM_DATA_TEMPLATE  IS '业务数据模板';


CREATE TABLE SYS_PERSON_SCRIPT
(
  ID             NUMBER(18) NOT NULL,
  CLASS_NAME     VARCHAR2(400),
  CLASS_INS_NAME VARCHAR2(200),
  METHOD_NAME    VARCHAR2(200),
  METHOD_DESC    VARCHAR2(400),
  RETURN_TYPE    VARCHAR2(50),
  ARGUMENT       CLOB,
  ENABLE         NUMBER,
  PRIMARY KEY (ID)
);
COMMENT ON TABLE SYS_PERSON_SCRIPT IS '人员脚本管理';

CREATE TABLE BPM_COMMON_WS_SET
(
  ID       NUMBER(18) NOT NULL,
  ALIAS    VARCHAR2(200) NOT NULL,
  WSDL_URL VARCHAR2(400),
  DOCUMENT CLOB,
  PRIMARY KEY (ID)
);
COMMENT ON TABLE BPM_COMMON_WS_SET IS '通用WEBSERVICE调用设置';


CREATE TABLE BPM_COMMON_WS_PARAMS
(
  ID          NUMBER(18) NOT NULL,
  SETID       NUMBER(18) NOT NULL,
  NAME        VARCHAR2(200),
  PARAM_TYPE  INTEGER,
  DESCRIPTION VARCHAR2(400),
  PRIMARY KEY (ID)
);
COMMENT ON TABLE BPM_COMMON_WS_PARAMS IS '通用WEBSERVICE调用参数';

/*==============================================================*/
/* Table: SYS_REPORT                                            */
/*==============================================================*/
CREATE TABLE SYS_REPORT  (
   REPORTID             NUMBER(18,0),
   TYPEID               NUMBER(18,0),
   TITLE                VARCHAR2(128),
   DESCP                VARCHAR2(200),
   FILEPATH             VARCHAR2(128),
   FILENAME             VARCHAR2(128),
   CREATETIME           TIMESTAMP (6),
   STATUS               NUMBER(1,0)                    DEFAULT 0,
   DSNAME               VARCHAR2(50),
   PARAMS               VARCHAR2(500)                  DEFAULT '[]',
   EXT                  VARCHAR2(20)                   DEFAULT 'jrxml',
   REALSQL                  CLOB
);

COMMENT ON TABLE SYS_REPORT IS
'Jasperreport报表';

/*==============================================================*/
/* Table: BPM_TABLE_TEMPLATE                                    */
/*==============================================================*/
CREATE TABLE BPM_TABLE_TEMPLATE  (
   ID                   NUMBER(18,0)                    NOT NULL,
   TABLE_ID             NUMBER(18,0),
   CATEGORY_ID          NUMBER(18,0),
   HTML_LIST            CLOB,
   HTML_DETAIL          CLOB,
   TEMPLATE_NAME        VARCHAR2(128 BYTE),
   AUTHOR_TYPE          NUMBER,
   FORMKEY              NUMBER(18,0),
   MEMO                 VARCHAR2(100 BYTE),
   LISTTEMPLATE         VARCHAR2(50 BYTE),
   DETAILTEMPLATE       VARCHAR2(50 BYTE),
   CONSTRAINT PK_TABLE_TEMPLATE PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_TABLE_TEMPLATE IS
'查看表格业务数据的模板';

create table BUS_QUERY_RULE
(
  id           NUMBER(18) not null,
  tablename    VARCHAR2(128),
  needpage     INTEGER default 1,
  pagesize     NUMBER(18),
  isquery      INTEGER default 0,
  isFilter      INTEGER default 0,
  displayfield CLOB,
  filterfield  CLOB,
  sortfield    CLOB,
  exportfield  CLOB,
  printfield   CLOB,
  createtime   TIMESTAMP(6),
  createby     NUMBER(18),
  updatetime   TIMESTAMP(6),
  updateby     NUMBER(18),
  PRIMARY KEY (ID)
);
comment on table BUS_QUERY_RULE  is '高级查询规则';

create table BUS_QUERY_FILTER
(
  id             NUMBER(18) not null,
  ruleid         NUMBER(18),
  tablename      VARCHAR2(256),
  filtername     VARCHAR2(256),
  filterdesc     CLOB,
  filterkey      VARCHAR2(256),
  queryparameter CLOB,
  sortparameter  CLOB,
  isshare        INTEGER default 0,
  createtime     TIMESTAMP(6),
  createby       NUMBER(18),
   PRIMARY KEY (ID)
);
comment on table BUS_QUERY_FILTER is '查询过滤';

create table BUS_QUERY_SHARE
(
  id         NUMBER(18) not null,
  filterid   NUMBER(18),
  shareright CLOB,
  sharerid   NUMBER(18),
  createtime TIMESTAMP(6),
  PRIMARY KEY (ID)
);
comment on table BUS_QUERY_SHARE  is '查询过滤共享';

  
CREATE TABLE SYS_WS_DATA_TEMPLATE (
	ID NUMBER(18) NOT NULL ,
	NAME VARCHAR2(500 BYTE) NULL ,
	SERVICEID NUMBER(18) NULL ,
	TEMPLATE CLOB NULL ,
	SCRIPT CLOB NULL,
	PRIMARY KEY (ID)
);
COMMENT ON TABLE SYS_WS_DATA_TEMPLATE IS 'webservice数据模板展示';

CREATE TABLE OUT_MAIL_ATTACHMENT (
	FILEID NUMBER(18) NOT NULL ,
	FILENAME VARCHAR2(128) NULL ,
	FILEPATH VARCHAR2(128) NULL ,
	MAILID NUMBER(18) NULL,
	PRIMARY KEY (FILEID)
);
COMMENT ON TABLE OUT_MAIL_ATTACHMENT IS '外部邮件附件表';

CREATE TABLE BPM_DEF_AUTHORIZE  (
   ID   NUMBER(18) not null,
   AUTHORIZE_DESC  VARCHAR2(512),
   PRIMARY KEY (ID)
);
COMMIT;
comment on table BPM_DEF_AUTHORIZE  is '流程授权主表';

CREATE TABLE BPM_DEF_USER  (
   ID   NUMBER(18)  not null,
   AUTHORIZE_ID  NUMBER(18) not null,
   OWNER_ID    NUMBER(18)  not null,
   OWNER_NAME  VARCHAR2(256),
   RIGHT_TYPE  VARCHAR2(40), 
   PRIMARY KEY (ID)
);
COMMIT;
comment on table BPM_DEF_USER is '流程授权中的用户子表';


CREATE TABLE BPM_DEF_ACT  (
   ID   NUMBER(18)  not null,
   AUTHORIZE_ID  NUMBER(18) not null,
   DEF_KEY   VARCHAR2(68)  not null,
   DEF_NAME  VARCHAR2(256),
   RIGHT_CONTENT  VARCHAR2(512),
   PRIMARY KEY (ID)
);
COMMIT;
comment on table BPM_DEF_ACT is '流程授权中流程子表';


create table SYS_PERSONAL_CALENDAR
(
  id          NUMBER(18) not null,
  title       VARCHAR2(512),
  description CLOB,
  starttime   DATE,
  endtime     DATE,
  createby    NUMBER(18),
  createtime  DATE,
  remindmode  INTEGER default 0,
  remindtime  DATE,
  remindtype  VARCHAR2(512),
   CONSTRAINT PK_SYS_PERSONAL_CALENDAR PRIMARY KEY (ID)
);
comment on table SYS_PERSONAL_CALENDAR
  is '个人日历'; 


CREATE TABLE SYS_URL_PERMISSION (
  ID_ NUMBER(18) NOT NULL ,
  DESCP_ VARCHAR2(500) ,
  URL_ VARCHAR2(2000) ,
  PARAMS_ VARCHAR2(500),
  ENABLE_ SMALLINT default '1' ,
  PRIMARY KEY  (ID_)
);
COMMENT ON TABLE SYS_URL_PERMISSION IS 'URL地址拦截管理'; 


CREATE TABLE SYS_URL_RULES (
  ID_ NUMBER(18) NOT NULL ,
  SCRIPT_  CLOB ,
  ENABLE_ SMALLINT DEFAULT '1' ,
  SYS_URL_ID_ NUMBER(18) ,
  DESCP_ VARCHAR2(500) ,
  SORT_ SMALLINT DEFAULT '1' ,
  PRIMARY KEY  (ID_)
);
COMMENT ON TABLE SYS_URL_RULES IS 'URL地址拦截脚本管理'; 


CREATE TABLE SYS_QUERY_SQL (
  ID NUMBER(18) NOT NULL ,
  SQL_  VARCHAR2(2000) ,
  NAME VARCHAR2(100) ,
  DSALIAS VARCHAR2(50) ,
  URL_PARAMS VARCHAR2(2000) ,
  CATEGORYID NUMBER(18),
  PRIMARY KEY  (ID)
);
COMMENT ON TABLE SYS_QUERY_SQL IS '自定义列表'; 


CREATE TABLE SYS_QUERY_FIELD
(
  ID               NUMBER(18) NOT NULL ,      
  SQL_ID           NUMBER(18) NOT NULL ,
  NAME             VARCHAR2(200 BYTE),
  TYPE             VARCHAR2(50 BYTE),
  FIELD_DESC       VARCHAR2(2000 BYTE),
  IS_SHOW          INTEGER default 1,
  IS_SEARCH        INTEGER default 0,
  CONTROL_TYPE     NUMBER,
  FORMAT           VARCHAR2(200 BYTE),
  CONTROL_CONTENT  VARCHAR2(4000 BYTE),
  PRIMARY KEY  (ID)
);
COMMENT ON TABLE SYS_QUERY_FIELD IS '自定义列表字段'; 

  
CREATE TABLE SYS_QUERY_SETTING
(
  ID               NUMBER(18) NOT NULL ,      
  SQL_ID           NUMBER(18) NOT NULL ,
  NAME             VARCHAR2(50 BYTE),
  ALIAS            VARCHAR2(50 BYTE),
  STYLE            INTEGER,
  NEED_PAGE        INTEGER,
  PAGE_SIZE        INTEGER,
  TEMPLATE_ALIAS   VARCHAR2(50 BYTE),
  TEMPLATE_HTML    CLOB,
  DISPLAY_FIELD    CLOB,
  FILTER_FIELD     CLOB,
  CONDITION_FIELD  CLOB,
  SORT_FIELD       CLOB,
  EXPORT_FIELD     CLOB,
  MANAGE_FIELD     CLOB,
  IS_QUERY         INTEGER default 1 ,
  PRIMARY KEY  (ID)
);

COMMENT ON TABLE SYS_QUERY_SETTING IS '自定义列表字段设置'; 

 
  
create table BPM_MOBILE_FORM
(
  id        NUMBER(18) not null,
  formid    NUMBER(18),
  formkey   NUMBER(18),
  html      CLOB,
  template  CLOB,
  formJson      CLOB,
  isdefault INTEGER,
  guid VARCHAR2(128),
  constraint PK_BPM_MOBILE_FORM primary key (ID)
);
comment on table BPM_MOBILE_FORM
  is '手机表单设置';

   CREATE TABLE SYS_ORG_AUTH 
   (	
		ID 			NUMBER(18) NOT NULL , 
		USER_ID 	NUMBER(18) NOT NULL , 
		ORG_ID 		NUMBER(18) NOT NULL , 
		DIM_ID 		NUMBER(18) NOT NULL , 
		ORG_PERMS 	VARCHAR2(255), 
		USER_PERMS 	VARCHAR2(255), 
		PRIMARY KEY  (ID)
	);
	
   COMMENT ON TABLE  SYS_ORG_AUTH IS '组织授权'; 
 
 
   CREATE TABLE SYS_AUTH_ROLE 
   (	
		ID 			NUMBER(18,0) NOT NULL , 
		AUTH_ID 	NUMBER(18,0) NOT NULL , 
		ROLE_ID 	NUMBER(18,0) NOT NULL , 
		PRIMARY KEY  (ID)
	);

   COMMENT ON TABLE SYS_AUTH_ROLE  IS '授权角色';
   
/*==============================================================*/
/* 可配置数据源:SYS_DATA_SOURCE ,SYS_DATA_SOURCE_DEF               */                                                                                          
/*==============================================================*/
	CREATE TABLE SYS_DATA_SOURCE
	(
	  ID_            NUMBER(18) NOT NULL,
	  NAME_          VARCHAR2(64),
	  ALIAS_         VARCHAR2(64),
	  DB_TYPE_       VARCHAR2(64),
	  SETTING_JSON_  CLOB,
	  INIT_ON_START_ NUMBER,
	  ENABLED_       NUMBER,
	  CLASS_PATH_    VARCHAR2(128),
	  INIT_METHOD_   VARCHAR2(128),
	  CLOSE_METHOD_  VARCHAR2(128)
	);

	ALTER TABLE SYS_DATA_SOURCE
	  ADD CONSTRAINT ID_PRIMAR PRIMARY KEY (ID_);
	ALTER TABLE SYS_DATA_SOURCE
	  ADD CONSTRAINT UNIQUE_COLUMN UNIQUE (NAME_, ALIAS_);
	  
	CREATE TABLE SYS_DATA_SOURCE_DEF
	(
	  ID_           NUMBER(18) NOT NULL,
	  NAME_         VARCHAR2(64) NOT NULL,
	  CLASS_PATH_   VARCHAR2(128) NOT NULL,
	  SETTING_JSON_ CLOB,
	  INIT_METHOD_  VARCHAR2(64),
	  IS_SYSTEM_    NUMBER,
	  CLOSE_METHOD_ VARCHAR2(64)
	);
	
	ALTER TABLE SYS_DATA_SOURCE_DEF
	  ADD CONSTRAINT ID_PRIMARY PRIMARY KEY (ID_)
	  USING INDEX;
	ALTER TABLE SYS_DATA_SOURCE_DEF
	  ADD CONSTRAINT UNIQUE_COLUMNS UNIQUE (NAME_, CLASS_PATH_)
	  USING INDEX;
  

CREATE TABLE BPM_DEF_AUTH_TYPE  (
   ID   NUMBER(18)  not null,
   AUTHORIZE_ID  NUMBER(18) not null,
   AUTHORIZE_TYPE  VARCHAR2(64),
   PRIMARY KEY (ID)
);
COMMIT;
comment on table BPM_DEF_AUTH_TYPE
  is '流程授权类型表';

  
 
CREATE TABLE BPM_FORM_DEF_HI  (
   HISID                NUMBER(18)                      NOT NULL,
   FORMDEFID            NUMBER(18)                      NOT NULL,
   CATEGORYID           NUMBER(18),
   FORMKEY              VARCHAR2(128),
   SUBJECT              VARCHAR2(128),
   FORMDESC             VARCHAR2(200),
   HTML                 CLOB,
   TEMPLATE             CLOB,
   ISDEFAULT            SMALLINT,
   VERSIONNO            NUMBER(18),
   TABLEID              NUMBER(18),
   ISPUBLISHED          SMALLINT,
   PUBLISHEDBY          VARCHAR2(20),
   PUBLISHTIME          DATE,
   TABTITLE             VARCHAR2(500),
   DESIGNTYPE           SMALLINT,
   TEMPLATESID          VARCHAR2(300),
   CREATEBY             NUMBER(18,0),
   CREATOR              VARCHAR2(50),
   CREATETIME           DATE
);

ALTER TABLE BPM_FORM_DEF_HI
   ADD CONSTRAINT PK_BPM_FORM_DEF_HI PRIMARY KEY (HISID);

CREATE TABLE BPM_ASSIGN_USERS 
(
   ID                   NUMBER(18)              NOT NULL,
   RUNID                NUMBER(18),
   DEFKEY               VARCHAR2(128),
   NODEID               VARCHAR2(128),
   NODENAME             VARCHAR2(256),
   USERID               VARCHAR2(20),
   USERNAME             VARCHAR2(127),
   STARTTIME            DATE,
   ENDTIME              DATE,
   PRIMARY KEY  (ID)
);
COMMENT ON TABLE BPM_ASSIGN_USERS  IS '发起人设置流程执行人表';

CREATE TABLE BPM_NODE_SQL
(
   ID                   NUMBER(18)           DEFAULT 0 NOT NULL,
   NAME                 VARCHAR2(100),
   DSALIAS              VARCHAR2(100),
   ACTDEFID             VARCHAR2(100),
   NODEID               VARCHAR2(50),
   ACTION_              VARCHAR2(50),
   SQL_                 VARCHAR2(4000),
   DESC_                VARCHAR2(400),
   PRIMARY KEY (ID)
);

COMMENT ON TABLE BPM_NODE_SQL  IS '字段的备注';

CREATE TABLE BPM_SUBTABLE_SORT 
(
   ID                   NUMBER(20)           NOT NULL,
   ACTDEFKEY            VARCHAR2(120),
   TABLENAME            VARCHAR2(120),
   FIELDSORT            VARCHAR2(500),
   CONSTRAINT PK_BPM_SUBTABLE_SORT PRIMARY KEY (ID)
);

COMMENT ON COLUMN BPM_SUBTABLE_SORT.ID IS
'主键';

COMMENT ON COLUMN BPM_SUBTABLE_SORT.ACTDEFKEY IS
'流程定义key';

COMMENT ON COLUMN BPM_SUBTABLE_SORT.TABLENAME IS
'子表名称';

COMMENT ON COLUMN BPM_SUBTABLE_SORT.FIELDSORT IS
'字段名，json数据';

CREATE TABLE SYS_WORD_TEMPLATE (
  ID_ 			NUMBER(18) NOT NULL,
  NAME_ 		VARCHAR2(200),
  ALIAS_ 		VARCHAR2(100),
  FILE_ID_ 		NUMBER(18),
  CREATETIME_ 	DATE,
  TYPE_ 		VARCHAR2(20),
  TABLE_ID_ 	NUMBER(18),
  TABLE_NAME_ 	VARCHAR2(128),
  DS_ALIAS_ 	VARCHAR2(50),
  SQL_ 			CLOB,
  PRIMARY KEY (ID_)
);
COMMENT ON COLUMN SYS_WORD_TEMPLATE.ID_ IS '主键';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.NAME_ IS '报表名称';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.ALIAS_ IS '报表别名';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.FILE_ID_ IS 'word模板附件ID';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.CREATETIME_ IS '创建时间';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.TYPE_ IS '类型\r\nSQL\r\n自定义表单\r\n1.如果类型为sql则根据数据源，sql语句获取数据。\r\n2.为表单则根据表定义和主键获取数据。';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.TABLE_ID_ IS '表ID';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.TABLE_NAME_ IS '表名';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.DS_ALIAS_ IS '数据源别名';
COMMENT ON COLUMN SYS_WORD_TEMPLATE.SQL_ IS 'Sql语句';


/*==============================================================*/
/* Table: SYS_INDEX_LAYOUT                                      */
/*==============================================================*/
CREATE TABLE SYS_INDEX_LAYOUT 
(
   ID                   NUMBER(18)           NOT NULL,
   NAME                 VARCHAR2(100),
   MEMO                 VARCHAR2(256),
   TEMPLATE_HTML        CLOB,
   SN                   NUMBER(18),
   CONSTRAINT PK_SYS_INDEX_LAYOUT PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_INDEX_LAYOUT.ID IS
'主键';

COMMENT ON COLUMN SYS_INDEX_LAYOUT.NAME IS
'布局名称';

COMMENT ON COLUMN SYS_INDEX_LAYOUT.MEMO IS
'布局描述';

COMMENT ON COLUMN SYS_INDEX_LAYOUT.TEMPLATE_HTML IS
'模版内容';

COMMENT ON COLUMN SYS_INDEX_LAYOUT.SN IS
'排序';


/*==============================================================*/
/* Table: SYS_INDEX_LAYOUT_MANAGE                               */
/*==============================================================*/
CREATE TABLE SYS_INDEX_LAYOUT_MANAGE 
(
   ID                   NUMBER(18)           NOT NULL,
   NAME                 VARCHAR2(100),
   MEMO                 VARCHAR2(256),
   ORG_ID               NUMBER(18),
   TEMPLATE_HTML        CLOB,
   DESIGN_HTML          CLOB,
   IS_DEF               NUMBER(18),
   CONSTRAINT PK_SYS_INDEX_LAYOUT_MANAGE PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.ID IS
'主键';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.NAME IS
'布局名称';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.MEMO IS
'布局描述';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.ORG_ID IS
'组织ID';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.TEMPLATE_HTML IS
'模版内容';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.DESIGN_HTML IS
'设计模版';

COMMENT ON COLUMN SYS_INDEX_LAYOUT_MANAGE.IS_DEF IS
'是否是默认';

/*==============================================================*/
/* Table: SYS_INDEX_MY_LAYOUT                                   */
/*==============================================================*/
CREATE TABLE SYS_INDEX_MY_LAYOUT 
(
   ID                   NUMBER(18)           NOT NULL,
   USER_ID              NUMBER(18),
   TEMPLATE_HTML        CLOB,
   DESIGN_HTML          CLOB,
   LAYOUT_ID            NUMBER(18),
   CONSTRAINT PK_SYS_INDEX_MY_LAYOUT PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_INDEX_MY_LAYOUT.ID IS
'主键';

COMMENT ON COLUMN SYS_INDEX_MY_LAYOUT.USER_ID IS
'用户ID';

COMMENT ON COLUMN SYS_INDEX_MY_LAYOUT.TEMPLATE_HTML IS
'模版内容';

COMMENT ON COLUMN SYS_INDEX_MY_LAYOUT.DESIGN_HTML IS
'设计模版';

COMMENT ON COLUMN SYS_INDEX_MY_LAYOUT.LAYOUT_ID IS
'布局管理ID';


/*==============================================================*/
/* Table: SYS_ORG_TACTIC                                        */
/*==============================================================*/
CREATE TABLE SYS_ORG_TACTIC 
(
   ID                   NUMBER(18)           NOT NULL,
   ORG_TACTIC           NUMBER(18),
   DEM_ID               NUMBER(18),
   ORG_LEVEL            NUMBER(18),
   ORG_SELECT_ID        NUMBER(18),
   CONSTRAINT PK_SYS_ORG_TACTIC PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_ORG_TACTIC.ID IS
'主键';

COMMENT ON COLUMN SYS_ORG_TACTIC.ORG_TACTIC IS
'策略（0、无策略1、按照级别 2、手工选择，3、按照级别+手工选择）';

COMMENT ON COLUMN SYS_ORG_TACTIC.DEM_ID IS
'维度ID';

COMMENT ON COLUMN SYS_ORG_TACTIC.ORG_LEVEL IS
'组织级别';

COMMENT ON COLUMN SYS_ORG_TACTIC.ORG_SELECT_ID IS
'组织选择ID';

/*==============================================================*/
/* Table: SYS_INDEX_COLUMN                                      */
/*==============================================================*/
CREATE TABLE SYS_INDEX_COLUMN 
(
   ID                   INTEGER              NOT NULL,
   NAME                 VARCHAR2(100),
   ALIAS                VARCHAR2(100),
   CATALOG              INTEGER,
   COL_TYPE             SMALLINT,
   DATA_MODE            SMALLINT,
   DATA_FROM            VARCHAR2(100),
   DS_ALIAS             VARCHAR2(100),
   DS_NAME              VARCHAR2(100),
   COL_HEIGHT           INTEGER,
   COL_URL              VARCHAR2(100),
   TEMPLATE_HTML        CLOB,
   IS_PUBLIC            SMALLINT,
   ORG_ID               INTEGER,
   SUPPORT_REFESH       SMALLINT,
   REFESH_TIME          INTEGER,
   SHOW_EFFECT          SMALLINT,
   MEMO                 VARCHAR2(256),
   DATA_PARAM           VARCHAR2(2000),
   NEEDPAGE             SMALLINT,
   CONSTRAINT PK_SYS_INDEX_COLUMN PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_INDEX_COLUMN.ID IS
'主键';

COMMENT ON COLUMN SYS_INDEX_COLUMN.NAME IS
'栏目名称';

COMMENT ON COLUMN SYS_INDEX_COLUMN.ALIAS IS
'栏目别名';

COMMENT ON COLUMN SYS_INDEX_COLUMN.CATALOG IS
'栏目分类';

COMMENT ON COLUMN SYS_INDEX_COLUMN.COL_TYPE IS
'栏目类型（0：一般栏目、1：图表栏目、2、滚动栏目）';

COMMENT ON COLUMN SYS_INDEX_COLUMN.DATA_MODE IS
'数据加载方式(0:服务方法;1:自定义查询)';

COMMENT ON COLUMN SYS_INDEX_COLUMN.DATA_FROM IS
'数据来源';

COMMENT ON COLUMN SYS_INDEX_COLUMN.DS_ALIAS IS
'数据别名';

COMMENT ON COLUMN SYS_INDEX_COLUMN.DS_NAME IS
'数据源名称';

COMMENT ON COLUMN SYS_INDEX_COLUMN.COL_HEIGHT IS
'栏目高度';

COMMENT ON COLUMN SYS_INDEX_COLUMN.COL_URL IS
'栏目URL';

COMMENT ON COLUMN SYS_INDEX_COLUMN.TEMPLATE_HTML IS
'栏目模版';

COMMENT ON COLUMN SYS_INDEX_COLUMN.IS_PUBLIC IS
'是否公共栏目';

COMMENT ON COLUMN SYS_INDEX_COLUMN.ORG_ID IS
'所属组织ID';

COMMENT ON COLUMN SYS_INDEX_COLUMN.SUPPORT_REFESH IS
'是否支持刷新';

COMMENT ON COLUMN SYS_INDEX_COLUMN.REFESH_TIME IS
'刷新时间';

COMMENT ON COLUMN SYS_INDEX_COLUMN.SHOW_EFFECT IS
'展示效果';

COMMENT ON COLUMN SYS_INDEX_COLUMN.MEMO IS
'描述';

/*==============================================================*/
/* Table: SYS_POPUP_REMIND                            */
/*==============================================================*/
CREATE TABLE sys_popup_remind (
	ID NUMBER(18) NOT NULL,
	SUBJECT varchar2(200) ,
	URL varchar2(200) ,
	SQL_ varchar2(2000) ,
	DSALIAS varchar2(20) ,
	SN  INTEGER,
	ENABLED INTEGER,
  	CREATETIME TIMESTAMP,
  	CREATOR varchar2(50) ,
  	DESC_ varchar2(300),
	CONSTRAINT PK_SYS_POPUP_REMIND PRIMARY KEY (ID)
);
/*==============================================================*/
/* Table: SYS_OBJ_RIGHTS                            */
/*==============================================================*/
CREATE TABLE sys_obj_rights (
  	ID NUMBER(18) NOT NULL ,
  	OBJ_TYPE varchar(20) ,
  	OBJECT_ID NUMBER(18)  ,
  	OWNER_ID NUMBER(18)  ,
  	OWNER varchar(50)  ,
  	RIGHT_TYPE varchar(10) ,
  	CONSTRAINT PK_SYS_OBJ_RIGHTS PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: SYS_PROPERTY                                          */
/*==============================================================*/
CREATE TABLE SYS_PROPERTY 
(
   ID                   NUMBER(18)           NOT NULL,
   NAME_                VARCHAR2(128),
   ALIAS_               VARCHAR2(128),
   VALUE_               VARCHAR2(256),
   GROUP_               VARCHAR2(128),
   SN_                  SMALLINT,
   MEMO                 VARCHAR2(200),
   CONSTRAINT PK_SYS_PROPERTY PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_PROPERTY.ID IS
'主键';

COMMENT ON COLUMN SYS_PROPERTY.NAME_ IS
'名字';

COMMENT ON COLUMN SYS_PROPERTY.ALIAS_ IS
'别名';

COMMENT ON COLUMN SYS_PROPERTY.VALUE_ IS
'值';

COMMENT ON COLUMN SYS_PROPERTY.GROUP_ IS
'分组';

COMMENT ON COLUMN SYS_PROPERTY.MEMO IS
'参数描述';

/*==============================================================*/
/* Table: BPM_BATCH_APPROVAL                                    */
/*==============================================================*/
CREATE TABLE BPM_BATCH_APPROVAL 
(
   ID                   NUMBER(18)           NOT NULL,
   DEF_KEY              VARCHAR2(128),
   NODE_ID              VARCHAR2(128),
   TABLE_ID             NUMBER(18),
   FIELD_JSON           CLOB,
   CONSTRAINT PK_BPM_BATCH_APPROVAL PRIMARY KEY (ID)
);

COMMENT ON COLUMN BPM_BATCH_APPROVAL.ID IS
'主键';

COMMENT ON COLUMN BPM_BATCH_APPROVAL.DEF_KEY IS
'流程定义Key';

COMMENT ON COLUMN BPM_BATCH_APPROVAL.NODE_ID IS
'节点ID';

COMMENT ON COLUMN BPM_BATCH_APPROVAL.TABLE_ID IS
'自定义表ID';

COMMENT ON COLUMN BPM_BATCH_APPROVAL.FIELD_JSON IS
'字段设置';


/*==============================================================*/
/* Table: SYS_BULLETIN                                          */
/*==============================================================*/
CREATE TABLE SYS_BULLETIN 
(
   ID                   NUMBER(18)           NOT NULL,
   SUBJECT              VARCHAR2(200),
   COLUMNID             NUMBER(18),
   CONTENT              CLOB,
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(50),
   CREATETIME           DATE,
   ATTACHMENT           VARCHAR2(400),
   CONSTRAINT PK_SYS_BULLETIN PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_BULLETIN.ID IS
'主键';

COMMENT ON COLUMN SYS_BULLETIN.SUBJECT IS
'主题';

COMMENT ON COLUMN SYS_BULLETIN.COLUMNID IS
'栏目id';

COMMENT ON COLUMN SYS_BULLETIN.CONTENT IS
'内容';

COMMENT ON COLUMN SYS_BULLETIN.CREATORID IS
'创建人id';

COMMENT ON COLUMN SYS_BULLETIN.CREATOR IS
'创建人';

COMMENT ON COLUMN SYS_BULLETIN.CREATETIME IS
'创建时间';

COMMENT ON COLUMN SYS_BULLETIN.ATTACHMENT IS
'附件';

/*==============================================================*/
/* Table: SYS_BULLETIN_COLUMN                                   */
/*==============================================================*/
CREATE TABLE SYS_BULLETIN_COLUMN 
(
   ID                   NUMBER(18)           NOT NULL,
   NAME                 VARCHAR2(100),
   ALIAS                VARCHAR2(20),
   ISPUBLIC             SMALLINT,
   TENANTID             NUMBER(18),
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(50),
   CREATETIME           DATE,
   STATUS               SMALLINT,
   CONSTRAINT PK_SYS_BULLETIN_COLUMN PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.ID IS
'主键';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.NAME IS
'栏目名';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.ALIAS IS
'别名';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.ISPUBLIC IS
'是否是集团栏目';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.TENANTID IS
'组织id';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.CREATORID IS
'创建人id';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.CREATOR IS
'创建人';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.CREATETIME IS
'创建时间';

COMMENT ON COLUMN SYS_BULLETIN_COLUMN.STATUS IS
'状态';




/*==============================================================*/
/* Table: SYS_PLAN   日程表                                    */
/*==============================================================*/
CREATE TABLE SYS_PLAN  (
    ID          	NUMBER(18)          NOT NULL,
	TASKNAME		VARCHAR2(200),
	SUBMITID  		NUMBER(18),
	SUBMITOR  		VARCHAR2(50),
	CHARGEID  		NUMBER(18),
	CHARGE  		VARCHAR2(50),
	STARTTIME 	    DATE,
	ENDTIME 	    DATE,
	PROJECTNAME     VARCHAR2(200),
	DOC             VARCHAR2(400),
	CUSTOMERID  	NUMBER(18),
	CUSTOMER  		VARCHAR2(200),
	RUNID  	     	NUMBER(18),
	RUNNAME         VARCHAR2(200),
	RATE  	     	NUMBER(18),
	CREATETIME      DATE,
	DESCRIPTION     VARCHAR2(2000),
    PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_PLAN IS
'日程表';
COMMENT ON COLUMN SYS_PLAN.ID IS 
'日程表主键';
COMMENT ON COLUMN SYS_PLAN.TASKNAME IS 
'任务名称';
COMMENT ON COLUMN SYS_PLAN.SUBMITID IS 
'提交人ID';
COMMENT ON COLUMN SYS_PLAN.SUBMITOR IS 
'提交人';
COMMENT ON COLUMN SYS_PLAN.CHARGEID IS 
'负责人ID';
COMMENT ON COLUMN SYS_PLAN.CHARGE IS 
'负责人';
COMMENT ON COLUMN SYS_PLAN.STARTTIME IS 
'开始时间';
COMMENT ON COLUMN SYS_PLAN.ENDTIME IS 
'结束时间';
COMMENT ON COLUMN SYS_PLAN.PROJECTNAME IS 
'项目名称';
COMMENT ON COLUMN SYS_PLAN.DOC IS 
'相关文档';
COMMENT ON COLUMN SYS_PLAN.CUSTOMERID IS 
'相关客户ID';
COMMENT ON COLUMN SYS_PLAN.CUSTOMER IS 
'相关客户';
COMMENT ON COLUMN SYS_PLAN.RUNID IS 
'相关工单ID';
COMMENT ON COLUMN SYS_PLAN.RUNNAME IS 
'工单名称';
COMMENT ON COLUMN SYS_PLAN.RATE IS 
'完成进度';
COMMENT ON COLUMN SYS_PLAN.CREATETIME IS 
'创建时间';
COMMENT ON COLUMN SYS_PLAN.DESCRIPTION IS 
'任务内容';



/*==============================================================*/
/* Table: SYS_PLAN_PARTICIPANTS  日程参与表                                     */
/*==============================================================*/
CREATE TABLE SYS_PLAN_PARTICIPANTS  (
    ID          	NUMBER(18)          NOT NULL,
	PLANID  		NUMBER(18),
	PARTICIPANTID  	NUMBER(18),
	PARTICIPANT  	VARCHAR2(50),
	PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_PLAN_PARTICIPANTS IS
'日程参与表 ';
COMMENT ON COLUMN SYS_PLAN_PARTICIPANTS.ID IS 
'日程参与表主键';
COMMENT ON COLUMN SYS_PLAN_PARTICIPANTS.PLANID IS 
'日程ID';
COMMENT ON COLUMN SYS_PLAN_PARTICIPANTS.PARTICIPANTID IS 
'参与者ID';
COMMENT ON COLUMN SYS_PLAN_PARTICIPANTS.PARTICIPANT IS 
'参与者';




/*==============================================================*/
/* Table: SYS_PLAN_SUBSCRIBE  日程订阅表                                   */
/*==============================================================*/
CREATE TABLE SYS_PLAN_SUBSCRIBE  (
    ID          	NUMBER(18)          NOT NULL,
	PLANID  		NUMBER(18),
	SUBSCRIBEID  	NUMBER(18),
	SUBSCRIBE  	    VARCHAR2(50),
	SUBSCRIBETIME   DATE,
	PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_PLAN_SUBSCRIBE IS
'日程订阅表 ';
COMMENT ON COLUMN SYS_PLAN_SUBSCRIBE.ID IS 
'日程订阅表主键';
COMMENT ON COLUMN SYS_PLAN_SUBSCRIBE.PLANID IS 
'日程ID';
COMMENT ON COLUMN SYS_PLAN_SUBSCRIBE.SUBSCRIBEID IS 
'订阅者ID';
COMMENT ON COLUMN SYS_PLAN_SUBSCRIBE.SUBSCRIBE IS 
'订阅者';
COMMENT ON COLUMN SYS_PLAN_SUBSCRIBE.SUBSCRIBETIME IS 
'订阅时间';


/*==============================================================*/
/* Table: SYS_PLAN_EXCHANGE  日程交流表                                      */
/*==============================================================*/
CREATE TABLE SYS_PLAN_EXCHANGE  (
	ID          	NUMBER(18)          NOT NULL,
	PLANID  		NUMBER(18),
	SUBMITID    	NUMBER(18),
	SUBMITOR  	    VARCHAR2(50),
	DOC  	        VARCHAR2(400),
	CONTENT  	    VARCHAR2(1000),
	CREATETIME      DATE,
	PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_PLAN_EXCHANGE IS
'日程交流表 ';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.ID IS 
'日程交流表主键';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.PLANID IS 
'日程ID';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.SUBMITID IS 
'提交人ID';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.SUBMITOR IS 
'提交人';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.DOC IS 
'相关文档';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.CONTENT IS 
'交流内容';
COMMENT ON COLUMN SYS_PLAN_EXCHANGE.CREATETIME IS 
'提交时间';


/*==============================================================*/
/* Table: SYS_TRANS_DEF                                         */
/*==============================================================*/
CREATE TABLE SYS_TRANS_DEF 
(
   ID                 NUMBER(18)           NOT NULL,
   NAME               VARCHAR2(100),
   SELECTSQL            CLOB,
   UPDATESQL            CLOB,
   STATE                SMALLINT,
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(50),
   CREATETIME           DATE,
   LOGCONTENT           VARCHAR2(500),
   CONSTRAINT PK_SYS_TRANS_DEF PRIMARY KEY (ID)
);


/*==============================================================*/
/* Table: SYS_OBJ_LOG                                           */
/*==============================================================*/
CREATE TABLE SYS_OBJ_LOG 
(
   ID                   NUMBER(18)           NOT NULL,
   OPERATOR_ID          NUMBER(18),
   OPERATOR             VARCHAR2(255),
   CREATE_TIME          DATE,
   NAME                 VARCHAR2(255),
   CONTENT              CLOB,
   OBJ_TYPE             VARCHAR2(255),
   PARAM                VARCHAR2(255),
   CONSTRAINT PK_SYS_OBJ_LOG PRIMARY KEY (ID)
);

CREATE TABLE BPM_FORM_DIALOG_COMBINATE 
(
   ID                   NUMBER(18)           NOT NULL,
   NAME                 VARCHAR2(200),
   ALIAS                VARCHAR2(200),
   WIDTH                INT,
   HEIGHT               INT,
   TREE_DIALOG_ID       NUMBER(18),
   TREE_DIALOG_NAME     VARCHAR2(200),
   LIST_DIALOG_ID       NUMBER(18),
   LIST_DIALOG_NAME     VARCHAR2(200),
   FIELD                VARCHAR2(1000),
   CONSTRAINT PK_BPM_FORM_DIALOG_COMBINATE PRIMARY KEY (ID)
);

ALTER TABLE BPM_FORM_DIALOG_COMBINATE
   ADD CONSTRAINT AK_KEY_3_BPM_FORM UNIQUE (ALIAS);
   
CREATE TABLE FORM_DEF_TREE 
(
   ID                   NUMBER(18)           NOT NULL,
   FORM_DEF_ID          NUMBER(18),
   NAME                 VARCHAR2(100),
   ALIAS                VARCHAR2(50),
   TREE_ID              VARCHAR2(100),
   PARENT_ID            VARCHAR2(100),
   DISPLAY_FIELD        VARCHAR2(100),
   LOAD_TYPE            SMALLINT,
   ROOT_ID              VARCHAR2(255),
   CONSTRAINT PK_FORM_DEF_TREE PRIMARY KEY (ID)
);
ALTER TABLE FORM_DEF_TREE
   ADD CONSTRAINT AK_KEY_3_FORM_DEF UNIQUE (ALIAS);
   
   
CREATE TABLE FORM_DEF_COMBINATE 
(
   ID                   INTEGER              DEFAULT 0 NOT NULL,
   NAME                 VARCHAR2(200),
   ALIAS                VARCHAR2(100),
   TREE_DIALOG_ID       INTEGER,
   TREE_DIALOG_NAME     VARCHAR2(200),
   FORM_DEF_ID          INTEGER,
   FORM_DEF_NAME        VARCHAR2(200),
   FIELD                CLOB,
   CONSTRAINT PK_FORM_DEF_COMBINATE PRIMARY KEY (ID)
);

COMMENT ON COLUMN FORM_DEF_COMBINATE.TREE_DIALOG_ID IS
'树对话框ID';

COMMENT ON COLUMN FORM_DEF_COMBINATE.FORM_DEF_ID IS
'表单ID 注意 表单需要有业务数据模板才行';

COMMENT ON COLUMN FORM_DEF_COMBINATE.FIELD IS
'树数据返回数据对应列表数据的查询条件';

ALTER TABLE FORM_DEF_COMBINATE
   ADD CONSTRAINT "ALIAS_UNIQUE" UNIQUE (ALIAS);
   
   
CREATE TABLE SYS_BUS_EVENT 
(
   ID                   INTEGER              DEFAULT 0 NOT NULL,
   FORMKEY              VARCHAR2(50)         DEFAULT NULL,
   JS_PRE_SCRIPT        VARCHAR2(4000)       DEFAULT '',
   JS_AFTER_SCRIPT      VARCHAR2(2000)       DEFAULT '',
   PRE_SCRIPT           VARCHAR2(2000)       DEFAULT NULL,
   AFTER_SCRIPT         VARCHAR2(2000)       DEFAULT NULL,
   CONSTRAINT PK_SYS_BUS_EVENT PRIMARY KEY (ID)
);

COMMENT ON COLUMN SYS_BUS_EVENT.ID IS
'主键';

COMMENT ON COLUMN SYS_BUS_EVENT.FORMKEY IS
'表单key';

COMMENT ON COLUMN SYS_BUS_EVENT.JS_PRE_SCRIPT IS
'JS前置脚本';

COMMENT ON COLUMN SYS_BUS_EVENT.JS_AFTER_SCRIPT IS
'js后置脚本';

COMMENT ON COLUMN SYS_BUS_EVENT.PRE_SCRIPT IS
'JAVA前置脚本';

COMMENT ON COLUMN SYS_BUS_EVENT.AFTER_SCRIPT IS
'java后置脚本';


CREATE TABLE BPM_NEWFLOW_TRIGGER (
 ID NUMBER(20) NOT NULL ,
 NAME VARCHAR2(255 BYTE) NULL ,
 NODEID VARCHAR2(255 BYTE) NULL ,
 ACTION VARCHAR2(50 BYTE) NULL ,
 FLOWKEY VARCHAR2(255 BYTE) NULL ,
 TRIGGERFLOWKEY VARCHAR2(255 BYTE) NULL ,
 TRIGGERFLOWNAME VARCHAR2(500 BYTE) NULL ,
 JSONMAPING CLOB NULL ,
 TRIGGERJSON CLOB NULL,
 NOTE VARCHAR2(2000 BYTE) NULL 
);

ALTER TABLE  BPM_NEWFLOW_TRIGGER ADD PRIMARY KEY (ID);

CREATE TABLE BPM_NODEMSG_TEMPLATE 
(
   ID                   NUMBER(20)           NOT NULL,
   DEFID                NUMBER(20),
   PARENTDEFID          NUMBER(20),
   NODEID               VARCHAR2(20),
   TITLE                VARCHAR2(200),
   HTML                 CLOB,
   TEXT                 CLOB,
   CONSTRAINT PK_BPM_NODEMSG_TEMPLATE PRIMARY KEY (ID)
);


CREATE TABLE SYS_EXCEL_TEMP 
(
   ID                   NUMBER(20)           NOT NULL,
   TEMP_CODE            VARCHAR2(100)        NOT NULL,
   TEMP_NAME            VARCHAR2(100),
   TEMP_DES             VARCHAR2(500),
   TEMP_DES_HEIGHT      NUMBER(4),
   MEMO                 VARCHAR2(500),
   TEMP_DATA_SAMPLE     CLOB,
   CONSTRAINT PK_SYS_EXCEL_TEMP PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_EXCEL_TEMP IS
'Excel模板';

COMMENT ON COLUMN SYS_EXCEL_TEMP.ID IS
'主键';

COMMENT ON COLUMN SYS_EXCEL_TEMP.TEMP_CODE IS
'模板代码';

COMMENT ON COLUMN SYS_EXCEL_TEMP.TEMP_NAME IS
'模板名称';

COMMENT ON COLUMN SYS_EXCEL_TEMP.TEMP_DES IS
'模板填写说明';

COMMENT ON COLUMN SYS_EXCEL_TEMP.TEMP_DES_HEIGHT IS
'填写说明行高';

COMMENT ON COLUMN SYS_EXCEL_TEMP.MEMO IS
'备注';

COMMENT ON COLUMN SYS_EXCEL_TEMP.TEMP_DATA_SAMPLE IS
'模板样例数据';


CREATE TABLE SYS_EXCEL_TEMP_DETAIL 
(
   ID                   NUMBER(20)           NOT NULL,
   TEMP_ID              NUMBER(20),
   COLUMN_NAME          VARCHAR2(100),
   COLUMN_TYPE          NUMBER(4),
   COLUMN_LEN           NUMBER(4),
   SHOW_INDEX           NUMBER(4),
   CONSTRAINT PK_SYS_EXCEL_TEMP_DETAIL PRIMARY KEY (ID)
);

COMMENT ON TABLE SYS_EXCEL_TEMP_DETAIL IS
'Excel模板明细';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.ID IS
'主键';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.TEMP_ID IS
'模板ID';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.COLUMN_NAME IS
'列名称';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.COLUMN_TYPE IS
'数据类型';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.COLUMN_LEN IS
'列长';

COMMENT ON COLUMN SYS_EXCEL_TEMP_DETAIL.SHOW_INDEX IS
'显示顺序';



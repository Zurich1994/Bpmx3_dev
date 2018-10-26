package com.hotent.core.bpm.util;

/**
 * 流程使用的常量。
 * @author ray
 *
 */
public class BpmConst {

	
	/**
	 * 上一步的组织ID。
	 */
	public static final String PRE_ORG_ID="preOrgId";
	/**
	 * 发起人的组织ID。
	 */
	public static final String START_ORG_ID="startOrgId";
	
	/**
	 * 发起人的岗位ID。
	 */
	public static final String START_POS_ID="startPosId";
	/**
	 * 发起人的职务ID。
	 */
	public static final String START_JOB_ID="startJobId";
	
	
	
	/**
	 * 开始变量
	 */
	public static final String StartUser="startUser";
	/**
	 * 上一个用户变量。
	 */
	public static final String PrevUser="prevUser";
	/**
	 * 开始
	 */
	public static final String StartEvent="start";
	
	public static final String EndEvent="end";
	
	public static final String CreateEvent="create";
	
	public static final String CompleteEvent="complete";
	
	public static final String AssignmentEvent="assignment";
	
	public static final String PROCESS_EXT_VARNAME="outPassVars";
	
	public static final String PROCESS_INNER_VARNAME="innerPassVars";
	
	public static final String FLOW_RUN_SUBJECT="subject_";
	
	public static final String FLOW_SERIALNO="serialNo_";
	
	public static final String FLOW_INFORM_TYPE="informType";
	/**
	 * 是否为外部的调用。
	 */
	public static final String IS_EXTERNAL_CALL="isExtCall";
	/**
	 * 1.事件前置脚本
	 */
	public static final Integer StartScript=1;
	
	/**
	 * 2.事件后置脚本
	 */
	public static final Integer EndScript=2;
	
	/**
	 * 3.脚本节点
	 */
	public static final Integer ScriptNodeScript=3;
	/**
	 * 4.分配脚本
	 */
	public static final Integer AssignScript=4;
	
	/**
	 * 5.开始前置脚本
	 */
	public static final Integer StartBeforeScript=5;
	
	/**
	 * 6.结束前置脚本
	 */
	public static final Integer EndBeforeScript=6;
	
	/**
	 * 节点的审批状态值变量名
	 */
	public static final String NODE_APPROVAL_STATUS="approvalStatus";
	/**
	 * 节点的审批意见变量名
	 */
	public static final String NODE_APPROVAL_CONTENT="approvalContent";
	
	/**
	 * 流程驳回。
	 */
	public static final Integer TASK_BACK=1;
	
	/**
	 * 流程驳回到发起人。
	 */
	public static final Integer TASK_BACK_TOSTART=2;
	
	
	
	/**
	 * 在线的表单
	 */
	public static final Short OnLineForm=0;
	
	/**
	 * 外部表单
	 */
	public static final Short UrlForm=1;
	
	/**
	 * 表单主键PK正则表达式。
	 */
	public static final String FORM_PK_REGEX="\\{pk\\}";
	/**
	 * 流程主键key
	 */
	public static final String FLOW_BUSINESSKEY="businessKey";
	
	/**
	 * 流程运行ID。
	 */
	public static final String FLOW_RUNID="flowRunId";
	
	/**
	 * 父流程定义ID
	 */
	public static final String FLOW_PARENT_ACTDEFID="parentActDefId";
	
	/**
	 * 主流程定义ID
	 */
	public static final String FLOW_MAIN_ACTDEFID="mainActDefId";
	
	/**
	 * 会签人员人员列表
	 */
	public static final String SIGN_USERIDS="signUsers";
	
	/**
	 * 多实例子流程节点人员列表
	 */
	public static final String SUBPRO_MULTI_USERIDS="subAssignIds";
	
	/**
	 * 多实例外部子流程节点人员列表
	 */
	public static final String SUBPRO_EXT_MULTI_USERIDS="subExtAssignIds";
	
	/**
	 * 邮件
	 */
	public static final String MESSAGE_TYPE_MAIL="1";
	
	/**
	 * 短消息
	 */
	public static final String MESSAGE_TYPE_SMS="2";
	
	/**
	 * 内部消息
	 */
	public static final String MESSAGE_TYPE_INNER="3";
	
	/**
	 * 原始的执行人（代理的授权人）
	 */
//	 public static final String ORIGINAL_EXECUTOR="original_executor";
	 
	 /**
	  * 空的用户ID。
	  */
	 public static final String EMPTY_USER="0";
	 
	//表单用户常量
	public static String PREVIEW_FORMUSER="__formUserId__";
	//组织用户常量
	public static String PREVIEW_FORMORG="__formOrgId__";
	
	//表单用户常量
	public static String PREVIEW_FORMPOS="__formPosId__";
	//组织用户常量
	public static String PREVIEW_FORMROLE="__formRoleId__";
	
	public static String LOCAL_DATASOURCE="LOCAL";
	
	/**
	 * executionid
	 */
	public static String EXECUTION_ID_="executionId";
	
	/**
	 * 流程定义常量。
	 */
	public final static String BPM_DEF="bpm_definition";
	
	
	public final static String BPM_DEFKEY="flowKey";
	
	/**
	 * 意见字段，这个为临时变量。
	 */
	public final static String OPINION_FIELD="opinionField";
	
	/**
	 * 
	 */
	public final static String OPINION_SUPPORTHTML="optionHtml";
	
}

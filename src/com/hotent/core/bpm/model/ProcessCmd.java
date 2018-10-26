package com.hotent.core.bpm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;

/**
 * 流程执行命令实体。
 * @author csx
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement
public class ProcessCmd{	
	/**
	 * 流程定义ID 来自Activiti的流程定义ID
	 */
	private String actDefId;
	/**
	 * 流程定义Key
	 */
	private String flowKey;
	
	/**
	 * ACT流程生成的任务ID
	 */
	private String taskId;
	
	/**
	 * 流程运行实例ID
	 */
	private Long runId = 0L;
	/**
	 * 流程实例标题
	 */
	private String subject="";
	/**
	 * 流程目标节点ActivityId，用于自由跳,及回退
	 */
	private String destTask;
	
	/**
	 * 下一步任务节点id。
	 * <pre>
	 * 可以为多个。
	 * ['userTask1','usertask2']
	 * </pre>
	 */
	private String[] lastDestTaskIds;
	/**
	 * 最终任务处理人员。
	 * <pre>
	 * 节点的执行人员，这个数组的长度和lastDestTaskIds数据的长度一致。
	 * 每个数组的值为人员，人员之间使用逗号分隔。
	 * ['user^用户ID^用户名,org^组织id^组织名称,role^角色ID^角色名','org^组织ID^组织名称,org^组织id^组织名称']
	 * </pre>
	 */
	private String[] lastDestTaskUids;
	
	/**
	 * 下一步任务执行人。
	 */
	private List<TaskExecutor> taskExecutors=new ArrayList<TaskExecutor>();
	
	/**
	 * 业务关联值,一般是流程关联的实体的主键值
	 */
	private String businessKey="";
	
	/**
	 * 回退时，用于把流程任务回退至stackId对应的任务Id，可为空，其值来自bpm_exe_stack的主键
	 */
	private Long stackId;
	/**
	 * 是否跳过前置方法
	 */
	private boolean skipPreHandler=false;
	/**
	 * 是否跳过后置方法
	 */
	private boolean skipAfterHandler=false;

	/**
	 * 驳回。
	 * <pre>
	 * 0，正常跳转。
	 * 1，驳回
	 * 2，驳回到发起人。
	 * 则表示回退
	 * </pre>
	 */
	private Integer  isBack=0;
	/**
	 * 追回任务
	 */
	private boolean isRecover=false;
	
	
	/**
	 * 是否仅完成当前任务。
	 * <pre>
	 * 意思是流程任务是否只是完成。而不发生流程跳转。
	 * 在执行的时候,先删除当前节点后的连线，在完成当前任务。
	 * </pre>
	 */
	private boolean isOnlyCompleteTask=false;
	
	/**
	 * 投票状态。
	 * <pre>
	 * 0=弃权， 1=同意
	 * 2=反对， 3=驳回
	 * 4=追回
	 * 5=会签通过
	 * 6=会签不通过
	 * </pre>
	 */
	private Short voteAgree=1;
	
	/**
	 * 投票意见
	 */
	private String voteContent="";
	/**
	 * 投票控件名称
	 */
	private String voteFieldName="";
	
	/**
	 * 流程变量。
	 */
	private Map<String,Object> variables=new HashMap<String, Object>();
	
	
	/**
	 * 表单数据。
	 * 数据格式为JSON。
	 */
	private String formData="";
	
	/**
	 * 表单map对象。
	 */
	private Map formDataMap=new HashMap();
	
	/**
	 * 当前用户ID。
	 */
	private String currentUserId="";
	
	
	private ProcessRun processRun=null;
	/**
	 * 流程执行人账号
	 */
	private String userAccount=null;
	
	/**
	 * 是否外部调用。
	 * 如值为true  不处理自定义表单数据，不处理表单运行时情况
	 */
	private boolean invokeExternal=false;
	
	/**
	 * 通知类型。
	 * 格式如下1,2,3
	 * 1.代表邮件
	 * 2.代表手机短信
	 * 3.代表站内消息
	 */
	private String informType="";
	
	/**
	 * 归档时发送消息给发起人.
	 * 格式如下1,2,3
	 * 1.代表邮件
	 * 2.代表手机短信
	 * 3.代表站内消息
	 */
	private String informStart="";
	
	
	/**
	 * 是否管理员干预
	 */
	private Short isManage=0;
	
	/**
	 * 流程编号
	 */
	private String appCode="";
	
	/**
	 * 动态创建任务名称，用于自跳转，设置动态节点的名称
	 */
	private String dynamicTask;
	/**
	 * 跳转类型。
	 * <br>
	 * 如果这个值设置为4，则会跳回本节点。
	 */
	private Short jumpType;
	/**
	 * 在发起流程时需要跳转到的目标节点。
	 */
	private String startNode;
	
	/**
	 * 关联流程运行ID。
	 */
	private Long relRunId=0L;
	
	/**
	 * 临时变量，这个变量不会实例化到流程变量中。
	 */
	private Map<String,Object> transientVars=new HashMap<String, Object>();
	
	/**
	 * 这个表示本地API调用。
	 */
	private boolean localApi=false;
	
	/**
	 * 设置流程是否是跳过节点。
	 * 如果当前流程为跳过的情况，那么这个值设为true。
	 */
	private boolean skip=false;

	

	public ProcessCmd(){
	}
	
	public ProcessCmd(String flowKey){
		this.flowKey=flowKey;
	}

	public ProcessCmd(String flowKey,boolean skipPreHandler,boolean skipAfterHandler){
		this.flowKey=flowKey;
		this.skipPreHandler=skipPreHandler;
		this.skipAfterHandler=skipAfterHandler;
	}
	
	public String getActDefId()
	{
		return actDefId;
	}

	public void setActDefId(String actDefId)
	{
		this.actDefId = actDefId;
	}

	public String getTaskId()
	{
		return taskId;
	}
	
	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	public Map<String, Object> getVariables()
	{
		return variables;
	}

	public void setVariables(Map<String, Object> variables)
	{
		this.variables = variables;
	}
	
	/**
	 * 加入变量
	 * @param variables
	 */
	public void putVariables(Map<String, Object> variables){
		this.variables.putAll(variables);
	}
	
	/**
	 * 添加变量。
	 * @param key
	 * @param obj
	 */
	public void addVariable(String key,Object obj){
		this.variables.put(key, obj);
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getDestTask()
	{
		return destTask;
	}

	public void setDestTask(String destTask)
	{
		this.destTask = destTask;
	}

	public String getBusinessKey()
	{
		return businessKey;
	}

	public void setBusinessKey(String businessKey)
	{
		this.businessKey = businessKey;
	}



	public String getFlowKey()
	{
		return flowKey;
	}

	public void setFlowKey(String flowKey)
	{
		this.flowKey = flowKey;
	}

	public Integer isBack()
	{
		return isBack;
	}

	public void setBack(Integer isBack)
	{
		this.isBack = isBack;
	}

	public boolean isRecover() {
		return isRecover;
	}

	public void setRecover(boolean isRecover) {
		//追回也是一种回退，只不过是需要标识其为追回状态
//		if(isRecover){
//			this.isBack=BpmConst.TASK_BACK;
//		}
		this.isRecover = isRecover;
	}

	public Short getVoteAgree(){
		return voteAgree;
	}
	
	/**
	 * 0=弃权
	 * 1=同意
	 * 2=反对
	 * 3=驳回
	 * 4=追回
	 * 5=会签通过
	 * 6=会签不通过
	 * 34=提交
	 * @param voteAgree
	 */
	public void setVoteAgree(Short voteAgree)
	{
		if(TaskOpinion.STATUS_RECOVER.equals(voteAgree)){
			setRecover(true);
		}
		this.voteAgree = voteAgree;
	}

	public String getVoteContent()
	{
		return voteContent;
	}

	public void setVoteContent(String voteContent)
	{
		this.voteContent = voteContent;
	}

	

	public Long getStackId()
	{
		return stackId;
	}

	public void setStackId(Long stackId)
	{
		this.stackId = stackId;
	}
	
	

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}
	
	
	public Map getFormDataMap() {
		return formDataMap;
	}

	public void setFormDataMap(Map formDataMap) {
		this.formDataMap = formDataMap;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	
	
	public ProcessRun getProcessRun() {
		return processRun;
	}

	public void setProcessRun(ProcessRun processRun) {
		//增加主键的设置。
		this.businessKey=processRun.getBusinessKey();
		this.processRun = processRun;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}


	

	public String[] getLastDestTaskIds() {
		return lastDestTaskIds;
	}

	public void setLastDestTaskIds(String[] lastDestTaskIds) {
		this.lastDestTaskIds = lastDestTaskIds;
	}

	public String[] getLastDestTaskUids() {
		return lastDestTaskUids;
	}

	public void setLastDestTaskUids(String[] lastDestTaskUids) {
		this.lastDestTaskUids = lastDestTaskUids;
	}

	public boolean isOnlyCompleteTask() {
		return isOnlyCompleteTask;
	}

	public void setOnlyCompleteTask(boolean isOnlyCompleteTask) {
		this.isOnlyCompleteTask = isOnlyCompleteTask;
	}

	
	
	
	public boolean isInvokeExternal() {
		return invokeExternal;
	}

	public void setInvokeExternal(boolean invokeExternal) {
		this.invokeExternal = invokeExternal;
	}


	public String getInformType() {
		return informType;
	}

	public void setInformType(String informType) {
		this.informType = informType;
	}

	public boolean isSkipPreHandler() {
		return skipPreHandler;
	}

	public void setSkipPreHandler(boolean skipPreHandler) {
		this.skipPreHandler = skipPreHandler;
	}

	public boolean isSkipAfterHandler() {
		return skipAfterHandler;
	}

	public void setSkipAfterHandler(boolean skipAfterHandler) {
		this.skipAfterHandler = skipAfterHandler;
	}
	
	/**
	 * 获取流程节点执行人。
	 * 
	 * @return 返回数据结构为一个map，键为节点id，值为执行人列表。
	 */
	public Map<String,List<TaskExecutor>> getTaskExecutor(){
		Map<String,List<TaskExecutor>> map=new HashMap<String, List<TaskExecutor>>();
		if(BeanUtils.isEmpty(lastDestTaskIds)) return map;
		for(int i=0;i<lastDestTaskIds.length;i++){
			String nodeId=lastDestTaskIds[i];
			String executor=lastDestTaskUids[i];
			if(StringUtil.isEmpty(executor)) continue;
			
			List<TaskExecutor> list=BpmUtil.getTaskExecutors(executor);
			map.put(nodeId, list);
		}
		return map;
	}
	public List<TaskExecutor> getTaskExecutors() {
		return taskExecutors;
	}
	public void setTaskExecutors(List<TaskExecutor> taskExecutors) {
		this.taskExecutors = taskExecutors;
	}
	

	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public Short getIsManage() {
		return isManage;
	}
	public void setIsManage(Short isManage) {
		this.isManage = isManage;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getDynamicTask() {
		return dynamicTask;
	}
	public void setDynamicTask(String dynamicTask) {
		this.dynamicTask = dynamicTask;
	}
	public Short getJumpType() {
		return jumpType;
	}
	public void setJumpType(Short jumpType) {
		this.jumpType = jumpType;
	}
	
	public String getInformStart() {
		return informStart;
	}
	public void setInformStart(String informStart) {
		this.informStart = informStart;
	}

	
	public String getStartNode() {
		return startNode;
	}
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
	public String getVoteFieldName() {
		return voteFieldName;
	}
	public void setVoteFieldName(String voteFieldName) {
		this.voteFieldName = voteFieldName;
	}
	public Long getRelRunId() {
		if(relRunId==null) return 0L;
		return relRunId;
	}
	public void setRelRunId(Long relRunId) {
		this.relRunId = relRunId;
	}
	public Map<String, Object> getTransientVars() {
		return transientVars;
	}
	public void setTransientVars(Map<String, Object> transientVars) {
		this.transientVars = transientVars;
	}
	
	/**
	 * 添加临时变量。
	 * @param key
	 * @param object
	 */
	public void addTransientVar(String key,Object object){
		this.transientVars.put(key, object);
	}
	
	/**
	 * 根据KEY获取变量。
	 * @param key
	 * @return
	 */
	public Object getTransientVar(String key){
		return this.transientVars.get(key);
	}
	
	
	public boolean isLocalApi() {
		return localApi;
	}

	public void setLocalApi(boolean localApi) {
		this.localApi = localApi;
	}
	
	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	@Override
	public String toString() {
		return "ProcessCmd [actDefId=" + actDefId + ", flowKey=" + flowKey
				+ ", taskId=" + taskId + ", runId=" + runId + ", destTask="
				+ destTask + ", isBack=" + isBack + ", isRecover=" + isRecover
				+ ", isOnlyCompleteTask=" + isOnlyCompleteTask + ", voteAgree="
				+ voteAgree + ", informType=" + informType + "]";
	}
	
	
}

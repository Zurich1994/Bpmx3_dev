package com.hotent.platform.service.bpm.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmNodeSignService;
import com.hotent.platform.service.bpm.BpmNodeSignService.BpmNodePrivilegeType;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ISignComplete;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskSignDataService;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.service.bpm.BpmNodeScriptService;
/**
 * 根据会签规则判定流程如何结束。<br>
 * <pre>
 * 系统提供的变量：
 * nrOfInstances：总的实例个数。
 * nrOfActiveInstances：当前活动的实例个数。
 * nrOfCompletedInstances：完成的实例个数。
 * 取得变量的方法：
 * execution.getVariable("变量名称");
 * 
 * 使用方法：
 * 1.spring app-beans.xml配置文件中配置如下：
 * &lt;bean id="signComplete" class="com.hotent.platform.service.bpm.impl.SignComplete">&lt;/bean>
 * 2.流程定义配置如下：
 * &lt;completionCondition >${signComplete.isComplete(execution) }&lt;/completionCondition>
 * </pre>
 * 
 * @author ray
 *
 */
public class SignComplete implements ISignComplete {
	
	private Logger logger=LoggerFactory.getLogger(SignComplete.class);
	
	@Resource
	private BpmService bpmService;
	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private BpmNodeSignService bpmNodeSignService;
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private CalendarAssignService calendarAssignService;
	@Resource
	private BpmNodeScriptService bpmNodeScriptService;
	@Resource
	private GroovyScriptEngine scriptEngine;
	

	public boolean isComplete(ActivityExecution execution) {
		
		logger.debug("entert the SignComplete isComplete method...");
		
		String nodeId=execution.getActivity().getId();
		String actInstId= execution.getProcessInstanceId();
		
		boolean isCompleted=false;
		String signResult="";
		ProcessDefinition processDefinition=bpmService.getProcessDefinitionByProcessInanceId(actInstId);
		String actDefId = processDefinition.getId();
		//取得会签设置的规则
		BpmNodeSign bpmNodeSign=bpmNodeSignService.getByDefIdAndNodeId(actDefId, nodeId);
		//完成会签的次数
		Integer completeCounter=(Integer)execution.getVariable("nrOfCompletedInstances");
		//总循环次数
		Integer instanceOfNumbers=(Integer)execution.getVariable("nrOfInstances");
		//获取审批状态
		Short approvalStatus=TaskThreadService.getProcessCmd().getVoteAgree().shortValue();
		
		Long orgId=0L;
		
		SysOrg curOrg=ContextUtil.getCurrentOrg();
		
		if(curOrg!=null){
			orgId=curOrg.getOrgId();
		}
		
		ProcessCmd processCmd=TaskThreadService.getProcessCmd();
		/**
		 *驳回的话直接完成。
		 */
		if(BpmConst.TASK_BACK_TOSTART.equals(processCmd.isBack())||BpmConst.TASK_BACK.equals(processCmd.isBack())){
			isCompleted=true;
		}else if(approvalStatus == 5 || approvalStatus == 6){//直接通过（5 同意，6，反对)
			//特权处理
			//直接通过
			isCompleted = true;
			if(approvalStatus==5){//同意
				signResult = SIGN_RESULT_PASS;
			}else{//拒绝
				signResult = SIGN_RESULT_REFUSE;
			}
		}else{
			//一票决策,在calcResult前调用
			//获取一票决策规则
			boolean isOneVote = bpmNodeSignService.checkNodeSignPrivilege(actDefId, nodeId, BpmNodePrivilegeType.ALLOW_ONE_VOTE, ContextUtil.getCurrentUserId(), orgId);
			if (isOneVote) {
				if (!execution.hasVariable("resultOfSign_" + nodeId)) {//已有决策的话,本次决策不起作用
					execution.setVariable("resultOfSign_" + nodeId,	approvalStatus);
				}
			}
			// 一票决策结果
			Short oneVoteResult = null;
			if (execution.hasVariable("resultOfSign_" + nodeId)) {
				oneVoteResult = (Short) execution.getVariable("resultOfSign_"+ nodeId);
			}
			//计算投票结果。
			VoteResult voteResult=calcResult(bpmNodeSign, actInstId, nodeId, completeCounter,instanceOfNumbers,oneVoteResult);
				
			signResult=voteResult.getSignResult();
			isCompleted=voteResult.getIsComplete();
		}
		
		
		/**
		* 会签完成做的动作。
		* 1.删除会签的流程变量。
		* 2.将会签数据更新为完成。
		* 3.设置会签结果变量。
		* 4.更新会签节点结果。
		* 5.清除会签用户。
		*/
		if(isCompleted){
			//删除会签的变量。
			//删除 assignee，loopCounter变量。
		//	bpmService.delLoopAssigneeVars(execution.getId());
			//将会签数据更新为完成。
			taskSignDataService.batchUpdateCompleted(actInstId, nodeId);
			
			Short status=null;
			if(BpmConst.TASK_BACK_TOSTART.equals(processCmd.isBack())||BpmConst.TASK_BACK.equals(processCmd.isBack())){
				status=processCmd.getVoteAgree();
				if(TaskOpinion.STATUS_RECOVER_TOSTART.equals(status)||TaskOpinion.STATUS_RECOVER.equals(status)){
					signResult=SIGN_RESULT_RECOVER;
				}else if(TaskOpinion.STATUS_REJECT_TOSTART.equals(status)){
					signResult=SIGN_RESULT_TOSTART;
				}else if(TaskOpinion.STATUS_REJECT.equals(status)){
					signResult=SIGN_RESULT_BACK;
				}else{
					signResult="UNKNOW_ACTION";
				}
			}else{
				//更新会签节点的状态。
				status=TaskOpinion.STATUS_PASSED;
				if(signResult.equals(SIGN_RESULT_REFUSE)){
					status=TaskOpinion.STATUS_NOT_PASSED;
				}
			}
			logger.debug("set the sign result + " + signResult);
			//设置会签的结果
			execution.setVariable("signResult_" + nodeId , signResult);
			String resultSign= "resultOfSign_"+nodeId;
			if( execution.hasVariable(resultSign)){
				execution.removeVariable(resultSign);
			}
			
			//更新会签节点的状态。
			bpmProStatusDao.updStatus(new Long(actInstId), nodeId,status);
			
			//修改任务状态。
			updOption(execution,status);
			
			//如果流程为串行会签节点删除会签人员变量。
			String multiInstance=(String)execution.getActivity().getProperty("multiInstance");
			//串行会签人员首先从流程变量中获取。
			if("sequential".equals(multiInstance)){
				String varName=nodeId +"_" +BpmConst.SIGN_USERIDS;
				execution.removeVariable(varName);
			}
			
		}
		exeEventScript(execution, actDefId, nodeId, isCompleted);
		return isCompleted;
	}
	
	/**
	 * 执行事件脚本
	 * @param execution
	 * @param actDefId
	 * @param nodeId
	 * @param isCompleted
	 */
	private void exeEventScript(ActivityExecution execution, String actDefId, String nodeId, boolean isCompleted){
		BpmNodeScript signNodeScript = bpmNodeScriptService.getScriptByType(nodeId, actDefId, BpmNodeScript.SCRIPT_TYPE_5);
		if(signNodeScript == null) return;
		Map<String, Object> vars = execution.getVariables();
		vars.put("execution", execution);
		vars.put("isCompleted", isCompleted);
		scriptEngine.execute(signNodeScript.getScript(), vars);
	}
	
	/**
	 * 会签完成时，修改会签的状态。
	 * <pre>
	 * 并行会签需要修改状态。
	 * 
	 * 如果当会签完成时，会签的任务还有没有完成的那么修改意见的状态。
	 * 1.驳回
	 * 		修改成驳回取消
	 * 2.通过取消
	 * 3.否决取消
	 * 4.撤销取消
	 * </pre>
	 * @param execution
	 * @param signStatus
	 */
	private void updOption(ActivityExecution execution,Short signStatus){
		String multiInstance= (String) execution.getActivity().getProperty("multiInstance");
		String nodeId=execution.getCurrentActivityId();
		String actInstId=execution.getProcessInstanceId();
		if(!"parallel".equals(multiInstance)) return ;
		
		Short status=getStatus(signStatus);
			
		List<TaskOpinion> list=taskOpinionService.getByActInstIdTaskKeyStatus(actInstId,nodeId,TaskOpinion.STATUS_CHECKING);
		for(TaskOpinion taskOpinion:list){
			taskOpinion.setCheckStatus(status);
			taskOpinion.setEndTime(new Date());
			Long duration = calendarAssignService.getRealWorkTime(taskOpinion.getStartTime(), taskOpinion.getEndTime(), taskOpinion.getExeUserId());
			taskOpinion.setDurTime(duration);
			taskOpinionService.update(taskOpinion);
		}
	}
	
	/**
	 * 获取审批状态。
	 * @param cmd
	 * @return
	 */
	private Short getStatus(Short signResult){
		ProcessCmd cmd=TaskThreadService.getProcessCmd();
		Short status=TaskOpinion.STATUS_PASS_CANCEL;
		/**
		 * 0，正常跳转。
		 * 1，驳回
		 * 2，驳回到发起人。
		 */
		int isBack=cmd.isBack();
		boolean isRevover=cmd.isRecover();
		switch(isBack){
			//正常
			case 0:
				if(TaskOpinion.STATUS_PASSED.equals(signResult)){
					status=TaskOpinion.STATUS_PASS_CANCEL;
				}
				else{
					status=TaskOpinion.STATUS_REFUSE_CANCEL;
				}
				break;
			//驳回（追回)
			case 1:
			//驳回（追回)到发起人。
			case 2:
				if(isRevover){
					status=TaskOpinion.STATUS_REVOKED_CANCEL;
				}
				else{
					status=TaskOpinion.STATUS_BACK_CANCEL;
				}
				break;
		}
		return status;
	}
	
	
	/**
	 * 根据会签规则计算投票结果。
	 * <pre>
	 * 1.如果会签规则为空，那么需要所有的人同意通过会签，否则不通过。
	 * 2.否则按照规则计算投票结果。
	 * </pre>
	 * @param bpmNodeSign		会签规则
	 * @param actInstId			流程实例ID
	 * @param nodeId			节点id名称
	 * @param completeCounter		循环次数
	 * @param instanceOfNumbers		总的会签次数。
	 * @param oneVoteResult		一票决策结果
	 * @return
	 */
	private VoteResult calcResult(BpmNodeSign bpmNodeSign,String actInstId,String nodeId,
			Integer completeCounter,Integer instanceOfNumbers,Short oneVoteResult){
		VoteResult voteResult=new VoteResult();
		
		//投同意票数
		Integer agreeAmount=taskSignDataService.getAgreeVoteCount(actInstId, nodeId);
		//投反对票数
		Integer refuseAmount=taskSignDataService.getRefuseVoteCount(actInstId, nodeId);
		//在没有设置会签规则的情况下。
		if(bpmNodeSign==null){
			voteResult=getResultNoRule(oneVoteResult, refuseAmount, agreeAmount, instanceOfNumbers);
			return voteResult;
		}
		//存在会签的规则。
		voteResult=getResultByRule( bpmNodeSign, oneVoteResult, agreeAmount, refuseAmount,   completeCounter, instanceOfNumbers);
		return voteResult;	
	}
	
	/**
	 * 根据会签规则计算结果。
	 * @param bpmNodeSign
	 * @param oneVoteResult
	 * @param agreeAmount
	 * @param refuseAmount
	 * @param completeCounter
	 * @param instanceOfNumbers
	 * @return
	 */
	private VoteResult getResultByRule(BpmNodeSign bpmNodeSign,Short oneVoteResult,Integer agreeAmount,Integer refuseAmount,  Integer completeCounter,Integer instanceOfNumbers){
		VoteResult voteResult=new VoteResult();
		//符合规则即终止投票过程。
		//否则全部投票知道结束。
		boolean isDirect= (BpmNodeSign.FLOW_MODE_DIRECT.equals(bpmNodeSign.getFlowMode()));
		//存在特权的情况。
		if(oneVoteResult!=null){
			String result= (oneVoteResult==1)? SIGN_RESULT_PASS : SIGN_RESULT_REFUSE;
			//直接完成的情况。
			if(isDirect){
				voteResult=new VoteResult(result, true);
			}
			//完成全部投票根据特权判定结果。
			else if (completeCounter.equals(instanceOfNumbers)){
				voteResult=new VoteResult(result, true);
			}
			return voteResult;
		}
		//没有特权的情况。
		else{
			if(BpmNodeSign.VOTE_TYPE_PERCENT.equals(bpmNodeSign.getVoteType())){
				voteResult=getResultByPercent(bpmNodeSign,agreeAmount,refuseAmount,instanceOfNumbers,completeCounter);
			}
			else{
				voteResult=getResultByVotes(bpmNodeSign,agreeAmount,refuseAmount,instanceOfNumbers,completeCounter);
			}
		}
		return voteResult;
	}
	
	/**
	 * 没有会签规则时计算会签结果。
	 * @param oneVoteResult
	 * @param refuseAmount
	 * @param agreeAmount
	 * @param instanceOfNumbers
	 * @return
	 */
	private VoteResult getResultNoRule(Short oneVoteResult,Integer refuseAmount,Integer agreeAmount,Integer instanceOfNumbers){
		VoteResult voteResult=new VoteResult();
		//有特权的情况
		if(oneVoteResult!=null){
			if (oneVoteResult == 1){
				voteResult.setSignResult(SIGN_RESULT_PASS);
			}
			else{
				voteResult.setSignResult(SIGN_RESULT_REFUSE);
			}
			voteResult.setIsComplete(true);
			
		}
		//没有特权的情况,只要一票反对
		else{
			if(refuseAmount>0){
				voteResult.setSignResult(SIGN_RESULT_REFUSE);
				voteResult.setIsComplete(true);
			}
			//全部同意
			else if(agreeAmount.equals(instanceOfNumbers)){
				voteResult.setSignResult(SIGN_RESULT_PASS);
				voteResult.setIsComplete(true);
			}
		}
		return voteResult;
	}
	
	/**
	 * 根据百分比判断投票结果。
	 * @param bpmNodeSign
	 * @param agree
	 * @param refuse
	 * @param instanceOfNumbers
	 * @return
	 */
	private VoteResult getResultByVotes(BpmNodeSign bpmNodeSign,Integer agree,Integer refuse,
			Integer instanceOfNumbers,Integer completeCounter ){
		boolean isComplete=instanceOfNumbers.equals(completeCounter);
		VoteResult voteResult=new VoteResult();
		String result="";
		boolean isDirect=bpmNodeSign.getFlowMode()==1 ;
		boolean isPass=false;
		
		//按同意票数进行决定
		if(BpmNodeSign.DECIDE_TYPE_PASS.equals(bpmNodeSign.getDecideType())){
			//投票同意票符合条件
			if(agree>=bpmNodeSign.getVoteAmount()){
				result=SIGN_RESULT_PASS;
				isPass=true;
			}
			else{
				result=SIGN_RESULT_REFUSE;
			}
		}
		//按反对票数进行决定
		else{
			if(refuse>=bpmNodeSign.getVoteAmount()){
				result=SIGN_RESULT_REFUSE;
				isPass=true;
			}
			else{
				result=SIGN_RESULT_PASS;
			}
		}
		//直接过
		if(isDirect && isPass){
			voteResult=new VoteResult(result,true);
		}
		else if(isComplete){
			voteResult=new VoteResult(result,true);
		}
		return voteResult;
	}
	
	/**
	 * 根据投票百分比计算投票结果。
	 * @param bpmNodeSign
	 * @param agree
	 * @param refuse
	 * @param instanceOfNumbers
	 * @param completeCounter
	 * @return
	 */
	private VoteResult getResultByPercent(BpmNodeSign bpmNodeSign,Integer agree,Integer refuse,
			Integer instanceOfNumbers,Integer completeCounter ){
		boolean isComplete=instanceOfNumbers.equals(completeCounter);
		VoteResult voteResult=new VoteResult();
		String result="";
		boolean isPass=false;
		boolean isDirect=bpmNodeSign.getFlowMode()==1 ;
		float percents=0;
		//按同意票数进行决定
		if(BpmNodeSign.DECIDE_TYPE_PASS.equals(bpmNodeSign.getDecideType())){
			percents=((float)agree)/instanceOfNumbers;
			//投票同意票符合条件
			if(percents*100>=bpmNodeSign.getVoteAmount()){
				result=SIGN_RESULT_PASS;
				isPass=true;
			}
			else{
				result=SIGN_RESULT_REFUSE;
			}
		}
		//按反对票数进行决定
		else{
			percents=((float)refuse)/instanceOfNumbers;
			//投票
			if(percents*100>=bpmNodeSign.getVoteAmount()){
				result=SIGN_RESULT_REFUSE;
				isPass=true;
			}
			else{
				result=SIGN_RESULT_PASS;
			}
		}
		//直接过
		if(isDirect && isPass){
			voteResult=new VoteResult(result,true);
		}
		else if(isComplete){
			voteResult=new VoteResult(result,true);
		}
		return voteResult;
	}
	
	/**
	 * 投票结果。
	 * <pre>
	 * 1.是否会签完成。
	 * 2.会签结果，pass，refuse。
	 * </pre>
	 * @author ray。
	 *
	 */
	class VoteResult{
		//会签结果
		private String signResult ="";
		//是否完成
		private boolean isComplete=false;
		
		public VoteResult(){}
		
		public VoteResult(String signResult,boolean isComplate){
			this.signResult=signResult;
			this.isComplete=isComplate;
		}
		
		public String getSignResult() {
			return signResult;
		}
		public void setSignResult(String signResult) {
			this.signResult = signResult;
		}
		public boolean getIsComplete() {
			return isComplete;
		}
		public void setIsComplete(boolean isComplete) {
			this.isComplete = isComplete;
		}
	}
}

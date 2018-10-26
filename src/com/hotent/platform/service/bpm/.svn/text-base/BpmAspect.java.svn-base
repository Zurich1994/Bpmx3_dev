package com.hotent.platform.service.bpm;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;

import com.hotent.core.annotion.WorkFlow;
import com.hotent.core.bpm.BpmAspectUtil;
import com.hotent.core.bpm.BpmResult;
import com.hotent.core.bpm.DataType;
import com.hotent.core.bpm.WorkFlowException;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.util.RequestContext;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;




/**
 * 流程启动拦截器。
 * <pre> 
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cnBpmAspect.java
 * 日期:2014-6-5-下午2:35:27
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmAspect {

	@Resource
	ProcessRunService processRunService;
	@Resource
	TaskUserAssignService taskUserAssignService; 
	@Resource
	BpmDefinitionService bpmDefinitionService;
	@Resource
	ProcessRunDao processRunDao;
	
	/**
	 * 拦截后置操作。
	 * @param point
	 * @param retValue 
	 * void
	 * @throws Exception 
	 */
	public void doAfter(JoinPoint point) throws Exception {  

		Method method=getMethod(point);
		if(method==null ) return;			
		
		WorkFlow workFlow = method.getAnnotation(WorkFlow.class);		
		
		if(workFlow==null) return;
		
		HttpServletRequest request= RequestContext.getHttpServletRequest();
		if(request==null ) {
			throw new WorkFlowException("请求对象为空，执行流程动作失败！");//抛出异常
		}
		
		//仅仅保存数据，不处理流程。
		int saveData=RequestUtil.getInt(request,"saveData",0);
		
		String executeType= request.getParameter("executeType");
		//保存数据
		if(saveData==1){
			saveData(workFlow);
		}
		else{
			doWorkFlow(workFlow,executeType);
		}
	}  
	
	/**
	 * 启动流程时保存实例数据。
	 * <pre>
	 * 	1.保存时报错流程运行实例。
	 * 	2.再次保存时，取回原来的实例进行更新。
	 * </pre>
	 * @param workFlow
	 * @throws Exception 
	 */
	private void saveData(WorkFlow workFlow ) throws Exception{
		String flowKey=getDefKey(workFlow);
		BpmResult result=getBpmResult(); 
		if(result==null ) {
			throw new WorkFlowException("传入的流程执行对象（BpmResult）为空，执行流程动作失败！");//抛出异常
		}
		BpmDefinition bpmDefinition=  bpmDefinitionService.getMainByDefKey(flowKey);
		
		ProcessCmd cmd=getCmd(flowKey, result);
		
		DataType dataType= result.getDataType();
		
		String businessKey=result.getBusinessKey();
		
		ProcessRun processRun=null;
		if(dataType.equals(DataType.STRING)){
			processRun= processRunDao.getByBusinessKey(businessKey);
		}
		else{
			processRun= processRunDao.getByBusinessKeyNum(new Long( businessKey));
		}
		//新增。
		if(processRun==null){
			processRun=processRunService.initProcessRun(bpmDefinition, cmd);
			if(dataType.equals(DataType.STRING)){
				processRun.setBusinessKey(businessKey);
			}
			else{
				processRun.setBusinessKeyNum(new Long(businessKey));
			}
			processRun.setStatus(ProcessRun.STATUS_FORM);
			processRunService.add(processRun);
		}
		else{
			String subject=processRunService.getSubject(bpmDefinition, cmd);
			processRun.setSubject(subject);
			processRun.setCreatetime(new Date());
			processRunService.update(processRun);
		}
	}
	
	/**
	 * 构建CMD
	 * @param flowKey
	 * @param result
	 * @return
	 */
	private ProcessCmd getCmd(String flowKey,BpmResult result ){
		ProcessCmd cmd=new ProcessCmd();
		cmd.setFlowKey(flowKey);
		Map<String,Object> params=result.getVars();
		
		cmd.setVariables(params);
		cmd.setBusinessKey(result.getBusinessKey());
		cmd.addTransientVar("bpmResult", result);
		return cmd;
	}
	
	/**
	 * 处理工作流相关操作。
	 * @param workFlow
	 * @param executeType
	 * @throws Exception
	 */
	void doWorkFlow(WorkFlow workFlow ,String executeType) throws Exception{
		HttpServletRequest request= RequestContext.getHttpServletRequest();
		//执行流程方法
		BpmResult result=getBpmResult(); 
		if(result==null ) {
			throw new WorkFlowException("传入的流程执行对象（BpmResult）为空，执行流程动作失败！");//抛出异常
		}
		
		if(StringUtil.isEmpty(executeType)) {
			throw new WorkFlowException("传入的执行任务类型（executeType）对象为空，执行流程动作失败！");//抛出异常
		};
		
		if("start".equals(executeType)){
			String flowKey=getDefKey(workFlow);
			String tableName=workFlow.tableName();
			String tbName=result.getTableName();
			if(StringUtil.isEmpty(tbName) && StringUtil.isNotEmpty(tableName)){
				result.setTableName(tableName);
			}
			doStart(flowKey,result,request);
		}else if("doNext".equals(executeType)){
			doNext(result,request);
		}else{
			throw new WorkFlowException("传入的执行任务类型（bpm_flow_excute_type）的值不符合条件，执行流程动作失败！"); //抛出异常
		}
		
	}
	
	/**
	 * 获取业务方法处理结果。
	 * @return  BpmResult
	 */
	private BpmResult getBpmResult(){
		BpmResult result=BpmAspectUtil.getBpmResult(); 
		BpmAspectUtil.clearBpmResult();
		return result;
	}
	
	/**
	 * 获取流程定义KEY。
	 * @param workFlow
	 * @return
	 */
	private String getDefKey(WorkFlow workFlow){
		HttpServletRequest request= RequestContext.getHttpServletRequest();
		String flowKey=workFlow.flowKey();
		String defKey= request.getParameter(BpmConst.BPM_DEFKEY);
		if(StringUtil.isEmpty( defKey)) {
			if(StringUtil.isEmpty(flowKey)){
				throw new WorkFlowException("传入的流程KEY（flowKey_）的值为空，执行启动流程动作失败！"); //抛出异常
			}
			else{
				defKey=flowKey;
			}
		};
		return defKey;
	}
	
	
	private void doStart(String flowKey,BpmResult result,HttpServletRequest request) throws Exception{
		
		String startNode=request.getParameter("startNode");
		Long runId=RequestUtil.getLong(request,"runId",0L);
		
		ProcessCmd cmd =getCmd( flowKey,result);
		//跳转到的节点。
		cmd.setStartNode(startNode);
		if(runId>0){
			ProcessRun processRun=processRunService.getById(runId);
			cmd.setProcessRun(processRun);
		}
		cmd.setRunId(runId);
		cmd.addTransientVar("bpmResult", result);
		processRunService.startProcess(cmd);
	}
	
	private void doNext(BpmResult result,HttpServletRequest request) throws Exception{
		
		String taskId= request.getParameter("taskId");
		if(StringUtil.isEmpty(taskId)) {
			throw new WorkFlowException("传入的任务ID（taskId）的值为空，执行流程动作失败！"); //抛出异常
		};
		
		short voteAgree=RequestUtil.getShort(request, "voteAgree",(short)1);
		String opinion=RequestUtil.getString(request, "opinion","");
		String executors=RequestUtil.getString(request, "executors");
		String destTask=RequestUtil.getString(request, "destTask","");
		Integer isBack=RequestUtil.getInt(request, "isBack",0);

		ProcessCmd cmd=new ProcessCmd();
		cmd.setTaskId(taskId);
		cmd.setVoteAgree(voteAgree);
		cmd.setVoteContent(opinion);
		cmd.setBusinessKey(result.getBusinessKey());
		cmd.setDestTask(destTask);
		cmd.setBack(isBack);
		
		if(result!=null){
			Map<String,Object> vars=result.getVars();
			cmd.setVariables(vars);
		}
		
		//设置目标节点映射
		Map<String,List<TaskExecutor>> nodeIdentityMap=TaskExecutorUtil.getBpmIdentity(executors);
		
		for(String key:nodeIdentityMap.keySet()){
			List<TaskExecutor> taskExecutors=nodeIdentityMap.get(key);
			taskUserAssignService.addNodeUser(key, taskExecutors);
		}
		processRunService.nextProcess(cmd);
		
	}
	
	
	
	private Method getMethod(JoinPoint point){
		String methodName = point.getSignature().getName();
		//类
		Class<?> targetClass = point.getTarget().getClass();
		//方法
		Method[] methods = targetClass.getMethods();
		Method method = null;
		for (int i = 0; i < methods.length; i++){
			if (methods[i].getName() == methodName){
				method = methods[i];
				break;
			}
		}
		return method;
	}
}

package com.hotent.core.bpm;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;


/**
 * 业务结果数据保存。
 * <pre> 
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-6-5-下午2:35:27
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmAspectUtil{
	
	
	private static ThreadLocal<BpmResult> bpmResultThreadLocal = new ThreadLocal<BpmResult>();
	
	
	public static BpmResult getBpmResult() {
		return  bpmResultThreadLocal.get();
	}

	public static void setBpmResult(BpmResult bpmResult) {
		bpmResultThreadLocal.set(bpmResult);
	}
	
	/**
	 * 构建结果并放到线程变量中。
	 * @param businessKey
	 * @param tableName
	 * @param vars
	 */
	public static void buildBpmResult(String businessKey,String tableName,Map<String,Object> vars){
		BpmResult bpmResult=new BpmResult();
		bpmResult.setBusinessKey(businessKey);
		bpmResult.setTableName(tableName);
		if(BeanUtils.isNotEmpty(vars)){
			bpmResult.setVars(vars);
		}
		bpmResultThreadLocal.set(bpmResult);
		
	}
	



	/**
	 * 清除BpmAspectUtil线程变量 
	 * void
	 */
	public static void clearBpmResult() {
		bpmResultThreadLocal.remove();
	}
	
	
	/**
	 * 根据任务ID获取任务对应的参数。
	 * <pre>
	 * 	返回使用map：
	 *  taskEnt:任务对象
	 *  processRun：流程实例对象。
	 * </pre>
	 * @param taskId
	 * @return
	 */
	public static Map<String,Object> getModelByTaskId(Long taskId){
		Map<String,Object> map=new HashMap<String, Object>();
		
		ProcessRunService runService=AppUtil.getBean(ProcessRunService.class);
		BpmService bpmService=AppUtil.getBean(BpmService.class);
		TaskEntity taskEnt=  bpmService.getTask(taskId.toString());
		
		String actInstId=taskEnt.getProcessInstanceId();
		ProcessRun processRun= runService.getByActInstanceId(new Long(actInstId));
		map.put("taskEnt", taskEnt);
		map.put("processRun", processRun);
		return map;
		
	}
	
	/**
	 * 根据runId获取数据。
	 * @param runId
	 * @return
	 */
	public static Map<String,Object> getModelByRunId(Long runId){
		Map<String,Object> map=new HashMap<String, Object>();
		ProcessRunService runService=AppUtil.getBean(ProcessRunService.class);
		ProcessRun processRun= runService.getById(runId);
		map.put("processRun", processRun);
		return map;
		
	}
	
}

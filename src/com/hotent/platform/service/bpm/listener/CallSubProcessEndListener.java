package com.hotent.platform.service.bpm.listener;

import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.thread.TaskThreadService;

/**
 * 外部子流程启动事件监听器。
 * <pre>
 * 	1.用于修改子流程节点的状态为正在结束。
 *  2.在子流程调用任务结束后，将人员变量删除。
 * </pre>
 * @author ray
 *
 */
@SuppressWarnings("serial")
public class CallSubProcessEndListener extends BaseNodeEventListener {
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	@Resource
	private RuntimeService runtimeService;
	@Override
	protected void execute(DelegateExecution execution, String actDefId,String nodeId) {
		Long processInstanceId=new Long( execution.getProcessInstanceId());
		ExecutionEntity ent=(ExecutionEntity)execution;
		String varName=ent.getActivityId() +"_" + BpmConst.SUBPRO_EXT_MULTI_USERIDS;
		
		ProcessCmd processCmd=TaskThreadService.getProcessCmd();
		//正常跳转。
		if(processCmd.isBack()==0){
			boolean rtn= BpmUtil.isMuiltiExcetion(ent);
			if(rtn){
				int completeInstance=(Integer)execution.getVariable("nrOfCompletedInstances");
				int nrOfInstances=(Integer)execution.getVariable("nrOfInstances");
				if(completeInstance== nrOfInstances){
					bpmProStatusDao.updStatus( processInstanceId, nodeId,TaskOpinion.STATUS_AGREE );
				}
			}
			else{
				bpmProStatusDao.updStatus(processInstanceId, nodeId,TaskOpinion.STATUS_AGREE );
				deleteInstanceBySupperInstanceId(processInstanceId);
			}
		}
		//驳回
		else if(processCmd.isBack()==1){
			bpmProStatusDao.updStatus(processInstanceId, nodeId,TaskOpinion.STATUS_REJECT);
		}
		execution.removeVariable(varName);
	}
	
	private void deleteInstanceBySupperInstanceId(Long processInstanceId){
		List<ProcessInstance> subInstances = runtimeService.createProcessInstanceQuery().superProcessInstanceId(processInstanceId.toString()).list();
		for(ProcessInstance instance:subInstances){
			runtimeService.deleteProcessInstance(instance.getProcessInstanceId(), "结束子流程");
		}
	}

	@Override
	protected Integer getScriptType() {
		return BpmConst.EndScript;
	}
}

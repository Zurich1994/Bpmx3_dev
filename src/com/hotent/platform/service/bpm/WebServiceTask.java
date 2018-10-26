package com.hotent.platform.service.bpm;

import java.util.Map;
import net.sf.json.JSONArray;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import com.hotent.core.soap.SoapUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.BpmNodeWebService;


/**
 * Webservice任务
 * 
 * @author zxh
 * 
 */
public class WebServiceTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		ExecutionEntity ent = (ExecutionEntity) execution;
		String nodeId = ent.getActivityId();
		String actDefId = ent.getProcessDefinitionId();
		
		Map<String, Object> map = execution.getVariables();
		BpmNodeWebServiceService bpmNodeWebServiceService = (BpmNodeWebServiceService) AppUtil.getBean("bpmNodeWebServiceService");
		// 获取脚本节点的脚本对象。
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);

		if(BeanUtils.isEmpty(bpmNodeWebService))return;
		String document = bpmNodeWebService.getDocument();
		
		JSONArray jArray = (JSONArray)JSONArray.fromObject(document);
		if(jArray.size()==0)return;

		SoapUtil.invoke(map, jArray);
		execution.setVariables(map);

	}

}

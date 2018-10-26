package com.hotent.platform.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.TaskOpinionService;

@Controller
@RequestMapping("/platform/mobile/processRun/")
public class MobileProcessRunContoller extends MobileBaseController {
	@Resource
	private TaskOpinionService taskOpinionService;
	
	/**
	 * 根据流程实例id获取流程的状态。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getFlowStatusByInstanceId")
	@ResponseBody
	public Object getFlowStatusByInstanceId(
			HttpServletRequest request) {
		
		String processInstanceId = RequestUtil.getString(request, "processInstanceId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		
		List<TaskOpinion> list = taskOpinionService.getByActInstIdTaskKey(new Long( processInstanceId), nodeId);
		try {
			list = taskOpinionService.setTaskOpinionListExeFullname(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return getPageList(list);
	}
}

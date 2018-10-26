package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.CommuReceiver;
import com.hotent.platform.service.bpm.CommuReceiverService;

/**
 *<pre>
 * 对象功能:沟通接收人 控制器类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-09 19:44:59
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/commuReceiver/")
public class CommuReceiverController extends BaseController
{
	@Resource
	private CommuReceiverService commuReceiverService;
		
	@RequestMapping("getByOpinionId")
	@Action(description="根据意见Id查看接收人")
	@ResponseBody
	public List<CommuReceiver> getByOpinionId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long opinionId=RequestUtil.getLong(request, "opinionId"); 
		List<CommuReceiver> list =commuReceiverService.getByOpinionId(opinionId);
		return list;
	}
	
	
	
	@RequestMapping("getMoblieByOpinionId")
	@Action(description="根据意见Id查看接收人(手机版本)")
	@ResponseBody
	public Object getMoblieByOpinionId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long opinionId=RequestUtil.getLong(request, "opinionId"); 
		List<CommuReceiver> list =commuReceiverService.getByOpinionId(opinionId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("results", list);
		return map;
	}
	
}

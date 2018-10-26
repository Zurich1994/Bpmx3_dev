package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.service.system.DesktopColumnService;
import com.hotent.platform.service.system.DesktopLayoutService;
import com.hotent.platform.service.system.DesktopLayoutcolService;
import com.hotent.platform.service.system.DesktopMycolumnService;
import com.hotent.platform.service.system.DesktopService;

@Controller
@RequestMapping("/platform/system/desktop/")
public class DesktopController extends BaseController{
	@Resource
	private DesktopService desktopService;
	@Resource
	private DesktopLayoutService desktopLayoutService;
	@Resource
	private DesktopMycolumnService desktopMycolumnService;
	@Resource
	private DesktopLayoutcolService desktopLayoutcolService;
	@Resource
	private DesktopColumnService desktopColumnService;
	
	
	@RequestMapping("getMyDesktop")
	@ResponseBody
	public Map getMyDesktop(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long userId=ContextUtil.getCurrentUserId();
		String ctxPath=request.getContextPath();
		Map<String,Object> jsonData=new HashMap<String,Object>();
		Map mapData=desktopMycolumnService.getMyDeskData(userId,ctxPath);
		return mapData;
	}
	
	@RequestMapping("getLayoutData")
	@ResponseBody
	public Map getLayoutData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Long layoutId=RequestUtil.getLong(request, "layoutId");
		String ctxPath=request.getContextPath();
		Map  map=desktopLayoutcolService.getLayoutData(layoutId,ctxPath);
		return map;
	}
}

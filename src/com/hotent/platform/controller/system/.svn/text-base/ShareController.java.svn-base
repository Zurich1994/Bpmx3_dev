package com.hotent.platform.controller.system;

import java.util.HashMap;
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
import com.hotent.platform.service.system.ShareService;
/**
 * 对象功能:JDBC 数据 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Raise
 * 创建时间:2013-01-26
 */
@Controller
@RequestMapping("/platform/system/share/")
public class ShareController extends BaseController
{	
	@Resource
	private ShareService shareService;
	
	@RequestMapping("getPingyin")
	@ResponseBody
	@Action(description="获取拼音")
	public Map<String,Object> getPingyin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		String input=RequestUtil.getString(request, "input");
		String output=shareService.getPingyin(input);
		map.put("output", output );
		return map;
	}
}

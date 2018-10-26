package com.hotent.platform.controller.bpm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmNodeUserUplow;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.DemensionService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能: 用户节点的上下级 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-02 10:07:45
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeUserUplow/")
public class BpmNodeUserUplowController extends BaseController
{
	@Resource
	private DemensionService demensionService;
	@Resource
	private SysUserService sysUserService;
	
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<Demension> demensionList=demensionService.getAll();
		//demensionList.add(Demension.positionDem);
		
		ModelAndView mv=this.getAutoView()
		.addObject("demensionList",demensionList)
		.addObject("uplowtypeList",BpmNodeUserUplow.UPLOWTYPE_MAP)
		;
		return mv;
	}
	@RequestMapping("getByUserId")
	public ModelAndView getByUserId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv=this.getAutoView();
		String json=RequestUtil.getString(request, "json");
		json=new String(json.getBytes("ISO-8859-1"),"utf-8");
		
		JSONArray ja= JSONArray.fromObject(json);
		List<BpmNodeUserUplow> uplowList= (List) JSONArray.toCollection(ja, BpmNodeUserUplow.class);
		
		List<SysUser> userList=sysUserService.getByUserIdAndUplow(ContextUtil.getCurrentUserId(),uplowList);
		
		return mv.addObject("userList",userList);
	}

}

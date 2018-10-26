package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysQueryFieldSetting;
import com.hotent.platform.service.system.SysQueryFieldSettingService;
/**
 *<pre>
 * 对象功能:视图自定义设定 控制器类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysQueryFieldSetting/")
public class SysQueryFieldSettingController extends BaseController
{
	@Resource
	private SysQueryFieldSettingService sysQueryFieldSettingService;
	
	
	/**
	 * 添加或更新视图自定义设定。
	 * @param request
	 * @param response
	 * @param sysQueryFieldSetting 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新视图自定义设定")
	public void save(HttpServletRequest request, HttpServletResponse response,SysQueryFieldSetting sysQueryFieldSetting) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysQueryFieldSetting.getId()==null||sysQueryFieldSetting.getId()==0){
				sysQueryFieldSettingService.save(sysQueryFieldSetting);
				resultMsg=getText("添加","视图自定义设定");
			}else{
			    sysQueryFieldSettingService.save(sysQueryFieldSetting);
				resultMsg=getText("更新","视图自定义设定");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得视图自定义设定分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看视图自定义设定分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysQueryFieldSetting> list=sysQueryFieldSettingService.getAll(new QueryFilter(request,"sysQueryFieldSettingItem"));
		ModelAndView mv=this.getAutoView().addObject("sysQueryFieldSettingList",list);
		return mv;
	}
	
	/**
	 * 删除视图自定义设定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除视图自定义设定")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysQueryFieldSettingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除视图自定义设定成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑视图自定义设定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑视图自定义设定")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysQueryFieldSetting sysQueryFieldSetting=sysQueryFieldSettingService.getById(id);
		
		return getAutoView().addObject("sysQueryFieldSetting",sysQueryFieldSetting)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得视图自定义设定明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看视图自定义设定明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysQueryFieldSetting sysQueryFieldSetting = sysQueryFieldSettingService.getById(id);	
		return getAutoView().addObject("sysQueryFieldSetting", sysQueryFieldSetting);
	}
	
}


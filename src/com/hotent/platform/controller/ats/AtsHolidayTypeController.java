package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsHolidayType;
import com.hotent.platform.service.ats.AtsHolidayTypeService;
/**
 *<pre>
 * 对象功能:假期类型 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 20:47:17
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsHolidayType/")
public class AtsHolidayTypeController extends BaseController
{
	@Resource
	private AtsHolidayTypeService atsHolidayTypeService;
	
	
	/**
	 * 添加或更新假期类型。
	 * @param request
	 * @param response
	 * @param atsHolidayType 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新假期类型")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsHolidayType atsHolidayType) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsHolidayType.getId()==null||atsHolidayType.getId()==0){
				resultMsg=getText("添加","假期类型");
			}else{
				resultMsg=getText("更新","假期类型");
			}
		    atsHolidayTypeService.save(atsHolidayType);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得假期类型分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看假期类型分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsHolidayType> list=atsHolidayTypeService.getAll(new QueryFilter(request,"atsHolidayTypeItem"));
		return this.getAutoView().addObject("atsHolidayTypeList",list);
	}
	
	/**
	 * 删除假期类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除假期类型")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsHolidayTypeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除假期类型成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑假期类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑假期类型")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsHolidayType atsHolidayType=atsHolidayTypeService.getById(id);
		
		return getAutoView().addObject("atsHolidayType",atsHolidayType)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得假期类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看假期类型明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsHolidayType atsHolidayType = atsHolidayTypeService.getById(id);	
		return getAutoView().addObject("atsHolidayType", atsHolidayType);
	}
	/**
	 * 取得假期类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getNameById")
	@ResponseBody
	public String getNameById(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsHolidayType atsHolidayType = atsHolidayTypeService.getById(id);	
		if(atsHolidayType!=null){
			return atsHolidayType.getName();
		}
		return "";
	}
	
	/**
	 * 
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("enable")
	@Action(description="查看假期类型明细")
	public ModelAndView enable(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Boolean enable=RequestUtil.getBoolean(request,"enable");
		AtsHolidayType atsHolidayType = atsHolidayTypeService.getById(id);
		atsHolidayType.setStatus(enable?AtsConstant.YES:AtsConstant.NO);
		atsHolidayTypeService.update(atsHolidayType);
		return new ModelAndView("redirect:list.ht");
	}
	/**
	 * 取得假期类型明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="取得假期类型明细")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsHolidayType> list=atsHolidayTypeService.getAll(new QueryFilter(request,"atsHolidayTypeItem"));
		return this.getAutoView().addObject("atsHolidayTypeList",list);
	}
}


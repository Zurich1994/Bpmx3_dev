package com.hotent.platform.controller.ats;

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
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsShiftType;
import com.hotent.platform.service.ats.AtsShiftTypeService;
/**
 *<pre>
 * 对象功能:班次类型 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 21:44:00
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsShiftType/")
public class AtsShiftTypeController extends BaseController
{
	@Resource
	private AtsShiftTypeService atsShiftTypeService;
	/**
	 * 添加或更新班次类型。
	 * @param request
	 * @param response
	 * @param atsShiftType 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新班次类型")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsShiftType atsShiftType) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsShiftType.getId()==null||atsShiftType.getId()==0){
				resultMsg=getText("添加","班次类型");
			}else{
				resultMsg=getText("更新","班次类型");
			}
		    atsShiftTypeService.save(atsShiftType);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得班次类型分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看班次类型分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsShiftType> list=atsShiftTypeService.getAll(new QueryFilter(request,"atsShiftTypeItem"));
		return this.getAutoView().addObject("atsShiftTypeList",list);
	}
	
	/**
	 * 删除班次类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除班次类型")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsShiftTypeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除班次类型成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑班次类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑班次类型")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsShiftType atsShiftType=atsShiftTypeService.getById(id);
		
		return getAutoView().addObject("atsShiftType",atsShiftType)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得班次类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看班次类型明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsShiftType atsShiftType = atsShiftTypeService.getById(id);	
		return getAutoView().addObject("atsShiftType", atsShiftType);
	}
	/**
	 * 取得假期类型明细
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
		AtsShiftType atsShiftType = atsShiftTypeService.getById(id);
		atsShiftType.setStatus(enable?AtsConstant.YES:AtsConstant.NO);
		atsShiftTypeService.update(atsShiftType);
		return new ModelAndView("redirect:list.ht");
	}
}


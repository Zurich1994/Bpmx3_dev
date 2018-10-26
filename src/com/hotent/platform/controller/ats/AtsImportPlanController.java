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
import com.hotent.platform.model.ats.AtsImportPlan;
import com.hotent.platform.service.ats.AtsImportPlanService;
/**
 *<pre>
 * 对象功能:打卡导入方案 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 13:50:13
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsImportPlan/")
public class AtsImportPlanController extends BaseController
{
	@Resource
	private AtsImportPlanService atsImportPlanService;
	
	
	/**
	 * 添加或更新打卡导入方案。
	 * @param request
	 * @param response
	 * @param atsImportPlan 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新打卡导入方案")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsImportPlan atsImportPlan) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsImportPlan.getId()==null||atsImportPlan.getId()==0){
				resultMsg=getText("添加","打卡导入方案");
			}else{
				resultMsg=getText("更新","打卡导入方案");
			}
		    atsImportPlanService.save(atsImportPlan);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得打卡导入方案分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看打卡导入方案分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsImportPlan> list=atsImportPlanService.getAll(new QueryFilter(request,"atsImportPlanItem"));
		return this.getAutoView().addObject("atsImportPlanList",list);
	}
	
	/**
	 * 删除打卡导入方案
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除打卡导入方案")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsImportPlanService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除打卡导入方案成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑打卡导入方案
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑打卡导入方案")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsImportPlan atsImportPlan=atsImportPlanService.getById(id);
		
		return getAutoView().addObject("atsImportPlan",atsImportPlan)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得打卡导入方案明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看打卡导入方案明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsImportPlan atsImportPlan = atsImportPlanService.getById(id);	
		return getAutoView().addObject("atsImportPlan", atsImportPlan);
	}
	
}


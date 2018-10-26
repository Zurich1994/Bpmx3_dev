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
import com.hotent.platform.model.ats.AtsHolidayPolicyDetail;
import com.hotent.platform.service.ats.AtsHolidayPolicyDetailService;
/**
 *<pre>
 * 对象功能:假期制度明细 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-05-28 14:25:03
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsHolidayPolicyDetail/")
public class AtsHolidayPolicyDetailController extends BaseController
{
	@Resource
	private AtsHolidayPolicyDetailService atsHolidayPolicyDetailService;
	
	
	/**
	 * 添加或更新假期制度明细。
	 * @param request
	 * @param response
	 * @param atsHolidayPolicyDetail 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新假期制度明细")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsHolidayPolicyDetail atsHolidayPolicyDetail) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsHolidayPolicyDetail.getId()==null||atsHolidayPolicyDetail.getId()==0){
				atsHolidayPolicyDetailService.save(atsHolidayPolicyDetail);
				resultMsg=getText("添加","假期制度明细");
			}else{
			    atsHolidayPolicyDetailService.save(atsHolidayPolicyDetail);
				resultMsg=getText("更新","假期制度明细");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得假期制度明细分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看假期制度明细分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsHolidayPolicyDetail> list=atsHolidayPolicyDetailService.getAll(new QueryFilter(request,"atsHolidayPolicyDetailItem"));
		ModelAndView mv=this.getAutoView().addObject("atsHolidayPolicyDetailList",list);
		return mv;
	}
	
	/**
	 * 删除假期制度明细
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除假期制度明细")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsHolidayPolicyDetailService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除假期制度明细成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑假期制度明细
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑假期制度明细")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsHolidayPolicyDetail atsHolidayPolicyDetail=atsHolidayPolicyDetailService.getById(id);
		
		return getAutoView().addObject("atsHolidayPolicyDetail",atsHolidayPolicyDetail)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得假期制度明细明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看假期制度明细明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsHolidayPolicyDetail atsHolidayPolicyDetail = atsHolidayPolicyDetailService.getById(id);	
		return getAutoView().addObject("atsHolidayPolicyDetail", atsHolidayPolicyDetail);
	}
	
}


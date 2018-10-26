
package com.hotent.BusinessCurve.controller.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.BusinessCurve.model.lc.BusinessCurve;
import com.hotent.BusinessCurve.service.lc.BusinessCurveService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:业务曲线信息表 控制器类
 */
@Controller
@RequestMapping("/BusinessCurve/lc/businessCurve/")
public class BusinessCurveController extends BaseController
{
	@Resource
	private BusinessCurveService businessCurveService;
	/**
	 * 添加或更新业务曲线信息表。
	 * @param request
	 * @param response
	 * @param businessCurve 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务曲线信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,BusinessCurve businessCurve) throws Exception
	{
		String processId = request.getParameter("processId");
		System.out.println("Curve-save-processId:"+processId);
		request.getSession().setAttribute("processId", processId);
		String resultMsg=null;		
		try{
			if(businessCurve.getId()==null){
				Long id=UniqueIdUtil.genId();
				businessCurve.setId(id);
				businessCurveService.add(businessCurve);
				resultMsg=getText("添加","业务曲线信息表");
			}else{
			    businessCurveService.update(businessCurve);
				resultMsg=getText("更新","业务曲线信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得业务曲线信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务曲线信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BusinessCurve> list=businessCurveService.getAll(new QueryFilter(request,"businessCurveItem"));
		ModelAndView mv=this.getAutoView().addObject("businessCurveList",list);
		
		return mv;
	}
	@RequestMapping("listNew")
	@Action(description="查看业务曲线信息表分页列表")
	public ModelAndView listNew(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String processId = String.valueOf(request.getSession().getAttribute("processId"));
		System.out.println("Curve-list-processId:"+processId);
		List<BusinessCurve> list=businessCurveService.getAll(new QueryFilter(request,"businessCurveItem"));
		ModelAndView mv=this.getAutoView().addObject("businessCurveList",list).addObject("processId", processId);
		
		return mv;
	}
	
	/**
	 * 删除业务曲线信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务曲线信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			businessCurveService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除业务曲线信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务曲线信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务曲线信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String type = request.getParameter("timeType");
		request.getSession().setAttribute("passType", type);
		String pid = request.getParameter("processId");
		request.getSession().setAttribute("passPid", pid);
		System.out.println("type: ?"+type+"?  pid:?"+pid);
		String returnUrl=RequestUtil.getPrePage(request);
		BusinessCurve businessCurve=businessCurveService.getById(id);
		
		return getAutoView().addObject("businessCurve",businessCurve)
							.addObject("returnUrl",returnUrl).addObject("rtnType",type).addObject("rtnPid",pid);
	}

	/**
	 * 取得业务曲线信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务曲线信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BusinessCurve businessCurve=businessCurveService.getById(id);
		return getAutoView().addObject("businessCurve", businessCurve);
	}
	
}

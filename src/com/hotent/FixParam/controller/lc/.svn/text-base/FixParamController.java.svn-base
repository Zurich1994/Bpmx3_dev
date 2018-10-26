
package com.hotent.FixParam.controller.lc;

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

import com.hotent.FixParam.model.lc.FixParam;
import com.hotent.FixParam.service.lc.FixParamService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:固定指标参数表 控制器类
 */
@Controller
@RequestMapping("/FixParam/lc/fixParam/")
public class FixParamController extends BaseController
{
	@Resource
	private FixParamService fixParamService;
	/**
	 * 添加或更新固定指标参数表。
	 * @param request
	 * @param response
	 * @param fixParam 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新固定指标参数表")
	public void save(HttpServletRequest request, HttpServletResponse response,FixParam fixParam) throws Exception
	{
		String resultMsg=null;	
		String processId = request.getParameter("processId");
		String timeType = request.getParameter("timeType");
		System.out.println("processId:"+processId);
		System.out.println("timeType:"+timeType);
		request.getSession().setAttribute("processId", processId);
		request.getSession().setAttribute("timeType", timeType);
		try{
			if(fixParam.getId()==null){
				Long id=UniqueIdUtil.genId();
				fixParam.setId(id);
				fixParamService.add(fixParam);
				resultMsg=getText("添加","固定指标参数表");
			}else{
			    fixParamService.update(fixParam);
				resultMsg=getText("更新","固定指标参数表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得固定指标参数表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看固定指标参数表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String processId = String.valueOf(request.getSession().getAttribute("processId"));
		String timeType = String.valueOf(request.getSession().getAttribute("timeType"));
		//List<FixParam> list=fixParamService.getAll(new QueryFilter(request,"fixParamItem"));
		List<FixParam> list=fixParamService.getAllByTimeType(timeType);
		ModelAndView mv=this.getAutoView().addObject("fixParamList",list).addObject("processId",processId).addObject("timeType",timeType);
		
		return mv;
	}
	
	/**
	 * 删除固定指标参数表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除固定指标参数表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			fixParamService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除固定指标参数表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑固定指标参数表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑固定指标参数表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		FixParam fixParam=fixParamService.getById(id);
		
		return getAutoView().addObject("fixParam",fixParam)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得固定指标参数表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看固定指标参数表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		FixParam fixParam=fixParamService.getById(id);
		return getAutoView().addObject("fixParam", fixParam);
	}
	
}

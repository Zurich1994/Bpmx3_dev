
package com.hotent.HistoryScale.controller.lc;

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

import com.hotent.HistoryScale.model.lc.HistoryScale;
import com.hotent.HistoryScale.service.lc.HistoryScaleService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:历史数据发生比例表 控制器类
 */
@Controller
@RequestMapping("/HistoryScale/lc/historyScale/")
public class HistoryScaleController extends BaseController
{
	@Resource
	private HistoryScaleService historyScaleService;
	/**
	 * 添加或更新历史数据发生比例表。
	 * @param request
	 * @param response
	 * @param historyScale 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新历史数据发生比例表")
	public void save(HttpServletRequest request, HttpServletResponse response,HistoryScale historyScale) throws Exception
	{
		String resultMsg=null;	
		String processId = request.getParameter("processId");
		System.out.println("processId:"+processId);
		request.getSession().setAttribute("processId", processId);
		try{
			if(historyScale.getId()==null){
				Long id=UniqueIdUtil.genId();
				historyScale.setId(id);
				historyScale.setProcesstId(processId);
				historyScaleService.add(historyScale);
				resultMsg=getText("添加","历史数据发生比例表");
			}else{
			    historyScaleService.update(historyScale);
				resultMsg=getText("更新","历史数据发生比例表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得历史数据发生比例表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看历史数据发生比例表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String processId =String.valueOf(request.getSession().getAttribute("processId"));
		
		List<HistoryScale> list=historyScaleService.getAll(new QueryFilter(request,"historyScaleItem"));
		
		ModelAndView mv=this.getAutoView().addObject("historyScaleList",list).addObject("processId",processId);
		
		return mv;
	}
	
	/**
	 * 删除历史数据发生比例表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除历史数据发生比例表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			historyScaleService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除历史数据发生比例表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑历史数据发生比例表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑历史数据发生比例表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//System.out.println("???///");
		Long id=RequestUtil.getLong(request,"id");
		String type = request.getParameter("timeType");
		request.getSession().setAttribute("sendTimeType", type);
		String pid = request.getParameter("pid");
		System.out.println("type: ?"+type+"?  pid:?"+pid);
		String returnUrl=RequestUtil.getPrePage(request);
		HistoryScale historyScale=historyScaleService.getById(id);
		
		return getAutoView().addObject("historyScale",historyScale)
							.addObject("returnUrl",returnUrl).addObject("rtnType",type).addObject("rtnPid",pid);
	}

	/**
	 * 取得历史数据发生比例表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看历史数据发生比例表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		HistoryScale historyScale=historyScaleService.getById(id);
		return getAutoView().addObject("historyScale", historyScale);
	}
	
}

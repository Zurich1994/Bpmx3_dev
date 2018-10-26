
package com.hotent.HistoryFlowRely.controller.lc;

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

import com.hotent.HistoryFlowRely.model.lc.HistoryFlowRely;
import com.hotent.HistoryFlowRely.service.lc.HistoryFlowRelyService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:历史数据流程依赖表 控制器类
 */
@Controller
@RequestMapping("/HistoryFlowRely/lc/historyFlowRelyLc/")
public class HistoryFlowRelyController extends BaseController
{
	@Resource
	private HistoryFlowRelyService historyFlowRelyLcService;
	/**
	 * 添加或更新历史数据流程依赖表。
	 * @param request
	 * @param response
	 * @param historyFlowRelyLc 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新历史数据流程依赖表")
	public void save(HttpServletRequest request, HttpServletResponse response,HistoryFlowRely historyFlowRelyLc) throws Exception
	{
		String resultMsg=null;		
		try{
			if(historyFlowRelyLc.getId()==null){
				Long id=UniqueIdUtil.genId();
				historyFlowRelyLc.setId(id);
				historyFlowRelyLcService.add(historyFlowRelyLc);
				resultMsg=getText("添加","历史数据流程依赖表");
			}else{
			    historyFlowRelyLcService.update(historyFlowRelyLc);
				resultMsg=getText("更新","历史数据流程依赖表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得历史数据流程依赖表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看历史数据流程依赖表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<HistoryFlowRely> list=historyFlowRelyLcService.getAll(new QueryFilter(request,"historyFlowRelyLcItem"));
		ModelAndView mv=this.getAutoView().addObject("historyFlowRelyLcList",list);
		
		return mv;
	}
	
	/**
	 * 删除历史数据流程依赖表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除历史数据流程依赖表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			historyFlowRelyLcService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除历史数据流程依赖表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑历史数据流程依赖表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑历史数据流程依赖表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		HistoryFlowRely historyFlowRelyLc=historyFlowRelyLcService.getById(id);
		
		return getAutoView().addObject("historyFlowRelyLc",historyFlowRelyLc)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得历史数据流程依赖表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看历史数据流程依赖表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		HistoryFlowRely historyFlowRelyLc=historyFlowRelyLcService.getById(id);
		return getAutoView().addObject("historyFlowRelyLc", historyFlowRelyLc);
	}
	
}

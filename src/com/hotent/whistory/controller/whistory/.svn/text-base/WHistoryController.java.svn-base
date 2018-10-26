
package com.hotent.whistory.controller.whistory;

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

import com.hotent.whistory.model.whistory.WHistory;
import com.hotent.whistory.service.whistory.WHistoryService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:w_history 控制器类
 */
@Controller
@RequestMapping("/whistory/whistory/wHistory/")
public class WHistoryController extends BaseController
{
	@Resource
	private WHistoryService wHistoryService;
	/**
	 * 添加或更新w_history。
	 * @param request
	 * @param response
	 * @param wHistory 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新w_history")
	public void save(HttpServletRequest request, HttpServletResponse response,WHistory wHistory) throws Exception
	{
		String resultMsg=null;		
		try{
			if(wHistory.getH_c_id()==null){
				Long h_c_id=UniqueIdUtil.genId();
				wHistory.setH_c_id(h_c_id);
				wHistoryService.add(wHistory);
				resultMsg=getText("添加","w_history");
			}else{
			    wHistoryService.update(wHistory);
				resultMsg=getText("更新","w_history");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得w_history分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看w_history分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<WHistory> list=wHistoryService.getAll(new QueryFilter(request,"wHistoryItem"));
		ModelAndView mv=this.getAutoView().addObject("wHistoryList",list);
		
		return mv;
	}
	
	/**
	 * 删除w_history
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除w_history")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			wHistoryService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除w_history成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑w_history
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑w_history")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long h_c_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		WHistory wHistory=wHistoryService.getById(h_c_id);
		
		return getAutoView().addObject("wHistory",wHistory)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得w_history明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看w_history明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long h_c_id=RequestUtil.getLong(request,"id");
		WHistory wHistory=wHistoryService.getById(h_c_id);
		return getAutoView().addObject("wHistory", wHistory);
	}
	
}


package com.hotent.RegularOccurrence.controller.lc;

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

import com.hotent.RegularOccurrence.model.lc.Regularoccurrence;
import com.hotent.RegularOccurrence.service.lc.RegularoccurrenceService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:发生规律信息表 控制器类
 */
@Controller
@RequestMapping("/RegularOccurrence/lc/regularoccurrence/")
public class RegularoccurrenceController extends BaseController
{
	@Resource
	private RegularoccurrenceService regularoccurrenceService;
	/**
	 * 添加或更新发生规律信息表。
	 * @param request
	 * @param response
	 * @param regularoccurrence 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新发生规律信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,Regularoccurrence regularoccurrence) throws Exception
	{
		String resultMsg=null;		
		try{
			if(regularoccurrence.getId()==null){
				Long id=UniqueIdUtil.genId();
				regularoccurrence.setId(id);
				regularoccurrenceService.add(regularoccurrence);
				resultMsg=getText("添加","发生规律信息表");
			}else{
			    regularoccurrenceService.update(regularoccurrence);
				resultMsg=getText("更新","发生规律信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得发生规律信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看发生规律信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Regularoccurrence> list=regularoccurrenceService.getAll(new QueryFilter(request,"regularoccurrenceItem"));
		ModelAndView mv=this.getAutoView().addObject("regularoccurrenceList",list);
		
		return mv;
	}
	
	/**
	 * 删除发生规律信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除发生规律信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			regularoccurrenceService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除发生规律信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑发生规律信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑发生规律信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Regularoccurrence regularoccurrence=regularoccurrenceService.getById(id);
		
		return getAutoView().addObject("regularoccurrence",regularoccurrence)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得发生规律信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看发生规律信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Regularoccurrence regularoccurrence=regularoccurrenceService.getById(id);
		return getAutoView().addObject("regularoccurrence", regularoccurrence);
	}
	
}

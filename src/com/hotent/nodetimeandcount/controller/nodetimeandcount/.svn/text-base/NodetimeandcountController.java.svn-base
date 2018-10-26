
package com.hotent.nodetimeandcount.controller.nodetimeandcount;

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

import com.hotent.nodetimeandcount.model.nodetimeandcount.Nodetimeandcount;
import com.hotent.nodetimeandcount.service.nodetimeandcount.NodetimeandcountService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:节点发生时间与发生量表 控制器类
 */
@Controller
@RequestMapping("/nodetimeandcount/nodetimeandcount/nodetimeandcount/")
public class NodetimeandcountController extends BaseController
{
	@Resource
	private NodetimeandcountService nodetimeandcountService;
	/**
	 * 添加或更新节点发生时间与发生量表。
	 * @param request
	 * @param response
	 * @param nodetimeandcount 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新节点发生时间与发生量表")
	public void save(HttpServletRequest request, HttpServletResponse response,Nodetimeandcount nodetimeandcount) throws Exception
	{
		String resultMsg=null;		
		try{
			if(nodetimeandcount.getId()==null){
				Long id=UniqueIdUtil.genId();
				nodetimeandcount.setId(id);
				nodetimeandcountService.add(nodetimeandcount);
				resultMsg=getText("添加","节点发生时间与发生量表");
			}else{
			    nodetimeandcountService.update(nodetimeandcount);
				resultMsg=getText("更新","节点发生时间与发生量表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得节点发生时间与发生量表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看节点发生时间与发生量表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Nodetimeandcount> list=nodetimeandcountService.getAll(new QueryFilter(request,"nodetimeandcountItem"));
		ModelAndView mv=this.getAutoView().addObject("nodetimeandcountList",list);
		
		return mv;
	}
	
	/**
	 * 删除节点发生时间与发生量表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除节点发生时间与发生量表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			nodetimeandcountService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除节点发生时间与发生量表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑节点发生时间与发生量表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑节点发生时间与发生量表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Nodetimeandcount nodetimeandcount=nodetimeandcountService.getById(id);
		
		return getAutoView().addObject("nodetimeandcount",nodetimeandcount)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得节点发生时间与发生量表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看节点发生时间与发生量表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Nodetimeandcount nodetimeandcount=nodetimeandcountService.getById(id);
		return getAutoView().addObject("nodetimeandcount", nodetimeandcount);
	}
	
}

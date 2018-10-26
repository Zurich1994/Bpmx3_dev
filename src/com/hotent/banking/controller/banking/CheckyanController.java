
package com.hotent.banking.controller.banking;

import java.util.ArrayList;
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

import com.hotent.banking.model.banking.Checkyan;
import com.hotent.banking.service.banking.CheckyanService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:支票查看表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/checkyan/")
public class CheckyanController extends BaseController
{
	@Resource
	private CheckyanService checkyanService;
	/**
	 * 添加或更新支票查看表。
	 * @param request
	 * @param response
	 * @param checkyan 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新支票查看表")
	public void save(HttpServletRequest request, HttpServletResponse response,Checkyan checkyan) throws Exception
	{
		String resultMsg=null;		
		try{
			if(checkyan.getId()==null){
				Long id=UniqueIdUtil.genId();
				checkyan.setId(id);
				checkyanService.add(checkyan);
				resultMsg=getText("添加","支票查看表");
			}else{
			    checkyanService.update(checkyan);
				resultMsg=getText("更新","支票查看表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得支票查看表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看支票查看表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Checkyan> list=checkyanService.getAll(new QueryFilter(request,"checkyanItem"));
		List<Checkyan> lists=new ArrayList<Checkyan>();
		String name = RequestUtil.getString(request,"name");
		
		for(int i = 0;i<lists.size();i++){
			if(lists.get(i).getUserid().equals(name)){
				lists.add(list.get(i));
			}
		}
		ModelAndView mv=this.getAutoView().addObject("checkyanList",lists);
		
		return mv;
	}
	
	/**
	 * 删除支票查看表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除支票查看表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			checkyanService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除支票查看表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑支票查看表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑支票查看表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Checkyan checkyan=checkyanService.getById(id);
		
		return getAutoView().addObject("checkyan",checkyan)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得支票查看表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看支票查看表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Checkyan checkyan=checkyanService.getById(id);
		return getAutoView().addObject("checkyan", checkyan);
	}
	
	
	/**
	 * 取得查询结果
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Result")
	@Action(description="查看支票查看表分页列表")
	public ModelAndView result(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Checkyan> list=checkyanService.getAll(new QueryFilter(request,"checkyanItem"));
		List<Checkyan> lists=new ArrayList<Checkyan>();
		String name = RequestUtil.getString(request,"name");
		
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUserid().equals(name)){
				lists.add(list.get(i));
			}
		}
		
		ModelAndView mv=this.getAutoView().addObject("checkyanList",lists).addObject("name",name);
		
		return mv;
	}
}

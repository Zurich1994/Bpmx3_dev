
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

import com.hotent.banking.model.banking.checkaccount;
import com.hotent.banking.service.banking.checkaccountService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:账户概要 控制器类
 */
@Controller
@RequestMapping("/banking/banking/checkaccount/")
public class checkaccountController extends BaseController
{
	int Checking = 1;
	@Resource
	private checkaccountService checkaccountService;
	/**
	 * 添加或更新账户概要。
	 * @param request
	 * @param response
	 * @param checkaccount 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新加载订单")
	public void save(HttpServletRequest request, HttpServletResponse response,checkaccount checkaccount) throws Exception
	{
		String resultMsg=null;		
		try{
			if(checkaccount.getId()==null){
				Long id=UniqueIdUtil.genId();
				checkaccount.setId(id);
				checkaccountService.add(checkaccount);
				resultMsg=getText("添加","加载订单");
			}else{
			    checkaccountService.update(checkaccount);
				resultMsg=getText("更新","加载订单");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	/**
	 * 取得账户概要分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看账户概要分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<checkaccount> list=checkaccountService.getAll(new QueryFilter(request,"checkaccountItem"));
		List<checkaccount> lists=new ArrayList<checkaccount>();
		String name = RequestUtil.getString(request,"name");
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUSERID().equals(name)){
				lists.add(list.get(i));
			}
		}
		for(int i=0;i<lists.size();i++)
		{
			if(lists.get(i).getTYPE().equals(1))
			{
				lists.get(i).setTYPE("Checking");
			}else if(lists.get(i).getTYPE().equals(2))
			{
				lists.get(i).setTYPE("Saving");
			}else 
			{
				lists.get(i).setTYPE("Other");
			}
		}
		ModelAndView mv=this.getAutoView().addObject("checkaccountList",lists);
		
		return mv;
	}
	
	/**
	 * 删除账户概要
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除账户概要")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			checkaccountService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除账户概要成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑账户概要
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑账户概要")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		checkaccount checkaccount=checkaccountService.getById(id);
		
		return getAutoView().addObject("checkaccount",checkaccount)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得账户概要明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看账户概要明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		checkaccount checkaccount=checkaccountService.getById(id);
		return getAutoView().addObject("checkaccount", checkaccount);
	}
	/**
	 * 取得审查
	 * @param request   
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("2Edit")
	@Action(description="查看账户概要分页列表")
	public ModelAndView results(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//Long id=RequestUtil.getLong(request,"name");
		String account_no=RequestUtil.getString(request,"account_no");
		String number=RequestUtil.getString(request,"number");
		String check_design=RequestUtil.getString(request,"check_design");
		String name=RequestUtil.getString(request,"name");
		//response.sendRedirect(request.getContextPath()+"/Order_check/Order_check/Order_check/Edit.ht?account_no="+account_no+"&number="+ number);
		
		ModelAndView mv=this.getAutoView().addObject("account_no", account_no).addObject("number", number).addObject("name", name).addObject("check_design", check_design);
		
		return mv;
	}
}

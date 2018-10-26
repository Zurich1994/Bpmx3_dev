
package com.hotent.banking.controller.banking;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.banking.model.banking.Account1;
import com.hotent.banking.service.banking.Account1Service;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:账户概要 控制器类
 */
@Controller
@RequestMapping("/banking/banking/account1/")
public class Account1Controller extends BaseController
{
	@Resource
	//static String userId;
	private Account1Service account1Service;
	String currentUserId;
	/**
	 * 添加或更新账户概要。
	 * @param request
	 * @param response
	 * @param account1 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新账户概要")
	public void save(HttpServletRequest request, HttpServletResponse response,Account1 account1) throws Exception
	{
		String resultMsg=null;		
		try{
			if(account1.getId()==null){
				Long id=UniqueIdUtil.genId();
				account1.setId(id);
				account1Service.add(account1);
				resultMsg=getText("添加","账户概要");
			}else{
			    account1Service.update(account1);
				resultMsg=getText("更新","账户概要");
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
		currentUserId =request.getParameter("name");
		List<Account1> list=account1Service.getUSERID(currentUserId);
		for(int i=0;i<list.size();i++)
		{
		 if(list.get(i).getTYPE().equals("1"))
		 {
			 list.get(i).setTYPE("Checking");
		 }
		 else if(list.get(i).getTYPE().equals("2"))
		 {
			 list.get(i).setTYPE("Saving");
		 }
		 else 
		 {
			 list.get(i).setTYPE("Other");
		 }
		}
		ModelAndView mv=this.getAutoView().addObject("account1List",list);
		//mv.addObject("type",)
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
			account1Service.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除账户概要成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	@RequestMapping("returntoSuccess")
	@Action(description="删除账户概要")
	public void returntoSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
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
		Account1 account1=account1Service.getById(id);
		
		return getAutoView().addObject("account1",account1)
		                    .addObject("currentUserId",currentUserId)
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
		Account1 account1=account1Service.getById(id);
			
		return getAutoView().addObject("account1", account1);
	}
	
}

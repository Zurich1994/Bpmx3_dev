
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

import com.hotent.banking.model.banking.Account;
import com.hotent.banking.service.banking.AccountService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:账户信息表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/account/")
public class AccountController extends BaseController
{
	@Resource
	private AccountService accountService;
	/**
	 * 添加或更新账户信息表。
	 * @param request
	 * @param response
	 * @param account 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新账户信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,Account account) throws Exception
	{
		String resultMsg=null;		
		try{
			if(account.getId()==null){
				Long id=UniqueIdUtil.genId();
				account.setId(id);
				accountService.add(account);
				resultMsg=getText("添加","账户信息表");
			}else{
			    accountService.update(account);
				resultMsg=getText("更新","账户信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	@RequestMapping("transfer")
	@Action(description="银行账户转账")
	public String ModelAndView(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		String  name=RequestUtil.getString(request,"nowaccount2");
		
		

		
		System.out.println("成功跳转到controller层！");
		double amount = RequestUtil.getLong(request, "amount");
		System.out.println("amount "+amount);
		
		//取出当前用户的账户余额，减去金额并更新回数据库；
		double nowaccount=RequestUtil.getLong(request,"nowaccount1");
		System.out.println("nowacount "+nowaccount);
		
		Account account1=accountService.getByAccountno(nowaccount);
		double nowblance=account1.getBALANCE();
		/*if(nowblance<amount){
			//跳转至报错页面
			request.getRequestDispatcher("/bankTransfer/bankTransfer/transferError.ht").forward(request, response);
		}*/
		nowblance=nowblance-amount;
		System.out.println("nowblance "+nowblance);
		accountService.update2(nowblance,nowaccount);
		
		
		//取出目标用户的账户余额，增加金额并更新回数据库；
		Long toaccount=RequestUtil.getLong(request,"toaccount");
		System.out.println("towacount "+toaccount);
		Account account2=accountService.getByAccountno(toaccount);
		double toblance=account2.getBALANCE();
		toblance=toblance+amount;
		System.out.println("toblance "+toblance);
		accountService.update2(toblance,toaccount);
	    
		/*accountService.transfer1(nowaccount,amount);	
		accountService.transfer2(toaccount,amount);	
		*/
		
		//String name=request.getParameter("name");
	
		
		return "redirect:/banking/banking/account/list.ht?name="+name;
		
		
		

	}
	
	
	/**
	 * 取得账户信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看指定账户信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//String name=request.getParameter("name");
		String name=RequestUtil.getString(request, "name");
		System.out.println("name:"+name);
		List<Account> list=accountService.getAll(new QueryFilter(request,"accountItem"));
		List<Account> lists = new ArrayList<Account>();
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUSERID().equals(name)){
				lists.add(list.get(i));
			}
		}
		ModelAndView mv=this.getAutoView().addObject("accountList",lists);
		return mv;
	}
	
	@RequestMapping("listb")
	@Action(description="查看账户信息表分页列表")
	public ModelAndView list1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String currentUserId=RequestUtil.getString(request, "name");
		System.out.println("name:"+currentUserId);
		List<Account> list=accountService.getAll(new QueryFilter(request,"accountItem"));
		List<Account> list2 = new ArrayList<Account>();
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUSERID().equals(currentUserId)){
				list2.add(list.get(i));
			}
		}
		ModelAndView mv=this.getAutoView().addObject("accountList",list2);
		return mv;
	}
	
	/**
	 * 删除账户信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除账户信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			accountService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除账户信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑账户信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑账户信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Account account=accountService.getById(id);
		
		return getAutoView().addObject("account",account)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得账户信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看账户信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Account account=accountService.getById(id);
		return getAutoView().addObject("account", account);
	}
	
}

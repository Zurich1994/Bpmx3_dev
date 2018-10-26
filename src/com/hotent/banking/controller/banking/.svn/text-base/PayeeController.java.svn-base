
package com.hotent.banking.controller.banking;

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

import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.banking.model.banking.Payee;
import com.hotent.banking.service.banking.PayeeService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:支付者信息表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/payee/")
public class PayeeController extends BaseController
{
	@Resource
	private PayeeService payeeService;
	/**
	 * 添加或更新支付者信息表。
	 * @param request
	 * @param response
	 * @param payee 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新支付者信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,Payee payee) throws Exception
	{
		String resultMsg=null;		
		try{
			if(payee.getId()==null){
				Long id=UniqueIdUtil.genId();
				payee.setId(id);
				payeeService.add(payee);
				resultMsg=getText("添加","支付者信息表");
			}else{
			    payeeService.update(payee);
				resultMsg=getText("更新","支付者信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得支付者信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看支付者信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Payee> list=payeeService.getAll(new QueryFilter(request,"payeeItem"));
		ModelAndView mv=this.getAutoView().addObject("payeeList",list);
		
		return mv;
	}
	
	/**
	 * 删除支付者信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除支付者信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			payeeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除支付者信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑支付者信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑支付者信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Payee payee=payeeService.getById(id);
		
		return getAutoView().addObject("payee",payee)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得支付者信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看支付者信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		//Long CurrentUserId=ContextUtil.getCurrentUserId();
		//String currentUserId = CurrentUserId.toString();
		//Long id=RequestUtil.getLong(request,"id");;
		//String payeeUserId = PayeeUserId.toString();
//		System.out.println(CurrentUserId);
//		String a="34";
		String name=RequestUtil.getString(request,"name");
		System.out.println(name);
		
		/*HttpSession session = request.getSession(); 
		userId=(String)session.getAttribute("userid"); 
		System.out.println("userid");*/
		Payee get=payeeService.getUSERID(name);
		return getAutoView().addObject("payee", get);
	}
	/**
	 * 取得支付者信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Confirmation")
	@Action(description="查看支付者信息表明细")
	public ModelAndView Confirmation(HttpServletRequest request, HttpServletResponse response) throws Exception
	{   

		String name=RequestUtil.getString(request,"name");
		System.out.println(name);
		Payee get=payeeService.getUSERID(name);
		return getAutoView().addObject("payee", get);
	}
	
}


package com.hotent.banking.controller.banking;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.css.engine.value.ValueConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.banking.model.banking.BillPay;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.banking.model.banking.quickpay;
import com.hotent.banking.service.banking.quickpayService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户ID 控制器类
 */
@Controller
@RequestMapping("/banking/banking/quickpay/")
public class quickpayController extends BaseController
{
	@Resource
	private quickpayService quickpayService;
	private String userid ;
	/**
	 * 添加或更新用户ID。
	 * @param request
	 * @param response
	 * @param quickpay 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户ID")
	public void save(HttpServletRequest request, HttpServletResponse response,quickpay quickpay) throws Exception
	{
		String resultMsg=null;		
		try{
			if(quickpay.getId()==null){
				Long id=UniqueIdUtil.genId();
				quickpay.setId(id);
				quickpayService.add(quickpay);
				resultMsg=getText("添加","用户ID");
			}else{
			    quickpayService.update(quickpay);
				resultMsg=getText("更新","用户ID");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得用户ID分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看用户ID分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String userid =request.getParameter("name");
		System.out.println("aaaa");
	    System.out.println(userid);
	   
	    List<quickpay> list=quickpayService.getUSERID(userid);
		ModelAndView mv=this.getAutoView().addObject("quickpayList",list);
		return mv;
	}
	
	/**
	 * 删除用户ID
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除用户ID")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			quickpayService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除用户ID成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑用户ID
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑用户ID")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{   
	
		String name=RequestUtil.getString(request, "name");
		
		System.out.println(name);
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		quickpay quickpay=quickpayService.getById(id);
		
		return getAutoView().addObject("quickpay",quickpay)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得用户ID明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看用户ID明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		quickpay quickpay=quickpayService.getById(id);
		return getAutoView().addObject("quickpay", quickpay);
	}
	
}

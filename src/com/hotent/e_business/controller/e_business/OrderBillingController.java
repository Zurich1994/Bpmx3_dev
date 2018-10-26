
package com.hotent.e_business.controller.e_business;

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

import com.hotent.e_business.model.e_business.OrderBilling;
import com.hotent.e_business.model.e_business.registration;
import com.hotent.e_business.service.e_business.OrderBillingService;
import com.hotent.core.web.ResultMessage;
import com.sun.mail.iap.Response;
/**
 * 对象功能:ORDER_BILLING 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/orderBilling/")
public class OrderBillingController extends BaseController
{
	@Resource
	private OrderBillingService orderBillingService;
	/**
	 * 添加或更新ORDER_BILLING。
	 * @param request
	 * @param response
	 * @param orderBilling 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新ORDER_BILLING")
	public void save(HttpServletRequest request, HttpServletResponse response,OrderBilling orderBilling) throws Exception
	{
		String resultMsg=null;	
		String email = RequestUtil.getString(request, "EMAIL");
		System.out.println(email);
		try{
			if(orderBilling.getId()==null){
				Long id=UniqueIdUtil.genId();
				orderBilling.setId(id);
				orderBilling.setEMAIL(email);
				orderBillingService.add(orderBilling);
				resultMsg=getText("添加","ORDER_BILLING");
			}else{
			    orderBillingService.update(orderBilling);
				resultMsg=getText("更新","ORDER_BILLING");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得ORDER_BILLING分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看ORDER_BILLING分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<OrderBilling> list=orderBillingService.getAll(new QueryFilter(request,"orderBillingItem"));
		ModelAndView mv=this.getAutoView().addObject("orderBillingList",list);
		
		return mv;	
	}
	
	/**
	 * 删除ORDER_BILLING
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除ORDER_BILLING")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			orderBillingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除ORDER_BILLING成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑ORDER_BILLING
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑ORDER_BILLING")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//OrderBilling orderBilling = new OrderBilling();
		/*String EMAIL = RequestUtil.getString(request, "EMAIL");
		String FIRSTNAME =RequestUtil.getString(request,"FIRSTNAME");
		String LASTNAME  =RequestUtil.getString(request,"LASTNAME");
		String ADDRESS  =RequestUtil.getString(request,"ADDRESS");
		String CITY  =RequestUtil.getString(request,"CITY");
		String STATE  =RequestUtil.getString(request,"STATE");
		String ZIP  =RequestUtil.getString(request,"ZIP");
		String PHONE  =RequestUtil.getString(request,"PHONE");
		
		System.out.println(EMAIL+FIRSTNAME+LASTNAME+ADDRESS+CITY+STATE+ZIP+PHONE);
		orderBilling.setEMAIL(EMAIL);
		orderBilling.setFIRSTNAME(FIRSTNAME);
		orderBilling.setLASTNAME(LASTNAME);
		orderBilling.setADDRESS(ADDRESS);
		orderBilling.setCITY(CITY);
		orderBilling.setSTATE(STATE);
		orderBilling.setZIP(ZIP);
		orderBilling.setPHONE(PHONE);	*/	
        Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		OrderBilling orderBilling = orderBillingService.getById(id);
		return getAutoView().addObject("orderBilling",orderBilling).addObject("returnUrl",returnUrl);				
	}

	
	
	
	@RequestMapping("add")
	@Action(description="编辑ORDER_BILLING")
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OrderBilling orderBilling = new OrderBilling();
		Long id = UniqueIdUtil.genId();
		String EMAIL = RequestUtil.getString(request, "EMAIL");
		String FIRSTNAME =RequestUtil.getString(request,"FIRSTNAME");
		String LASTNAME  =RequestUtil.getString(request,"LASTNAME");
		String ADDRESS  =RequestUtil.getString(request,"ADDRESS");
		String CITY  =RequestUtil.getString(request,"CITY");
		String STATE  =RequestUtil.getString(request,"STATE");
		String ZIP  =RequestUtil.getString(request,"ZIP");
		String PHONE  =RequestUtil.getString(request,"PHONE");
		String SHIPPING  =RequestUtil.getString(request,"SHIPPING");
		
		System.out.println("EMAIL:   "+EMAIL+"FIRSTNAME:  "+FIRSTNAME+"LASTNAME:   "+LASTNAME+"ADDRESS:   "+
				ADDRESS+"CITY:   "+CITY+"STATE:  "+STATE+"ZIP:  "+ZIP+"PHONE:  "+PHONE+"SHIPPING:  "+SHIPPING);
		orderBilling.setId(id);
		orderBilling.setEMAIL(EMAIL);
		orderBilling.setFIRSTNAME(FIRSTNAME);
		orderBilling.setLASTNAME(LASTNAME);
		orderBilling.setADDRESS(ADDRESS);
		orderBilling.setCITY(CITY);
		orderBilling.setSTATE(STATE);
		orderBilling.setZIP(ZIP);
		orderBilling.setPHONE(PHONE);
		orderBilling.setCC_TYPE(SHIPPING);
		
		orderBillingService.add(orderBilling);
		response.sendRedirect("/mas/e_business/e_business/orderBilling/list.ht");
	}
	
	
	/**
	 * 取得ORDER_BILLING明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看ORDER_BILLING明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		OrderBilling orderBilling=orderBillingService.getById(id);
		return getAutoView().addObject("orderBilling", orderBilling);
	}
	
	
	@RequestMapping("edits")
	@Action(description="编辑用户信息表")
	public ModelAndView edits(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		OrderBilling orderBilling=orderBillingService.getById(id);
		
		return getAutoView().addObject("OrderBilling",orderBilling)
							.addObject("returnUrl",returnUrl);
	}

}

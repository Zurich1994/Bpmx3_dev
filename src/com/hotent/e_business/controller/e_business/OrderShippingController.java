
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

import com.hotent.e_business.model.e_business.Cart;
import com.hotent.e_business.model.e_business.OrderShipping;
import com.hotent.e_business.service.e_business.OrderShippingService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:运货表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/orderShipping/")
public class OrderShippingController extends BaseController
{
	@Resource
	private OrderShippingService orderShippingService;
	/**
	 * 添加或更新运货表。
	 * @param request
	 * @param response
	 * @param orderShipping 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新运货表")
	public void save(HttpServletRequest request, HttpServletResponse response,OrderShipping orderShipping) throws Exception
	{
		String resultMsg=null;	
		String email = RequestUtil.getString(request,"EMAIL");
		System.out.println("email= "+email);
		try{
			if(orderShipping.getId()==null){
				Long id=UniqueIdUtil.genId();
				orderShipping.setId(id);
				orderShipping.setEMAIL(email);
				orderShippingService.add(orderShipping);
				resultMsg=getText("添加","运货表");
			}else{
			    orderShippingService.update(orderShipping);
				resultMsg=getText("更新","运货表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得运货表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看运货表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//List<OrderShipping> list=orderShippingService.getAll(new QueryFilter(request,"orderShippingItem"));
		String email = request.getParameter("EMAIL");
		email = "126548476@qq.com";
		List<OrderShipping> list = orderShippingService.getByEMAIL(email);
		ModelAndView mv=this.getAutoView().addObject("orderShippingList",list).addObject("EMAIL", email);
		
		return mv;
	}
	
	/**
	 * 删除运货表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除运货表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			orderShippingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除运货表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑运货表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑运货表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
/*		Long id=RequestUtil.getLong(request,"id");
		System.out.println(id);
		String returnUrl=RequestUtil.getPrePage(request);
		OrderShipping orderShipping=orderShippingService.getById(id);     */
		Long id=RequestUtil.getLong(request,"id");
		OrderShipping orderShipping =orderShippingService.getById(id);
		ModelAndView mv = this.getAutoView().addObject("orderShipping",orderShipping);
		System.out.println(mv);
		return mv;
	}

	/**
	 * 取得运货表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看运货表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		OrderShipping orderShipping=orderShippingService.getById(id);
		return getAutoView().addObject("orderShipping",orderShipping).addObject("returnUrl",returnUrl);
		//return getAutoView().addObject("orderShipping", orderShipping);
	}
	
}

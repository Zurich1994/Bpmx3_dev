
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
import com.hotent.e_business.service.e_business.CartService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:购物车 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/cart/")
public class CartController extends BaseController
{
	@Resource
	private CartService cartService;
	/**
	 * 添加或更新购物车。
	 * @param request
	 * @param response
	 * @param cart 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新购物车")
	public void save(HttpServletRequest request, HttpServletResponse response,Cart cart) throws Exception
	{
		String resultMsg=null;		
		try{ 
			if(cart.getId()==null){
				Long id=UniqueIdUtil.genId();
				cart.setId(id);
				cartService.add(cart);
				resultMsg=getText("添加","购物车");
			}else{
			    cartService.update(cart);
				resultMsg=getText("更新","购物车");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得购物车分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看购物车分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Cart> list=cartService.getAll(new QueryFilter(request,"cartItem"));
		ModelAndView mv=this.getAutoView().addObject("cartList",list);
		
		return mv;
	}
	
	/**
	 * 删除购物车
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除购物车")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			cartService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除购物车成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑购物车
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑购物车")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		Long EMAIL=Long.parseLong(request.getParameter("id"));		
		Cart cart=cartService.getById(EMAIL);
		
		return getAutoView().addObject("cart",cart)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得购物车明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看购物车明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Cart cart=cartService.getById(id);
		return getAutoView().addObject("cart", cart);
	}
	@RequestMapping("edits")
	@Action(description="编辑用户信息表")
	public ModelAndView edits(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Cart cart=cartService.getById(id);
		
		return getAutoView().addObject("cart",cart)
							.addObject("returnUrl",returnUrl);
	}
}


package com.hotent.tpcc.controller.tpcc;

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

import com.hotent.tpcc.model.tpcc.NewOrders;
import com.hotent.tpcc.service.tpcc.NewOrdersService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:new_orders 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/newOrders/")
public class NewOrdersController extends BaseController
{
	@Resource
	private NewOrdersService newOrdersService;
	/**
	 * 添加或更新new_orders。
	 * @param request
	 * @param response
	 * @param newOrders 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新new_orders")
	public void save(HttpServletRequest request, HttpServletResponse response,NewOrders newOrders) throws Exception
	{
		String resultMsg=null;		
		try{
			if(newOrders.getNo_o_id()==null){
				Long no_o_id=UniqueIdUtil.genId();
				newOrders.setNo_o_id(no_o_id);
				newOrdersService.add(newOrders);
				resultMsg=getText("添加","new_orders");
			}else{
			    newOrdersService.update(newOrders);
				resultMsg=getText("更新","new_orders");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得new_orders分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看new_orders分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<NewOrders> list=newOrdersService.getAll(new QueryFilter(request,"newOrdersItem"));
		ModelAndView mv=this.getAutoView().addObject("newOrdersList",list);
		
		return mv;
	}
	
	/**
	 * 删除new_orders
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除new_orders")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			newOrdersService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除new_orders成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑new_orders
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑new_orders")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long no_o_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		NewOrders newOrders=newOrdersService.getById(no_o_id);
		
		return getAutoView().addObject("newOrders",newOrders)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得new_orders明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看new_orders明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long no_o_id=RequestUtil.getLong(request,"id");
		NewOrders newOrders=newOrdersService.getById(no_o_id);
		return getAutoView().addObject("newOrders", newOrders);
	}
	
}

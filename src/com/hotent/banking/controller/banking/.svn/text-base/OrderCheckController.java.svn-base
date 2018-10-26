
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

import com.hotent.banking.model.banking.OrderCheck;
import com.hotent.banking.service.banking.OrderCheckService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:银行订单表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/orderCheck/")
public class OrderCheckController extends BaseController
{
	@Resource
	private OrderCheckService orderCheckService;
	/**
	 * 添加或更新银行订单表。
	 * @param request
	 * @param response
	 * @param orderCheck 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新银行订单表")
	public void save(HttpServletRequest request, HttpServletResponse response,OrderCheck orderCheck) throws Exception
	{
		String resultMsg=null;		
		try{
			if(orderCheck.getId()==null){
				Long id=UniqueIdUtil.genId();
				orderCheck.setId(id);
				orderCheckService.add(orderCheck);
				resultMsg=getText("添加","银行订单表");
			}else{
			    orderCheckService.update(orderCheck);
				resultMsg=getText("更新","银行订单表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得银行订单表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看银行订单表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<OrderCheck> list=orderCheckService.getAll(new QueryFilter(request,"orderCheckItem"));
		List<OrderCheck> lists = new ArrayList<OrderCheck>();
		String name = RequestUtil.getString(request,"name");
		System.out.println(name);
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUserid().equals(name)){
				lists.add(list.get(i));
			}
		}
		ModelAndView mv=this.getAutoView().addObject("orderCheckList",lists).addObject("name", name);
		
		return mv;
	}
	
	/**
	 * 删除银行订单表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除银行订单表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			orderCheckService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除银行订单表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑银行订单表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit2")
	@Action(description="编辑银行订单表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		OrderCheck orderCheck=orderCheckService.getById(id);
		
		return getAutoView().addObject("orderCheck",orderCheck)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得银行订单表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看银行订单表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		OrderCheck orderCheck=orderCheckService.getById(id);
		return getAutoView().addObject("orderCheck", orderCheck);
	}
	
}

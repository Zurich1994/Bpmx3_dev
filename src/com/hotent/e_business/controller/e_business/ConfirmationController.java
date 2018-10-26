
package com.hotent.e_business.controller.e_business;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.e_business.model.e_business.Confirmation;
import com.hotent.e_business.service.e_business.ConfirmationService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:confirmation 控制器类
 */
@Controller
@RequestMapping("/e_business/loginsee/e_business/")
public class ConfirmationController extends BaseController
{
	@Resource
	private ConfirmationService confirmationService;
	/**
	 * 添加或更新confirmation。
	 * @param request
	 * @param response
	 * @param confirmation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新confirmation")
	public void save(HttpServletRequest request, HttpServletResponse response,Confirmation confirmation) throws Exception
	{
		String resultMsg=null;		
		try{
			if(confirmation.getId()==null){
				Long id=UniqueIdUtil.genId();
				confirmation.setId(id);
				confirmationService.add(confirmation);
				resultMsg=getText("添加","confirmation");
			}else{
			    confirmationService.update(confirmation);
				resultMsg=getText("更新","confirmation");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得confirmation分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看confirmation分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String EMAIL=request.getParameter("EMAIL");
		String sql="SELECT w_cart.F_ITEMName,w_cart.F_COMPONENTTYPE,w_cart.F_QUANTITY as castquantity,w_cart.F_UNITPRICE,"+
				"w_order_shipping.F_FIRSTNAME as shippingname,w_order_shipping.F_ADDRESS as shippingaddress,w_order_shipping.F_CITY as shippingcity,"+
"w_order_shipping.F_STATE as shippingstate,w_order_shipping.F_ZIP as shippingzip,w_order_shipping.F_PHONE as shippingphone,"+
"w_order_shipping.F_SHIPPING,"+
		"w_order_billing.F_FIRSTNAME as billingname,w_order_billing.F_ADDRESS as billingaddress,"+
"w_order_billing.F_CITY as billingcity,w_order_billing.F_STATE as billingstate,"+
"w_order_billing.F_ZIP as billingzip,w_order_billing.F_PHONE as billingphone,"+
"w_order_billing.F_CC_TYPE FROM w_cart INNER JOIN w_order_billing ON(w_cart.F_EMAIL=w_order_billing.F_EMAIL)"+
				"INNER JOIN w_order_shipping on (w_cart.F_EMAIL=w_order_shipping.F_EMAIL)"+
				"where w_cart.F_EMAIL='126548476@qq.com'";
		
				//""+"'"+EMAIL+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		
		for(int i=0;i<list.size();i++)
		{
			
			Map<String,Object> map=list.get(i);
			int number=Integer.parseInt(map.get("castquantity").toString());
			int price=Integer.parseInt(map.get("F_UNITPRICE").toString());
			int totalprice=number*price;
			map.put("totalprice", totalprice);
			/*if(list.get(i).get  .equals("castquantity"))
			{
				int a=(int) list.get(i).get("castquantity");
			}*/
		}
		//List<Confirmation> list=confirmationService.getAll(new QueryFilter(request,"confirmationItem"));
		//List<Confirmation> list=confirmationService.getByEmail("126548476@qq.com");
		ModelAndView mv=this.getAutoView().addObject("confirmationList",list);
		
		return mv;
	}
	
	/**
	 * 删除confirmation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除confirmation")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			confirmationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除confirmation成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑confirmation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑confirmation")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Confirmation confirmation=confirmationService.getById(id);
		
		return getAutoView().addObject("confirmation",confirmation)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得confirmation明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看confirmation明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Confirmation confirmation=confirmationService.getById(id);
		return getAutoView().addObject("confirmation", confirmation);
	}
	
}

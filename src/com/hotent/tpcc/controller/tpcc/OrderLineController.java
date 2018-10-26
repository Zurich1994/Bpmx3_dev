
package com.hotent.tpcc.controller.tpcc;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.tpcc.model.tpcc.NewOrders;
import com.hotent.tpcc.service.tpcc.NewOrdersService;
import com.hotent.tpcc.model.tpcc.OrderLine;
import com.hotent.tpcc.service.tpcc.OrderLineService;
import com.hotent.tpcc.model.tpcc.Orders;
import com.hotent.tpcc.service.tpcc.OrdersService;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.service.bpm.WebserviceHelper;
import com.hotent.tpcc.model.tpcc.Stock;
import com.hotent.tpcc.service.tpcc.StockService;
import com.hotent.core.web.ResultMessage;
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.model.tpcc.District;
import com.hotent.tpcc.service.tpcc.DistrictService;
import com.hotent.tpcc.model.tpcc.Item;
import com.hotent.tpcc.service.tpcc.ItemService;
/**
 * 对象功能:order_line 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/orderLine/")
public class OrderLineController extends BaseController
{
	@Resource
	private OrderLineService orderLineService;
	@Resource
	private DistrictService districtService;
	@Resource
	private ItemService itemService;
	@Resource
	private StockService stockService;
	@Resource
	private OrdersService ordersService;
	@Resource
	private NewOrdersService newOrdersService;
	static int a =1;
	/**
	 * 添加或更新order_line。
	 * @param request
	 * @param response
	 * @param orderLine 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新order_line")
	public void save(HttpServletRequest request, HttpServletResponse response,OrderLine orderLine) throws Exception
	{
		String resultMsg=null;		
		try{
			if(orderLine.getOl_o_id()==null){
				Long ol_o_id=UniqueIdUtil.genId();
				orderLine.setOl_o_id(ol_o_id);
				orderLineService.add(orderLine);
				resultMsg=getText("添加","order_line");
			}else{
			    orderLineService.update(orderLine);
				resultMsg=getText("更新","order_line");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得order_line分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看order_line分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<OrderLine> list=orderLineService.getAll(new QueryFilter(request,"orderLineItem"));
		ModelAndView mv=this.getAutoView().addObject("orderLineList",list);
		
		return mv;
	}
	
	/**
	 * 删除order_line
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除order_line")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			orderLineService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除order_line成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑order_line
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑order_line")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long ol_o_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		OrderLine orderLine=orderLineService.getById(ol_o_id);
		
		return getAutoView().addObject("orderLine",orderLine)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得order_line明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看order_line明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long ol_o_id=RequestUtil.getLong(request,"id");
		OrderLine orderLine=orderLineService.getById(ol_o_id);
		return getAutoView().addObject("orderLine", orderLine);
	}
	
	
	
	/**
	 * 跳入orderlineOrderline.jsp
	 * @param request
	 * @param response
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping("mingxi")
	@Action(description="生成订单明细")
	@ResponseBody
	public String mingxi(HttpServletRequest request) throws Exception{
		String I_ID = RequestUtil.getString(request, "i_id");
		String S_W_ID = RequestUtil.getString(request, "s_w_id");
		Long d_next_o_id = RequestUtil.getLong(request, "d_next_o_id");
		Long amount = RequestUtil.getLong(request, "amount");
		Long c_d_id = RequestUtil.getLong(request, "c_d_id");
		Long c_w_id = RequestUtil.getLong(request, "c_w_id");
		Float w_tax = RequestUtil.getFloat(request, "w_tax");
		Float d_tax = RequestUtil.getFloat(request, "d_tax");
		JSONObject jobject = new JSONObject();
		Item item = itemService.queryItem(I_ID);
	    Stock stock1 =stockService.queryStock(I_ID,S_W_ID);
	    Long S_QUANTITY= stock1.getS_quantity(); 
	    Stock stock2 =stockService.updateStock(I_ID,S_W_ID,S_QUANTITY,amount);
	    
	    OrderLine orderLine = new OrderLine();
	    orderLine.setOl_o_id(d_next_o_id);
	    orderLine.setOl_d_id(c_d_id);
	    orderLine.setOl_w_id(c_w_id);
	    orderLine.setOl_number(Long.valueOf(a++));
	    orderLine.setOl_i_id(Long.valueOf(I_ID));
		orderLine.setOl_supply_w_id(Long.valueOf(S_W_ID));
		orderLine.setOl_quantity(Long.valueOf(S_QUANTITY));
		
		Double W_TAX = Double.parseDouble(String.valueOf(w_tax));
		Double D_TAX = Double.parseDouble(String.valueOf(d_tax));
		Long S_QUANTITY2= amount;
		Long I_PRICE=item.getI_price();
		double OL_AMOUNT = (1+W_TAX+D_TAX)*S_QUANTITY2*I_PRICE;
		orderLine.setOl_amount(OL_AMOUNT);
		
		String OL_DIST_INFO =stock1.getS_dist_01()+stock1.getS_dist_02();
		orderLine.setOl_dist_info(OL_DIST_INFO);
		OrderLine orderLine1 =orderLineService.insertOrderline(orderLine);

		jobject.accumulate("item", item);
		jobject.accumulate("orderLine", orderLine);
		return jobject.toString();
//		return "{\"name\":\"Michael Jordan\",\"age\":\"51\"}";
		
	}
	
	
	/**
	 * 跳入orderlineOrderline.jsp
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("order")
	@Action(description="生成订单")
	public ModelAndView order(HttpServletRequest request) throws Exception{
		String  D_NEXT_O_ID= request.getParameter("d_next_o_id");
		String  C_D_ID= request.getParameter("c_d_id");
		String  C_W_ID= request.getParameter("c_w_id");
		String  C_ID= request.getParameter("c_id");
		Orders orders = ordersService.makeorders(D_NEXT_O_ID,C_D_ID,C_W_ID,C_ID);
		NewOrders newOrders= newOrdersService.makeneworders(D_NEXT_O_ID,C_D_ID,C_W_ID);
		a=1;
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	
	@RequestMapping("doExecute")
	@ResponseBody
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String json = RequestUtil.getString(request, "json");
		JSONObject jobject = new JSONObject();
				JSONArray jarray = JSONArray.fromObject(json);
				jobject.accumulate("result", ResultMessage.Success);
		return jobject.toString();
	}
	


}

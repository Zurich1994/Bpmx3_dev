
package com.hotent.tpcc.controller.tpcc;

import java.text.SimpleDateFormat;
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

import com.hotent.tpcc.model.tpcc.Orders;
import com.hotent.tpcc.service.tpcc.OrdersService;
import com.hotent.warehouse.service.warehouse.WarehouseService;
import com.hotent.whistory.service.whistory.WHistoryService;
import com.hotent.core.web.ResultMessage;
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.service.tpcc.CustomerService;
import com.hotent.tpcc.service.tpcc.DistrictService;
import com.hotent.tpcc.service.tpcc.ItemService;
/**
 * 对象功能:orders 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/orders/")
public class OrdersController extends BaseController
{
	private static final Object customlist1 = null;
	@Resource
	private OrdersService ordersService;
	@Resource
	private ItemService itemService;
	@Resource
	private CustomerService customerService; 
	@Resource
	private WarehouseService warehouseService;
	@Resource
	private DistrictService districtService;
	@Resource
	private WHistoryService whistoryService;
	private Customer customer;

		
	/**
	 * 添加或更新orders。
	 * @param request
	 * @param response
	 * @param orders 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新orders")
	public void save(HttpServletRequest request, HttpServletResponse response,Orders orders) throws Exception
	{
		String resultMsg=null;		
		try{
			if(orders.getO_id()==null){
				Long o_id=UniqueIdUtil.genId();
				orders.setO_id(o_id);
				ordersService.add(orders);
				resultMsg=getText("添加","orders");
			}else{
			    ordersService.update(orders);
				resultMsg=getText("更新","orders");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得orders分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看orders分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Orders> list=ordersService.getAll(new QueryFilter(request,"ordersItem"));
		ModelAndView mv=this.getAutoView().addObject("ordersList",list);
		
		return mv;
	}
	
	/**
	 * 删除orders
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除orders")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			ordersService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除orders成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑orders
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑orders")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long o_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Orders orders=ordersService.getById(o_id);
		
		return getAutoView().addObject("orders",orders)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得orders明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看orders明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long o_id=RequestUtil.getLong(request,"id");
		Orders orders=ordersService.getById(o_id);
		return getAutoView().addObject("orders", orders);
	}
	/**
	 * 察看订单查询页面
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("search")
	@Action(description="查看订单查询页面")
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.getAutoView();
		
		return mv;
	}
	/**
	 * 察看订单查询页面2
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("search1")
	@Action(description="查看订单查询页面2")
	public ModelAndView search1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		request.setCharacterEncoding("utf-8");
		String customStoreNumber = request.getParameter("customStoreNumber");
		String customAreaNumber = request.getParameter("customAreaNumber");
		String style = RequestUtil.getString(request, "style");
		String customerNumber=null;
		List<Customer> customerList=new ArrayList<Customer>();
		Customer customer1=null;
		//客户号查询
		if(style.equals("number"))
		{
			customerNumber = request.getParameter("id");
			String W_ID = request.getParameter("customStoreNumber");
			String D_ID = request.getParameter("customAreaNumber");
			String C_ID = customerNumber;
		 customerList= customerService.getcustomerMsg2(W_ID,D_ID,C_ID);
		 if(customerList.size()!=0)
			 customer1 =  customerList.get(0);
		}
		
		//客户名称查询
		else
		{
			String customName = new String(request.getParameter("id"));
//			String customName = RequestUtil.getString(request,"id");
			String W_ID = request.getParameter("customStoreNumber");
			String D_ID = request.getParameter("customAreaNumber");
			String C_ID = customName;
		    int count = customerService.count(W_ID,D_ID,C_ID);
			customerList  = customerService.get(W_ID,D_ID,C_ID);
			if(customerList.size()!=0)
				 customer1 =  customerList.get(0);
		}
		Customer customer2=new Customer();
		customer2.setC_w_id(RequestUtil.getLong(request, "customStoreNumber"));
		customer2.setC_d_id(RequestUtil.getLong(request, "customAreaNumber"));
		ModelAndView mv=this.getAutoView().addObject("customerList",customer1 )
										  .addObject("customer2",customer2 );
		return mv;
	}
	/**
	 * 查询可用最近订单页面
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchorder")
	@Action(description="查询最近订单")
	public ModelAndView searchorder(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String D_ID = request.getParameter("customAreaNumber");
		String W_ID = request.getParameter("customStoreNumber");
		String C_ID = request.getParameter("customNumber");
		Orders orders1= ordersService.searchorder(D_ID,W_ID,C_ID);
		SimpleDateFormat simDate=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=orders1.getO_entry_d();
		String o_entry_d=simDate.format(date);
		Customer customer3=new Customer();
		customer3.setC_w_id(RequestUtil.getLong(request, "customStoreNumber"));
		customer3.setC_d_id(RequestUtil.getLong(request, "customAreaNumber"));
		ModelAndView mv=this.getAutoView().addObject("orders1", orders1)
										  .addObject("o_entry_d", o_entry_d)
										  .addObject("customer3", customer3); 
		return mv;
	}
	/**
	 * 查询订单明细页面
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cxddmx")
	@Action(description="查询订单明细")
	public ModelAndView cxddmx(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String D_ID = request.getParameter("customAreaNumber");
		String W_ID = request.getParameter("customStoreNumber");
		String O_ID = request.getParameter("o_id");
		Orders orders2= ordersService.cxddmx(D_ID,W_ID,O_ID);
		
		ModelAndView mv=this.getAutoView().addObject("orders2", orders2);	
											 
		return mv;
	}
//     订单查询
	
	
	/**
	 * 察看订单查询页面
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chaxun")
	@Action(description="查看最小订单查询页面")
	public ModelAndView chaxun(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.getAutoView();
		
		return mv;
	}
	/**
	 * 查询可用最近订单页面
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chaxunmin")
	@Action(description="查询最小订单号")
	public ModelAndView chaxunmin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String D_ID = request.getParameter("d_id");
		String W_ID = request.getParameter("w_id");
		
		Orders orders3= ordersService.chaxunmin(D_ID,W_ID);
		
		ModelAndView mv=this.getAutoView().addObject("orders3", orders3); 
		return mv;
	}
	@RequestMapping("chaxunzuijin")
	@Action(description="查看最小订单查询页面")
	public ModelAndView chaxunzuijin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Orders orders4= ordersService.chaxunzuijin();
		ModelAndView mv=this.getAutoView().addObject("orders4", orders4); 
		
		return mv;
	}
	@RequestMapping("cxkhh")
	@Action(description="查询客户号")
	public ModelAndView cxkhh(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		
		
		Long o_id=RequestUtil.getLong(request,"id");
		Orders orders=ordersService.getById(o_id);
		return getAutoView().addObject("orders", orders);
//		Long o_id=RequestUtil.getLong(request,"id");
//		System.out.println("kehuhaowei"+o_id);
//		Orders orders5= ordersService.cxkhh();		
//		ModelAndView mv=this.getAutoView().addObject("orders5", orders5);	
											 

	}
}

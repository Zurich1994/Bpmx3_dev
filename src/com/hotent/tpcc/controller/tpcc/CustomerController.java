
package com.hotent.tpcc.controller.tpcc;

import java.util.ArrayList;
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

import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.service.tpcc.CustomerService;
import com.hotent.core.web.ResultMessage;
import com.hotent.tpcc.model.tpcc.District;
import com.hotent.tpcc.service.tpcc.DistrictService;
import com.hotent.tpcc.model.tpcc.OrderLine;
/**
 * 对象功能:customer 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/customer/")
public class CustomerController extends BaseController
{
	@Resource
	private CustomerService customerService;
	@Resource
	protected DistrictService districtService;
	/**
	 * 添加或更新customer。
	 * @param request
	 * @param response
	 * @param customer 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新customer")
	public void save(HttpServletRequest request, HttpServletResponse response,Customer customer) throws Exception
	{
		String resultMsg=null;		
		try{
			if(customer.getC_id()==null){
				Long c_id=UniqueIdUtil.genId();
				customer.setC_id(c_id);
				customerService.add(customer);
				resultMsg=getText("添加","customer");
			}else{
			    customerService.update(customer);
				resultMsg=getText("更新","customer");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得customer分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	 
	@RequestMapping("list")
	@Action(description="查看客户特定信息")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Customer cust = new Customer();
		District district = new District();
		String W_ID = request.getParameter("Q_c_w_id_L");
		String D_ID = request.getParameter("Q_c_d_id_L");
		String C_ID = request.getParameter("Q_c_id_L");
		List<Customer> customerList = customerService.getcustomerMsg(W_ID,D_ID,C_ID);
		List<District> districtList = districtService.getorderMsg(W_ID,D_ID);
		if(customerList.size()!=0)
		    cust = customerList.get(0);
		if(districtList.size()!=0)
		    district = districtList.get(0);
		ModelAndView mv=this.getAutoView().addObject("customer",cust)
											.addObject("district",district);
		return mv;
	}
	
	
	/**
	 * 跳入customerlist1
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
 
	@RequestMapping("query")
	@Action(description="查询客户信息")
	public ModelAndView query(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	/**
	 * 跳入customerlist1
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("query2")
	@Action(description="查看客户特定信息")
	public ModelAndView query1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Customer cust = new Customer();
		District district = new District();
		String W_ID = request.getParameter("c_w_id");
		String D_ID = request.getParameter("c_d_id");
		String C_ID = request.getParameter("c_id");
		List<Customer> customerList = customerService.getcustomerMsg(W_ID,D_ID,C_ID);
		List<District> districtList = districtService.getorderMsg(W_ID,D_ID);
		if(customerList.size()!=0)
		    cust = customerList.get(0);
		if(districtList.size()!=0)
		    district = districtList.get(0);
		ModelAndView mv=this.getAutoView().addObject("customer",cust)
										  .addObject("district",district);
		return mv;
	}
	
	/**
	 * 跳入orderlineOrderline.jsp
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderno")
	@Action(description="更新订单号")
	public ModelAndView xiadan(HttpServletRequest request) throws Exception{
		String W_ID = request.getParameter("c_w_id");
		String D_ID = request.getParameter("c_d_id");
		District district1= districtService.orderno(W_ID,D_ID);
	    Customer customer =new Customer();
	    customer.setC_id(RequestUtil.getLong(request, "c_id"));
	    customer.setC_d_id(RequestUtil.getLong(request, "c_d_id"));
	    customer.setC_w_id(RequestUtil.getLong(request, "c_w_id"));
		customer.setC_last(request.getParameter("c_last"));
		customer.setC_credit(request.getParameter("c_credit"));
		customer.setC_discount((double)RequestUtil.getFloat(request, "c_discount"));
		customer.setW_tax((double)RequestUtil.getFloat(request,"w_tax"));
		District district2 =new District();
		district2.setD_tax((double)RequestUtil.getFloat(request,"d_tax"));
		district2.setD_next_o_id(RequestUtil.getLong(request,"d_next_o_id"));
		ModelAndView mv=this.getAutoView().addObject("customer", customer)
										  .addObject("district2", district2);
		return mv;
	}
	
	@RequestMapping("whp")
	@ResponseBody
	public String whp(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String json = RequestUtil.getString(request, "json");
		JSONObject jobject = new JSONObject();
		if(json == null)
			jobject.accumulate("null", "null");
		else
			jobject.accumulate("id", json);
		String resultString = jobject.toString();
		
		return resultString;
	}
	
	/**
	 * 删除customer
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除customer")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			customerService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除customer成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑customer
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑customer")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long c_id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		Customer customer=customerService.getById(c_id);
		return getAutoView().addObject("customer",customer)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得customer明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看customer明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long c_id=RequestUtil.getLong(request,"id");
		Customer customer=customerService.getById(c_id);
		return getAutoView().addObject("customer", customer);
	}
	
	
}

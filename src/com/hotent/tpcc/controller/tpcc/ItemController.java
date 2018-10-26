package com.hotent.tpcc.controller.tpcc;

import java.util.Date;
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

import com.hotent.tpcc.model.tpcc.History2;
import com.hotent.tpcc.service.tpcc.History2Service;
import com.hotent.tpcc.model.tpcc.Item;
import com.hotent.tpcc.service.tpcc.ItemService;
import com.hotent.warehouse.model.warehouse.Warehouse;
import com.hotent.warehouse.service.warehouse.WarehouseService;
import com.hotent.core.web.ResultMessage;
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.service.tpcc.CustomerService;
import com.hotent.tpcc.model.tpcc.District;
import com.hotent.tpcc.service.tpcc.DistrictService;

/**
 * 对象功能:item 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/item/")
public class ItemController extends BaseController {
	@Resource
	private ItemService itemService;
	@Resource
	private CustomerService customerService;
	@Resource
	private WarehouseService warehouseService;
	@Resource
	private DistrictService districtService;
	@Resource
	private History2Service history2Service;

	/**
	 * 添加或更新item。
	 * 
	 * @param request
	 * @param response
	 * @param item
	 *            添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新item")
	public void save(HttpServletRequest request, HttpServletResponse response,
			Item item) throws Exception {
		String resultMsg = null;
		try {
			if (item.getI_id() == null) {
				Long i_id = UniqueIdUtil.genId();
				item.setI_id(i_id);
				itemService.add(item);
				resultMsg = getText("添加", "item");
			} else {
				itemService.update(item);
				resultMsg = getText("更新", "item");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + ","
					+ e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得item分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看item分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Item> list = itemService.getAll(new QueryFilter(request,
				"itemItem"));
		ModelAndView mv = this.getAutoView().addObject("itemList", list);

		return mv;
	}

	/**
	 * 删除item
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除item")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			itemService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除item成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑item
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑item")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long i_id = RequestUtil.getLong(request, "id");

		String returnUrl = RequestUtil.getPrePage(request);
		Item item = itemService.getById(i_id);

		return getAutoView().addObject("item", item).addObject("returnUrl",
				returnUrl);
	}

	/**
	 * 取得item明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看item明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long i_id = RequestUtil.getLong(request, "id");
		Item item = itemService.getById(i_id);
		return getAutoView().addObject("item", item);
	}

	/**
	 * 察看支付页面
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("zf")
	@Action(description = "查看支付页面")
	public ModelAndView zf(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();

		return mv;
	}

	/**
	 * 察看支付页面2
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("zfb")
	@Action(description = "查看支付页面2")
	public ModelAndView zfb(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Long customStoreNumber = RequestUtil.getLong(request,
				"customStoreNumber");
		Long customAreaNumber = RequestUtil
				.getLong(request, "customAreaNumber");
		String style = RequestUtil.getString(request, "style");
		List<Customer> customlist = null;
		Long customerNumber = null;
		// 客户号查询
		if (style.equals("number")) {
			customerNumber = RequestUtil.getLong(request, "id");

		}
		// 客户名称查询
		else {
			String customName = new String(request.getParameter("id").getBytes(
					"ISO-8859-1"), "UTF-8");
			// String customName = RequestUtil.getString(request,"id");
			int count = customerService.countByIdStoreIdAndAreaIdAndName(
					customStoreNumber, customAreaNumber, customName);
			customlist = customerService.getIdbyStoreIdAndAreaIdAndName(
					customStoreNumber, customAreaNumber, customName);
			if (count % 2 == 1)
				count++;
			customerNumber = customlist.get(count / 2 - 1).getC_id();
		}
//		ModelAndView mView = new ModelAndView("/item/item/itemZfb.jsp");
		ModelAndView mv = this.getAutoView().addObject("customerNumber",
				customerNumber).addObject("customerStore", customStoreNumber)
				.addObject("customerArea", customAreaNumber);
		return mv;
	}

	/**
	 * 操作支付
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("zfc")
	@Action(description = "操作支付")
	public ModelAndView zfc(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long stockStoreNumber = RequestUtil
				.getLong(request, "stockStoreNumber");
		Long stockAreaNumber = RequestUtil.getLong(request, "stockAreaNumber");
		Long customNumber = RequestUtil.getLong(request, "customNumber");
		Long customStoreNumber = RequestUtil.getLong(request,
				"customStoreNumber");
		Long customAreaNumber = RequestUtil
				.getLong(request, "customAreaNumber");
		Long orderValue = RequestUtil.getLong(request, "orderValue");
		String data = null;
		String temp=null;
		
			warehouseService.updateWare(orderValue, stockStoreNumber);
			Warehouse warehouse = warehouseService.getById(stockStoreNumber);
			districtService.updateDis(stockStoreNumber, stockAreaNumber,
					orderValue);
			District district = districtService.getByStoreIdAndAreaId(
					stockAreaNumber, stockStoreNumber);
			Customer customer = customerService.getByStoreIdAndAreaIdAndId(
					customStoreNumber, customAreaNumber, customNumber);
			if (customer.getC_credit().equals("BC")) {
				data = customerService.getDataByStoreIdAndAreaIdAndId(
						customStoreNumber, customAreaNumber, customNumber);
				customerService.updateBalanceAndDataByStoreIdAndAreaIdAndId(
						data, orderValue, customStoreNumber, customAreaNumber,
						customNumber);
			} else {
				customerService.updateBalanceByStoreIdAndAreaIdAndId(
						orderValue, customStoreNumber, customAreaNumber,
						customNumber);
			}
			/*WHistory history = new WHistory(customNumber, customAreaNumber,
					customStoreNumber, stockAreaNumber, stockStoreNumber,
					new Date(), orderValue, "asdf");*/
			History2 history=new History2();
			Date tempDate=new Date();
			history.setH_date(tempDate);
			history.setH_w_id(stockStoreNumber);
			history.setH_c_d_id(customAreaNumber);
			history.setH_c_id(customNumber);
			history.setH_c_w_id(customStoreNumber);
			history.setH_data("hello");
			history.setH_d_id(stockAreaNumber);
			history.setH_amount(orderValue);
			history2Service.addHistory(history);
			//history2Service.add(history);

			ResultMessage message = new ResultMessage(ResultMessage.Success,
					"支付成功!");
			addMessage(message, request);
			//String url = RequestUtil.getPrePage(request);
			ModelAndView mvAndView = new ModelAndView("/tpcc/tpcc/itemZf.jsp");
			return mvAndView;
//			response.sendRedirect(request.getContextPath()
//					+ "/item/item/itemZf.ht");
		
	}

}

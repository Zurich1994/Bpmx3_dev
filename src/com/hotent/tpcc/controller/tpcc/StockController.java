
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

import com.hotent.tpcc.model.tpcc.Stock;
import com.hotent.tpcc.service.tpcc.StockService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:stock 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/stock/")
public class StockController extends BaseController
{
	@Resource
	private StockService stockService;
	/**
	 * 添加或更新stock。
	 * @param request
	 * @param response
	 * @param stock 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新stock")
	public void save(HttpServletRequest request, HttpServletResponse response,Stock stock) throws Exception
	{
		String resultMsg=null;		
		try{
			if(stock.getS_i_id()==null){
				Long s_i_id=UniqueIdUtil.genId();
				stock.setS_i_id(s_i_id);
				stockService.add(stock);
				resultMsg=getText("添加","stock");
			}else{
			    stockService.update(stock);
				resultMsg=getText("更新","stock");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得stock分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看stock分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Stock> list=stockService.getAll(new QueryFilter(request,"stockItem"));
		ModelAndView mv=this.getAutoView().addObject("stockList",list);
		
		return mv;
	}
	
	/**
	 * 删除stock
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除stock")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			stockService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除stock成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑stock
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑stock")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long s_i_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Stock stock=stockService.getById(s_i_id);
		
		return getAutoView().addObject("stock",stock)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得stock明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看stock明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long s_i_id=RequestUtil.getLong(request,"id");
		Stock stock=stockService.getById(s_i_id);
		return getAutoView().addObject("stock", stock);
	}
	
}

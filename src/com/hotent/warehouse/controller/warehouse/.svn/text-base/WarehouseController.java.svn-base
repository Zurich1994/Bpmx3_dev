
package com.hotent.warehouse.controller.warehouse;

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

import com.hotent.warehouse.model.warehouse.Warehouse;
import com.hotent.warehouse.service.warehouse.WarehouseService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:warehouse 控制器类
 */
@Controller
@RequestMapping("/warehouse/warehouse/warehouse/")
public class WarehouseController extends BaseController
{
	@Resource
	private WarehouseService warehouseService;
	/**
	 * 添加或更新warehouse。
	 * @param request
	 * @param response
	 * @param warehouse 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新warehouse")
	public void save(HttpServletRequest request, HttpServletResponse response,Warehouse warehouse) throws Exception
	{
		String resultMsg=null;		
		try{
			if(warehouse.getW_id()==null){
				Long w_id=UniqueIdUtil.genId();
				warehouse.setW_id(w_id);
				warehouseService.add(warehouse);
				resultMsg=getText("添加","warehouse");
			}else{
			    warehouseService.update(warehouse);
				resultMsg=getText("更新","warehouse");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得warehouse分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看warehouse分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Warehouse> list=warehouseService.getAll(new QueryFilter(request,"warehouseItem"));
		ModelAndView mv=this.getAutoView().addObject("warehouseList",list);
		
		return mv;
	}
	
	/**
	 * 删除warehouse
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除warehouse")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			warehouseService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除warehouse成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑warehouse
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑warehouse")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long w_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Warehouse warehouse=warehouseService.getById(w_id);
		
		return getAutoView().addObject("warehouse",warehouse)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得warehouse明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看warehouse明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long w_id=RequestUtil.getLong(request,"id");
		Warehouse warehouse=warehouseService.getById(w_id);
		return getAutoView().addObject("warehouse", warehouse);
	}
	
}

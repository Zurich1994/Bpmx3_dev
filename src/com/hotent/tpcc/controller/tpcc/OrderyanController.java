
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

import com.hotent.tpcc.model.tpcc.Orderyan;
import com.hotent.tpcc.service.tpcc.OrderyanService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:order 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/orderyan/")
public class OrderyanController extends BaseController
{
	@Resource
	private OrderyanService orderyanService;
	/**
	 * 添加或更新order。
	 * @param request
	 * @param response
	 * @param orderyan 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新order")
	public void save(HttpServletRequest request, HttpServletResponse response,Orderyan orderyan) throws Exception
	{
		String resultMsg=null;		
		try{
			if(orderyan.getId()==null){
				Long id=UniqueIdUtil.genId();
				orderyan.setId(id);
				orderyanService.add(orderyan);
				resultMsg=getText("添加","order");
			}else{
			    orderyanService.update(orderyan);
				resultMsg=getText("更新","order");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得order分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看order分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Orderyan> list=orderyanService.getAll(new QueryFilter(request,"orderyanItem"));
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getO_all_local().equals("1"))
			{
				list.get(i).setO_all_local("是");
			}else
			{
				list.get(i).setO_all_local("否");
			}
		}
		ModelAndView mv=this.getAutoView().addObject("orderyanList",list);
		
		return mv;
	}
	 
	/**
	 * 删除order
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除order")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			orderyanService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除order成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑order
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑order")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Orderyan orderyan=orderyanService.getById(id);
		
		return getAutoView().addObject("orderyan",orderyan)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得order明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看order明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Orderyan orderyan=orderyanService.getById(id);
		return getAutoView().addObject("orderyan", orderyan);
	}
	
}

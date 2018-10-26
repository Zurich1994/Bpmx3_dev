
package com.hotent.support.controller.catelog;

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

import com.hotent.support.model.catelog.ProductOs;
import com.hotent.support.service.catelog.ProductOsService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:Support操作系统 控制器类
 */
@Controller
@RequestMapping("/support/catelog/productOs/")
public class ProductOsController extends BaseController
{
	@Resource
	private ProductOsService productOsService;
	/**
	 * 添加或更新Support操作系统。
	 * @param request
	 * @param response
	 * @param productOs 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新Support操作系统")
	public void save(HttpServletRequest request, HttpServletResponse response,ProductOs productOs) throws Exception
	{
		String resultMsg=null;		
		try{
			if(productOs.getId()==null){
				Long id=UniqueIdUtil.genId();
				productOs.setId(id);
				productOsService.add(productOs);
				resultMsg=getText("添加","Support操作系统");
			}else{
			    productOsService.update(productOs);
				resultMsg=getText("更新","Support操作系统");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得Support操作系统分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看Support操作系统分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ProductOs> list=productOsService.getAll(new QueryFilter(request,"productOsItem"));
		ModelAndView mv=this.getAutoView().addObject("productOsList",list);
		
		return mv;
	}
	
	/**
	 * 删除Support操作系统
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除Support操作系统")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			productOsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除Support操作系统成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑Support操作系统
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑Support操作系统")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ProductOs productOs=productOsService.getById(id);
		
		return getAutoView().addObject("productOs",productOs)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得Support操作系统明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看Support操作系统明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ProductOs productOs=productOsService.getById(id);
		return getAutoView().addObject("productOs", productOs);
	}
	
}

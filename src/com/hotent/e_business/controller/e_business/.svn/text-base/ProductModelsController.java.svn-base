
package com.hotent.e_business.controller.e_business;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.hotent.e_business.model.e_business.ProductModels;
import com.hotent.e_business.service.e_business.ProductModelsService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:电子商务搜索表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/productModels/")
public class ProductModelsController extends BaseController
{
	@Resource
	private ProductModelsService productModelsService;
	/**
	 * 添加或更新电子商务搜索表。
	 * @param request
	 * @param response
	 * @param productModels 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新电子商务搜索表")
	public void save(HttpServletRequest request, HttpServletResponse response,ProductModels productModels) throws Exception
	{
		String resultMsg=null;		
		try{
			if(productModels.getId()==null){
				Long id=UniqueIdUtil.genId();
				productModels.setId(id);
				productModelsService.add(productModels);
				resultMsg=getText("添加","电子商务搜索表");
			}else{
			    productModelsService.update(productModels);
				resultMsg=getText("更新","电子商务搜索表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	@RequestMapping("search")
	@Action(description="查询客户类型简称")
	@ResponseBody
	public String search(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	System.out.print("1111111111111");
		ProductModels cust=new ProductModels();
		String Q_REGION_S = request.getParameter("Q_REGION_S");
		String Q_KEYWORDS_S = request.getParameter("Q_KEYWORDS_S");
		String Q_CATEGORY_S = request.getParameter("Q_CATEGORY_S");
		List<ProductModels> productList = productModelsService.getProductModels(Q_REGION_S,Q_KEYWORDS_S,Q_CATEGORY_S);
		System.out.print("1111111111111");
		if(productList.size()!=0)
	    cust = productList.get(0);
	    String type=cust.getCUSTOMER_TYPE_SHORT();
	    System.out.print(type);
		return type;
	}
	/**
	 * 取得电子商务搜索表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看电子商务搜索表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ProductModels> list=productModelsService.getAll(new QueryFilter(request,"productModelsItem"));
		ModelAndView mv=this.getAutoView().addObject("productModelsList",list);
		
		return mv;
	}
	
	/**
	 * 删除电子商务搜索表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除电子商务搜索表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			productModelsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除电子商务搜索表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑电子商务搜索表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑电子商务搜索表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ProductModels productModels=productModelsService.getById(id);
		
		return getAutoView().addObject("productModels",productModels)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得电子商务搜索表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看电子商务搜索表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ProductModels productModels=productModelsService.getById(id);
		return getAutoView().addObject("productModels", productModels);
	}
	
}

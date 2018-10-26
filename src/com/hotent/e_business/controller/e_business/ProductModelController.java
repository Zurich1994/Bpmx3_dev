
package com.hotent.e_business.controller.e_business;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.e_business.model.e_business.ProductModel;
import com.hotent.e_business.service.e_business.ProductModelService;
import com.hotent.core.web.ResultMessage;
import com.hotent.e_business.controller.e_business.ProductDetailController;
/**
 * 对象功能:产品模型表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/productModel/")

public class ProductModelController extends BaseController
{
	@Resource
	private ProductModelService productModelService;
	/**
	 * 添加或更新产品模型表。
	 * @param request
	 * @param response
	 * @param productModel 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新产品模型表")
	public void save(HttpServletRequest request, HttpServletResponse response,ProductModel productModel) throws Exception
	{
		String resultMsg=null;		
		try{
			if(productModel.getId()==null){
				Long id=UniqueIdUtil.genId();
				productModel.setId(id);
				productModelService.add(productModel);
				resultMsg=getText("添加","产品模型表");
			}else{
			    productModelService.update(productModel);
				resultMsg=getText("更新","产品模型表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得产品模型表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看产品模型表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ProductModel> list=productModelService.getAll(new QueryFilter(request,"productModelItem"));
		ModelAndView mv=this.getAutoView().addObject("productModelList",list);
		
//		String PRODUCTID=request.getParameter("productid=");
//		System.out.println("cartproductid"+PRODUCTID);
		
		String EMAIL=request.getParameter("EMAIL");
		System.out.println("URL传到cart"+EMAIL);
		
		
		return mv;
	}
	
	/**
	 * 删除产品模型表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除产品模型表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			productModelService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除产品模型表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑产品模型表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑产品模型表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		ProductModel productModel=productModelService.getById(id);
		
		return getAutoView().addObject("productModel",productModel)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得产品模型表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看产品模型表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ProductModel productModel=productModelService.getById(id);
		return getAutoView().addObject("productModel", productModel);
	}
	
	
}

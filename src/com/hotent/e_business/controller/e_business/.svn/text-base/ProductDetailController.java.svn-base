
package com.hotent.e_business.controller.e_business;

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

import com.hotent.e_business.model.e_business.Cart;
import com.hotent.e_business.model.e_business.ProductDetail;
import com.hotent.e_business.service.e_business.ProductDetailService;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.service.system.IdentityService;
/**
 * 对象功能:商品详细表 控制器类
 */
@Controller
@RequestMapping("/e_business/e_business/productDetail/")
public class ProductDetailController extends BaseController
{
	@Resource
	private ProductDetailService productDetailService;
	@Resource
	private IdentityService identityService;
	/**
	 * 添加或更新商品详细表。
	 * @param request
	 * @param response
	 * @param productDetail 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新商品详细表")
	public void save(HttpServletRequest request, HttpServletResponse response,ProductDetail productDetail) throws Exception
	{
		String resultMsg=null;		
		try{
			if(productDetail.getId()==null){
				Long id=UniqueIdUtil.genId();
				productDetail.setId(id);
				productDetailService.add(productDetail);
				resultMsg=getText("添加","商品详细表");
			}else{
			    productDetailService.update(productDetail);
				resultMsg=getText("更新","商品详细表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得商品详细表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看商品详细表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ProductDetail> list=productDetailService.getAll(new QueryFilter(request,"productDetailItem"));
		ModelAndView mv=this.getAutoView().addObject("productDetailList",list);
		
		return mv;
	}
	
	/**
	 * 删除商品详细表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除商品详细表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			productDetailService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除商品详细表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑商品详细表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑商品详细表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
	    String returnUrl=RequestUtil.getPrePage(request);
		ProductDetail productDetail=productDetailService.getById(id);
		if(BeanUtils.isEmpty(productDetail)){
			productDetail=new ProductDetail();
			String PRODUCTID_id=identityService.nextId("jdbh");
			productDetail.setPRODUCTID(PRODUCTID_id);
		}
		
		return getAutoView().addObject("productDetail",productDetail)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得商品详细表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看商品详细表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ProductDetail productDetail=productDetailService.getById(id);
		System.out.println("id="+id);
		return getAutoView().addObject("productDetail", productDetail);
	}
	
	
	@RequestMapping("edits")
	@Action(description="编辑用户信息表")
	public ModelAndView edits(HttpServletRequest request) throws Exception
	{	
		String returnUrl=RequestUtil.getPrePage(request);
		
		return getAutoView().addObject("returnUrl",returnUrl);
	}
}

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

import com.hotent.support.model.catelog.Category;
import com.hotent.support.service.catelog.CategoryService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:Support类别表 控制器类
 */
@Controller
@RequestMapping("/support/catelog/category/")
public class CategoryController extends BaseController
{
	@Resource
	private CategoryService categoryService;
	/**
	 * 添加或更新Support类别表。
	 * @param request
	 * @param response
	 * @param category 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新Support类别表")
	public void save(HttpServletRequest request, HttpServletResponse response,Category category) throws Exception
	{
		String resultMsg=null;		
		try{
			if(category.getId()==null){
				Long id=UniqueIdUtil.genId();
				category.setId(id);
				categoryService.add(category);
				resultMsg=getText("添加","Support类别表");
			}else{
			    categoryService.update(category);
				resultMsg=getText("更新","Support类别表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得Support类别表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看Support类别表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	//String aa = RequestUtil.getString(request, "aa");
		List<Category> list=categoryService.getAll(new QueryFilter(request,"categoryItem"));
		ModelAndView mv=this.getAutoView().addObject("categoryList",list);
		
		return mv;
	}
	
	/**
	 * 删除Support类别表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除Support类别表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			categoryService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除Support类别表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑Support类别表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑Support类别表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Category category=categoryService.getById(id);
		
		return getAutoView().addObject("category",category)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得Support类别表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看Support类别表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Category category=categoryService.getById(id);
		//查询第二个表		
		return getAutoView().addObject("category", category);
	}
	
}

package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsBaseItem;
import com.hotent.platform.service.ats.AtsBaseItemService;
/**
 *<pre>
 * 对象功能:基础数据 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 18:08:43
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsBaseItem/")
public class AtsBaseItemController extends BaseController
{
	@Resource
	private AtsBaseItemService atsBaseItemService;
	
	
	/**
	 * 添加或更新基础数据。
	 * @param request
	 * @param response
	 * @param atsBaseItem 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新基础数据")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsBaseItem atsBaseItem) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsBaseItem.getId()==null||atsBaseItem.getId()==0){
				resultMsg=getText("添加","基础数据");
			}else{
				resultMsg=getText("更新","基础数据");
			}
		    atsBaseItemService.save(atsBaseItem);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得基础数据分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看基础数据分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsBaseItem> list=atsBaseItemService.getAll(new QueryFilter(request,"atsBaseItemItem"));
		return this.getAutoView().addObject("atsBaseItemList",list);
	}
	
	/**
	 * 删除基础数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除基础数据")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsBaseItemService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除基础数据成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑基础数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑基础数据")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsBaseItem atsBaseItem=atsBaseItemService.getById(id);
		
		return getAutoView().addObject("atsBaseItem",atsBaseItem)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得基础数据明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看基础数据明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsBaseItem atsBaseItem = atsBaseItemService.getById(id);	
		return getAutoView().addObject("atsBaseItem", atsBaseItem);
	}
	
}


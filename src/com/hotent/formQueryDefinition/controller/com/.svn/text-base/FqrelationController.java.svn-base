
package com.hotent.formQueryDefinition.controller.com;

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

import com.hotent.formQueryDefinition.model.com.Fqrelation;
import com.hotent.formQueryDefinition.service.com.FqrelationService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:节点查询关联表 控制器类
 */
@Controller
@RequestMapping("/formQueryDefinition/com/fqrelation/")
public class FqrelationController extends BaseController
{
	@Resource
	private FqrelationService fqrelationService;
	/**
	 * 添加或更新节点查询关联表。
	 * @param request
	 * @param response
	 * @param fqrelation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新节点查询关联表")
	public void save(HttpServletRequest request, HttpServletResponse response,Fqrelation fqrelation) throws Exception
	{
		String resultMsg=null;		
		try{
			if(fqrelation.getId()==null){
				Long id=UniqueIdUtil.genId();
				fqrelation.setId(id);
				fqrelationService.add(fqrelation);
				resultMsg=getText("添加","节点查询关联表");
			}else{
			    fqrelationService.update(fqrelation);
				resultMsg=getText("更新","节点查询关联表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得节点查询关联表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看节点查询关联表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Fqrelation> list=fqrelationService.getAll(new QueryFilter(request,"fqrelationItem"));
		ModelAndView mv=this.getAutoView().addObject("fqrelationList",list);
		
		return mv;
	}
	
	/**
	 * 删除节点查询关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除节点查询关联表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			fqrelationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除节点查询关联表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑节点查询关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑节点查询关联表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Fqrelation fqrelation=(Fqrelation) fqrelationService.getById(id);
		
		return getAutoView().addObject("fqrelation",fqrelation)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得节点查询关联表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看节点查询关联表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Fqrelation fqrelation=(Fqrelation) fqrelationService.getById(id);
		return getAutoView().addObject("fqrelation", fqrelation);
	}
	
}

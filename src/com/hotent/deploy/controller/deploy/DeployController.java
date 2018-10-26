

package com.hotent.deploy.controller.deploy;

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
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.deploy.model.deploy.Deploy;
import com.hotent.deploy.service.deploy.DeployService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:部署表 控制器类
 */
@Controller
@RequestMapping("/deploy/deploy/deploy/")
public class DeployController extends BaseController
{
	@Resource
	private DeployService deployService;
	
	/**
	 * 添加或更新部署表。
	 * @param request
	 * @param response
	 * @param deploy 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新部署表")
	public void save(HttpServletRequest request, HttpServletResponse response,Deploy deploy) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deploy.getId()==null){
				deployService.save(deploy);
				resultMsg=getText("添加","部署表");
			}else{
			    deployService.save(deploy);
				resultMsg=getText("更新","部署表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得部署表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看部署表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Deploy> list=deployService.getAll(new QueryFilter(request,"deployItem"));
		ModelAndView mv=this.getAutoView().addObject("deployList",list);
		return mv;
	}
	
	/**
	 * 删除部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除部署表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deployService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除部署表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑部署表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Deploy deploy=deployService.getById(id);
		
		return getAutoView().addObject("deploy",deploy)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得部署表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看部署表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Deploy deploy=deployService.getById(id);
		return getAutoView().addObject("deploy", deploy);
	}
	
}
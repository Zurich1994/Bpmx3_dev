
package com.hotent.VirtualSoftware.controller.VirtualSoftwarePac;

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

import com.hotent.VirtualSoftware.model.VirtualSoftwarePac.VirtualSoftware;
import com.hotent.VirtualSoftware.service.VirtualSoftwarePac.VirtualSoftwareService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:虚拟机-软件表 控制器类
 */
@Controller
@RequestMapping("/VirtualSoftware/VirtualSoftwarePac/virtualSoftware/")
public class VirtualSoftwareController extends BaseController
{
	@Resource
	private VirtualSoftwareService virtualSoftwareService;
	/**
	 * 添加或更新虚拟机-软件表。
	 * @param request
	 * @param response
	 * @param virtualSoftware 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新虚拟机-软件表")
	public void save(HttpServletRequest request, HttpServletResponse response,VirtualSoftware virtualSoftware) throws Exception
	{
		String resultMsg=null;		
		try{
			if(virtualSoftware.getId()==null){
				Long id=UniqueIdUtil.genId();
				virtualSoftware.setId(id);
				virtualSoftwareService.add(virtualSoftware);
				resultMsg=getText("添加","虚拟机-软件表");
			}else{
			    virtualSoftwareService.update(virtualSoftware);
				resultMsg=getText("更新","虚拟机-软件表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得虚拟机-软件表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看虚拟机-软件表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<VirtualSoftware> list=virtualSoftwareService.getAll(new QueryFilter(request,"virtualSoftwareItem"));
		ModelAndView mv=this.getAutoView().addObject("virtualSoftwareList",list);
		
		return mv;
	}
	
	/**
	 * 删除虚拟机-软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除虚拟机-软件表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			virtualSoftwareService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除虚拟机-软件表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑虚拟机-软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑虚拟机-软件表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		VirtualSoftware virtualSoftware=virtualSoftwareService.getById(id);
		
		return getAutoView().addObject("virtualSoftware",virtualSoftware)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得虚拟机-软件表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看虚拟机-软件表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		VirtualSoftware virtualSoftware=virtualSoftwareService.getById(id);
		return getAutoView().addObject("virtualSoftware", virtualSoftware);
	}
	
}

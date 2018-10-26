

package com.hotent.application.controller.application;

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

import com.hotent.application.model.application.Application;
import com.hotent.application.service.application.ApplicationService;
import com.hotent.core.web.ResultMessage;
import com.hotent.deploy.model.deploy.Deploy;
import com.hotent.deploy.service.deploy.DeployService;
import com.hotent.equipment.model.equipment.Equipment;
import com.hotent.equipment.service.equipment.EquipmentService;
/**
 * 对象功能:应用表 控制器类
 */
@Controller
@RequestMapping("/application/application/application/")
public class ApplicationController extends BaseController
{
	@Resource
	private ApplicationService applicationService;
	@Resource
	private DeployService deployService;
	@Resource
	private EquipmentService equipmentService;
	/**
	 * 添加或更新应用表。
	 * @param request
	 * @param response
	 * @param application 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新应用表")
	public void save(HttpServletRequest request, HttpServletResponse response,Application application) throws Exception
	{
		String resultMsg=null;		
		try{
			if(application.getId()==null){
				applicationService.save(application);
				resultMsg=getText("添加","应用表");
			}else{
			    applicationService.save(application);
				resultMsg=getText("更新","应用表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得应用表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看应用表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Application> list=applicationService.getAll(new QueryFilter(request,"applicationItem"));
		ModelAndView mv=this.getAutoView().addObject("applicationList",list);
		return mv;
	}
	
	@RequestMapping("assemblelist")
	@Action(description="查看应用表分页列表")
	public ModelAndView assemblelist(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Application> list=applicationService.getAll(new QueryFilter(request,"applicationItem"));
		ModelAndView mv=this.getAutoView().addObject("applicationList",list);
		return mv;
	}
	@RequestMapping("equipmentlist")
	@Action(description="查看应用表分页列表")
	public ModelAndView equipmentlist(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Application> list=applicationService.getAll(new QueryFilter(request,"applicationItem"));
		Long equipmentId=RequestUtil.getLong(request,"equipmentId");
		
		String status=RequestUtil.getString(request,"status");
		String typeId=RequestUtil.getString(request,"typeId");
		System.out.println("收到的社贝iid为："+equipmentId);
		if(status.equals("1"))
		{		
			if(typeId.equals("10"))//应用类别树 父节点
			{
				deployService.delByEquipmentId(equipmentId);			
			}else//应用类别树子节点
			{				
				deployService.delByEquipmentIdAndApplicationList(equipmentId,list);										
			}
			Long[]  applicationIds=RequestUtil.getLongAryByStr(request,"applicationId");
			if(applicationIds!=null)
			for(Long applicationId:applicationIds)
				{
					Deploy deploy=new Deploy();
					deploy.setDeviceID(equipmentId.toString());
					deploy.setApplicationID(applicationId);
					deployService.save(deploy);
				}
		}
		List<Deploy>deployList=deployService.getByEqupmentId(equipmentId);
		System.out.println("部署对象个数："+deployList.size());
		for(int j=0;j<list.size();j++)
		{int i;
			for(i=0;i<deployList.size();i++)
			{
				if(list.get(j).getId().toString().equals(deployList.get(i).getApplicationID().toString()))
				{
					list.get(j).setEquipmentStatus("1");
					System.out.println("找到相同");break;
					
				}
			}
			if(i==deployList.size())
				list.get(j).setEquipmentStatus("0");
		}
		for(Application a:list)
		{
			System.out.println(a.getEquipmentStatus());
		}
		Equipment equipment=equipmentService.getById(equipmentId);
		ModelAndView mv=this.getAutoView()
		.addObject("applicationList",list)
		.addObject("equipmentId",equipmentId)
		.addObject("typeId",typeId)
		.addObject("equipmentName",equipment.getDeviceName());
		return mv;
	}
	
	@RequestMapping("assembletree")
	@Action(description = "自定义表管理页面")
	public ModelAndView assembletreetree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();

		return mv;
	}	
	/**
	 * 删除应用表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除应用表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			applicationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除应用表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑应用表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑应用表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Application application=applicationService.getById(id);
		
		return getAutoView().addObject("application",application)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得应用表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看应用表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Application application=applicationService.getById(id);
		return getAutoView().addObject("application", application);
	}
	@RequestMapping("systemTree")
	@Action(description="查看应用表分页列表")
	public ModelAndView systemTree(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long applicationId=RequestUtil.getLong(request,"applicationId");
		System.out.println("子系统树收到的applicationId为："+applicationId);
		ModelAndView mv=this.getAutoView().addObject("applicationId",applicationId);
		return mv;
	}
	
}


package com.hotent.equipment.controller.equipment;

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

import com.hotent.equipment.model.equipment.Equipment;
import com.hotent.equipment.service.equipment.EquipmentService;
import com.hotent.core.web.ResultMessage;

/**
 * 对象功能:设备表 控制器类
 */
@Controller
@RequestMapping("/equipment/equipment/equipment/")
public class EquipmentController extends BaseController
{
	@Resource
	private EquipmentService equipmentService;
	
	/**
	 * 添加或更新设备表。
	 * @param request
	 * @param response
	 * @param equipment 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备表")
	public void save(HttpServletRequest request, HttpServletResponse response,Equipment equipment) throws Exception
	{
		String resultMsg=null;		
		try{
			if(equipment.getId()==null){
				equipmentService.save(equipment);
				resultMsg=getText("添加","设备表");
			}else{
			    equipmentService.save(equipment);
				resultMsg=getText("更新","设备表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Equipment> list=equipmentService.getAll(new QueryFilter(request,"equipmentItem"));
		ModelAndView mv=this.getAutoView().addObject("equipmentList",list);
		return mv;
	}
	
	/**
	 * 删除设备表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			equipmentService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Equipment equipment=equipmentService.getById(id);
		
		return getAutoView().addObject("equipment",equipment)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Equipment equipment=equipmentService.getById(id);
		return getAutoView().addObject("equipment", equipment);
	}
	@RequestMapping("applicationTree")
	@Action(description="查看应用表分页列表")
	public ModelAndView applicationTree(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long equipmentId=RequestUtil.getLong(request,"equipmentId");
		
		
		
		System.out.println("应用树收到的equipmentId为："+equipmentId);
		ModelAndView mv=this.getAutoView().addObject("equipmentId",equipmentId);
		return mv;
	}
}
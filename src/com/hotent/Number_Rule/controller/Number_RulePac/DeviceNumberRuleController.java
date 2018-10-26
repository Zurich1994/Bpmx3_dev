
package com.hotent.Number_Rule.controller.Number_RulePac;

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

import com.hotent.Number_Rule.model.Number_RulePac.DeviceNumberRule;
import com.hotent.Number_Rule.service.Number_RulePac.DeviceNumberRuleService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备编号规则表 控制器类
 */
@Controller
@RequestMapping("/Number_Rule/Number_RulePac/deviceNumberRule/")
public class DeviceNumberRuleController extends BaseController
{
	@Resource
	private DeviceNumberRuleService deviceNumberRuleService;
	/**
	 * 添加或更新设备编号规则表。
	 * @param request
	 * @param response
	 * @param deviceNumberRule 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备编号规则表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceNumberRule deviceNumberRule) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deviceNumberRule.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceNumberRule.setId(id);
				deviceNumberRuleService.add(deviceNumberRule);
				resultMsg=getText("添加","设备编号规则表");
			}else{
			    deviceNumberRuleService.update(deviceNumberRule);
				resultMsg=getText("更新","设备编号规则表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备编号规则表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备编号规则表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceNumberRule> list=deviceNumberRuleService.getAll(new QueryFilter(request,"deviceNumberRuleItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceNumberRuleList",list);
		
		return mv;
	}
	
	/**
	 * 删除设备编号规则表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备编号规则表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceNumberRuleService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备编号规则表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备编号规则表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备编号规则表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceNumberRule deviceNumberRule=deviceNumberRuleService.getById(id);
		
		return getAutoView().addObject("deviceNumberRule",deviceNumberRule)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备编号规则表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备编号规则表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceNumberRule deviceNumberRule=deviceNumberRuleService.getById(id);
		return getAutoView().addObject("deviceNumberRule", deviceNumberRule);
	}
	
}


package com.hotent.DevicerelationshipCode.controller.DevicerelationshipCodePath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.DevicerelationshipCode.model.DevicerelationshipCodePath.Devicerelationship;
import com.hotent.DevicerelationshipCode.service.DevicerelationshipCodePath.DevicerelationshipService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:线路表 控制器类
 */
@Controller
@RequestMapping("/DevicerelationshipCode/DevicerelationshipCodePath/devicerelationship/")
public class DevicerelationshipController extends BaseController
{
	@Resource
	private DevicerelationshipService devicerelationshipService;
	
	
	
	
	
	/**
	 * 	小页面编辑线的信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("smailedit")
	@Action(description="小页面编辑设备基本信息配置")
	public ModelAndView smailedit(HttpServletRequest request) throws Exception
	{
		String f_dev_id = request.getParameter("F_dev_ID");
		String f_opp_id = request.getParameter("F_opp_ID");
		String actDefId = request.getParameter("actDefId");
		//判断该线信息是否已存在
		Devicerelationship devicerelationship=new Devicerelationship();
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select ID from w_devicerelationship where F_dev_ID='"+f_dev_id+"' AND F_opp_ID='"+f_opp_id+"' AND F_actDefId='"+actDefId+"'";		
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){		
			Long id=(Long)list.get(0).get("ID");		
			devicerelationship=devicerelationshipService.getById(id);
		}		
		String returnUrl=RequestUtil.getPrePage(request);
		return getAutoView().addObject("f_dev_id",f_dev_id)
							.addObject("f_opp_id",f_opp_id)
							.addObject("actDefId",actDefId)
							.addObject("devicerelationship",devicerelationship)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 	查看线的信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("listnew")
	@Action(description="小页面编辑设备基本信息配置")
	public ModelAndView listnew(HttpServletRequest request) throws Exception
	{
		String f_dev_id1 = request.getParameter("F_dev_ID");
		String f_opp_id1 = request.getParameter("F_opp_ID");
		String actDefId = request.getParameter("actDefId");
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select * from w_devicerelationship where F_dev_ID='"+f_dev_id1+"' AND F_opp_ID='"+f_opp_id1+"'"+" AND F_actDefId='"+actDefId+"'";		
		List<Map<String,Object>> list=template.queryForList(sql);			
		String f_dev_id=list.get(0).get("f_dev_id").toString();
		String dev_Port=list.get(0).get("f_dev_Port").toString();
		String f_opp_id=list.get(0).get("f_opp_id").toString();
		String opp_PortType=list.get(0).get("f_opp_PortType").toString();
		String opp_Port=list.get(0).get("f_opp_Port").toString();
		String remark=list.get(0).get("f_remark").toString();
		
		String returnUrl=RequestUtil.getPrePage(request);
		return getAutoView().addObject("f_dev_id",f_dev_id).addObject("dev_Port",dev_Port).addObject("f_opp_id",f_opp_id)
							.addObject("opp_PortType",opp_PortType).addObject("opp_Port",opp_Port).addObject("remark",remark)
							.addObject("returnUrl",returnUrl);
	}
	/**
	 * 添加或更新线路表。
	 * @param request
	 * @param response
	 * @param devicerelationship 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新线路表")
	public void save(HttpServletRequest request, HttpServletResponse response,Devicerelationship devicerelationship) throws Exception
	{
		String resultMsg=null;		
		try{
			if(devicerelationship.getId()==null){
				Long id=UniqueIdUtil.genId();
				devicerelationship.setId(id);
				devicerelationshipService.add(devicerelationship);
				resultMsg=getText("添加","线路表");
			}else{
			    devicerelationshipService.update(devicerelationship);
				resultMsg=getText("更新","线路表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得线路表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看线路表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Devicerelationship> list=devicerelationshipService.getAll(new QueryFilter(request,"devicerelationshipItem"));
		ModelAndView mv=this.getAutoView().addObject("devicerelationshipList",list);
		
		return mv;
	}
	
	/**
	 * 删除线路表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除线路表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			devicerelationshipService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除线路表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑线路表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑线路表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Devicerelationship devicerelationship=devicerelationshipService.getById(id);
		
		return getAutoView().addObject("devicerelationship",devicerelationship)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得线路表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看线路表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Devicerelationship devicerelationship=devicerelationshipService.getById(id);
		return getAutoView().addObject("devicerelationship", devicerelationship);
	}
	
}

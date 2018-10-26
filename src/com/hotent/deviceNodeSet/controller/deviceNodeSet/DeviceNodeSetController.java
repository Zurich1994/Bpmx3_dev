
package com.hotent.deviceNodeSet.controller.deviceNodeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
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
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.deviceNodeSet.model.deviceNodeSet.DeviceNodeSet;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备节点部署表 控制器类
 */
@Controller
@RequestMapping("/deviceNodeSet/deviceNodeSet/deviceNodeSet/")
public class DeviceNodeSetController extends BaseController
{
	@Resource
	private DeviceNodeSetService deviceNodeSetService;
	/**
	 * 添加或更新设备节点部署表。
	 * @param request
	 * @param response
	 * @param deviceNodeSet 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备节点部署表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceNodeSet deviceNodeSet) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deviceNodeSet.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceNodeSet.setId(id);
				deviceNodeSetService.add(deviceNodeSet);
				resultMsg=getText("添加","设备节点部署表");
			}else{
			    deviceNodeSetService.update(deviceNodeSet);
				resultMsg=getText("更新","设备节点部署表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备节点部署表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备节点部署表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceNodeSet> list=deviceNodeSetService.getAll(new QueryFilter(request,"deviceNodeSetItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceNodeSetList",list);
		
		return mv;
	}
	
	/**
	 * 	保存节点设备部署
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//zl...............................................
	@RequestMapping("saveDeviceNode")
	@Action(description="保存节点设备部署")
	public void saveDeviceNode(HttpServletRequest request ,HttpServletResponse response) throws Exception
	{
		String returnUrl=RequestUtil.getPrePage(request);
		String selecteIP=request.getParameter("selecteIP");
		String actdefid=request.getParameter("actdefid");
		String nodeid=request.getParameter("nodeid");
		String deviceTable = request.getParameter("deviceTable");
		DeviceNodeSet deviceNodeSet = new DeviceNodeSet();
		deviceNodeSet.setActdefid(actdefid);
		deviceNodeSet.setNodeid(nodeid);
		deviceNodeSet.setBusinessIP(selecteIP);
		deviceNodeSet.setDeviceTable(deviceTable);
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select id from w_device_node_set where F_actDefId='"+actdefid+"' AND F_nodeId='"+nodeid+"'";
		List<Map<String,Object>> list=template.queryForList(sql);
		if(list.size()!=0){
		    String id = list.get(0).get("ID").toString();
		    deviceNodeSet.setId(Long.valueOf(id));
		    deviceNodeSetService.update(deviceNodeSet);
		}
		else{
		    deviceNodeSetService.add(deviceNodeSet);
		}
		response.sendRedirect(returnUrl);
		
	}
	/**
	 * 删除设备节点部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备节点部署表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceNodeSetService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备节点部署表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备节点部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备节点部署表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceNodeSet deviceNodeSet=deviceNodeSetService.getById(id);
		
		return getAutoView().addObject("deviceNodeSet",deviceNodeSet)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备节点部署表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备节点部署表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceNodeSet deviceNodeSet=deviceNodeSetService.getById(id);
		return getAutoView().addObject("deviceNodeSet", deviceNodeSet);
	}
	
}

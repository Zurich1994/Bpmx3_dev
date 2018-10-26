
package com.hotent.Device_Login_Info.controller.Device_Login_InfoPac;

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

import com.hotent.Device_Login_Info.model.Device_Login_InfoPac.DeviceLoginInfo;
import com.hotent.Device_Login_Info.service.Device_Login_InfoPac.DeviceLoginInfoService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:硬件登录信息表 控制器类
 */
@Controller
@RequestMapping("/Device_Login_Info/Device_Login_InfoPac/deviceLoginInfo/")
public class DeviceLoginInfoController extends BaseController
{
	@Resource
	private DeviceLoginInfoService deviceLoginInfoService;
	/**
	 * 添加或更新硬件登录信息表。
	 * @param request
	 * @param response
	 * @param deviceLoginInfo 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新硬件登录信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceLoginInfo deviceLoginInfo) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deviceLoginInfo.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceLoginInfo.setId(id);
				deviceLoginInfoService.add(deviceLoginInfo);
				resultMsg=getText("添加","硬件登录信息表");
			}else{
			    deviceLoginInfoService.update(deviceLoginInfo);
				resultMsg=getText("更新","硬件登录信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得硬件登录信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看硬件登录信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceLoginInfo> list=deviceLoginInfoService.getAll(new QueryFilter(request,"deviceLoginInfoItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceLoginInfoList",list);
		
		return mv;
	}
	
	/**
	 * 删除硬件登录信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除硬件登录信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceLoginInfoService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除硬件登录信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑硬件登录信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑硬件登录信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceLoginInfo deviceLoginInfo=deviceLoginInfoService.getById(id);
		
		return getAutoView().addObject("deviceLoginInfo",deviceLoginInfo)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得硬件登录信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看硬件登录信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceLoginInfo deviceLoginInfo=deviceLoginInfoService.getById(id);
		return getAutoView().addObject("deviceLoginInfo", deviceLoginInfo);
	}
	
}

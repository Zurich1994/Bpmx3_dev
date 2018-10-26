
package com.hotent.DeviceActionShowPath.controller.DeviceActionShowCode;

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

import com.hotent.DeviceActionShowPath.model.DeviceActionShowCode.DeviceActionShow;
import com.hotent.DeviceActionShowPath.service.DeviceActionShowCode.DeviceActionShowService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备动作展现表 控制器类
 */
@Controller
@RequestMapping("/DeviceActionShowPath/DeviceActionShowCode/deviceActionShow/")
public class DeviceActionShowController extends BaseController
{
	@Resource
	private DeviceActionShowService deviceActionShowService;
	/**
	 * 添加或更新设备动作展现表。
	 * @param request
	 * @param response
	 * @param deviceActionShow 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备动作展现表")
	public void save(HttpServletRequest request, HttpServletResponse response,DeviceActionShow deviceActionShow) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deviceActionShow.getId()==null){
				Long id=UniqueIdUtil.genId();
				deviceActionShow.setId(id);
				deviceActionShowService.add(deviceActionShow);
				resultMsg=getText("添加","设备动作展现表");
			}else{
			    deviceActionShowService.update(deviceActionShow);
				resultMsg=getText("更新","设备动作展现表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备动作展现表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备动作展现表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DeviceActionShow> list=deviceActionShowService.getAll(new QueryFilter(request,"deviceActionShowItem"));
		ModelAndView mv=this.getAutoView().addObject("deviceActionShowList",list);
		
		return mv;
	}
	
	/**
	 * 删除设备动作展现表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备动作展现表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deviceActionShowService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备动作展现表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备动作展现表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备动作展现表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DeviceActionShow deviceActionShow=deviceActionShowService.getById(id);
		
		return getAutoView().addObject("deviceActionShow",deviceActionShow)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备动作展现表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备动作展现表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DeviceActionShow deviceActionShow=deviceActionShowService.getById(id);
		return getAutoView().addObject("deviceActionShow", deviceActionShow);
	}
	
}

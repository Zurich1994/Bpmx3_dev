package com.hotent.platform.controller.bus;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bus.BusQuerySetting;
import com.hotent.platform.service.bus.BusQuerySettingService;
/**
 *<pre>
 * 对象功能:查询设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-20 15:31:03
 *</pre>
 */
@Controller
@RequestMapping("/platform/bus/busQuerySetting/")
public class BusQuerySettingController extends BaseController
{
	@Resource
	private BusQuerySettingService busQuerySettingService;
	
	
	/**
	 * 添加或更新查询设置。
	 * @param request
	 * @param response
	 * @param busQuerySetting 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新查询设置")
	public void save(HttpServletRequest request, HttpServletResponse response,BusQuerySetting busQuerySetting) throws Exception
	{
		String resultMsg=null;		
		try{
			if(busQuerySetting.getId()==null||busQuerySetting.getId()==0){
				busQuerySetting.setId(UniqueIdUtil.genId());
				busQuerySetting.setUserId(ContextUtil.getCurrentUserId());
				busQuerySettingService.add(busQuerySetting);
			}else{
			    busQuerySettingService.update(busQuerySetting);
			}
			resultMsg=getText("保存成功");
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得查询设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看查询设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BusQuerySetting> list=busQuerySettingService.getAll(new QueryFilter(request,"busQuerySettingItem"));
		ModelAndView mv=this.getAutoView().addObject("busQuerySettingList",list);
		
		return mv;
	}
	
	/**
	 * 删除查询设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除查询设置")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			busQuerySettingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除查询设置成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑查询设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑查询设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String tableName =RequestUtil.getString(request,"tableName");
		
		Long userId = ContextUtil.getCurrentUserId();

		BusQuerySetting busQuerySetting = busQuerySettingService.getShowList(tableName,userId);
		
		return getAutoView().addObject("busQuerySetting",busQuerySetting);
	}

	/**
	 * 取得查询设置明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看查询设置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BusQuerySetting busQuerySetting = busQuerySettingService.getById(id);	
		return getAutoView().addObject("busQuerySetting", busQuerySetting);
	}
	
}

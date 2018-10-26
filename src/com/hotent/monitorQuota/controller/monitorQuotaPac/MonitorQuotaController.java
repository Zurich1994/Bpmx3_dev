
package com.hotent.monitorQuota.controller.monitorQuotaPac;

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

import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:设备监控指标表 控制器类
 */
@Controller
@RequestMapping("/monitorQuota/monitorQuotaPac/monitorQuota/")
public class MonitorQuotaController extends BaseController
{
	@Resource
	private MonitorQuotaService monitorQuotaService;
	/**
	 * 添加或更新设备监控指标表。
	 * @param request
	 * @param response
	 * @param monitorQuota 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新设备监控指标表")
	public void save(HttpServletRequest request, HttpServletResponse response,MonitorQuota monitorQuota) throws Exception
	{
		String resultMsg=null;		
		try{
			if(monitorQuota.getId()==null){
				Long id=UniqueIdUtil.genId();
				monitorQuota.setId(id);
				monitorQuotaService.add(monitorQuota);
				resultMsg=getText("添加","设备监控指标表");
			}else{
			    monitorQuotaService.update(monitorQuota);
				resultMsg=getText("更新","设备监控指标表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得设备监控指标表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看设备监控指标表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<MonitorQuota> list=monitorQuotaService.getAll(new QueryFilter(request,"monitorQuotaItem"));
		ModelAndView mv=this.getAutoView().addObject("monitorQuotaList",list);
		
		return mv;
	}
	
	/**
	 * 删除设备监控指标表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除设备监控指标表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			//禁止删除预设指标
			List<MonitorQuota> list = monitorQuotaService.getByIds(lAryId);
			for(MonitorQuota monitorQuota :list){
				if(monitorQuota.getDeviceType()!=null){
					message=new ResultMessage(ResultMessage.Fail, "删除失败,无法删除预设指标！");
					addMessage(message, request);
					response.sendRedirect(preUrl);
					return;
				}
			}
			monitorQuotaService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除设备监控指标成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑设备监控指标表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑设备监控指标表")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		//禁止编辑预设指标
		MonitorQuota monitorQuota = monitorQuotaService.getById(id);
			if(monitorQuota!=null&&monitorQuota.getDeviceType()!=null){
				
				ResultMessage message=new ResultMessage(ResultMessage.Fail, "编辑失败,无法编辑预设指标！");
				addMessage(message, request);
				response.sendRedirect(returnUrl);
				return null;
			}
		
		//MonitorQuota monitorQuota=monitorQuotaService.getById(id);
		if(null==monitorQuota){
			monitorQuota = new MonitorQuota();
			monitorQuota.setClassName("com.hotent.platform.job.Monitor_custom");
		}
		
		return getAutoView().addObject("monitorQuota",monitorQuota)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得设备监控指标表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看设备监控指标表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		MonitorQuota monitorQuota=monitorQuotaService.getById(id);
		return getAutoView().addObject("monitorQuota", monitorQuota);
	}
	
}

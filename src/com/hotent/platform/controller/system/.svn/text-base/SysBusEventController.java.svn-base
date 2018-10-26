package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.table.TableModel;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysBusEvent;
import com.hotent.platform.service.system.SysBusEventService;
/**
 *<pre>
 * 对象功能:sys_bus_event 控制器类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-05-22 11:14:30
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysBusEvent/")
public class SysBusEventController extends BaseController
{
	@Resource
	private SysBusEventService sysBusEventService;
	
	
	/**
	 * 添加或更新 业务保存逻辑。
	 * @param request
	 * @param response
	 * @param sysBusEvent 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新 业务保存逻辑")
	public void save(HttpServletRequest request, HttpServletResponse response,SysBusEvent sysBusEvent) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysBusEvent.getId()==null||sysBusEvent.getId()==0){
				sysBusEventService.save(sysBusEvent);
				resultMsg="添加业务保存逻辑";
			}else{
			    sysBusEventService.save(sysBusEvent);
				resultMsg="更新业务保存逻辑";
			}
			resultMsg+="成功!";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			resultMsg+="失败,";
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得 业务保存逻辑分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看 业务保存逻辑分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysBusEvent> list=sysBusEventService.getAll(new QueryFilter(request,"sysBusEventItem"));
		ModelAndView mv=this.getAutoView().addObject("sysBusEventList",list);
		return mv;
	}
	
	/**
	 * 删除 业务保存逻辑
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除 业务保存逻辑")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysBusEventService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除 业务保存逻辑成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑 业务保存逻辑
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑 业务保存逻辑")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String formKey=RequestUtil.getString(request,"formKey");
		String returnUrl=RequestUtil.getPrePage(request);
		SysBusEvent sysBusEvent=sysBusEventService.getByFormKey(formKey);
		if(sysBusEvent==null){
			sysBusEvent=new SysBusEvent();
			sysBusEvent.setFormkey(formKey);
		}
		return getAutoView().addObject("sysBusEvent",sysBusEvent)
							.addObject("returnUrl",returnUrl)
							.addObject("tablePre", TableModel.CUSTOMER_TABLE_PREFIX)
							.addObject("fieldPre", TableModel.CUSTOMER_COLUMN_PREFIX);
	}

	/**
	 * 取得 业务保存逻辑明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看 业务保存逻辑明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysBusEvent sysBusEvent = sysBusEventService.getById(id);	
		return getAutoView().addObject("sysBusEvent", sysBusEvent);
	}
	
}


package com.hotent.platform.controller.bpm;

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
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hotent.platform.model.bpm.BpmBusLink;
import com.hotent.platform.service.bpm.BpmBusLinkService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:业务数据关联表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2013-08-21 16:51:49
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmBusLink/")
public class BpmBusLinkController extends BaseController
{
	@Resource
	private BpmBusLinkService bpmBusLinkService;
	
	
	/**
	 * 添加或更新业务数据关联表。
	 * @param request
	 * @param response
	 * @param bpmBusLink 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务数据关联表")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		BpmBusLink bpmBusLink=getFormObject(request);
		try{
			if(bpmBusLink.getBusId()==null||bpmBusLink.getBusId()==0){
				bpmBusLink.setBusId(UniqueIdUtil.genId());
				bpmBusLinkService.add(bpmBusLink);
				resultMsg="添加业务数据关联表成功";
			}else{
			    bpmBusLinkService.update(bpmBusLink);
				resultMsg="更新业务数据关联表成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmBusLink 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmBusLink getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		
		BpmBusLink bpmBusLink = (BpmBusLink)JSONObject.toBean(obj, BpmBusLink.class);
		
		return bpmBusLink;
    }
	
	/**
	 * 取得业务数据关联表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务数据关联表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmBusLink> list=bpmBusLinkService.getAll(new QueryFilter(request,"bpmBusLinkItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmBusLinkList",list);
		
		return mv;
	}
	
	/**
	 * 删除业务数据关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务数据关联表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "busId");
			bpmBusLinkService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除业务数据关联表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务数据关联表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务数据关联表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long busId=RequestUtil.getLong(request,"busId",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		BpmBusLink bpmBusLink=bpmBusLinkService.getById(busId);
		
		return getAutoView().addObject("bpmBusLink",bpmBusLink)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得业务数据关联表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务数据关联表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long busId=RequestUtil.getLong(request,"busId");
		BpmBusLink bpmBusLink = bpmBusLinkService.getById(busId);	
		return getAutoView().addObject("bpmBusLink", bpmBusLink);
	}
	
}



package com.hotent.platform.controller.share;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.share.SysShareRights;
import com.hotent.platform.service.share.SysShareRightsService;
import com.hotent.platform.webservice.impl.util.GsonUtil;
/**
 * 对象功能:数据功能权限分享 控制器类
 */
@Controller
@RequestMapping("/platform/share/sysShareRights/")
public class SysShareRightsController extends BaseController
{
	@Resource
	private SysShareRightsService sysShareRightsService;

	
	JSONArray allTypes = new JSONArray();
	/**
	 * 添加或更新数据功能权限分享。
	 * @param request
	 * @param response
	 * @param sysShareRights 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新数据功能权限分享")
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String jsonStr = RequestUtil.getString(request, "sysShareRights");
		JSONObject jobject = new JSONObject();
		try{
			SysShareRights sysShareRights = GsonUtil.toBean(jsonStr, SysShareRights.class);
			if(BeanUtils.isNotEmpty(sysShareRights.getId())){
				sysShareRightsService.update(sysShareRights);
				jobject.accumulate("message", "修改数据成功");
			}
			else{
				sysShareRightsService.save(sysShareRights);
				jobject.accumulate("message", "添加数据成功");
			}
			return jobject.accumulate("result", true);
		}
		catch(Exception e){
			e.printStackTrace();
			return jobject.accumulate("result", false).accumulate("message", e.getMessage());
		}
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("items")
	public ModelAndView getPermissionByRule(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mv = getAutoView();
		String ruleId = RequestUtil.getString(request, "ruleId");
		String ids = RequestUtil.getString(request, "ids");
		short sourceType = RequestUtil.getShort(request, "sourceType");
		String shareType = RequestUtil.getString(request, "shareType");
		JSONObject permisionJo = sysShareRightsService.getPermisionJo(ruleId, ids, sourceType,shareType);
		mv.addObject("display",permisionJo.getJSONObject("display"));
		mv.addObject("manager",permisionJo.getJSONObject("manager"));
		mv.addObject("filter",permisionJo.getJSONObject("filter"));
		mv.addObject("exports",permisionJo.getJSONObject("exports"));
		return mv;
	}
	@RequestMapping("getPermissionByRuleIds")
	@ResponseBody
	public JSONObject getPermissionByRuleIdss(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String[] ruleIds = RequestUtil.getStringAryByStr(request, "ruleIds");
		String ids = RequestUtil.getString(request, "ids");
		short sourceType = RequestUtil.getShort(request, "sourceType");
		String shareType = RequestUtil.getString(request, "shareType");
		JSONObject jo = new JSONObject();
		for(String ruleId : ruleIds){
			jo.put("v_"+ruleId, sysShareRightsService.getPermisionJo(ruleId, ids, sourceType,shareType));
		}
		return jo;
	}
	/**
	 * 取得数据功能权限分享分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看数据功能权限分享分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysShareRights> list=sysShareRightsService.getAll(new QueryFilter(request,"sysShareRightsItem"));
		sysShareRightsService.getAllTypes(allTypes);
		sysShareRightsService.setTypeAndDesc(list,allTypes);
		ModelAndView mv=this.getAutoView().addObject("sysShareRightsList",list);
		return mv;
	}
	
	/**
	 * 删除数据功能权限分享
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除数据功能权限分享")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			sysShareRightsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除数据功能权限分享成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑数据功能权限分享
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑数据功能权限分享")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		SysShareRights sysShareRights=sysShareRightsService.getById(id);
		sysShareRightsService.getAllTypes(allTypes);
		if(BeanUtils.isEmpty(sysShareRights)) {
			sysShareRights = new SysShareRights();
			sysShareRights.setEnable(SysShareRights.YES);
			sysShareRights.setIsAll(SysShareRights.NO);
			sysShareRights.setSync(SysShareRights.NO);
		}
		return getAutoView().addObject("sysShareRights",JSONObject.fromObject(sysShareRights))
				.addObject("returnUrl",returnUrl).addObject("allTypes",allTypes);
	}

	/**
	 * 取得数据功能权限分享明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看数据功能权限分享明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysShareRights sysShareRights=sysShareRightsService.getById(id);
		return getAutoView().addObject("sysShareRights", sysShareRights);
	}
	
}
package com.hotent.platform.controller.bpm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmGangedSet;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmGangedSetService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
/**
 * 
 * 对象功能:联动设置表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-12-28 16:50:37
 */
@Controller
@RequestMapping("/platform/bpm/bpmGangedSet/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmGangedSetController extends BaseController
{
	@Resource
	private BpmGangedSetService bpmGangedSetService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	/**
	 * 保存联动设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	@Action(description="保存联动设置",
			execOrder=ActionExecOrder.AFTER,
			detail="保存流程定义${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defid))}联动设置"
	)
	public String save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String json = RequestUtil.getString(request, "json");
		Long defId = RequestUtil.getLong(request, "defid");
		try{
			bpmGangedSetService.batchSave(defId,json);
		}
		catch(Exception e){
			e.printStackTrace();
			return "{result:0,message:\"保存失败\"}";
		}
		return "{result:1,message:\"保存成功\"}";
	}
	
	/**
	 * 取得 BpmGangedSet 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmGangedSet getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		BpmGangedSet bpmGangedSet = (BpmGangedSet)JSONObject.toBean(obj, BpmGangedSet.class);
		
		return bpmGangedSet;
    }
	
	/**
	 * 取得联动设置表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看联动设置表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long defId=RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);		
		List<BpmNodeSet> nodeList = null;
		BpmNodeSet globalNodeSet = null;
		if(StringUtil.isEmpty(parentActDefId)) {
			nodeList = bpmNodeSetService.getByDefId(defId);
			globalNodeSet = bpmNodeSetService.getBySetType(defId,BpmNodeSet.SetType_GloabalForm);
		}else {
			nodeList = bpmNodeSetService.getByDefId(defId, parentActDefId);
			globalNodeSet = bpmNodeSetService.getBySetType(defId,BpmNodeSet.SetType_GloabalForm, parentActDefId);
		}
		nodeList.add(0, globalNodeSet);
		
		JSONArray jArray = (JSONArray)JSONArray.fromObject(nodeList);
		
		List<BpmGangedSet> list=bpmGangedSetService.getByDefId(defId);
		String s = getJsonFromList(list);
		ModelAndView mv=this.getAutoView().addObject("bpmGangedSetList",s)
										  .addObject("defid",defId)
										  .addObject("nodes",jArray.toString())
										  .addObject("bpmDefinition", bpmDefinition);
		
		return mv;
	}
	
	/**
	 * 将BpmGangedSet列表转为json格式的字符串
	 * @param list
	 * @return
	 */
	protected String getJsonFromList(List<BpmGangedSet> list){
		JSONArray jarray = new JSONArray();
		for(BpmGangedSet bpmGangedSet:list){
			JSONObject setObject = new JSONObject().accumulate("id", bpmGangedSet.getId())
												   .accumulate("nodename", bpmGangedSet.getNodename())
												   .accumulate("changefield", (JSONArray)JSONArray.fromObject(bpmGangedSet.getChangefield()))
												   .accumulate("choisefield", (JSONArray)JSONArray.fromObject(bpmGangedSet.getChoisefield()));
			jarray.add(setObject);
		}
		return jarray.toString();
	}
	
	/**
	 * 获取字段
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFields")
	@ResponseBody
	public String getFields(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		//	Long formKey = RequestUtil.getLong(request, "formKey");
		Integer needFilter = RequestUtil.getInt(request, "filter");
		
		String fields = bpmGangedSetService.getFieldsByFormkey(formKey,needFilter==1);
		return fields;
	}
	
	/**
	 * 删除联动设置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除联动设置表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmGangedSetService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除成功");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑联动设置表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑联动设置表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmGangedSet bpmGangedSet=bpmGangedSetService.getById(id);
		
		return getAutoView().addObject("bpmGangedSet",bpmGangedSet).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得联动设置表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看联动设置表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		BpmGangedSet bpmGangedSet = bpmGangedSetService.getById(id);	
		return getAutoView().addObject("bpmGangedSet", bpmGangedSet);
	}
}

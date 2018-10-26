package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.groovy.GJson;

import org.jivesoftware.smack.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.event.def.TriggerNewFlowEvent;
import com.hotent.platform.event.def.TriggerNewFlowModel;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNewFlowTrigger;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNewFlowTriggerService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.form.BpmFormTableService;
/**
 *<pre>
 * 对象功能:触发新流程配置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2015-05-28 11:20:59
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmNewFlowTrigger/")
public class BpmNewFlowTriggerController extends BaseController
{
	@Resource
	private BpmNewFlowTriggerService bpmNewFlowTriggerService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	
	
	/**
	 * 添加或更新触发新流程配置。
	 * @param request
	 * @param response
	 * @param bpmNewFlowTrigger 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新触发新流程配置")
	public void save(HttpServletRequest request, HttpServletResponse response,BpmNewFlowTrigger bpmNewFlowTrigger) throws Exception
	{
		String resultMsg=null;		
		try{
			if(bpmNewFlowTrigger.getId()==null||bpmNewFlowTrigger.getId()==0){
				bpmNewFlowTrigger.setId(UniqueIdUtil.genId());
				bpmNewFlowTriggerService.add(bpmNewFlowTrigger);
				resultMsg=getText("添加","触发新流程配置");
			}else{
			    bpmNewFlowTriggerService.update(bpmNewFlowTrigger);
				resultMsg=getText("更新","触发新流程配置");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	



	/**
	 * 取得触发新流程配置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看触发新流程配置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmNewFlowTrigger> list=bpmNewFlowTriggerService.getAll(new QueryFilter(request,"bpmNewFlowTriggerItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmNewFlowTriggerList",list);
		return mv;
	}
	
	/**
	 * 删除触发新流程配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除触发新流程配置")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmNewFlowTriggerService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除触发新流程配置成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑触发新流程配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑触发新流程配置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String nodeId = RequestUtil.getString(request, "nodeId");
		String flowKey = RequestUtil.getString(request, "flowKey");
		String actDefId = RequestUtil.getString(request, "actDefId");
		
		List<BpmUserCondition> starUserCondition = bpmUserConditionService.getTriggerNewFlowStartUserConditions(actDefId,nodeId);
		
		BpmNewFlowTrigger bpmNewFlowTrigger;
		if(id == 0)
			bpmNewFlowTrigger =bpmNewFlowTriggerService.getByFlowkeyNodeId(nodeId,flowKey);
		else  bpmNewFlowTrigger=bpmNewFlowTriggerService.getById(id);
		
		if(bpmNewFlowTrigger != null){
			String jsonMaping = bpmNewFlowTrigger.getJsonmaping();
			String triggerJson = bpmNewFlowTrigger.getTriggerJson();
			/*if(StringUtil.isNotEmpty(jsonMaping)){
				bpmNewFlowTrigger.setJsonmaping(JSONUtil.escapeSpecialChar(jsonMaping));
				bpmNewFlowTrigger.setTriggerJson(JSONUtil.escapeSpecialChar(triggerJson));
			}*/
			
			bpmNewFlowTrigger.getTriggerJson();
		}
		return getAutoView().addObject("bpmNewFlowTrigger",bpmNewFlowTrigger)
							.addObject("nodeId", nodeId).addObject("starUserCondition", JSONArray.fromObject(starUserCondition).toString())
							.addObject("flowKey", flowKey).addObject("actDefId",actDefId);
	}

	/**
	 * 取得触发新流程配置明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看触发新流程配置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BpmNewFlowTrigger bpmNewFlowTrigger = bpmNewFlowTriggerService.getById(id);	
		return getAutoView().addObject("bpmNewFlowTrigger", bpmNewFlowTrigger);
	}
	
	/**
	 * 通过流程key 获取表单信息
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTableTreeByDefkey")
	@ResponseBody
	@Action(description="通过流程key 获取表单信息")
	public String getTableTreeByDefkey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String ctx = request.getContextPath();
		String defKey = RequestUtil.getString(request,"defKey");
		String type = RequestUtil.getString(request, "type","trigger");
		BpmDefinition def = bpmDefinitionService.getMainByDefKey(defKey);
		
		BpmFormTable table = bpmFormTableService.getByDefId(def.getDefId());
		List<BpmFormField> fieldList = handelTableTOfieldTree(table);
		
		JSONArray array = new JSONArray();
		for(BpmFormField field : fieldList){
			String icon ;
			if(type.equals("trigger"))
				icon = ctx+"/styles/default/images/resicon/tree_file.png";
			else icon = ctx+"/styles/default/images/resicon/o_10.png";
			if("table".equals(field.getFieldType())) icon = ctx+"/styles/default/images/resicon/tree_folder.gif";
			
			JSONObject json=new JSONObject();
			
			json.accumulate("style", type)
			.accumulate("fieldId", field.getFieldId())
			.accumulate("tableId", field.getTableId())
			.accumulate("fieldType", field.getFieldType())
			.accumulate("fieldName", field.getFieldName())
			.accumulate("fieldDesc", field.getFieldDesc()+"("+field.getFieldType()+")")
			
			
			.accumulate("icon", icon);
			array.add(json);
		}
		
		return array.toString();
	}


	
	private List<BpmFormField> handelTableTOfieldTree(BpmFormTable table) {
		List<BpmFormField> fieldList = new ArrayList<BpmFormField>();
		BpmFormField mainField = getFieldByTable(table);
		fieldList.add(mainField);
		fieldList.addAll(table.getFieldList());
		
		if(BeanUtils.isNotEmpty(table.getSubTableList()))
		for(BpmFormTable subTable :table.getSubTableList()){
			fieldList.add(getFieldByTable(subTable));
			fieldList.addAll(subTable.getFieldList());
		}
		return fieldList;
	}


	private BpmFormField getFieldByTable(BpmFormTable table) {
		BpmFormField field = new BpmFormField();
		field.setTableId(table.getMainTableId());
		field.setFieldId(table.getTableId());
		field.setFieldName(table.getTableName());
		field.setFieldDesc(table.getTableDesc());
		field.setFieldType("table");
		field.setType(table.getIsMain());
		return field;
	}

	
}


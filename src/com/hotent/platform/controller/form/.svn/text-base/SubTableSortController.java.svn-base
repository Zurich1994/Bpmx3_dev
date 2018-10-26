package com.hotent.platform.controller.form;

import java.util.ArrayList;
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
import com.hotent.core.page.PageList;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.SubTableSort;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.SubTableSortService;


/**
 *<pre>
 * 对象功能:bpm_subtable_sort 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-03-12 13:56:01
 *</pre>
 */
@Controller
@RequestMapping("/platform/form/subTableSort/")
public class SubTableSortController extends BaseController
{
	@Resource
	private SubTableSortService subTableSortService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	
	/**
	 * 取得bpm_subtable_sort分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getList")
	@Action(description="查看bpm_subtable_sort分页列表")
	@ResponseBody
	public Object getList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		PageList list=(PageList)subTableSortService.getAll(new QueryFilter(request,true));
		return getMapByPageList(list);
	}
	
	/**
	 * 删除bpm_subtable_sort
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除bpm_subtable_sort")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			subTableSortService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除bpm_subtable_sort成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	
	/**
	 * 	编辑bpm_subtable_sort
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑bpm_subtable_sort")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		
		SubTableSort subTableSort=subTableSortService.getById(id);
		
		
		
		ModelAndView mv=getAutoView();
		mv.addObject("subTableSort",subTableSort)
							.addObject("returnUrl",returnUrl);
		return mv;
	}
	
	/**
	 * 取得bpm_subtable_sort明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SubTableSort subTableSort = subTableSortService.getById(id);	
		
		ModelAndView mv= getAutoView().addObject("subTableSort",subTableSort);
		
		
		return mv;
	}
	
	/**
	 * 
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long tableId = RequestUtil.getLong(request,"tableId");
		String actDefKey=RequestUtil.getString(request,"actDefKey");
		List<BpmFormTable> formTablelist =new ArrayList<BpmFormTable>();
		List<String> tableName =new ArrayList<String>();
		BpmFormTable mainTable = bpmFormTableService.getByTableId(tableId, 1);
		for(BpmFormTable bpmFormTable : mainTable.getSubTableList()){
			tableName.add(bpmFormTable.getTableName());
			formTablelist.add(bpmFormTable);
			
		}
		
		
		List<SubTableSort> subTableSortList = subTableSortService.getByActDefKeyAndTableName(actDefKey,tableName);
		String sortField = this.getJson(subTableSortList);
		ModelAndView mv= getAutoView().addObject("sortField",sortField)
									  .addObject("actDefKey", actDefKey)
									  .addObject("tableId", tableId)
									  .addObject("formTablelist", formTablelist);
		return mv;
	}
	
	
	@RequestMapping("getTreeData")
	@ResponseBody
	public Object getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long tableId = RequestUtil.getLong(request,"tableId");
		if (BeanUtils.isEmpty(tableId)) return null;
		JSONArray jsonArray = new JSONArray();
		BpmFormTable mainTable = bpmFormTableService.getByTableId(tableId, 1);
		JSONObject pJsonObject = new JSONObject();
		pJsonObject.put("sortId", mainTable.getTableId());
		pJsonObject.put("supSortId", "0");
		pJsonObject.put("sortName", mainTable.getTableName());
		pJsonObject.put("sortDesc", mainTable.getTableDesc());
		pJsonObject.put("isField", "0");
		jsonArray.add(pJsonObject);
		for(BpmFormTable bpmFormTable : mainTable.getSubTableList()){
			JSONObject cJsonObject = new JSONObject();
			cJsonObject.put("sortId", bpmFormTable.getTableId());
			cJsonObject.put("supSortId", bpmFormTable.getMainTableId());
			cJsonObject.put("sortName", bpmFormTable.getTableName());
			cJsonObject.put("sortDesc", bpmFormTable.getTableDesc());
			cJsonObject.put("isField", "0");
			jsonArray.add(cJsonObject);
			for (BpmFormField bpmFormField : bpmFormTable.getFieldList()) {
				JSONObject fJsonObject = new JSONObject();
				fJsonObject.put("sortId", bpmFormField.getFieldId());
				fJsonObject.put("supSortId", bpmFormField.getTableId());
				fJsonObject.put("sortName", bpmFormField.getFieldName());
				fJsonObject.put("sortDesc", bpmFormField.getFieldDesc());
				fJsonObject.put("isField", "1");
				jsonArray.add(fJsonObject);
			}
		}
		return jsonArray;
	}
	

	/**
	 * 
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String actDefKey = RequestUtil.getString(request, "actDefKey");
		String json =  RequestUtil.getString(request, "json");
		List<SubTableSort> subTableSortList = this.getFormObject(json,actDefKey);
		try {
			subTableSortService.addList(subTableSortList);
			writeResultMessage(response.getWriter(), "配置子表排序成功!",
					ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(),
					"配置子表排序失败!" + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 把json转换为对象
	 * @param json
	 * @param actDefKey
	 * @return
	 */
	private List<SubTableSort> getFormObject(String json, String actDefKey) {
		if(StringUtil.isEmpty(json))return null;
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<SubTableSort> sList = new ArrayList<SubTableSort>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject tableJsonObject = (JSONObject) jsonArray.get(i);
			JSONArray fieldJsonArray = tableJsonObject.getJSONArray("field");
			String tableName = tableJsonObject.getString("tableName");
			SubTableSort subTableSort = new SubTableSort();
			subTableSort.setId(UniqueIdUtil.genId());
			subTableSort.setActdefkey(actDefKey);
			subTableSort.setTablename(tableName);
			subTableSort.setFieldsort(fieldJsonArray.toString());
			sList.add(subTableSort);
		}
		return sList;
	}
	
	/**
	 * 把对象转换为json
	 * @param subTableSortList
	 * @return
	 */
	private String getJson(List<SubTableSort> subTableSortList) {
		if (BeanUtils.isEmpty(subTableSortList)) return null;
		JSONArray jsonArray = new JSONArray();
		for (SubTableSort subTableSort :subTableSortList) {
			JSONObject tableJsonObject = new JSONObject();
			tableJsonObject.put("tableName", subTableSort.getTablename());
			tableJsonObject.put("field", subTableSort.getFieldsort());
			jsonArray.add(tableJsonObject);
		}
		return jsonArray.toString();
	}
	
}

package com.hotent.platform.service.system;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.WsDataTemplateDao;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.model.system.DataModel;
import com.hotent.platform.model.system.WsDataTemplate;
import com.hotent.platform.service.bpm.BpmCommonWsSetService;
import com.hotent.platform.service.bpm.WebserviceHelper;


@Service
public class WsDataTemplateService extends BaseService<WsDataTemplate>
{
	@Resource
	private WsDataTemplateDao dao;

	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private GroovyScriptEngine scriptEngine ;
	
	private Logger logger = LoggerFactory.getLogger(WsDataTemplateService.class);
	
	public WsDataTemplateService()
	{
	}
	
	/**
	 * 根据之前的webservice配置定义，以及需要的传入参数，对webservice进行访问并返回XML字符串
	 * @param bpmCommonWsSet	webservice配置定义
	 * @param json	需要的传入参数JSON字符串
	 * @return	XML字符串
	 * @throws Exception
	 */
	public String doExecute(BpmCommonWsSet bpmCommonWsSet,String json) throws Exception{
		JSONArray jarray = JSONArray.fromObject(json);
		Map<String,Object> map = new HashMap<String, Object>();
		for(Object obj : jarray){
			JSONObject jObj = (JSONObject)obj;
			String name = jObj.getString("name");
			map.put(name, getVal(jObj));
		}
		String result = WebserviceHelper.executeXml(bpmCommonWsSet.getAlias(), map);
		Document doc= Dom4jUtil.loadXml(result);
		result = Dom4jUtil.docToPrettyString(doc,false);
		return result ;
	}
	
	/**
	 * 根据实体，编译freemaker，得到最终的html
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public String show(WsDataTemplate data, BpmCommonWsSet bpmCommonWsSet, Map<String, Object> params) throws Exception {
		DataModel dataModel = new DataModel();
		Long serviceId = data.getServiceId() ;
		String document = bpmCommonWsSet.getDocument();
		JSONObject jobj = JSONObject.fromObject(document);
		JSONArray jarray = jobj.getJSONArray("inputs");
		Boolean hasEmptyValue = false ;
		Map<String,Object> map = new HashMap<String, Object>();
		for(Object obj : jarray){
			JSONObject jObj = (JSONObject)obj;
			String bindingType = jObj.getString("bindingType");
			//只设置没有预设值参数
			if(!"2".equals(bindingType)) continue ;
			String bindingVal = jObj.getString("bindingVal");
			Object value = params.get(bindingVal);
			if(BeanUtils.isEmpty(value)) {
				hasEmptyValue = true ;
				break ;
			}
			map.put(bindingVal, value);
		}
		//如果有参数值为空的，则不解析模板
		if(hasEmptyValue) return "";
		try{
			String returnXML = WebserviceHelper.executeXml(bpmCommonWsSet.getAlias(), map);
			Document doc= Dom4jUtil.loadXml(returnXML);
			dataModel = parseByScript(data.getScript(), doc);
		}catch(Exception e){
			logger.error(e.getMessage());
			dataModel.setHasData(false);
			dataModel.setErrorMessage(e.getMessage());
		}
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("dataModel", dataModel);
		String result = freemarkEngine.parseByStringTemplate(obj, data.getTemplate());
		return result ;
	}
	
	/**
	 * 根据webservice返回的xml、自定义模板、解析脚本，解析出DataModel对象，最终返回freemarker模板以及解析模板之后的列表html
	 * @param returnXML	webservice返回的xml
	 * @param template 自定义模板
	 * @param parseScript 解析脚本
	 * @return	key: message	&nbsp; value: 解析模板之后的列表html<br/>
	 * 			key: template	&nbsp; value: freemarker模板
	 * @throws Exception
	 */
	public Map<String,String> parseToDataModel(String returnXML, String template, String parseScript) throws Exception{
		DataModel dataModel = new DataModel();
		try{
			dataModel = parseByScript(parseScript, Dom4jUtil.loadXml(returnXML));
		}catch(Exception e){
			logger.error(e.getMessage());
			dataModel.setHasData(false);
			dataModel.setErrorMessage(e.getMessage());
		}
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("dataModel", dataModel);
		if(StringUtil.isEmpty(template)){//没有传入模板代码，使用预设的模板进行解析
			template = FileUtil.inputStream2String(WsDataTemplateService.class.getResourceAsStream(AppConfigUtil.get("webservice.template.path")));
			//第一次解析，解析出字段名
			template = freemarkEngine.parseByStringTemplate(obj, template);
		}
		String message = "" ;
		Map<String, String> resultMap = new HashMap<String, String>();
		try{
			message = freemarkEngine.parseByStringTemplate(obj, template);
			resultMap.put("message", message);
		}catch(Exception e){
			logger.error(e.getMessage());
			dataModel.setHasData(false);
			dataModel.setErrorMessage(e.getMessage());
			resultMap.put("message", e.getMessage());
		}
		resultMap.put("template", template);
		return resultMap;
	}
	
	private DataModel parseByScript(String script, Document doc) throws Exception {
		DataModel dataModel = new DataModel();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doc",doc);
		List<Map> list = (List<Map>) scriptEngine.executeObject(script, params);
		dataModel.setIsList(false);
		if(list.size()>1){//列表
			dataModel.setIsList(true);
			dataModel.setReturnList(list);
		}else if(list.size()==1){//单个
			dataModel.setReturnObj(list.get(0));
		}else{
			dataModel.setHasData(false);
		}
		return dataModel;
	}
	
	private Object getVal(JSONObject obj) throws Exception{
		Integer javaType = obj.getInt("javaType");
		Object paramValue = obj.get("paramValue");
		switch(javaType){
			//列表
			case 3:
				if(paramValue instanceof JSONArray){
					List list = JSONArray.toList((JSONArray)paramValue,String.class);
					paramValue = list;
				}
				break;
			//日期
			case 4:
				String[] formatter = new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","HH:mm:ss","yyyy-MM-dd HH:mm:00"};
				if(paramValue instanceof String){
					paramValue = DateUtils.parseDate(paramValue.toString(), formatter);
				}
				break;
		}
		return paramValue;
	}
	
	@Override
	protected IEntityDao<WsDataTemplate,Long> getEntityDao() 
	{
		return dao;
	}
}

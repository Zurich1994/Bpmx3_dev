package com.hotent.platform.service.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.form.BpmFormFieldDao;
import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.dao.form.BpmPrintTemplateDao;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmPrintTemplate;
import com.hotent.platform.model.form.SubTable;

import freemarker.template.TemplateException;


/**
 *<pre>
 * 对象功能:自定义表单打印模版 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 10:01:30
 *</pre>
 */
@Service
public class BpmPrintTemplateService extends BaseService<BpmPrintTemplate>
{
	@Resource
	private BpmPrintTemplateDao dao;
	@Resource
	private BpmFormFieldDao bpmFormFieldDao;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormControlService bpmFormControlService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmFormHandlerDao bpmFormHandlerDao;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormRightsService bpmFormRightsService;
	@Resource
	private BpmFormTableDao bpmFormTableDao;
	// 流程实例分隔符
	private String InstanceIdSplitor = "#instanceId#";
	// 流程示意图替换符
	private String FlowChartSplitor = "(?s)<div[^>]*?\\s+name=\"editable-input\"\\s+class=\"flowchart\">(.*?)</div>";
	private String FlowChartSplitor_IE = "(?s)<div[^>]*?\\s+class=\"flowchart\"\\s+name=\"editable-input\">(.*?)</div>";
	
	public BpmPrintTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<BpmPrintTemplate, Long> getEntityDao() 
	{
		return dao;
	}

	public int getCountByFormKey(String formKey) {
		return dao.getCountByFormKey(formKey);
	}

	public void setDefault(Long id, Long formKey) {
		dao.updateIsNotDefault(formKey);
		BpmPrintTemplate bpmPrintTemplate=dao.getById(id);
		bpmPrintTemplate.setIsDefault((short)1);
		update(bpmPrintTemplate);
	}

	public BpmPrintTemplate getDefaultByFormKey(String formKey) {
		return dao.getDefaultByFormKey(formKey);
	}
	
	public void delByFormKey(Long formKey){
		dao.delByFormKey(formKey);
	}

	public String obtainHtml(BpmPrintTemplate bpmPrintTemplate) throws TemplateException, IOException {
		Long tableId = bpmPrintTemplate.getTableId();
		String template = bpmPrintTemplate.getTemplate();
		BpmFormData data=new BpmFormData();
		List<BpmFormField> list = bpmFormFieldDao.getByTableIdContainHidden(tableId);
		// 获取流水号和脚本计算结果
		Map<String, Object> resultMap = bpmFormHandlerService.getDataMap(list, data);
		// 将流水号和脚本计算结果加入data
		data.setMainFields(resultMap);

	
		Map<String, Map> model = new HashMap<String, Map>();
		model.put("main", data.getMainFields());
		model.put("opinion", data.getOptions());
		model.put("sub", data.getSubTableMap());
		model.put("others", new HashMap<String, String>());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		// 传入控制器的service，用于在模版中解析字段。
		map.put("service", bpmFormControlService);
	
		// 兼容之前生成的模版。
		map.put("table", new HashMap<Object, Object>());
		String output = freemarkEngine.parseByStringTemplate(map, template);

		output = output.replace(InstanceIdSplitor, "");
		return output;
	}
	
	/**
	 * 获取选项的国际化值
	 * @param data
	 */
	private void handlerOptionResource(Map<String,Object> map,String prefix,Map<String,BpmFormField> optionmap){
		Iterator<String> it = map.keySet().iterator();
		
		while(it.hasNext()){
			String key = it.next();
			String value = map.get(key).toString();
			if(StringUtil.isEmpty(value))continue;
			String replaceValue = bpmFormControlService.getOptionValue(prefix + key, value, optionmap);
			if(StringUtil.isNotEmpty(replaceValue)){
				map.put(key, replaceValue);
			}
		}
	}
	
	/**
	 * 替换掉选项中的 选项key为value
	 * @param data
	 */
	private void replaceOptionKeyWithValue(BpmFormData data){
		Long mainTableId = data.getTableId();
		Map<String,BpmFormField> optionmap = bpmFormHandlerService.getOptionI18nMap(mainTableId);
		BpmFormTable bpmFormTalbe = bpmFormTableDao.getById(mainTableId);
		if(BeanUtils.isEmpty(bpmFormTalbe))return;
		String mainTableName = bpmFormTalbe.getTableName();
		Map<String,Object> mainData = data.getMainFields(); 
		handlerOptionResource(mainData,"m_"+mainTableName+"_",optionmap);
		
		Map<String,SubTable> subtableMap = data.getSubTableMap();
		Iterator<String> it = subtableMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			SubTable subTable = subtableMap.get(key);
			String subTableName = subTable.getTableName();
			List<Map<String, Object>> dataList = subTable.getDataList();
			for(Map<String, Object> subData : dataList){
				handlerOptionResource(subData,"s_"+subTableName+"_",optionmap);
			}
		}
	}
	
	/**
	 * update 2013-1-1 start
	 * 根据打印模版解析 出所打印表单的Html
	 * @param template
	 * @param processRun
	 * @return
	 * @throws Exception
	 */
	public String parseTempalte(BpmPrintTemplate template, ProcessRun processRun,String ctxPath) throws Exception {
		BpmFormDef bpmFormDef=bpmFormDefService.getDefaultVersionByFormKey(template.getFormKey());
		String tempStr="<input id='tableId' name='tableId' type='hidden' value='" + template.getTableId() + "'/>" +template.getTemplate();
		Map<String, Map<String, String>> permission = bpmFormRightsService.getByFormKey(bpmFormDef);
		long runId=processRun.getRunId();
		String instanceId=processRun.getActInstId();
		String pkValue=processRun.getBusinessKey();
		Long tableId=template.getTableId();
		String actDefId=processRun.getActDefId();
		BpmFormData data=new BpmFormData();
		if(StringUtil.isNotEmpty(pkValue)){
			data = bpmFormHandlerDao.getByKey(tableId, pkValue,true);
			// 获取表单的意见。
			if (StringUtil.isNotEmpty(instanceId)) {
				Map<String, String> formOptions = bpmFormHandlerService.getFormOptionsByInstance(instanceId);
				if (BeanUtils.isNotEmpty(formOptions)) {
					data.setOptions(formOptions);
				}
			}
		}
		//替换掉选项中的 选项key 为value
		//备注：此方法会改变data中复选框、单选框的值，影响表单打印功能
		//replaceOptionKeyWithValue(data);
		
		@SuppressWarnings("rawtypes")
		Map<String, Map> model = new HashMap<String, Map>();
		model.put("main", data.getMainFields());
		model.put("opinion", data.getOptions());
		model.put("sub", data.getSubTableMap());
		
		Map<String,String> otherMap = new HashMap<String, String>();
		
		model.put("others", otherMap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		// 传入控制器的service，用于在模版中解析字段。
		map.put("service", bpmFormControlService);
		map.put("table", new HashMap());
		map.put("permission", permission);
		//添加
//		map.put("i18nmap", bpmFormHandlerService.getI18nMap(tableId));
//		Long formId = bpmFormDef.getFormDefId();
//		map.put("i18nformmap", bpmFormHandlerService.getI18nFormMap(formId));
		
		String html = freemarkEngine.parseByStringTemplate(map, tempStr);
		html = html.replace(InstanceIdSplitor, instanceId);
		// 流程图解析
		if (instanceId != "") {
			String repStr = "<iframe src=\""
					+ ctxPath
					+ "/platform/bpm/processRun/processImage.ht?actInstId="
					+ instanceId
					+ "\" name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
			html = html.replaceAll(FlowChartSplitor, repStr);
		} else if (actDefId != "") {
			String repStr = "<iframe src=\""
					+ ctxPath
					+ "/platform/bpm/bpmDefinition/flowImg.ht?actDefId="
					+ actDefId
					+ "\"  name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
			html = html.replaceAll(FlowChartSplitor, repStr);
		}

		// 发布类信、行政公文、流程制度需要用的地方,用于挂载明细页面以供审批 Added by HM
//		if(html.indexOf("replace here for") > 0){
//				if (runId > 0) {
//					html=adService.replaceIframeParam(runId, html,ctxPath);
//				}
//		}
		return html;
	}
	/**update 2013-1-1 end**/

	/**
	 * 获取默认的打印模板
	 */
	public BpmPrintTemplate getDefaultPrintTemplateByFormKey(String formKey) {
		BpmFormDef bpmFormDef=bpmFormDefService.getDefaultVersionByFormKey(formKey);
		String html=bpmFormDef.getHtml();
//		String template=bpmFormDef.getTemplate();
		String taskopinion ="<p><br /></p>"+
	    "<div name=\"editable-input\" class=\"taskopinion\" instanceid=\"#instanceId#\">"+
        	"<input type=\"text\" />"+
	    "</div><br />";
		BpmPrintTemplate bpmPrintTemplate = new BpmPrintTemplate();
		
		String name=bpmFormDef.getSubject();
		bpmPrintTemplate=new BpmPrintTemplate();
		bpmPrintTemplate.setFormKey(formKey);
		bpmPrintTemplate.setTemapalteName(name);
		bpmPrintTemplate.setTableId(bpmFormDef.getTableId());
		bpmPrintTemplate.setHtml(html+taskopinion);
//		bpmPrintTemplate.setTemplate(template+taskopinion);

		return bpmPrintTemplate;
	}
}

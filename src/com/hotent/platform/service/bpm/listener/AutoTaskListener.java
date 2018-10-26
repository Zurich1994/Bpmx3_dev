package com.hotent.platform.service.bpm.listener;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.xml.soap.SOAPMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fr.report.core.A.s;
import com.hotent.act_hi_html.model.ActHiHtml.ActHiHtml;
import com.hotent.act_hi_html.service.ActHiHtml.ActHiHtmlService;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.soap.SoapUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeWebService;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.bpm.BpmProStatusService;
import com.hotent.platform.service.bpm.WebServiceTask;
import com.hotent.platform.service.bpm.impl.ScriptImpl;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;
import com.sun.faces.taglib.html_basic.InputSecretTag;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AutoTaskListener extends BaseNodeEventListener {
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private DbFuncService dbFuncService;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private TableParcelService tableParcelService;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private BpmDataTemplateService BpmDataTemplateService;
	@Resource
	private ActHiHtmlService actHiHtmlService;
	
	@Override
	protected void execute(DelegateExecution execution, String actDefId,
			String nodeId) {
		Map<String,Object> paramsMap = execution.getVariables();                  //获得流程变量的参数	
		System.out.println("123流程变量++++++++++++："+paramsMap);
		System.out.println("actDefId:"+actDefId+"nodeId:"+nodeId);
		Map<String,Object> map = new HashMap<String, Object>();
		

		String subString = subSystemService.findScriptNodeDefKey11(actDefId,nodeId);

		System.out.println("subString:++++++++++++++++++++++"+subString);


		if("ADD".equals(subString)||"DEL".equals(subString)||"UPD".equals(subString)||"QUI".equals(subString)){
			System.out.println("增删改查");

			 map = bpmNodeWebServiceService.resolve(actDefId, nodeId,paramsMap);
			BpmProStatusService bpmProStatusService=(BpmProStatusService)AppUtil.getBean("bpmProStatusService");
			Long actInstanceId=Long.parseLong(execution.getProcessInstanceId());	

			/////////////////////////////////////////////////////////////////////////////////////
			System.out.println("map::::"+map);
			execution.setVariables(map);
			System.out.println("AutoTaskListener执行");
			System.out.println("actInstanceId:"+actInstanceId);
			System.out.println("nodeId:"+nodeId);
			System.out.println("TaskOpinion:"+TaskOpinion.STATUS_EXECUTED);
			bpmProStatusService.addOrUpd(actDefId, actInstanceId,nodeId,TaskOpinion.STATUS_EXECUTED);
			
			
		}else if ("Write".equals(subString)) {
			System.out.println("写页面+++++++++++");
			String templateId = bpmNodeWebServiceService.templateGet(nodeId, actDefId);
			System.out.println("templateId:+++++"+templateId);
			System.out.println("paramsMap123:"+paramsMap);
			String actString = (String)paramsMap.get("mainId");
			System.out.println("actString:::::"+actString);
			ActHiHtml actHiHtml = new ActHiHtml();
			actHiHtml.setCreateId(templateId);
			actHiHtml.setActInstId(actString);
			map = execution.getVariables();
		    System.out.println("map:++112:"+map);
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("actDefId", actDefId);
			params.put("__baseURL", "/mas/platform/bpm/task/startOperate.ht");
			params.put("_ctx","/mas");
			params.put("__tic", "bpmDataTemplate");
			params.put("defId", "10000053860000");
			Map<String,Object> queryparams=new HashMap<String, Object>();
			try {
				if("".equals(templateId)){
					//bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actDefId, nodeId).getFormKey();
					System.out.println("绑定表单");
					
				}else {
					
					String html=BpmDataTemplateService.CreatHtml(templateId, params, queryparams,map);
					actHiHtml.setHtml(html);
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp time = Timestamp.valueOf(df.format(new Date()));
				actHiHtml.setCreatetime(time);
				
				actHiHtmlService.save(actHiHtml);
		
				
				execution.setVariables(map);
				System.out.println("maps:ssssssssssssssssss"+map);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Read".equals(subString)){
			System.out.println("进入读页面");
			BpmProStatusService bpmProStatusService=(BpmProStatusService)AppUtil.getBean("bpmProStatusService");

			Long actInstanceId=Long.parseLong(execution.getProcessInstanceId());	
			map = execution.getVariables();
			
			Object xString = map.get("request");
			JSONObject json = JSONObject.fromObject(xString);
			JSONObject json2 = (JSONObject) json.get("main");
			JSONObject json3 = (JSONObject) json2.get("fields");	
			System.out.println("request:"+json3.toString());
			Iterator iterator = json3.keys();
			while(iterator.hasNext()){
			        String	key = (String) iterator.next();
			        String value = json3.getString(key);
			        System.out.println("key"+key);
			        System.out.println("value"+value);
			        map.put(key,value);
			        
			}
            Map<String, String> mapnextMap = NodeCache.getNextNodes(nodeId, actDefId);
            String nnId = mapnextMap.keySet().toString().substring(1,mapnextMap.keySet().toString().length()-1);
            System.out.println("nnId:"+nnId);
            String sb = subSystemService.findScriptNodeDefKey11(actDefId,nnId);
            if("ADD".equals(sb)){
            	map.put("ID", UniqueIdUtil.genId());
            }
		    System.out.println("map:"+map);


			execution.setVariables(map);
			bpmProStatusService.addOrUpd(actDefId, actInstanceId,nodeId,TaskOpinion.STATUS_EXECUTED);
		}
		else {
			System.out.println("事物图，子事物图");
			ScriptImpl scriptImpl = AppUtil.getBean(com.hotent.platform.service.bpm.impl.ScriptImpl.class);
			System.out.println(subString);
			try {
				paramsMap.put("mainId",execution.getProcessInstanceId());

				ProcessRun processrun= scriptImpl.startFlow(subString,null,paramsMap);
				String actInstId = processrun.getActInstId();
				map.put("actInstId", actInstId);
				
				//改变流程变量 
			
				execution.setVariables(map);
				//System.out.println("maps:ssssssssssssssssss"+map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
		
		
		
	}
	@Override
	protected Integer getScriptType() {
		
		return BpmConst.StartScript;
	}
	
}

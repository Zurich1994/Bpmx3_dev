package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.hotent.core.web.controller.BaseController;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;


@Controller
@RequestMapping("/platform/bpm/bpmTemplateEventBind/")
public class BpmTemplateEventBindController extends BaseController{
	@Resource
	private BpmDataTemplateService bpmDataTemplateService;
	@Resource
	private BpmFormDefService  bpmFormDefService;
	
	
	
	@RequestMapping("config")
	@Action(description = "功能按钮绑定事件图")
	public ModelAndView config(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		System.out.println("config");
		String formDesc = request.getParameter("formDesc")==""?"none":request.getParameter("formDesc");
		String graphsubject = request.getParameter("graphsubject")==""?"none":request.getParameter("graphsubject");
		String graphId = request.getParameter("graphId")==""?"none":request.getParameter("graphId");
		String event = request.getParameter("event")==""?"none":request.getParameter("event");
		String cannum = request.getParameter("cannum")==""?"none":request.getParameter("cannum");
		String inputbind = request.getParameter("inputbind")==""?"none":request.getParameter("inputbind");
		String bindhtml = request.getParameter("bindhtml")==""?"none":request.getParameter("bindhtml");
		//String bindvar = request.getParameter("bindvar")==""?"none":request.getParameter("bindvar");
		mv.addObject("formDesc",formDesc)
		.addObject("graphsubject",graphsubject)
		.addObject("graphId", graphId)
		.addObject("event", event)
		.addObject("cannum", cannum)
		.addObject("inputbind", inputbind)
		.addObject("bindhtml", bindhtml);
		//.addObject("inputcanhtml",inputcanhtml);
		//.addObject("bindtype", bindtype)
		//.addObject("bindvar", bindvar);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("getTemplateField")
	@Action(description = "取得模板所绑定表单的字段值")
	public String getTemplateField(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String formDesc = request.getParameter("formDesc");
		System.out.println("formDesc:"+formDesc);
		System.out.println("取得模板所绑定表单的字段值");
		List<String> filedStrs = new ArrayList<String>();
		List<BpmFormDef> bpmFormDefList = bpmFormDefService.getByDesc(formDesc);
		BpmFormDef bpmFormDef = bpmFormDefList.get(0);
		String formHtml =  bpmFormDef.getHtml();
		System.out.println("-------------html--------------:"+formHtml);
		Pattern p = Pattern.compile("<input[^>]*?name=\"([^\"]+[:]*)");
		Pattern p2 = Pattern.compile("<textarea[^>]*?name=\"([^\"]+[:]*)");
		Matcher m = p.matcher(formHtml);
		while(m.find()){
			System.out.println("匹配到的："+m.group(1));
			filedStrs.add(m.group(1));
		}
		Matcher m2 = p2.matcher(formHtml);
		while(m2.find()){
			System.out.println("匹配到的："+m2.group(1));
			filedStrs.add(m2.group(1));
		}
		String[] fields = new String[3];
		JSONArray ja =  new JSONArray();
		JSONObject jo = new JSONObject();
		for(int i=0;i<filedStrs.size();i++){
			fields = filedStrs.get(i).split(":");
			jo.put("fieldname", fields[2]);
			ja.add(jo);
		}
		System.out.println("jaStr:"+ja.toString());
		return ja.toString();
	}
	
	@RequestMapping("columnBind")
	@Action(description = "列字段事件图绑定")
	public ModelAndView columnBind(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = this.getAutoView();
		System.out.println("config");
		String formDesc = request.getParameter("formDesc")==""?"none":request.getParameter("formDesc");
		String graphsubject = request.getParameter("graphsubject")==""?"none":request.getParameter("graphsubject");
		String graphId = request.getParameter("graphId")==""?"none":request.getParameter("graphId");
		String event = request.getParameter("event")==""?"none":request.getParameter("event");
		String cannum = request.getParameter("cannum")==""?"none":request.getParameter("cannum");
		String inputbind = request.getParameter("inputbind")==""?"none":request.getParameter("inputbind");
		String bindhtml = request.getParameter("bindhtml")==""?"none":request.getParameter("bindhtml");
		//String bindvar = request.getParameter("bindvar")==""?"none":request.getParameter("bindvar");
		mv.addObject("formDesc",formDesc)
		.addObject("graphsubject",graphsubject)
		.addObject("graphId", graphId)
		.addObject("event", event)
		.addObject("cannum", cannum)
		.addObject("inputbind", inputbind)
		.addObject("bindhtml", bindhtml);
		//.addObject("inputcanhtml",inputcanhtml);
		//.addObject("bindtype", bindtype)
		//.addObject("bindvar", bindvar);
		return mv;
	}
}

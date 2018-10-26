package com.hotent.platform.service.system;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.FormUtil;

public class CodeUtil {
	private static String RadioReg="radio=.*?(\\{.*?\\})\"";
	private static String CheckReg="check=.*?(\\{.*?\\})\"";
	private static String SelectReg="select=.*?(\\{.*?\\})\"";
	private static Log logger = LogFactory.getLog(CodeUtil.class);
	
	/**
	 * 根据生成的自定义表单的html 解析成代码生成器所需要的模版
	 * @param html
	 * @param bpmFormTable
	 * @param isEdit
	 * @return
	 */
	public static String getFreeMarkerTemplate(String html,BpmFormTable bpmFormTable,Boolean isEdit){
		Document doc= Jsoup.parseBodyFragment(html);
		Elements p=doc.select("p,script");
		p.remove();
		//遍历主表字段
		parseMainField(doc,bpmFormTable,isEdit);
		//处理子表。
		parseSubTable(doc,bpmFormTable,isEdit);
		String rtn=doc.body().html();
		rtn=replaceAllTag(rtn);
		
		return rtn;
	}
	
	public static String getDialogTags(String html, BpmFormField field) {
			Document doc= Jsoup.parseBodyFragment(html);
			Elements external=new Elements();
			external = doc.select("span:has(input[name="+field.getFieldName()+"])+span:has(a[dialog])");
			if(!external.toString().equals("")){
				return "{ dialog : "+external.select("a[dialog]").attr("dialog")+" }";
			}
			return "";
	}
	
	
	/**
	 * 解析子表
	 * @param doc
	 * @param bpmFormTable
	 * @param isEdit
	 */
	private static void parseSubTable(Document doc,BpmFormTable bpmFormTable,boolean isEdit){
		Elements list=doc.select("div[type=subtable]");
		List<BpmFormTable> subTables=bpmFormTable.getSubTableList();
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element subEle=it.next();
			String mode="";
			String tableName=subEle.attr("tableName").toLowerCase();
			for(BpmFormTable subTable:subTables){
				if(subTable.getTableName().equalsIgnoreCase(tableName)){
					String classVar=subTable.getVariable().get("classVar");
					subEle.attr("id", classVar);
					if(subEle.select("[formtype]").size()!=0){
						Element row=subEle.select("[formtype]").get(0);
						mode=row.attr("formtype");
						//行内编辑模式
						if(BpmFormDef.EditInline.equals(mode)){
							parseEditSubTableField(subTable,row,isEdit);
						}
						//弹出窗口模式
						else{
							parseWindowSubTableField(subTable,subEle,isEdit);
						}
						row.removeAttr("formtype");
						if(isEdit){
							Element appendTr=row.clone();
							appendTr.attr("type", "append");
							appendTr.attr("style", "display:none;");
							row.after(appendTr.outerHtml());
						}
						row.attr("type", "subdata");
						row.before("&lt;c:forEach items='&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"List}' var='"+classVar+"' varStatus='status'&gt;");
						row.after("&lt;/c:forEach&gt;");
					}
					
					subEle.attr("formtype", mode);
				}
			}
				
		}
	}
	
	
	/**
	 * 解析弹框模式的子表
	 * @param subTable
	 * @param e
	 * @param isEdit
	 */
	private static void parseWindowSubTableField(BpmFormTable subTable,
			Element e, boolean isEdit) {
		Elements list=e.select("td[fieldname^=s:]");
		if(list.get(0).parent().select("input").size()!=0){
			list.get(0).parent().select("input").get(0).remove();
		}
		String classVar=subTable.getVariable().get("classVar");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("fieldname");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "").toLowerCase();
			if(subTable.getIsExternal()==1){
				el.attr("name", fieldName.toLowerCase());
			}else{
				el.attr("name", fieldName);
			}
			el.removeAttr("fieldname");
			el.text("&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}");
			el.parent().append("&lt;input type=\"hidden\" name=\""+fieldName+"\"value=\"&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}\"/&gt;");
		}
		Element windowForm=e.select("table[formtype=window],div[formtype=window]").get(0);
		windowForm.attr("style", "display:none;");
		windowForm.attr("id", classVar+"Form");
		if(!isEdit){
			windowForm.remove();
		}else{
			Elements controls=windowForm.select("input[name^=s:],textarea[name^=s:],select[name^=s:],a.link");
			for(Iterator<Element> it= controls.iterator();it.hasNext();){
				Element el=it.next();
				String name=el.attr("name");
				//获取字段名
				String fieldName=name.replaceAll("^.*:", "");
				if(StringUtil.isEmpty(name)){//处理附件的bug
					name = el.attr("field");
					if(StringUtil.isNotEmpty(name) && name.matches("s:(\\w*):(\\w*)")){
						fieldName =name.replaceAll("^.*:", "");
					}
				}
				el.attr("name", fieldName);
			}
		}
	}

	private static void parseEditSubTableField(BpmFormTable table,Element e,boolean isEdit){
		Elements list=e.select("input[name^=s:],textarea[name^=s:],select[name^=s:],a.link");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "");
			if(StringUtil.isEmpty(name)){//处理附件的bug
				name = el.attr("field");
				//不是子表
				if(StringUtil.isNotEmpty(name) && name.matches("s:(\\w*):(\\w*)")){
					fieldName =name.replaceAll("^.*:", "");
				}
			}
			if(fieldName.equalsIgnoreCase(table.getPkField())){
				el.remove();
				continue;
			}
			if(table.getIsExternal()==1){
				el.attr("name", fieldName.toLowerCase());
			}else{
				el.attr("name", fieldName);
			}
			parseField(el,table.getVariable().get("classVar"),isEdit);
		}
	}
	
	
	
	private static void parseField(Element e,String classVar, boolean isEdit) {
		String controltype=e.attr("controltype");
		String fieldName=e.attr("name");
		if(e.nodeName().equals("input")){
			String type=StringUtil.isEmpty(e.attr("type"))?"text":e.attr("type");
			if(type.equals("text")){
				if(e.hasClass("Wdate")){
					String datefmt=e.attr("datefmt");
					if(isEdit){
						e.removeAttr("datefmt");
						e.attr("value", "&lt;fmt:formatDate value='&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}' pattern='"+datefmt+"'/&gt;");
					}else{
						e.before("&lt;fmt:formatDate value='&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}' pattern='"+datefmt+"'/&gt;");
						e.remove();
					}
				}else{
					if(isEdit){
						e.attr("value","&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}");
					}else{
						e.before("&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}");
						e.remove();
					}
					
				}
			}else if(type.equals("radio")){
				String value=e.attr("value");
				if(isEdit){
					if(StringUtil.isNotEmpty(value)){
						e.attr("radio","{field:'"+fieldName+"',value:'"+value+"',classVar:'"+classVar+"'}");
					}
				}else{
					Element label=e.parent();
					e.remove();
					String labelname=label.text();
					label.before("&lt;c:if test='&lt;#noparse&gt${&lt;/#noparse&gt;"+classVar+"."+fieldName+"=="+value+"}'&gt;"+labelname+"&lt;/c:if&gt;");
					label.remove();
				}
			}else if(type.equals("checkbox")){
				String value=e.attr("value");
				if(isEdit){
					if(StringUtil.isNotEmpty(value)){
						e.attr("check","{field:'"+fieldName+"',value:'"+value+"',classVar:'"+classVar+"'}");
					}
				}else{
					Element label=e.parent();
					e.remove();
					String labelname=label.text();
					label.before("&lt;c:if test='&lt;#noparse&gt${&lt;/#noparse&gt;fn:contains("+classVar+"."+fieldName+","+value+")}'&gt;"+labelname+" &lt;/c:if&gt;");
					label.remove();
				}
			}else if(type.equals("hidden")){
				e.attr("value","&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}");
			}
		}
		//多行文本框
		if(e.nodeName().equals("textarea")){
			e.text("&lt;#noparse&gt;${&lt;/#noparse&gt;"+classVar+"."+fieldName+"}");
			if(!isEdit){
				e.attr("readonly","readonly");
			}
		}
		//下拉选项
		if(e.nodeName().equals("select")){
			Elements options=e.select("option");
			if(isEdit){
				for(Iterator<Element>o=options.iterator();o.hasNext();){
					Element option=o.next();
					String value=option.attr("value");
					if(StringUtil.isNotEmpty(value)){
						option.attr("select","{field:'"+fieldName+"',value:'"+value+"',classVar:'"+classVar+"'}");
					}
				}
			}else{
				for(Iterator<Element>o=options.iterator();o.hasNext();){
					Element option=o.next();
					String labelname=option.text();
					String value=option.attr("value");
					if(StringUtil.isNotEmpty(value)){
						e.before("&lt;c:if test='&lt;#noparse&gt${&lt;/#noparse&gt;"+classVar+"."+fieldName+"=="+value+"}'&gt;"+labelname+"&lt;/c:if&gt;");
					}
				}
				e.remove();
			}
		}
		//子表添加按钮 选择器等
		if(e.nodeName().equals("a")){
			if(e.hasClass("selectFile")){
				e.attr("field", fieldName);
				if(isEdit){
					e.parent().parent().attr("right", "w");
				}else{
					e.parent().parent().attr("right", "r");
				}
			}
			if(!isEdit){
				e.remove();
			}
			
		}
		//office控件
		if(controltype.equals("office")){
			if(isEdit){
				e.attr("right", "w");
				e.parent().select("div[class=office-div]").get(0).attr("id", "div_"+fieldName);
			}else{
				e.parent().select("div[class=office-div]").get(0).attr("id", "div_"+fieldName);
				e.attr("right", "r");
			}
		}
		//附件上传
		if(controltype.equals("attachment")){
			if(isEdit){
				e.parent().attr("right", "w");
			}else{
				e.parent().attr("right", "r");
			}
		}
		
		//pictureShow控件
		if(controltype.equals("pictureShow")){
			if(isEdit){
				e.attr("right", "w");
			}else{
				e.attr("right", "r");
			}
			Element pictureShowDiv = e.parent().select("div[class=pictureShow-div]").get(0);
			pictureShowDiv.select("div").get(0).attr("id", "div_"+fieldName);
			pictureShowDiv.select("div").get(1).attr("id", "div_"+fieldName+"_container");
			pictureShowDiv.select("table").get(0).attr("id", "pictureShow_"+fieldName+"_Toolbar");
			Elements aEl = pictureShowDiv.select("table").get(0).select("a");
			for (Element a : aEl){
				a.attr("name",fieldName);
				a.attr("field",fieldName);
			}
		}
		
	}
	
	
	/**
	 * 替换所有标记
	 * @param rtn
	 * @return
	 */
	private static String replaceAllTag(String rtn) {
		rtn=replaceTag(rtn,RadioReg);
		rtn=replaceTag(rtn,CheckReg);
		rtn=replaceTag(rtn,SelectReg);
		rtn=rtn.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;lt;", "<").replaceAll("&amp;gt;", ">").replace("&quot;", "'");
		return rtn;
	}

	/**
	 * 替换标识
	 * @param html
	 * @param reg
	 * @return
	 */
	private static String replaceTag(String html,String reg){
		 StringBuffer resultString = new StringBuffer();
	        try {
	            Pattern regex = Pattern.compile(reg);
	            Matcher regexMatcher = regex.matcher(html);
	            while (regexMatcher.find()) {
	            	String value=regexMatcher.group(0);
	            	String type=value.split("=")[0];
	            	String jsonStr=value.substring(value.indexOf("{"), value.indexOf("}")+1);
	    			JSONObject jsonObj=JSONObject.fromObject(jsonStr);
	    			String rep="";
	    			String field=(String)jsonObj.get("field");
	    			String thisVal=(String)jsonObj.get("value");
	    			String classVar=(String)jsonObj.get("classVar");
	    			if(type.equals("radio")){
	    				rep="<c:if test='<#noparse>\\${</#noparse>"+classVar+"."+field+"==\""+thisVal+"\"}'>checked='checked'</c:if>";
	    			}
	    			if(type.equals("check")){
	    				rep="<c:if test='<#noparse>\\${</#noparse>fn:contains("+classVar+"."+field+",\""+thisVal+"\")}'>checked='checked'</c:if>";
	    			}
	    			if(type.equals("select")){
	    				rep="<c:if test='<#noparse>\\${</#noparse>"+classVar+"."+field+"==\""+thisVal+"\"}'>selected='selected'</c:if>";
	    			}
	    			
	                regexMatcher.appendReplacement(resultString, rep);
	            }
	            regexMatcher.appendTail(resultString);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return resultString.toString();
	}
	
	/**
	 * 解析主表
	 * @param doc
	 * @param isEdit
	 */
	private static void parseMainField(Document doc,BpmFormTable bpmFormTable,Boolean isEdit) {
		String classVar=bpmFormTable.getVariable().get("classVar");
		Elements list=doc.select("input[name^=m:],textarea[name^=m:],select[name^=m:],a.link");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "");
			if(StringUtil.isEmpty(name)){//处理附件的bug
				name = el.attr("field");
				//不是子表
				if(StringUtil.isNotEmpty(name) && name.matches("m:(\\w*):(\\w*)")){
					fieldName =name.replaceAll("^.*:", "");
				}
			}
			if(bpmFormTable.getIsExternal()==1){
				el.attr("name", fieldName.toLowerCase());
			}else{
				el.attr("name", fieldName);
			}
			parseField(el,classVar,isEdit);
		}
	}
	
}

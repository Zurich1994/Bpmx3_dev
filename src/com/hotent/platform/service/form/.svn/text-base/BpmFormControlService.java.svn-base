package com.hotent.platform.service.form;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormField;

/**
 * 对象功能:控件Service类。 
 * 用于在freemaker模版中使用。 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:xwy 创建时间:2012-02-10
 * 10:48:16
 */
@Service
public class BpmFormControlService {
	public static final String NO_PERMISSION="";
	
	/**
	 * 获取选项的国际化资源
	 * @param fieldName
	 * @param optionKey
	 * @return
	 */
	public String getOptionValue(String fieldName,String optionKey,Map<String,BpmFormField> optionmap){
		BpmFormField bpmFormField = optionmap.get(fieldName);
		if(BeanUtils.isEmpty(bpmFormField)){
			return "";
		}
		Map<String,Object> optsMap = bpmFormField.getAryOptions();
		String value = "";
		if(StringUtil.isEmpty(optionKey)){
			Set<String> keySet = optsMap.keySet();
			StringBuilder sb = new StringBuilder();
			for(Iterator<String> it = keySet.iterator();it.hasNext();){
				String resKey = it.next();
				String resValue = optsMap.get(resKey).toString();
				sb.append("<option value=\"");
				sb.append(resKey);
				sb.append("\">");
				sb.append(resValue);
				sb.append("</option>");
			}
			value = sb.toString();
		}
		else{
			Object valueObj = optsMap.get(optionKey);
			if(BeanUtils.isNotEmpty(valueObj)){
				value = valueObj.toString();
			}
		}
		return value;
	}
	
	/**
	 * 显示主表字段值，用于模版中使用。
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getField(String fieldName, String html,
			Map<String, Map<String, Map>> model,
			Map<String, Map<String, String>> permission) {
		fieldName = fieldName.toLowerCase();
		// 取得值
		Object object = model.get("main").get(fieldName);
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		
		// 可写权限
		if (permission.get("field") == null
				|| permission.get("field").get(fieldName) == null
				|| "w".equals(permission.get("field").get(fieldName))) {
			String dealHtml = dealTextAreaValue(html, "#value", value);
			return StringUtil.replaceAll(dealHtml, "#value", value);
			//return html.replaceAll("#value", value);
		} else if ("b".equals(permission.get("field").get(fieldName))) {
			html=toRequired(html);
			String dealHtml = dealTextAreaValue(html, "#value", value);
			return StringUtil.replaceAll(dealHtml, "#value", value);
			//return toRequired(html).replaceAll("#value", value);
		} else if ("r".equals(permission.get("field").get(fieldName))) {
			//将换行符转换为html的换行符标签
			value = value.replaceAll("[\\r\\n|\\n|\\r]", "<br/>");
			String dealHtml = dealTextAreaValue(html, "#value", value);
			String extLink = getExtLink(fieldName,model,dealHtml,value);
			extLink = getOptionValue(dealHtml,value,extLink);
			extLink += getHiddenWebSign(dealHtml,value);
			return extLink;
		}else if ("rp".equals(permission.get("field").get(fieldName))) {
			//将换行符转换为html的换行符标签
			value = value.replaceAll("[\\r\\n|\\n|\\r]", "<br/>");
			String rpostInput = getReadPost(html);
			rpostInput=StringUtil.replaceAll(rpostInput, "#value", value);
			String dealHtml = dealTextAreaValue(html, "#value", value);
			String extLink = getExtLink(fieldName,model,dealHtml,value);
			String retval =rpostInput + extLink;
			return retval;
		}else {
			return NO_PERMISSION;
		}
	}


	/**
	 * 获取只读状态的 选项值
	 * @param html
	 * @param value
	 * @return
	 */
	
	private static String getOptionValue(String html,String value, String extLink){
		Document  doc = Jsoup.parse(html);
		Elements selectElement=doc.select("select");
		String sQuery=selectElement.attr("selectquery");
		if (sQuery!=null && sQuery!="") {
			String external = sQuery.replace("&#39;", "\"").replace("&quot;", "\"");
			String temp = "<span selectvalue=" + value + " selectquery=" + external + "><lable></lable></span>";
			return temp.replaceAll("'", "\"");
			
		}else {
			//if (StringUtil.isNotEmpty(value)) {
				Elements optElments = doc.select("option");
				if(optElments.size()>0){
					for(Element elment : optElments){
						String key = elment.attr("value");
						if(value.equals(key)){
							return elment.text();
						}
					}
				}
			/*}else{
				if (BeanUtils.isNotEmpty(selectElement)) {
					return html;
				}
				Elements inputObj=doc.select("input");
				String dicCom=inputObj.attr("class");
				if ("dicComboTree".equals(dicCom)) {
					return html;
				}
				return extLink;
			}*/
		}
		
		return extLink;
	}
	
	
	/**
	 * 获取只读状态下有WEB验证的标签要用到input:hidden
	 * @param html
	 * @param value
	 * @return
	 */
	private static String getHiddenWebSign(String html,String value){
		StringBuffer sb = new StringBuffer();
		String str = html.replaceAll(" ", "");  //去掉空格
		if(str.contains("isWebSign':true")||str.contains("isWebSign:true")||str.contains("isWebSign\":true")){     
			Document  doc = Jsoup.parse(html);
			String elStr ="";
			//输入文本等类型
			Elements elList = doc.select("input");
			for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
				Element el = it.next();
				el.addClass("hidden");
				el.attr("value",value);
				el.attr("validate", "{'isWebSign':true}");
				el.attr("right", "r");
				sb.append(el+" ");
			}
			//下拉框
			elList = doc.select("select");
			for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
				Element el = it.next();
				elStr ="<input type=\"hidden\" name=\""+el.attr("name")+"\" lablename=\""+el.attr("lablename")+"\" validate=\"{'isWebSign':true}\" value=\""+value+"\" right=\"r\" /> ";
				sb.append(elStr+" ");
				
			}
			//大文本
			elList = doc.select("textarea");
			for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
				Element el = it.next();
				elStr = "<textarea name=\""+el.attr("name")+"\" lablename=\""+el.attr("lablename")+"\" validate=\"{'isWebSign':true}\"  class=\"hidden\" value=\""+value+"\"  right=\"r\" >"+value+"</textarea>";
				sb.append(elStr+" ");
			}
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * 生成引用超连接
	 * @param fieldName
	 * @param model
	 * @param html
	 * @param value
	 * @return
	 */
	private static String  getExtLink(String fieldName,Map<String, Map<String, Map>> model,String html,String value){
		if(StringUtil.isEmpty(value)){
			return value;
		}
		Object objectId =  model.get("main").get(fieldName+"id");
		if(objectId==null){
			return value;
		}
		String idStr =  objectId.toString();
		
		if(StringUtil.isEmpty(idStr)){
			return value;
		}
		Element div = new Element(Tag.valueOf("div"), "");
		Document  doc = Jsoup.parse(html);
		Elements links = doc.select("[linktype]");
		
		String nameStr = value;
		String[] names = nameStr.split(",");

		if(links.size()==0){
			for (int i = 0; i < names.length; i++) {
				Element span = new Element(Tag.valueOf("span"), "");
			//	span.attr("class","backgrounddiv");
		//		Element lable = new Element(Tag.valueOf("lable"), "");
			//	lable.text(names[i]);
			//	span.appendChild(lable);
					span.text(names[i]);
				div.appendChild(span);
			}
			String valuela= div.outerHtml();
			return valuela;
		}
		
		Element node = links.get(0);
		String linktype = node.attr("linktype");
		String[] ids = idStr.split(",");

		for(int i=0;i<ids.length;i++){
			Element span = new Element(Tag.valueOf("span"), "");
			span.attr("class","backgrounddiv");
			
			span.text(names[i]);
          /*
			Element link = new Element(Tag.valueOf("a"), "");
			String id=ids[i];
			String name = names[i];
			link.attr("refid", id);
			link.attr("href","#");
			link.attr("linktype",linktype);
			link.attr("style","text-decoration:none");
			link.text(name);
			
			span.appendChild(link);*/
			div.appendChild(span);
		}
		String retval = div.outerHtml();
		
		return retval;
	}
	
	/**
	 * 显示选择器的选择按钮
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getLink(String fieldName, String html,
			Map<String, Map<String, Map>> model,
			Map<String, Map<String, String>> permission) {
		Document doc = Jsoup.parse(html);
		Elements elList = doc.select("a");
		if(elList.size()>0){              //有超链接的
			for (Element el : elList)
			{
				String name = el.attr("name");        
				if(StringUtil.isNotEmpty(name)){
					name = name.replaceAll(" ", "").toLowerCase().substring(0, 2);  //去掉空格 、改变为小写、并截取前两个字符
					if(name.contains("s:")){            //并是子表的超链接 直接返回 由页面JS解析 子表字段名称是s:开头的
						return html;                // 直接返回
					}
				}
			}
		}
		fieldName = fieldName.toLowerCase();
		// 可写权限
		if (permission.get("field") == null
				|| permission.get("field").get(fieldName) == null
				|| "w".equals(permission.get("field").get(fieldName))
				|| "b".equals(permission.get("field").get(fieldName))) {
			return html;
		} else {
			return NO_PERMISSION;
		}
	}

	/**
	 * 显示主表字段中的隐藏字段
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getHiddenField(String fieldName, String html,
			Map<String, Map<String, Map>> model,
			Map<String, Map<String, String>> permission) {

		fieldName = fieldName.toLowerCase();
		// 取得值
		Object object = model.get("main").get(fieldName);
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		// 可写权限
		/*
		if (permission.get("field") == null
				|| permission.get("field").get(fieldName) == null
				|| "w".equals(permission.get("field").get(fieldName))) {
			
			return StringUtil.replaceAll(html, "#value", value);
		} else if ("b".equals(permission.get("field").get(fieldName))){
			html=toRequired(html);
			return StringUtil.replaceAll(html, "#value", value);
		// 只读和其他情况下不返回值
		}else if ("rp".equals(permission.get("field").get(fieldName))) {
			String rpostInput = getReadPost(html);
			rpostInput=StringUtil.replaceAll(rpostInput, "#value", value);
			return rpostInput;
		}else {
			return NO_PERMISSION;
		}
		*/
			String right = this.getFieldRight(fieldName, permission);
		if ("w".equals(right)) {
			
			return StringUtil.replaceAll(html, "#value", value);
		} else if ("b".equals(right)){
			html=toRequired(html);
			return StringUtil.replaceAll(html, "#value", value);
		// 只读和其他情况下不返回值
		}else if ("r".equals(right)) {
			String rpostInput = getReadPost(html);
			rpostInput=StringUtil.replaceAll(rpostInput, "#value", value);
			return rpostInput;
		}else {
			return NO_PERMISSION;
		}
	}

	/**
	 * 获取子表权限。
	 * 
	 * @param tableName
	 * @param permission
	 * @return
	 */
	public String getSubTablePermission(String tableName,
			Map<String, Map<String, String>> permission) {
		tableName = tableName.toLowerCase();
		Map map = (Map) permission.get("table");
		String right = "w";
		if (map.containsKey(tableName)) {
			right = (String) map.get(tableName);
		}
		return right;
	}

	/**
	 * 获取字段的权限
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param model
	 *            数据对象
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getFieldRight(String fieldName,
			Map<String, Map<String, String>> permission) {
		fieldName = fieldName.toLowerCase();
/*
		// 可写权限
		if (permission.get("field").get(fieldName) == null
				|| "w".equals(permission.get("field").get(fieldName))) {
			return "w";
		}else if ("b".equals(permission.get("field").get(fieldName))) {
			return "b";
		}else if ("r".equals(permission.get("field").get(fieldName))) {
			return "r";
		} else {
			return "no";
		}
		*/
			String fieldRight= null;
		if( permission.get("field") != null){
			fieldRight = permission.get("field").get(fieldName);
		}
		// 可写权限
		if (fieldRight == null
				|| "w".equals(fieldRight)) {
			return "w";
		}else if ("b".equals(fieldRight) ) {
			return "b";
		}else if ("r".equals(fieldRight) || "rp".equals(fieldRight)) {
			return "r";
		} else {
			return "no";
		}
	}
	
	
	/**
	 * 获取字段的控件菜单权限
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getFieldMenuRight(String fieldName,
			Map<String, Map<String, String>> permission) {
		fieldName = fieldName.toLowerCase();
		
		if (permission.get("menuRight") == null ) {
			return "";
		}
		
		// 可写权限
		if (permission.get("menuRight").get(fieldName)!= null ) {
			String menuRight =  permission.get("menuRight").get(fieldName);
			menuRight = menuRight.replaceAll("\"", "'");
			return menuRight;
		}
		return "";
	}
	

	/**
	 * 将#value替换为字段的值。
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
	 * @return
	 */
	public String getFieldValue(String fieldName,
			Map<String, Map<String, Map>> model) {
		fieldName = fieldName.toLowerCase();
		// 取得值
		Object object = model.get("main").get(fieldName);
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		return value;
	}
	
	/**
	 * 将#value替换为字段的值。
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
     * @param type
	 *            控件类型（用于过虑值中的特殊符号）
	 * @return
	 */
	public String getFieldValue(String fieldName,
			Map<String, Map<String, Map>> model,String type) {
		// 取得值
        String value = getFieldValue(fieldName, model);
		if("pictureShow".equalsIgnoreCase(type)){
			value = value.replaceAll("\"", "'");
        }			
		return value;
	}
	
/**
	 * 取得主表数值型需要千分位显示的字段 值
	 * @param fieldName
	 * @param model
	 * @return
	 */
	public String getComdifyValue(String fieldName,  Map<String, Map<String, Map>> model){
		fieldName=fieldName.toLowerCase();
		//取得值
		Object object = model.get("main").get(fieldName);
		String value = "";
		if(object != null) {
			value = object.toString();
		}
		return StringUtil.comdify(value);
	}
	
	/**
	 * 取得子表 数值型需要千分位显示的字段 值
	 * @param fieldName
	 * @param table
	 * @return
	 */
	public String getSubComdifyValue(String fieldName, Map<String, Map>table){
		fieldName=fieldName.toLowerCase();
		String value="";
		Object obj=table.get(fieldName);
		if(obj!=null){
			value=obj.toString();
		}
		return StringUtil.comdify(value);
	}
	/**
	 * 显示意见，用于模版中使用。
	 * 
	 * @param opinionName
	 * @param html
	 *            字段html
	 * @param model
	 *            数据对象
	 * @param permission
	 *            权限对象
	 * @return
	 */
	public String getOpinion(String opinionName, String html,
			Map<String, Map<String, Map>> model,
			Map<String, Map<String, String>> permission) {
		opinionName = opinionName.toLowerCase();

		Object object = model.get("opinion").get(opinionName);
		if (object == null) {
			object = model.get("opinion").get("opinion:" + opinionName);
		}

		String value = "";
		if (object != null) {
			value = object.toString();
		}
		Map<String, String> opinion = permission.get("opinion");
		String rights = opinion.get(opinionName);
		if (rights == null) {
			rights = opinion.get("opinion:" + opinionName);
		}

		if (opinion == null || rights == null || "w".equals(rights)) {
			return value + html;
			// return html.replaceAll("#value", value);
		}else if ("b".equals(rights)){
			return value + toRequired(html) ;
		}else if ("r".equals(rights)) {
			return value;
		} else {
			return NO_PERMISSION;
		}
	}
	/**
	 *   取得意见
	 * @param opinionName
	 * @param model
	 * @return
	 */
	public String getOpinion(String opinionName,Map<String,Map<String,Map>>model){
		opinionName=opinionName.toLowerCase();
		Object object = model.get("opinion").get(opinionName);
		if(object==null){
			object=model.get("opinion").get("opinion:" +opinionName);
		}
		String value = "";
		if(object != null) {
			value = object.toString();
		}
		return value;
	}
	
	/**
	 * 返回rdo和checkbox控件
	 * 
	 * @param fieldName
	 * @param html
	 * @param ctlVal
	 * @param model
	 * @param permission
	 * @return
	 */
	public String getRdoChkBox(String fieldName, String html, String ctlVal,
			Map<String, Map<String, Map>> model,
			Map<String, Map<String, String>> permission) {
		Object object = model.get("main").get(fieldName.toLowerCase());
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		if (permission.get("field").get(fieldName) == null
				|| "w".equals(permission.get("field").get(fieldName)) ) {
			html = html.replaceAll("(?s)disabled=\\s*\"?\\s*disabled\\s*\"?",
					"");
			html = getHtml(html, ctlVal, value);
			return html;
		}else if ("b".equals(permission.get("field").get(fieldName))) {
			html = html.replaceAll("(?s)disabled=\\s*\"?\\s*disabled\\s*\"?","");
			html = getHtml(html, ctlVal, value);
			Document doc = Jsoup.parse(html);
			Elements elList = doc.select("input,select,textarea");
			for(Element e:elList){
				toRequired(e.outerHtml());
				e.after(toRequired(e.outerHtml()));
				e.remove();
			}
			return doc.getElementsByTag("body").html();
		} else if ("r".equals(permission.get("field").get(fieldName))) {
			html = getHtml(html, ctlVal, value);
			return html;
		} else {
			return NO_PERMISSION;
		}
	}
	
	/**
	 * 返回rdo和checkbox控件(用于打印模板)
	 * 
	 * @param fieldName
	 * @param html
	 * @param ctlVal
	 * @param model
	 * @param permission
	 * @return
	 */
	public String getPrintRdoChkBox(String fieldName, String html, String ctlVal,
			Map<String, Map<String, Map>> model) {
		Object object = model.get("main").get(fieldName.toLowerCase());
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		html = getHtml(html, ctlVal, value);
		return html;
	}
	/**
	 * 返回下拉框值(用于打印模板)
	 * @param fieldName
	 * @param html
	 * @param model
	 * @return
	 */
	public String getPrintOptionValue(String fieldName, String html, Map<String, Map<String, Map>> model){
		fieldName = fieldName.toLowerCase();
		// 取得值
		Object object = model.get("main").get(fieldName);
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		if("".equals(value)) return html;
		
		Document  doc = Jsoup.parse(html);
		Elements selectElement=doc.select("select");
		String sQuery=selectElement.attr("selectquery");
		if (sQuery!=null && sQuery!="") {
			String external=sQuery.replace("&#39;", "\"").replace("&quot;","\"");
			JSONObject jsonObject=null;
			try{
				jsonObject=JSONObject.fromObject(external);
				String search=jsonObject.getString("search");
				if ("1".equals(search)) {
					String temp="<span selectvalue="+value+" selectquery="+external+"><lable></lable></span>";
					return temp.replace("\"","&quot;");
				}else {
					Elements optElments = doc.select("option");
					if(optElments.size()>0){
						for(Element elment : optElments){
							String key = elment.attr("value");
							if(value.equals(key)){
								return elment.text();
							}
						}
					}
				}
			}
			catch(Exception ex){
				return value;
			}
			
			
		}else {
			Elements optElments = doc.select("option");
			if(optElments.size()>0){
				for(Element elment : optElments){
					String key = elment.attr("value");
					if(value.equals(key)){
						return elment.text();
					}
				}
			}
		}
		return html;
	}

	/**
	 * 在子表中选中下checkbox和radio
	 * 
	 * @param fieldName
	 * @param html
	 * @param ctlVal
	 * @param table
	 * @return
	 */
	public String getRdoChkBox(String fieldName, String html, String ctlVal,
			Map<String, Object> table) {
		String value = (String) table.get(fieldName.toLowerCase());

		html = getHtml(html, ctlVal, value);
		return html;

	}
	
	/**
	 * 获取子表的文件上传
	 * @param fieldName
	 * @param table
	 * @return
	 */
	public String getSubTableAttachMent(String fieldName, Map<String, Object> table) {
		String value = (String) table.get(fieldName.toLowerCase());
		return value;
	}
	
	/**
	 * 获取子表下拉框的内容
	 * @param fieldName
	 * @param html
	 * @param table
	 * @return
	 */
	public String getSubTableOptionValue(String fieldName, String html, Map<String, Object> table){
		String value = (String) table.get(fieldName.toLowerCase());
		Document  doc = Jsoup.parse(html);
		Elements selectElement=doc.select("select");
		String sQuery=selectElement.attr("selectquery");
		if (sQuery!=null && sQuery!="") {
			String external=sQuery.replace("&#39;", "\"").replace("&quot;","\"");
			JSONObject jsonObject=null;
			try{
				jsonObject=JSONObject.fromObject(external);
				String search=jsonObject.getString("search");
				if ("1".equals(search)) {
					String temp="<span selectvalue="+value+" selectquery="+external+"><lable></lable></span>";
					return temp.replace("\"","&quot;");
				}else {
					Elements optElments = doc.select("option");
					if(optElments.size()>0){
						for(Element elment : optElments){
							String key = elment.attr("value");
							if(value.equals(key)){
								return elment.text();
							}
						}
					}
				}
			}
			catch(Exception ex){
				return html;
			}
			
			
		}else {
			Elements optElments = doc.select("option");
			if(optElments.size()>0){
				for(Element elment : optElments){
					String key = elment.attr("value");
					if(value.equals(key)){
						return elment.text();
					}
				}
			}
		}
		return html;
	}

	/**
	 * 替换html。
	 * 
	 * @param html
	 *            html的代码
	 * @param ctlVal
	 *            控件代表的值。
	 * @param value
	 *            当前字段的值。
	 * @return
	 */
	private String getHtml(String html, String ctlVal, String value) {
		// 还没有选择任何的字段
		if (StringUtil.isEmpty(value)) {
			html = html.replaceAll("chk=\"?1\"?", "");
		} else {
			html = html.replace("checked=\"checked\"", "");
			if (value.contains(ctlVal)) {
				html = html.replaceAll("chk=\"?1\"?", "checked=\"checked\"");
			} else {
				html = html.replaceAll("chk=\"?1\"?", "");
			}
		}
		return html;
	}

	/**
	 * 转换必填的Html
	 * 
	 * @param html
	 * @return
	 */
	public static String toRequired(String html) {
		StringBuffer sb = new StringBuffer();
		try {
			Document doc = Jsoup.parse(html);
			Elements elList = doc.select("input,select,textarea");
			for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
				Element el = it.next();
				String validate = el.attr("validate");
	validate = validate.replace("'", "\'");
				String validateValue = "{'required':'true'}";
			//	String validateValue = "{required:true}";
				if (StringUtil.isNotEmpty(validate)) {
					JSONObject json = JSONObject.fromObject(validate);
					json.element("required", "true");
				//	validateValue = json.toString().replace("\"", "")
				//			.replace("\'", "");
					validateValue = json.toString().replace("\"", "'");
				}
				el.attr("validate", validateValue);
				el.attr("right", "b");
				sb.append(el);
			}
		} catch (Exception e) {
			return html;
		}
		return sb.toString();
	}
	
	
	/**
	 * 只读提交
	 * @param html
	 * @return
	 */
	public static String getReadPost(String html){
		StringBuffer sb = new StringBuffer();
		try {
			Document doc = Jsoup.parse(html);
			Elements elList = doc.select("input,select,textarea");
			for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
				Element el = it.next();
				el.addClass("hidden");
				el.attr("right", "rp");
				el.removeAttr("validate");
				sb.append(el);
			}
		} catch (Exception e) {
			return html;
		}
		return sb.toString();
	}
	
	/**
	 * 处理多行文本框预设值问题
	 * @param html
	 * @param string
	 * @param value
	 * @return
	 */
	private String dealTextAreaValue(String html, String val, String value) {
		if (StringUtil.isEmpty(value)) return html;
		Document doc = Jsoup.parse(html);
		Elements elList = doc.select("textarea");
		if(BeanUtils.isEmpty(elList)) return html;
		for(Element ele : elList){
			ele.html(val);
		}
		return elList.toString();
	}

}

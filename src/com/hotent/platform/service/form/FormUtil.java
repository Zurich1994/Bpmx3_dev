package com.hotent.platform.service.form;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.datakey.IKeyGenerator;
import com.hotent.core.datakey.impl.GuidGenerator;
import com.hotent.core.datakey.impl.IdentityGenerator;
import com.hotent.core.datakey.impl.TimeGenerator;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.util.FieldPool;

/**
 * 表单实用函数。
 * @author ray。
 *
 */
public class FormUtil {
	public static Map<String, Short> controlTypeMap=new HashMap<String, Short>();
	static {
		controlTypeMap.put("textinput",FieldPool.TEXT_INPUT);
		controlTypeMap.put("textarea",FieldPool.TEXTAREA);
		controlTypeMap.put("dictionary",FieldPool.DICTIONARY);
		controlTypeMap.put("user",FieldPool.SELECTOR_USER_SINGLE);
		controlTypeMap.put("rolepicker",FieldPool.SELECTOR_ROLE_MULTI);
		controlTypeMap.put("departmentpickerMulti",FieldPool.SELECTOR_ORG_MULTI);
		controlTypeMap.put("positionpicker", FieldPool.SELECTOR_POSITION_SINGLE);
		controlTypeMap.put("userMulti",FieldPool.SELECTOR_USER_MULTI);
		controlTypeMap.put("attachement",FieldPool.ATTACHEMENT);
		controlTypeMap.put("ckeditor",FieldPool.CKEDITOR);
		controlTypeMap.put("selectinput",FieldPool.SELECT_INPUT);
		controlTypeMap.put("officecontrol",FieldPool.OFFICE_CONTROL);
		controlTypeMap.put("checkbox",FieldPool.CHECKBOX);
		controlTypeMap.put("radioinput",FieldPool.RADIO_INPUT);
		controlTypeMap.put("datepicker",FieldPool.DATEPICKER);
		controlTypeMap.put("hidedomain",FieldPool.HIDEDOMAIN);
		controlTypeMap.put("rolepickerMulti",FieldPool.SELECTOR_ROLE_SINGLE);
		controlTypeMap.put("departmentpicker",FieldPool.SELECTOR_ORG_SINGLE);
		controlTypeMap.put("positionpickerMulti",FieldPool.SELECTOR_POSITION_MULTI);
		controlTypeMap.put("websigncontrol",FieldPool.WEBSIGN_CONTROL);   //xcx
		controlTypeMap.put("pictureshowcontrol",FieldPool.PICTURE_SHOW_CONTROL);   //xcx
		controlTypeMap.put("processinstance",FieldPool.SELECTOR_PROCESS_INSTANCE);
	}
	
	
	private static Log logger = LogFactory.getLog(FormUtil.class);
	//正则表达式,不必每次都产生正则表达式实例。
	private final static Pattern regex = Pattern.compile("\\[(.*?)\\]", Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	
	
	/**
	 * 计算脚本，并可根据其他字段进行运算。
	 * <pre>
	 * 注意脚本中一定要有return 语句。
	 * </pre>
	 * @param script 需要计算的脚本。
	 * @param map			数据类型。
	 * @param colPreFix		列前缀。
	 * @return	
	 */
	public static Object calcuteField( String script,Map<String,Object> map,String colPreFix){
		GroovyScriptEngine engine=(GroovyScriptEngine)AppUtil.getBean(GroovyScriptEngine.class);
		script=parseScript(script,map,colPreFix);
		return engine.executeObject(script, null);
	}
	
	/**
	 * 解析脚本。
	 * <pre>
	 * 功能：将脚本中的字段使用实际的值进行替换，返回实际的脚本。
	 * 实现机制：
	 * 查询脚本中如果有[字段名]，map中的相应的值进行替换。
	 * </pre>
	 * @param script 需要计算字段脚本。
	 * @param map 字段名称值的映射Map对象。
	 * @return
	 */
	private static String parseScript(String script,Map<String,Object> map,String colPreFix){
		if(map==null || map.size()==0) return script;
		
		Matcher regexMatcher = regex.matcher(script);
		while (regexMatcher.find()) {
			String tag=regexMatcher.group(0);
			//取得关联字段的值。
			String key=colPreFix + regexMatcher.group(1);
			String value=map.get(key).toString();
			script=script.replace(tag, value);
		} 
		return script;
	}
	
	/**
	 * 取得外键值。
	 * @param keyType
	 * @param alias
	 * @return
	 * @throws Exception 
	 */
	public static Object getKey(int keyType,String alias) throws Exception{
		IKeyGenerator generator=null;
		switch(keyType) {
			//guid
			case 1:
				generator=new GuidGenerator();
				break;
			//流水号
			case 2:
				generator=new IdentityGenerator();
				break;
			//时间序列
			case 3:
				generator=new TimeGenerator();
				break;
				
		}
		if(generator==null) return UniqueIdUtil.genId();
		generator.setAlias(alias);
		
		return generator.nextId();
	}
	
	/**
	 * 根据指定的名称，查找某个节点的父节点。
	 * @param node
	 * @param containerName
	 * @return
	 */
	private static Element getContainer(Element node,String containerName){
		Element parent=node;
		while ((parent = (Element) parent.parent()) != null){
			String name=parent.attr("name");
			if(containerName.equals(name)){
				return parent;
			}
		}
		return node;
	}
	

	
	/**
	 * 解析html表单数据
	 * @param html
	 * @param tableId
	 * @return
	 */
	public static String getFreeMarkerTemplate(String html,Long tableId){
		BpmFormTableService bpmFormTableService=(BpmFormTableService) AppUtil.getBean(BpmFormTableService.class);
		
		BpmFormTable bpmFormTable=bpmFormTableService.getById(tableId);
		
		Document doc= Jsoup.parseBodyFragment(html);
		//遍历主表字段
		parseMainField(doc);
		//处理子表。
		parseSubTable(doc,bpmFormTable.getTableId());
		//解析意见
		parseOpinion(doc);
		
		String rtn=doc.body().html();
		
		rtn=rtn.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		rtn = "<#setting number_format=\"#\">\n" + rtn;
		
		return rtn;
	}
	
	/**
	 * 处理主表字段
	 * @param doc
	 */
	private static void parseMainField(Document doc){
	//	Elements list=doc.select("input[name^=m:],textarea[name^=m:],select[name^=m:],a.link");
		Elements list = doc.select("input[name^=m:],textarea[name^=m:],select[name^=m:],a.link,a.extend");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "").toLowerCase();
			if(StringUtil.isEmpty(name)){//处理附件的bug
				name = el.attr("field");
				//不是子表
				if(StringUtil.isNotEmpty(name) && name.matches("m:(\\w*):(\\w*)")){
					fieldName =name.replaceAll("^.*:", "").toLowerCase();
				}
			}
			//解析模版，对模版进行复制复制和授权的处理。
			parseMainField(el, fieldName);	
		}
	}
	
	/**
	 *  解析意见。
	 * @param doc
	 */
	private static void parseOpinion(Document doc){
		parseOpinion(doc,null);
	}
	
	
	
	/**
	 * 解析意见。
	 * 
	 * @param doc
	 */
	private static void parseOpinion(Document doc,ParseReult result){
		//opinion:www
		Elements list=doc.select("[name^=opinion:]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			String memo=el.attr("opinionname");
			String opinionName=name.replaceFirst("^opinion:", "");
			//getOpinion(String opinionName, String html, Map<String, Map<String, Map>> model, Map<String, Map<String, Map>> permission)
			
			String str="&lt;#assign "+opinionName+"Opinion&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getOpinion('" +opinionName +"',"+opinionName+"Opinion, model, permission)}";
			
			el.before(str);
			el.remove();
			if(result!=null)
				result.addOpinion(opinionName, memo);
		}
	}
	
	/**
	 * 插入一个新行，用户数据列表显示，添加freemaker标签。
	 * 
	 * @param subTable
	 * @return
	 */
	private static Map<String,Object> handNewRow(Element subTable,String pkField,Document doc){
		if(StringUtil.isEmpty(pkField))
			pkField = "id";
		String tableName=subTable.attr("tableName").toLowerCase();
		//设置子表权限。
		subTable.attr("right", "${permission.table." + tableName+ "}");
		//查询编辑行
		Elements rows= subTable.select("[formtype=edit],[formtype=form]");
		if(rows.size()==0){
			logger.debug("no formtype row defined");
			return null;
		}
		Element row=rows.get(0);
		
		//增加子表
//		String idHtml="<input type='hidden' name='s:" + tableName + ":id' value='${table.id}' />"; 
		
		String name="s:" + tableName + ":" + pkField;
		
		Element elPkId= doc.createElement("input").attr("type", "hidden").attr("name", name).attr("value", "${table."+pkField+"}");
		
		row.appendChild(elPkId);
		
		String mode=row.attr("formtype");
		
		Element newRow=row.clone().attr("formtype","newrow");
		
		row.after(newRow);
		
		newRow.before("&lt;#if model.sub."+tableName+" != null&gt; &lt;#list model.sub."+tableName+".dataList as table&gt;");
		newRow.after("&lt;/#list> &lt;/#if&gt;");
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("newRow", newRow);
		map.put("mode", mode);
		
		return map;
	}
	
	/**
	 * 解析子表
	 * @param doc	:doc
	 * @param mainTableId	:主表id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	private static void parseSubTable(Document doc,Long mainTableId){
		BpmFormTableService bpmFormTableService=(BpmFormTableService)AppUtil.getBean(BpmFormTableService.class);
		
		Elements list=doc.select("div[type=subtable]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element subTable=it.next();
			String tableName=subTable.attr("tableName").toLowerCase();
			
			if(StringUtil.isEmpty(tableName)){
				logger.debug("subtable tableName is not specialed");
				continue;
			}
			String pkField="";
			//获取所有子表
			List<BpmFormTable> bpmFormTables=bpmFormTableService.getSubTableByMainTableId(mainTableId);
			for(BpmFormTable bft:bpmFormTables){
				//当子表名字跟表单内容的表名相同时就赋值
				if(!bft.getTableName().equals(tableName)) continue;
				pkField=bft.getPkField();
				continue;
			}
			
			
			Map<String,Object> map=handNewRow(subTable,pkField,doc);
			
			if(map==null) 
				continue;
			
			Element newRow=(Element)map.get("newRow");
			String mode=(String)map.get("mode");
			
			//行内编辑模式
			if(BpmFormDef.EditInline.equals(mode)){
				parseSubTableEditField(newRow);
				
			}
			//弹出窗口
			else if(BpmFormDef.EditForm.equals(mode)){
				Elements windowRows= subTable.select("[formtype=window]");
				if(windowRows.size()!=1){
					logger.debug("window mode hasn't window defined");
					return;
				}
				Element window=windowRows.get(0);
				parseSubTableFormField(doc,newRow,window);
			}
		}
	}
	
	/**
	 * 处理弹出窗口模式。
	 * @param doc
	 * @param newRow
	 * @param window
	 */
	private static void parseSubTableFormField(Document doc, Element newRow,Element window){
		Elements fields=newRow.select("[fieldname^=s:]");
		for(Iterator<Element> it= fields.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("fieldname");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "").toLowerCase();
			//<td fieldname="s:subtable:name">${table.name}</td>
			//示例添加 ${table.name}
			el.append("${table." + fieldName +"}");
		}
		//对form表单进行遍历，添加隐藏域。
		Elements windowFields=window.select("[name^=s:]");
		for(Iterator<Element> it= windowFields.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "").toLowerCase();
			Element appendTag=	doc.createElement("input").attr("type","hidden").attr("name", name);
			appendTag.attr("value", "${table." + fieldName +"}");
			newRow.children().last().after(appendTag);	
		}
	}
	
	/**
	 * 处理行内编辑情况。
	 * @param newRow
	 */
	private static void parseSubTableEditField(Element newRow){
		Elements fields=newRow.select("[name^=s:]");
		for(Iterator<Element> it= fields.iterator();it.hasNext();){
			Element el=it.next();
			handSubFieldValuePermission(el);
		}
		
		
	}

	/**
	 * 处理子表字段赋值和授权。
	 * @param el
	 */
	private static void handSubFieldValuePermission(Element el) {
		String nodeName=el.nodeName();
		String name=el.attr("name");
		String type=el.attr("type").toLowerCase();
		//获取字段名
		String fieldName=name.replaceAll("^.*:", "").toLowerCase();
		//文本框
		if("textarea".equals(nodeName)){
			el.append("${table." + fieldName +"}");
		}
		//复选框和radio
		else if("checkbox".equals(type) || "radio".equals(type)){
			el.attr("chk","1");
			String value=el.attr("value");
			String html = el.toString().replaceAll("&quot;", "'").replaceAll("'", "\\\\'");   //防止有'时HTML会出现乱嵌套 "'" 报错 所以要把  '  改成  \';
			el.before("${service.getRdoChkBox('" +fieldName +"', '" +html + "','"+value+"', table)}");
			el.remove();
		}
		//input表单和下拉框
		else if("select".equals(nodeName) ){
			el.attr("val","${table." + fieldName + "}");
		}else if( "input".equals(nodeName) ){
			el.attr("value","${table." + fieldName + "}");
		}
	}
	
	
	

	
	
	/**
	 * 返回模版物理的路径。
	 * @return
	 */
	public static String getDesignTemplatePath(){
		return FileUtil. getClassesPath() + "template" + File.separator +"design" + File.separator;
	}
	
	/**
	 * 返回按钮物理的路径。
	 * @return
	 */
	public static String getDesignButtonPath(){
		return FileUtil. getClassesPath() + "com" + File.separator + "hotent" 
				+ File.separator + "platform" + File.separator + "button" + File.separator;
	}
	
	
	
	

	/**
	 * 对输入的html进行解析。
	 * <pre>
	 * 	1.解析出表结构。
	 *  2.生成fremaker的模版。
	 *  
	 *  处理过程：
	 *  1.先处理子表的字段，处理子表字段后将html元素的external属性删除掉。
	 *  2.在处理主表字段。
	 *  3.处理意见模版。
	 *  
	 *  返回值为map：
	 *  键：bpmFormTable:表示为生成的表对象。
	 *  键：template：表示生成的模版。
	 * </pre>
	 * @param html
	 * @return
	 */
	public static ParseReult parseHtmlNoTable(String html,String tableName,String comment){
		ParseReult result=new ParseReult();
		Document doc= Jsoup.parseBodyFragment(html);
		//解析子表
		List<BpmFormTable>  subTableList= parseSubTableHtml(doc,result);
		//解析主表。
		BpmFormTable mainTable=parseMainTable(doc, tableName, comment,result);
		//设置子表。
		mainTable.setSubTableList(subTableList);
		//获取主表
		result.setBpmFormTable(mainTable);
		//处理意见权限及赋值处理。
		parseOpinion(doc,result);
		
		String template=doc.body().html();
		template=template.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "'");
		template = "<#setting number_format=\"#\">\r\n" + template;
		
		result.setTemplate(template);
		return result;
	}
	
	//更新发布表单页面未更新的html
	public static String changeHtml(String html, String json,String mainTableName){
		
		JSONObject jsonobject = JSONObject.fromObject(json);
		JSONArray mainRows = jsonobject.getJSONArray("rows");
		JSONArray jsonSubTables = jsonobject.getJSONArray("subTable");
		html = html.replace("'", "###");//处理html脚本中的单引号
		Document doc= Jsoup.parseBodyFragment(html);
		
		//获取和处理HTML主表的字段列表
		Elements allFields=doc.select("span[external]");		
		Elements subFields=doc.select("div[type=subtable] span[external]");		//所有子表字段
		//删除所有子表字段，剩下的就是主表字段
		allFields.removeAll(subFields);
		
		for(Element me:allFields){			
			//处理HTML主表的字段列表内容
			String external=me.attr("external").replace("&amp;#39;", "'").replace("&#39;", "'").replace("&quot", "\"");
			JSONObject jsonObject=JSONObject.fromObject(external);
			String name = jsonObject.getString("name");
			for (int j = 0; j < mainRows.size(); j++){
				JSONObject row = mainRows.getJSONObject(j);					
				if(name.equals(row.getString("fieldName"))){
					String temp=change(jsonObject,row);
					temp = temp.replace("\"", "&#39;");
					me.attr("external", temp);
					mainRows.remove(j);  //选中了的主表字段JSON，处理后就删除（避免大循环嵌套）
					break;
				}
			}
		}
		
		//获取和处理子表的HTML字段
		Elements subtables=doc.select("div[type=subtable]");         //子表
		for (Element sub : subtables){		  //HTML子表整个与子表JSON比较	
			String tableName = sub.attr("tablename");
			for (int i=0;i<jsonSubTables.size();i++ ){
				JSONObject obj = (JSONObject) jsonSubTables.get(i);
				String jsonTableName = obj.getString("tableName");
				if(tableName.equals(jsonTableName)){              //找到对应的子表JSON
					JSONArray subRows = obj.getJSONArray("rows");      //找到对应的子表字段的JSON
					Elements ses = sub.select("span[external]");                   //HTML中子表的字段
					for (Element se : ses){                                   //对应的HTML子表字段和子表字段的JSON比较
						String external=se.attr("external").replace("&amp;#39;", "'").replace("&#39;", "'").replace("&quot", "\"");
						JSONObject jsonObject = JSONObject.fromObject(external);
						String name = jsonObject.getString("name");
						for (int j = 0; j < subRows.size(); j++){
							JSONObject row = subRows.getJSONObject(j);					
							if(name.equals(row.getString("fieldName"))){
								String temp=change(jsonObject,row);
								temp = temp.replace("\"", "&#39;");
								se.attr("external", temp);
								subRows.remove(j);              //选中了的子表字段JSON，处理后就删除（避免后面会可拿去循环）
								break;
							}
						}
					}
					jsonSubTables.remove(i);      //选中了的子表JSON，处理后就删除（避免后面会可拿去循环）
					break;
				}
			}

		}
		
		String tempHtml = doc.body().html();
		return tempHtml.replace("'", "&#39;").replace("###", "'");
	}
	
	private static String change(JSONObject jsonObject, JSONObject row){
		//必填
		Object isRequired=(Object) row.get("isRequired");
		if(isRequired!=null){
			jsonObject=jsonObject.element("isRequired", Short.parseShort(isRequired.toString()));
		}
		//列表
		Object isList=(Object) row.get("isList");
		if(isList!=null){
			jsonObject=jsonObject.element("isList", Short.parseShort(isList.toString()));
		}
		//查询条件。
		Object isQuery=(Object) row.get("isQuery");
		if(isQuery!=null){
			jsonObject=jsonObject.element("isQuery", Short.parseShort(isQuery.toString()));
		}
		//流程变量
		Object isFlowVar=(Object) row.get("isFlowVar");
		if(isFlowVar!=null){
			jsonObject=jsonObject.element("isFlowVar", Short.parseShort(isFlowVar.toString()));
		}
		//web印章验证
		Object isWebSign=(Object) row.get("isWebSign");
		if(isWebSign!=null){
			jsonObject=jsonObject.element("isWebSign", Short.parseShort(isWebSign.toString()));
		}
		return jsonObject.toString();
	}
	
	public static void main(String[] args) {
		String str=FileUtil.readFile("E:\\work\\bpm\\src\\main\\webapp\\test.jsp");
		ParseReult map= parseHtmlNoTable(str,"test","测试表");
		BpmFormTable bpmFormTable=map.getBpmFormTable();
		for(Iterator<BpmFormField> it=bpmFormTable.getFieldList().iterator();it.hasNext();){
			BpmFormField bpmFormField=it.next();
			logger.info(bpmFormField.getFieldName() +"," + bpmFormField.getFieldType());
		}
		String template=map.getTemplate();
		logger.info(template);
		String options="中共党员 #newline#中共预备党员#newline#共青团员#newline#民革党员#newline#民盟盟员";
		options =options.replace("#newline#", "\r\n");
		logger.info(options);
	}
	
	/**
	 * 解析主表。
	 * <pre>
	 * 1.解析主表的字段。
	 * 2.将容器类的控件进行解析。
	 * 3.解析主表字段授权和值的处理。
	 * 4.删除&lt;span name="editable-input" ...>&lt;/span>
	 * </pre>
	 * @param doc
	 * @return
	 */
	public static BpmFormTable parseMainTable(Document doc ,String tableName,String comment,ParseReult result){
		BpmFormTable bpmFormTable=new BpmFormTable();
		
		bpmFormTable.setTableName(tableName);
		bpmFormTable.setTableDesc(comment);
		bpmFormTable.setIsMain((short)1);
		bpmFormTable.setGenByForm(1);
		//获取主表中有external属性的控件集合。
		Elements mainFields=doc.select("[external]");
		
		if(mainFields.size()==0){
			result.addError("主表【"+tableName +"," +comment+"】还未定义任何字段");
			return bpmFormTable;
		}
	
		for(Iterator<Element> it= mainFields.iterator();it.hasNext();){
			Element el=it.next();
			//从html中解析出字段。
			BpmFormField bpmFormField= parseExternal(el,result);
			
			if(bpmFormField==null){
				continue;
			}
			
			String controlName="m:" + tableName +":" +bpmFormField.getFieldName();
			
			boolean rtn= bpmFormTable.addField(bpmFormField);
			if(!rtn){
				String error = "表单中主表:【" +tableName +"】，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				if(StringUtil.isEmpty(tableName)){
					error = "主表中，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				}
				result.addError(error);
				continue;
			}
			
			//整理html。
			List<Pair> list=parseChilds( doc,el,controlName,bpmFormField);
			
			for(Iterator<Pair> tmpIt=list.iterator();tmpIt.hasNext();){
				Pair pair=tmpIt.next();
				String error=parseMainField(pair.getEl(), pair.getFieldName());
				if(StringUtil.isNotEmpty(error)){
					result.addError(error);
				}
			}
			//删除包装。
			removeWrap(el);
		}
		return bpmFormTable;
	}
	
	
	/**
	 * @param doc
	 * @param parentEl
	 * @param controlName
	 * @param bpmFormField
	 * @return
	 */
	private static List<Pair> parseChilds(Document doc,Element parentEl,String controlName,BpmFormField bpmFormField){
		List<Pair> list=new ArrayList<Pair>();
		Elements elList=parentEl.select("input,select,textarea");
		for(Iterator<Element> it=elList.iterator();it.hasNext();){
			Element el=it.next();
			Pair pair=new Pair(el,bpmFormField.getFieldName());
			list.add(pair);
			short controlType=bpmFormField.getControlType();
			String fieldType=bpmFormField.getFieldType();
			el.attr("name", controlName);
			el.attr("lablename", bpmFormField.getFieldDesc());    //字段的显示标识或名称  说明
			
			//
			short isReference=bpmFormField.getIsReference();
			if (isReference==1) {
				el.attr("linktype", String.valueOf(controlType));
				el.attr("refid", controlName+"ID");
			}
			
			
			//验证
			String valid=handValidate(bpmFormField);
			if(StringUtil.isNotEmpty(valid)){
				String validate = el.attr("validate");   //原来的对象 是否有 validate 这个属性
				if(StringUtil.isNotEmpty(validate)){
					Pattern p = Pattern.compile("(\\{)|(\\})");   //如果有validate这个属性那么就要把两个validate对象合并成一个对象
					Matcher m = p.matcher(valid);
					Matcher n = p.matcher(validate);
					valid = "{"+ m.replaceAll("")+","+n.replaceAll("")+"}";
				}
				el.attr("validate", valid);
			}
			if(BeanUtils.isNotEmpty(bpmFormField.getStyle())){
			       el.attr("style", bpmFormField.getStyle());
			}
			
			Map<String,String> propertyMap=bpmFormField.getPropertyMap();
			switch(controlType){
			//文字类型
			case FieldPool.TEXT_INPUT:
				el.wrap("<span></span>");
				if("varchar".equals(fieldType)){
					if(!((short)1==bpmFormField.getIsDateString())){
						break;
					}
				}else if("number".equals(fieldType)){
					//TODO 加数字类型处理					
					Short isShowComdify=bpmFormField.getIsShowComdify();
					String coinValue=bpmFormField.getCoinValue();
					Integer decimalLen=bpmFormField.getDecimalLen();
					el.attr("showtype", "{\"coinValue\":\""+coinValue+"\",\"isShowComdify\":\""+isShowComdify+"\",\"decimalValue\":"+decimalLen+"}");
					break;
				}
				else if(!"date".equals(fieldType)) break;
				el.addClass("Wdate");
			//	Map<String,String> optionsMap=bpmFormField.getPropertyMap();
				String dateformat="yyyy-MM-dd";
				if(propertyMap.containsKey("format")){
					dateformat= propertyMap.get("format");
				}
				if(propertyMap.containsKey("displayDate")){
					el.attr("displayDate", propertyMap.get("displayDate"));
				}
				el.attr("dateFmt", dateformat);        
				break;
			//数据字典。
			case FieldPool.DICTIONARY:
					el.attr("class", "dicComboTree");
					el.attr("nodeKey", bpmFormField.getDictType());
					el.wrap("<span></span>");
					break;
		
			//单选用户
			case FieldPool.SELECTOR_USER_SINGLE:
				if(propertyMap.containsKey("showCurUser")){
					el.attr("showCurUser", propertyMap.get("showCurUser"));
				}
			//角色选择
			case FieldPool.SELECTOR_ROLE_MULTI:
			//部门
			case FieldPool.SELECTOR_ORG_SINGLE:
				if(propertyMap.containsKey("showCurOrg")){
					el.attr("showCurOrg", propertyMap.get("showCurOrg"));
				}
			//岗位
			case FieldPool.SELECTOR_POSITION_MULTI:
					el.wrap("<span></span>");
					Element elInput= doc.createElement("input")
							.attr("name", controlName+"ID")
							.attr("type", "hidden")
							.attr("class", "hidden");
					
					el.before(elInput);
					
					Pair pairSelect = new Pair(elInput,bpmFormField.getFieldName() +"ID");
					list.add(pairSelect);
					//parseMainField(elInput, bpmFormField.getFieldName() +"id");
					el.attr("readonly", "readonly");
					Elements links= parentEl.select("a.link");
					if(links.size()>0){
						Element link=links.get(0);
						link.attr("name", controlName);
						el.after(link);
						Pair pLink = new Pair(link,bpmFormField.getFieldName());
						list.add(pLink);
					}					
					break;
			//多选用户
			case FieldPool.SELECTOR_USER_MULTI:
					el.wrap("<span></span>");
					Element elUsers= doc.createElement("input")
							.attr("name", controlName+"ID")
							.attr("type", "hidden")
							.attr("class", "hidden");
					
					Pair pairUsers=new Pair(elUsers,bpmFormField.getFieldName() +"ID");
					list.add(pairUsers);
					
					el.before(elUsers);
					//parseMainField(elUsers, bpmFormField.getFieldName() +"id");
					el.attr("readonly", "readonly");
					Elements linkUsers= parentEl.select("a.link");
					if(linkUsers.size()>0){
						Element link=linkUsers.get(0);
						link.attr("name", controlName);
						link.removeClass("user");
						link.addClass("users");
						el.after(link);
						Pair pLink = new Pair(link,bpmFormField.getFieldName());
						list.add(pLink);
					}					
					break;
			//单选角色
			case FieldPool.SELECTOR_ROLE_SINGLE:
					el.wrap("<span></span>");
					Element elRoles= doc.createElement("input")
							.attr("name", controlName+"ID")
							.attr("type", "hidden")
							.attr("class", "hidden");
					
					Pair pairRoles=new Pair(elRoles,bpmFormField.getFieldName() +"ID");
					list.add(pairRoles);
					
					el.before(elRoles);
					//parseMainField(elUsers, bpmFormField.getFieldName() +"id");
					el.attr("readonly", "readonly");
					Elements linkRoles= parentEl.select("a.link");
					if(linkRoles.size()>0){
						Element link=linkRoles.get(0);
						link.attr("name", controlName);
						link.removeClass("role");
						link.addClass("roles");
						el.after(link);
						Pair pLink = new Pair(link,bpmFormField.getFieldName());
						list.add(pLink);
					}					
					break;
				//多选组织
				case FieldPool.SELECTOR_ORG_MULTI:
					el.wrap("<span></span>");
					Element elOrgs= doc.createElement("input")
							.attr("name", controlName+"ID")
							.attr("type", "hidden")
							.attr("class", "hidden");
					
					Pair pairOrgs=new Pair(elOrgs,bpmFormField.getFieldName() +"ID");
					list.add(pairOrgs);
					
					el.before(elOrgs);
					//parseMainField(elUsers, bpmFormField.getFieldName() +"id");
					el.attr("readonly", "readonly");
					Elements linkOrgs= parentEl.select("a.link");
					if(linkOrgs.size()>0){
						Element link=linkOrgs.get(0);
						link.attr("name", controlName);
						link.removeClass("org");
						link.addClass("orgs");
						el.after(link);
						Pair pLink = new Pair(link,bpmFormField.getFieldName());
						list.add(pLink);
					}					
					break;
				//单选岗位
				case FieldPool.SELECTOR_POSITION_SINGLE:
					el.wrap("<span></span>");
					Element elPositions= doc.createElement("input")
							.attr("name", controlName+"ID")
							.attr("type", "hidden")
							.attr("class", "hidden");
					
			//		Map<String,String> map=bpmFormField.getPropertyMap();
					if(propertyMap.containsKey("showCurPos")){
						el.attr("showCurPos", propertyMap.get("showCurPos"));
					}
					
					Pair pairPositions=new Pair(elPositions,bpmFormField.getFieldName() +"ID");
					list.add(pairPositions);
					
					el.before(elPositions);
					//parseMainField(elUsers, bpmFormField.getFieldName() +"id");
					el.attr("readonly", "readonly");
					Elements linkPositions= parentEl.select("a.link");
					if(linkPositions.size()>0){
						Element link=linkPositions.get(0);
						link.attr("name", controlName);
						link.removeClass("position");
						link.addClass("positions");
						el.after(link);
						Pair pLink = new Pair(link,bpmFormField.getFieldName());
						list.add(pLink);
					}					
					break;
				//附件
				case FieldPool.ATTACHEMENT:
					el.tagName("textarea");
					el.removeAttr("type");
					el.removeAttr("validate");
					el.wrap("<div name='div_attachment_container' ></div>");
					el.before("<div  class='attachement' ></div>");
					el.attr("controltype","attachment");
					el.attr("style", "display:none;");
					
					Elements linkFile= parentEl.select("a.attachement");
					if(linkFile.size()>0){
						Element link=linkFile.get(0);
						link.attr("field", controlName);
						link.attr("validate", valid);
						
						String ctlProperty=bpmFormField.getCtlProperty();
						JSONObject jsonObj=null;
						if(StringUtil.isNotEmpty(ctlProperty)){
							jsonObj=JSONObject.fromObject(ctlProperty);
							if(jsonObj.has("directUpLoad")){
								if(jsonObj.get("directUpLoad").toString().equals("1")){
									link.attr("onclick", "AttachMent.directUpLoadFile(this);");
								}else{
									link.attr("onclick", "AttachMent.addFile(this);");
								}
							}
							else {
								link.attr("onclick", "AttachMent.addFile(this);");
							}
						}
						else{
							link.attr("onclick", "AttachMent.addFile(this);");
						}	
						link.removeClass("attachement").addClass("selectFile");
						el.after(link);
					}
					else{
						el.after("<a href='#' field='"+controlName+"' validate='"+valid+"' class='link selectFile' onclick='AttachMent.addFile(this);'>选择</a>");
					}
					break;
				case 10:
					el.attr("class", "ckeditor");
					break;
				//复选框
				case FieldPool.CHECKBOX:
				//单选按钮
				case FieldPool.RADIO_INPUT:
					break;
				//office 控件
				//officecontrol
				case FieldPool.OFFICE_CONTROL:
					el.attr("type", "hidden");
					el.attr("class", "hidden");
					el.attr("controltype", "office");
					
					String html="<div id='div_" +controlName.replace(":","_")+"'" ;
					String style=el.attr("style");
					if(StringUtil.isNotEmpty(style)){
						html+=" style='" + style +"' ";
					}
					html+=" class='office-div'></div>";
					el.after(html);

					break;
				//日期类型
				case FieldPool.DATEPICKER:
					el.wrap("<span></span>");
					el.addClass("Wdate");
			//		Map<String,String> map =bpmFormField.getPropertyMap();
					String format="yyyy-MM-dd";
					if(propertyMap.containsKey("format")){
						format=propertyMap.get("format");
					}
					if(propertyMap.containsKey("displayDate")){
						el.attr("displayDate", propertyMap.get("displayDate"));
					}
					el.attr("dateFmt", format);
					break;
				//隐藏域
				case FieldPool.HIDEDOMAIN:
					el.wrap("<span></span>");
					el.attr("type", "hidden");
					break;
				//websigncontrol   WEB印章控件
				case FieldPool.WEBSIGN_CONTROL:
					el.attr("type", "hidden");
					el.attr("class", "hidden");
					el.attr("controltype", "webSign");
					
					html="<div id='div_" +controlName.replace(":","_")+"'" ;
					style=el.attr("style");
					if(StringUtil.isNotEmpty(style)){
						html+=" style='" + style +"' ";
					}
					html+=" class='webSign-div'></div>";
					el.after(html);
					break;
				//pictureshowcontrol   图片展示控件
				case FieldPool.PICTURE_SHOW_CONTROL:
					el.attr("type", "hidden");
					el.attr("class", "hidden");
					el.attr("controltype", "pictureShow");
					html = "<div id='div_" +controlName.replace(":","_")+"'  class='pictureShow-div' ";
					style=el.attr("style");
					if(StringUtil.isNotEmpty(style)){
						html += " style='" + style +"' ";
					}
					html += " >  ";
					html += " <div id='div_" +controlName.replace(":","_")+"_container' ></div>  ";
					html += "   <table id='pictureShow_"+controlName+"_Toolbar'>";
					html += "     <tr>";
					html += "       <td width='80'>";
					html += " 	       <a href='javascript:;' field='"+controlName+"' class='link selectFile' onclick='{PictureShowPlugin.upLoadPictureFile(this);}' >上传图片</a> ";
					html += "       </td>";
					html += "       <td width='80'>";
					html += " 	       <a href='javascript:;' field='"+controlName+"' class='link del' onclick='{PictureShowPlugin.deletePictureFile(this);}' >删除图片</a> ";
					html += "       </td>";
					html += "     </tr>";
					html += "   <table>";
					html += " </div>  ";
					el.removeAttr("style");
					el.before(html);
					break;
				case FieldPool.SELECTOR_PROCESS_INSTANCE:
					el.wrap("<span></span>");
					Element elProcessInstance= doc.createElement("input").attr("name", controlName+"ID").attr("type", "hidden").attr("class", "hidden");
					Pair pairProcessInstance=new Pair(elProcessInstance,bpmFormField.getFieldName() +"ID");			
				    list.add(pairProcessInstance);
					el.before(elProcessInstance);
					el.attr("readonly", "readonly");
					break;
				default:
					el.wrap("<span></span>");
					break;
			}
		}
		return list;
		
	}
	
	
	
	/**
	 * 移除外围span对象。
	 * <pre>
	 * &lt;span style="display:inline-block" name="editable-input"><input type="text" external="" />&lt;/span>
	 * 此方法删除外围name为editable-input的span对象。
	 * </pre>
	 * @param el
	 */
	private static void removeWrap(Element parentEl){
		
		for(Iterator< Element> it=parentEl.children().iterator();it.hasNext();){
			Node elClone=it.next();
			parentEl.before(elClone);
		}
		parentEl.remove();
		
	}

	/**
	 * 处理主表字段的权限计算和对控件赋值。
	 * @param el
	 * @param fieldName
	 */
	private static String parseMainField(Element el, String fieldName) {
		String controltype=el.attr("controltype");
		//控件类型
		String type=el.attr("type").toLowerCase();
		//附件的处理方式
		if("attachment".equalsIgnoreCase(controltype)){
			Element parent=getContainer(el, "div_attachment_container");
			parent.attr("right", "${service.getFieldRight('" +fieldName+ "',  permission)}");
			
			el.val("${service.getFieldValue('" + fieldName+ "',model)}");
		}		
		
		//pictureShow控件的处理
		//设置pictureShow的值和权限。
		else if("pictureShow".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model,'" + controltype+ "')}");
			//设置权限。
			el.attr("right","${service.getFieldRight('" +fieldName+ "',  permission)}");
		}	
		
		//webSign控件的处理
		//设置webSign的值和权限。
		else if("webSign".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model)}");
			//设置权限。
			el.attr("right","${service.getFieldRight('" +fieldName+ "',  permission)}");
		}	
		
		//office控件的处理
		//设置office的值和权限。
		else if("office".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model)}");
			//设置权限。
			el.attr("right","${service.getFieldRight('" +fieldName+ "',  permission)}");
			//设置工具栏目权限
			el.attr("menuRight","${service.getFieldMenuRight('" +fieldName+ "',  permission)}");
		}
		//checkbox和radio的处理
		//checkbox和radio的必须为以下格式
		//<label><input type='checkbox' value='JAVA' />JAVA</label>
		//<label><input type='checkbox' value='JQUERY' />JQUERY</label>
		else if ("checkbox".equalsIgnoreCase(type) || "radio".equalsIgnoreCase(type)){
			String value=el.attr("value");
			//给checkbox设置是否chk和disabled属性。
			//用户在模版中取值。
			el.attr("chk","1").attr("disabled", "disabled");
			Element elParent=el.parent();
			String parentNodeName=elParent.nodeName();
			if(!parentNodeName.equals("label")){
				return fieldName +"的html代码必须为<label><input type='checkbox|radio' value='是'/>是</label>的形式";
			}
			//将html赋值给一个变量，在使用service.getRdoChkBox 方法做解析
			//如果外层元素是label，就把<#assign fieldName><label><input type='checkbox' value='' /></label></#assign>当成一个整体进行处理。
			String tmp=parentNodeName.equals("label")?elParent.toString() :el.toString();
			
			String str="<span>&lt;#assign "+fieldName+"Html&gt;"+tmp+" &lt;/#assign&gt;" +
					"\r\n${service.getRdoChkBox('" +fieldName +"', "+fieldName+"Html,'"+value+"', model, permission)}</span>";
			elParent.before(str);
			elParent.remove();
		}
		//多行文本
		else if(el.nodeName().equalsIgnoreCase("textarea")){
			el.append("#value");
			String str="<span>&lt;#assign "+fieldName+"Html&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getField('" +fieldName +"',"+fieldName+"Html, model, permission)}</span>";
			el.before(str);
			el.remove();
		} 
		//处理文本输入框
		else if(el.nodeName().equalsIgnoreCase("input")){
			el.attr("value","#value");	
			//处理选择器的的情况
		    String ctlType = el.attr("ctlType").toLowerCase();
			if("selector".equalsIgnoreCase(ctlType)){
				el.attr("initvalue","${service.getFieldValue('" +fieldName+ "id', model)}");
			}
			String str="&lt;#assign "+fieldName+"Html&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getField('" +fieldName +"',"+fieldName+"Html, model, permission)}";
			//隐藏的文本框在只读权限下面不返回value
			if("hidden".equalsIgnoreCase(type)){
				str="&lt;#assign "+fieldName+"Html&gt;"+el.toString()+" &lt;/#assign&gt;"+
						"\r\n${service.getHiddenField('" +fieldName +"',"+fieldName+"Html, model, permission)}";
			}
			el.before(str);
			el.remove();
		}
		//下拉框
		else if(el.nodeName().equalsIgnoreCase("select")){
			el.attr("val","#value");
			String str="&lt;#assign "+fieldName+"Html&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getField('" +fieldName +"',"+fieldName+"Html, model, permission)}";
			el.before(str);
			el.remove();
		}
		//处理选择器的a标签
		else if(el.nodeName().equalsIgnoreCase("a")){
			String str="&lt;#assign "+fieldName+"Html&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getLink('" +fieldName +"',"+fieldName+"Html, model, permission)}";
			el.before(str);
			el.remove();
		}
		return "";
	}
	
	

	/**
	 * 解析子表。(在线编辑)
	 * <pre>
	 * 1.解析&lt;div type='subtable' tablename='' comment=''>&lt;/div>
	 * 2.解析 formtype属性的元素。
	 * 	edit：页内编辑。
	 *  form：弹出窗口编辑。
	 * 3.解析formtype内的有external属性的元素。
	 * 	1.获取字段BpmFormField。
	 *  2.将formtype中的元素进行解析html元素。
	 *  	&lt;span name="editable-input" style="display:inline-block;" class="personpicker" 
	 *  	external="{name:&#39;username&#39;,comment:&#39;用户姓名&#39;,dbType:{type:&#39;varchar&#39;,length:&#39;50&#39;},isRequired:1,isList:1,isQuery:1,isFlowVar:1,valueFrom:{value:&#39;0&#39;,content:&#39;无&#39;},buttoncontent:&#39;选择用户&#39;,singleselect:1}">
	 *  	&lt;input type="text" />&lt;a href="javascript:;" class="link user">选择用户&lt;/a>&lt;/span>
	 *  	
	 *  	解析为：
	 *  	&lt;div>
	 *  	&lt;input name="s:表名:表字段ID" type="hidden" class="hidden" value="">
	 *  	&lt;input type="text" name='s:表名:表字段' />&lt;a href="javascript:;" name='s:表名:表字段'  class="link user">选择用户&lt;/a>
	 *  	&lt;/div>
	 *  3.将formtype复制一份作为循环列表。
	 *  	&lt;#if model.sub.子表名 != null&gt; &lt;#list model.sub.子表名.dataList as table&gt;
	 *  	&lt;tr formtype='newrow'>
	 *  	&lt;/tr>
	 *  	&lt;/#list> &lt;/#if&gt;
	 *  4.对新行的元素复制。
	 * </pre>
	 * @param doc
	 * @return
	 */
	public static List<BpmFormTable> parseSubTableHtml(Document doc ,ParseReult result){
		List<BpmFormTable> subList=new ArrayList<BpmFormTable>();
		Elements list=doc.select("div[type=subtable]");
		
		for(Iterator<Element> it=list.iterator();it.hasNext();){
			Element subTable=it.next();
			//如果子表设置了external属性，则先删除external属性。
			if(subTable.hasAttr("external")){
				subTable.removeAttr("external");
				subTable.removeClass("subtable");
			}
			
			BpmFormTable table=new BpmFormTable();
			//设置子表。
			String tableName=subTable.attr("tablename").toLowerCase();
			if(StringUtil.isEmpty(tableName)){
				 result.addError("有子表对象没有设置表名。");
				continue;
			}
			String comment=subTable.attr("tabledesc");
			
			table.setTableName(tableName);
			table.setTableDesc(comment);
			table.setIsMain((short)0);
			table.setGenByForm(1);
			subList.add(table);
			
			
			//设置子表权限。
			subTable.attr("right", "${service.getSubTablePermission('"+tableName+"', permission)}");
			//查询编辑行
			Elements rows= subTable.select("[formtype=edit],[formtype=form]");
			if(rows.size()==0){
				logger.debug("no formtype row defined");
				result.addError("子表【"+tableName+"】没有定义属性【formtype】。");
				continue;
			}
			//原行
			Element row=rows.get(0);
			
			//取得行编辑模式。
			//edit:页内编辑模式
			//form:弹窗编辑模式 
			String mode=row.attr("formtype");
			
			//行内编辑模式
			if(BpmFormDef.EditInline.equals(mode)){
				parseSubTableEditField(doc,row,table,result);
			}
			//添加主键ID。
			row.appendElement("input").attr("name", "s:" +tableName+ ":id").attr("type", "hidden").attr("value", "${table.id}");
			//新行
			Element newRow=row.clone().attr("formtype","newrow");
			
			row.after(newRow);
						
			newRow.before("&lt;#if model.sub."+tableName+" != null&gt; &lt;#list model.sub."+tableName+".dataList as table&gt;");
			newRow.after("&lt;/#list> &lt;/#if&gt;");
			
			if(BpmFormDef.EditInline.equals(mode)){
				parseSubTableEditField(newRow);
			}
			//弹出窗口
			else if(BpmFormDef.EditForm.equals(mode)){
				Elements windowRows= subTable.select("[formtype=window]");
				if(windowRows.size()!=1){
					logger.debug("window mode hasn't window defined");
					result.addError("在弹出窗口模式下，子表【"+tableName+"】没有设置window属性的对象。");
					continue;
				}
				Element window=windowRows.get(0);
				parseSubTableFormField(doc,row,newRow,window,table,result);
			}
		
		}
		return subList;
	}
	
	
	/**
	 * 解析弹出窗口。
	 * @param doc		文档
	 * @param newRow	新行
	 * @param window	window窗口对象
	 * @param table		自定义表
	 */
	private static void parseSubTableFormField(Document doc,Element row, Element newRow,Element window,BpmFormTable table,ParseReult result){
		String tableName=table.getTableName();
		Elements rowFields=row.select("[fieldname]");
		if(rowFields.size()==0){
			result.addError("表:" +table.getTableName() +"," +table.getTableDesc()+ ",弹窗编辑模式，显示行没有定义任何字段");
			return ;
		}
		//原行
		for(Iterator<Element> it= rowFields.iterator();it.hasNext();){
			Element el=it.next();
			String fieldname=el.attr("fieldname").toLowerCase();
			//修改fieldname。
			el.attr("fieldname","s:" + tableName +":" + fieldname);
		}
		//新行
		Elements newRowfields=newRow.select("[fieldname]");
		for(Iterator<Element> it= newRowfields.iterator();it.hasNext();){
			Element el=it.next();
			String fieldname=el.attr("fieldname").toLowerCase();
			//修改fieldname。
			el.attr("fieldname","s:" + tableName +":" + fieldname);
			//<td fieldname="name">${table.name}</td>
			//示例添加 ${table.name}
			el.append("${table." + fieldname +"}");
		}
		//对form表单进行遍历，在newrow添加隐藏域。
		Elements windowFields=window.select("[external]");
		
		if(rowFields.size()==0){
			result.addError("表:" +table.getTableName() +"," +table.getTableDesc()+ ",弹窗编辑模式，窗口没有定义任何字段");
			return ;
		}
		for(Iterator<Element> it= windowFields.iterator();it.hasNext();){
			Element el=it.next();
			
			//解析字段。
			BpmFormField bpmFormField= parseExternal(el,result);
			if(bpmFormField==null){
				continue;
			}
			boolean rtn= table.addField(bpmFormField);
			
			String fieldName=bpmFormField.getFieldName();
			
			if(!rtn){
				String error = "表单中子表:【" +tableName +"】，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				if(StringUtil.isEmpty(tableName)){
					error = "子表中，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				}
				result.addError(error);
				continue;
			}
			
			
			//获取字段名
			//在newrow中添加隐藏字段<input type="hidden" name="s:表名:字段名" value="${table.字段名}"/>
			
			String name="s:" + tableName +":" + fieldName;
			Element appendTag=	doc.createElement("input")
					.attr("type","hidden")
					.attr("name", name)
					.attr("value", "${table." + fieldName +"}");
			newRow.children().last().after(appendTag);	
			
			parseChilds(doc, el, name, bpmFormField);	
			//删除span 
			removeWrap(el);
		}
	}
	
	/**
	 * 解析行内模式。
	 * <pre>
	 * 1.解析字段。
	 * 2.将控件解析符合自定义表单的模式。
	 * 3.删除&lt;span name="editable-input>&lt;/span>
	 * </pre>
	 * @param doc
	 * @param newRow
	 * @param table
	 * @param result
	 */
	private static void parseSubTableEditField(Document doc, Element newRow,BpmFormTable table,ParseReult result){
		Elements fields=newRow.select("[external]");
		String tableName=table.getTableName();
		if(fields.size()==0){
			result.addError("子表【"+tableName+"," +table.getTableDesc()+"】尚未定义任何字段");
			return;
		}
		for(Iterator<Element> it= fields.iterator();it.hasNext();){
			Element el=it.next();
			//解析字段。
			BpmFormField bpmFormField= parseExternal(el, result);
			
			boolean rtn= table.addField(bpmFormField);
			
			String fieldName=bpmFormField.getFieldName();
			
			if(!rtn){
				String error = "表单中子表:【" +tableName +"】，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				if(StringUtil.isEmpty(tableName)){
					error = "子表中，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				}
				result.addError(error);
				continue;
			}
			
			String controlName="s:" + tableName + ":" + fieldName;
			parseChilds(doc,el,controlName,bpmFormField);
			//删除父节点
			removeWrap(el);
		}
	}
	
	
	
	/**
	 * 生成字段。
	 * <pre>
	 * 对字段的external属性进行解析。
	 * 
	 * external 为一个json对象。
	 * 
	 * 存储字段类型，备注，值来源，时间格式，选项，条件等信息。
	 * </pre>
	 * @param table
	 * @param fieldName
	 * @param external
	 */
	private static BpmFormField parseExternal(Element el,ParseReult result){
		
		BpmFormField bpmFormField=new BpmFormField();
		
		String external=el.attr("external").replace("&#39;", "\"").replace("&quot;","\"");
		List<Node> childNodes = el.childNodes();
		String defValue = "";
		for (Node node : childNodes) {
			defValue = node.attr("value");
		}
		bpmFormField.setDefValue(defValue);
		//移除external属性。
		el.removeAttr("external");
		JSONObject jsonObject=null;
		try{
			jsonObject=JSONObject.fromObject(external);
		}
		catch(Exception ex){
			result.addError(external +"错误的JSON格式!");
			return null;
		}
		
		//获取字段名
		String fieldName=jsonObject.getString("name");
		
		if(StringUtil.isEmpty(fieldName)){
			result.addError(external +"没有定义字段名");
			return null;
		}
			
		bpmFormField.setFieldName(fieldName);

		//字段类型
		JSONObject dbType=(JSONObject) jsonObject.get("dbType");
		
		if(dbType==null){
			result.addError(fieldName +",没有定义字段类型。");
			return null;
		}
		//TODO
		
		//处理字段类型。
		handFieldType(dbType,bpmFormField,result);
		//注释
		String comment=(String)jsonObject.get("comment");
		bpmFormField.setFieldDesc((comment==null)?fieldName:comment);
		//验证规则
//		String validRule=(String)jsonObject.get("validRule");
//		bpmFormField.setValidRule(validRule==null?"":validRule);
		handStyle(jsonObject,bpmFormField);
		//dictType,字典类型
		handDictType(jsonObject,bpmFormField);
		//值来源处理
		handValueFrom(jsonObject,bpmFormField);
		//处理选项。
		handOption(jsonObject,bpmFormField);
		//处理条件
		//handCondition(jsonObject,bpmFormField);
		//获取控件类型。
		short controlType=handControlType( el, jsonObject);
		
		bpmFormField.setControlType(controlType);
		
		handOptions(jsonObject,bpmFormField);
		
		handFormUser(jsonObject,bpmFormField);
		
		// 人员选择器显示当前用户
		handShowCurUser(jsonObject,bpmFormField);
		
		// 组织选择器显示当前组织
		handShowCurOrg(jsonObject,bpmFormField);
		// 附件直接文件上传
		handAttachMent(jsonObject,bpmFormField);
		
		//处理千分位和货币问题
		handComdifyAndCoin(jsonObject,bpmFormField);
	
		return bpmFormField;
	}
	
	/**
	 * 处理人员，组织，岗位选择器的限定范围
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handOrgUserScope(JSONObject jsonObject,BpmFormField bpmFormField) {
		if(bpmFormField.getControlType()==FieldPool.SELECTOR_ORG_SINGLE||bpmFormField.getControlType() ==FieldPool.SELECTOR_ORG_MULTI||
		bpmFormField.getControlType() == FieldPool.SELECTOR_USER_MULTI ||bpmFormField.getControlType() == FieldPool.SELECTOR_USER_SINGLE ||
		bpmFormField.getControlType() == FieldPool.SELECTOR_POSITION_MULTI ||bpmFormField.getControlType() == FieldPool.SELECTOR_POSITION_SINGLE ){
			JSONObject jsonObj=null;
			String ctlProperty=bpmFormField.getCtlProperty();
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}	
			Object scope=jsonObject.get("scope");
			if(BeanUtils.isNotEmpty(scope)){
				jsonObj.element("scope", scope);
			}
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
	}
	/**
	 * 
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handComdifyAndCoin(JSONObject jsonObject, BpmFormField bpmFormField) {
		if (bpmFormField.getControlType()==1) {
			JSONObject dbType=(JSONObject) jsonObject.get("dbType");
			String ctlProperty=bpmFormField.getCtlProperty();
			JSONObject jsonObj=null;
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}
			if(dbType.containsKey("isShowComdify")){
				Short isShowComdify=0;
				//为了兼容旧版本数据
				String temp=dbType.getString("isShowComdify");
				boolean isInteger = temp.matches("^[0-9]*$");
				if (isInteger) {
					isShowComdify=Short.parseShort(temp);
				}else {
					if ("true".equals(temp)) {
						isShowComdify=(short)1;
					}else if ("false".equals(temp)) {
						isShowComdify=(short)0;
					}
				}
				jsonObj.element("isShowComdify", isShowComdify);
			}
			if(dbType.containsKey("coinValue")){
				String coinValue=dbType.getString("coinValue");
				jsonObj.element("coinValue", coinValue);
			}
			if(dbType.containsKey("decimalLen")){
				String decimalValue=dbType.getString("decimalLen");
				jsonObj.element("decimalValue", decimalValue);
			}
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
		
	}
	
	/**
	 * 保存options。
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handOptions(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getControlType()==11 || bpmFormField.getControlType()==13 || bpmFormField.getControlType()==14){
			Object objOptions=jsonObject.get("options");
			if(objOptions==null) return ;
			String options=objOptions.toString();
			bpmFormField.setOptions(options);
		}
	}
	
	/**
	 * 保存是否作为表单用户
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handFormUser(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getControlType()==4 || bpmFormField.getControlType()==6 || bpmFormField.getControlType()==8){
			Object isformuser=jsonObject.get("isformuser");
			if(isformuser==null) return ;
			String ctlProperty=bpmFormField.getCtlProperty();
			JSONObject jsonObj=null;
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}			
			jsonObj.element("isformuser", isformuser);
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
	}
	
	/**
	 * 保存人员选择器是否显示当前用户
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handShowCurUser(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getControlType()==FieldPool.SELECTOR_USER_SINGLE){
			Object showCurUser=jsonObject.get("showCurUser");
			if(showCurUser==null) return ;
			String ctlProperty=bpmFormField.getCtlProperty();
			JSONObject jsonObj=null;
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}			
			jsonObj.element("showCurUser", showCurUser);
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
	}
	
	/**
	 * 保存组织选择器是否显示当前组织
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handShowCurOrg(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getControlType()==18){
			Object showCurOrg=jsonObject.get("showCurOrg");
			if(showCurOrg==null) return ;
			String ctlProperty=bpmFormField.getCtlProperty();
			JSONObject jsonObj=null;
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}			
			jsonObj.element("showCurOrg", showCurOrg);
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
	}
	/**
	 * 保存附件是否直接上传
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handAttachMent(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getControlType()==9){
			Object directUpLoad=jsonObject.get("directUpLoad");
			if(directUpLoad==null) return ;
			String ctlProperty=bpmFormField.getCtlProperty();
			JSONObject jsonObj=null;
			if(StringUtil.isNotEmpty(ctlProperty)){
				jsonObj=JSONObject.fromObject(ctlProperty);
			}
			else{
				jsonObj=JSONObject.fromObject("{}");
			}			
			jsonObj.element("directUpLoad", directUpLoad);
			bpmFormField.setCtlProperty(jsonObj.toString());
		}
	}
	
	/**
	 * 获取控件类型
	 * @param el
	 * @param jsonObject
	 * @return
	 */
	private static short handControlType(Element el,JSONObject jsonObject){
		String clsName=el.attr("class").toLowerCase();
		Short controlType=controlTypeMap.get("textinput");
		if(clsName.equals("personpicker")){
			if(jsonObject.containsKey("singleselect")){
				if(jsonObject.get("singleselect").toString().equals("1")){
					controlType=controlTypeMap.get("user");
				}
				else{
					controlType=controlTypeMap.get("userMulti");
				}
			}
			else{
				controlType=controlTypeMap.get("user");
			}
		}else if (clsName.equals("rolepicker")) {
			if(jsonObject.containsKey("singleselect")){
				if(jsonObject.get("singleselect").toString().equals("1")){
					controlType=controlTypeMap.get("rolepicker");
				}
				else{
					controlType=controlTypeMap.get("rolepickerMulti");
				}
			}
			else{
				controlType=controlTypeMap.get("rolepicker");
			}

		}else if (clsName.equals("departmentpicker")) {
			if(jsonObject.containsKey("singleselect")){
				if(jsonObject.get("singleselect").toString().equals("1")){
					controlType=controlTypeMap.get("departmentpicker");
				}
				else{
					controlType=controlTypeMap.get("departmentpickerMulti");
				}
			}
			else{
				controlType=controlTypeMap.get("departmentpicker");
			}
		}else if (clsName.equals("positionpicker")) {
			if(jsonObject.containsKey("singleselect")){
				if(jsonObject.get("singleselect").toString().equals("1")){
					controlType=controlTypeMap.get("positionpicker");
				}
				else{
					controlType=controlTypeMap.get("positionpickerMulti");
				}
			}
			else{
				controlType=controlTypeMap.get("positionpicker");
			}
		}
		else{
			if(controlTypeMap.containsKey(clsName)){
				controlType=controlTypeMap.get(clsName);
			}
		}
		return controlType;
	}
	
	/**
	 * 设置验证规则。
	 * @param bpmFormField
	 * @return
	 */
	private static String handValidate(BpmFormField bpmFormField){
		//处理验证规则
		JSONObject valid=JSONObject.fromObject("{}");
		
		if(bpmFormField.getIsRequired()==1){
			valid.put("required", true);
		}
		if(bpmFormField.getIsWebSign()==1){   //xcx web印章验证
			valid.put("isWebSign", true);
		}
		//验证规则。
		if(StringUtil.isNotEmpty(bpmFormField.getValidRule())){
			valid.put(bpmFormField.getValidRule(), true);
		}
		//
		String fieldType=bpmFormField.getFieldType();
		if("varchar".equals(fieldType)){
			valid.put("maxlength", bpmFormField.getCharLen());
		}
		else if("date".equals(fieldType)){
			valid.put("date", true);
		}
		else if("number".equals(fieldType)){
			valid.put("number", true);
			valid.put("maxIntLen", bpmFormField.getIntLen());
			valid.put("maxDecimalLen", bpmFormField.getDecimalLen());
		}
	
		return valid.toString();
	}
	
	
	/**
	 * 解析数据字典。
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handDictType(JSONObject jsonObject,BpmFormField bpmFormField){
		String dictType=(String)jsonObject.get("dictType");
		if(StringUtil.isEmpty(dictType)) return ;
	
		bpmFormField.setDictType(dictType);
		
	}
	/**
	 * 
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handStyle(JSONObject jsonObject,BpmFormField bpmFormField){
		StringBuffer style=new StringBuffer();
		Object objWidthOptions=jsonObject.get("width");
		Object objHeightOptions=jsonObject.get("height");
		Object objWidthUnit=jsonObject.get("widthUnit");
		Object objHeightUnit=jsonObject.get("heightUnit");
		if(BeanUtils.isEmpty(objWidthUnit) ) {
			objWidthUnit="px";
		}
		if(BeanUtils.isEmpty(objHeightUnit)  ) {
			objHeightUnit="px";
		}
		if(BeanUtils.isNotEmpty(objWidthOptions)){
			style.append("width:"+objWidthOptions+objWidthUnit+";");
		}
		if(BeanUtils.isNotEmpty(objHeightOptions) ){
			style.append("height:"+objHeightOptions+objHeightUnit+";");
		}
		bpmFormField.setStyle(style.toString());

		
	}
	/**
	 * 处理值来源
	 * <pre>
	 * 0,表单输入。
	 * 1.脚本运算(显示)。
	 * 2.脚本运算(不显示)。
	 * 3.流水号。
	 * </pre>
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handValueFrom(JSONObject jsonObject,BpmFormField bpmFormField){
		JSONObject valueFrom=(JSONObject)jsonObject.get("valueFrom");
		if(valueFrom==null) return; 
		
		Short value=Short.parseShort( valueFrom.get("value").toString());
		bpmFormField.setValueFrom(value);
		switch(value){
			//表单输入
			case 0:
				//当值来源是表单输入的时候，content：为验证规则。
				if(valueFrom.containsKey("content")){
					String validRule=valueFrom.getString("content");
					if(StringUtil.isNotEmpty(validRule)){
						bpmFormField.setValidRule(validRule);
					}
				}
				break;
			//脚本
			case 1:
			case 2:
				String content=valueFrom.getString("content");
				bpmFormField.setScript(content);
				Object scriptID = valueFrom.get("scriptID");
				bpmFormField.setScriptID(scriptID==null?"":scriptID.toString());
				break;
			//流水号。
			case 3:
				String identity=valueFrom.getString("content");
				bpmFormField.setIdentity(identity);
				//处理流水号中是否勾选了  是否显示在流程启动页面中
				JSONObject jsonObj=null;
				String ctlProperty= bpmFormField.getCtlProperty();
				if(StringUtil.isNotEmpty(ctlProperty)){
					jsonObj=JSONObject.fromObject(ctlProperty);
				}
				else{
					jsonObj=JSONObject.fromObject("{}");
				}
				
				if (jsonObject.containsKey("isShowidentity")) {
					String isShowidentity=jsonObject.getString("isShowidentity");
					jsonObj.put("isShowidentity", isShowidentity);
				}else {
					jsonObj.put("isShowidentity", "0");
				}
				bpmFormField.setCtlProperty(jsonObj.toString());
				break;
		}
		
	}
	
	/**
	 * 处理条件。
	 * <pre>
	 * isQuery:1,search:{condition:'LIKE',searchFrom:'fromScript',searchValue:'this is a test'},
	 *	condition:等于、LIKE、LIKEEND
	 *	searchFrom:fromForm(表单输入)、fromStatic(固定值)、fromScript(脚本)
	 *	searchValue：
	 *	</pre>
	 * @param jsonObject
	 * @param bpmFormField
	 */
	/*private static void handCondition(JSONObject jsonObject,BpmFormField bpmFormField){
		if(bpmFormField.getIsQuery()!=1) return;
		
		JSONObject obj=(JSONObject)jsonObject.get("search");
		
		if(obj==null){
			bpmFormField.setIsQuery((short)0);
		}
		JSONObject jsonObj=null;
		String ctlProperty= bpmFormField.getCtlProperty();
		if(StringUtil.isNotEmpty(ctlProperty)){
			jsonObj=JSONObject.fromObject(ctlProperty);
		}
		else{
			jsonObj=JSONObject.fromObject("{}");
		}
		String searchFrom=obj.getString("searchFrom");
		String searchValue="";
		int condValFrom=1;
		if(searchFrom.equalsIgnoreCase("fromStatic")){
			condValFrom=2;
			searchValue=obj.getString("searchValue");
		}
		else if(searchFrom.equalsIgnoreCase("fromScript")){
			condValFrom=3;
			searchValue=obj.getString("searchValue");
		}
		
		jsonObj.element("condition", obj.getString("condition"));
		jsonObj.element("condValFrom",condValFrom);
		jsonObj.element("condValue",searchValue);
		
		bpmFormField.setCtlProperty(jsonObj.toString());
	}*/
	

	/**
	 * 处理必填，是否列表，是否查询条件，是否流程变量。
	 * @param jsonObject
	 * @param bpmFormField
	 */
	private static void handOption(JSONObject jsonObject,BpmFormField bpmFormField){
		Object isRequired=(Object) jsonObject.get("isRequired");
		if(isRequired==null){
			bpmFormField.setIsRequired((short)0);
		}
		else{
			bpmFormField.setIsRequired(Short.parseShort(isRequired.toString()));
		}
		//列表
		Object isList=(Object) jsonObject.get("isList");
		if(isList==null){
			bpmFormField.setIsList((short)0);
		}
		else{
			bpmFormField.setIsList(Short.parseShort(isList.toString()));
		}
		//查询条件。
		Object isQuery=(Object) jsonObject.get("isQuery");
		if(isQuery==null){
			bpmFormField.setIsQuery((short)0);
		}
		else{
			bpmFormField.setIsQuery(Short.parseShort(isQuery.toString()));
		}
		//流程变量
		Object isFlowVar=(Object) jsonObject.get("isFlowVar");
		if(isFlowVar==null){
			bpmFormField.setIsFlowVar((short)0);
		}
		else{
			bpmFormField.setIsFlowVar(Short.parseShort(isFlowVar.toString()));
		}
		Object isAllowMobile=(Object) jsonObject.get("isAllowMobile");
		if(isAllowMobile==null){
			bpmFormField.setIsAllowMobile((short)0);
		}else{
			bpmFormField.setIsAllowMobile(Short.parseShort(isAllowMobile.toString()));
		}
		
		//web印章验证
		Object isWebSign = (Object) jsonObject.get("isWebSign");   //xcx
		if(isWebSign==null){
			bpmFormField.setIsWebSign((short)0);
		}else{
			bpmFormField.setIsWebSign(Short.parseShort(isWebSign.toString()));
		}
		
		//处理文字类型日期格式
		Object isDateString=(Object) jsonObject.get("isDateString");
		if("varchar".equals(bpmFormField.getFieldType()) && isDateString!=null){
			bpmFormField.setIsDateString(Short.parseShort(isDateString.toString()));
			String displayDate=	bpmFormField.getPropertyMap().get("displayDate");		
			if(displayDate!=null){
				bpmFormField.setIsCurrentDateStr(Short.parseShort(displayDate));
			}
		}
		
		
		//处理超连接
		Object isReference=(Object) jsonObject.get("isReference");
		if(isReference==null){
			bpmFormField.setIsReference((short)0);
		}else {
			bpmFormField.setIsReference(Short.parseShort(isReference.toString()));
		}
	}
	
	/**
	 * 处理字段类型。
	 * @param dbType {type:'varchar',length:20,}
	 * @param bpmFormField
	 */
	private  static void handFieldType(JSONObject dbType,BpmFormField bpmFormField,ParseReult result){
		if(!dbType.containsKey("type")){
			result.addError("字段:" +bpmFormField.getFieldName() +"," + bpmFormField.getFieldDesc() +",没有设置数据类型!");
			return ;
		}
		//type
		String type=dbType.getString("type");
		if(StringUtil.isEmpty(type)){
			result.addError("字段:" +bpmFormField.getFieldName() +"," + bpmFormField.getFieldDesc() +",没有设置数据类型!");
			return ;
		}
		if(!isValidType(type)){
			result.addError("字段:" +bpmFormField.getFieldName() +"," + bpmFormField.getFieldDesc() +",数据类型设置错误:" + type);
			return ;
		}
		
		bpmFormField.setCharLen(0);
		bpmFormField.setIntLen(0);
		bpmFormField.setDecimalLen(0);
		
		bpmFormField.setFieldType(type);
		if("varchar".equals(type)){
			if(!dbType.containsKey("length")){
				result.addError("字段:" +bpmFormField.getFieldName() +"," + bpmFormField.getFieldDesc() +",数据类型(VARCHAR)长度未设置。" );
				return ;
			}
			
			//length
			int len=dbType.getInt("length");
			
			bpmFormField.setCharLen(len);
			//设置日期格式
			setDateFormat(dbType, bpmFormField);
		}
		else if("number".equals(type)){
			if(!dbType.containsKey("intLen") ){
				result.addError("字段:" +bpmFormField.getFieldName() +"," + bpmFormField.getFieldDesc() +",数据类型(number)数据长度未设置。" );
				return ;
			}
			int intLen=dbType.getInt("intLen");
			int decimalLen=0;
			Short isShowComdify=0;
			String coinValue="";
			//decimalLen
			if(dbType.containsKey("decimalLen")){
				decimalLen=dbType.getInt("decimalLen");
			}
			//TODO 在此添加属性
			if(dbType.containsKey("isShowComdify")){
				//为了兼容旧版本数据
				String temp=dbType.getString("isShowComdify");
				boolean isInteger = temp.matches("^[0-9]*$");
				if (isInteger) {
					isShowComdify=Short.parseShort(temp);
				}else {
					if ("true".equals(temp)) {
						isShowComdify=(short)1;
					}else if ("false".equals(temp)) {
						isShowComdify=(short)0;
					}
				}
			}
			if(dbType.containsKey("coinValue")){
				coinValue=dbType.getString("coinValue");
			}
			bpmFormField.setCoinValue(coinValue);
			bpmFormField.setIsShowComdify(isShowComdify);
			bpmFormField.setIntLen(intLen);
			bpmFormField.setDecimalLen(decimalLen);
		}
		else if("date".equals(type)){
			setDateFormat(dbType, bpmFormField);
			
		}
	}
	
	/**
	 * 校验字段类型是否有效。
	 * @param type
	 * @return
	 */
	private static boolean isValidType(String type){
		if(type.equals("varchar") || type.equals("number") || type.equals("date") || type.equals("clob")){
			return true;
		}
		return false;
	}

	/**
	 * 时间格式的处理。
	 * @param dbType
	 * @param bpmFormField
	 */
	private static void setDateFormat(JSONObject dbType,	BpmFormField bpmFormField) {
		String ctlProperty=bpmFormField.getCtlProperty();
		JSONObject jsonObj=null;
		if(StringUtil.isNotEmpty(ctlProperty)){
			jsonObj=JSONObject.fromObject(ctlProperty);
		}
		else{
			jsonObj=JSONObject.fromObject("{}");
		}
		//格式
		String format=(String)dbType.get("format");
		String dateStrFormat=(String)dbType.get("dateStrFormat");
		if(format==null  && dateStrFormat==null){
			jsonObj.element("format", "yyyy-MM-dd");
		}
		else if(format!=null){
			jsonObj.element("format", format);
		}else if(dateStrFormat!=null){
			jsonObj.element("format", dateStrFormat);
		}
		//显示时间
		Object displayDate=dbType.get("displayDate");
		if(displayDate==null){
			jsonObj.element("displayDate", 0);
		}
		else{
			jsonObj.element("displayDate", Integer.parseInt(displayDate.toString()));
		}
		bpmFormField.setCtlProperty(jsonObj.toString());
	}
	
	/**
	 * 获取意见表单字段。
	 * <pre>
	 * 通过解析表单，返回表单中的意见字段数据。
	 * </pre>
	 * @param html
	 * @return
	 */
	public static Map<String,String> parseOpinion(String html){
		Map<String,String> map=new HashMap<String, String>();
		Document doc= Jsoup.parseBodyFragment(html);
		Elements list=doc.select("[name^=opinion:]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			String memo=el.attr("opinionname");
			String opinionName=name.replaceFirst("^opinion:", "");
			map.put(opinionName, memo);
		}
		return map;
	}

	public static String getPrintFreeMarkerTemplate(String html, String tableName, String comment){
		Document doc= Jsoup.parseBodyFragment(html);
		//获取主表中有external属性的控件集合。
		Elements mainFields=doc.select("[external]");
		
		//处理流水号
		parseSerialNumber(doc);
		//处理国际化资源
		//parseI18nKey(doc);
		//处理表单中的其他国际化资源
		//parseFormI18n(doc);
		if(mainFields.size()>0){//由编辑器生成的表单
			ParseReult result = new ParseReult();
			parsePrintSubTableOfFormEditor(doc , result);
			parsePrintMainTableOfFormEditor(doc, tableName, comment, result);
		}
		//遍历主表字段
		parsePrintMainField(doc);
		//处理子表。
		parsePrintSubTable(doc);
		//解析意见
		parsePrintOpinion(doc);
		String rtn=doc.body().html();
		rtn=rtn.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		rtn = "<#setting number_format=\"#\">\n" + rtn;
		return rtn;
	}
	
	/**
	 * 处理流水号
	 * @param doc
	 */
	private static void parseSerialNumber(Document doc){
		Elements list=doc.select("span[serialnum=true]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String defaultVal = el.text();
			String str = "<#if model.others.serialnum??>${model.others.serialnum}<#else>"+defaultVal+"</#if>";
			el.text(str);
		}
	}
	
	public static void parsePrintMainField(Document doc){
		Elements list=doc.select("input[name^=m:],textarea[name^=m:],select[name^=m:],a.link");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			//获取字段名
			String fieldName=name.replaceAll("^.*:", "").toLowerCase();
			//解析模版，对模版进行复制复制和授权的处理。
			parsePrintMainField(el, fieldName);	
		}
	}
	
	public static void parsePrintMainField(Element el,String fieldName){
		String controltype=el.attr("controltype");
		//控件类型
		String type=el.attr("type").toLowerCase();
		//附件的处理方式
		if("attachment".equalsIgnoreCase(controltype)){
			//在<div name="div_attachment_container"></div>中加一个right="r"的属性
			Element parent=getContainer(el, "div_attachment_container");
			parent.attr("right", "r");
			el.val("${service.getFieldValue('" + fieldName+ "',model)}");
		}
		
		//pictureShow控件的处理
		//设置pictureShow的值和权限。
		else if("pictureShow".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model,'"+controltype+"')}");
		}
		//webSign控件的处理
		//设置webSign的值和权限。
		else if("webSign".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model)}");
		}
		//office控件的处理
		//设置office的值和权限。
		else if("office".equalsIgnoreCase(controltype)){
			el.attr("value", "${service.getFieldValue('" + fieldName+ "',model)}");
		}
		//checkbox和radio的处理
		//checkbox和radio的必须为以下格式
		//<label><input type='checkbox' value='JAVA' />JAVA</label>
		//<label><input type='checkbox' value='JQUERY' />JQUERY</label>
		else if ("checkbox".equalsIgnoreCase(type) || "radio".equalsIgnoreCase(type)){
			String value=el.attr("value");
			el.attr("chk","1").attr("disabled", "disabled");
			Element elParent=el.parent();
			String parentNodeName=elParent.nodeName();
			String tmp=parentNodeName.equals("label")?elParent.toString() :el.toString();
			//将html赋值给一个变量，在使用service.getRdoChkBox 方法做解析
			String str="<span>&lt;#assign "+fieldName+"Html&gt;"+tmp+" &lt;/#assign&gt;" +
					"\r\n${service.getPrintRdoChkBox('" +fieldName +"', "+fieldName+"Html,'"+value+"', model)}</span>";
			elParent.before(str);
			elParent.remove();
		}
		//多行文本
		else if(el.nodeName().equalsIgnoreCase("textarea")){
			String str="${model.main."+fieldName+"}";
			el.before(str);
			el.remove();
		}
		//处理文本输入框
		else if(el.nodeName().equalsIgnoreCase("input")){
			if(!"hidden".equals(type)){
				el.before("${model.main."+fieldName+"}");
			}
			el.remove();
		}
		//下拉框
		else if(el.nodeName().equalsIgnoreCase("select")){
			String tmp = el.toString().replaceAll("&quot;", "'").replaceAll("'", "\\\\'");   //防止有'时HTML会出现乱嵌套 "'" 报错 所以要把  '  改成  \';
			String str = "&lt;#assign "+fieldName+"Html&gt;"+tmp+" &lt;/#assign&gt;" +
			"\r\n${service.getPrintOptionValue('" +fieldName +"', " +fieldName+"Html, model)}";
			el.before(str);
			el.remove();
		}
		//处理选择器的a标签
		else if(el.nodeName().equalsIgnoreCase("a")){
			el.remove();
		}
		
	}
	
	private static void parsePrintSubTable(Document doc){
		Elements list=doc.select("div[type=subtable]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element subTable=it.next();
			String tableName=subTable.attr("tableName").toLowerCase();
			if(StringUtil.isEmpty(tableName)){
				logger.debug("subtable tableName is not specialed");
				continue;
			}
			
			//查询编辑行
			Elements rows= subTable.select("[formtype=edit],[formtype=form]");
			if(rows.size()==0){
				logger.debug("no formtype row defined");
				return ;
			}
			Element row=rows.get(0);
			String mode=row.attr("formtype");
			Element newRow=row.clone().attr("formtype","newrow");
//			Map<String,Boolean> checkboxTagMap = new HashMap<String, Boolean>();
			if(BpmFormDef.EditInline.equals(mode)){
				Elements fields=newRow.select("[name^=s:]");
				for(Iterator<Element> its= fields.iterator();its.hasNext();){
					Element el=its.next();
					String name=el.attr("name");
					String type=el.attr("type");
					String controltype=el.attr("controltype");
					//获取字段名
					String fieldName=name.replaceAll("^.*:", "").toLowerCase();
					//复选框和radio
					if("checkbox".equals(type) || "radio".equals(type)){
						el.attr("chk","1").attr("disabled", "disabled");
						String value=el.attr("value");
						String html = el.toString().replaceAll("&quot;", "'").replaceAll("'", "\\\\'");   //防止有'时HTML会出现乱嵌套 "'" 报错 所以要把  '  改成  \';
						el.before("${service.getRdoChkBox('" +fieldName +"', '" +html + "','"+value+"', table)}");
						el.remove();
					}
					else if("attachment".equalsIgnoreCase(controltype)){//附件的处理方式
						//在<div name="div_attachment_container"></div>中加一个right="r"的属性
						Element parent=getContainer(el, "div_attachment_container");
						parent.attr("right", "r");
						el.val("${service.getSubTableAttachMent('" + fieldName+ "', table)}");
					}
					//下拉选项
					else if(el.nodeName().equalsIgnoreCase("select")){
						String html = el.toString().replaceAll("&quot;", "'").replaceAll("'", "\\\\'");   //防止有'时HTML会出现乱嵌套 "'" 报错 所以要把  '  改成  \';
						el.before("${service.getSubTableOptionValue('" +fieldName +"', '" +html + "', table)}");
						el.remove();
					}
					else{
						el.before("${table."+fieldName+"}");
						el.remove();
					}
				}
				
			}else{
				Elements windowRows= subTable.select("[formtype=window]");
				if(windowRows.size()!=1){
					logger.debug("window mode hasn't window defined");
					return;
				}
				Element window=windowRows.get(0);
				parseSubTableFormField(doc, newRow, window);
//				Elements windowFields=window.select("[name^=s:]");
				windowRows.remove();
			}
			row.after(newRow);
			newRow.before("&lt;#if model.sub."+tableName+" != null&gt; &lt;#list model.sub."+tableName+".dataList as table&gt;");
			newRow.after("&lt;/#list> &lt;/#if&gt;");
			row.remove();
			subTable.removeAttr("right");
			subTable.attr("type", "sub");
		}
		
	}
	
	private static void parsePrintOpinion(Document doc){
		//opinion:www
		Elements list=doc.select("[name^=opinion:]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String name=el.attr("name");
			String opinionName=name.replaceFirst("^opinion:", "");
			//getOpinion(String opinionName, String html, Map<String, Map<String, Map>> model, Map<String, Map<String, Map>> permission)
			
			String str="&lt;#assign "+opinionName+"Opinion&gt;"+el.toString()+" &lt;/#assign&gt;"+
					"\r\n${service.getOpinion('" +opinionName +"',model)}";
			
			el.before(str);
			el.remove();
			
		}
	}
	
	/**
	 * 处理表字段的国际化资源
	 * @param doc
	 */
	@SuppressWarnings("unused")
	private static void parseI18nKey(Document doc){
		Elements list=doc.select("span[i18nkey]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String i18nkey = el.attr("i18nkey").replaceAll(":", "_");
			String defaultVal = el.text();
			String str = "&lt;#if i18nmap."+i18nkey+"??&gt;${i18nmap."+i18nkey+"}&lt;#else&gt;"+defaultVal+"&lt;/#if&gt;";
			el.before(str);
			el.remove();
		}
	}
	
	/**
	 * 处理表单中其他资源的国际化
	 * @param doc
	 */
	@SuppressWarnings("unused")
	private static void parseFormI18n(Document doc){
		Elements list=doc.select("p[i18n]");
		for(Iterator<Element> it= list.iterator();it.hasNext();){
			Element el=it.next();
			String i18n = el.attr("i18n");
			JSONObject jobject = JSONObject.fromObject(i18n);
			String reskey = jobject.getString("reskey");
			String defaultVal = el.text();
			String str = "&lt;#if i18nformmap."+reskey+"??&gt;&lt;p&gt;${i18nformmap."+reskey+"}&lt;br/&gt;&lt;/p&gt;&lt;#else&gt;&lt;p&gt;"+defaultVal+"&lt;br/&gt;&lt;/p&gt;&lt;/#if&gt;";
			el.before(str);
			el.remove();
		}
	}
	
	/**
	 * 生成编辑器设计的表单的主表模板
	 * @param doc
	 * @param tableName
	 * @param comment
	 * @param result
	 */
	public static void parsePrintMainTableOfFormEditor(Document doc ,String tableName,String comment,ParseReult result){
		BpmFormTable bpmFormTable=new BpmFormTable();
		bpmFormTable.setTableName(tableName);
		bpmFormTable.setTableDesc(comment);
		bpmFormTable.setIsMain((short)1);
		bpmFormTable.setGenByForm(1);
		//获取主表中有external属性的控件集合。
		Elements mainFields=doc.select("[external]");
		
		if(mainFields.size()==0){
			result.addError("主表【"+tableName +"," +comment+"】还未定义任何字段");
			return ;
		}
	
		for(Iterator<Element> it= mainFields.iterator();it.hasNext();){
			Element el=it.next();
			//从html中解析出字段。
			BpmFormField bpmFormField= parseExternal(el,result);
			
			if(bpmFormField==null){
				continue;
			}
			
			String controlName="m:" + tableName +":" +bpmFormField.getFieldName();
			
			boolean rtn= bpmFormTable.addField(bpmFormField);
			if(!rtn){
				String error = "表单中主表:【" +tableName +"】，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				if(StringUtil.isEmpty(tableName)){
					error = "主表中，字段:【"+bpmFormField.getFieldDesc()+"】重复";
				}
				result.addError(error);
				continue;
			}
			
			//整理html。
			parseChilds( doc,el,controlName,bpmFormField);
			//删除包装。
			removeWrap(el);
		}
	}
	
	public static void parsePrintSubTableOfFormEditor(Document doc ,ParseReult result){
		Elements list=doc.select("div[type=subtable]");
		for(Iterator<Element> it=list.iterator();it.hasNext();){
			Element subTable=it.next();
			//如果子表设置了external属性，则先删除external属性。
			if(subTable.hasAttr("external")){
				subTable.removeAttr("external");
				subTable.removeClass("subtable");
			}
			
			BpmFormTable table=new BpmFormTable();
			//设置子表。
			String tableName=subTable.attr("tablename").toLowerCase();
			if(StringUtil.isEmpty(tableName)){
				 result.addError("有子表对象没有设置表名。");
				continue;
			}
			String comment=subTable.attr("tabledesc");
			
			table.setTableName(tableName);
			table.setTableDesc(comment);
			table.setIsMain((short)0);
			table.setGenByForm(1);
			
			//设置子表权限。
//			subTable.attr("right", "${service.getSubTablePermission('"+tableName+"', permission)}");
			Elements rows= subTable.select("[formtype=edit],[formtype=form]");
			if(rows.size()==0){
				logger.debug("no formtype row defined");
				result.addError("子表【"+tableName+"】没有定义属性【formtype】。");
				continue;
			}
			Element row=rows.get(0);
			Elements fields=row.select("[external]");
			if(fields.size()==0){
				result.addError("子表【"+tableName+"," +table.getTableDesc()+"】尚未定义任何字段");
				return;
			}
			for(Iterator<Element> ite= fields.iterator();ite.hasNext();){
				Element el=ite.next();
				//解析字段。
				BpmFormField bpmFormField= parseExternal(el, result);
				
				boolean rtn= table.addField(bpmFormField);
				
				String fieldName=bpmFormField.getFieldName();
				
				if(!rtn){
					String error = "表单中子表:【" +tableName +"】，字段:【"+bpmFormField.getFieldDesc()+"】重复";
					if(StringUtil.isEmpty(tableName)){
						error = "子表中，字段:【"+bpmFormField.getFieldDesc()+"】重复";
					}
					result.addError(error);
					continue;
				}
				
				String controlName="s:" + tableName + ":" + fieldName;
				parseChilds(doc,el,controlName,bpmFormField);
				//删除父节点
				removeWrap(el);
			}
		}
	}
	
}

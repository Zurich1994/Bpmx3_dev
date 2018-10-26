package com.hotent.platform.service.form.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;

public class DetailParseHandler extends AbstractParseHandler {

	@Override
	protected void parse(Document doc, Map<String, Object> param) {

		BpmFormDef bpmFormDef = (BpmFormDef) param.get("bpmFormDef");
		int fromType = bpmFormDef.getDesignType();
		
		doc.select("[dialog]").remove();//删除自定义按钮
		doc.select("a.add").remove();//删除添加按钮
		
		if (fromType == BpmFormDef.DesignType_CustomDesign) {
			parseDesign(doc, param);
		} else if (fromType == BpmFormDef.DesignType_FromTable) {
			parseTable(doc, param);
		}
	}

	/**
	 * 解释表模式
	 * @param doc
	 * @param param
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void parseTable(Document doc, Map<String, Object> param) {
		BpmFormTable table = (BpmFormTable) param.get("table");

		// 字段处理 主表跟子表字段一样处理
		doc.select("div[name=div_attachment_container]").attr("right", "r");//这是处理文件上传字段改为right权限
		Elements fieldElements = doc.select("[name^=m:],[name^=s:]");
		List<BpmFormField> fields = new ArrayList<BpmFormField>(table.getFieldList());// 获取主表字段
		for (BpmFormTable subTable : table.getSubTableList()) {// 获取所有子表字段
			fields.addAll(subTable.getFieldList());
		}
		for (Element element : fieldElements) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fieldList", fields);
			handleField_table(element, map);
		}
		

		// 处理子表
		Elements addLinkElements = doc.select(".add");//删除添加按钮
		addLinkElements.remove();
		
		Elements subtableElements = doc.select("div[tablename]");// 找到子表们
		for (Element subtableElement : subtableElements) {
			
			String tname = subtableElement.attr("tablename");// 获取子表名字
			BpmFormTable subTable = getByTableNameFromList(table.getSubTableList(), tname);// 获取子表
			subtableElement.attr("id", subtableElement.attr("tablename"));// 设置id

			Element divElement = subtableElement.select("[formtype]").get(0);
			String formtype = divElement.attr("formtype");// 获取弹框模式
			divElement.attr("type", "subdata");// 添加指定的type
			divElement.removeAttr("formtype");// 删除没用的信息

			subtableElement.attr("formtype", formtype);

			divElement.wrap("<c:forEach items='${" + subTable.getTableName() + "List}' var='" + subTable.getTableName() + "' varStatus='status'>");
		}

	}

	/**
	 * TODO方法名称描述
	 * 
	 * @param mainFieldElement
	 *            void
	 * @param table
	 * @exception
	 * @since 1.0.0
	 */
	private void handleField_table(Element element, Map<String, Object> param) {
		
		String tableName =StringUtil.underlineToCamel(element.attr("name").split(":")[1]);
		String fieldName = StringUtil.underlineToCamel(element.attr("name").split(":")[2]);

		BpmFormField bpmFormField = getByFieldNameFormList((List<BpmFormField>) param.get("fieldList"), fieldName);
		
		fieldName=bpmFormField.getFieldName();//保证完全一致
		
		if (bpmFormField.getControlType() == 9) {// 文件上传
			element.text("${" + tableName + "." + fieldName + "}");
			Element e = element.nextElementSibling();
			e.remove();
			return;
		}
		
		if (bpmFormField.getControlType() == 11) {// 下拉选项
			boolean isJL=false;//是否级联出来的数据
			JSONArray jsonArray = JSONArray.fromObject(bpmFormField.getOptions());
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.optJSONObject(i);
				//为空说明级联出来的
				if(StringUtil.isEmpty(jsonObject.get("key").toString())||StringUtil.isEmpty(jsonObject.get("value").toString())){
					isJL=true;
					break;
				}
				Element ep = element.parent();
				ep.appendText("<c:if test=\"${" + tableName + "." + fieldName + "=='" + jsonObject.get("key") + "'}\">" + jsonObject.get("value") + "</c:if>");
			}
			if(!isJL){
				element.remove();
				return;
			}
		}
		if(bpmFormField.getControlType()==13||bpmFormField.getControlType()==14){//复选框
			element.attr("disabled","disabled");
			element.attr("data","${" + tableName + "." + fieldName + "}");
			return;
			
		}
		element.parent().append("${" + tableName + "." + fieldName + "}");
		element.remove();
	}
	
	/**
	 * 处理表单编辑器来的表单
	 * 
	 * @param doc
	 * @param param
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void parseDesign(Document doc, Map<String, Object> param) {
		BpmFormTable table = (BpmFormTable) param.get("table");
		
		// 处理主表字段
		Elements mainExternals = doc.select("table.formTable span[external]");
		for (Element external : mainExternals) {
			parseFieldExternal(external, table);
			external.remove();// 删除external
		}
		
		// 处理子表字段
		Elements subtableElements = doc.select("div[tablename]");// 找到子表们
		for (Element subtableElement : subtableElements) {
			String tname = subtableElement.attr("tablename");// 获取子表名字

			BpmFormTable subTable = getByTableNameFromList(table.getSubTableList(), tname);// 获取子表

			// 处理子表字段
			Elements subExternals = subtableElement.select("span[external]");
			for (Element subExternal : subExternals) {
				parseFieldExternal(subExternal, subTable);
				subExternal.remove();// 删除external
			}

			// 拼装html，下面的代码开始处理子表弹出框或者编辑模式的html拼装
			Element listRowElement = subtableElement.select("tr.listRow").get(0);
			String formtype = listRowElement.attr("formtype");// 获取弹框模式

			subtableElement.removeAttr("external");// 删除编辑模式的表单信息
			subtableElement.removeAttr("class");// 删除class，因为这个class是编辑表单的时候给用户看的而已
			subtableElement.attr("id", subtableElement.attr("tablename"));// 设置id
			subtableElement.attr("formtype", formtype);

			// 构建jstl foreach 开始
			listRowElement.removeAttr("formtype");// 删除没用的信息
			listRowElement.attr("type", "subdata");// 添加指定的type
			listRowElement.wrap("<c:forEach items='${" + subTable.getTableName() + "List}' var='" + subTable.getTableName() + "' varStatus='status'>");
			// 构建jstl foreach 结束

			if (formtype.equals("form")) {// 弹框模式
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("listRowElement", listRowElement);
				map.put("subTable", subTable);
				map.put("subtableElement", subtableElement);
				handleFormType(map);
			}
			
			//序号
			Elements elements = listRowElement.select(".tdNo");
			if (!elements.isEmpty()) {
				elements.get(0).text("${status.index+1}");
			}
		}
	}
	
	/**
	 * 解释一个字段span中的external标签 在这个过程中，会先把解释好的html标签插入span，然后删除span标签
	 * 
	 * @param external
	 * @param table
	 * @return Element ：返回刚插入的字段标签
	 * @exception
	 * @since 1.0.0
	 */
	private Elements parseFieldExternal(Element external, BpmFormTable table) {
		String estr = external.attr("external").replace("&#39;", "'");
		JSONObject ejson = JSONObject.fromObject(estr);
		String fname = ejson.getString("name");

		BpmFormField field = getByFieldNameFormList(table.getFieldList(), fname);
		
		if (field == null)
			return null;
		fname=field.getFieldName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("field", field);
		map.put("table", table);
		String fieldHtml = parseField(map);// 根据external生成html代码
		/*if (fname.equals("zbzdw")) {
			// System.out.println("日期html:" + fieldHtml);
		}*/

		external.after(fieldHtml);// 插入html

		Elements fieldElements = null;
		try {
			fieldElements = external.nextElementSibling().select("[name=" + field.getFieldName() + "]");// 获取刚刚添加到next的标签
		} catch (Exception e) {
		}
		return fieldElements;
	}

	/**
	 * 处理弹框模式 formtype=form void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private void handleFormType(Map<String, Object> param) {
		Element listRowElement = (Element) param.get("listRowElement");
		BpmFormTable subTable = (BpmFormTable) param.get("subTable");
		Element subtableElement = (Element) param.get("subtableElement");
		/**
		 * 
		 * 下面开始解释 <td fieldname="l1"><br />
		 * </td> <td fieldname="l2"><br />
		 * </td> <td fieldname="l3"><br />
		 * </td> 成： <td class="tdNo"><br />
		 * </td> <td name="s:tablename:l1">${zibiao.l1}</td> <td style="display:none;"><input name="s:tablename:l1" type="hidde" value="${zibiao.l1}" /></td> <td name="s:tablename:l2">${zibiao.l2}</td> <td style="display:none;"><input name="s:tablename:l2" type="hidde" value="${zibiao.l2}" /></td> <td name="s:tablename:l3">${zibiao.l3}</td> <td style="display:none;"><input name="s:tablename:l3" type="hidde" value="${zibiao.l3}" /></td>
		 */
		Elements fieldElements = listRowElement.select("[fieldname]");
		for (Element fieldElement : fieldElements) {
			String fieldName = fieldElement.attr("fieldname");
			listRowElement.append("<td name=\"s:" + subTable.getTableName() + ":" + fieldName + "\">${" + subTable.getTableName() + "." + fieldName + "}</td> ");
			listRowElement.append("<td style='display:none;'><input name='s:" + subTable.getTableName() + ":" + fieldName + "' type=\"hidde\" value='${" + subTable.getTableName() + "." + fieldName + "}'/></td>");
			fieldElement.remove();
		}
		/** ---------------------->end **/

		/**
		 * 解释弹出框的模板： <div formtype="window">
		 * **/
		Element windowDivElement = subtableElement.select("[formtype=window]").get(0);
		windowDivElement.attr("style", "display:none;");
		windowDivElement.attr("id", subTable.getTableName() + "Form");
	}
	
	/**
	 * 
	 * 获取在external标签属性下的子标签蕴含的有用信息 然后将其加到新生成的字段html fieldElement里面 1:funcexp:字段间的运算 2:selectquery:自定义查询实现的联动
	 * 
	 * @param attrName
	 *            :子标签中的有用的属性名
	 * @param externalElement
	 *            ：当前标签
	 * @param fieldElement
	 *            ：添加到的目标标签 void
	 * @exception
	 * @since 1.0.0
	 */
	private void handleExternalChild(String attrName, Element externalElement, Element fieldElement) {
		Elements es = externalElement.select("[" + attrName + "]");
		if (es.size() > 0) {
			Element element = es.get(0);
			String str = element.attr(attrName);
			fieldElement.attr(attrName, str);
		}
	}
}

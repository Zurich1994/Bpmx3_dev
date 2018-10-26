package com.hotent.platform.service.form.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;

public class EditParseHandler extends AbstractParseHandler {

	@Override
	protected void parse(Document doc, Map<String, Object> param) {
		BpmFormDef bpmFormDef = (BpmFormDef) param.get("bpmFormDef");
		int fromType = bpmFormDef.getDesignType();
		
		//处理选择器的scope转义问题,因为scope在表单上是js赋值的，
		//所以生成的东西 ——scope="{"a":"1","b":"2"}"，这在当前页面没问题
		//但复制到其他页面就会json解释报错
		Elements scope=doc.select("[scope]");
		scope.attr("scope",scope.attr("scope").replace("\"", "'"));
		
		if (fromType == BpmFormDef.DesignType_CustomDesign) {
			// System.out.println("改表单是由设计器生成");
			parseDesign(doc, param);
		} else if (fromType == BpmFormDef.DesignType_FromTable) {
			// System.out.println("改表单是由表生成");
			parseTable(doc, param);
		}
		// FileUtil.writeFile("C:\\Users\\User\\Desktop\\代码生成器\\当前html.txt", doc.body().toString().replace("&quot;", "'"));
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
			Elements fieldElements = parseFieldExternal(external, table);
			external.remove();// 删除external
			if (fieldElements == null)
				continue;
			for (Element fieldElement : fieldElements) {
				String name = fieldElement.attr("name");
				fieldElement.attr("name", "m:" + table.getTableName() + ":" + name);// 为主表的name封装一层变成：m:tabelname:fieldname :为什么这么做？之前的js都这样写- -
			}
		}

		// 处理子表字段
		Elements subtableElements = doc.select("div[tablename]");// 找到子表们
		for (Element subtableElement : subtableElements) {
			String tname = subtableElement.attr("tablename");// 获取子表名字

			BpmFormTable subTable = getByTableNameFromList(table.getSubTableList(), tname);// 获取子表

			// 处理子表字段
			Elements subExternals = subtableElement.select("span[external]");
			for (Element subExternal : subExternals) {
				Elements fieldElements = parseFieldExternal(subExternal, subTable);
				subExternal.remove();// 删除external
				if (fieldElements == null)
					continue;
				for (Element fieldElement : fieldElements) {
					String name = fieldElement.attr("name");
					fieldElement.attr("name", "s:" + subTable.getTableName() + ":" + name);// 为子表的name封装一层变成：s:tabelname:fieldname :为什么这么做？之前的js都这样写- -
				}
			}

			// 拼装html，下面的代码开始处理子表弹出框或者编辑模式的html拼装
			Element listRowElement = subtableElement.select("tr.listRow").get(0);
			String formtype = listRowElement.attr("formtype");// 获取弹框模式

			subtableElement.removeAttr("external");// 删除编辑模式的表单信息
			subtableElement.removeAttr("class");// 删除class，因为这个class是编辑表单的时候给用户看的而已
			subtableElement.attr("id", subtableElement.attr("tablename"));// 设置id
			subtableElement.attr("formtype", formtype);

			// 添加id
			subtableElement.append("<input name=\"s:" + subTable.getTableName() + ":" + subTable.getPkField().toLowerCase() + "\" type=\"hidden\" pk=\"true\" value=\"\" />");

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
			// 添加append 字段
			Element newListRowElement = listRowElement.clone();
			Element tbodyElement = subtableElement.select("tbody").get(0);
			newListRowElement.attr("type", "append");// 添加指定的type
			newListRowElement.attr("style", "display:none;");
			tbodyElement.append(newListRowElement.toString());

			// 为什么在这里处理，因为103 newListRowElement是listRowElement的克隆，但new的不需要编号
			// 添加循环时的编号
			Elements elements = listRowElement.select(".tdNo");
			if (!elements.isEmpty()) {
				elements.get(0).text("${status.index+1}");
			}
		}
	}

	/**
	 * 处理从生成表来的表单 处理这个比较简单，直接套一个foreach则可
	 * 
	 * @param doc
	 * @param param
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void parseTable(Document doc, Map<String, Object> param) {

		BpmFormTable table = (BpmFormTable) param.get("table");

		// 主表字段处理
		Elements mainFieldElements = doc.select("[name^=m:]");
		for (Element mainFieldElement : mainFieldElements) {
			handleField_table(mainFieldElement);
		}

		// 子表字段处理
		Elements subFieldElements = doc.select("[name^=s:]");
		for (Element subFieldElement : subFieldElements) {
			handleField_table(subFieldElement);
		}

		// 处理子表
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

			// 添加append 字段
			Element newListRowElement = divElement.clone();
			newListRowElement.attr("type", "append");// 添加指定的type
			newListRowElement.attr("style", "display:none;");
			if (divElement.tagName().equals("div")) {
				subtableElements.append(newListRowElement.toString());
			}else if(divElement.tagName().equals("tr")){
				divElement.parent().parent().append(newListRowElement.toString());
			}
		}
	}

	/**
	 * 处理table模式下的字段信息
	 * 
	 * @param element
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void handleField_table(Element element) {
		String tableName =StringUtil.underlineToCamel(element.attr("name").split(":")[1]);
		String fieldName = StringUtil.underlineToCamel(element.attr("name").split(":")[2]);
		// element.attr("name", "" + fieldName);
		if (element.tagName().equals("textarea")) {
			element.text("${" + tableName + "." + fieldName + "}");
		}else if(element.attr("type").equals("checkbox")||element.attr("type").equals("radio")){
			element.attr("data", "${" + tableName + "." + fieldName + "}");
		}else {
			element.attr("value", "${" + tableName + "." + fieldName + "}");
		}
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("field", field);
		map.put("table", table);
		
		/*if (fname.startsWith("gwxzq")) {
			System.out.println(field.getPropertyMap());
		}*/
		
		String fieldHtml = parseField(map);// 根据external生成html代码
		

		external.after(fieldHtml);// 插入html

		Elements fieldElements = null;
		try {
			fieldElements = external.nextElementSibling().select("[name=" + field.getFieldName() + "]");// 获取刚刚添加到next的标签
			for (Element fieldElement : fieldElements) {
				handleExternalChild("funcexp", external, fieldElement);
				handleExternalChild("selectquery", external, fieldElement);
			}
		} catch (Exception e) {
		}
		return fieldElements;
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

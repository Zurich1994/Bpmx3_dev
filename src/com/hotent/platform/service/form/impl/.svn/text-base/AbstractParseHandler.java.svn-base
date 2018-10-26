/**
 * 描述：TODO
 * 包名：com.hotent.platform.service.form.impl
 * 文件名：AbstractParseHandler.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2014-12-15-上午9:23:19
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.service.form.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.FileUtil;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.form.IParseHandler;
import com.hotent.platform.service.system.SysCodeTemplateService;

import freemarker.template.TemplateException;

/**
 * <pre>
 * 描述：是前台生成代码的时候处理edit页面时，会获取表单的html
 * 这系列的类就是为了解释这一段html
 * 关键是每个bpmFormField的解析
 * 构建组：bpm33_cf
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-12-15-上午9:23:19
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractParseHandler implements IParseHandler {
	protected String template = "";

	@Resource
	protected FreemarkEngine freemarkEngine;
	@Resource
	protected BpmFormTableService bpmFormTableService;

	@Override
	public void setTemplate(String template) {
		this.template = template;

	}

	protected String parseField(Map<String, Object> map) {
		try {
			String html = freemarkEngine.mergeTemplateIntoString(template, map);
			return html;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String parseHtml(String html, Map<String, Object> param) {

		Document doc = Jsoup.parseBodyFragment(html);
		doc.select("p,script").remove();// 删除js和p
		//FileUtil.writeFile("C:\\Users\\User\\Desktop\\代码生成器\\原Detailhtml.txt", doc.body().toString().replace("&quot;", "'"));
		parse(doc, param);
		
		String result=doc.body().html()
				.replace("&quot;", "\"")
				.replace("&lt;", "<")
				.replace("&gt;", ">")
				.replace("foreach", "forEach")
				.replace("varstatus", "varStatus");
		
		// 处理分页
		BpmFormDef bpmFormDef = (BpmFormDef) param.get("bpmFormDef");
		// 有分页的情况。
		String tabTitle = bpmFormDef.getTabTitle();
		if (tabTitle.indexOf(BpmFormDef.PageSplitor) > -1) {
			try {
				result = getTabHtml(tabTitle, result);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取tab的html。
	 * 
	 * @param tabTitle
	 * @param html
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	private String getTabHtml(String tabTitle, String html) throws TemplateException, IOException {
		String[] aryTitle = tabTitle.split(BpmFormDef.PageSplitor);
		String[] aryHtml = html.split(BpmFormDef.PageSplitor);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < aryTitle.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", aryTitle[i]);
			map.put("html", aryHtml[i]);
			list.add(map);
		}
		String formPath = BpmFormTemplateService.getFormTemplatePath() + "tab.ftl";
		String tabTemplate = FileUtil.readFile(formPath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabList", list);
		String output = freemarkEngine.parseByStringTemplate(map, tabTemplate);
		return output;
	}
	
	/**
	 * 根据名称获取列表中的bpmFormField
	 * 
	 * @param list
	 * @param fieldName
	 * @return BpmFormField
	 * @exception
	 * @since 1.0.0
	 */
	protected BpmFormField getByFieldNameFormList(List<BpmFormField> list, String fieldName) {
		for (BpmFormField field : list) {
			if (field.getFieldName().equalsIgnoreCase(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 根据名称获取列表中的bpmFormTable
	 * 
	 * @param list
	 * @param fieldName
	 * @return BpmFormField
	 * @exception
	 * @since 1.0.0
	 */
	protected BpmFormTable getByTableNameFromList(List<BpmFormTable> list, String tableName) {
		for (BpmFormTable table : list) {
			if (table.getTableName().equals(tableName)) {
				return table;
			}
		}
		return null;
	}
	
	protected abstract void parse(Document doc, Map<String, Object> param);

}

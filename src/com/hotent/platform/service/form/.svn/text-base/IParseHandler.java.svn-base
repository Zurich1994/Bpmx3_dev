/**
 * 描述：TODO
 * 包名：com.hotent.platform.service.form
 * 文件名：IParseHandler.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2014-12-15-上午9:21:21
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.service.form;

import java.util.Map;

import com.hotent.platform.model.form.BpmFormDef;

/**
 * <pre> 
 * 描述：TODO
 * 构建组：bpm33_cf
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-12-15-上午9:21:21
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface IParseHandler {
	
	void setTemplate(String template);
	
	/**
	 * 解释html，param是参数，为什么要用map？因为预防后面还要加新参数
	 * ***************************
	 * 目前map必须包含的参数有：
	 * bpmFormDef:表单信息
	 * table:包含子表跟字段信息的table
	 * ***************************
	 * @param html
	 * @param param
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String parseHtml(String html, Map<String, Object> param);

}

package com.hotent.core.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hotent.core.util.ContextUtil;





/**
 * 国际化标签。
 * <pre>
 * &lt;script type="text/javascript" src="{ctx}/{pre}/zh_CN.js">&lt;/script>
 * pre可能的值：
 * js/lang/view/platform/bpm
 * 
 * 使用：
 * <f:js pre="js/lang/view/platform/bpm" ></f:js>
 * </pre>
 * @author sfhq282
 *
 */
public class LanguageJsTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String template="<script type=\"text/javascript\" src=\"{ctx}/{pre}/zh_CN.js\"></script>";
	
	private String pre;

	/**
	 * 前缀
	 * @param href
	 */
	public void setPre(String pre) {
		this.pre = pre;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspTagException {

		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String ctx= request.getContextPath();
			String str=template.replace("{ctx}", ctx).replace("{pre}", pre);
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}

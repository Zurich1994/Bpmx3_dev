package com.hotent.platform.tag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.hotent.platform.model.bus.BusQueryRule;

/**
 * displaytag 列权限控制标签。
 * <pre>
 * 	这个标签的权限(busQueryRule)，从控制器传递过来。
 *  在页面上如此调用。
 *  &lt;f:col name="memo" >内容&lt;/f:col>
 *  permission 为一个HashMap对象，键为权限名称，值为布尔值。
 * </pre>
 * @author ray
 *
 */
public class ColumnTag extends BodyTagSupport {
	/** */
	private static final long serialVersionUID = 1001L;
	
	private String name="";
	
	public void setName(String name){
		this.name=name;
	}
	


	public int doStartTag() throws JspTagException {
		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();	
		BusQueryRule busQueryRule =(BusQueryRule) request.getAttribute("busQueryRule");
		if(busQueryRule == null)
			return EVAL_BODY_INCLUDE;	
		Map<String,Boolean> permission = (Map<String,Boolean>)busQueryRule.getPermission();
		//没有权限信息直接显示。
		if(permission == null|| StringUtils.isEmpty(name))
			return EVAL_BODY_INCLUDE;
		Boolean b= permission.get(name);
		if(b==null || b==false)
			return SKIP_BODY;	
		else
			return EVAL_BODY_INCLUDE;
	}
	
	
	public int doEndTag() throws JspTagException {
		return SKIP_BODY;
	}

}

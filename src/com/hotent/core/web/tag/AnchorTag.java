package com.hotent.core.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.util.StringUtil;
import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SubSystemUtil;
 


/**
 * 功能权限标签。
 * <br>
 * 功能：这个标签配合权限分配，可以实现页面上的按钮是否可以点击操作，将权限操作控制到按钮。
 * <ul>
 *  <li>标签中 有个别名属性，系统根据该别名控制当前用户是否有该操作的的权限。</li>
 *  <li>标签的使用需要在tld文件和web.xml中进行配置。</li>
 *  <li>权限标签的写法:<f:a alias="site_add" css="add" href="addSite1.ht">;添加&lt;/f:a&gt;</li>
 * </ul>
 */
@SuppressWarnings("serial")
public class AnchorTag extends BodyTagSupport {
	
	private Log logger = LogFactory.getLog(AnchorTag.class);
	/** 链接的样式class */
	private String css;
	/** 链接的 别名*/
	private String alias;
	/** 链接的 name*/
	private String name;
	/** 链接的 name*/
	private String id;
	/** 链接的 href*/
	private String href;
	/** 链接的 action*/
	private String action;
	/** 链接的 onclick*/
	private String onclick;
	/** 链接的 目标*/
	private String target;
	/**
	 * 当没有权限的时候超链接是否显示。
	 */
	private boolean showNoRight=true;
	private static ThreadLocal<Map<String,Boolean>> threadLocalVar = new ThreadLocal<Map<String,Boolean>>();
	
	public static void cleanFuncRights(){
		threadLocalVar.remove();
	}
	

	public void setTarget(String target) {
		this.target = target;
	}
	
	public void setShowNoRight(boolean _isShowNoRight){
		this.showNoRight=_isShowNoRight;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	protected boolean getHasRights(String systemAlias) {
		if(StringUtil.isEmpty(systemAlias))return false;
		String key=systemAlias +"_" + this.alias;
		boolean rtn=false;
		if (threadLocalVar.get()==null) {
			// 判断别名是否可以访问。
			boolean canAccess = SecurityUtil.hasFuncPermission(systemAlias, alias);
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			map.put(key, canAccess);
			threadLocalVar.set(map);
			rtn= canAccess;
		} else {
			Map<String,Boolean> map =threadLocalVar.get();
			if(map.containsKey(key)){
				rtn= map.get(key);
			}
			else{
				boolean canAccess = SecurityUtil.hasFuncPermission(systemAlias, alias);
				map.put(key, canAccess);
				rtn= canAccess;
			}
		}
		return rtn;
	}

	private String getOutput() throws Exception
	{
		String systemAlias=SubSystemUtil.getCurrentSystemAlias((HttpServletRequest) pageContext.getRequest());

		boolean canAccess =getHasRights(systemAlias);
		/**
		 * 当没有权限不显示返回空串。
		 */
		if(!showNoRight && !canAccess){
			return "";
		}
		
		String body = this.getBodyContent().getString();
		StringBuffer content = new StringBuffer("<a ");
		if(id != null) {
			content.append("id=\"" + id + "\" ");
		}
		if(name != null) { 
			content.append("name=\"" + name + "\" ");
		}		
		if(target != null){
			content.append("target=\"" + target + "\" ");
		}
		
		//可以访问的情况。
		if(canAccess) {
			if(css != null) {
				content.append(" class=\"" + css + "\" ");
			}
			if(href != null) {
				content.append(" href=\"" + href + "\" ");
			}
			if(action != null) {
				content.append(" action=\"" + action + "\" ");
			}
		} 
		else {
			if(css != null) {
				content.append(" class=\"" + css + " disabled\" ");
			} else {
				content.append(" class=\"disabled\" ");
			}
		}
		if(onclick != null) {
			content.append("onclick=\"" + onclick + "\" ");
		}
		content.append(">");
		content.append(body);
		content.append("</a>");
		
		return content.toString();
	}
	

	public int doEndTag() throws JspTagException {

		try {
			String str = getOutput();
			pageContext.getOut().print(str);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}

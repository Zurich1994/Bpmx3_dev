package com.hotent.core.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SubSystemUtil;

/**
 * 菜单授权标签。<br>
 * 功能：<br>
 * 读取菜单的json。
 * 根据别名判断菜单项是否有权限。如果没有权限则添加一个disable的属性。
 * 
 * @author ray
 *
 */
public class MenuTag   extends BodyTagSupport{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doEndTag() throws JspTagException {
		String systAlias=SubSystemUtil.getCurrentSystemAlias((HttpServletRequest) pageContext.getRequest());
		StringBuffer sb=new StringBuffer();
		try {
			String body = this.getBodyContent().getString();
			JSONArray aryJson=JSONArray.fromObject(body);
			sb.append("[ ");
			for(int i=0;i<aryJson.size();i++){
				JSONObject jsonObj=(JSONObject)aryJson.get(i);
				sb.append("{ ");
				sb.append("text:'"+jsonObj.get("text")+"',").append("click:"+jsonObj.get("click"));
				String alias=(String)jsonObj.get("alias");
				//判断别名是否可以访问。
				boolean canAccess=SecurityUtil.hasFuncPermission(systAlias, alias);
				if(!canAccess){
					sb.append(",disable:"+true);
				}
				if(i==(aryJson.size()-1)){
					sb.append("}");
				}else{
					sb.append("},");
				}
			}
			sb.append("]");
			pageContext.getOut().print(sb.toString());
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
	

}

package com.hotent.core.web.tag;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.collections.map.HashedMap;
import org.displaytag.util.ParamEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.AppUtil;
/**
 * 分页标签,用于displaytag分页。<br/>
 * <pre>
 * 使用方法如下：
 * <b>paging.tld文件配置如下：</b>
 *  &lt;tag>
 *   &lt;description>
 *    分页标签
 *   &lt;/description>
 *   &lt;name>paging&lt;/name>
 *   &lt;tag-class>com.hotent.core.web.tag.PageTag&lt;/tag-class>
 *   &lt;body-content>JSP&lt;/body-content>
 *   &lt;attribute>
 *       &lt;name>tableId&lt;/name>
 *       &lt;required>true&lt;/required>
 *       &lt;rtexprvalue>true&lt;/rtexprvalue>
 *   &lt;/attribute>
 * &lt;/tag>
 * <b> 页面使用如下：</b>
 * tableId代表displaytag的Id。
 * &lt;hotent:paging tableId="roleItem"/>
 * </pre>
 * @author csx
 *
 */
public class PageTag extends TagSupport {
	private static Logger logger=LoggerFactory.getLogger(PageTag.class);
	
	/**
	 * 分页对应的TableID
	 */
	private String tableId;
	
	/**
	 * 是否显示 信息：  显示记录从1到20，总数 37条
	 */
	private boolean showExplain=true;
	
	/**
	 * 是否显示 每页记录数量
	 */
	private boolean showPageSize=true;
	
	public String getTableId() {
		return tableId;
	}
	
	/**
	 * 设置displaytag的Id。
	 * @param tableId
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	public boolean isShowExplain() {
		return showExplain;
	}

	public void setShowExplain(boolean showExplain) {
		this.showExplain = showExplain;
	}
	

	public boolean isShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(boolean showPageSize) {
		this.showPageSize = showPageSize;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int doStartTag() throws JspException {	
	 JspWriter out=pageContext.getOut();
	 HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	 try{
		 	
		 	FreemarkEngine freemarkEngine=(FreemarkEngine)AppUtil.getBean("freemarkEngine");
			Map model=new HashedMap();
			PageBean pb=null;
			logger.debug("table id:" + tableId);
			
			String url=null;
			
			if(tableId!=null){
				pb=(PageBean)request.getAttribute("pageBean"+tableId);
				url=(String)request.getAttribute("requestURI"+tableId);
				ParamEncoder paramEncoder=new ParamEncoder(tableId);
				model.put("tableIdCode", paramEncoder.encodeParameterName(""));
			}else{
				pb=(PageBean)request.getAttribute("pageBean");
				url+=request.getRequestURI();
				model.put("tableIdCode", "");
			}
			if(pb==null){
				throw new RuntimeException("pagingBean can't no be null");
			}
			model.put("pageBean", pb);
			
			String params=getQueryParameters(request);
			if(url.indexOf("?")>0){
				if(!"".equals(params)){
					url+="&" + params;
				}else{
					url+="?" +params;
				}
			}else if(!"".equals(params)){
				url+="?" +params;
			}
			logger.info("current url:" + url);
			model.put("showExplain", showExplain);
			model.put("showPageSize", showPageSize);
			model.put("baseHref",url);
			String html=freemarkEngine.mergeTemplateIntoString("page.ftl", model);
			out.println(html);
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }
	 return SKIP_BODY;
	}
	
	private String getQueryParameters(HttpServletRequest request){
		Enumeration names=request.getParameterNames();
		StringBuffer sb=new StringBuffer();
		int i=0;
		while(names.hasMoreElements()){
			if(i++>0){
				sb.append("&");
			}
			String name=(String)names.nextElement();
			String value=request.getParameter(name);
			sb.append(name).append("=").append(value);
		}
		return sb.toString();
	}
}

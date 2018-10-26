package com.hotent.core.web.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 排序标签。
 * <br>功能
 * <br>实现字段排序。
 * <br>标签的写法：
 * <br>&lt;f:th field="enname"&gt;英文名称&lt;/f:th&gt;
 *
 */
@SuppressWarnings("serial")
public class OrderTag extends BodyTagSupport {
	
	private static final String Asc="ASC";
	private static final String Desc="DESC";
	
	
	private String field="";
	private String order="DESC";
	
	private String ascImg="/themes/img/commons/asc.png";
	private String descImg="/themes/img/commons/desc.png";
	
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	
	private String[] aryAvoid={"sortField","orderSeq"};



	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	private String getOutput(HttpServletRequest request) throws Exception
	{
		String body = this.getBodyContent().getString();
		if(this.field==null || this.field.equals(""))
		{
			return "<th>"+body+"</th>";
		}
		String img="";
		String orderSeq=request.getParameter("orderSeq");
		String sortField=request.getParameter("sortField");
		if(orderSeq==null || !sortField.equals(this.field)) 
		{
			orderSeq=order;
		}
		else
		{
			if(orderSeq.equals(OrderTag.Desc))
			{
				orderSeq=OrderTag.Asc;
			}
			else
			{
				orderSeq=OrderTag.Desc;
			}
		}
		if(orderSeq.equals(OrderTag.Desc))
		{
			img=request.getContextPath() + descImg;
		}
		else
		{	
			img=request.getContextPath() + ascImg;
		}
		
		String url=getUrl(request);
		
		String para="sortField=" + this.field +"&orderSeq="+orderSeq ;
		if(url.indexOf("?")>-1)
			url+="&" + para;
		else
			url+="?" + para;


		StringBuffer sb=new StringBuffer();
		sb.append("<th  >");
	 	
		sb.append("<a href='"+url+"'>"+body+"<span style='vertical-align:middle;'><img border='0' src='"+img+"'/></span></a>");
	
			
		sb.append("</th>");
		return sb.toString();
	}
	
	
	private  String getUrl(HttpServletRequest request) throws Exception
    {
        StringBuffer urlThisPage=new StringBuffer();
        String url=request.getAttribute("currentPath").toString();
        if(url==null )
        {
        	throw new Exception("请在控制器中设置currentPath(当前路径)!");
        }
        
        urlThisPage.append(url);
        Enumeration e=request.getParameterNames();
        String para="";
        String values="";
        urlThisPage.append("?");
        while (e.hasMoreElements())
        {
            para=(String)e.nextElement();
            boolean rtn=isExists(para);
            if(!rtn)
            {
	            values=java.net.URLEncoder.encode( getValueByKey( request,para),"utf-8");
	            urlThisPage.append(para);
	            urlThisPage.append("=");
	            urlThisPage.append(values);
	            urlThisPage.append("&");
            }
        }
        return urlThisPage.substring(0,urlThisPage.length()-1);
    }
	
	private boolean isExists(String key)
	{
		for(String str:aryAvoid)
		{
			if(key.equals(str))
				return true;
		}
		return false;
	}
	
	private String getValueByKey(HttpServletRequest request,String key)
	{
		String rtn="";
		String[] values=request.getParameterValues(key);
		for(String str:values)
		{
			if(str!=null && !str.trim().equals(""))
			{
				rtn+=str +",";
			}
		}
		if(rtn.length()>0)
			rtn=rtn.substring(0,rtn.length()-1);
		return rtn;
	}

	public int doEndTag() throws JspTagException {

		String body = this.getBodyContent().getString();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		try {
			JspWriter writer = pageContext.getOut();
			String str = getOutput(request);
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}

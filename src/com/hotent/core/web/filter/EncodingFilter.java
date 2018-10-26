package com.hotent.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过滤器， 设置当前系统的编码。
 * 
 * @author hotent
 * 
 */
public class EncodingFilter implements Filter
{

	private String encoding = "UTF-8";
	private String contentType = "text/html;charset=UTF-8";

	public void destroy()
	{

	}

	public void doFilter(ServletRequest request, ServletResponse httpresponse,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletResponse response=(HttpServletResponse)httpresponse;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		response.setContentType(contentType);
		
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
		response.setHeader("Access-Control-Allow-Headers","*"); 
		response.setHeader("Access-Control-Allow-Origin","*"); 
		response.setHeader("Access-Control-Allow-Credentials","true"); 
		
		response.setHeader("Access-Control-Allow-Methods","POST,GET"); 
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException
	{
		String _encoding = config.getInitParameter("encoding");
		String _contentType = config.getInitParameter("contentType");
		// String ext=config.getInitParameter("ext");
		if (_encoding != null)
			encoding = _encoding;
		if (_contentType != null)
			contentType = _contentType;

	}

}

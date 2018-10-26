package com.hotent.core.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * springsecurity 访问拒绝自定义处理器。<br>
 * 接收AccessDeniedException 并交由accessDeniedUrl页面处理。<br>
 * 在处理页面中需要使用 AccessDeniedException ex=(AccessDeniedException)request.getAttribute("ex");
 * <pre>
 * 具体参考app-security.xml。
 *  &lt;security:access-denied-handler ref="htAccessDeniedHandler"/>
 * &lt;bean id="htAccessDeniedHandler" class="com.hotent.core.web.security.HtAccessDeniedHandler">
 *		&lt;property name="accessDeniedUrl" value="/403.jsp">&lt;/property>
 *	&lt;/bean>
 * </pre>
 * @author ray
 *
 */
public class HtAccessDeniedHandler implements AccessDeniedHandler {

	private String accessDeniedUrl;

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
	
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
	

	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException ex) throws IOException, ServletException {
		request.setAttribute("ex", ex);
		try{
			request.getRequestDispatcher(accessDeniedUrl).forward(request, response);
			return;
		}
		catch(Exception e){
			
		}
		
		
	}

}

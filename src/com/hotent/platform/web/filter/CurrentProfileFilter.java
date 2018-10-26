package com.hotent.platform.web.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;


/**
 * 此过滤器实现了添加当前组织的功能。
 * 1.如果当前用户没有登录则直接通过。
 * 2.如果当前在session中有组织的信息，则直接通过。
 * 3.如果session中没有组织的信息，则从cookie查看有没有组织id的信息。
 * 	  1.如果有则根据该组织id从上下文中获取到组织信息，并把它放到session中。
 *    2.没有的话则根据当前用户获取组织数据把他放到session当中。
 *  
 * 同时在HttpSessionAttributeListener监听器中进行监听这个session的变化情况，session一旦变化则
 * 调用ContextUtil.setCurrentOrg(sysOrg)方法更新当前线程中的组织对象。
 * @author ray
 *
 */
public class CurrentProfileFilter implements Filter {
	
	

	public void destroy() {
		

	}

	public void doFilter(ServletRequest reqeust, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		Long userId=ContextUtil.getCurrentUserId();
		//SessionLocaleResolver.
		SessionLocaleResolver sessionResolver=(SessionLocaleResolver)AppUtil.getBean(SessionLocaleResolver.class);
		Locale local= sessionResolver.resolveLocale((HttpServletRequest) reqeust);
		ContextUtil.setLocale(local);
		if(userId==null){
			
			filterChain.doFilter(reqeust,response);
		}
		else{
		//	ContextUtil.getCurrentPosFromSession();
			ContextUtil.getCurrentPos();
			filterChain.doFilter(reqeust,response);
		}
		

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}

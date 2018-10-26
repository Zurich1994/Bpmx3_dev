package com.hotent.core.web.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;

/**
 * springsecurity 安全过滤器。
 * 文件app-security.xml。
 * &lt;bean id="permissionFilter" class="com.hotent.core.web.filter.PermissionFilter">
 *		&lt;property name="authenticationManager" ref="authenticationManager" />
 *		&lt;property name="accessDecisionManager" ref="accessDecisionManager" />
 *		&lt;property name="securityMetadataSource" ref="securityMetadataSource" />
 *	&lt;/bean>
 * @author ray
 *
 */
public class PermissionFilter extends AbstractSecurityInterceptor implements Filter {

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		boolean canInvokeNext = canInvokeNextFilter(request, response, fi);
		if(canInvokeNext){
			invoke(fi);
		}
	}
	
	/**
	 * 判断是否是需要执行下一个过滤器<br>
	 * 目前在 非匿名 的 ajax请求 或 带有 isAjaxRequest=true 参数的情况下，需要判断session是否失效，
	 * 如果失效，则返回超时提示而不是登录页面的html代码
	 * @param request
	 * @param response
	 * @param fi
	 * @return
	 * @throws IOException
	 */
	private boolean canInvokeNextFilter(ServletRequest request, ServletResponse response, FilterInvocation fi) throws IOException{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Collection<ConfigAttribute> configAttributes = this.obtainSecurityMetadataSource().getAttributes(fi);
		// 处理 非匿名 的 ajax请求 或 带有 isAjaxRequest=true 的请求，若session失效，则返回超时提示而不是抛出异常
		// 解决在session失效时，用ajax请求，返回的结果直接是登录页面
		if(!configAttributes.contains(SystemConst.ROLE_CONFIG_ANONYMOUS)
				&& ("XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))
				|| "true".equalsIgnoreCase(httpRequest.getParameter("isAjaxRequest")))){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !(authentication.getPrincipal() instanceof SysUser)) {
				response.getWriter().print(new ResultMessage(ResultMessage.Fail, "登录超时，请重新登录！"));
				return false;
			}
		}
		return true;
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException,ServletException {
		super.setRejectPublicInvocations(false);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//此处可能会拿到 xml配置里的 <http></http> 头
	}
	
	

	

}

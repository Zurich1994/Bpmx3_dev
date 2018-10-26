package com.hotent.core.web.security;

import javax.servlet.http.HttpServletRequest;

import com.hotent.core.util.StringUtil;

/**
 * 实现抽象类AbstractDirectUrlResolver的support方法
 * by cjj
 */
public class RequestUriDirectUrlResolver extends AbstractDirectUrlResolver {  
	
	/**
	 * 获得跳转路径里的标识
	 */
    @Override  
    public boolean support(HttpServletRequest request) {
    	
        String requestURI = request.getRequestURI();  
        return requestURI.contains(this.pattern);
        
    }
    
}  

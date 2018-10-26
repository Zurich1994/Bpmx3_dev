package com.hotent.core.web.security;

import javax.servlet.http.HttpServletRequest;

/**
 * 多用户入口接口
 * by cjj
 */
public interface DirectUrlResolver {
	
	/**
	 * 获得配置配置标识用以跳转判断
	 * @param request
	 * @return
	 */
    boolean support(HttpServletRequest request);  
    
    /**
     * 获取跳转路径
     * @return
     */
    String directUrl();
    
}

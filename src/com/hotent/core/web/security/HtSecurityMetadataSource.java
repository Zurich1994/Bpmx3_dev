package com.hotent.core.web.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.system.SubSystemUtil;
import com.hotent.platform.service.system.SysRoleService;

/**
 * 实现的功能。
 * <pre>
 * 1.系统初始化时，构建系统的url和角色映射。
 * 2.并根据当前的url取出url具有的角色权限。
 * 3.实现了 BeanPostProcessor接口，保证SysRoleService，SubSystemService，ICache能在初始化时进行注入，并调用其获取系统资源。
 * </pre>
 * @author ray
 */
public class HtSecurityMetadataSource implements FilterInvocationSecurityMetadataSource ,BeanPostProcessor {
    /**具有匿名访问权限的url*/
	private  HashSet<String> anonymousUrls=new HashSet<String>();
   
	/**
	 * 设置具有匿名访问权限的url
	 * @param anonymousUrls
	 */
	public  void setAnonymousUrls(HashSet<String> anonymousUrls) {
		this.anonymousUrls = anonymousUrls;
	}

    
    /**
     * 根据当前的URL返回该url的角色集合。
     * 1.如果当前的URL在匿名访问的URL集合当中时，在当前的角色中添加匿名访问的角色(SysRole.ROLE_CONFIG_ANONYMOUS)。
     * 2.如果当前系统不存在的情况，给当前用户添加一个公共访问的角色(SysRole.ROLE_CONFIG_PUBLIC)。
     * 3.url 和角色映射，url和参数映射，给当前用户添加一个公共的角色(SysRole.ROLE_CONFIG_PUBLIC)。
     * 
     * @param url
     */
    public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException {
    	
		Collection<ConfigAttribute> configAttribute =new HashSet<ConfigAttribute>();
		
		FilterInvocation filterInvocation=((FilterInvocation)object);
    	HttpServletRequest request=filterInvocation.getRequest();
    	
    	String token= request.getParameter("curAccount");//request.getHeader("Content-Type");//
    	if(StringUtil.isNotEmpty(token)){
    		configAttribute.add(SystemConst.ROLE_CONFIG_ANONYMOUS);
    		return configAttribute;
    	}
    	
    	String url = request.getRequestURI();
    	url=removeCtx(url,request.getContextPath());
    	//匿名访问的URL
    	if(anonymousUrls.contains(url)){
    		configAttribute.add(SystemConst.ROLE_CONFIG_ANONYMOUS);
    		return configAttribute;
    	}

        //获取系统id
    	String sysAlias =SubSystemUtil.getCurrentSystemAlias(request);

    	if(sysAlias==null ) {
    		configAttribute.add(SystemConst.ROLE_CONFIG_PUBLIC);
    		return configAttribute;
    	};
    	
    	SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
    	Map<String, Object> map=sysRoleService.getUrlRightMap(sysAlias, url);
    	
    	boolean isUrlExist=(Boolean)map.get("isUrlExist");
    	
    	List<String> roleList=(List<String>)map.get("roleList");
    	
    	if(!isUrlExist ){
    		configAttribute.add(SystemConst.ROLE_CONFIG_PUBLIC);
    		return configAttribute;
    	}
    	else{
    		if(roleList.size()>0){
    			for(String role:roleList){
    	    		configAttribute.add(new SecurityConfig(role));
    	    	}
    		}
    	}
    	
    	return configAttribute;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    /**
     * 返回系统中所有为url分配了的权限
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
    	return null;
    }
    

	
	
	
	/**
	 * 获取当前URL
	 * @param url
	 * @param contextPath
	 * @return
	 */
	private static String removeCtx(String url,String contextPath){
		url=url.trim();
		if(StringUtil.isEmpty(contextPath)) return url;
		if(StringUtil.isEmpty(url)) return "";
		if(url.startsWith(contextPath)){
			url=url.replaceFirst(contextPath, "");
		}
		return url;
	}
	
	/**
	 * 保证资源只被初始化一次。
	 */
	boolean isInit=false;
	
	/**
	 * 保证service的注入。
	 * 获取系统资源。
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
	
		
//		if(bean instanceof ICache){
//			this.iCache=(ICache)bean;
//		}
		
//		if(this.sysRoleService!=null && this.subSystemService!=null && iCache!=null ){
//			if(!isInit){
////				SecurityUtil.loadRes();
//				isInit=true;
//			}
//		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		return bean;
	}


	

}

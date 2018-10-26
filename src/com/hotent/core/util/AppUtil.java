package com.hotent.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import com.hotent.core.model.OnlineUser;


/**
 * 获取ApplicationContext， 调用方法 SpringContextUtil.getContext();<br>
 * 在spring文件中配置方法：<br>
 * <bean id="springContextUtil" class="com.hotent.core.util.AppUtil" />
 * @author hotent
 *
 */
public class AppUtil implements ApplicationContextAware {

	private static  ApplicationContext applicationContext;
	
	
	
	/**
	 * 在线用户
	 */
	private static Map<Long,OnlineUser> onlineUsers=new LinkedHashMap<Long, OnlineUser>();
	
	
	private static ServletContext servletContext;
	
	
	/**
	 * 
	 * @param servletContext
	 */
	public static void init(ServletContext _servletContext)
	{
		servletContext=_servletContext;
	}
	
	/**
	 * 获取web应用的ServletContext对象。
	 * @return
	 * @throws Exception
	 */
	public static ServletContext getServletContext() throws Exception{
		return servletContext;
	}

	/**
	 * spring启动时注入context
	 */
	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		applicationContext=contex;
	}

	/**
	 * 获取spring的上下文。
	 * @return
	 */
	public static ApplicationContext getContext(){
		return applicationContext;
	}
	
	
	/**
	 * 根据指定的接口或基类获取实现类列表。
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<Class> getImplClass(Class clazz) throws ClassNotFoundException{
		List<Class> list=new ArrayList<Class>();
		
		Map map= applicationContext.getBeansOfType(clazz);
		for(Object obj : map.values()){
			String name=obj.getClass().getName();
			int pos=name.indexOf("$$");
			if(pos>0){
				name=name.substring(0,name.indexOf("$$")) ; 
			}
			Class cls= Class.forName(name);
			
			list.add(cls);
		}
		return list;
	}
	
	
	/**
	 * 获取接口的实现类实例。
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Map<String,Object> getImplInstance(Class clazz) throws ClassNotFoundException{
		
		Map map= applicationContext.getBeansOfType(clazz);
		
		return map;
	}
	

	
	/**
	 * 根据类从spring上下文获取bean。
	 * @param cls
	 * @return
	 */
	public  static <C> C getBean(Class<C> cls){
		return applicationContext.getBean(cls);
	}
	
	/**
	 * 根据类名从spring上下文获取bean。
	 * @param cls
	 * @return
	 */
	public static Object getBean(String  beanId){
		return applicationContext.getBean(beanId);
	}

	/**
	 * 取得应用程序的绝对路径
	 * @return
	 */
	public static String getAppAbsolutePath(){
		return servletContext.getRealPath("/");
	}
	
	/**
	 * 在web环境中根据web页面的路径获取对应页面的绝对路径。
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path){
		return servletContext.getRealPath(path);
	}
	
	public static Map<Long, OnlineUser> getOnlineUsers() {
		return onlineUsers;
	}
	
	/**
	 * 获取Classpath物理路径
	 * @return
	 */
	public static String getClasspath(){
		 String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		 String rootPath  = "";
		  //windows下
		  if("\\".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("/", "\\");
		  }
		  //linux下
		  if("/".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("\\", "/");
		  }
		  return rootPath;
	}
	
	/**
	 * Spring发布事件。
	 * @param applicationEvent
	 */
	public static void publishEvent(ApplicationEvent applicationEvent){
		applicationContext.publishEvent(applicationEvent);
	}


}

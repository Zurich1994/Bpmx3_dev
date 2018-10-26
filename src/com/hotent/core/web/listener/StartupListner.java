package com.hotent.core.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ClassLoadUtil;

/**
 * Spring启动监听器。<br/>
 * 用于注入servletContext和applicationContext。
 * <pre>
 * 在webxml配置如下：
 * &lt;listener>
 *       &lt;listener-class>com.hotent.core.web.listener.StartupListner&lt;/listener-class>
 *   &lt;/listener>
 *  <pre>
 * @author csx
 *
 */
public class StartupListner extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ClassLoadUtil.contextInitialized(event);
	}
}

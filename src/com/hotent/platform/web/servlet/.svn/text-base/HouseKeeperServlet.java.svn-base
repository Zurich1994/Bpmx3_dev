package com.hotent.platform.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.logicalcobwebs.proxool.ProxoolFacade;

/*
 * 此类用来处理 在class类进行修改的时候 保存了之后服务自动重新启动 报：
 *  Exception in thread "HouseKeeper" java.lang.NullPointerException
 *  错误原因为：
 *  This is because Proxool is not being shutdown properly. 
 *  If the JVM stops then Proxool recognises that and shuts down gracefully, 
 *  but if you redeploy Proxool into some environments (for example, a servlet container) 
 *  then Proxool needs to be explicitly told so by calling ProxoolFacade.shutdown(). 
 *  If you have a servlet container then you could put it in the servlet's destroy() method. 
 *  Alternatively, use the ServletConfigurator to both configure and shutdown Proxool 
 */
public class HouseKeeperServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4829418704873725291L;

	public void destroy() {
		// 此处添加处理
		ProxoolFacade.shutdown();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

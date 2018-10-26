package com.hotent.platform.controller.console;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysErrorLog;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysErrorLogService;

/**
 * 异常
 * @author sfhq284
 *
 */
@Controller
@RequestMapping("/error.ht")
public class ErrorController {
	@Resource
	private SysErrorLogService sysErrorLogService;
	
	@RequestMapping("*")
	@Action(description="Exception异常")
	public ModelAndView error(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		Exception exception=(Exception) request.getAttribute("javax.servlet.error.exception");
		
	    String errorurl = getErrorUrl(request);
	
	    String ip =RequestUtil.getIpAddr(request);	
		
		SysUser sysUser=ContextUtil.getCurrentUser();
		
		String account= sysUser.getAccount();
		String error = ExceptionUtil.getExceptionMessage(exception);

		Long id = UniqueIdUtil.genId();
		SysErrorLog sysErrorLog =  new SysErrorLog();
		sysErrorLog.setId(id);
		sysErrorLog.setHashcode(error.hashCode()+"");
		sysErrorLog.setAccount(account);
		sysErrorLog.setIp(ip);
		sysErrorLog.setError(error);
		sysErrorLog.setErrorurl(StringUtils.substring(errorurl, 0, 1000));
		sysErrorLog.setErrordate(new Date());
		sysErrorLogService.add(sysErrorLog);
//		
		return new ModelAndView("error.jsp").addObject("errorCode",id);
	}
	
	private String getErrorUrl(HttpServletRequest request){
		
		String url=request.getAttribute("javax.servlet.error.request_uri").toString();
		StringBuffer urlThisPage = new StringBuffer();
		urlThisPage.append(url);
		Enumeration<?> e = request.getParameterNames();
		String para = "";
		String values = "";
		urlThisPage.append("?");
		while (e.hasMoreElements()) {
			para = (String) e.nextElement();
			values = request.getParameter(para);
			urlThisPage.append(para);
			urlThisPage.append("=");
			urlThisPage.append(values);
			urlThisPage.append("&");
		}
		return urlThisPage.substring(0, urlThisPage.length() - 1);
		
	}
}

package com.hotent.platform.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysErrorLogService;

/**
 * 手机基类
 * 
 * @author zxh
 * 
 */
public class MobileBaseController extends BaseController {

	/**
	 * 
	 */
//	protected Map<String, Object> map = new HashMap<String, Object>();
	
	@Resource
	private SysErrorLogService sysErrorLogService;

//	public Map<String, Object> getMap() {
//		return map;
//	}
//
//	public void setMap(Map<String, Object> map) {
//		this.map = map;
//	}

	/**
	 * 初始化Map
	 * 
	 * @return
	 */
//	public Map<String, Object> initMap() {
//		map = new HashMap<String, Object>();
//		return map;
//	}

	/**
	 * 返回分页数据
	 * 
	 * @param list
	 * @param filter
	 * @return
	 */
	public Map<String, Object> getPageList(List<?> list, QueryFilter filter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("results", list);
		map.put("totalCounts", filter.getPageBean().getTotalCount());
		return map;
	}

	/**
	 * 返回分页数据（不带总记录数）
	 * 
	 * @param list
	 *            分页
	 * @return
	 */
	public Map<String, Object> getPageList(List<?> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("results", list);
		map.put("totalCounts", list.size());
		return map;
	}

	/**
	 * 返回错误的消息
	 * 
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getError(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.error" : msg;
		map.put("success", false);
		map.put("msg", getText(msg));
		return map;
	}
	
	/**
	 * 返回错误的消息
	 * @param msg
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getError(String msg,Locale locale) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.error" : msg;
		map.put("success", false);
		map.put("msg", getText(msg,locale));
		return map;
	}
	/**
	 * 返回错误的消息
	 * @param msg
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getError(String msg, Object[] args, Locale locale) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.error" : msg;
		map.put("success", false);
		map.put("msg", getText(msg,args,locale));
		return map;
	}


	/**
	 * 返回错误的消息
	 * @param msg
	 * @param arg
	 * @return
	 */
	public Map<String, Object> getError(String msg,Object[] arg) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.error" : msg;
		map.put("success", false);
		map.put("msg", ContextUtil.getMessages(msg,arg));
		return map;
	}
	
	/**
	 * 增加错误消息
	 */
	public Map<String, Object> addError(HttpServletRequest request,SysUser sysUser,String msg,String error){
	    String ip =RequestUtil.getIpAddr(request);
		String errorUrl = RequestUtil.getErrorUrl(request);
		//添加错误消息到日志
		Long errorCode  =sysErrorLogService.addError(sysUser.getAccount(), ip, error, errorUrl);
		Object[] params = {errorCode};
		return getError(msg,params);
		
	}
	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getSuccess(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.success" : msg;
		map.put("success", true);
		map.put("msg",ContextUtil.getMessages(msg));
		return map;
	}

	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getSuccess(String msg,Object[] arg) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.success" : msg;
		map.put("success", true);
		map.put("msg",ContextUtil.getMessages(msg,arg));
		return map;
	}
	
	/**
	 * 返回成功的消息
	 * @param msg
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getSuccess(String msg,Locale locale) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.success" : msg;
		map.put("success", true);
		map.put("msg", getText(msg,locale));
		return map;
	}
	
	/**
	 * 返回错误的消息
	 * @param msg
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getSuccess(String msg, Object[] args, Locale locale) {
		Map<String, Object> map = new HashMap<String, Object>();
		msg= StringUtils.isEmpty(msg) ? "mobile.success" : msg;
		map.put("success", false);
		map.put("msg", getText(msg,args,locale));
		return map;
	}


	
	/**
	 * 获取当地的语言key
	 * @param code
	 * @return
	 */
	public static Locale getLocale() {
		Locale locale =ContextUtil.getLocale();
		if(locale!= null)
			locale = new Locale(locale.toString());
		return 	locale;
	}
	
	
	/**
	 * 获取当地的语言key
	 * @param code
	 * @return
	 */
	public static String getMessage(String code) {
		return 	ContextUtil.getMessagesL(code,getLocale());
	}
}

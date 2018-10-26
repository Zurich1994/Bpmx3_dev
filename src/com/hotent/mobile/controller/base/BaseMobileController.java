package com.hotent.mobile.controller.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
public class BaseMobileController extends BaseController {
	/**
	 * 成功的类型
	 */
	public final static Boolean TYPE_SUCCESS = true;
	/**
	 * 错误的类型
	 */
	public final static Boolean TYPE_ERROR = false;

	@Resource
	private SysErrorLogService sysErrorLogService;

	/**
	 * 返回分页数据
	 * 
	 * @param list
	 *            分页列表数据
	 * @param totalCounts
	 *            总记录数
	 * @return 返回分页数据
	 */
	public String getPageList(List<?> list, int totalCounts) {
		// JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new
		// String[] { DateUtil.DATE_FORMAT_YMD,DateUtil.DATE_FORMAT_FULL })));
		JSONObject json = new JSONObject();
		json.element("results", JSONArray.fromObject(list));
		json.element("totalCounts", totalCounts);
		return json.toString();
	}

	/**
	 * 返回分页数据
	 * 
	 * @param list
	 *            分页列表数据
	 * @param totalCounts
	 *            总记录数
	 * @param params
	 *            其它需要转化为json的参数
	 * @return 返回分页数据
	 */
	public String getPageList(List<?> list, int totalCounts,
			Map<String, Object> params) {
		JSONObject json = new JSONObject();
		json.element("results", JSONArray.fromObject(list));
		json.element("totalCounts", totalCounts);
		json.element("success", "true");
		Set<String> sets = params.keySet();
		for (String s : sets) {
			Object obj = params.get(s);
			if (obj instanceof String) {
				json.element(s, obj.toString());
			}
		}
		return json.toString();
	}

	/**
	 * 返回分页数据
	 * 
	 * @param list
	 *            分页列表数据
	 * @param filter
	 *            查询过滤
	 * @return 返回分页数据
	 */
	public String getPageList(List<?> list, QueryFilter filter) {
		return getPageList(list, filter.getPageBean().getTotalCount());
	}

	/**
	 * 返回分页数据
	 * 
	 * @param request
	 * @param list
	 * @return
	 */
	private String getCallbackPageList(HttpServletRequest request, List<?> list) {

		return getCallbackData(request, getPageList(list, list.size()));
	}

	/**
	 * 返回分页jsonp数据
	 * 
	 * @param request
	 *            request请求
	 * @param list
	 *            分页列表数据
	 * @param filter
	 *            查询过滤
	 * @return 返回分页数据
	 */
	public String getCallbackPageList(HttpServletRequest request, List<?> list,
			QueryFilter filter) {
		return getCallbackData(request, getPageList(list, filter));
	}

	/**
	 * 返回分页jsonp数据
	 * 
	 * @param request
	 *            request请求
	 * @param list
	 *            分页列表数据
	 * @param filter
	 *            查询过滤
	 * @return 返回分页数据
	 */
	public String getCallbackPageList(HttpServletRequest request, List<?> list,
			int total) {
		return getCallbackData(request, getPageList(list, total));
	}

	/**
	 * 返回分页jsonp数据
	 * 
	 * @param request
	 *            request请求
	 * @param list
	 *            分页列表数据
	 * @param filter
	 *            查询过滤
	 * @return 返回分页数据
	 */
	public String getCallbackPageList(HttpServletRequest request, List<?> list,
			QueryFilter filter, Map<String, Object> param) {
		if (param.containsKey("userCount")) {
			if (param.get("userCount").toString().equals("0")
					&& param.containsKey("orgCount")
					&& !param.get("orgCount").toString().equals("0")) {
				return getCallbackData(
						request,
						getPageList(list, Integer.valueOf(param.get("orgCount")
								.toString()), param));
			} else {
				return getCallbackData(
						request,
						getPageList(list, Integer.valueOf(param
								.get("userCount").toString()), param));
			}
		} else {
			return getCallbackData(
					request,
					getPageList(list, filter.getPageBean() != null ? filter
							.getPageBean().getTotalCount() : 0, param));
		}
	}

	/**
	 * 返回callback的jsonp 的数据
	 * 
	 * @param request
	 *            request请求
	 * @param json
	 *            传入的json 数据
	 * @return 返回 jsonp 的数据 callback([]);
	 */
	public String getCallbackData(HttpServletRequest request, String json) {
		return RequestUtil.getString(request, "callback") + "(" + json + ")";
	}

	/**
	 * 返回消息
	 * 
	 * @param msg
	 *            消息
	 * @param type
	 *            消息类型
	 * @param args
	 *            参数
	 * @param locale
	 *            当地语言
	 * @return 返回消息
	 */
	public String getMessage(String msg, Boolean type, Object[] args,
			Locale locale) {
		// 消息为空返回默认消息
		if (StringUtils.isEmpty(msg))
			msg = type ? "mobile.success" : "mobile.error";
		if (locale == null)
			locale = getLocale();
		JSONObject json = new JSONObject();
		json.element("success", type ? true : false);
		json.element("msg", getText(msg, args, locale));
		return json.toString();
	}

	/**
	 * 返回消息
	 * 
	 * @param map
	 *            map 参数
	 * @param type
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getMessage(Map<String, Object> map, Boolean type) {
		JSONObject json = new JSONObject();
		json.element("success", type ? true : false);
		for (Iterator<Entry<String, Object>> iterator = map.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<String, Object> e = iterator.next();
			json.element(e.getKey(), e.getValue());
		}
		return json.toString();
	}

	/**
	 * 返回成功的消息
	 * 
	 * @param map
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getSuccess(Map<String, Object> map) {
		return getMessage(map, TYPE_SUCCESS);

	}

	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 *            消息
	 * @param args
	 *            参数
	 * @param locale
	 *            当地语言
	 * @return 返回成功的消息
	 */
	public String getSuccess(String msg, Object[] args, Locale locale) {
		return getMessage(msg, TYPE_SUCCESS, args, locale);
	}

	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 *            消息
	 * @param locale
	 *            当地语言
	 * @return 返回成功的消息
	 */
	public String getSuccess(String msg, Locale locale) {
		return getSuccess(msg, null, locale);
	}

	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 *            消息
	 * @param args
	 *            参数
	 * @return 返回成功的消息
	 */
	public String getSuccess(String msg, Object[] args) {
		return getSuccess(msg, args, null);
	}

	/**
	 * 返回成功的消息
	 * 
	 * @param msg
	 *            消息
	 * @return 返回成功的消息
	 */
	public String getSuccess(String msg) {
		return getSuccess(msg, null, null);
	}

	/**
	 * 返回错误的消息
	 * 
	 * @param msg
	 *            消息
	 * @param args
	 *            参数
	 * @param locale
	 *            当地语言
	 * @return 返回错误的消息
	 */
	public String getError(String msg, Object[] args, Locale locale) {
		return getMessage(msg, TYPE_ERROR, args, locale);
	}

	/**
	 * 返回错误的消息
	 * 
	 * @param msg
	 *            消息
	 * @param locale
	 *            当地语言
	 * @return 返回错误的消息
	 */
	public String getError(String msg, Locale locale) {
		return getError(msg, null, locale);
	}

	/**
	 * 返回错误的消息
	 * 
	 * @param msg
	 *            消息
	 * @param args
	 *            参数
	 * @return 返回错误的消息
	 */
	public String getError(String msg, Object[] args) {
		return getError(msg, args, null);
	}

	/**
	 * 返回错误的消息
	 * 
	 * @param msg
	 *            消息
	 * @return 返回错误的消息
	 */
	public String getError(String msg) {
		return getError(msg, null, null);
	}

	/**
	 * 返回成功的jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @throws IOException
	 */
	public void returnCallbackSuccessData(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		returnCallbackData(request, response, getSuccess(map));
	}

	/**
	 * 返回成功的jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public void returnCallbackSuccessData(HttpServletRequest request,
			HttpServletResponse response, String msg) throws IOException {
		returnCallbackData(request, response, getSuccess(msg));
	}

	/**
	 * 返回错误的jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public void returnCallbackErrorData(HttpServletRequest request,
			HttpServletResponse response, String msg) throws IOException {
		returnCallbackData(request, response, getError(msg));
	}

	/**
	 * 返回数据
	 * 
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	public void returnData(HttpServletResponse response, String str)
			throws IOException {
		response.getWriter().print(str);
	}

	/**
	 * 返回jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	public void returnCallbackData(HttpServletRequest request,
			HttpServletResponse response, String str) throws IOException {
		response.getWriter().print(getCallbackData(request, str));
	}

	/**
	 * 返回jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param list
	 * @param filter
	 * @throws IOException
	 */
	public void returnCallbackPageList(HttpServletRequest request,
			HttpServletResponse response, List<?> list, QueryFilter filter)
			throws IOException {
		response.getWriter().print(getCallbackPageList(request, list, filter));
	}

	/**
	 * 返回jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param list
	 * @param filter
	 * @throws IOException
	 */
	public void returnCallbackPageList(HttpServletRequest request,
			HttpServletResponse response, List<?> list, QueryFilter filter,
			Map<String, Object> param) throws IOException {
		response.getWriter().print(
				getCallbackPageList(request, list, filter, param));
	}

	/**
	 * 返回jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param list
	 * @param filter
	 * @throws IOException
	 */
	public void returnCallbackPageList(HttpServletRequest request,
			HttpServletResponse response, List<?> list) throws IOException {
		response.getWriter().print(getCallbackPageList(request, list));
	}

	/**
	 * 返回jsonp数据
	 * 
	 * @param request
	 * @param response
	 * @param list
	 * @param filter
	 * @throws IOException
	 */
	public void returnCallbackPageList(HttpServletRequest request,
			HttpServletResponse response, List<?> list, int total)
			throws IOException {
		response.getWriter().print(getCallbackPageList(request, list, total));
	}

	/**
	 * 增加错误消息 并返回错误消息
	 * 
	 * @param request
	 *            请求的 request
	 * @param sysUser
	 *            用户
	 * @param msg
	 *            消息
	 * @param error
	 *            错误消息
	 * @return
	 */
	public String addErrorCode(HttpServletRequest request, SysUser sysUser,
			String msg, String error) {
		String ip = RequestUtil.getIpAddr(request);
		String errorUrl = RequestUtil.getErrorUrl(request);
		// 添加错误消息到日志
		Long errorCode = sysErrorLogService.addError(sysUser.getAccount(), ip,
				error, errorUrl);
		Object[] params = { errorCode };
		return getError(msg, params);
	}

	/**
	 * 获取当地的语言key
	 * 
	 * @return
	 */
	public static Locale getLocale() {
		Locale locale = ContextUtil.getLocale();
		if (locale != null)
			locale = new Locale(locale.toString().toLowerCase());
		return locale;
	}

}

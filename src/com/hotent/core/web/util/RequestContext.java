package com.hotent.core.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <pre> 
 * 描述：HttpServetRequest上下文
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-4-下午3:37:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class RequestContext {
	
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	public static void setHttpServletRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	/**
	 * 清除request和response线程变量 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void clearHttpReqResponse() {
		requestLocal.remove();
		responseLocal.remove();
	}

	/**
	 * 
	 * @param response 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void setHttpServletResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	/**
	 * 获取当前请求的Request，需要保证AopFilter在web.xml里配置才能取到
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return requestLocal.get();
	}

	/**
	 * 返回response。
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return responseLocal.get();
	}


}

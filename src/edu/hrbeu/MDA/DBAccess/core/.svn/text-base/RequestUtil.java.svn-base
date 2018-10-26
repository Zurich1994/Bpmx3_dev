package edu.hrbeu.MDA.DBAccess.core;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 扩展HttpServletRequest的功能，所有请求参数获取都通过该类方法来获取。
 */
public class RequestUtil {

	private static ThreadLocal<HttpServletRequest> reqLocal = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	private static Logger logger = LoggerFactory.getLogger(QueryFilter.class);
	
	public static final String RETURNURL="returnUrl";

	public static void setHttpServletRequest(HttpServletRequest request) {
		reqLocal.set(request);
	}

	/**
	 * 清除request和response线程变量。
	 */
	public static void clearHttpReqResponse() {
		reqLocal.remove();
		responseLocal.remove();
	}

	/**
	 * 设置HttpServletResponse response。
	 * 
	 * @param response
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
		return reqLocal.get();
	}

	/**
	 * 返回response。
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return responseLocal.get();
	}

	private RequestUtil() {
	}

	/**
	 * 取字符串类型的参数。 如果取得的值为null，则返回默认字符串。
	 * 
	 * @param request
	 * @param key
	 *            字段名
	 * @param defaultValue
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key,
			String defaultValue,boolean b) {
		String value = request.getParameter(key);
		if (StringUtil.isEmpty(value))
			return defaultValue;
		if(b){
			return value.replace("'", "''").trim();
		}else{
			return value.trim();
		}
	}

	/**
	 * 取字符串类型的参数。 如果取得的值为null，则返回空字符串。
	 * 
	 * @param request
	 * @param key  字段名
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key) {
		return getString(request, key, "",true);
	}
	
	/**
	 * 取字符串类型的参数。 如果取得的值为null，则返回空字符串。
	 * 
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key,
			boolean b) {
		return getString(request, key, "", b);
	}
	/**
	 * 取字符串类型的参数。 如果取得的值为null，则返回空字符串。
	 * 
	 * @param request
	 * @param key
	 * @param b 是否替换'为''
	 * @return
	 */
	public static String getString(HttpServletRequest request, String key,
			String defaultValue) {
		return getString(request, key, defaultValue, true);
	}
	
	/**
	 * 
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStringAry(HttpServletRequest request, String key){
		String[] aryValue = request.getParameterValues(key);
		if(aryValue==null || aryValue.length==0){
			return "";
		}
		String tmp="";
		for(String v:aryValue){
			if("".equals(tmp)){
				tmp+=v;
			}
			else{
				tmp+="," + v;
			}
		}
		return tmp;
	}
	/**
	 * 取得安全字符串。
	 * 
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getSecureString(HttpServletRequest request,
			String key, String defaultValue) {
		String value = request.getParameter(key);
		if (StringUtil.isEmpty(value))
			return defaultValue;
		return filterInject(value);

	}

	/**
	 * 取得安全字符串，防止程序sql注入，脚本攻击。
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getSecureString(HttpServletRequest request, String key) {
		return getSecureString(request, key, "");
	}

	/**
	 * 过滤script|iframe|\\||;|exec|insert|select|delete|update|count|chr|truncate
	 * |char字符串 防止SQL注入
	 * 
	 * @param str
	 * @return
	 */
	public static String filterInject(String str) {
		String injectstr = "\\||;|exec|insert|select|delete|update|count|chr|truncate|char";
		Pattern regex = Pattern.compile(injectstr, Pattern.CANON_EQ
				| Pattern.DOTALL | Pattern.CASE_INSENSITIVE
				| Pattern.UNICODE_CASE);
		Matcher matcher = regex.matcher(str);
		str = matcher.replaceAll("");
		str = str.replace("'", "''");
		str = str.replace("-", "—");
		str = str.replace("(", "（");
		str = str.replace(")", "）");
		str = str.replace("%", "％");

		return str;
	}

	/**
	 * 从Request中取得指定的小写值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getLowercaseString(HttpServletRequest request,
			String key) {
		return getString(request, key).toLowerCase();
	}

	/**
	 * 从request中取得int值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static int getInt(HttpServletRequest request, String key) {
		return getInt(request, key, 0);
	}

	/**
	 * 从request中取得int值,如果无值则返回缺省值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static int getInt(HttpServletRequest request, String key,
			int defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		return Integer.parseInt(str);

	}

	/**
	 * 从Request中取得long值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static long getLong(HttpServletRequest request, String key) {
		return getLong(request, key, 0);
	}

	/**
	 * 取得长整形数组
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Long[] getLongAry(HttpServletRequest request, String key) {
		String[] aryKey = request.getParameterValues(key);
		if (BeanUtils.isEmpty(aryKey))
			return null;
		Long[] aryLong = new Long[aryKey.length];
		for (int i = 0; i < aryKey.length; i++) {
			aryLong[i] = Long.parseLong(aryKey[i]);
		}
		return aryLong;
	}

	/**
	 * 根据一串逗号分隔的长整型字符串取得长整形数组
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Long[] getLongAryByStr(HttpServletRequest request, String key) {
		String str = request.getParameter(key);
		if(StringUtil.isEmpty(str)) return null;
		String[] aryId = str.split(",");
		Long[] lAryId = new Long[aryId.length];
		for (int i = 0; i < aryId.length; i++) {
			lAryId[i] = Long.parseLong(aryId[i]);
		}
		return lAryId;
	}

	/**
	 * 根据一串逗号分隔的字符串取得字符串形数组
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String[] getStringAryByStr(HttpServletRequest request,
			String key) {
		String str = request.getParameter(key);
		if(StringUtil.isEmpty(str)) return null;
		String[] aryId = str.split(",");
		String[] lAryId = new String[aryId.length];
		for (int i = 0; i < aryId.length; i++) {
			lAryId[i] = (aryId[i]);
		}
		return lAryId;
	}

	/**
	 * 根据键值取得整形数组
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Integer[] getIntAry(HttpServletRequest request, String key) {
		String[] aryKey = request.getParameterValues(key);
		if(BeanUtils.isEmpty(aryKey)) return null;
		Integer[] aryInt = new Integer[aryKey.length];
		for (int i = 0; i < aryKey.length; i++) {
			aryInt[i] = Integer.parseInt(aryKey[i]);
		}
		return aryInt;
	}

	public static Float[] getFloatAry(HttpServletRequest request, String key) {
		String[] aryKey = request.getParameterValues(key);
		if(BeanUtils.isEmpty(aryKey)) return null;
		Float[] fAryId = new Float[aryKey.length];
		for (int i = 0; i < aryKey.length; i++) {
			fAryId[i] = Float.parseFloat(aryKey[i]);
		}
		return fAryId;
	}

	/**
	 * 从Request中取得long值,如果无值则返回缺省值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static long getLong(HttpServletRequest request, String key,
			long defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		return Long.parseLong(str.trim());
	}

	
	/**
	 * 从Request中取得long值,如果无值则返回缺省值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Long getLong(HttpServletRequest request, String key,
			Long defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		return Long.parseLong(str.trim());
	}
	/**
	 * 从Request中取得float值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static float getFloat(HttpServletRequest request, String key) {
		return getFloat(request, key, 0);
	}

	/**
	 * 从Request中取得float值,如无值则返回缺省值
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static float getFloat(HttpServletRequest request, String key,
			float defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		return Float.parseFloat(request.getParameter(key));
	}

	/**
	 * 从Request中取得boolean值,如无值则返回缺省值 false, 如值为数字1，则返回true
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(HttpServletRequest request, String key) {
		return getBoolean(request, key, false);
	}

	/**
	 * 从Request中取得boolean值 对字符串,如无值则返回缺省值, 如值为数字1，则返回true
	 * 
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(HttpServletRequest request, String key,
			boolean defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		if (StringUtils.isNumeric(str))
			return (Integer.parseInt(str) == 1 ? true : false);
		return Boolean.parseBoolean(str);
	}

	/**
	 * 从Request中取得boolean值,如无值则返回缺省值 0
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Short getShort(HttpServletRequest request, String key) {
		return getShort(request, key, (short) 0);
	}

	/**
	 * 从Request中取得Short值 对字符串,如无值则返回缺省值
	 * 
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Short getShort(HttpServletRequest request, String key,
			Short defaultValue) {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return defaultValue;
		return Short.parseShort(str);
	}

	/**
	 * 从Request中取得Date值,如无值则返回缺省值,如有值则返回 yyyy-MM-dd HH:mm:ss 格式的日期,或者自定义格式的日期
	 * 
	 * @param request
	 * @param key
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(HttpServletRequest request, String key,
			String style) throws ParseException {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return null;
		if (StringUtil.isEmpty(style))
			style = "yyyy-MM-dd HH:mm:ss";
		return (Date) DateFormatUtil.parse(str, style);
	}

	/**
	 * 从Request中取得Date值,如无值则返回缺省值null, 如果有值则返回 yyyy-MM-dd 格式的日期
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(HttpServletRequest request, String key)
			throws ParseException {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return null;
		return (Date) DateFormatUtil.parseDate(str);

	}

	/**
	 * 从Request中取得Date值,如无值则返回缺省值 如有值则返回 yyyy-MM-dd HH:mm:ss 格式的日期
	 * 
	 * @param request
	 * @param key
	 * @return
	 * @throws ParseException
	 */
	public static Date getTimestamp(HttpServletRequest request, String key)
			throws ParseException {
		String str = request.getParameter(key);
		if (StringUtil.isEmpty(str))
			return null;
		return (Date) DateFormatUtil.parseDateTime(str);
	}

	/**
	 * 取得当前页URL,如有参数则带参数
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request) {
		StringBuffer urlThisPage = new StringBuffer();
		urlThisPage.append(request.getRequestURI());
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

	/**
	 * 取得上一页的路径。
	 * 
	 * @param request
	 * @return
	 */
	public static String getPrePage(HttpServletRequest request) {
		if(StringUtil.isEmpty(request.getParameter(RETURNURL))){
			return request.getHeader("Referer");
		}
		return request.getParameter(RETURNURL);
	}
	
	/**
	 * 只对查询的参数进行处理
	 * <pre>
	 * Q_参数名称_参数类型
	 * </pre>
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getQueryParams(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement().toString();
			String[] values = request.getParameterValues(key);
			if (values.length > 0 && StringUtils.isNotEmpty(values[0])) {
				if (key.startsWith("Q_")) {
					String[] aryParaKey = key.split("_");
					if (aryParaKey.length < 3)
						continue;
					if (values.length == 1) {
						String val = values[0].trim();
						if (StringUtil.isNotEmpty(val)) 
							map.put(key, values[0].trim());
					} 
					else 
						map.put(key, values);
				} 
			}
		}
		return map;
	}

	/**
	 * 将参数放到map中。<br>
	 * 
	 * <pre>
	 * 1.如果是需要分页的情况，参数需要做如下配置：
	 * Q_参数名称_参数类型
	 * 
	 * 可以使用的类型如下：
	 * S：字符串
	 * L：长整型
	 * N：整形
	 * DB:double
	 * BD：decimal
	 * FT:浮点型数据
	 * SN：short数据
	 * DL：开始时间
	 * DG:结束时间
	 * SL:字符串 模糊查询
	 * SLL:字符串 左模糊查询
	 * SLR:字符串 右模糊查询
	 * 2.如果参数不需要分页的情况可以不用按照上面的方式传递参数。
	 * 
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> getQueryMap(HttpServletRequest request) {
		// TYPE
		// 参数名:Q_PARANAME_TYPE
		// sortField,orderSeq
		Map map = new HashMap();
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement().toString();
			String[] values = request.getParameterValues(key);
			if (key.equals("sortField") || key.equals("orderSeq")
					|| key.equals("sortColumns")) {
				String val = values[0].trim();
				if (StringUtil.isNotEmpty(val)) {
					map.put(key, values[0].trim());
				}
			}
			if (values.length > 0 && StringUtils.isNotEmpty(values[0])) {
				if (key.startsWith("Q_")) {
					addParameter(key, values, map);
				} else {
					if (values.length == 1) {
						String val = values[0].trim();
						if (StringUtil.isNotEmpty(val)) {
							map.put(key, values[0].trim());
						}
					} else {
						map.put(key, values);
					}
				}
			}
		}
		return map;
	}

	private static void addParameter(String key, String[] values,
			Map<String, Object> map) {
		String[] aryParaKey = key.split("_");
		if (aryParaKey.length < 3)
			return;
		String paraName = key.substring(2, key.lastIndexOf("_"));
		String type = key.substring(key.lastIndexOf("_") + 1);
		if (values.length == 1) {
			String value = values[0].trim();
			if (StringUtil.isNotEmpty(value)) {
				try {
					if (value.indexOf("_") != -1) {//_特殊字符处理
						value = value.replaceAll("_", "\\\\_");
					}
					Object obj = getObjValue(type, value);
					map.put(paraName, obj);
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
			}
		} else {
			Object[] aryObj = getObjValue(type, values);
			map.put(paraName, aryObj);
		}
	}

	/**
	 * 根据传入的类型获得真正值的格式
	 * 
	 * </p>
	 * 
	 * @param type
	 * @param paramValue
	 * @return
	 */
	private static Object getObjValue(String type, String paramValue) {
		Object value = null;
		// 字符串 精准匹配
		if ("S".equals(type)) {
			value = paramValue;
		}
		// 字符串 模糊查询
		else if ("SL".equals(type)) {
			value = "%" + paramValue + "%";
		}
		// 字符串 右模糊查询
		else if ("SLR".equals(type)) {
			value = paramValue + "%";
		}
		// 字符串 左模糊查询
		else if ("SLL".equals(type)) {
			value = "%" + paramValue;
		}
		// 字符串 大写 模糊查询
		else if ("SUPL".equals(type)) {
			value = "%" + paramValue.toUpperCase() + "%";
		}
		// 字符串  大写 右模糊查询
		else if ("SUPLR".equals(type)) {
			value = paramValue.toUpperCase() + "%";
		}
		// 字符串  大写  左模糊查询
		else if ("SUPLL".equals(type)) {
			value = "%" + paramValue.toUpperCase() ;
		}
		// 字符串 小写 模糊查询
		else if ("SLOL".equals(type)) {
			value = "%" + paramValue.toLowerCase() + "%";
		}
		// 字符串  小写 右模糊查询
		else if ("SLOLR".equals(type)) {
			value = paramValue.toLowerCase() + "%";
		}
		// 字符串  小写  左模糊查询
		else if ("SLOLL".equals(type)) {
			value = "%" + paramValue.toLowerCase() ;
		}
		// 长整型
		else if ("L".equals(type)) {
			value = new Long(paramValue);
		}
		// 整型
		else if ("N".equals(type)) {
			value = new Integer(paramValue);
		} else if ("DB".equals(type)) {
			value = new Double(paramValue);
		}
		// decimal
		else if ("BD".equals(type)) {
			value = new BigDecimal(paramValue);
		}
		// FLOAT
		else if ("FT".equals(type)) {
			value = new Float(paramValue);
		}
		// short
		else if ("SN".equals(type)) {
			value = new Short(paramValue);
		}
		// date begin
		else if ("DL".equals(type)) {
			value = TimeUtil.convertString(paramValue, "yyyy-MM-dd");
		}
		// date end
		else if ("DG".equals(type)) {
			value = TimeUtil.getNextDays(
					TimeUtil.convertString(paramValue, "yyyy-MM-dd"), 1);
		} else {
			value = paramValue;
		}
		return value;
	}

	private static Object[] getObjValue(String type, String[] value) {
		Object[] aryObj = new Object[value.length];
		for (int i = 0; i < aryObj.length; i++) {
			String v = "";
			if(value[i] != null)
				v=value[i].toString();
			aryObj[i] = getObjValue(type, v);
		}
		return aryObj;
	}

	/**
	 * 把当前上下文的请求封装在map中
	 * 
	 * @param request
	 * @param remainArray
	 *            保持为数组
	 * @param isSecure
	 *            过滤不安全字符
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParameterValueMap(HttpServletRequest request,
			boolean remainArray, boolean isSecure) {
		Map map = new HashMap();
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement().toString();
			String[] values = request.getParameterValues(key);
			if (values == null)
				continue;
			if (values.length == 1) {
				String tmpValue = values[0];
				if (tmpValue == null)
					continue;
				tmpValue = tmpValue.trim();
				if (tmpValue.equals(""))
					continue;
				if (isSecure)
					tmpValue = filterInject(tmpValue);
				if (tmpValue.equals(""))
					continue;
				map.put(key, tmpValue);
			} else {
				String rtn = getByAry(values, isSecure);
				if (rtn.length() > 0) {
					if (remainArray)
						map.put(key, rtn.split(","));
					else
						map.put(key, rtn);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * @param aryTmp
	 * @param isSecure
	 * @return
	 */
	private static String getByAry(String[] aryTmp, boolean isSecure) {
		String rtn = "";
		for (int i = 0; i < aryTmp.length; i++) {
			String str = aryTmp[i].trim();
			if (!str.equals("")) {
				if (isSecure) {
					str = filterInject(str);
					if (!str.equals(""))
						rtn += str + ",";
				} else {
					rtn += str + ",";
				}
			}
		}
		if (rtn.length() > 0)
			rtn = rtn.substring(0, rtn.length() - 1);
		return rtn;
	}

	/**
	 * 根据参数名称获取参数值。
	 * 
	 * <pre>
	 * 1.根据参数名称取得参数值的数组。
	 * 2.将数组使用逗号分隔字符串。
	 * </pre>
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getStringValues(HttpServletRequest request,
			String paramName) {
		String[] values = request.getParameterValues(paramName);
		if (BeanUtils.isEmpty(values))
			return "";
		String tmp = "";
		for (int i = 0; i < values.length; i++) {
			if (i == 0) {
				tmp += values[i];
			} else {
				tmp += "," + values[i];
			}
		}
		return tmp;
	}

	/**
	 * 取得local。
	 * 
	 * @param request
	 * @return
	 */
	public static Locale getLocal(HttpServletRequest request) {
		Locale local = request.getLocale();
		if (local == null)
			local = Locale.CHINA;
		return local;
	}

	/**
	 * 获取出错的url
	 * 
	 * @param request
	 * @return
	 */
	public final static String getErrorUrl(HttpServletRequest request) {
		String errorUrl = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (errorUrl == null) {
			errorUrl = (String) request
					.getAttribute("javax.servlet.forward.request_uri");
		}
		if (errorUrl == null) {
			errorUrl = (String) request
					.getAttribute("javax.servlet.include.request_uri");
		}
		if (errorUrl == null) {
			errorUrl = request.getRequestURL().toString();
		}
		return errorUrl;
	}
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public  static String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	       }      
	       return ip;      
	   }   
	/**
	 * 获取出错信息
	 * 
	 * @param request
	 * @param isInfoEnabled
	 * @return
	 */
	public final static StringBuilder getErrorInfoFromRequest(
			HttpServletRequest request, boolean isInfoEnabled) {
		StringBuilder sb = new StringBuilder();
		String errorUrl = getErrorUrl(request);
		sb.append(StringUtil.formatMsg(
				"Error processing url: %1, Referrer: %2, Error message: %3.\n",
				errorUrl, request.getHeader("REFERER"),
				request.getAttribute("javax.servlet.error.message")));

		Throwable ex = (Throwable) request.getAttribute("exception");
		if (ex == null) {
			ex = (Throwable) request
					.getAttribute("javax.servlet.error.exception");
		}

		if (ex != null) {
			sb.append(StringUtil.formatMsg("Exception stack trace: \n", ex));
		}
		return sb;
	}

	/**
	 * 获取请求页面的所有请求相关的信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public final static StringBuilder getRequestInfo(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------Debuging request.getParameterNames()---------\n");
		java.util.Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			sb.append(StringUtil.formatMsg("Request Parameter - %1 = %2.\n",
					paramName, request.getParameter(paramName)));
		}

		sb.append("-----------Debuging request.getAttributeNames()---------\n");
		enumeration = request.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String attrName = (String) enumeration.nextElement();
			if (!attrName.endsWith("exception")) {
				sb.append(StringUtil.formatMsg(
						"Request Attribute - %1 = %2.\n", attrName,
						request.getAttribute(attrName)));
			}
		}

		sb.append("-----------Debuging request.getHeaderNames()---------------\n");
		enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String headerName = (String) enumeration.nextElement();
			sb.append(StringUtil.formatMsg("Request Header - %1 = %2.\n",
					headerName, request.getHeader(headerName)));
		}

		return sb;
	}

}

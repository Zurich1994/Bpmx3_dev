package com.hotent.platform.web.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.hotent.core.cache.ICache;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.hotent.core.web.util.RequestContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerMapping;

import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysUrlPermission;
import com.hotent.platform.model.system.SysUrlRules;
import com.hotent.platform.service.system.SysUrlPermissionService;
import com.hotent.platform.service.system.SysUrlRulesService;

/**
 *<pre>
 * 对象功能:URL访问权限控制
 * 开发公司:广州宏天软件有限公司
 * 开发人员:gjh
 * 创建时间:2014-03-26 11:45:14
 *</pre>
 */
public class SysUrlPermissionFilter {
	@Resource
	private SysUrlPermissionService sysUrlPermissionService;
	@Resource
	private SysUrlRulesService sysUrlRulesService;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	
	private static Map<SysUrlPermission, List<SysUrlRules>> permissionMap ;
	@Resource
	private ICache iCache;
	private final static String URL_PERMISSION_MAP_KEY = "urlPermissionMapKey_";
	/**
	 * URL访问拦截操作
	 */
	public void doHandler(){
		// 第一次请求
		if(!iCache.containKey(URL_PERMISSION_MAP_KEY)) initMap();
		Map<SysUrlPermission, List<SysUrlRules>> permissionMap = (Map<SysUrlPermission, List<SysUrlRules>>) iCache.getByKey(URL_PERMISSION_MAP_KEY);
		// 如果不是第一次请求，并且没有设置拦截规则，则返回
		if(BeanUtils.isEmpty(permissionMap)) return ;
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		String currentURI = request.getRequestURI();
		// 获取当前请求的所有参数
		Map<String, Object> paramMap = RequestUtil.getQueryMap(request);
		// 获取@PathVariables参数，并加入到paramMap中
		Map<String, Object> pathVariables = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(BeanUtils.isNotEmpty(pathVariables)) paramMap.putAll(pathVariables);
		Map<String, Object> matchParams ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		for(SysUrlPermission permission:permissionMap.keySet()){
			List<SysUrlRules> rules = permissionMap.get(permission);
			// 当前请求不匹配拦截的URL或者当前拦截规则脚本为空，则跳过
			if(!StringUtil.validByRegex(permission.getUrl(), currentURI) || BeanUtils.isEmpty(rules)) continue ;
			String paramString = permission.getParams();
			// 无配置参数，则将所有参数传入
			if(StringUtil.isEmpty(paramString)){
				matchParams = paramMap;
			} else {
				matchParams = new HashMap<String, Object>();
				String[] params = paramString.split(",");
				// 配置了具体参数，获取匹配的参数
				for(String key:params){
					Object value = paramMap.get(key);
					matchParams.put(key, value);
				}
				// 无参数匹配，则不拦截
				if(BeanUtils.isEmpty(matchParams)) continue ;
			}
			map.put("map", matchParams) ;
			// 若有一条规则符合，则允许用户访问该页面
			for(SysUrlRules rule:rules){
				boolean result = groovyScriptEngine.executeBoolean(rule.getScript(), map);
				if(result) return;
			}
			// 若上述循环结束后并无符合条件的规则，则无权访问该页面
			throw new AccessDeniedException("对不起,你没有访问该页面的权限!");
		}
	}
	
	/**
	 * 从数据库中加载所有URL拦截规则
	 */
	public void initMap(){
		Map<SysUrlPermission, List<SysUrlRules>> permissionMap = new ConcurrentHashMap<SysUrlPermission, List<SysUrlRules>>();
		QueryFilter filter = new QueryFilter(RequestContext.getHttpServletRequest());
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("enable", 1);//只获取启用的规则
		filter.setFilters(map);
		List<SysUrlPermission> permissionList = sysUrlPermissionService.getAll(filter);
		List<SysUrlRules> rules ;
		for(SysUrlPermission permission:permissionList){
			map.put("sysUrlId", permission.getId());
			rules = sysUrlRulesService.getAll(filter);
			permissionMap.put(permission, rules);
		}
		iCache.add(URL_PERMISSION_MAP_KEY, permissionMap);
	}
}
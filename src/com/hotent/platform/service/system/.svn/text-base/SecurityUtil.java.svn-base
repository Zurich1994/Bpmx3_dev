package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.security.UrlPararRole;
import com.hotent.platform.model.system.ResourcesUrlExt;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;

/**
 * 主要用于系统权限资源缓存。
 * <pre>
 * 	1.系统的url和角色映射。
 *  2.系统的url和参数列表进行映射。
 * 	4.系统和角色进行映射。
 *  3.系统的功能和角色映射。
 * </pre>
 * @author ray
 *
 */
public class SecurityUtil {
	
	protected static Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
//	public static String FunctionRoleMap="functionRoleMap_";
//	
//	public static String UrlRoleMap="urlRoleMap_";
//	
//	public static String UrlParaMap="urlParaMap_";
//	
//	public static String SystemRoleMap="systemRoleMap_";
//	
//	public static String UserRole="userRole_";
//	
//	public static String UserRoleMenu="userRoleMenu_";
//	
//	public static String UserOrgRole="userOrgRole_";
//	
//	public static String OrgRole="orgRole_";
//	
//	public static String SystemFlag="systemFlag_";

	/**
	 * 加载本地子系统的所有资源。
	 * @param sysRoleService
	 * @param subSystemService
	 */
//	public static void loadRes(){
//		SubSystemService subSystemService=(SubSystemService)AppUtil.getBean(SubSystemService.class);
//		List<SubSystem> sysList=subSystemService.getLocalSystem();
//    	if(sysList==null || sysList.size()==0) return;
//    	for(SubSystem sys:sysList){
//			Long systemId=sys.getSystemId();
//			loadRes( systemId);
//		}
//	}
	
	
//	@SuppressWarnings("unchecked")
//	public static Map<String, Collection<ConfigAttribute>> getUrlRoleMap(Long systemId){
//		
//		
//		
//		
//		
//		SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
//		List<ResourcesUrlExt> urlList=sysRoleService.getUrlRightMap(systemId);
//		List<SysRole> listRole= sysRoleService.getBySystemId(systemId);
//		List<ResourcesUrlExt> funcRoleList=sysRoleService.getFunctionRoleList(systemId);
//		
//		putResources(systemId,urlList);
//		putSystemRole(systemId,listRole);
//		putFuncRoleList(systemId,funcRoleList);
//		
//		
////		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
////		Map<String, Collection<ConfigAttribute>> roleMap=(Map<String, Collection<ConfigAttribute>>)iCache.getByKey(SecurityUtil.UrlRoleMap + systemId);
////		//如果没有获取到则添加缓存
////		if(roleMap==null){
////			loadRes(systemId);
////			//再次获取
////			roleMap=(Map<String, Collection<ConfigAttribute>>)iCache.getByKey(SecurityUtil.UrlRoleMap + systemId);
////		}
////		
////		return roleMap;
//	}
	
	
//	public static Map<String, Set<String>> getUrlParaMap(Long systemId){
//		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
//		Map<String, Set<String>> paraMap=(Map<String, Set<String>>) iCache.getByKey(SecurityUtil.UrlParaMap + systemId);
//		if(paraMap==null){
//			loadRes(systemId);
//			//再次获取
//			paraMap=(Map<String, Set<String>>)iCache.getByKey(SecurityUtil.UrlParaMap + systemId);
//		}
//		
//		return paraMap;
//	}
	
	
	
	/**
	 * 根据系统Id删除缓存后，重新根据系统id加入缓存。
	 * @param systemId
	 */
//	public static void removeCacheBySystemId(Long systemId){
//		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
//		iCache.delByKey(FunctionRoleMap+systemId);
//		iCache.delByKey(UrlRoleMap+systemId);
//		iCache.delByKey(UrlParaMap+systemId);
//		iCache.delByKey(SystemRoleMap+systemId);
//		iCache.delByKey(SystemFlag+systemId);
//		
//		if(iCache instanceof MemoryCache){
//			try {
//				//删除其他系统
//				delCacheBySystemId(systemId.toString());
//				
//			} catch (IOException e) {
//				String msg=ExceptionUtil.getExceptionMessage(e);
//				log.error(msg);
//				e.printStackTrace();
//				
//			}
//		}
//		loadRes(systemId);
//	}
	
	/**
	 * 将系统 的功能和角色列表加入到映射中。
	 * @param systemId		系统id。
	 * @param funcRoleList	功能和角色映射列表。
	 */
	private static FunctionRights getFuncRoleList(String sysAlias,String function){
		
		SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
		List<ResourcesUrlExt> funcRoleList=sysRoleService.getFunctionRoleList(sysAlias,function);
		boolean hasFunction=false;
		if(funcRoleList.size()>0){
			hasFunction=true;
		}
		
		Collection<ConfigAttribute> collectoin=new HashSet<ConfigAttribute>();
		for(ResourcesUrlExt table:funcRoleList){
			String role=(String)table.getRole();
			if(StringUtil.isEmpty(role)) continue;
			collectoin.add(new SecurityConfig(role));
		}
		SecurityUtil util=new SecurityUtil();
		FunctionRights rights=util.new FunctionRights(hasFunction, collectoin);
		return rights;

	}
	
	
	 /**
     * 转化功能与角色的关系。
     * <pre>
     * 	将功能和角色对象列表转化为功能对角色列表的映射。
     * 	List&lt;ResourcesUrlExt> 转换为
     *  Map&lt;功能,角色集合>
     * </pre>
     * @param funcRoleList
     * @return
     */
//	 private static Map<String, Collection<ConfigAttribute>> getResources(List<ResourcesUrlExt> funcRoleList){
//	    	if(BeanUtils.isEmpty(funcRoleList)) return null;
//			Map<String, Collection<ConfigAttribute>>  functionRole=new HashMap<String, Collection<ConfigAttribute>>();
//			for(ResourcesUrlExt table:funcRoleList){
//				if(table==null) continue;
//				String function=(String) table.getFunc();
//				String role=(String)table.getRole();
//				if(StringUtil.isEmpty(function))
//					continue;
//				
//				function=function.trim();
//				if(functionRole.containsKey(function)){
//					if(StringUtil.isNotEmpty(role))
//						functionRole.get(function).add(new SecurityConfig(role));
//				}else{
//					Collection<ConfigAttribute> collectoin=new HashSet<ConfigAttribute>();
//					if(StringUtil.isNotEmpty(role))
//						collectoin.add(new SecurityConfig(role));
//					functionRole.put(function, collectoin);
//				}
//			}
//			return functionRole;
//		}
//	
	/**
	 * 根据系统id加载资源。
	 * @param sysRoleService
	 * @param subSystemService
	 * @param systemId
	 */
//	public static  void loadRes(Long systemId){
//		SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
//		List<ResourcesUrlExt> urlList=sysRoleService.getUrlRightMap(systemId);
//		List<SysRole> listRole= sysRoleService.getBySystemId(systemId);
//		List<ResourcesUrlExt> funcRoleList=sysRoleService.getFunctionRoleList(systemId);
//		
//		putResources(systemId,urlList);
//		putSystemRole(systemId,listRole);
//		putFuncRoleList(systemId,funcRoleList);
//	}
	
	/**
	 * 系统资源是否缓存。
	 * @param systemId
	 * @return
	 */
//	public static boolean isResCached(Long systemId){
//		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
//		Map<String, Collection<ConfigAttribute>> map=(Map<String, Collection<ConfigAttribute>> )iCache.getByKey(UrlRoleMap + systemId);
//		if(map==null)
//			return false;
//		return true;
//	}
	
	/**
	 * url和参数，url和角色进行映射起来。
	 * @param systemId
	 * @param urlList
	 */
//	@SuppressWarnings("rawtypes")
//	public  static  UrlPararRole getUrlParaRole(Long systemId){
//		
//		SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
//		List<ResourcesUrlExt> urlList=sysRoleService.getUrlRightMap(systemId);
//		
//		UrlPararRole urlPararRole=new UrlPararRole();
//		
//
//		//URL 和角色列表映射。
//		Map<String, Collection<ConfigAttribute>> 	urlRoleMap=new HashMap<String, Collection<ConfigAttribute>>();
//		//url和参数列表的映射
//		Map<String, Set<String>> urlParaMap	=new HashMap<String, Set<String>>();
//		
//		if(BeanUtils.isEmpty(urlList)) return urlPararRole;
//		
//		for(ResourcesUrlExt resource:urlList){
//			if(resource==null) continue;
//			String fullUrl=resource.getUrl();
//			
//			
//			
//			String role=resource.getRole();
//		
//			if(StringUtil.isEmpty(fullUrl)) continue;
//			fullUrl=fullUrl.trim();
//			
//			String parameter="";
//			String url=fullUrl;
//			//有参数 地址如下 add.ht?name=sf。
//			if(fullUrl.indexOf("?")>-1){
//				String[] aryUrl=fullUrl.split("\\?");
//				url=aryUrl[0];
//				parameter=aryUrl[1];
//			}
//			
//			//参数处理
//			//url ： 对应参数的SET集合。
//			if(urlParaMap.containsKey(url)){
//				Set<String> paramList=urlParaMap.get(url);
//				paramList.add(parameter);
//			}
//			else{
//				Set<String> paramList=new HashSet<String>();
//				paramList.add(parameter);
//				urlParaMap.put(url, paramList);
//			}
//			
//			//角色处理
//			//url : 对应角色集合。
//			if(urlRoleMap.containsKey(fullUrl)){
//				Collection<ConfigAttribute> roleList=urlRoleMap.get(fullUrl);
//				if(StringUtil.isNotEmpty(role))
//					roleList.add(new SecurityConfig(role));
//			}
//			else{
//				Collection<ConfigAttribute> collectoin=new HashSet<ConfigAttribute>();
//				if(StringUtil.isNotEmpty(role))
//					collectoin.add(new SecurityConfig(role));
//				urlRoleMap.put(fullUrl, collectoin);
//			}
//			
//		}
//		urlPararRole.setUrlParaMap(urlParaMap);
//		urlPararRole.setUrlRoleMap(urlRoleMap);
//		return urlPararRole;
//		
////		if(BeanUtils.isNotEmpty(urlRoleMap))
////			iCache.add(urlRoleMapKey,urlRoleMap);
////		if(BeanUtils.isNotEmpty(urlParaMap))
////			iCache.add(urlParaMapKey,urlParaMap);
//		
//	}
	
	
	/**
	 * 添加系统和角色的关系映射。
	 * 系统id ： 角色set集合。
	 * @param systemId
	 */
	public static Set<String> getSystemRole(Long systemId){
		SysRoleService sysRoleService=(SysRoleService)AppUtil.getBean(SysRoleService.class);
		List<SysRole> listRole= sysRoleService.getBySystemId(systemId);
//		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
//		String systemRoleKey=SystemRoleMap + systemId;
		//URL 和角色列表映射。
		Set<String> roleSet=new HashSet<String>();
		for(SysRole role: listRole){
			roleSet.add(role.getAlias());
		}
//		iCache.add(systemRoleKey, roleSet);
		return roleSet;
	}
	
	/**
	 * 根据用户ID删除对应的角色映射缓存。
	 * @param account
	 */
	@SuppressWarnings("unchecked")
//	public static void removeUserRoleCache(Long userId){
//		ICache cache=(ICache) AppUtil.getBean(ICache.class);
//		String key=UserRole + userId;
//		cache.delByKey(key);
//		
//		if(cache instanceof MemoryCache){
//			try {
//				delCacheByDefId(key);
//			} catch (IOException e) {
//				String msg=ExceptionUtil.getExceptionMessage(e);
//				log.error(msg);
//				e.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * 根据orgId删除对应的角色映射缓存。
	 * @param orgId
	 */
//	public static void removeOrgRoleCache(Long orgId){
//		ICache cache=(ICache) AppUtil.getBean(ICache.class);
//		String key=OrgRole + orgId;
//		cache.delByKey(key);
//		if(cache instanceof MemoryCache){
//			try {
//				delCacheByDefId(key);
//			} catch (IOException e) {
//				String msg=ExceptionUtil.getExceptionMessage(e);
//				log.error(msg);
//				
//				e.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * 将所有的缓存都清除。
	 * @throws IOException 
	 */
//	@SuppressWarnings("unchecked")
//	public static void removeAll() {
//		ICache cache=(ICache) AppUtil.getBean(ICache.class);
//		cache.clearAll();
//		if(cache instanceof MemoryCache){
//			try {
//				removeAllCache();
//			} catch (IOException e) {
//				
//				String msg=ExceptionUtil.getExceptionMessage(e);
//				log.error(msg);
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	
	/**
	 * 根据系统和功能别名判断是否有权限访问。
	 * @param systemId
	 * @param function
	 * @return
	 */
	public static boolean hasFuncPermission(String systemAlias, String function){
		
		FunctionRights functionRights= getFuncRoleList(systemAlias, function);
		
		
		
		SysUser currentUser= ContextUtil.getCurrentUser();
		//超级管理员
		if(currentUser.getAuthorities().contains(SystemConst.ROLE_GRANT_SUPER)){
			return true;
		}
		//当功能在系统功能表中，匹配当前用户的角色是否在功能的角色列表中。
		else {
			if(!functionRights.isHasFunction()) return true ;
			Collection<ConfigAttribute> functionRole=functionRights.getRoles();
			if(functionRole.size()==0) return false;
			
			for(GrantedAuthority hadRole:currentUser.getAuthorities()){
				if(functionRole.contains(new SecurityConfig(hadRole.getAuthority()))){  
	                return true;
	            }
	        }
			return false;
	    }
    }
	
	/**
	 * 调用webservice清除缓存。
	 * @param key
	 * @throws IOException
	 */
//	private static void delCacheByDefId(String key) throws IOException {
//		String serviceUrl = AppConfigUtil.get("serviceUrl");
//		String ipAddress = AppConfigUtil.get("ipAddress");
//		if (StringUtil.isEmpty(serviceUrl) || StringUtil.isEmpty(ipAddress)){
//			log.error("delCacheByDefId,serviceUrl:" + serviceUrl +",ipAddress:" + ipAddress);
//			return;
//		}
//		StringBuffer sb = new StringBuffer();
//		
//		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://api.webservice.platform.sf.com/\">");
//		sb.append("<soapenv:Header/>");
//		sb.append("<soapenv:Body>");
//		sb.append("<api:delCacheByKey>");
//		sb.append("<arg0>"+key+"</arg0>");
//		sb.append("</api:delCacheByKey>");
//		sb.append("</soapenv:Body>");
//		sb.append("</soapenv:Envelope>");
//
//		String[] aryIp = ipAddress.split(",");
//		for (String ip : aryIp) {
//			String url = serviceUrl.replace("{url}", ip);
//			HttpUtil.sendMessage(url, sb.toString());
//		}
//	}
	
	/**
	 * 清除所有的缓存。
	 * @throws IOException
	 */
//	private static void removeAllCache() throws IOException {
//		String serviceUrl = AppConfigUtil.get("serviceUrl");
//		String ipAddress = AppConfigUtil.get("ipAddress");
//		if (StringUtil.isEmpty(serviceUrl) || StringUtil.isEmpty(ipAddress)){
//			log.error("removeAllCache,serviceUrl:" + serviceUrl +",ipAddress:" + ipAddress);
//			return;
//		}
//		StringBuffer sb = new StringBuffer();
//		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://api.webservice.platform.sf.com/\">");
//		sb.append("<soapenv:Header/>");
//		sb.append("<soapenv:Body>");
//		sb.append("<api:removeAllCache/>");
//		sb.append("</soapenv:Body>");
//		sb.append("</soapenv:Envelope>");
//		
//
//		String[] aryIp = ipAddress.split(",");
//		for (String ip : aryIp) {
//			String url = serviceUrl.replace("{url}", ip);
//			HttpUtil.sendMessage(url, sb.toString());
//		}
//	}
	
	
	/**
	 * 调用webservice清除缓存。
	 * @param key
	 * @throws IOException
	 */
//	private static void delCacheBySystemId(String key) throws IOException {
//		String serviceUrl = AppConfigUtil.get("serviceUrl");
//		String ipAddress = AppConfigUtil.get("ipAddress");
//		if (StringUtil.isEmpty(serviceUrl) || StringUtil.isEmpty(ipAddress)){
//			log.error("delCacheBySystemId,serviceUrl:" + serviceUrl +",ipAddress:" + ipAddress);
//			return;
//
//		}
//
//		StringBuffer sb = new StringBuffer();
//		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://api.webservice.platform.sf.com/\">");
//		sb.append("<soapenv:Header/>");
//		sb.append("<soapenv:Body>");
//		sb.append("<api:delCacheBySystemId>");
//		sb.append("<arg0>"+key+"</arg0>");
//		sb.append("</api:delCacheBySystemId>");
//		sb.append("</soapenv:Body>");
//		sb.append("</soapenv:Envelope>");
//		String[] aryIp = ipAddress.split(",");
//		for (String ip : aryIp) {
//			String url = serviceUrl.replace("{url}", ip);
//			HttpUtil.sendMessage(url, sb.toString());
//		}
//	}
//	
	public class FunctionRights{
		
		private boolean hasFunction=false;
		
		private Collection<ConfigAttribute> roles=new ArrayList<ConfigAttribute>();
		
		public FunctionRights(boolean hasFunction,Collection<ConfigAttribute> roles){
			this.hasFunction=hasFunction;
			this.roles=roles;
		}

		public boolean isHasFunction() {
			return hasFunction;
		}

		public void setHasFunction(boolean hasFunction) {
			this.hasFunction = hasFunction;
		}

		public Collection<ConfigAttribute> getRoles() {
			return roles;
		}

		public void setRoles(Collection<ConfigAttribute> roles) {
			this.roles = roles;
		}
		
	}
	

}



 

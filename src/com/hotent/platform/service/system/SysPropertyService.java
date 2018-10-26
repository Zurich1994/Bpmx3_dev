package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.cache.ICache;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysPropertyDao;
import com.hotent.platform.model.system.SysProperty;

/**
 * <pre>
 * 对象功能:系统配置参数表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-04-16 11:20:41
 * </pre>
 */
@Service
public class SysPropertyService extends BaseService<SysProperty> {
	@Resource
	private SysPropertyDao dao;


	public SysPropertyService() {
	}

	@Override
	protected IEntityDao<SysProperty, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 系统配置参数表 信息
	 * <pre>
	 * 保存时清空缓存。
	 * </pre>
	 * @param sysProperty
	 */
	public void save(SysProperty sysProperty) {
		this.update(sysProperty);
		
		reloadProperty();
	}
	
	/**
	 * 重新加载系统参数。
	 * @return
	 */
	private static Map<String,String>  reloadProperty(){
		ICache cache=AppUtil.getBean(ICache.class);
		SysPropertyService propertyService=AppUtil.getBean(SysPropertyService.class);
		Map<String,String> map=new HashMap<String, String>();
		List<SysProperty> list=propertyService.getAll();
		for(SysProperty property:list){
			map.put(property.getAlias().toLowerCase(), property.getValue());
		}
		cache.add(SysProperty.PropertyCache, map);
		
		return map;
	}
	
	/**
	 * 根据别名获取参数值。
	 * @param alias
	 */
	public static String getByAlias(String alias){
		ICache cache=AppUtil.getBean(ICache.class);
		Map<String,String> map=(Map<String,String>) cache.getByKey(SysProperty.PropertyCache);
		if(BeanUtils.isEmpty(map)){
			map=reloadProperty();
		}
		return map.get(alias.toLowerCase());
	}
	
	public static String getByAlias(String alias,String defaultValue){
		String val=getByAlias(alias);
		if(StringUtil.isEmpty(val)) return defaultValue;
		return val;
	}
	
	/**
	 * 根据别名获取整形参数值。
	 * @param alias
	 * @return
	 */
	public static Integer getIntByAlias(String alias){
		String val= getByAlias(alias);
		if(StringUtil.isEmpty(val)) return 0;
		Integer rtn=Integer.parseInt(val);
		return rtn;
	}
	
	/**
	 * 根据别名获取整形的参数值。
	 * @param alias
	 * @param defaulValue
	 * @return
	 */
	public static Integer getIntByAlias(String alias,Integer defaulValue){
		String val= getByAlias(alias);
		if(StringUtil.isEmpty(val)) return defaulValue;
		Integer rtn=Integer.parseInt(val);
		return rtn;
	}
	/**
	 * 根据别名获取长整型参数。
	 * @param alias
	 * @return
	 */
	public static Long getLongByAlias(String alias){
		String val= getByAlias(alias);
		Long rtn=Long.parseLong(val);
		return rtn;
	}
	
	/**
	 * 根据别名获取布尔型值。
	 * @param alias
	 * @return
	 */
	public static boolean getBooleanByAlias(String alias){
		String val= getByAlias(alias);
		return Boolean.parseBoolean(val);
	}
	/**
	 * 根据别名获取布尔型值。 1 为true,0为false
	 * @param alias
	 * @return
	 */
	public static boolean getBooleanByAlias(String alias,boolean defaulValue){
		String val= getByAlias(alias);
		if(StringUtil.isEmpty(val)) return defaulValue;
		
		if("1".equals(val)) return true;
		
		return Boolean.parseBoolean(val);
	}

}

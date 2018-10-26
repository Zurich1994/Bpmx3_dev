package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysQuerySetting;

/**
 * <pre>
 * 对象功能:SYS_QUERY_SETTING Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 12:43:14
 * </pre>
 */
@Repository
public class SysQuerySettingDao extends BaseDao<SysQuerySetting> {
	@Override
	public Class<?> getEntityClass() {
		return SysQuerySetting.class;
	}

	/**
	 * 根据sqlId获取配置对象
	 * 
	 * @param sqlId
	 * @return
	 */
	public SysQuerySetting getBySqlId(Long sqlId) {
		SysQuerySetting sysQuerySetting = this.getUnique("getBySqlId", sqlId);
		return sysQuerySetting == null ? new SysQuerySetting()
				: sysQuerySetting;
	}

	public void delBySqlId(Long sqlId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sqlId", sqlId);
		this.delBySqlKey("delBySqlId", params);
	}
	
	public SysQuerySetting getByAlias(String alias) {
		SysQuerySetting sysQuerySetting = this.getUnique("getByAlias", alias);
		return sysQuerySetting ;
	}

}
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysObjRights;
import com.hotent.platform.model.system.SysPopupRemind;

/**
 * <pre>
 * 对象功能:sys_popup_remind Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-03-18 11:36:25
 * </pre>
 */
@Repository
public class SysPopupRemindDao extends BaseDao<SysPopupRemind> {
	@Override
	public Class<?> getEntityClass() {
		return SysPopupRemind.class;
	}
	
	public List<SysPopupRemind> getByUser(Map<String, List<Long>> map){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relationMap", map);//基本固定
		params.put("objType", SysObjRights.RIGHT_TYPE_POPUP_MSG);
		List<SysPopupRemind> sprs = this.getBySqlKey("getByUser",params);
		return sprs;
	}
}
/**
 * 对象功能:桌面个人栏目 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.DesktopMycolumn;

@Repository
public class DesktopMycolumnDao extends BaseDao<DesktopMycolumn>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return DesktopMycolumn.class;
	}
	
	public List<DesktopMycolumn> getByUserId(Long userId) {	
		return getBySqlKey("getByUserId",userId);
	}
	
	/**
	 * 根据用户Id，获取用户相应的用户桌面栏目列表。
	 * @param userId 用户Id。
	 * @return 用户Id对应的用户桌面栏目列表，
	 */
	public List<DesktopMycolumn> getMyDeskData(Long userId){
		return getBySqlKey("getMyDeskData",userId);
	}
	
	public List<DesktopMycolumn> getDefaultDeskDataById(Long layoutId){
		return getBySqlKey("getDefaultDeskDataById",layoutId);
	}
	
	public List<DesktopMycolumn> getDefaultDeskData(){
		return getBySqlKey("getDefaultDeskData");
	}
	
	public void delByUserId(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		getBySqlKey("delByUserId",params);
	}
	
	public void delByLayoutId(Long layoutId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", layoutId);
		getBySqlKey("delByLayoutId",params);
	}
	
	public void delByLinkMycolumn(Long COLUMNID) {
		getBySqlKey("delByLinkMycolumn",COLUMNID);
	}

	public List<DesktopMycolumn> getByLayoutIdColNum(Long id, int colNum) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("layoutId", id);
		params.put("colNum", colNum);
		return this.getBySqlKey("getByLayoutIdColNum", params);
	}
	
}
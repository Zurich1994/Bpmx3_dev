/**
 * 对象功能:桌面栏目管理表 Dao类
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
import com.hotent.platform.model.system.DesktopLayoutcol;

@Repository
public class DesktopLayoutcolDao extends BaseDao<DesktopLayoutcol>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return DesktopLayoutcol.class;
	}

	/**
	 * 根据布局ID删除
	 * @param layoutId
	 */
	public void delByLayoutId(Long layoutId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", layoutId);
		getBySqlKey("delByLayoutId",params);
	}
	
	/**
	 * 
	 * @param layoutId
	 */
	public void delByNoLayoutId(Long layoutId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", layoutId);
		getBySqlKey("delByNoLayoutId",params);
	}
	
	/**
	 * 
	 * @param layoutId
	 * @return
	 */
	public List<DesktopLayoutcol> getByLayoutId(Long layoutId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", layoutId);
		return getBySqlKey("getByLayoutId",params);
	}
	
	public void delByLinkLayout(Long COLUMNID) {
		getBySqlKey("delByLinkLayout",COLUMNID);
	}

	public List<DesktopLayoutcol> getByLayoutIdAndColNum(Long id, int colNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("layoutId", id);
		params.put("col", colNum);
		return this.getBySqlKey("getByLayoutIdAndColNum", params);
	}	
}
package com.hotent.extension.dao.bpm;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppUtil;
import com.hotent.extension.model.bpm.BpmNodeData;
/**
 *<pre>
 * 对象功能:BPM_NODE_DATA Dao类
 * 开发公司:哈尔滨工程大学
 * 开发人员:ray
 * 
 *</pre>
 */
@Repository
public class BpmNodeDataDao extends BaseDao<BpmNodeData>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmNodeData.class;
	}
	
	public BpmNodeData getBySetId(Long setId){
		return getUnique("getBySetId", setId);
	}

	public void deleteBySetId(long setId) {
		delBySqlKey("delBySetId", setId);
	}
	
	public List<BpmNodeData> getBpmNodeData(long defId){
		return getBySqlKey("getNameAndAliasByDefId", defId);
	}
	
	public List<BpmNodeData> getGlobalData(long defId){
		return getBySqlKey("getGlobalByDefId", defId);
	}
	
	public String getNodePathByTypeId(String typeId){
		
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select nodepath from sys_gl_type where typeid='"+typeId+"'";
		List<Map<String,Object>> list = template.queryForList(sql);
		return list.get(0).get("NODEPATH").toString();
		
	}
}


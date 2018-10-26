
package com.hotent.BpmFormBang.dao.bpmFormBang;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;

@Repository
public class BpmFormBangDao extends BaseDao<BpmFormBang>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmFormBang.class;
	}
	//whr根据事件ID获取事件图关联关系
	/*
	public BpmFormBang getByDefbId(String defbId)
	{
		String getStatement= getIbatisMapperNamespace() + ".getByDefbId";
		BpmFormBang object = (BpmFormBang)getSqlSessionTemplate().selectOne(getStatement, defbId);
		
		return object;
	}
	*/
	public List<BpmFormBang> getByFormDefId(String formDefId)	{
		 List<BpmFormBang> list = getBySqlKey("getByFormDefId", formDefId);
		 return list;

	}
	public List<BpmFormBang> getByBtnName(String btnname)	{
		 List<BpmFormBang> list = getBySqlKey("getByBtnName", btnname);
		 return list;

	}
}

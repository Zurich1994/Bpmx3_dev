
package com.hotent.eventgraphrelation.dao.eventgraphrelation;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;

@Repository
public class EventgraphrelationDao extends BaseDao<Eventgraphrelation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Eventgraphrelation.class;
	}
	
	//zl根据事件ID获取事件图关联关系
	public Eventgraphrelation getByEventId(String eventID)
	{
		String getStatement= getIbatisMapperNamespace() + ".getByEventId";
		Eventgraphrelation object = (Eventgraphrelation)getSqlSessionTemplate().selectOne(getStatement, eventID);
		
		return object;
	}

}

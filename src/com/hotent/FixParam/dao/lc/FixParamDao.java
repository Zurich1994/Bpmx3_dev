
package com.hotent.FixParam.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.FixParam.model.lc.FixParam;

@Repository
public class FixParamDao extends BaseDao<FixParam>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FixParam.class;
	}
	
	public List<FixParam> getParamName(String timeType){
		List<FixParam> fixParamList = getBySqlKey("getParamName",timeType);
		return fixParamList;
	}
	
	public List<FixParam> getAllByTimeType(String timeType){
		List<FixParam> fixParamList = getBySqlKey("getAllByTimeType",timeType);
		return fixParamList;
	}

}

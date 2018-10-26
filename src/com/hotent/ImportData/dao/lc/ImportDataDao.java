
package com.hotent.ImportData.dao.lc;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.ImportData.model.lc.ImportData;

@Repository
public class ImportDataDao extends BaseDao<ImportData>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ImportData.class;
	}

}

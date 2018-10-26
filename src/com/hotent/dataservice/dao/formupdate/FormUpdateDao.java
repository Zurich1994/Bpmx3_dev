
package com.hotent.dataservice.dao.formupdate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.dataservice.model.formupdate.FormUpdate;

@Repository
public class FormUpdateDao extends BaseDao<FormUpdate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FormUpdate.class;
	}

}

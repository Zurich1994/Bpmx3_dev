
package com.hotent.VirtualSoftware.dao.VirtualSoftwarePac;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.VirtualSoftware.model.VirtualSoftwarePac.VirtualSoftware;

@Repository
public class VirtualSoftwareDao extends BaseDao<VirtualSoftware>
{
	@Override
	public Class<?> getEntityClass()
	{
		return VirtualSoftware.class;
	}

}

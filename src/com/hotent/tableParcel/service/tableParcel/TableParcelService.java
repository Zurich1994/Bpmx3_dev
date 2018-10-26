package com.hotent.tableParcel.service.tableParcel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.dao.tableParcel.TableParcelDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class TableParcelService extends BaseService<TableParcel>
{
	@Resource
	private TableParcelDao dao;
	
	public TableParcelService()
	{
	}
	
	@Override
	protected IEntityDao<TableParcel,Long> getEntityDao() 
	{
		return dao;
	}

	public List<TableParcel> getParcelsbyTableName(String tablename) {
		// TODO Auto-generated method stub
		return dao.getParcelsbyTableName(tablename);
	}

	public List<TableParcel> getParcelbyName(String name, String tablename) {
		// TODO Auto-generated method stub
		System.out.println("看看是什么 :"+name+"tablename:"+tablename);
		return dao.getParcelbyName(name,tablename);
	}
	public TableParcel getParcelByParcelId(Long F_parcel_id) {
		return dao.getParcelByParcelId(F_parcel_id);
	}
	
}

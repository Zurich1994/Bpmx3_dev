
package com.hotent.tableParcel.dao.tableParcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tableParcel.model.tableParcel.TableParcel;

@Repository
public class TableParcelDao extends BaseDao<TableParcel>
{
	@Override
	public Class<?> getEntityClass()
	{
		return TableParcel.class;
	}

	public List<TableParcel> getParcelsbyTableName(String tablename) {
		// TODO Auto-generated method stub
		return getBySqlKey("getParcelsbyTableName", tablename);
	}

	public List<TableParcel> getParcelbyName(String name, String tablename) {
		// TODO Auto-generated method stub
		Map<String, String> params=new HashMap<String, String>();
		params.put("tablename", tablename);
		params.put("name", name);
		return getBySqlKey("getParcelbyName",params);
	}
	/**
	 * 根据数据包ID获取数据包
	 * 
	 * @param F_parcel_id
	 * @return
	 * */
	public TableParcel getParcelByParcelId(Long F_parcel_id) {
		return this.getUnique("getParcelByParcelId",F_parcel_id);
	}

}

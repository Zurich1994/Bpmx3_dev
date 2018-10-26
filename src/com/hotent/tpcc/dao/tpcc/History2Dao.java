
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.History2;

@Repository
public class History2Dao extends BaseDao<History2>
{
	@Override
	public Class<?> getEntityClass()
	{
		return History2.class;
	}
	public void addHistory(History2 history)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("h_c_id", history.getH_c_id());
		params.put("h_c_d_id", history.getH_c_d_id());
		params.put("h_d_id", history.getH_d_id());
		params.put("h_c_w_id", history.getH_c_w_id());
		
		params.put("h_data", history.getH_data());
		params.put("h_amount", history.getH_amount());
		params.put("h_date", history.getH_date());
		params.put("h_w_id", history.getH_w_id());
		
			System.out.println(params.get("h_c_id"));
			System.out.println(params.get("h_c_d_id"));
			System.out.println(params.get("h_d_id"));
			System.out.println(params.get("h_c_w_id"));
			System.out.println(params.get("h_data"));
			System.out.println(params.get("h_amount"));
			System.out.println(params.get("h_date"));
			System.out.println(params.get("h_w_id"));
			
		
		insert("addHistory",params);
	}
}

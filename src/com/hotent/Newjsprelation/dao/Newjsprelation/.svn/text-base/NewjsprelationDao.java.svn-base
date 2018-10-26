
package com.hotent.Newjsprelation.dao.Newjsprelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.Newjsprelation.model.Newjsprelation.Newjsprelation;

@Repository
public class NewjsprelationDao extends BaseDao<Newjsprelation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Newjsprelation.class;
	}
	public String  getFormDefIdByFormDefId(long formidl) 
	{
		
		  return (String)getOne("getFormDefIdByFormDefId", formidl);
		
			
	}
	public void updatenameandvalueByFormDefId(Long FORMDEFID,ArrayList<String> names ,ArrayList<String> values)
	{
			Map<String, String> map1 = new HashMap<String, String>();
			int j = 0;
			for (int i = 0; i < names.size(); i++)
			{
				if (j < values.size())
				{
					if (values.get(j) == null)
					{
						map1.put(names.get(i), "");
					}
					else
					{
						map1.put(names.get(i), values.get(j));
						j++;
					}
				} 
				else
				{
					map1.put(names.get(i), "");
				}

			}
			JSONArray ja1 = JSONArray.fromObject(map1);
			String NAMEANDVALUE= ja1.toString();
			System.out.println("ja1.toString()"+ja1.toString());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("FORMDEFID", FORMDEFID);
			params.put("NAMEANDVALUE",NAMEANDVALUE);
			update("updatenameandvalueByFormDefId", params);
	}
}

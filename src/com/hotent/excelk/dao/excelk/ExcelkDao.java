
package com.hotent.excelk.dao.excelk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.hotent.core.db.BaseDao;

import com.hotent.excelk.model.excelk.Excelk;

@Repository
public class ExcelkDao extends BaseDao<Excelk>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Excelk.class;
	}
	/**
	 * 添加  删除  修改  查询  
	 * @param 
	 * @return
	 */
	
	public List<Excelk> getPictureData(Long processId){
		List<Excelk> list = getBySqlKey("getPictureData", processId);
		return list;
	}
	public List<Excelk> getTimeByIdAndTime(String time,Long processId){
		Map  map=new HashMap();
		map.put("time", time);
		map.put("processId", processId);
		List<Excelk> list = getBySqlKey("getTimeByIdAndTime",map);
		return list;
	}
	public  Excelk getByIdAndTime(String time,Long processId){
		Map  map=new HashMap();
		map.put("time", time);
		map.put("processId", processId);
		Excelk list =getUnique("getByIdAndTime", map);
		return list;
	}
}
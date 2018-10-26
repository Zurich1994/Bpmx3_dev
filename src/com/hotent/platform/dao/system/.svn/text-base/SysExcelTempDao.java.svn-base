package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysExcelTemp;
import com.hotent.platform.model.system.SysExcelTempDetail;

/**
 * <pre>
 * 对象功能:Excel模板 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-06-13 14:36:35
 * </pre>
 */
@Repository
public class SysExcelTempDao extends BaseDao<SysExcelTemp> {
	@Override
	public Class<?> getEntityClass() {
		return SysExcelTemp.class;
	}
	
	/**
	 * 判断别名是否已经存在了
	 * @param tempCode 
	 * @param id
	 * @return
	 */
	public boolean isTempCodeExist(String tempCode, Long id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tempCode", tempCode.trim());
		params.put("id", id);
		return (Integer) this.getOne("isTempCodeExist", params) > 0;
	}
	
	/**
	 * 模板样例数据
	 * @param id
	 * @param tempDataSample
	 */
	public void updateTempDataSample(Long id, String tempDataSample) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("tempDataSample", tempDataSample);
		this.update("updateTempDataSample", params);
	}
	
	/**
	 * 根据别名获取对象
	 * @param tempCode
	 * @return
	 */
	public SysExcelTemp getByTempCode(String tempCode) {
		return this.getUnique("getByTempCode", tempCode);
	}
	
}
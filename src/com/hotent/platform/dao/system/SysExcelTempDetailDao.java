package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysExcelTempDetail;

/**
 * <pre>
 * 对象功能:Excel模板明细 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-06-13 14:36:35
 * </pre>
 */
@Repository
public class SysExcelTempDetailDao extends BaseDao<SysExcelTempDetail> {
	@Override
	public Class<?> getEntityClass() {
		return SysExcelTempDetail.class;
	}

	/**
	 * 根据外键获取子表明细列表
	 * 
	 * @param tempId
	 * @return
	 */
	public List<SysExcelTempDetail> getByMainId(Long tempId) {
		return this.getBySqlKey("getSysExcelTempDetailList", tempId);
	}

	/**
	 * 根据外键删除子表记录
	 * 
	 * @param tempId
	 * @return
	 */
	public void delByMainId(Long tempId) {
		this.delBySqlKey("delByMainId", tempId);
	}
	
	/**
	 * 根据别名获取明细
	 * @param tempCode
	 * @return
	 */
	public List<SysExcelTempDetail> getAllByTempCode(String tempCode) {
		return this.getBySqlKey("getAllByTempCode", tempCode);
	}

}
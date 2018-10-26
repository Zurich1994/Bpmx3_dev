package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsShiftRuleDetail;

/**
 * <pre>
 * 对象功能:轮班规则明细 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-21 09:06:50
 * </pre>
 */
@Repository
public class AtsShiftRuleDetailDao extends BaseDao<AtsShiftRuleDetail> {
	@Override
	public Class<?> getEntityClass() {
		return AtsShiftRuleDetail.class;
	}

	public void delByRuleId(Long ruleId) {
		this.delBySqlKey("delByRuleId",ruleId);
	}

	public List<AtsShiftRuleDetail> getByRuleId(Long ruleId) {
		return getBySqlKey("getByRuleId", ruleId);
	}

}
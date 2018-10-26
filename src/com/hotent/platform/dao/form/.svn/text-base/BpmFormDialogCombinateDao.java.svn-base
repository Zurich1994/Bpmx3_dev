package com.hotent.platform.dao.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmFormDialogCombinate;

/**
 * <pre>
 * 对象功能:bpm_form_dialog_combinate Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-06 11:36:18
 * </pre>
 */
@Repository
public class BpmFormDialogCombinateDao extends BaseDao<BpmFormDialogCombinate> {
	@Override
	public Class<?> getEntityClass() {
		return BpmFormDialogCombinate.class;
	}

	public BpmFormDialogCombinate getByAlias(String alias) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alias", alias);
		return (BpmFormDialogCombinate) this.getOne("getByAlias", map);
	}

}
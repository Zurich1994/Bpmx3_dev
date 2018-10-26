package com.hotent.platform.service.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.model.bpm.ProcessRun;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.form.BpmFormDialogCombinate;
import com.hotent.platform.dao.form.BpmFormDialogCombinateDao;
import com.hotent.core.service.BaseService;

/**
 * <pre>
 * 对象功能:bpm_form_dialog_combinate Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-06 11:36:18
 * </pre>
 */
@Service
public class BpmFormDialogCombinateService extends BaseService<BpmFormDialogCombinate> {
	@Resource
	private BpmFormDialogCombinateDao dao;

	public BpmFormDialogCombinateService() {
	}

	@Override
	protected IEntityDao<BpmFormDialogCombinate, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据json字符串获取BpmFormDialogCombinate对象
	 * 
	 * @param json
	 * @return
	 */
	public BpmFormDialogCombinate getBpmFormDialogCombinate(String json) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(json))
			return null;
		JSONObject obj = JSONObject.fromObject(json);
		BpmFormDialogCombinate bpmFormDialogCombinate = (BpmFormDialogCombinate) JSONObject.toBean(obj, BpmFormDialogCombinate.class);
		return bpmFormDialogCombinate;
	}

	/**
	 * 保存 bpm_form_dialog_combinate 信息
	 * 
	 * @param bpmFormDialogCombinate
	 * @throws Exception
	 */
	public void save(BpmFormDialogCombinate bpmFormDialogCombinate) throws Exception {

		Long id = bpmFormDialogCombinate.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			bpmFormDialogCombinate.setId(id);
			this.add(bpmFormDialogCombinate);
		} else {
			this.update(bpmFormDialogCombinate);
		}
	}

	public BpmFormDialogCombinate getByAlias(String alias) {
		return dao.getByAlias(alias);
	}
}

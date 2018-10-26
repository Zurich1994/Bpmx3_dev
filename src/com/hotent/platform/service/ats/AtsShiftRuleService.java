package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsShiftRuleDao;
import com.hotent.platform.dao.ats.AtsShiftRuleDetailDao;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.ats.AtsShiftRule;
import com.hotent.platform.model.ats.AtsShiftRuleDetail;

/**
 *<pre>
 * 对象功能:轮班规则 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-21 09:06:10
 *</pre>
 */
@Service
public class AtsShiftRuleService extends  BaseService<AtsShiftRule>
{
	@Resource
	private AtsShiftRuleDao dao;
	@Resource
	private AtsShiftRuleDetailDao atsShiftRuleDetailDao;
	@Resource
	private AtsShiftInfoService atsShiftInfoService;
	@Resource
	private AtsShiftTimeService atsShiftTimeService;
	
	
	public AtsShiftRuleService()
	{
	}
	
	@Override
	protected IEntityDao<AtsShiftRule, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 轮班规则 信息
	 * @param atsShiftRule
	 */
	public void save(AtsShiftRule atsShiftRule){
		Long id=atsShiftRule.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsShiftRule.setId(id);
			this.add(atsShiftRule);
		}
		else{
			this.update(atsShiftRule);
			atsShiftRuleDetailDao.delByRuleId(id);
		}
		
		String detailList = atsShiftRule.getDetailList();
		if(StringUtils.isEmpty(detailList))
			return;
		JSONArray jary = JSONArray.fromObject(detailList);
		for (int i = 0; i < jary.size(); i++) {
			JSONObject json =  (JSONObject) jary.get(i);
			String dateType = (String) json.get("dateType");
			
			Object shiftId = json.get("shiftId");;
		
			AtsShiftRuleDetail srd = new AtsShiftRuleDetail();
			
			srd.setDateType(Short.parseShort(dateType));
			if(BeanUtils.isNotEmpty(shiftId))
			srd.setShiftId(Long.parseLong(shiftId.toString()));
			srd.setSn(i+1);
			srd.setRuleId(id);
			srd.setId(UniqueIdUtil.genId());
			atsShiftRuleDetailDao.add(srd);
		}
	}

	public String getDetailList(Long id) {
		List<AtsShiftRuleDetail> list = atsShiftRuleDetailDao
				.getByRuleId(id);
		JSONArray jary = new JSONArray();
		for (AtsShiftRuleDetail srd : list) {
			JSONObject json = new JSONObject();
			Long shiftId = srd.getShiftId();
			
			String shiftId1 = "";
			String shiftCode = "";
			String shiftName = "";
			String shiftTime = "";
			if(BeanUtils.isNotEmpty(shiftId)){
				AtsShiftInfo atsShiftInfo = atsShiftInfoService.getById(shiftId);
				shiftId1 = shiftId.toString();
				shiftTime = atsShiftTimeService.getShiftTime(shiftId);
				shiftCode = atsShiftInfo.getCode();
				shiftName = atsShiftInfo.getName();
			}
			
			
			json.accumulate("sn", srd.getSn().toString());
			json.accumulate("dateType", srd.getDateType().toString());
			json.accumulate("shiftId",shiftId1);
			json.accumulate("shiftCode",shiftCode);
			json.accumulate("shiftName",shiftName);
		
			json.accumulate("shiftTime",shiftTime);
			jary.add(json);
		}
		return jary.toString();
	}
	
}

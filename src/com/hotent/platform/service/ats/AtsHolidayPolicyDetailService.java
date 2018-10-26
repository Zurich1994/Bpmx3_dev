package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsHolidayPolicyDetailDao;
import com.hotent.platform.model.ats.AtsHolidayPolicyDetail;

/**
 *<pre>
 * 对象功能:假期制度明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-05-28 14:25:03
 *</pre>
 */
@Service
public class AtsHolidayPolicyDetailService extends  BaseService<AtsHolidayPolicyDetail>
{
	@Resource
	private AtsHolidayPolicyDetailDao dao;
	
	
	
	public AtsHolidayPolicyDetailService()
	{
	}
	
	@Override
	protected IEntityDao<AtsHolidayPolicyDetail, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 根据json字符串获取AtsHolidayPolicyDetail对象
	 * @param json
	 * @return
	 */
	public AtsHolidayPolicyDetail getAtsHolidayPolicyDetail(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		AtsHolidayPolicyDetail atsHolidayPolicyDetail = (AtsHolidayPolicyDetail)JSONObject.toBean(obj, AtsHolidayPolicyDetail.class);
		return atsHolidayPolicyDetail;
	}
	
	/**
	 * 保存 假期制度明细 信息
	 * @param atsHolidayPolicyDetail
	 */
	public void save(AtsHolidayPolicyDetail atsHolidayPolicyDetail){
		Long id=atsHolidayPolicyDetail.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsHolidayPolicyDetail.setId(id);
			this.add(atsHolidayPolicyDetail);
		}
		else{
			this.update(atsHolidayPolicyDetail);
		}
	}
	
}

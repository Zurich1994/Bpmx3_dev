package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsAttenceGroupDao;
import com.hotent.platform.dao.ats.AtsAttenceGroupDetailDao;
import com.hotent.platform.dao.ats.AtsAttendanceFileDao;
import com.hotent.platform.model.ats.AtsAttenceGroup;
import com.hotent.platform.model.ats.AtsAttenceGroupDetail;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsCalendarTemplDetail;

/**
 *<pre>
 * 对象功能:考勤组 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 10:07:27
 *</pre>
 */
@Service
public class AtsAttenceGroupService extends  BaseService<AtsAttenceGroup>
{
	@Resource
	private AtsAttenceGroupDao dao;
	@Resource
	private AtsAttenceGroupDetailDao atsAttenceGroupDetailDao;
	@Resource
	private AtsAttendanceFileDao atsAttendanceFileDao;
	
	public AtsAttenceGroupService()
	{
	}
	
	@Override
	protected IEntityDao<AtsAttenceGroup, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 考勤组 信息
	 * @param atsAttenceGroup
	 */
	public void save(AtsAttenceGroup atsAttenceGroup){
		Long id=atsAttenceGroup.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsAttenceGroup.setId(id);
			this.add(atsAttenceGroup);
		}
		else{
			this.update(atsAttenceGroup);
			atsAttenceGroupDetailDao.delByGroupId(id);
		}
		String detailList = atsAttenceGroup.getDetailList();
		JSONArray jary = JSONArray.fromObject(detailList);
		for (Object obj : jary) {
			JSONObject json = (JSONObject) obj;
			String fileId = (String) json.get("id");
			AtsAttenceGroupDetail ag = new AtsAttenceGroupDetail();
			ag.setFileId(Long.parseLong(fileId));
			ag.setGroupId(id);
			ag.setId(UniqueIdUtil.genId());
			atsAttenceGroupDetailDao.add(ag);
		}
	}

	public String getDetailList(Long id) {
		List<AtsAttenceGroupDetail> list = atsAttenceGroupDetailDao
				.getByGroupId(id);
		JSONArray jary = new JSONArray();
		for (AtsAttenceGroupDetail ag: list) {
			JSONObject json = new JSONObject();
			AtsAttendanceFile atsAttendanceFile = atsAttendanceFileDao.getByFileId(ag.getFileId());
			json.accumulate("id", ag.getFileId().toString())
				.accumulate("account", atsAttendanceFile.getAccount())
				.accumulate("userName", atsAttendanceFile.getUserName())
				.accumulate("orgName", atsAttendanceFile.getOrgName());
			jary.add(json);
		}
		return jary.toString();
	}
	
}

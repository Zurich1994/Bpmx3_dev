package com.hotent.platform.service.system;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysQueryFieldSettingDao;
import com.hotent.platform.model.system.SysQueryFieldSetting;

/**
 *<pre>
 * 对象功能:视图自定义设定 Service类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Service
public class SysQueryFieldSettingService extends  BaseService<SysQueryFieldSetting>
{
	@Resource
	private SysQueryFieldSettingDao dao;
	
	
	
	public SysQueryFieldSettingService()
	{
	}
	
	@Override
	protected IEntityDao<SysQueryFieldSetting, Long> getEntityDao() 
	{
		return dao;
	}
	
	

	
	/**
	 * 根据json字符串获取SysQueryFieldSetting对象
	 * @param json
	 * @return
	 */
	public SysQueryFieldSetting getSysQueryFieldSetting(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysQueryFieldSetting sysQueryFieldSetting = (SysQueryFieldSetting)JSONObject.toBean(obj, SysQueryFieldSetting.class);
		return sysQueryFieldSetting;
	}
	
	/**
	 * 保存 视图自定义设定 信息
	 * @param sysQueryFieldSetting
	 */
	public void save(SysQueryFieldSetting sysQueryFieldSetting){
		Long id=sysQueryFieldSetting.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysQueryFieldSetting.setId(id);
			this.add(sysQueryFieldSetting);
		}
		else{
			this.update(sysQueryFieldSetting);
		}
	}
	public void removeBySysQueryViewId(Long viewId){
		dao.removeBySysQueryViewId(viewId);
	}

	public List<SysQueryFieldSetting> getBySysQueryViewId(Long viewId) {
		return dao.getBySysQueryViewId(viewId);
	}
}

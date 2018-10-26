package com.hotent.platform.service.system;
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
import com.hotent.platform.model.system.SysBusEvent;
import com.hotent.platform.dao.system.SysBusEventDao;
import com.hotent.core.service.BaseService;

/**
 *<pre>
 * 对象功能:sys_bus_event Service类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-05-22 11:14:30
 *</pre>
 */
@Service
public class SysBusEventService extends  BaseService<SysBusEvent>
{
	@Resource
	private SysBusEventDao dao;
	
	
	
	public SysBusEventService()
	{
	}
	
	@Override
	protected IEntityDao<SysBusEvent, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			SysBusEvent sysBusEvent=getSysBusEvent(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				sysBusEvent.setId(genId);
				this.add(sysBusEvent);
			}else{
				sysBusEvent.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(sysBusEvent);
			}
			cmd.setBusinessKey(sysBusEvent.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取SysBusEvent对象
	 * @param json
	 * @return
	 */
	public SysBusEvent getSysBusEvent(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysBusEvent sysBusEvent = (SysBusEvent)JSONObject.toBean(obj, SysBusEvent.class);
		return sysBusEvent;
	}
	
	/**
	 * 保存 sys_bus_event 信息
	 * @param sysBusEvent
	 */
	public void save(SysBusEvent sysBusEvent){
		Long id=sysBusEvent.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysBusEvent.setId(id);
			this.add(sysBusEvent);
		}
		else{
			this.update(sysBusEvent);
		}
	}
	
	/**
	 * 根据formKey获取脚本验证。
	 * @param formKey
	 * @return
	 */
	public SysBusEvent getByFormKey(String formKey){
		return dao.getByFormKey(formKey);
	}
	
	
}

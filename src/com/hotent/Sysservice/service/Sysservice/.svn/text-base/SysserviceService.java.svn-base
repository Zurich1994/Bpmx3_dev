package com.hotent.Sysservice.service.Sysservice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Sysservice.model.Sysservice.Sysservice;
import com.hotent.Sysservice.dao.Sysservice.SysserviceDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class SysserviceService extends BaseService<Sysservice>
{
	@Resource
	private SysserviceDao dao;
	
	public SysserviceService()
	{
	}
	
	@Override
	protected IEntityDao<Sysservice,Long> getEntityDao() 
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
			Sysservice sysservice=getSysservice(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				sysservice.setId(genId);
				this.add(sysservice);
			}else{
				sysservice.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(sysservice);
			}
			cmd.setBusinessKey(sysservice.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Sysservice对象
	 * @param json
	 * @return
	 */
	public Sysservice getSysservice(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Sysservice sysservice = (Sysservice)JSONObject.toBean(obj, Sysservice.class);
		return sysservice;
	}
	/**
	 * 保存 子系统服务表 信息
	 * @param sysservice
	 */

	public void save(Sysservice sysservice) throws Exception{
		Long id=sysservice.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysservice.setId(id);
		    this.add(sysservice);
		}
		else{
		   this.update(sysservice);
		}
	}
}

package com.hotent.application.service.application;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.application.model.application.Application;
import com.hotent.application.dao.application.ApplicationDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class ApplicationService extends BaseService<Application>
{
	@Resource
	private ApplicationDao dao;
	
	public ApplicationService()
	{
	}
	
	@Override
	protected IEntityDao<Application,Long> getEntityDao() 
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
			Application application=getApplication(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				application.setId(genId);
				this.add(application);
			}else{
				application.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(application);
			}
			cmd.setBusinessKey(application.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Application对象
	 * @param json
	 * @return
	 */
	public Application getApplication(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Application application = (Application)JSONObject.toBean(obj, Application.class);
		return application;
	}
	/**
	 * 保存 应用表 信息
	 * @param application
	 */

	public void save(Application application) throws Exception{
		Long id=application.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			application.setId(id);
		    this.add(application);
		}
		else{
		   this.update(application);
		}
	}


}

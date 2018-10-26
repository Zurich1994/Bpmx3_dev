package com.hotent.Assemble.service.Assemble;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Assemble.model.Assemble.Assemble;
import com.hotent.Assemble.dao.Assemble.AssembleDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class AssembleService extends BaseService<Assemble>
{
	@Resource
	private AssembleDao dao;
	
	public AssembleService()
	{
	}
	
	@Override
	protected IEntityDao<Assemble,Long> getEntityDao() 
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
			Assemble assemble=getAssemble(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				assemble.setId(genId);
				this.add(assemble);
			}else{
				assemble.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(assemble);
			}
			cmd.setBusinessKey(assemble.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Assemble对象
	 * @param json
	 * @return
	 */
	public Assemble getAssemble(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Assemble assemble = (Assemble)JSONObject.toBean(obj, Assemble.class);
		return assemble;
	}
	/**
	 * 保存 组装表 信息
	 * @param assemble
	 */

	public void save(Assemble assemble) throws Exception{
		Long id=assemble.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			assemble.setId(id);
		    this.add(assemble);
		}
		else{
		   this.update(assemble);
		}
	}

	public List<Assemble> getBySystemId(Long systemId) {
		// TODO Auto-generated method stub
		return dao.getBySystemId(systemId);
	}


	public List<Assemble> getByServiceId(Long serviceId) {
		// TODO Auto-generated method stub
		return dao.getByServiceId(serviceId);
	}

	public void gengxin(Long a, Long applicationId) {
		// TODO Auto-generated method stub
		 dao.gengxin(a,applicationId);
	}

	public void cleanByApplicationId(Long applicationId) {
		// TODO Auto-generated method stub
		dao.cleanByApplicationId(applicationId);
	}
	
	

	public void cleanByApplicationIdAndSystemId(Long applicationId,
			Long systemId) {
		// TODO Auto-generated method stub
		dao.cleanByApplicationIdAndSystemId(applicationId, systemId);
	}

	public void cleanByApplicationIdAndServiceId(Long applicationId,
			Long serviceId) {
		// TODO Auto-generated method stub
		dao.cleanByApplicationIdAndServiceId(applicationId, serviceId);
	}
	 
	
}

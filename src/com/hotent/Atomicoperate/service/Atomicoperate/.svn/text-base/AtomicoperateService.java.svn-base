package com.hotent.Atomicoperate.service.Atomicoperate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Atomicoperate.model.Atomicoperate.Atomicoperate;
import com.hotent.Atomicoperate.dao.Atomicoperate.AtomicoperateDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class AtomicoperateService extends BaseService<Atomicoperate>
{
	@Resource
	private AtomicoperateDao dao;
	
	public AtomicoperateService()
	{
	}
	
	@Override
	protected IEntityDao<Atomicoperate,Long> getEntityDao() 
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
			Atomicoperate atomicoperate=getAtomicoperate(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				atomicoperate.setId(genId);
				this.add(atomicoperate);
			}else{
				atomicoperate.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(atomicoperate);
			}
			cmd.setBusinessKey(atomicoperate.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Atomicoperate对象
	 * @param json
	 * @return
	 */
	public Atomicoperate getAtomicoperate(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Atomicoperate atomicoperate = (Atomicoperate)JSONObject.toBean(obj, Atomicoperate.class);
		return atomicoperate;
	}
	/**
	 * 保存 原子操作表 信息
	 * @param atomicoperate
	 */

	public void save(Atomicoperate atomicoperate) throws Exception{
		Long id=atomicoperate.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atomicoperate.setId(id);
		    this.add(atomicoperate);
		}
		else{
		   this.update(atomicoperate);
		}
	}
	public List<Atomicoperate> list(Long serviceId){
		  return this.dao.getByServiceId(serviceId);
		}
}

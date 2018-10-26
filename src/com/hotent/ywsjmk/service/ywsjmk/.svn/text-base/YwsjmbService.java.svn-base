package com.hotent.ywsjmk.service.ywsjmk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.ywsjmk.model.ywsjmk.Ywsjmb;
import com.hotent.ywsjmk.dao.ywsjmk.YwsjmbDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class YwsjmbService extends BaseService<Ywsjmb>
{
	@Resource
	private YwsjmbDao dao;
	
	
	
	public void delByFormkey(String[] ids)
	{
		if(BeanUtils.isEmpty(ids)) return;
		for (int i = 0; i < ids.length; i++)
		{
			dao.delByFormkey(ids[i]);
		}
	}
	public void delById(Long ids)
	{
		dao.delById(ids);
	}
	
	@Override
	protected IEntityDao<Ywsjmb,Long> getEntityDao() 
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
			Ywsjmb ywsjmb=getYwsjmb(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				ywsjmb.setId(genId);
				this.add(ywsjmb);
			}else{
				ywsjmb.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(ywsjmb);
			}
			cmd.setBusinessKey(ywsjmb.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Ywsjmb对象
	 * @param json
	 * @return
	 */
	public Ywsjmb getYwsjmb(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Ywsjmb ywsjmb = (Ywsjmb)JSONObject.toBean(obj, Ywsjmb.class);
		return ywsjmb;
	}
	/**
	 * 保存 业务数据模板 信息
	 * @param ywsjmb
	 */

	public void save(Ywsjmb ywsjmb) throws Exception{
		Long id=ywsjmb.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			ywsjmb.setId(id);
		    this.add(ywsjmb);
		}
		else{
		   this.update(ywsjmb);
		}
	}
}

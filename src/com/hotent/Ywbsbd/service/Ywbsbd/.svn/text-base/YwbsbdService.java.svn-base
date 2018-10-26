package com.hotent.Ywbsbd.service.Ywbsbd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Ywbsbd.model.Ywbsbd.Ywbsbd;
import com.hotent.Ywbsbd.dao.Ywbsbd.YwbsbdDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class YwbsbdService extends BaseService<Ywbsbd>
{
	@Resource
	private YwbsbdDao dao;
	
	public YwbsbdService()
	{
	}
	
	@Override
	protected IEntityDao<Ywbsbd,Long> getEntityDao() 
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
			Ywbsbd ywbsbd=getYwbsbd(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				ywbsbd.setId(genId);
				this.add(ywbsbd);
			}else{
				ywbsbd.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(ywbsbd);
			}
			cmd.setBusinessKey(ywbsbd.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Ywbsbd对象
	 * @param json
	 * @return
	 */
	public Ywbsbd getYwbsbd(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Ywbsbd ywbsbd = (Ywbsbd)JSONObject.toBean(obj, Ywbsbd.class);
		return ywbsbd;
	}
	/**
	 * 保存 业务部署绑定 信息
	 * @param ywbsbd
	 */

	public void save(Ywbsbd ywbsbd) throws Exception{
		Long id=ywbsbd.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			ywbsbd.setId(id);
		    this.add(ywbsbd);
		}
		else{
		   this.update(ywbsbd);
		}
	}

	public boolean exist(String defId, String nodeId, String serivce,
			String systemId) {
		// TODO Auto-generated method stub
		return dao.exist(defId,nodeId,serivce,systemId);
	}

	public List<Ywbsbd> getBySystemId(String systemId) {
		// TODO Auto-generated method stub
		return dao.getBySystemId(systemId);
	}
}

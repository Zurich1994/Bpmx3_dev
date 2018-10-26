package com.hotent.Serviceproducts.service.Serviceproducts;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Serviceproducts.model.Serviceproducts.Serviceproducts;
import com.hotent.Serviceproducts.dao.Serviceproducts.ServiceproductsDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class ServiceproductsService extends BaseService<Serviceproducts>
{
	@Resource
	private ServiceproductsDao dao;
	
	public ServiceproductsService()
	{
	}
	
	@Override
	protected IEntityDao<Serviceproducts,Long> getEntityDao() 
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
			Serviceproducts serviceproducts=getServiceproducts(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				serviceproducts.setId(genId);
				this.add(serviceproducts);
			}else{
				serviceproducts.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(serviceproducts);
			}
			cmd.setBusinessKey(serviceproducts.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Serviceproducts对象
	 * @param json
	 * @return
	 */
	public Serviceproducts getServiceproducts(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Serviceproducts serviceproducts = (Serviceproducts)JSONObject.toBean(obj, Serviceproducts.class);
		return serviceproducts;
	}
	/**
	 * 保存 服务产品表 信息
	 * @param serviceproducts
	 */

	public void save(Serviceproducts serviceproducts) throws Exception{
		Long id=serviceproducts.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			serviceproducts.setId(id);
		    this.add(serviceproducts);
		}
		else{
		   this.update(serviceproducts);
		}
	}

	public List<Serviceproducts> getByserviceId(Long serviceId) {
		// TODO Auto-generated method stub
		return dao.getByserviceId(serviceId);
	}
}

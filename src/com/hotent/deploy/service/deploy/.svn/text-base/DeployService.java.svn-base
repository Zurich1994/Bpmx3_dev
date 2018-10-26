package com.hotent.deploy.service.deploy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hotent.application.model.application.Application;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.deploy.model.deploy.Deploy;
import com.hotent.deploy.dao.deploy.DeployDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class DeployService extends BaseService<Deploy>
{
	@Resource
	private DeployDao dao;
	
	public DeployService()
	{
	}
	
	@Override
	protected IEntityDao<Deploy,Long> getEntityDao() 
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
			Deploy deploy=getDeploy(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				deploy.setId(genId);
				this.add(deploy);
			}else{
				deploy.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(deploy);
			}
			cmd.setBusinessKey(deploy.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Deploy对象
	 * @param json
	 * @return
	 */
	public Deploy getDeploy(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Deploy deploy = (Deploy)JSONObject.toBean(obj, Deploy.class);
		return deploy;
	}
	/**
	 * 保存 部署表 信息
	 * @param deploy
	 */

	public void save(Deploy deploy) throws Exception{
		Long id=deploy.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			deploy.setId(id);
		    this.add(deploy);
		}
		else{
		   this.update(deploy);
		}
	}

	public List<Deploy> getByEqupmentId(Long equipmentId) {
		// TODO Auto-generated method stub
		return dao.getByEqupmentId(equipmentId);
	}

	public void delByEquipmentId(Long equipmentId) {
		// TODO Auto-generated method stub
		dao.delByEquipmentId(equipmentId);
	}
	public void updateEquipmentIdByApplicationId(Long[] applicationIds,
			Long equipmentId) {
		// TODO Auto-generated method stub
		for(Long a:applicationIds)
			dao.updateEquipmentIdByApplicationId(a,equipmentId);
	}
	public void delByEquipmentIdAndApplicationList(Long equipmentId,
			List<Application> list) {
		// TODO Auto-generated method stub
		for(Application application:list){
			dao.delByEquipmentIdAndApplicationId(equipmentId, application.getId());
		}
	}
}

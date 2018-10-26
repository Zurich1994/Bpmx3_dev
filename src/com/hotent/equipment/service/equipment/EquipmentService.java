package com.hotent.equipment.service.equipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.equipment.model.equipment.Equipment;
import com.hotent.equipment.dao.equipment.EquipmentDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class EquipmentService extends BaseService<Equipment>
{
	@Resource
	private EquipmentDao dao;
	
	public EquipmentService()
	{
	}
	
	@Override
	protected IEntityDao<Equipment,Long> getEntityDao() 
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
			Equipment equipment=getEquipment(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				equipment.setId(genId);
				this.add(equipment);
			}else{
				equipment.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(equipment);
			}
			cmd.setBusinessKey(equipment.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Equipment对象
	 * @param json
	 * @return
	 */
	public Equipment getEquipment(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Equipment equipment = (Equipment)JSONObject.toBean(obj, Equipment.class);
		return equipment;
	}
	/**
	 * 保存 设备表 信息
	 * @param equipment
	 */

	public void save(Equipment equipment) throws Exception{
		Long id=equipment.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			equipment.setId(id);
		    this.add(equipment);
		}
		else{
		   this.update(equipment);
		}
	}
}

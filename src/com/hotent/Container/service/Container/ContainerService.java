package com.hotent.Container.service.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Container.model.Container.Container;
import com.hotent.Container.dao.Container.ContainerDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class ContainerService extends BaseService<Container>
{
	@Resource
	private ContainerDao dao;
	
	public ContainerService()
	{
	}
	
	@Override
	protected IEntityDao<Container,Long> getEntityDao() 
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
			Container container=getContainer(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				container.setId(genId);
				this.add(container);
			}else{
				container.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(container);
			}
			cmd.setBusinessKey(container.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Container对象
	 * @param json
	 * @return
	 */
	public Container getContainer(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Container container = (Container)JSONObject.toBean(obj, Container.class);
		return container;
	}
	/**
	 * 保存 容器表 信息
	 * @param container
	 */

	public void save(Container container) throws Exception{
		Long id=container.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			container.setId(id);
		    this.add(container);
		}
		else{
		   this.update(container);
		}
	}
	
	 
	
}

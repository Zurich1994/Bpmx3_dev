package com.hotent.EventGraph.service.EventGraph;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.EventGraph.model.EventGraph.EventGraph;
import com.hotent.EventGraph.dao.EventGraph.EventGraphDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class EventGraphService extends BaseService<EventGraph>
{
	@Resource
	private EventGraphDao dao;
	
	public EventGraphService()
	{
	}
	
	@Override
	protected IEntityDao<EventGraph,Long> getEntityDao() 
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
			EventGraph eventGraph=getEventGraph(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				eventGraph.setId(genId);
				this.add(eventGraph);
			}else{
				eventGraph.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(eventGraph);
			}
			cmd.setBusinessKey(eventGraph.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取EventGraph对象
	 * @param json
	 * @return
	 */
	public EventGraph getEventGraph(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		EventGraph eventGraph = (EventGraph)JSONObject.toBean(obj, EventGraph.class);
		return eventGraph;
	}
	/**
	 * 保存 事件绑定 信息
	 * @param eventGraph
	 */

	public void save(EventGraph eventGraph) throws Exception{
		Long id=eventGraph.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			eventGraph.setId(id);
		    this.add(eventGraph);
		}
		else{
		   this.update(eventGraph);
		}
	}


/***增删改查
**@param eventGraph
****/
}

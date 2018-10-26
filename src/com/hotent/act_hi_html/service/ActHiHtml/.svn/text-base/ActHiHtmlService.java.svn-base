package com.hotent.act_hi_html.service.ActHiHtml;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.act_hi_html.model.ActHiHtml.ActHiHtml;
import com.hotent.act_hi_html.dao.ActHiHtml.ActHiHtmlDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class ActHiHtmlService extends BaseService<ActHiHtml>
{
	@Resource
	private ActHiHtmlDao dao;
	
	public ActHiHtmlService()
	{
	}
	
	@Override
	protected IEntityDao<ActHiHtml,Long> getEntityDao() 
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
			ActHiHtml actHiHtml=getActHiHtml(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				actHiHtml.setId(genId);
				this.add(actHiHtml);
			}else{
				actHiHtml.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(actHiHtml);
			}
			cmd.setBusinessKey(actHiHtml.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取ActHiHtml对象
	 * @param json
	 * @return
	 */
	public ActHiHtml getActHiHtml(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		ActHiHtml actHiHtml = (ActHiHtml)JSONObject.toBean(obj, ActHiHtml.class);
		return actHiHtml;
	}
	/**
	 * 保存 act_hi_html 信息
	 * @param actHiHtml
	 */

	public void save(ActHiHtml actHiHtml) throws Exception{
		Long id=actHiHtml.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			actHiHtml.setId(id);
		    this.add(actHiHtml);
		}
		else{
		   this.update(actHiHtml);
		}
	}
}

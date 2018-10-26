package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.model.bpm.ProcessRun;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.PageTest;
import com.hotent.platform.dao.system.PageTestDao;
import com.hotent.core.service.BaseService;

/**
 *<pre>
 * 对象功能:PageTest Service类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-02 22:29:22
 *</pre>
 */
@Service
public class PageTestService extends  BaseService<PageTest>
{
	@Resource
	private PageTestDao dao;
	
	
	
	public PageTestService()
	{
	}
	
	@Override
	protected IEntityDao<PageTest, Long> getEntityDao() 
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
			PageTest pageTest=getPageTest(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				pageTest.setId(genId);
				this.add(pageTest);
			}else{
				pageTest.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(pageTest);
			}
			cmd.setBusinessKey(pageTest.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取PageTest对象
	 * @param json
	 * @return
	 */
	public PageTest getPageTest(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		PageTest pageTest = (PageTest)JSONObject.toBean(obj, PageTest.class);
		return pageTest;
	}
	
	/**
	 * 保存 PageTest 信息
	 * @param pageTest
	 */
	public void save(PageTest pageTest){
		Long id=pageTest.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			pageTest.setId(id);
			this.add(pageTest);
		}
		else{
			this.update(pageTest);
		}
	}
	
}

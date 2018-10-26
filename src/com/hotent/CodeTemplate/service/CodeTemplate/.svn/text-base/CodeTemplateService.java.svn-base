package com.hotent.CodeTemplate.service.CodeTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.CodeTemplate.model.CodeTemplate.CodeTemplate;
import com.hotent.CodeTemplate.dao.CodeTemplate.CodeTemplateDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class CodeTemplateService extends BaseService<CodeTemplate>
{
	@Resource
	private CodeTemplateDao dao;
	
	public CodeTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<CodeTemplate,Long> getEntityDao() 
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
			CodeTemplate codeTemplate=getCodeTemplate(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				codeTemplate.setId(genId);
				this.add(codeTemplate);
			}else{
				codeTemplate.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(codeTemplate);
			}
			cmd.setBusinessKey(codeTemplate.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取CodeTemplate对象
	 * @param json
	 * @return
	 */
	public CodeTemplate getCodeTemplate(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		CodeTemplate codeTemplate = (CodeTemplate)JSONObject.toBean(obj, CodeTemplate.class);
		return codeTemplate;
	}
	/**
	 * 保存 流程模板 信息
	 * @param codeTemplate
	 */

	public void save(CodeTemplate codeTemplate) throws Exception{
		Long id=codeTemplate.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			codeTemplate.setId(id);
		    this.add(codeTemplate);
		}
		else{
		   this.update(codeTemplate);
		}
	}
	
	/**
	 * 查询模板表里的流程id
	 * @param subject
	 * @return
	 */
	public long getByBm(String subject) {
		List<CodeTemplate> list = dao.getByBm(subject);
		CodeTemplate codeTemplate = list.get(0);
		long defId = codeTemplate.getSubId();
		return defId;
	}
}


package com.hotent.CodeTemplate.dao.CodeTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmDefinition;

import com.hotent.CodeTemplate.model.CodeTemplate.CodeTemplate;

@Repository
public class CodeTemplateDao extends BaseDao<CodeTemplate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return CodeTemplate.class;
	}
	/**
	 * 查询模板表里的流程id
	 * @param subject
	 * @return
	 */
	public List<CodeTemplate> getByBm(String subject) {
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("subject", subject);
		
		return getBySqlKey("getByBm",subject);
	}

}
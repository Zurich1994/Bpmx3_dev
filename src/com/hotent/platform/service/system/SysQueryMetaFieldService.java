package com.hotent.platform.service.system;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysQueryMetaFieldDao;
import com.hotent.platform.model.system.SysQueryMetaField;

/**
 *<pre>
 * 对象功能:自定义SQL字段定义 Service类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Service
public class SysQueryMetaFieldService extends  BaseService<SysQueryMetaField>
{
	@Resource
	private SysQueryMetaFieldDao dao;
	
	public SysQueryMetaFieldService()
	{
	}
	
	@Override
	protected IEntityDao<SysQueryMetaField, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据json字符串获取SysQueryMetaField对象
	 * @param json
	 * @return
	 */
	public SysQueryMetaField getSysQueryMetaField(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysQueryMetaField sysQueryMetaField = (SysQueryMetaField)JSONObject.toBean(obj, SysQueryMetaField.class);
		return sysQueryMetaField;
	}
	
	/**
	 * 根据sqlid获取字段列定义。
	 * @param sqlId
	 * @return
	 */
	public List<SysQueryMetaField> getListBySqlId(Long sqlId) {
		List<SysQueryMetaField> list = dao.getListBySqlId(sqlId);
		return list;
	}
	
	/**
	 * 根据sqlAlias别名获取字段列表。
	 * @param sqlAlias
	 * @return
	 */
	public List<SysQueryMetaField> getListBySqlAlias(String sqlAlias) {
		List<SysQueryMetaField> list = dao.getListBySqlAlias(sqlAlias);
		return list;
	}
	
	
	/**
	 * 根据视图的ID获取对应的字段数据。
	 * @param viewId	视图ID
	 * @param type		-1,全部,0,基础字段,1,虚拟列
	 * @return
	 */
	public List<SysQueryMetaField> getListByView(Long viewId,int type){
		return  dao.getListByView(viewId,type);
	}

	public void removeBySQLId(Long sqlId) {
		dao.removeBySQLId(sqlId);
	}
}

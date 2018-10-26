package com.hotent.platform.service.form;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.model.form.BpmFormData;


/**
 * 对象功能:表数据类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-02-10 10:48:16
 */
@Service
public class BpmFormTableDataService
{
	
	@Resource
	private BpmFormHandlerDao dao;
	
	/**
	 * 获取列表
	 * @param tableId
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getAll(Long tableId, Map<String, Object> param) throws Exception {
		return dao.getAll(tableId, param);
	}
	
	/**
	 * 根据主键查询列表数据
	 * @param tableId
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public BpmFormData getByKey(Long tableId, String pkValue) throws Exception {
		return dao.getByKey(tableId, pkValue,true);
	}
	
}

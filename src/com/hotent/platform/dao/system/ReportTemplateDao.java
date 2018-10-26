/**
 * 对象功能:报表模板 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-04-12 09:59:47
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.ReportTemplate;

@Repository
public class ReportTemplateDao extends BaseDao<ReportTemplate>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return ReportTemplate.class;
	}
	
	public List<ReportTemplate> getReportTemplateByTitle(String reportTitle){
		List<ReportTemplate> reportTemplateList = getBySqlKey("getByTitle", reportTitle);
		System.out.println("reportTemplateListSize:"+reportTemplateList.size());
		return reportTemplateList;
	}
}
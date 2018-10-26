package com.hotent.platform.service.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.ReportTemplateDao;
import com.hotent.platform.model.system.ReportTemplate;

/**
 * 对象功能:报表模板Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-04-12 09:59:47
 */
@Service
public class ReportTemplateService extends BaseService<ReportTemplate>
{
	@Resource
	private ReportTemplateDao dao;
	
	public ReportTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<ReportTemplate, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 *  插入报表模板
	 * @param reportTemplate
	 * @param localPath
	 * @param createTime
	 * @throws Exception
	 */
	public void saveReportTemplate(ReportTemplate reportTemplate, String localPath, Date createTime) throws Exception {
		
		if(createTime==null){
			createTime= new Date();
		}
		
		if(reportTemplate.getReportId()==null){
			reportTemplate.setReportId(UniqueIdUtil.genId());
			reportTemplate.setReportLocation(localPath);
			reportTemplate.setCreateTime(createTime);
			reportTemplate.setUpdateTime(createTime);
			add(reportTemplate);
		}else{
			reportTemplate.setReportLocation(localPath);
			reportTemplate.setCreateTime(createTime);
			reportTemplate.setUpdateTime(new Date());
			update(reportTemplate);
		}
	}
	
	public ReportTemplate getReportTemplateByTitle(String reportTitle){
		List<ReportTemplate> reprotTemplateList = dao.getReportTemplateByTitle(reportTitle);
		if(reprotTemplateList.size()==0){
			System.out.println("此报表不存在");
			return null;
		}
		if(reprotTemplateList.size()>1){
			System.out.println("存在同名报表");
			return null;
		}
		ReportTemplate reportTemplate = reprotTemplateList.get(0);
		return reportTemplate;
	}
	
}

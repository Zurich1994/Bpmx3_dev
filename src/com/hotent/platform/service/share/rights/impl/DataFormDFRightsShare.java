package com.hotent.platform.service.share.rights.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.share.rights.DataTemplateVO;
import com.hotent.platform.service.share.rights.FormDFRightShare;

/**
 * @author as xianggang
 * 
 */
@Service
public class DataFormDFRightsShare extends FormDFRightShare{
	
	@Resource
	BpmDataTemplateService bpmDataTemplateService;
	
	@Resource
	BpmFormDefService bpmFormDefService ;
	
	
	BpmDataTemplate bpmDataTemplate;

	@Override
	public String getShareType() {
		return "formDTDF";
	}

	@Override
	public String getShareDesc() {
		return "业务数据模板数据权限";
	}

	@Override
	public DataTemplateVO getDataObject(String id) {
		DataTemplateVO vo = new DataTemplateVO();
		BpmDataTemplate bpmDataTemplate = bpmDataTemplateService.getByFormKey(id);
		if(bpmDataTemplate==null)
			bpmDataTemplate = bpmDataTemplateService.getByFormKey(bpmFormDefService.getById(Long.parseLong(id)).getFormKey());
		vo.setDisplayField(bpmDataTemplate.getDisplayField());
		vo.setExportField(bpmDataTemplate.getExportField());
		vo.setFilterField(bpmDataTemplate.getFilterField());
		vo.setManageField(bpmDataTemplate.getManageField());
		vo.setPrintField(bpmDataTemplate.getPrintField());
		return vo;
	}

	@Override
	public DataTemplateVO getDataTemplateVO(Long ruleId) {
		bpmDataTemplate = bpmDataTemplateService.getByFormKey(ruleId.toString());//
		if(bpmDataTemplate == null){
			bpmDataTemplateService.getByFormKey(bpmFormDefService.getById(ruleId).getFormKey());
		}
		DataTemplateVO vo = new DataTemplateVO();
		vo.setDisplayField(bpmDataTemplate.getDisplayField());
		vo.setExportField(bpmDataTemplate.getExportField());
		vo.setFilterField(bpmDataTemplate.getFilterField());
		vo.setManageField(bpmDataTemplate.getManageField());
		vo.setPrintField(bpmDataTemplate.getPrintField());
		return vo;
	}

	@Override
	public void updateDataTemplateVO(DataTemplateVO vo) {
		bpmDataTemplate.setDisplayField(vo.getDisplayField());
		bpmDataTemplate.setExportField(vo.getExportField());
		bpmDataTemplate.setFilterField(vo.getFilterField());
		bpmDataTemplate.setManageField(vo.getManageField());
		bpmDataTemplate.setPrintField(vo.getPrintField());
		bpmDataTemplateService.update(bpmDataTemplate);
	}
}

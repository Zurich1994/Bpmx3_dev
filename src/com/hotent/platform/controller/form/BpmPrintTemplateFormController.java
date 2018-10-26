/**update 2013-1-1**/
package com.hotent.platform.controller.form;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmPrintTemplate;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.BpmPrintTemplateService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.ParseReult;

/**
 * 对象功能:表单模板 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-01-09 16:35:25
 */
@Controller
@RequestMapping("/platform/form/bpmPrintTemplate/")
public class BpmPrintTemplateFormController extends BaseFormController {
	@Resource
	private BpmPrintTemplateService  bpmPrintTemplateService;
	@Resource
	private BpmFormTableService  bpmFormTableService;
	
	/**
	 * 添加或更新表单模板。
	 * @param request
	 * @param response
	 * @param bpmFormTemplate 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新表单模板")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String data = request.getParameter("data");
		BpmPrintTemplate bpmPrintTemplate = (BpmPrintTemplate) JSONObject.toBean(JSONObject.fromObject(data), BpmPrintTemplate.class);
		String resultMsg=null;
		String html=bpmPrintTemplate.getHtml();
		String template = "";
		Long tableId = bpmPrintTemplate.getTableId();
		BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);
		String tableName = bpmFormTable.getTableName();
		String tableComment = bpmFormTable.getTableDesc();
		template = FormUtil.getPrintFreeMarkerTemplate(html,tableName,tableComment);
		bpmPrintTemplate.setTemplate(template);
		if(bpmPrintTemplate.getId()==null){
			bpmPrintTemplate.setId(UniqueIdUtil.genId());
			int count=bpmPrintTemplateService.getCountByFormKey(bpmPrintTemplate.getFormKey());
			if(count==0){
				bpmPrintTemplate.setIsDefault((short)1);
			}else{
				bpmPrintTemplate.setIsDefault((short)0);
			}
			bpmPrintTemplateService.add(bpmPrintTemplate);
			resultMsg=getText(ContextUtil.getMessages("controller.bpmPrintTemplate"));
		}else{
			bpmPrintTemplateService.update(bpmPrintTemplate);
			resultMsg=getText(ContextUtil.getMessages("controller.bpmPrintTemplate"));
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}

}

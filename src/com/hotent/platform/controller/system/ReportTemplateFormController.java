//package com.hotent.platform.controller.system;
//
//import java.util.Date;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.hotent.core.annotion.Action;
//import com.hotent.core.util.CopyFileUtil;
//import com.hotent.core.util.UniqueIdUtil;
//import com.hotent.core.web.ResultMessage;
//import com.hotent.core.web.controller.BaseFormController;
//import com.hotent.platform.model.system.ReportTemplate;
//import com.hotent.platform.service.system.ReportTemplateService;
//
///**
// * 对象功能:报表模板// 控制器类
// * 开发公司:广州宏天软件有限公司
// * 开发人员:cjj
// * 创建时间:2012-04-12 09:59:47
// */
//@Controller
//@RequestMapping("/platform/system/reportTemplate/")
//public class ReportTemplateFormController extends BaseFormController
//{
//	@Resource
//	private ReportTemplateService reportTemplateService;
//	
//	/**
//	 * 添加或更新报表模板
//	 * @param request
//	 * @param response
//	 * @param reportTemplate 添加或更新的实体
//	 * @param bindResult
//	 * @param viewName
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("save")
//	@Action(description="添加或更新报表模板")
//	public void save(HttpServletRequest request, HttpServletResponse response, ReportTemplate reportTemplate,BindingResult bindResult) throws Exception
//	{
//		
//		ResultMessage resultMessage=validForm("reportTemplate", reportTemplate, bindResult, request);
//		//add your custom validation rule here such as below code:
//		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
//		if(resultMessage.getResult()==ResultMessage.Fail)
//		{
//			writeResultMessage(response.getWriter(),resultMessage);
//			return;
//		}
//		String path = request.getParameter("file");
//		String resultMsg=null;
//		if(reportTemplate.getReportId()==null){
//			reportTemplate.setReportId(UniqueIdUtil.genId());
//			reportTemplate.setCreateTime(new Date());
//			reportTemplate.setUpdateTime(new Date());
////			reportTemplateService.add(reportTemplate);
//			resultMsg="添加报表模板成功";
//		}else{
//			reportTemplate.setUpdateTime(new Date());
//			reportTemplateService.update(reportTemplate);
//			resultMsg="更新报表模板成功";
//		}
////		CopyFileUtil.copyFile(reportTemplate.getReportLocation(),
////				request.getContextPath()+ReportTemplate.targetPath, true);
//		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
//	}
//	
//	/**
//	 * 在实体对象进行封装前，从对应源获取原实体
//	 * @param REPORTID
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//    @ModelAttribute
//    protected ReportTemplate getFormObject(@RequestParam("REPORTID") Long REPORTID,Model model) throws Exception {
//		logger.debug("enter ReportTemplate getFormObject here....");
//		ReportTemplate reportTemplate=null;
//		if(REPORTID!=null){
//			reportTemplate=reportTemplateService.getById(REPORTID);
//		}else{
//			reportTemplate= new ReportTemplate();
//		}
//		return reportTemplate;
//    }
//
//}

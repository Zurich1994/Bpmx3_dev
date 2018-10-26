package com.hotent.platform.controller.form;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormRule;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormRuleService;
import com.hotent.platform.model.system.SysAuditModelType;

/**
 * 对象功能:表单验证规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-01-11 10:53:15
 */
@Controller
@RequestMapping("/platform/form/bpmFormRule/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormRuleController extends BaseController
{
	@Resource
	private BpmFormRuleService bpmFormRuleService;
	
	/**
	 * 取得表单验证规则分页列表
	 * @param request
	 * @param response
	 * @param page    请求页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看表单验证规则分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmFormRule> list=bpmFormRuleService.getAll(new QueryFilter(request,"bpmFormRuleItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmFormRuleList",list);
		
		return mv;
	}
	
	/**
	 * 删除表单验证规则
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除表单验证规则",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除表单验证规则" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=bpmFormRuleService.getById(Long.valueOf(item))/>" +
						"【${entity.name}】"+
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request); 
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");  
			bpmFormRuleService.delByIds(lAryId);
			bpmFormRuleService.generateJS();
			message=new ResultMessage(ResultMessage.Success, "删除表单验证规则成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
    
	/**
	 * 编辑表单验证规则
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑表单验证规则")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmFormRule bpmFormRule=null;
		if(id!=0){
			 bpmFormRule= bpmFormRuleService.getById(id);
		}else{
			bpmFormRule=new BpmFormRule();
		}
		return getAutoView().addObject("bpmFormRule",bpmFormRule).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得表单验证规则明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看表单验证规则明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		BpmFormRule bpmFormRule = bpmFormRuleService.getById(id);		
		return getAutoView().addObject("bpmFormRule", bpmFormRule).addObject("canReturn",canReturn);
	}
	
	/**
	 * 返回所有验证规则
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllRules")
	public List<BpmFormRule> getAllRules(HttpServletRequest request) {
		List<BpmFormRule> validRuleList = bpmFormRuleService.getAll();
		return validRuleList;
	}
	
	@RequestMapping("export")
	@Action(description="导出脚本",execOrder=ActionExecOrder.AFTER,
			detail="<#list StringUtils.split(id,\",\") as item>" +
					"<#assign entity=bpmFormRuleService.getById(Long.valueOf(item))/>" +
					"【${entity.name}】"+
					"</#list>")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
		if(BeanUtils.isNotEmpty(lAryId)){
			Calendar now=Calendar.getInstance();				
			String localTime=now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.DATE);
			String strXml=bpmFormRuleService.exportXml(lAryId);
			response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition","attachment;filename=BpmFormRule"+localTime+".xml");
	        response.getWriter().write(strXml);
	        response.getWriter().flush();
	        response.getWriter().close();			
		}		
	}
	
	/**
	 * 导入表单验证规则的XML。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description = "导入自定义表")
	public void importXml(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {	
		MultipartFile fileLoad = request.getFile("xmlFile");
		bpmFormRuleService.importXml(fileLoad.getInputStream());
		ResultMessage message=null;		
		message=new ResultMessage(ResultMessage.Success,"导入成功!");
		writeResultMessage(response.getWriter(), message);
	}
}

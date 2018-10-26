

package com.hotent.CodeTemplate.controller.CodeTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.CodeTemplate.model.CodeTemplate.CodeTemplate;
import com.hotent.CodeTemplate.service.CodeTemplate.CodeTemplateService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:流程模板 控制器类
 */
@Controller
@RequestMapping("/CodeTemplate/CodeTemplate/codeTemplate/")
public class CodeTemplateController extends BaseController
{
	@Resource
	private CodeTemplateService codeTemplateService;
	
	/**
	 * 添加或更新流程模板。
	 * @param request
	 * @param response
	 * @param codeTemplate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程模板")
	public void save(HttpServletRequest request, HttpServletResponse response,CodeTemplate codeTemplate) throws Exception
	{
		String resultMsg=null;		
		try{
			if(codeTemplate.getId()==null){
				System.out.println("subjectname:"+codeTemplate.getSubjectname());
				codeTemplateService.save(codeTemplate);
				resultMsg=getText("添加","流程模板");
			}else{
			    codeTemplateService.save(codeTemplate);
				resultMsg=getText("更新","流程模板");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得流程模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程模板分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<CodeTemplate> list=codeTemplateService.getAll(new QueryFilter(request,"codeTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("codeTemplateList",list);
		return mv;
	}
	
	/**
	 * 删除流程模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程模板")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			codeTemplateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除流程模板成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑流程模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑流程模板")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		CodeTemplate codeTemplate=codeTemplateService.getById(id);
		
		return getAutoView().addObject("codeTemplate",codeTemplate)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得流程模板明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程模板明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		CodeTemplate codeTemplate=codeTemplateService.getById(id);
		return getAutoView().addObject("codeTemplate", codeTemplate);
	}
	
}
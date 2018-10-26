package com.hotent.platform.controller.bpm;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefRights;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.bpm.BpmDefRightsService;

import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.model.system.SysAuditModelType;

/**
 * 对象功能:流程定义权限  控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2011-12-13 10:29:26
 */
@Controller
@RequestMapping("/platform/bpm/bpmDefRight/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmDefRightController extends BaseController
{
	@Resource
	private BpmDefRightsService bpmDefRightService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private GlobalTypeService globalTypeService;
	
	
	
	/**
	 * 取得流程定义权限列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程定义权限分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		//流程或类型的ID
		//Long id = RequestUtil.getLong(request, "id");
		String id=RequestUtil.getString(request, "id");
		String defKey = RequestUtil.getString(request, "defKey","");
		int type = RequestUtil.getInt(request, "type");
		int isParent = RequestUtil.getInt(request, "isParent",0);
		
		ModelAndView mv=this.getAutoView();
		
		Map<String, String> rightsMap= bpmDefRightService.getRights("".equals(defKey)?id+"":defKey, type);
		//定义
		String [] ids= id.split(",");
		if(type==BpmDefRights.SEARCH_TYPE_DEF){
			List<BpmDefinition> bpmDefinitions=new ArrayList<BpmDefinition>();
			for (String defId:ids) {
				BpmDefinition bpmDefinition=bpmDefinitionService.getById(Long.parseLong(defId));
				bpmDefinitions.add(bpmDefinition);
			}
			mv.addObject("bpmDefinitions", bpmDefinitions);
		
		}else{
			
			List<GlobalType> globalTypes=new ArrayList<GlobalType>();
			for (String gloId:ids) {
				GlobalType globalType=globalTypeService.getById(Long.parseLong(gloId));
				globalTypes.add(globalType);
			}
			mv.addObject("globalTypes", globalTypes);
		
		}
		mv.addObject("rightsMap", rightsMap)
		.addObject("id", id)
		.addObject("defKey", defKey)
		.addObject("type", type)
		.addObject("isParent", isParent);
		return mv;
	}
	
	/**
	 * 删除流程定义权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程定义权限"
//			execOrder=ActionExecOrder.BEFORE,
//			detail="<#list StringUtils.split(rightId,\",\") as item>" +
//						"<#assign entity = bpmDefRightService.getById(Long.valueOf(item))/>" +
//						"<#if item_index==0>" +
//							"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.defId)}】的权限" +
//						"</#if>"+
//						"【${entity.rightType}】" +
//					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		Long[] lAryId =RequestUtil.getLongAryByStr(request, "rightId");
		bpmDefRightService.delByIds(lAryId);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("save")
	@Action(description="编辑流程定义权限",
			detail="<#assign actDefKey = defKey!''>" +
					"<#if actDefKey==''>" +
						"<#assign actDefKey = id/>" +
					"</#if>"+
					"修改了流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(actDefKey)}】的权限设置"
	)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id=RequestUtil.getString(request,"id");
		int type=RequestUtil.getInt(request,"type");
		String defKey = RequestUtil.getString(request,"defKey");
		int isChange = RequestUtil.getInt(request, "isChange",0);
		
		String[] rightType = request.getParameterValues("rightType");
		String[] owner = request.getParameterValues("owner");
		try
		{
			bpmDefRightService.saveRights("".equals(defKey)?id:defKey, type, rightType, owner, isChange);
			writeResultMessage(response.getWriter(),"设置权限成功!",ResultMessage.Success);
		}
		catch (Exception e) {
			String str= MessageUtil.getMessage();
			if(StringUtil.isNotEmpty(str)){
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, "设置权限设失败:" + str);
				response.getWriter().print(resultMessage);
			}
			else{
				String message=ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		
	}
	
	
	
}

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
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.service.bpm.BpmNodeScriptService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.model.system.SysAuditModelType;

/**
 * 对象功能:节点运行脚本 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-30 14:31:20
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeScript/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmNodeScriptController extends BaseController
{
	@Resource
	private BpmNodeScriptService bpmNodeScriptService;

	/**
	 * 添加或更新节点运行脚本。
	 * @param request
	 * @param response
	 * @param bpmNodeScript 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新节点运行脚本",
			detail="保存流程定义 【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}】" +
					"的节点 【${SysAuditLinkService.getNodeName(actDefId,nodeId)}】 的脚本设置（事件设置）"
	)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String nodeId=RequestUtil.getString(request, "nodeId");
		String actDefId=RequestUtil.getString(request, "actDefId");
		
		String[] aryScript=request.getParameterValues("script");
		String[] aryScriptType=request.getParameterValues("scriptType");
		List<BpmNodeScript> list=new ArrayList<BpmNodeScript>();
		for(int i=0;i<aryScriptType.length;i++){
			String script=aryScript[i];
			Integer type=Integer.parseInt(aryScriptType[i]);
			if(StringUtil.isEmpty(script)) continue;
			
			BpmNodeScript bpmNodeScript=new BpmNodeScript();
			bpmNodeScript.setScript(script);
			bpmNodeScript.setScriptType(type);
			list.add(bpmNodeScript);
			
		}
		try{
			bpmNodeScriptService.saveScriptDef(actDefId, nodeId, list);
			writeResultMessage(response.getWriter(),"保存节点脚本成功",ResultMessage.Success);
		}
		catch ( Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"保存节点脚本失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		
		
	}

	@RequestMapping("edit")
	@Action(description="编辑节点运行脚本")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long defId=RequestUtil.getLong(request, "defId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		String actDefId=RequestUtil.getString(request, "actDefId");
		String type=RequestUtil.getString(request, "type");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		ModelAndView mv=getAutoView();
		String vers= request.getHeader("USER-AGENT");
		if(vers.indexOf("MSIE 6")!=-1){
			mv= new ModelAndView("/platform/bpm/bpmNodeScriptEdit_ie6.jsp");
		}
		Map<String,BpmNodeScript> map= bpmNodeScriptService.getMapByNodeScriptId(nodeId, actDefId);
		return mv.addObject("map",map)
				.addObject("type", type)
				.addObject("nodeId", nodeId)
				.addObject("actDefId", actDefId)
				.addObject("defId", defId)
				.addObject("parentActDefId", parentActDefId);
				
	}
	/**设置脚本跳转大页面*/
	@RequestMapping("edit1")
	@Action(description="编辑节点运行脚本")
	public ModelAndView edit1(HttpServletRequest request) throws Exception
	{
		Long defId=RequestUtil.getLong(request, "defId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		String actDefId=RequestUtil.getString(request, "actDefId");
		String type=RequestUtil.getString(request, "type");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		ModelAndView mv=getAutoView();
		String vers= request.getHeader("USER-AGENT");
		if(vers.indexOf("MSIE 6")!=-1){
			mv= new ModelAndView("/platform/bpm/bpmNodeScriptEdit_ie6.jsp");
		}
		Map<String,BpmNodeScript> map= bpmNodeScriptService.getMapByNodeScriptId(nodeId, actDefId);
		return mv.addObject("map",map)
				.addObject("type", type)
				.addObject("nodeId", nodeId)
				.addObject("actDefId", actDefId)
				.addObject("defId", defId)
				.addObject("parentActDefId", parentActDefId);
				
	}
	
}

package com.hotent.platform.controller.bpm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.service.bpm.BpmNodeRuleService;
import com.hotent.platform.service.bpm.BpmNodeSetService;

/**
 * 对象功能:流程节点规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 15:41:53
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeRule/")
public class BpmNodeRuleFormController extends BaseFormController
{
	@Resource
	private BpmNodeRuleService bpmNodeRuleService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	/**
	 * 添加或更新流程节点规则。
	 * @param request
	 * @param response
	 * @param bpmNodeRule 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程节点规则")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmNodeRule bpmNodeRule,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("bpmNodeRule", bpmNodeRule, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		String tmp=bpmNodeRule.getTargetNode();
		String[] aryTmp=tmp.split(",");
		bpmNodeRule.setTargetNode(aryTmp[0]);
		bpmNodeRule.setTargetNodeName(aryTmp[1]);
		if(bpmNodeRule.getRuleId()==0L){
			bpmNodeRule.setRuleId(UniqueIdUtil.genId());
			bpmNodeRule.setPriority(System.currentTimeMillis());
			bpmNodeRuleService.add(bpmNodeRule);
			resultMsg="添加流程节点规则成功";
		}else{
			bpmNodeRuleService.update(bpmNodeRule);
			resultMsg="更新流程节点规则成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param ruleId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmNodeRule getFormObject(@RequestParam("ruleId") Long ruleId,Model model) throws Exception {
		logger.debug("enter BpmNodeRule getFormObject here....");
		BpmNodeRule bpmNodeRule=null;
		if(ruleId!=0L){
			bpmNodeRule=bpmNodeRuleService.getById(ruleId);
		}else{
			bpmNodeRule= new BpmNodeRule();
			
		}
		return bpmNodeRule;
    }

}

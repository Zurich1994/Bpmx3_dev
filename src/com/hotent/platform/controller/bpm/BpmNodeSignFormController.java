package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.List;

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
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.bpm.BpmNodePrivilege;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.service.bpm.BpmNodeSignService;

/**
 * 对象功能:会签任务投票规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 08:41:55
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeSign/")
public class BpmNodeSignFormController extends BaseFormController
{
	@Resource
	private BpmNodeSignService bpmNodeSignService;

	
	/**
	 * 添加或更新会签任务投票规则。
	 * @param request
	 * @param response
	 * @param bpmNodeSign 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新会签任务投票规则")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmNodeSign bpmNodeSign,BindingResult bindResult) throws Exception{
		ResultMessage resultMessage=validForm("bpmNodeSign", bpmNodeSign, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		
		//处理特权列表
		List<BpmNodePrivilege> privileges = new ArrayList<BpmNodePrivilege>();
		String[] usertypes = request.getParameterValues("userType");
		String[] cmpIds = request.getParameterValues("cmpIds");
		String[] cmpNames = request.getParameterValues("cmpNames");
		for (int i = 0; i < usertypes.length; i++) {
			String usertype = usertypes[i];
			String cmpId = cmpIds[i];
			String cmpName = cmpNames[i];
			if (StringUtil.isEmpty(usertype) || StringUtil.isEmpty(cmpName)) {
				continue;
			}

			BpmNodePrivilege vo = new BpmNodePrivilege();
			vo.setPrivilegemode(Long.valueOf(i));
			vo.setUsertype(Long.valueOf(usertype));
			vo.setCmpids(cmpId);
			vo.setCmpnames(cmpName);
			privileges.add(vo);
		}
		
		bpmNodeSignService.addOrUpdateSignAndPrivilege(bpmNodeSign, privileges);
		
		String resultMsg="保存会签任务投票规则成功";
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param signId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmNodeSign getFormObject(@RequestParam("signId") Long signId,Model model) throws Exception {
		logger.debug("enter BpmNodeSign getFormObject here....");
		BpmNodeSign bpmNodeSign=null;
		if(signId!=0L){
			bpmNodeSign=bpmNodeSignService.getById(signId);
		}else{
			bpmNodeSign= new BpmNodeSign();
		}
		return bpmNodeSign;
    }

}

package com.hotent.platform.controller.system;

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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.system.SysOrgService;

/**
 * 对象功能:组织架构 控制器类 开发公司:广州宏天软件有限公司 开发人员:pkq 创建时间:2011-12-02 10:41:53
 */
@Controller
@RequestMapping("/platform/system/sysOrg/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysOrgFormController extends BaseFormController {
	@Resource
	private SysOrgService sysOrgService;


	/**
	 * 添加或更新组织架构。
	 * 
	 * @param request
	 * @param response
	 * @param sysOrg
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新组织架构",detail="<#if isAdd>添加<#else>更新</#if>组织【${SysAuditLinkService.getSysOrgLink(Long.valueOf(orgId))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, SysOrg sysOrg, BindingResult bindResult) throws Exception {
		ResultMessage resultMessage = validForm("sysOrg", sysOrg, bindResult, request);
		if (resultMessage.getResult() == ResultMessage.Fail) {
			writeResultMessage(response.getWriter(), resultMessage);
			return;
		}
		
		boolean isAdd=true;
		if (sysOrg.getOrgId() == null) {
			Long orgId=UniqueIdUtil.genId();
			sysOrg.setOrgId(orgId);
			sysOrg.setCreatorId(ContextUtil.getCurrentUserId());
			Long supOrgId=RequestUtil.getLong(request, "orgSupId");
			SysOrg supOrg = sysOrgService.getById(supOrgId);
			// 若以维度为父节点新建，则设置其Path为维度ID+该组织ID
			if (supOrg == null) {
				sysOrg.setPath(supOrgId + "." + orgId +".");
				sysOrg.setOrgPathname("/" +sysOrg.getOrgName());
			} else {
				sysOrg.setPath(supOrg.getPath() + sysOrg.getOrgId() +".");
				sysOrg.setOrgPathname(supOrg.getOrgPathname() +"/" + sysOrg.getOrgName());
			}
			
			if(sysOrgService.getByCode(sysOrg.getCode())!=null){//code已被使用
				writeResultMessage(response.getWriter(), "code:"+sysOrg.getCode()+",已被使用", ResultMessage.Fail);
				return;
			}
			
			sysOrgService.addOrg(sysOrg);
			
			writeResultMessage(response.getWriter(), "{\"orgId\":\""+ orgId +"\",\"action\":\"add\"}", ResultMessage.Success);
		} else {
			isAdd=false;
			sysOrg.setUpdateId(ContextUtil.getCurrentUserId());
			
			String pathName=getOrgPathName(sysOrg.getPath(),sysOrg.getOrgName());
			sysOrg.setOrgPathname(pathName);
			sysOrgService.updOrg(sysOrg);
			this.upSysOrgBySupId(sysOrg.getOrgId(),pathName);
			writeResultMessage(response.getWriter(), "{\"orgId\":\""+ sysOrg.getOrgId() +"\",\"action\":\"upd\"}", ResultMessage.Success);
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		SysAuditThreadLocalHolder.putParamerter("orgId",sysOrg.getOrgId().toString());
	}
	
	/**
	 * 更新其下子组织的路径
	 * 
	 */
	private void upSysOrgBySupId(long orgSupId,String supPathName) throws Exception{
		//根据id查找子组织
		List<SysOrg> sysOrgs=sysOrgService.getOrgByOrgSupId(orgSupId);
		if (sysOrgs!=null && sysOrgs.size()!=0) {
			SysOrg sysOrg=null;
			for (int i = 0; i < sysOrgs.size(); i++) {
				sysOrg=sysOrgs.get(i);
				String pathName= supPathName+"/"+sysOrg.getOrgName();
				sysOrg.setOrgPathname(pathName);
				sysOrgService.updOrg(sysOrg);
				//递归遍历是否存在子组织，存在就继续修改，不存在就结束
				this.upSysOrgBySupId(sysOrg.getOrgId(),pathName);
			}
		}
	}
	
	private String getOrgPathName(String orgPath,String orgName){
		orgPath=StringUtil.trimSufffix(orgPath, ".");
		String[] aryPath=orgPath.split("\\.");
		String pathName="";
		for(int i=1;i<aryPath.length-1;i++){
			SysOrg sysOrg=sysOrgService.getById(new Long(aryPath[i]));
			pathName+="/" + sysOrg.getOrgName();
		}
		pathName+="/" + orgName;
		return pathName;
	}
	
	
	

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * 
	 * @param orgId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute
	protected SysOrg getFormObject(@RequestParam("orgId") Long orgId, Model model) throws Exception {
		logger.debug("enter SysOrg getFormObject here....");
		SysOrg sysOrg = null;
		if (orgId != null) {
			sysOrg = sysOrgService.getById(orgId);
		} else {
			sysOrg = new SysOrg();
		}
		return sysOrg;
	}

}

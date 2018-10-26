//package com.hotent.platform.controller.system;
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
//import com.hotent.core.annotion.ActionExecOrder;
//import com.hotent.core.bpm.util.BpmConst;
//import com.hotent.core.log.SysAuditThreadLocalHolder;
//import com.hotent.core.util.UniqueIdUtil;
//import com.hotent.core.web.ResultMessage;
//import com.hotent.core.web.controller.BaseFormController;
//import com.hotent.platform.model.system.SysAuditModelType;
//import com.hotent.platform.model.system.SysDataSource;
//import com.hotent.platform.service.system.SysDataSourceService;
//
///**
// * 对象功能:系统数据源管理 控制器类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2011-11-16 16:34:16
// */
//@Controller
//@RequestMapping("/platform/system/sysDataSource/")
//@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
//public class SysDataSourceFormController extends BaseFormController
//{
//	@Resource
//	private SysDataSourceService service;
//
//	/**
//	 * 添加或更新系统数据源
//	 * 
//	 * @param request
//	 * @param response
//	 * @param po
//	 * @param bindResult
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("save")
//	@Action(description = "添加或更新系统数据源",
//			execOrder=ActionExecOrder.AFTER,
//			detail="<#if isAdd>添加<#else>更新</#if>系统数据源" +
//					"【${SysAuditLinkService.getSysDataSourceLink(Long.valueOf(id))}】"
//			)
//	public void save(HttpServletRequest request, HttpServletResponse response, SysDataSource po,
//			BindingResult bindResult) throws Exception
//	{		
//		if(po.getAlias().toUpperCase().equalsIgnoreCase(BpmConst.LOCAL_DATASOURCE)){
//			String msg = "LOCAL是系统关键字<br/>不能使用LOCAl作为别名";
//			writeResultMessage(response.getWriter(), msg, ResultMessage.Fail);
//			return ;
//		}
//		ResultMessage resultMessage = validForm("sysDataSource", po, bindResult, request);
//		if (resultMessage.getResult() == ResultMessage.Fail)
//		{
//			writeResultMessage(response.getWriter(), resultMessage);
//			return;
//		}
//		SysAuditThreadLocalHolder.putParamerter("isAdd", po.getId()==null);
//		po.setAlias(po.getAlias().toLowerCase());
//		if (po.getId() == null){
//			if (service.isAliasExisted(po.getAlias())){
//				String msg = getText("errors.sysDataSource.aliasExisted", po.getAlias());
//				writeResultMessage(response.getWriter(), msg, ResultMessage.Fail);
//			} else{
//				po.setId(UniqueIdUtil.genId());
//				service.add(po);
//				String msg = "系统数据源管理添加成功";
//				writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
//			}
//		} else{
//			if (service.isAliasExistedByUpdate(po)){
//				String msg = getText("errors.sysDataSource.aliasExisted", po.getAlias());
//				writeResultMessage(response.getWriter(), msg, ResultMessage.Fail);
//			} else{
//				service.update(po);
//				//更新时删除数据源。
////				JdbcHelper.getInstance().removeAlias(po.getAlias());
//				String msg = "系统数据源管理更新成功";
//				writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
//			}
//		}
//		// 添加或更新系统数据源信息 
//		SysAuditThreadLocalHolder.putParamerter("sysDataSource", po);
//		SysAuditThreadLocalHolder.putParamerter("id", po.getId().toString());
//
//	}
//
//	/**
//	 * 在实体对象进行封装前，从对应源获取原实体
//	 * @param id
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@ModelAttribute
//	protected SysDataSource getFormObject(@RequestParam("id") Long id, Model model) throws Exception
//	{
//		SysDataSource sysDataSource = null;
//		if (id != null)
//		{
//			sysDataSource = service.getById(id);
//		} else
//		{
//			sysDataSource = new SysDataSource();
//		}
//		return sysDataSource;
//	}
//}

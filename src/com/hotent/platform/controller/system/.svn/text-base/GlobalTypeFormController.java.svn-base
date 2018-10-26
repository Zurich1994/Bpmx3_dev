package com.hotent.platform.controller.system;

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
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.IdentityService;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:系统分类表单控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 09:28:34
 */
@Controller
@RequestMapping("/platform/system/globalType/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class GlobalTypeFormController extends BaseFormController
{
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private IdentityService identityService;
	
	/**
	 * 添加或更新系统分类。
	 * @param request
	 * @param response
	 * @param globalType 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统分类",
			detail="<#if isAdd>添加<#else>更新</#if>系统分类" +
					"【${SysAuditLinkService.getGlobalTypeLink(Long.valueOf(typeId))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, GlobalType globalType,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("globalType", globalType, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		boolean isadd=globalType.getTypeId()==0;
		//父节点
		long parentId=RequestUtil.getLong(request, "parentId",0);
		//是否根节点
		int isRoot=RequestUtil.getInt(request, "isRoot");
		
		int isPrivate=RequestUtil.getInt(request, "isPrivate",0);
		
		Long userId=ContextUtil.getCurrentUserId();
		
		String nodeKey=globalType.getNodeKey();
		String resultMsg=null;
		if(globalType.getTypeId()==0){
			if(parentId!=0){
				GlobalType parentGlobal=globalTypeService.getById(parentId);
				if(parentGlobal!=null){
					parentGlobal.setIsLeaf(1);
					globalTypeService.update(parentGlobal);
				}
			}
			GlobalType tmpGlobalType=globalTypeService.getInitGlobalType(isRoot,parentId);
			String catKey=tmpGlobalType.getCatKey();
			if(StringUtil.isNotEmpty(nodeKey)){
				boolean isExist=globalTypeService.isNodeKeyExists(catKey, nodeKey);
				if(isExist){
					resultMsg="节点KEY已存在!";
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
					return;
				}
			}
			//分类key不为数据字典的情况
			if(!catKey.equals(GlobalType.CAT_DIC)){
				globalType.setType(tmpGlobalType.getType());
			}
			//设置用户ID
			if(isPrivate==1){
				globalType.setUserId(userId);
			}
			globalType.setCatKey(catKey);
			globalType.setNodePath(tmpGlobalType.getNodePath());
			globalType.setTypeId(tmpGlobalType.getTypeId());
			globalType.setDepth(1);
			globalType.setSn(0L);
			globalType.setIsLeaf(0);
			if(globalType.getNodeCodeType().equals(GlobalType.NODE_CODE_TYPE_AUTO_Y)){
				globalType.setNodeCode(identityService.nextId(globalType.getNodeCode()));
			}
			globalTypeService.add(globalType);
			resultMsg="添加系统分类成功";
		}else{
			Long typeId=globalType.getTypeId();
			String catKey=globalType.getCatKey();
			//判断是否存在。
			boolean isExist= globalTypeService.isNodeKeyExistsForUpdate(typeId, catKey, nodeKey);
			if(isExist){
				resultMsg="节点KEY已存在!";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
				return;
			}
			globalTypeService.update(globalType);
			resultMsg="更新系统分类成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	
		try {
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("typeId", globalType.getTypeId().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param typeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected GlobalType getFormObject(@RequestParam("typeId") Long typeId,Model model) throws Exception {
		GlobalType globalType=null;
		
		if(typeId!=0){
			globalType=globalTypeService.getById(typeId);
		}else{
			globalType= new GlobalType();
		}
		return globalType;
    }

}

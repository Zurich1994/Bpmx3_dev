package com.hotent.platform.controller.system;

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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.UserPositionService;

/**
 * 对象功能:系统岗位表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-30 09:49:45
 */
@Controller
@RequestMapping("/platform/system/position/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class PositionFormController extends BaseFormController
{
	@Resource
	private PositionService positionService;
	@Resource
	UserPositionService userPositionService;
	
	/**
	 * 添加或更新系统岗位表。
	 * @param request
	 * @param response
	 * @param position 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping("save")
	@Action(description="添加或更新系统岗位表",
			detail="<#if isAdd>添加<#else>更新</#if>系统岗位表" +
			"【${SysAuditLinkService.getPositionLink(Long.valueOf(posId))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, Position position,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("position", position, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		List<UserPosition> upList=getUserPositions(request);
		boolean isadd=true;
		if(position.getPosId()==null){
			long parentId=RequestUtil.getLong(request, "parentId",0);
//			Position parent=positionService.getParentPositionByParentId(parentId);
			
			position.setPosId(UniqueIdUtil.genId());
//			position.setDepth(parent.getDepth()+1);
//			position.setParentId(parent.getPosId());
//			position.setNodePath(parent.getNodePath()+position.getPosId() + ".");
			positionService.add(position,upList);
			
			resultMsg="添加系统岗位表成功";
		}else{
			positionService.update(position);
			resultMsg="更新系统岗位表成功";
			isadd=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
	//	SysAuditThreadLocalHolder.putParamerter("posId", position.getParentId().toString());
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param posId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected Position getFormObject(@RequestParam("posId") Long posId,Model model) throws Exception {
		logger.debug("enter Position getFormObject here....");
		Position position=null;
		if(posId!=null){
			position=positionService.getById(posId);
		}else{
			position= new Position();
		}
		return position;
    }
    
    /**
     * 判定用户是否选择了主岗位。
     * @param userId
     * @param aryPrimary
     * @return
     */
    private boolean getIsPrimary(Long userId,Long[] aryPrimary){
    	if(BeanUtils.isEmpty(aryPrimary)) return false;
    	for(int i=0;i<aryPrimary.length;i++){
    		if(userId.equals(aryPrimary[i])){
    			return true;
    		}
    	}
    	return false;
    }
    
    protected List<UserPosition> getUserPositions(HttpServletRequest request) throws Exception{
    	
    	List<UserPosition> list=new ArrayList<UserPosition>();
    	Long[] aryUserId=RequestUtil.getLongAry(request, "userId") ;
    	
	 	Long[] aryPrimary=RequestUtil.getLongAry(request,"chkPrimary");
	 	if(BeanUtils.isEmpty(aryPrimary)) return list;
	 		
	 	
 		for(int i=0;i<aryUserId.length;i++){
			Long userId=aryUserId[i];
			boolean isPrimary=getIsPrimary(userId,aryPrimary);
 			UserPosition userPosition=new UserPosition();
 			Short primary=isPrimary?UserPosition.PRIMARY_YES:UserPosition.PRIMARY_NO;
 		    userPosition.setIsPrimary(primary);
 		    userPosition.setUserId(userId);
 			list.add(userPosition);
 		}
	 
	 	return list;
    };

}

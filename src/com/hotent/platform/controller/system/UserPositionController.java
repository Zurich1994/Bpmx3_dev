package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.UserPositionService;
/**
 *<pre>
 * 对象功能:SYS_USER_POS 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/userPosition/")
public class UserPositionController extends BaseController
{
	@Resource
	private UserPositionService userPositionService;
	
	
	/**
	 * 添加或更新SYS_USER_POS。
	 * @param request
	 * @param response
	 * @param userPosition 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新SYS_USER_POS")
	public void save(HttpServletRequest request, HttpServletResponse response,UserPosition userPosition) throws Exception
	{
		String resultMsg=null;		
		try{
			if(userPosition.getUserPosId()==null||userPosition.getUserPosId()==0){
				userPosition.setUserPosId(UniqueIdUtil.genId());
				userPositionService.add(userPosition);
				resultMsg=getText("添加","SYS_USER_POS");
			}else{
			    userPositionService.update(userPosition);
				resultMsg=getText("更新","SYS_USER_POS");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得SYS_USER_POS分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看SYS_USER_POS分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<UserPosition> list=userPositionService.getAll(new QueryFilter(request,"userPositionItem"));
		ModelAndView mv=this.getAutoView().addObject("userPositionList",list);
		
		return mv;
	}
	
	/**
	 * 删除SYS_USER_POS
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除SYS_USER_POS")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "userposid");
			userPositionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除SYS_USER_POS成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑SYS_USER_POS
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑SYS_USER_POS")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long userposid=RequestUtil.getLong(request,"userposid",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		UserPosition userPosition=userPositionService.getById(userposid);
		
		return getAutoView().addObject("userPosition",userPosition)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得SYS_USER_POS明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看SYS_USER_POS明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long userposid=RequestUtil.getLong(request,"userposid");
		UserPosition userPosition = userPositionService.getById(userposid);	
		return getAutoView().addObject("userPosition", userPosition);
	}
	
	
	/**
	 * 岗位加入人员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addPosUser")
	@Action(description="向岗位添加人员",execOrder=ActionExecOrder.AFTER,
			detail="${SysAuditLinkService.getOrgUserName(Long.valueOf(orgId),userIds)}" +
					"<#if isSuccess>成功<#else>失败</#if>")
	public void addPosUser(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long[] userIds =RequestUtil.getLongAryByStr(request, "userIds");
		Long posId=RequestUtil.getLong(request, "posId");
		ResultMessage resultMessage=null;
		boolean issuccess=true;
		try {
			userPositionService.addPosUser(userIds, posId);
			
			//SecurityUtil.removeAll();
			
			resultMessage=new ResultMessage(ResultMessage.Success,"在岗位添加用户成功!");
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"在岗位添加用户中失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
			issuccess=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", issuccess);
		writeResultMessage(response.getWriter(), resultMessage);
	}
	
	
	@RequestMapping("getUserListByPosId")
	@ResponseBody
	@Action(description="根据岗位ID取得用户List",detail="根据岗位ID取得用户List")
	public List<UserPosition> getUserListByPosId(HttpServletRequest request){
		Long posId=RequestUtil.getLong(request, "posId");
		return userPositionService.getByPosId(posId);
	}
	
	
}

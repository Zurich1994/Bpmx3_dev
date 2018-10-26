package com.hotent.platform.controller.bpm;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmSubtableRights;
import com.hotent.platform.service.bpm.BpmSubtableRightsService;
/**
 * 对象功能:子表权限 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wwz
 * 创建时间:2013-01-16 10:13:31
 */
@Controller
@RequestMapping("/platform/bpm/BpmSubtableRights/")
public class BpmSubtableRightsController extends BaseController
{
	@Resource
	private BpmSubtableRightsService bpmSubtableRightsService;
	
	/**
	 * 添加或更新子表权限。
	 * @param request
	 * @param response
	 * @param BpmSubtableRights 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新子表权限")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmSubtableRights bpmSubtableRights) throws Exception
	{
		String resultMsg=null;		
//		BpmSubtableRights bpmSubtableRights1=getFormObject(request);
		try{
			String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
			BpmSubtableRights bpmSubtableRights2 = null;
			if(StringUtil.isEmpty(parentActDefId)){
				bpmSubtableRights2 = bpmSubtableRightsService.getByDefIdAndNodeId(bpmSubtableRights.getActdefid(),  
								bpmSubtableRights.getNodeid(), bpmSubtableRights.getTableid());
			}else{
				bpmSubtableRights2 = bpmSubtableRightsService.getByDefIdAndNodeId(bpmSubtableRights.getActdefid(),  
						bpmSubtableRights.getNodeid(), bpmSubtableRights.getTableid(), parentActDefId);
			}
			
			if(bpmSubtableRights2==null){
				bpmSubtableRights.setId(UniqueIdUtil.genId());
				bpmSubtableRightsService.add(bpmSubtableRights);
				resultMsg="添加子表权限成功";
			}else{
				bpmSubtableRights.setId(bpmSubtableRights2.getId());
				bpmSubtableRightsService.update(bpmSubtableRights);
				resultMsg="更新子表权限成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmSubtableRights 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
//    protected BpmSubtableRights getFormObject(HttpServletRequest request, BpmSubtableRights bpmSubtableRights) throws Exception {
//    
//    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
//    	
//		
//		
//		return BpmSubtableRights;
//    }
	
	/**
	 * 取得子表权限分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看子表权限分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmSubtableRights> list=bpmSubtableRightsService.getAll(new QueryFilter(request,"BpmSubtableRightsItem"));
		ModelAndView mv=this.getAutoView().addObject("BpmSubtableRightsList",list);
		
		return mv;
	}
	
	/**
	 * 删除子表权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除子表权限")
	public void del(HttpServletRequest request, HttpServletResponse response, BpmSubtableRights bpmSubtableRights) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			
			bpmSubtableRightsService.delById(bpmSubtableRights.getId());
			
			message=new ResultMessage(ResultMessage.Success, "删除子表权限成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
//		addMessage(message, request);
//		response.sendRedirect(preUrl);
//		writeResultMessage(response.getWriter(),message,ResultMessage.Success);
		response.getWriter().append(message.toString());
	}
	
	/**
	 * 	编辑子表权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑子表权限")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmSubtableRights BpmSubtableRights=bpmSubtableRightsService.getById(id);
		
		return getAutoView().addObject("BpmSubtableRights",BpmSubtableRights).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得子表权限明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看子表权限明细")
	public void get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String actdefid = RequestUtil.getString(request,"actdefid");
		String nodeid = RequestUtil.getString(request,"nodeid");
		long tableid=RequestUtil.getLong(request,"tableid");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		BpmSubtableRights bpmSubtableRights = null;
		if(StringUtil.isEmpty(parentActDefId)){
			bpmSubtableRights = bpmSubtableRightsService.getByDefIdAndNodeId(actdefid, nodeid, tableid);
		}else{
			bpmSubtableRights = bpmSubtableRightsService.getByDefIdAndNodeId(actdefid, nodeid, tableid, parentActDefId);
		}
		StringBuffer sb = new StringBuffer("{success:true");
		if(bpmSubtableRights!=null){
			sb.append(",\"id\":\"").append(bpmSubtableRights.getId()).append("\",")
			.append("\"permissiontype\":").append(bpmSubtableRights.getPermissiontype()).append(",")
			.append("\"permissionseting\":\"")
			.append(bpmSubtableRights.getPermissionseting()!=null?
					bpmSubtableRights.getPermissionseting().replaceAll("\n", "<br>").replaceAll("[\"]", "<032>"):"")
			.append("\"");
		}
		sb.append("}");
		response.getWriter().print(sb.toString());
	}
}

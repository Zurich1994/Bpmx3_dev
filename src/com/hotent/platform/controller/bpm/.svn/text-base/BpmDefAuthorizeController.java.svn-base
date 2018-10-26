package com.hotent.platform.controller.bpm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefAuthorize;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.system.GlobalTypeService;




/**
 * <pre>
 * 对象功能:流程分管授权  控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 10:39:26
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmDefAuthorize/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmDefAuthorizeController extends BaseController
{
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;	
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;	
	
	@Resource
	private GlobalTypeService globalTypeService;
	
	
	/**
	 * 取得流程定义权限列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程分管权限分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefAuthorizeItem");		
		List<BpmDefAuthorize> list = bpmDefAuthorizeService.getAuthorizeListByFilter(filter);
		ModelAndView mv = getAutoView().addObject("bpmDefAuthorizeList", list);
		return mv;
	}
	
	
	/**
	 * 新增修改授权信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="新增修改授权信息")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id=RequestUtil.getLong(request,"id");
		BpmDefAuthorize bpmDefAuthorize = null;
		if(id!=0){
			bpmDefAuthorize = bpmDefAuthorizeService.getAuthorizeById(id);
		}else{
			bpmDefAuthorize =new BpmDefAuthorize();
		}
		return getAutoView().addObject("bpmDefAuthorize",bpmDefAuthorize);
	}
	
	
	/**
	 * 保存新增或修改授权信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="保存新增或修改授权信息")
	public void save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id=RequestUtil.getLong(request,"id",0);
		String authorizeDesc = RequestUtil.getString(request, "authorizeDesc","");
		String authorizeTypes =  RequestUtil.getStringValues(request, "authorizeTypes");
		String ownerNameJson = RequestUtil.getString(request, "ownerNameJson","");
		String defNameJson = RequestUtil.getString(request, "defNameJson","");
		BpmDefAuthorize bpmDefAuthorize = new BpmDefAuthorize();
		//用ID判断是修改还是新增
		if(id>0){
			bpmDefAuthorize.setId(id);
		}else{
			bpmDefAuthorize.setId(0L);
		}
		bpmDefAuthorize.setAuthorizeDesc(authorizeDesc);
		//确定授权类型
/*		if(BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT.equals(authorizeType)){
			authorizeType = BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT;
		}else{
			authorizeType = BPMDEFAUTHORIZE_RIGHT_TYPE.START;
		}*/
		bpmDefAuthorize.setAuthorizeTypes(authorizeTypes);
		bpmDefAuthorize.setOwnerNameJson(ownerNameJson);
		bpmDefAuthorize.setDefNameJson(defNameJson);
		Long myId = bpmDefAuthorizeService.saveOrUpdateAuthorize(bpmDefAuthorize);
		if(myId>0){
			writeResultMessage(response.getWriter(),"保存授权信息成功！",ResultMessage.Success);
		}else{
			writeResultMessage(response.getWriter(),"保存授权信息失败！",ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 删除流程分管授权信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程分管授权信息")
	public void del(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmDefAuthorizeService.deleteAuthorizeByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除流程分管授权信息成功！");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除流程分管授权信息失败！" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	
	/**
	 * 获得流程分管授权详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="获得流程分管授权详情")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id=RequestUtil.getLong(request,"id");
		BpmDefAuthorize bpmDefAuthorize = null;
		if(id!=0){
			bpmDefAuthorize = bpmDefAuthorizeService.getAuthorizeById(id);
		}else{
			bpmDefAuthorize =new BpmDefAuthorize();
		}
		return getAutoView().addObject("bpmDefAuthorize",bpmDefAuthorize);
	}
	
	
	/**
	 * 打开用户选择窗口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userDialog")
	@Action(description="查看流程分管权限分页列表")
	public ModelAndView userDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = getAutoView();
		return mv;
	}
	
	/**
	 * 取得流程定义明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("actDetail")
	@Action(description = "查看流程定义明细")
	public ModelAndView actDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "defId");
		BpmDefinition po = null;
		if(id<=0){
			String defKey = RequestUtil.getString(request, "defKey","");
			po = bpmDefinitionService.getMainByDefKey(defKey);
		}else{
			po = bpmDefinitionService.getById(id);
		}
		ModelAndView modelAndView = getAutoView();
		
		if (po.getTypeId() != null) {
			GlobalType globalType = globalTypeService.getById(po.getTypeId());
			modelAndView.addObject("globalType", globalType);
		}
		return modelAndView.addObject("bpmDefinition", po);
	}
}

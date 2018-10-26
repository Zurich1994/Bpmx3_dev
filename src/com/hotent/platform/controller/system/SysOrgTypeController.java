package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysOrgTypeService;
/**
 * 对象功能:组织结构类型 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-11-27 09:55:21
 */
@Controller
@RequestMapping("/platform/system/sysOrgType/")
public class SysOrgTypeController extends BaseController
{
	@Resource
	private SysOrgTypeService sysOrgTypeService;
	
	/**
	 * 添加或更新组织结构类型。
	 * @param request
	 * @param response
	 * @param sysOrgType 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新组织结构类型")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		SysOrgType sysOrgType=getFormObject(request);
		try{
			if(sysOrgType.getId()==null||sysOrgType.getId()==0){
				sysOrgType.setId(UniqueIdUtil.genId());
				sysOrgTypeService.add(sysOrgType);
				resultMsg="组织结构类型保存成功";
			}else{
			    sysOrgTypeService.update(sysOrgType);
				resultMsg="组织结构类型更新成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"添加或更新组织结构类型失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 取得 SysOrgType 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected SysOrgType getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		SysOrgType sysOrgType = (SysOrgType)JSONObject.toBean(obj, SysOrgType.class);
		
		return sysOrgType;
    }
	
	/**
	 * 取得组织结构类型分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看组织结构类型分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
			Long demId=RequestUtil.getLong(request,"demId");		
			List<SysOrgType> list=sysOrgTypeService.getByDemId(demId);
			ModelAndView mv=this.getAutoView().addObject("sysOrgTypeList",list)
														.addObject("demId",demId);

		return mv;
	}
	
	/**
	 * 删除组织结构类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除组织结构类型")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysOrgTypeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除组织结构类型成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑组织结构类型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑组织结构类型")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Long demId=RequestUtil.getLong(request,"demId");
	
		String returnUrl=RequestUtil.getPrePage(request);
		SysOrgType sysOrgType=sysOrgTypeService.getById(id);
		ModelAndView mv= getAutoView().addObject("sysOrgType",sysOrgType).addObject("returnUrl", returnUrl);							
		if(id==0){
			Integer temp=sysOrgTypeService.getMaxLevel(demId);
			if(temp==null)
				temp=0;
			Integer currentLevel=temp+1;
			mv.addObject("levels",currentLevel).addObject("demId", demId);;
		}
		return mv;
	}

	/**
	 * 取得组织结构类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看组织结构类型明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		SysOrgType sysOrgType = sysOrgTypeService.getById(id);	
		return getAutoView().addObject("sysOrgType", sysOrgType);
	}
	/**
	 * 取得组织结构类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("multSave")
	@Action(description="查看组织结构类型明细")
	public void multSave(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;
		String[]  ids=RequestUtil.getStringAryByStr(request, "ids");
		//String[]  params=RequestUtil.getStringAryByStr(request, "params");			
		
		try{
		
			for(Integer i=1;i<ids.length;i++){
				SysOrgType sysOrgType=sysOrgTypeService.getById(Long.parseLong(ids[i]) );
				
				sysOrgType.setLevels(Long.parseLong(i.toString()) );
			   sysOrgTypeService.update(sysOrgType);			
			}
			resultMsg="组织结构类型保存成功";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"组织结构类型保存失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		
	}
}

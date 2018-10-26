package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.cache.ICache;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysPaur;
import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SysPaurService;
import com.hotent.platform.model.system.SysAuditModelType;
/**
 * 对象功能:SYS_PAUR 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-24 14:41:59
 */
@Controller
@RequestMapping("/platform/system/sysPaur/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysPaurController extends BaseController
{
	@Resource
	private SysPaurService sysPaurService;
	
	/**
	 * 添加或更新SYS_PAUR。
	 * @param request
	 * @param response
	 * @param sysPaur 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新SYS_PAUR",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>SYS_PAUR" +
					"【${SysAuditLinkService.getSysPaurLink(Long.valueOf(paurid))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		SysPaur sysPaur=getFormObject(request);
		try{
		SysAuditThreadLocalHolder.putParamerter("isAdd", sysPaur.getPaurid()==null);
			if(sysPaur.getPaurid()==null||sysPaur.getPaurid()==0){
				sysPaur.setPaurid(UniqueIdUtil.genId());
				sysPaurService.add(sysPaur);
				resultMsg=getText("SYS_PAUR");
			}else{
			    sysPaurService.update(sysPaur);
				resultMsg=getText("SYS_PAUR");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			SysAuditThreadLocalHolder.putParamerter("paurid", sysPaur.getPaurid().toString());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 SysPaur 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected SysPaur getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		SysPaur sysPaur = (SysPaur)JSONObject.toBean(obj, SysPaur.class);
		
		return sysPaur;
    }
	
	/**
	 * 取得SYS_PAUR分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看SYS_PAUR分页列表",detail="查看SYS_PAUR分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter=new QueryFilter(request,"");
		queryFilter.getFilters().put("userid", 0) ;
		List<SysPaur> list=sysPaurService.getAll(queryFilter);
		ModelAndView mv=this.getAutoView().addObject("sysPaurList",list);
		
		return mv;
	}
	
	/**
	 * 删除SYS_PAUR
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除SYS_PAUR",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除SYS_PAUR"+
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=sysPaurService.getById(Long.valueOf(item))/>" +
						"【${entity.paurname}】"+
					"</#list>"
			)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "paurid");
			sysPaurService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除SYS_PAUR成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑SYS_PAUR
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="添加或编辑SYS_PAUR",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加SYS_PAUR<#else>编辑SYS_PAUR:" +
					"<#assign entity=sysPaurService.getById(Long.valueOf(paurid))/>" +
					"【${entity.paurname}】</#if>"
			)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long paurid=RequestUtil.getLong(request,"paurid");
		String returnUrl=RequestUtil.getPrePage(request);
		SysPaur sysPaur=sysPaurService.getById(paurid);
		SysAuditThreadLocalHolder.putParamerter("isAdd", sysPaur==null);
		
		return getAutoView().addObject("sysPaur",sysPaur).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得SYS_PAUR明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看SYS_PAUR明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long paurid=RequestUtil.getLong(request,"paurid");
		SysPaur sysPaur = sysPaurService.getById(paurid);	
		return getAutoView().addObject("sysPaur", sysPaur);
	}
	
	/**
	 * 换皮肤风格。
	 * @param request
	 * @param response
	 * @param sysPaur 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("changeSkin")
	@Action(description="添加或更新SYS_PAUR",detail="<#if isAdd> 添加<#else>更新</#if>SYS_PAUR:" +
			"【${SysAuditLinkService.getSysPaurLink(Long.valueOf(paurid))}】")
	public void changeSkin(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;	
		String styleName=RequestUtil.getString(request,"styleName");
		//SysPaur sysPaur=getFormObject(request);
		Long userId=ContextUtil.getCurrentUserId();
		try{
			
		
			
			if(StringUtil.isNotEmpty(styleName)){
				boolean isadd=true;
				SysPaur sysPaur = sysPaurService.getByUserAndAlias(userId, "skin");
				if(sysPaur!=null){
					sysPaur.setPaurvalue(styleName);
					sysPaurService.update(sysPaur);
					resultMsg=getText("SYS_PAUR");
					isadd=false;
				}else{
					sysPaur=new SysPaur();
					sysPaur.setPaurid(UniqueIdUtil.genId());
					sysPaur.setAliasname("skin");
					sysPaur.setPaurname("皮肤");
					sysPaur.setUserid(userId);
					sysPaur.setPaurvalue(styleName);
					sysPaurService.add(sysPaur);
					resultMsg=getText("SYS_PAUR");
				}
				ICache iCache=(ICache)AppUtil.getBean(ICache.class);
				iCache.delByKey("skinStyle_"+userId);
				HttpSession session=request.getSession();
				session.setAttribute("skinStyle", styleName);
				SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
				SysAuditThreadLocalHolder.putParamerter("paurid", sysPaur.getPaurid().toString());
			}
			
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
}

package com.hotent.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysIndexColumn;
import com.hotent.platform.model.system.SysIndexLayout;
import com.hotent.platform.model.system.SysIndexLayoutManage;
import com.hotent.platform.model.system.SysObjRights;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgTactic;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysIndexColumnService;
import com.hotent.platform.service.system.SysIndexLayoutManageService;
import com.hotent.platform.service.system.SysIndexLayoutService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysOrgTacticService;
import com.hotent.platform.service.system.impl.curuser.OrgSubUserService;
import com.hotent.platform.service.util.ServiceUtil;
/**
 *<pre>
 * 对象功能:布局管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:40:13
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysIndexLayoutManage/")
public class SysIndexLayoutManageController extends BaseController
{
	@Resource
	private SysIndexLayoutManageService sysIndexLayoutManageService;
	
	@Resource
	private SysIndexLayoutService sysIndexLayoutService;
	
	@Resource
	private SysIndexColumnService sysIndexColumnService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysOrgTacticService sysOrgTacticService;
	@Resource
	private OrgSubUserService orgSubUserService;
	/**
	 * 添加或更新布局管理。
	 * @param request
	 * @param response
	 * @param sysIndexLayoutManage 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新布局管理")
	public void save(HttpServletRequest request, HttpServletResponse response,SysIndexLayoutManage sysIndexLayoutManage) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysIndexLayoutManage.getId()==null||sysIndexLayoutManage.getId()==0){
				sysIndexLayoutManage.setId(UniqueIdUtil.genId());
				sysIndexLayoutManageService.add(sysIndexLayoutManage);
				resultMsg=getText("添加","布局管理");
			}else{
			    sysIndexLayoutManageService.update(sysIndexLayoutManage);
				resultMsg=getText("更新","布局管理");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得布局管理分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看布局管理分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter filter =new QueryFilter(request, "sysIndexLayoutManageItem");
		boolean isSuperAdmin = SysUser.isSuperAdmin();
		if(!isSuperAdmin){	//不是超级管理员 filter  //TODO  保留接口
		    List<Long> orgIds =	orgSubUserService.getByCurUser(ServiceUtil.getCurrentUser());
			filter.addFilter("orgIds", StringUtils.join(orgIds,","));
		}
		List<SysIndexLayoutManage> list=sysIndexLayoutManageService.getAll(filter);
		for (SysIndexLayoutManage sysIndexLayoutManage : list) {
			if(BeanUtils.isNotEmpty(sysIndexLayoutManage.getOrgId())){
				SysOrg sysOrg =sysOrgService.getById(sysIndexLayoutManage.getOrgId());
				sysIndexLayoutManage.setOrgName(sysOrg.getOrgName());
			}
		}
		SysOrgTactic sysOrgTactic = sysOrgTacticService.getOrgTactic();
		return this.getAutoView()
				.addObject("objType",SysObjRights.RIGHT_TYPE_INDEX_MANAGE)
				.addObject("isSuperAdmin",isSuperAdmin)
				.addObject("sysIndexLayoutManageList",list)
				.addObject("orgTactic",sysOrgTactic.getOrgTactic());
	}
	
	
	/**
	 * 删除布局管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除布局管理")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysIndexLayoutManageService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除布局管理成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑布局管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑布局管理")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysIndexLayoutManage sysIndexLayoutManage=sysIndexLayoutManageService.getById(id);
		
		return getAutoView().addObject("sysIndexLayoutManage",sysIndexLayoutManage)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得布局管理明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看布局管理明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysIndexLayoutManage sysIndexLayoutManage = sysIndexLayoutManageService.getById(id);	
		return getAutoView().addObject("sysIndexLayoutManage", sysIndexLayoutManage);
	}
	
	/**
	 * 设计我的首页布局
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design")
	@Action(description="设计我的首页布局")
	public ModelAndView design(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id =  RequestUtil.getLong(request, "id");
		//首页布局
		List<SysIndexLayout> layoutList = sysIndexLayoutService.getAll();
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("id",null);
		filter.setPageBean(null);
		Map<String,Object>  params  =  RequestUtil.getParameterValueMap(request);
		//首页栏目，取出来需要解析
		List<SysIndexColumn>  columnList = sysIndexColumnService.getHashRightColumnList(filter,params,true);
		//获取展示的布局
		Map<String,List<SysIndexColumn>> columnMap = sysIndexColumnService.getColumnMap(columnList);
		//获取当前的布局
		SysIndexLayoutManage sysIndexLayoutManage = sysIndexLayoutManageService.getLayoutList(id,columnList);	
		if(BeanUtils.isNotEmpty(sysIndexLayoutManage)){
			if(BeanUtils.isNotEmpty(sysIndexLayoutManage.getOrgId())){
				SysOrg sysOrg =sysOrgService.getById(sysIndexLayoutManage.getOrgId());
				sysIndexLayoutManage.setOrgName(sysOrg.getOrgName());
			}
		}
		return getAutoView().addObject("layoutList",layoutList)
				.addObject("columnMap",columnMap)
				.addObject("sysIndexLayoutManage", sysIndexLayoutManage);
	}
	

	/**
	 * 保存首页布局
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveLayout")
	@Action(description="保存首页布局")
	public void saveLayout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id =  RequestUtil.getLong(request, "id",null);
		String name =  RequestUtil.getString(request, "name");
		String memo =  RequestUtil.getString(request, "memo");
		Short isDef =  RequestUtil.getShort(request, "isDef");
		String html =  RequestUtil.getString(request, "html");
		String designHtml =  RequestUtil.getString(request, "designHtml");
		
		ResultMessage resultObj = null;
		
		
			
		try {
				int type = 0;
				SysIndexLayoutManage sysIndexLayoutManage = new SysIndexLayoutManage();
				if (BeanUtils.isEmpty(id)) {
					id = UniqueIdUtil.genId();
					sysIndexLayoutManage.setId(id);
				} else {
					type = 1;
					sysIndexLayoutManage = sysIndexLayoutManageService.getById(id);
				}
				if(! SysUser.isSuperAdmin()){//把自己的组织设置进去
					Long companyId  =ContextUtil.getCurrentCompanyId();
					sysIndexLayoutManage.setOrgId(companyId);
				}
				sysIndexLayoutManage.setName(name);
				sysIndexLayoutManage.setMemo(memo);
				sysIndexLayoutManage.setIsDef(isDef);
				sysIndexLayoutManage.setDesignHtml(designHtml);
				sysIndexLayoutManage.setTemplateHtml(html);
				sysIndexLayoutManageService.save(sysIndexLayoutManage,type);
	
			resultObj = new ResultMessage(ResultMessage.Success,id.toString());	
		} catch (Exception e) {
			resultObj = new ResultMessage(ResultMessage.Fail, e.getMessage());	
		}
		 response.getWriter().print(resultObj);
	}
	
	/**
	 * 选择首页布局模版
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description="选择首页布局模版")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter filter =new QueryFilter(request, "sysIndexLayoutManageItem");
		Map<String,Object>  params  =  RequestUtil.getParameterValueMap(request);
		//首页栏目，取出来需要解析
		List<SysIndexColumn>  columnList = sysIndexColumnService.getIndexColumnData(params);
		List<SysIndexLayoutManage> list=sysIndexLayoutManageService.getList(filter);
		for (SysIndexLayoutManage sysIndexLayoutManage : list) {
			sysIndexLayoutManage.setDesignHtml(sysIndexColumnService.parserDesignHtml(sysIndexLayoutManage.getDesignHtml(), columnList));
		}
		return this.getAutoView()
				.addObject("sysIndexLayoutManageList",list);
	}
	
	/**
	 * 保存组织
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveOrg")
	@Action(description = "保存组织")
	public void saveOrg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		Long id = RequestUtil.getLong(request, "id");
		Long orgId = RequestUtil.getLong(request, "orgId",null);
		try {
			SysIndexLayoutManage  sysIndexLayoutManage = sysIndexLayoutManageService.getById(id);
			sysIndexLayoutManage.setOrgId(orgId);
			sysIndexLayoutManageService.update(sysIndexLayoutManage);
			resultMsg = getText("保存组织成功");
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}
}

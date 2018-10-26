
package com.hotent.platform.controller.system;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.Subsystemdef.service.Subsystemdef.SubsystemdefService;
import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;




import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WSystemInformation;

import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.ResourcesService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SubSystemUtil;
import com.hotent.sysinfomation.service.sysinfomation.SysinfomationService;
//import com.hotent.w_tba_zsjghcd.service.w_tba_zsjghcd.WTbaZsJghCdService;
import com.hotent.core.log.SysAuditThreadLocalHolder;
//import com.hotent.w_tba_zsjghcd.model.w_tba_zsjghcd.w_system_information;
//import com.hotent.w_tba_zsjghcd.model.w_tba_zsjghcd.w_def_information;
import org.springframework.jdbc.core.JdbcTemplate;
/*import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;*/
/**
 * 对象功能:子系统管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 12:22:06
 */
@Controller
@RequestMapping("/platform/system/subSystem/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SubSystemController extends BaseController
{
	@Resource
	private SubSystemService service;
	@Resource
	private ResourcesService resourcesService;

	
	@Resource
	private SubsystemdefService subsystemdefService;//子系统与流程名 表
	@Resource
	private BpmDefinitionService  bpmDefinitionService;//流程名与流程id表
	@Resource
	private BpmNodeSetService  bpmNodeSetService;//流程id与节点信息表
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private SysinfomationService sysinfomationService;
	
    
	
	@RequestMapping("save1")
	@Action(description="保存logo连接更改信息")  //sysinfomationplat.jsp调用此方法的
	public void save1(HttpServletRequest request,HttpServletResponse response, SubSystem subSystemForm) throws Exception
	{
		String resultMsg=null;		
		try{
			
			    service.update1(subSystemForm);
				resultMsg=getText("更新","流程右键信息表");
			
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
		
	}
		
	@RequestMapping("tree")
	@ResponseBody
	public List<SubSystem> tree(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		System.out.println("进入tree");	
		List<SubSystem> list=service.getAll();
		SubSystem root=new SubSystem();
		root.setSystemId(0l);
		root.setParentId(-1l);
		root.setSysName("所有系统");
		list.add(root);
		System.out.println("完成tree");	
		return list;
	}

	
	/**
	 * 取得子系统管理分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="取得子系统管理分页列表",detail="取得子系统管理分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		System.out.println("进入list");	
		List<SubSystem> list=service.getAll(new QueryFilter(request,"subSystemItem"));
		ModelAndView mv=this.getAutoView().addObject("subSystemList",list);
		System.out.println("完成list");	
		return mv;
	}
	
	@RequestMapping("edit")
	@Action(description="添加或编辑子系统管理",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加子系统管理<#else>编辑子系统管理" +
					"<#assign entity=SubSystemService.getById(Long.valueOf(id))/>" +
					"【${entity.sysName}】</#if>"
	)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		
		System.out.println("进入edit");	
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		SubSystem subSystem=null;
		boolean isadd=true;
		if(id!=0){
			subSystem= service.getById(id);
			isadd=false;
		}else{
			subSystem=new SubSystem();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		
		System.out.println("完成edit");	
		return getAutoView().addObject("subSystem",subSystem).addObject("returnUrl", returnUrl);
	}
	
	
	
	
	
	

	/**
	 * 删除子系统管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除子系统管理",
	execOrder=ActionExecOrder.BEFORE,
	detail= "删除子系统管理" +
			"<#list StringUtils.split(id,\",\") as item>" +
				"<#assign entity=subSystemService.getById(Long.valueOf(item))/>" +
				"【${entity.sysName}】" +
			"</#list>"
			)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		System.out.println("进入del按钮");	
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			service.delByIds(lAryId);
			
			//从session清除当前系统。
			SubSystemUtil.removeSystem();
			message=new ResultMessage(ResultMessage.Success,"删除子系统成功");
		}catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除子系统失败");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);	
		System.out.println("完成del按钮");	
		
	}


	/**
	 * 取得子系统管理明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="取得子系统管理明细",detail="取得子系统管理明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		System.out.println("进入get按钮");	
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		SubSystem po = service.getById(id);		
		
		System.out.println("完成get按钮");	
		return this.getAutoView().addObject("subSystem", po).addObject("canReturn",canReturn);
		
	}
	
	/**
	 * 导出子系统资源
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description="导出子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="导出子系统资源【${subSystem.sysName}】"
			)
	public void exportXml(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		System.out.println("进入exportxml");	
		long id=RequestUtil.getLong(request, "systemId");
		if(id!=0){
			String strXml=service.exportXml(id);
			SubSystem subSystem=service.getById(id);
			String fileName=subSystem.getAlias();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fileName,"UTF-8") + ".xml");
			response.getWriter().write(strXml);
			response.getWriter().flush();
			response.getWriter().close();
			try {
				SysAuditThreadLocalHolder.putParamerter("subSystem", subSystem);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		System.out.println("完成exportxml");	
	}
	
	/**
	 * 导入子系统资源。
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description="导入子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="导入子系统资源<#if isAdd><#assign entity=subSystemService.getById(Long.valueOf(systemId))/>" +
					"【${entity.sysName}】成功<#else>失败</#if>"
			)
	public void importXml(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		System.out.println("进入importxml按钮");	
		long systemId=RequestUtil.getLong(request, "systemId");
		MultipartFile fileLoad = request.getFile("xmlFile");
		ResultMessage resultMessage = null;
		try {
			service.importXml(fileLoad.getInputStream(),systemId);
			resultMessage = new ResultMessage(ResultMessage.Success, "导入成功!");
			SysAuditThreadLocalHolder.putParamerter("isAdd", true);
			writeResultMessage(response.getWriter(), resultMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			SysAuditThreadLocalHolder.putParamerter("isAdd", false);
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"导入失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		System.out.println("完成importxml");	
	}
	@RequestMapping("information")
	@Action(description="取得子系统管理分页列表",detail="取得子系统管理分页列表")
	public ModelAndView information(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		System.out.println("进入information");			
		ModelAndView mv=this.getAutoView();
		System.out.println("完成information");	
		return mv;
	}
	
	/**
	 * 业务逻辑校验
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("verification")        
	@Action(description="取得子系统管理分页列表",detail="取得子系统管理分页列表")
	public ModelAndView verification(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		System.out.println("进入list");	
		String status =RequestUtil.getString(request, "status");
		System.out.println("进入list++********************++"+status);	
		List<SubSystem> list=new ArrayList<SubSystem>();;
		if(status.equals("1")||status.equals("0"))//业务类
		{
			list=service.getAll2();
		
			
		}	
		if(status.equals("2"))// sysdefnodeergodicBox.jsp 调的----来自仿真报告
			{
			Long[] ids =RequestUtil.getLongAryByStr(request, "ids");
			System.out.println("获得子系统ids++-----------------------------------+++++++"+ids[0]);	
			
			 List<WSystemInformation> sys_information = new ArrayList<WSystemInformation>();//创建子系统链表
			 
			   sys_information=service.systemIdToSysInfo(ids);
				service.countServiceNumber(sys_information);  //调用输出服务信息的方法
							
				sys_information=sysdefnodeergodicService.sysListBasicStatistics(sys_information);
				sysdefnodeergodicService.get_jisuan_tongji(sys_information);		
				sysinfomationService.writeToWTbaSubsysList(sys_information);//将子系统信息转成我想要的表	
			
			//=service.getAll2();
			SubSystem subSystem;
			for(int i=0;i<ids.length;i++)
				{subSystem=service.getById(ids[i]);
					list.add(subSystem);
				}
			}
		if(status.equals("3"))  //sysdefnodeergodicBox1.jsp 调的   ----来自业务逻辑校验
		{Long[] ids =RequestUtil.getLongAryByStr(request, "ids");
			SubSystem subSystem;
			for(int i=0;i<ids.length;i++)
				{subSystem=service.getById(ids[i]);
					list.add(subSystem);
				}	
		}
		ModelAndView mv=this.getAutoView().addObject("subSystemList",list).addObject("status",status);
		                                  
		System.out.println("完成list");	
		
		return mv;
	}
	@RequestMapping("systree")        
	@Action(description="取得子系统管理分页列表",detail="取得子系统管理分页列表")
	public ModelAndView systree(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	String containerId =RequestUtil.getString(request, "containerId");	
	    System.out.println("点击容器树状，容器id为："+containerId); 
		ModelAndView mv=this.getAutoView().addObject("containerId",containerId);                           		
		return mv;
	}	
}






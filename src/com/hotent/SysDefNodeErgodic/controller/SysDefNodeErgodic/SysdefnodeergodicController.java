

package com.hotent.SysDefNodeErgodic.controller.SysDefNodeErgodic;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.page.PageList;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.OperateNodeInfo;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.TransactionNodeInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WNodeInformation;
import com.hotent.platform.model.system.WSystemInformation;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.sysinfomation.service.sysinfomation.SysinfomationService;

import org.apache.commons.lang.exception.ExceptionUtils;


import com.hotent.SysDefNodeErgodic.model.SysDefNodeErgodic.Sysdefnodeergodic;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.web.ResultMessage;
import com.hotent.formParcel.model.formParcel.FormParcel;
/**
 * 对象功能:子系统流程节点遍历 控制器类
 */
@Controller
@RequestMapping("/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/")
public class SysdefnodeergodicController extends BaseController
{
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private SysinfomationService sysinfomationService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;

	/**
	 * 添加或更新子系统流程节点遍历。
	 * @param request
	 * @param response
	 * @param sysdefnodeergodic 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新子系统流程节点遍历")
	public void save(HttpServletRequest request, HttpServletResponse response,Sysdefnodeergodic sysdefnodeergodic) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysdefnodeergodic.getId()==null){
				sysdefnodeergodicService.save(sysdefnodeergodic);
				resultMsg=getText("添加","子系统流程节点遍历");
			}else{
			    sysdefnodeergodicService.save(sysdefnodeergodic);
				resultMsg=getText("更新","子系统流程节点遍历");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得子系统流程节点遍历分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看子系统流程节点遍历分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Sysdefnodeergodic> list=sysdefnodeergodicService.getAll(new QueryFilter(request,"sysdefnodeergodicItem"));
		ModelAndView mv=this.getAutoView().addObject("sysdefnodeergodicList",list);
		return mv;
	}
	
	/**
	 * 删除子系统流程节点遍历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除子系统流程节点遍历")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			sysdefnodeergodicService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除子系统流程节点遍历成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑子系统流程节点遍历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑子系统流程节点遍历")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Sysdefnodeergodic sysdefnodeergodic=sysdefnodeergodicService.getById(id);
		
		return getAutoView().addObject("sysdefnodeergodic",sysdefnodeergodic)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得子系统流程节点遍历明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看子系统流程节点遍历明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Sysdefnodeergodic sysdefnodeergodic=sysdefnodeergodicService.getById(id);
		return getAutoView().addObject("sysdefnodeergodic", sysdefnodeergodic);
	}
	@RequestMapping("tongji")
	@Action(description="查看子系统明细")
	public ModelAndView tongji(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		
		System.out.println("进入sysdefnode统计按钮");	
		Long[] ids =RequestUtil.getLongAryByStr(request, "id") ;//获得jsp中打对钩的子系统id	
		
		
		
		System.out.println("获得id【】"+ids[0]);
		System.out.println(ids);
		List<WSystemInformation> sys_information = new ArrayList<WSystemInformation>();//创建子系统链表
		for (int i1=0;i1<ids.length;i1++)//遍历传过来的id【】
			{	
				WSystemInformation sys_info = new WSystemInformation(Long.toString(ids[i1]),subSystemService.getById(ids[i1]).getSysName());//因为通过for循环说明有一个子系统，创建一个子系统对象						
				sys_info=sysdefnodeergodicService.getlianjie(sys_info);	
				sys_information.add(sys_info);	//江子系统放入子系统list中	
			}		
		List<Sysdefnodeergodic> sysdefnodeergodicList=sysdefnodeergodicService.writeInBySysList(sys_information);//将子系统信息转成我想要的表	
	
		ModelAndView mv=this.getAutoView().addObject("sysdefnodeergodicList",sysdefnodeergodicList);		
		return mv;		
	}
	@RequestMapping("systongji")
	@Action(description="查看子系统明细")
	public ModelAndView systongji(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		
		System.out.println("进入systongji统计按钮");	
		Long[] ids =RequestUtil.getLongAryByStr(request, "id") ;//获得jsp中打对钩的子系统id	
		
		List<WSystemInformation> sys_information = new ArrayList<WSystemInformation>();//创建子系统链表
		for (int i1=0;i1<ids.length;i1++)//遍历传过来的id【】
			{	
				WSystemInformation sys_info = new WSystemInformation(Long.toString(ids[i1]),subSystemService.getById(ids[i1]).getSysName());//因为通过for循环说明有一个子系统，创建一个子系统对象						
				sys_info=sysdefnodeergodicService.getlianjie(sys_info);	
				sys_information.add(sys_info);	//江子系统放入子系统list中	
			}	
		
		sys_information=sysdefnodeergodicService.sysListBasicStatistics(sys_information);
		
		
		int sort =RequestUtil.getInt(request, "sort");   //获得排序的方式
		sysdefnodeergodicService.orderingBysystongji(sort, sys_information);   //调用 排序 的 方法
		
		sysdefnodeergodicService.ergodicId=0;
		
		List<Sysdefnodeergodic> sysdefnodeergodicList=sysdefnodeergodicService.writeInBySysList(sys_information);//将子系统信息转成我想要的表	
		for(int i=0;i<sysdefnodeergodicList.size();i++)
		{
			if(sysdefnodeergodicList.get(i).getDefName().equals("")&&
					sysdefnodeergodicList.get(i).getNodeName().equals(""))
			{
				sysdefnodeergodicList.remove(i);
				i--;
			}
		}
		ModelAndView mv=this.getAutoView().addObject("sysdefnodeergodicList",sysdefnodeergodicList).addObject("sort",sort);		
		return mv;	
	}
	@RequestMapping("deftongji")
	@Action(description="查看流程图明细")
	public ModelAndView deftongji(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("进入def统计按钮");	
			Long[] ids =RequestUtil.getLongAryByStr(request, "id") ;//获得jsp中打对钩的流程id			
			String typeName =RequestUtil.getString(request,"typeName") ;
			System.out.println("获得typeName"+typeName);			
			List<WDefInformation> def_information = new ArrayList<WDefInformation>();//创建流程链表
			for(int i=0;i<ids.length;i++)
			{
				System.out.println(ids[i]);
				List<BpmDefinition> bpmDefinitionList=bpmDefinitionService.getByDefId(ids[i]);
				//System.out.println("获得流程基本信息");
				if(bpmDefinitionList.size()==0)continue;
				//System.out.println("创建流程对象");
				WDefInformation def_info=new WDefInformation(ids[i]);
				//System.out.println("写入流程对象");
				def_info.bpmdef=bpmDefinitionList.get(0);
				//System.out.println("补全流程分支");
				def_info=sysdefnodeergodicService.defGetlianjie(def_info,"");
			
									
				//System.out.println("完成流程链接");
				def_info.typeName=typeName;
				//System.out.println("写入流程链表");
				def_information.add(def_info);
			}
			//sysdefnodeergodicService.defceshilianbiao(def_information);//输出	
			//subSystemService.writeInSysDefNodeErgodic(sys_information);//写入sysdefnodeergodec表
			/*int ergodicId, 
			int systemNum,
			
			String systemId,
			int sysNotSetNum,
			List<WDefInformation> sysDefInfoList, 
			List<Sysdefnodeergodic> sysdefnodeergodicList*/
			sysdefnodeergodicService.defBasicStatistics(def_information.get(0));
			int sort =RequestUtil.getInt(request, "sort");   //获得排序的方式
			sysdefnodeergodicService.orderingBydeftongji(sort, def_information);  //调用排序的方法
			
			List<Sysdefnodeergodic> sysdefnodeergodicList=new ArrayList<Sysdefnodeergodic>();
			WSystemInformation a1=new WSystemInformation("10001","流程补全子系统");
			a1.sys_def_info_list=def_information;	
			sysdefnodeergodicService.ergodicId=0;
			sysdefnodeergodicList=sysdefnodeergodicService.writeInByDefList(1,a1,sysdefnodeergodicList);//将流程信息转成我想要的表			
			//subSystemService.writeInSysDefNodeErgodicListSystemOut(sysdefnodeergodicList);//输出转型后的表		
			//sysdefnodeergodicService.getNodeNum(def_information);
			//sysdefnodeergodicService.getNodeNum(sysdefnodeergodicList);//统计每个节点利用个数
			
			for(int i=0;i<sysdefnodeergodicList.size();i++)
			{
				if(
						sysdefnodeergodicList.get(i).getNodeName().equals("")&&
						sysdefnodeergodicList.get(i).getOperateNodeName().equals(""))
				{
					sysdefnodeergodicList.remove(i);
					i--;
				}
			}
			ModelAndView mv=this.getAutoView().addObject("sysdefnodeergodicList",sysdefnodeergodicList).addObject("sort",sort);			
			return mv;	
			//sys_information=subSystemService.get_jiben_tongji(sys_information);	//基本信息统计
			//sys_information=subSystemService.get_jisuan_tongji(sys_information);	//运算基本信息统计
	}
	
	
	@RequestMapping("nodetongji")
	@Action(description="查看流程节点明细")
	public ModelAndView nodetongji(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("进入节点统计按钮");	
			Long[] ids =RequestUtil.getLongAryByStr(request, "id") ;//获得jsp中打对钩的流程id			
			BpmNodeSet a1=bpmNodeSetService.getById(ids[0]);
			List<BpmDefinition> bpmDefinitionList=bpmDefinitionService.getByDefId(a1.getDefId());
			//System.out.println("获得流程基本信息");
			List<WDefInformation> def_information = new ArrayList<WDefInformation>();//创建流程链表
			//System.out.println("创建流程对象");
			WDefInformation def_info=new WDefInformation(a1.getDefId());
			//System.out.println("写入流程对象");
			def_info.bpmdef=bpmDefinitionList.get(0);
			//System.out.println("补全流程分支");
			def_info=sysdefnodeergodicService.defGetlianjie(def_info,"");
			
			
			List<WNodeInformation> newList=new ArrayList<WNodeInformation>();
			for(int i=0;i<def_info.def_node_info_list.size();i++)
			{
				if(Long.toString(def_info.def_node_info_list.get(i).bpmNodeSet.getSetId()).equals(Long.toString(ids[0])))
					{newList.add(def_info.def_node_info_list.get(i));break;}
			}
			def_info.def_node_info_list=newList;
			def_information.add(def_info);
			sysdefnodeergodicService.defBasicStatistics(def_information.get(0));
			int sort =RequestUtil.getInt(request, "sort");   //获得排序的方式
			sysdefnodeergodicService.orderingBynodetongji(sort,def_info.def_node_info_list);    //调用排序的方法
		
			List<Sysdefnodeergodic> sysdefnodeergodicList=new ArrayList<Sysdefnodeergodic>();
			WSystemInformation a2=new WSystemInformation("10001","流程补全子系统");
			a2.sys_def_info_list=def_information;
			sysdefnodeergodicService.ergodicId=0;
			sysdefnodeergodicList=sysdefnodeergodicService.writeInByDefList(1,a2,sysdefnodeergodicList);//将流程信息转成我想要的表		
			for(int i=0;i<sysdefnodeergodicList.size();i++)
			{
				if(
						sysdefnodeergodicList.get(i).getOperateNodeName().equals("")&&
						sysdefnodeergodicList.get(i).getTransactionNodeName().equals("")&&
						sysdefnodeergodicList.get(i).getTableParcelName().equals("")&&
						sysdefnodeergodicList.get(i).getTableParcelNodeName().equals("")
						
						)
				{
					sysdefnodeergodicList.remove(i);
					i--;
				}
			}
			
			ModelAndView mv=this.getAutoView().addObject("sysdefnodeergodicList",sysdefnodeergodicList).addObject("sort",sort);		
			return mv;	
			//sys_information=subSystemService.get_jiben_tongji(sys_information);	//基本信息统计
			//sys_information=subSystemService.get_jisuan_tongji(sys_information);	//运算基本信息统计
	}
	

	@RequestMapping("box")
	@Action(description="业务逻辑校验跳转jsp")
	public ModelAndView box(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		    System.out.println("进入box方法 ");	
			String[] ids =RequestUtil.getStringAryByStr(request, "ids");
			String ids1="";
			for(int i=0;i<ids.length;i++)
				ids1=ids1+ids[i]+",";
			ModelAndView mv=this.getAutoView().addObject("ids",ids1);		
			return mv;	

	}
	@RequestMapping("box1")
	@Action(description="业务逻辑校验跳转jsp")
	public ModelAndView box1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		    System.out.println("进入box1方法 ");	
		  //  excelService.jisuan();
			String[] ids =RequestUtil.getStringAryByStr(request, "ids");
			Long[] id1111 =RequestUtil.getLongAryByStr(request, "ids");
			List<WSystemInformation>  aaa=subSystemService.systemIdToSysInfo(id1111);
			String a=subSystemService.countServiceNumber(aaa);
			System.out.println(a);
			String ids1="";
			for(int i=0;i<ids.length;i++)
				ids1=ids1+ids[i]+",";
			ModelAndView mv=this.getAutoView().addObject("ids",ids1);		
			return mv;	

	}
	
	
	
}


package com.hotent.Ywbsbd.controller.Ywbsbd;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.page.PageList;
import com.hotent.netWorkMap.model.netWorkMap.Bpmnetworkmap;
import com.hotent.netWorkMap.service.netWorkMap.BpmnetworkmapService;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SubSystemService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.Ywbsbd.model.Ywbsbd.Ywbsbd;
import com.hotent.Ywbsbd.service.Ywbsbd.YwbsbdService;
import com.hotent.core.web.ResultMessage;

/**
 * 对象功能:业务部署绑定 控制器类
 */
@Controller
@RequestMapping("/Ywbsbd/Ywbsbd/ywbsbd/")
public class YwbsbdController extends BaseController
{
	@Resource
	private YwbsbdService ywbsbdService;
	@Resource
	private SubSystemService subSystemService;
	// whp
	@Resource
	private BpmnetworkmapService bpmnetworkmapService;
	/**
	 * 添加或更新业务部署绑定。
	 * @param request
	 * @param response
	 * @param ywbsbd 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务部署绑定")
	public void save(HttpServletRequest request, HttpServletResponse response,Ywbsbd ywbsbd) throws Exception
	{
		String resultMsg=null;		
		try{
			if(ywbsbd.getId()==null){
				ywbsbdService.save(ywbsbd);
				resultMsg=getText("添加","业务部署绑定");
			}else{
			    ywbsbdService.save(ywbsbd);
				resultMsg=getText("更新","业务部署绑定");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	/**
	 * 添加或更新业务部署绑定。
	 * @param request
	 * @param response
	 * @param ywbsbd 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveSubsystemRelation")
	@Action(description="添加或更新业务部署绑定")
	public void saveSubsystemRelation(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		String service = request.getParameter("service");
		String[] quotaids = request.getParameter("quotaids").split(","); 
		String nodeId= request.getParameter("nodeId");
		String defId= request.getParameter("defId");
		ResultMessage message=null;
		try{
			for(int i=0;i<quotaids.length;i++) {
				if(!ywbsbdService.exist(defId,nodeId,service,quotaids[i])) {
					Ywbsbd ywbsbd1=new Ywbsbd();
					SubSystem subSystem=subSystemService.getById(Long.parseLong(quotaids[i]));
					ywbsbd1.setDefId(defId);
					ywbsbd1.setNodeId(nodeId);
					ywbsbd1.setSystemId(quotaids[i]);
					ywbsbd1.setService(service);
					ywbsbd1.setSysname(subSystem.getSysName());
					ywbsbd1.setSysmeno(subSystem.getMemo());
					ywbsbd1.setSysalias(subSystem.getAlias());
					ywbsbdService.save(ywbsbd1);
					resultMsg=getText("添加","业务部署绑定");
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
				}
				else
				{
					message=new ResultMessage(ResultMessage.Fail, "该子系统和服务已经被绑定在此节点下!");
					addMessage(message, request);
					resultMsg=getText("已有","业务部署绑定");
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
				}
			}
			
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得业务部署绑定分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务部署绑定分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String defId=request.getParameter("defId");
		String nodeId=request.getParameter("nodeId");
		List<Ywbsbd> list=ywbsbdService.getAll(new QueryFilter(request,"ywbsbdItem"));
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String all="SELECT * FROM w_service_form";
		List<Map<String,Object>>  serivcetList = template.queryForList(all);
		ModelAndView mv=this.getAutoView().addObject("ywbsbdList",list)
		.addObject("defId", defId)
		.addObject("nodeId", nodeId)
		.addObject("serivcetList", serivcetList);
		return mv;
	}
	
	/**
	 * 删除业务部署绑定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务部署绑定")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			ywbsbdService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除业务部署绑定成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务部署绑定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务部署绑定")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		Ywbsbd ywbsbd=ywbsbdService.getById(id);
		
		return getAutoView().addObject("ywbsbd",ywbsbd);
	}

	/**
	 * 取得业务部署绑定明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务部署绑定明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Ywbsbd ywbsbd=ywbsbdService.getById(id);
		return getAutoView().addObject("ywbsbd", ywbsbd);
	}
	/**
	 * 取得子系统设备关联表
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getRelation")
	@Action(description="查看业务部署绑定明细")
	public ModelAndView getRelation(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String systemId=RequestUtil.getString(request,"systemId");
		String defId=RequestUtil.getString(request, "defId");
		String nodeId=RequestUtil.getString(request, "nodeId");
		List<Ywbsbd> list=ywbsbdService.getBySystemId(systemId);
		List<Bpmnetworkmap> list1 = bpmnetworkmapService.getAll();
		List<String> listSubject = new ArrayList<String>();	
		for(Ywbsbd b:list)
		{
			for(Bpmnetworkmap a:list1)
			{
				if(b.getDefId().equals(a.getDefId().toString()))
				{
					listSubject.add(a.getSubject());
				}
			}
		}
		return getAutoView().addObject("list", list)
							.addObject("defId", defId)
							.addObject("nodeId", nodeId)
							.addObject("listSubject", listSubject);
	}
}
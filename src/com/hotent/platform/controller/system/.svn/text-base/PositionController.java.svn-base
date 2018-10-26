package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.Job;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.system.DemensionService;
import com.hotent.platform.service.system.JobService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.UserPositionService;
import com.hotent.platform.service.system.impl.OrgServiceImpl;
/**
 *<pre>
 * 对象功能:系统岗位表，实际是部门和职务的对应关系表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/position/")
public class PositionController extends BaseController
{
	@Resource
	private PositionService positionService;
	@Resource
	private UserPositionService userPositionService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private JobService jobService;
	@Resource
	private UserPositionDao userPositionDao;
	@Resource
	private DemensionService demensionService;
	@Resource
	private OrgServiceImpl orgServiceImpl;

	/**
	 * 添加或更新系统岗位表，实际是部门和职务的对应关系表。
	 * @param request
	 * @param response
	 * @param position 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统岗位表，实际是部门和职务的对应关系表")
	public void save(HttpServletRequest request, HttpServletResponse response,Position position) throws Exception
	{
		String resultMsg=null;		
		try{
			if(position.getPosId()==null||position.getPosId()==0){
				position.setPosId(UniqueIdUtil.genId());
				positionService.add(position);
				resultMsg=getText("添加成功","系统岗位表，实际是部门和职务的对应关系表");
			}else{
			    positionService.update(position);
			    // 同时要修改userposition
			    updateUserPosition(position);
				resultMsg=getText("更新成功","系统岗位表，实际是部门和职务的对应关系表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}
		catch(DuplicateKeyException ex){
			writeResultMessage(response.getWriter(),"该岗位代码已存在.",ResultMessage.Fail);
		}
		catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	//更新userposition中的jobid
	public void updateUserPosition(Position position){
		List<UserPosition> userPositionList=userPositionDao.getByPosId(position.getPosId());
		for(UserPosition userPosition:userPositionList){
			userPosition.setJobId(position.getJobId());
			userPositionDao.update(userPosition);
		}
	}
	
	/**
	 * 取得系统岗位表，实际是部门和职务的对应关系表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统岗位表，实际是部门和职务的对应关系表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long orgId = RequestUtil.getLong(request, "orgId");
		List<Position> list=positionService.getAll(new QueryFilter(request,"positionItem"));
	    for(Position position:list){
	    	// 获取某岗位的用户列表字符串
	    	position.setUserNames(userPositionService.getUsernamesByPosId(position.getPosId()));
	    }
		ModelAndView mv=this.getAutoView()
				.addObject("positionList",list)
		        .addObject("action", "global")
		        .addObject("orgId", orgId);
		return mv;
	}
	
	
	@RequestMapping("gradeList")
	@Action(description="查看系统岗位表，实际是部门和职务的对应关系表分页列表") 
	public ModelAndView gradeList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.list(request,response);
		mv.setViewName("/platform/system/positionList.jsp");
	    mv.addObject("action", "grade"); 
		return mv;
	}
	
	/**
	 * 删除系统岗位表，实际是部门和职务的对应关系表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统岗位表，实际是部门和职务的对应关系表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "posId");
			//循环修改删除标志
			for(int i=0,n=lAryId.length;i<n;i++){
				positionService.deleteByUpdateFlag(lAryId[i]);
			}
			message=new ResultMessage(ResultMessage.Success, "删除岗位成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑系统岗位表，实际是部门和职务的对应关系表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑系统岗位表，实际是部门和职务的对应关系表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{   
		//岗位
		Long posid=RequestUtil.getLong(request,"posId",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		Position position=positionService.getById(posid);
		//组织
		Long orgId =0L;
		orgId=RequestUtil.getLong(request, "orgId");
		if(orgId==null||orgId==0L){
			orgId=position.getOrgId();
		}
		SysOrg sysOrg = sysOrgService.getById(orgId);
		//职务
		List<Job> jobList=jobService.getAll();
		return getAutoView().addObject("position",position)
							.addObject("returnUrl",returnUrl)
							.addObject("sysOrg", sysOrg)
							.addObject("jobList", jobList);
	}

	/**
	 * 取得系统岗位表，实际是部门和职务的对应关系表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统岗位表，实际是部门和职务的对应关系表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long posid=RequestUtil.getLong(request,"posId");
		Position position = positionService.getById(posid);	
		
		//组织
		Long orgId =0L;
		orgId=RequestUtil.getLong(request, "orgId");
		if(orgId==null||orgId==0L){
			orgId=position.getOrgId();
		}
		SysOrg sysOrg = sysOrgService.getById(orgId);
		//职务
		Job job=jobService.getById(position.getJobId());
		position.setJobName(job.getJobname());
		return getAutoView().addObject("position", position)
				.addObject("sysOrg", sysOrg);
	}
	
	/**
	 * 岗位组织树
	 * @author hjx
	 * @version 创建时间：2013-11-28  下午9:39:16
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("getOrgPosTreeData")
	@ResponseBody
	public List<Position> getOrgPosTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String orgIds=RequestUtil.getString(request,"orgIds");
		
		//根据组织ID串 得到组织集合
		List<Position> positionList=positionService.getOrgListByOrgIds(orgIds);
		
		//根据组织ID串得到用户岗位集合
		List<Position> positionList2=positionService.getOrgPosListByOrgIds(orgIds);
		
		List  treeList=new ArrayList();
		
		//父节点 {组织id,0,    组织名称,true    }
		//子节点{岗位id,组织ID,岗位名称,null     }
		treeList.addAll(positionList);
		treeList.addAll(positionList2);	
		
		return treeList;
	}
	
	/**
	 * 获取岗位树
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getTreeData")
	@ResponseBody
	public List<Position> getTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		List<Position> posList=positionService.getAll();		
		Position pos=new Position();
		pos.setPosId(new Long(0));
		pos.setPosName("全部");
		pos.setPosDesc("岗位");
		posList.add(pos);
		return posList;
	}
	
	/**
	 * 组织对话框的展示
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		QueryFilter filter = new QueryFilter(request, "positionItem", true);
		String type = RequestUtil.getString(request, "type");
		String typeVal = RequestUtil.getString(request, "typeVal");
		Long orgId = RequestUtil.getLong(request, "orgId");
		SysOrg sysOrgTemp = sysOrgService.getById(orgId);
		if (BeanUtils.isEmpty(sysOrgTemp)) {
			SysOrg sysOrg =  orgServiceImpl.getSysOrgByScope(type, typeVal);
			Map<String, Object> filters = filter.getFilters();
			filters.remove("orgId");
			filter.setFilters(filters);
			filter.addFilter("orgPath", StringUtil.isNotEmpty(sysOrg.getPath()) ? (sysOrg.getPath() + "%") : ("%."+sysOrg.getOrgId()+".%"));
		}
		filter.addFilter("nodekey", Job.NODE_KEY);
		List<Position> positionList=positionService.getBySupOrgId(filter);
		
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		ModelAndView mv=this.getAutoView()
				.addObject("positionList",positionList) 
				.addObject("type",type)
				.addObject("typeVal",typeVal)
				.addObject("isSingle", isSingle);
		return mv;

	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBySupOrgId")
	@ResponseBody
	@Action(description="获取岗位信息",
			execOrder=ActionExecOrder.AFTER,
			detail="获取岗位信息",
			exectype="管理日志")
	public List<Position> getBySupOrgId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "positionItem", true);
		String type = RequestUtil.getString(request,"type");
		String typeVal = RequestUtil.getString(request,"typeVal");
		SysOrg sysOrg= orgServiceImpl.getSysOrgByScope(type, typeVal);
		filter.addFilter("orgPath", StringUtil.isNotEmpty(sysOrg.getPath()) ? (sysOrg.getPath() + "%") : ("%."+sysOrg.getOrgId()+".%"));
		List<Position> list = positionService.getBySupOrgId(filter);
		return list;
	}

	
	
	/**
	 * 取得系统角色表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAll")
	@ResponseBody
	@Action(description="获取角色信息",
			execOrder=ActionExecOrder.AFTER,
			detail="获取角色信息",
			exectype="管理日志")
	public List<Position> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Position> list = positionService.getAll(new QueryFilter(request, false));
		return list;
	}
	
	
	/**
	 * 取得系统岗位表明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByPosId")
	public ModelAndView getByPosId(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mv=new ModelAndView("/platform/system/positionGet.jsp");
		return getView(request, response, mv, 1);
	}
	
	
	/**
	 * 取得系统岗位表明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Action(description="查看系统岗位表明细")
	public ModelAndView getView(HttpServletRequest request, HttpServletResponse response,ModelAndView mv ,int isOtherLink) throws Exception
	{
		long id=RequestUtil.getLong(request,"posId");
		String canReturn = RequestUtil.getString(request, "canReturn","0");
		Position position = positionService.getById(id);	
		if (BeanUtils.isNotEmpty(position)) {
			SysOrg sysOrg = sysOrgService.getById(position.getOrgId());
			Job job = jobService.getById(position.getJobId());
			position.setOrgName(sysOrg.getOrgName());
			position.setJobName(job.getJobname());
		}
		return mv.addObject("position", position).addObject("isOtherLink", isOtherLink).addObject("canReturn", canReturn);
	}
	
	@RequestMapping("dialog")
	@Action(description = "岗位对话框", 
			execOrder=ActionExecOrder.AFTER,
			detail="岗位对话框",
			exectype = "管理日志")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Demension> demensionList = demensionService.getAll();
		ModelAndView mv = this.getAutoView().addObject("demensionList", demensionList);

		return mv;
	}
}

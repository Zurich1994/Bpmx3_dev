package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.ResultMessage;

import com.hotent.platform.model.system.Dictionary;

import com.hotent.platform.model.system.Job;
import com.hotent.platform.service.system.DictionaryService;
import com.hotent.platform.service.system.JobService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:职务表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-28 16:17:48
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/job/")
public class JobController extends BaseController
{
	@Resource
	private JobService jobService;
	//xinjia
	@Resource
	private DictionaryService dictionaryService;
	//xinjia
	/**
	 * 添加或更新职务表。
	 * @param request
	 * @param response
	 * @param job 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新职务表")
	public void save(HttpServletRequest request, HttpServletResponse response,Job job) throws Exception
	{
		String resultMsg=null;		
		try{
			boolean isExistJobName=false;
			if(job.getJobid()==null||job.getJobid()==0){
				job.setJobid(UniqueIdUtil.genId());
				 isExistJobName=jobService.isExistJobCode(job.getJobcode());
				if (!isExistJobName) {
					jobService.add(job);
					resultMsg="添加职务成功";
				}else {
					resultMsg="职务名称已经存在！";
					
				}
				
			}else{
				isExistJobName=jobService.isExistJobCodeForUpd(job.getJobcode(), job.getJobid());
				if(isExistJobName){
					resultMsg="职务名称已经存在！";
				}
				else{
					jobService.update(job);
					resultMsg="更新职务成功";
				}
			}
			if (isExistJobName) {
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
			}else{
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			}
		}
		catch(DuplicateKeyException ex){
			writeResultMessage(response.getWriter(),"该职务代码已存在.",ResultMessage.Fail);
		}
		catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得职务表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看职务表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Job> list=jobService.getAll(new QueryFilter(request,"jobItem"));
		ModelAndView mv=this.getAutoView().addObject("jobList",list);
		
		return mv;
	}
	
	/**
	 * 删除职务表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除职务表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			//非物理删除，修改删除标志
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "jobid");
			//jobService.delByIds(lAryId);
			//循环修改删除标志
			for(int i=0,n=lAryId.length;i<n;i++){
				jobService.deleteByUpdateFlag(lAryId[i]);
			}
			message=new ResultMessage(ResultMessage.Success, "删除职务表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑职务表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑职务表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long jobid=RequestUtil.getLong(request,"jobid",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		Job job=jobService.getById(jobid);
		List<Dictionary> dicList = dictionaryService.getByNodeKey(Job.NODE_KEY);
		
		return getAutoView().addObject("job",job)
							.addObject("returnUrl",returnUrl)
							.addObject("dicList", dicList);
	}

	/**
	 * 取得职务表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看职务表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long jobid=RequestUtil.getLong(request,"jobid");
		Job job = jobService.getById(jobid);	
		return getAutoView().addObject("job", job);
	}
	
	
	/**
	 * 角色对话框的展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description="角色对话框显示",
			execOrder=ActionExecOrder.AFTER,
			detail="角色对话框显示",
			exectype = "管理日志")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isSingle =RequestUtil.getString(request, "isSingle");
		return getAutoView().addObject("isSingle",isSingle);
		
	}
	
	/**
	 * 角色对话框的展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="角色对话框显示",
			execOrder=ActionExecOrder.AFTER,
			detail="角色对话框显示",
			exectype = "管理日志")
	public ModelAndView selector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		QueryFilter queryFilter = new QueryFilter(request, "jobItem");
		String jobName = RequestUtil.getString(request, "Q_jobname_S");
		if(StringUtil.isNotEmpty(jobName)){
			queryFilter.addFilter("jobname","%"+ jobName+"%");
		}
		List<Job> list = jobService.getAll(queryFilter);
		ModelAndView mv = this.getAutoView()
				.addObject("jobList", list)
				.addObject("isSingle", isSingle);
		return mv;
	}
	
}

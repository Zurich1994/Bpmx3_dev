
package com.hotent.projectinfo.controller.projectinfo;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.projectinfo.model.projectinfo.projectinfo;
import com.hotent.projectinfo.service.projectinfo.projectinfoService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:projectInfo 控制器类
 */
@Controller
@RequestMapping("/projectinfo/projectinfo/projectinfo/")
public class projectinfoController extends BaseController
{
	@Resource
	private projectinfoService projectinfoService;
	/**
	 * 添加或更新projectInfo。
	 * @param request
	 * @param response
	 * @param projectinfo 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新projectInfo")
	public void save(HttpServletRequest request, HttpServletResponse response,projectinfo projectinfo) throws Exception
	{
		String resultMsg=null;
		try{
			if(projectinfo.getId()==null){
				Long id=UniqueIdUtil.genId();
				projectinfo.setId(id);
				projectinfoService.add(projectinfo);
				resultMsg=getText("添加","projectInfo");
			}else{
			    projectinfoService.update(projectinfo);
				resultMsg=getText("更新","projectInfo");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得projectInfo分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看projectInfo分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<projectinfo> list=projectinfoService.getAll(new QueryFilter(request,"projectinfoItem"));
		ModelAndView mv=this.getAutoView().addObject("projectinfoList",list);
		
		return mv;
	}
	
	/**
	 * 删除projectInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除projectInfo")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			projectinfoService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除projectInfo成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑projectInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑projectInfo")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		projectinfo projectinfo=projectinfoService.getById(id);
		
		return getAutoView().addObject("projectinfo",projectinfo)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得projectInfo明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看projectInfo明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		projectinfo projectinfo=projectinfoService.getById(id);
		return getAutoView().addObject("projectinfo", projectinfo);
	}
	
	/**
	 * 保存Flex项目信息
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveProject")
	@Action(description="保存Flex项目信息")
	public ModelAndView saveProject(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		projectinfo projectinfo=projectinfoService.getById(id);
		String pxml = RequestUtil.getString(request, "projectXml");
		System.out.println("保存Xml："+pxml);
		projectinfo.setProjectXml(pxml);
		PrintWriter out = response.getWriter();
		out.write("保存成功");
	    projectinfoService.update(projectinfo);
		return null;
	}
	
	/**
	 * 获取Flex项目信息
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getProject")
	@Action(description="获取Flex项目信息")
	public ModelAndView getProject(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		System.out.println("getProject:"+id);
		projectinfo projectinfo=projectinfoService.getById(id);
		String pxml = projectinfo.getProjectXml();
		System.out.println("获取xml:"+pxml);
		PrintWriter out = response.getWriter();
		out.write(pxml);
		return null;
	}
	
	/**
	 * 进入项目
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design")
	@Action(description="进入项目")
	public ModelAndView design(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		System.out.println("进入项目："+id);
		request.setAttribute("id", id);
		request.setAttribute("xmlRecord", "lasdjfsdjlkjfkl;sajflk;ajfdkl;jfdkl;f");
		String returnUrl=RequestUtil.getPrePage(request);
		projectinfo projectinfo=projectinfoService.getById(id);
		return getAutoView().addObject("projectinfo",projectinfo)
							.addObject("returnUrl",returnUrl);
	}	
}

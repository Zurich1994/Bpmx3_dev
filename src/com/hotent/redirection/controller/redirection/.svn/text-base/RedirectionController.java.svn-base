
package com.hotent.redirection.controller.redirection;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.redirection.model.redirection.Redirection;
import com.hotent.redirection.service.redirection.RedirectionService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:redirection 控制器类
 */
@Controller
@RequestMapping("/redirection/redirection/redirection/")
public class RedirectionController extends BaseController
{
	@Resource
	private RedirectionService redirectionService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	/**
	 * 添加或更新redirection。
	 * @param request
	 * @param response
	 * @param redirection 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新redirection")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;	
		Redirection redirection=new Redirection();
		Long defid = RequestUtil.getLong(request, "defid");
		System.out.println("defid:"+defid);
		redirection.setDefid(defid);
		String defid_str = defid+"";
		
		redirection.setNodeid(RequestUtil.getString(request, "nodeid"));
		String nodeid_str = RequestUtil.getString(request, "nodeid");
		System.out.println("nodeid:"+RequestUtil.getString(request, "nodeid"));
		redirection.setRedirectionurl(RequestUtil.getString(request, "redirectionurl"));
		System.out.println("redirectionurl:"+RequestUtil.getString(request, "redirectionurl"));
		//Redirection redirectionurl=redirectionService.search(redirection);
		
		int result_id = redirectionService.getByTwo(defid_str,nodeid_str);
		try{
			if(result_id == -1){
				redirectionService.add(redirection);
				resultMsg=getText("添加","redirection");	
			}
			else{
				redirectionService.update(redirection);
				resultMsg=getText("更新","redirectionurl");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得redirection分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看redirection分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Redirection> list=redirectionService.getAll(new QueryFilter(request,"redirectionItem"));
		ModelAndView mv=this.getAutoView().addObject("redirectionList",list);
		
		return mv;
	}
	
	/**
	 * 删除redirection
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除redirection")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			redirectionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除redirection成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑redirection
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑redirection")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String defID = RequestUtil.getLong(request, "defId")+"";
		String nodeID = RequestUtil.getString(request, "nodeId")+"";
		System.out.println("intoContoller:DefId:"+defID+",nodeID:"+nodeID);
//		Redirection redirection = new Redirection();
//		redirection.setDefid(RequestUtil.getLong(request, "defId"));
//		redirection.setNodeid(RequestUtil.getString(request, "nodeId"));
//		String returnUrl=RequestUtil.getPrePage(request);
//		System.out.println("returnUrl:"+returnUrl);
		Redirection redir = redirectionService.getByactDefId(defID,nodeID);
		if(redir==null)
		{
			Redirection redirs = new Redirection();
			redirs.setDefid(Long.parseLong(defID));
			redirs.setNodeid(nodeID);
			return getAutoView().addObject("redir",redirs);
		}
		String redirect = redir.getRedirectionurl();
		BpmFormDef bpmform = bpmFormDefService.getByFormKey(redirect).get(0);
		System.out.println(bpmform.getSubject());
		return getAutoView().addObject("bpmform",bpmform)
							.addObject("redir",redir);
							//.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得redirection明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看redirection明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long defid=RequestUtil.getLong(request,"id");
		Redirection redirection=redirectionService.getById(defid);
		return getAutoView().addObject("redirection", redirection);
	}
	
	/**
	 * 取得redirection分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addresource")
	@Action(description="跳入redirectionaddresource.jsp")
	public ModelAndView addresource(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.getAutoView();	
		return mv;
	}
	
}

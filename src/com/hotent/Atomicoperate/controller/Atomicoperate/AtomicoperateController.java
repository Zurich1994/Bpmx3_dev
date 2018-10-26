

package com.hotent.Atomicoperate.controller.Atomicoperate;

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
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.Atomicoperate.model.Atomicoperate.Atomicoperate;
import com.hotent.Atomicoperate.service.Atomicoperate.AtomicoperateService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:原子操作表 控制器类
 */
@Controller
@RequestMapping("/Atomicoperate/Atomicoperate/atomicoperate/")
public class AtomicoperateController extends BaseController
{
	@Resource
	private AtomicoperateService atomicoperateService;
	
	/**
	 * 添加或更新原子操作表。
	 * @param request
	 * @param response
	 * @param atomicoperate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新原子操作表")
	public void save(HttpServletRequest request, HttpServletResponse response,Atomicoperate atomicoperate) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atomicoperate.getId()==null){
				atomicoperateService.save(atomicoperate);
				resultMsg=getText("添加","原子操作表");
			}else{
			    atomicoperateService.save(atomicoperate);
				resultMsg=getText("更新","原子操作表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得原子操作表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看原子操作表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long status= Long.parseLong(request.getParameter("status"));
		Long serviceId= Long.parseLong(request.getParameter("id"));
		ModelAndView mv=null;
		if(status==1)
		{
			List<Atomicoperate> list=atomicoperateService.list(serviceId);
			mv=this.getAutoView().addObject("atomicoperateList",list);
		}
		else if(status==2){
			 List<Atomicoperate> list=atomicoperateService.getAll(new QueryFilter(request,"atomicoperateItem"));
			 mv=this.getAutoView().addObject("atomicoperateList",list);
		}
		return mv;
	}
	
	/**
	 * 删除原子操作表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除原子操作表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			atomicoperateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除原子操作表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑原子操作表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑原子操作表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		Atomicoperate atomicoperate=atomicoperateService.getById(id);
		
		return getAutoView().addObject("atomicoperate",atomicoperate)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得原子操作表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看原子操作表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Atomicoperate atomicoperate=atomicoperateService.getById(id);
		return getAutoView().addObject("atomicoperate", atomicoperate);
	}
}
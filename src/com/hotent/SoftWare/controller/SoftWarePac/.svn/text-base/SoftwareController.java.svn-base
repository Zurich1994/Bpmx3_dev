
package com.hotent.SoftWare.controller.SoftWarePac;

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

import com.hotent.SoftWare.model.SoftWarePac.Software;
import com.hotent.SoftWare.service.SoftWarePac.SoftwareService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:软件表 控制器类
 */
@Controller
@RequestMapping("/SoftWare/SoftWarePac/software/")
public class SoftwareController extends BaseController
{
	@Resource
	private SoftwareService softwareService;
	/**
	 * 添加或更新软件表。
	 * @param request
	 * @param response
	 * @param software 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新软件表")
	public void save(HttpServletRequest request, HttpServletResponse response,Software software) throws Exception
	{
		String resultMsg=null;		
		try{
			if(software.getId()==null){
				Long id=UniqueIdUtil.genId();
				software.setId(id);
				softwareService.add(software);
				resultMsg=getText("添加","软件表");
			}else{
			    softwareService.update(software);
				resultMsg=getText("更新","软件表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得软件表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看软件表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Software> list=softwareService.getAll(new QueryFilter(request,"softwareItem"));
		ModelAndView mv=this.getAutoView().addObject("softwareList",list);
		
		return mv;
	}
	
	/**
	 * 删除软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除软件表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			softwareService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除软件表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑软件表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑软件表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Software software=softwareService.getById(id);
		
		return getAutoView().addObject("software",software)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得软件表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看软件表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Software software=softwareService.getById(id);
		return getAutoView().addObject("software", software);
	}
	
}


package com.hotent.BpmFormBang.controller.bpmFormBang;

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

import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;
import com.hotent.BpmFormBang.service.bpmFormBang.BpmFormBangService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:绑定表 控制器类
 */
@Controller
@RequestMapping("/BpmFormBang/bpmFormBang/bpmFormBang/")
public class BpmFormBangController extends BaseController
{
	@Resource
	private BpmFormBangService bpmFormBangService;
	/**
	 * 添加或更新绑定表。
	 * @param request
	 * @param response
	 * @param bpmFormBang 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新绑定表")
	public void save(HttpServletRequest request, HttpServletResponse response,BpmFormBang bpmFormBang) throws Exception
	{
		String resultMsg=null;		
		try{
			if(bpmFormBang.getId()==null){
				Long id=UniqueIdUtil.genId();
				bpmFormBang.setId(id);
				bpmFormBangService.add(bpmFormBang);
				resultMsg=getText("添加","绑定表");
			}else{
			    bpmFormBangService.update(bpmFormBang);
				resultMsg=getText("更新","绑定表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得绑定表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看绑定表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmFormBang> list=bpmFormBangService.getAll(new QueryFilter(request,"bpmFormBangItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmFormBangList",list);
		
		return mv;
	}
	
	/**
	 * 删除绑定表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除绑定表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			bpmFormBangService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除绑定表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑绑定表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑绑定表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		BpmFormBang bpmFormBang=bpmFormBangService.getById(id);
		
		return getAutoView().addObject("bpmFormBang",bpmFormBang)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得绑定表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看绑定表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BpmFormBang bpmFormBang=bpmFormBangService.getById(id);
		return getAutoView().addObject("bpmFormBang", bpmFormBang);
	}
	
}

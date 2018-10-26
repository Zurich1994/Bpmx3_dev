
package com.hotent.kvmSet.LineActionShowPath.controller.LineActionShowCode;

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

import com.hotent.LineActionShowPath.model.LineActionShowCode.LineActionShow;
import com.hotent.LineActionShowPath.service.LineActionShowCode.LineActionShowService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:线路动作展现表 控制器类
 */
@Controller
@RequestMapping("/LineActionShowPath/LineActionShowCode/lineActionShow/")
public class LineActionShowController extends BaseController
{
	@Resource
	private LineActionShowService lineActionShowService;
	/**
	 * 添加或更新线路动作展现表。
	 * @param request
	 * @param response
	 * @param lineActionShow 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新线路动作展现表")
	public void save(HttpServletRequest request, HttpServletResponse response,LineActionShow lineActionShow) throws Exception
	{
		String resultMsg=null;		
		try{
			if(lineActionShow.getId()==null){
				Long id=UniqueIdUtil.genId();
				lineActionShow.setId(id);
				lineActionShowService.add(lineActionShow);
				resultMsg=getText("添加","线路动作展现表");
			}else{
			    lineActionShowService.update(lineActionShow);
				resultMsg=getText("更新","线路动作展现表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得线路动作展现表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看线路动作展现表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<LineActionShow> list=lineActionShowService.getAll(new QueryFilter(request,"lineActionShowItem"));
		ModelAndView mv=this.getAutoView().addObject("lineActionShowList",list);
		
		return mv;
	}
	
	/**
	 * 删除线路动作展现表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除线路动作展现表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			lineActionShowService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除线路动作展现表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑线路动作展现表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑线路动作展现表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		LineActionShow lineActionShow=lineActionShowService.getById(id);
		
		return getAutoView().addObject("lineActionShow",lineActionShow)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得线路动作展现表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看线路动作展现表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		LineActionShow lineActionShow=lineActionShowService.getById(id);
		return getAutoView().addObject("lineActionShow", lineActionShow);
	}
	
}


package com.hotent.TbaActivityInfoPath.controller.TbaActivityInfoCode;

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

import com.hotent.TbaActivityInfoPath.model.TbaActivityInfoCode.TbaActivityInfo;
import com.hotent.TbaActivityInfoPath.service.TbaActivityInfoCode.TbaActivityInfoService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:活动操作信息表 控制器类
 */
@Controller
@RequestMapping("/TbaActivityInfoPath/TbaActivityInfoCode/tbaActivityInfo/")
public class TbaActivityInfoController extends BaseController
{
	@Resource
	private TbaActivityInfoService tbaActivityInfoService;
	/**
	 * 添加或更新活动操作信息表。
	 * @param request
	 * @param response
	 * @param tbaActivityInfo 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新活动操作信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,TbaActivityInfo tbaActivityInfo) throws Exception
	{
		String resultMsg=null;		
		try{
			if(tbaActivityInfo.getId()==null){
				Long id=UniqueIdUtil.genId();
				tbaActivityInfo.setId(id);
				tbaActivityInfoService.add(tbaActivityInfo);
				resultMsg=getText("添加","活动操作信息表");
			}else{
			    tbaActivityInfoService.update(tbaActivityInfo);
				resultMsg=getText("更新","活动操作信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得活动操作信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看活动操作信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<TbaActivityInfo> list=tbaActivityInfoService.getAll(new QueryFilter(request,"tbaActivityInfoItem"));
		ModelAndView mv=this.getAutoView().addObject("tbaActivityInfoList",list);
		
		return mv;
	}
	
	/**
	 * 删除活动操作信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除活动操作信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			tbaActivityInfoService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除活动操作信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑活动操作信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑活动操作信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		TbaActivityInfo tbaActivityInfo=tbaActivityInfoService.getById(id);
		
		return getAutoView().addObject("tbaActivityInfo",tbaActivityInfo)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得活动操作信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看活动操作信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		TbaActivityInfo tbaActivityInfo=tbaActivityInfoService.getById(id);
		return getAutoView().addObject("tbaActivityInfo", tbaActivityInfo);
	}
	
}

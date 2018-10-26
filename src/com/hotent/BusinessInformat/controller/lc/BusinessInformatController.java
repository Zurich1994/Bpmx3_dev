
package com.hotent.BusinessInformat.controller.lc;

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

import com.hotent.BusinessInformat.model.lc.BusinessInformat;
import com.hotent.BusinessInformat.service.lc.BusinessInformatService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:业务发生量信息表 控制器类
 */
@Controller
@RequestMapping("/BusinessInformat/lc/businessInformatLc/")
public class BusinessInformatController extends BaseController
{
	@Resource
	private BusinessInformatService businessInformatLcService;
	/**
	 * 添加或更新业务发生量信息表。
	 * @param request
	 * @param response
	 * @param businessInformatLc 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务发生量信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,BusinessInformat businessInformatLc) throws Exception
	{
		String resultMsg=null;		
		try{
			if(businessInformatLc.getId()==null){
				Long id=UniqueIdUtil.genId();
				businessInformatLc.setId(id);
				businessInformatLcService.add(businessInformatLc);
				resultMsg=getText("添加","业务发生量信息表");
			}else{
			    businessInformatLcService.update(businessInformatLc);
				resultMsg=getText("更新","业务发生量信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得业务发生量信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务发生量信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BusinessInformat> list=businessInformatLcService.getAll(new QueryFilter(request,"businessInformatLcItem"));
		ModelAndView mv=this.getAutoView().addObject("businessInformatLcList",list);
		
		return mv;
	}
	
	/**
	 * 删除业务发生量信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务发生量信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			businessInformatLcService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除业务发生量信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务发生量信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务发生量信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		BusinessInformat businessInformatLc=businessInformatLcService.getById(id);
		
		return getAutoView().addObject("businessInformatLc",businessInformatLc)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得业务发生量信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务发生量信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BusinessInformat businessInformatLc=businessInformatLcService.getById(id);
		return getAutoView().addObject("businessInformatLc", businessInformatLc);
	}
	
}

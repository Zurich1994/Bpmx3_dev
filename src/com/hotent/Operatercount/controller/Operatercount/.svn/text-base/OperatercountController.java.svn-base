

package com.hotent.Operatercount.controller.Operatercount;

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

import com.hotent.Operatercount.model.Operatercount.Operatercount;
import com.hotent.Operatercount.service.Operatercount.OperatercountService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:操作运行次数 控制器类
 */
@Controller
@RequestMapping("/Operatercount/Operatercount/operatercount/")
public class OperatercountController extends BaseController
{
	@Resource
	private OperatercountService operatercountService;
	
	/**
	 * 添加或更新操作运行次数。
	 * @param request
	 * @param response
	 * @param operatercount 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新操作运行次数")
	public void save(HttpServletRequest request, HttpServletResponse response,Operatercount operatercount) throws Exception
	{
		String resultMsg=null;		
		try{
			if(operatercount.getId()==null){
				operatercountService.save(operatercount);
				resultMsg=getText("添加","操作运行次数");
			}else{
			    operatercountService.save(operatercount);
				resultMsg=getText("更新","操作运行次数");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得操作运行次数分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看操作运行次数分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Operatercount> list=operatercountService.getAll(new QueryFilter(request,"operatercountItem"));
		ModelAndView mv=this.getAutoView().addObject("operatercountList",list);
		return mv;
	}
	
	/**
	 * 删除操作运行次数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除操作运行次数")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			operatercountService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除操作运行次数成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑操作运行次数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑操作运行次数")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Operatercount operatercount=operatercountService.getById(id);
		
		return getAutoView().addObject("operatercount",operatercount)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得操作运行次数明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看操作运行次数明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Operatercount operatercount=operatercountService.getById(id);
		return getAutoView().addObject("operatercount", operatercount);
	}
	
}
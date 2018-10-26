
package com.hotent.banking.controller.banking;

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

import com.hotent.banking.model.banking.add_payee;
import com.hotent.banking.service.banking.add_payeeService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:支付者信息表 控制器类
 */
@Controller
@RequestMapping("/banking/banking/add_payee/")
public class add_payeeController extends BaseController
{
	@Resource
	private add_payeeService add_payeeService;
	/**
	 * 添加或更新支付者信息表。
	 * @param request
	 * @param response
	 * @param add_payee 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加支付者信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,add_payee add_payee) throws Exception
	{
		String USERID = add_payee.getUSERID();
		String resultMsg=null;	
		add_payee add = null ;
		int state = ResultMessage.Fail;                      //定义操作返回值，1-SUCCESS 成功  0-Fail 失败
		try{
			if(add_payee.getId()==null){
				Long id=UniqueIdUtil.genId();	    
				add_payee.setId(id);		
				add = add_payeeService.getByUSERID(USERID);  //判断添加用户是否存在
				if(add==null){
					add_payeeService.add(add_payee);
					state = ResultMessage.Success;
					resultMsg=getText("添加","支付者信息表");
				}			
			}
			writeResultMessage(response.getWriter(),resultMsg,state);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得支付者信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看支付者信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<add_payee> list=add_payeeService.getAll(new QueryFilter(request,"add_payeeItem"));
		ModelAndView mv=this.getAutoView().addObject("add_payeeList",list);
		
		return mv;
	}
	
	/**
	 * 删除支付者信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除支付者信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			add_payeeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除支付者信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑支付者信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑支付者信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{		
		return getAutoView();
	}

	/**
	 * 取得支付者信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看支付者信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		add_payee add_payee=add_payeeService.getById(id);
		return getAutoView().addObject("add_payee", add_payee);
	}
	/**
	 * 添加结果查询
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("result")
	@Action(description="添加结果查询")
	public ModelAndView result(HttpServletRequest request) throws Exception
	{
		String Str=RequestUtil.getString(request,"Str");
		
		return getAutoView().addObject("Str", Str);
	}
	/**
	 * 	添加支付者信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("add")
	@Action(description="添加支付者信息表")
	public ModelAndView add(HttpServletRequest request) throws Exception
	{	
		String name=RequestUtil.getString(request,"name");
		
		return getAutoView().addObject("name", name);
	}
}

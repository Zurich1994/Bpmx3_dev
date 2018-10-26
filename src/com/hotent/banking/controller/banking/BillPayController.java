
package com.hotent.banking.controller.banking;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.banking.model.banking.BillPay;
import com.hotent.banking.service.banking.BillPayService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:用户ID 控制器类
 */
@Controller
@RequestMapping("/banking/banking/billPay/")
public class BillPayController extends BaseController
{
	@Resource
	private BillPayService billPayService;
    private String userid ;
	/**
	 * 添加或更新用户ID。
	 * @param request
	 * @param response
	 * @param billPay 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户ID")
	public void save(HttpServletRequest request, HttpServletResponse response,BillPay billPay) throws Exception
	{
		String resultMsg=null;		
		try{
			if(billPay.getId()==null){
				Long id=UniqueIdUtil.genId();
				billPay.setId(id);
				billPayService.add(billPay);
				resultMsg=getText("添加","用户ID");
			}else{
			    billPayService.update(billPay);
				resultMsg=getText("更新","用户ID");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得用户ID分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看用户ID分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		//String a="aa";
		//Long CurrentUserId=ContextUtil.getCurrentUserId();
		// = CurrentUserId.toString();
		//userId=（String）CurrentUserId;
		
		userid =request.getParameter("name");
		System.out.println(userid);
		List<BillPay> list=billPayService.getUSERID(userid);
		//List<BillPay> list=billPayService.getAll(new QueryFilter(request,"billPayItem"));
		//List<BillPay> list=billPayService.getList(a, null);
		ModelAndView mv=this.getAutoView().addObject("billPayList",list);
		
		return mv;
	} 
	
	/**
	 * 删除用户ID
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除用户ID")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			billPayService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除用户ID成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
		HttpSession session = request.getSession();
		session.setAttribute("abc",new Integer(567));
	}

	
	/**
	 * 	编辑用户ID
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑用户ID")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		BillPay billPay=billPayService.getById(id);
		
		return getAutoView().addObject("billPay",billPay)
		.addObject("userid",userid)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得用户ID明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看用户ID明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BillPay billPay=billPayService.getById(id);
		return getAutoView().addObject("billPay", billPay);
	}
	

	@RequestMapping("getdate")
	@Action(description="得到信息")
	public ModelAndView getdate(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String start_time_text=RequestUtil.getString(request,"start_time_text");
		String end_time_text=RequestUtil.getString(request,"end_time_text");
		
		List<BillPay>list=billPayService.getDate(start_time_text, end_time_text);
		ModelAndView mv = new ModelAndView("/banking/banking/billPayList.jsp");{
		return mv.addObject("billPayList", list);

	}
}
}

package com.hotent.tpcc.controller.tpcc;

import java.util.Date;
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

import com.hotent.tpcc.model.tpcc.History2;
import com.hotent.tpcc.service.tpcc.History2Service;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:history2 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/history2/")
public class History2Controller extends BaseController
{
	@Resource
	private History2Service history2Service;
	/**
	 * 添加或更新history2。
	 * @param request
	 * @param response
	 * @param history2 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新history2")
	public void save(HttpServletRequest request, HttpServletResponse response,History2 history2) throws Exception
	{
		String resultMsg=null;		
		try{
			if(history2.getId()==null){
				Long id=UniqueIdUtil.genId();
				history2.setId(id);
				history2Service.add(history2);
				resultMsg=getText("添加","history2");
			}else{
			    history2Service.update(history2);
				resultMsg=getText("更新","history2");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得history2分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看history2分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
//		History2 history=new History2(1L,1L,1L,1L,1L,new Date(),1L,"asdf");
		//History2 history=new History2();
		History2 history=new History2();
		history.setH_w_id(1L);
		history.setH_c_d_id(1L);
		history.setH_c_id(1L);
		history.setH_c_w_id(1L);
		history.setH_data("hello");
		Date tempDate=new Date();
		history.setH_date(tempDate);
		history.setH_date(new Date());
		history.setH_d_id(1L);
		history.setH_amount(1L);
	
		//history2Service.add(history);
		//history2Service.add(history);
		history2Service.addHistory(history);
		List<History2> list=history2Service.getAll(new QueryFilter(request,"history2Item"));
		ModelAndView mv=this.getAutoView().addObject("history2List",list);
		return mv;
	}
	
	/**
	 * 删除history2
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除history2")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			history2Service.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除history2成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑history2
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑history2")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		History2 history2=history2Service.getById(id);
		
		return getAutoView().addObject("history2",history2)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得history2明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看history2明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		History2 history2=history2Service.getById(id);
		return getAutoView().addObject("history2", history2);
	}
	
}

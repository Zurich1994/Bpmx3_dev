
package com.hotent.banking.controller.banking;

import java.util.ArrayList;
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

import com.hotent.banking.model.banking.update;
import com.hotent.banking.service.banking.updateService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:更新账户信息 控制器类
 */
@Controller
@RequestMapping("/banking/banking/update/")
public class updateController extends BaseController
{
	@Resource
	private updateService updateService;
	String name ="";
	/**
	 * 添加或更新更新账户信息。
	 * @param request
	 * @param response
	 * @param update 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新更新账户信息")
	public void save(HttpServletRequest request, HttpServletResponse response,update update) throws Exception
	{
		String resultMsg=null;		
		try{
			if(update.getId()==null){
				Long id=UniqueIdUtil.genId();
				update.setId(id);
				updateService.add(update);
				resultMsg=getText("添加","更新账户信息");
			}else{
			    updateService.update(update);
				resultMsg=getText("更新","更新账户信息");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得更新账户信息分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看更新账户信息分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<update> list=updateService.getAll(new QueryFilter(request,"updateItem"));
		List<update> lists=new ArrayList<update>();
	name = RequestUtil.getString(request,"name");
		
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getUSERID().equals(name)){
				lists.add(list.get(i));
			}
		}

		ModelAndView mv=this.getAutoView()
		.addObject("updateList",lists);

	
		return mv;
	}
	
	/**
	 * 删除更新账户信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除更新账户信息")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			updateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除更新账户信息成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑更新账户信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑更新账户信息")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		update update=updateService.getById(id);
		
		return getAutoView().addObject("update",update)
		                    .addObject("name", name)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得更新账户信息明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看更新账户信息明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		update update=updateService.getById(id);
		return getAutoView().addObject("update", update);
	}
	
}

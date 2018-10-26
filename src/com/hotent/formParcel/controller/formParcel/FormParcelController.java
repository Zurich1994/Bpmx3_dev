
package com.hotent.formParcel.controller.formParcel;

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

import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:表单数据包对应关系表 控制器类
 */
@Controller
@RequestMapping("/formParcel/formParcel/formParcel/")
public class FormParcelController extends BaseController
{
	@Resource
	private FormParcelService formParcelService;
	/**
	 * 添加或更新表单数据包对应关系表。
	 * @param request
	 * @param response
	 * @param formParcel 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新表单数据包对应关系表")
	public void save(HttpServletRequest request, HttpServletResponse response,FormParcel formParcel) throws Exception
	{
		String resultMsg=null;		
		try{
			if(formParcel.getId()==null){
				Long id=UniqueIdUtil.genId();
				formParcel.setId(id);
				formParcelService.add(formParcel);
				resultMsg=getText("添加","表单数据包对应关系表");
			}else{
			    formParcelService.update(formParcel);
				resultMsg=getText("更新","表单数据包对应关系表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得表单数据包对应关系表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看表单数据包对应关系表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<FormParcel> list=formParcelService.getAll(new QueryFilter(request,"formParcelItem"));
		ModelAndView mv=this.getAutoView().addObject("formParcelList",list);
		
		return mv;
	}
	
	/**
	 * 删除表单数据包对应关系表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除表单数据包对应关系表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			formParcelService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除表单数据包对应关系表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑表单数据包对应关系表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑表单数据包对应关系表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		FormParcel formParcel=formParcelService.getById(id);
		
		return getAutoView().addObject("formParcel",formParcel)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得表单数据包对应关系表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看表单数据包对应关系表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		FormParcel formParcel=formParcelService.getById(id);
		return getAutoView().addObject("formParcel", formParcel);
	}
	
}

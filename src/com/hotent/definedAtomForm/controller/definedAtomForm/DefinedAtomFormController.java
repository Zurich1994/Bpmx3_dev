
package com.hotent.definedAtomForm.controller.definedAtomForm;

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

import com.hotent.definedAtomForm.model.definedAtomForm.DefinedAtomForm;
import com.hotent.definedAtomForm.service.definedAtomForm.DefinedAtomFormService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:defined_atom_form 控制器类
 */
@Controller
@RequestMapping("/definedAtomForm/definedAtomForm/definedAtomForm/")
public class DefinedAtomFormController extends BaseController
{
	@Resource
	private DefinedAtomFormService definedAtomFormService;
	/**
	 * 添加或更新defined_atom_form。
	 * @param request
	 * @param response
	 * @param definedAtomForm 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新defined_atom_form")
	public void save(HttpServletRequest request, HttpServletResponse response,DefinedAtomForm definedAtomForm) throws Exception
	{
		String resultMsg=null;		
		try{
			if(definedAtomForm.getId()==null){
				Long id=UniqueIdUtil.genId();
				definedAtomForm.setId(id);
				definedAtomFormService.add(definedAtomForm);
				resultMsg=getText("添加","defined_atom_form");
			}else{
			    definedAtomFormService.update(definedAtomForm);
				resultMsg=getText("更新","defined_atom_form");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得defined_atom_form分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看defined_atom_form分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DefinedAtomForm> list=definedAtomFormService.getAll(new QueryFilter(request,"definedAtomFormItem"));
		ModelAndView mv=this.getAutoView().addObject("definedAtomFormList",list);
		
		return mv;
	}
	
	/**
	 * 删除defined_atom_form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除defined_atom_form")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			definedAtomFormService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除defined_atom_form成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑defined_atom_form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑defined_atom_form")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DefinedAtomForm definedAtomForm=definedAtomFormService.getById(id);
		
		return getAutoView().addObject("definedAtomForm",definedAtomForm)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得defined_atom_form明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看defined_atom_form明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DefinedAtomForm definedAtomForm=definedAtomFormService.getById(id);
		return getAutoView().addObject("definedAtomForm", definedAtomForm);
	}
	
}

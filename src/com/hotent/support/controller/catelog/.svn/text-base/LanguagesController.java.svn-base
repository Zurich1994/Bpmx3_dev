
package com.hotent.support.controller.catelog;

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

import com.hotent.support.model.catelog.Languages;
import com.hotent.support.service.catelog.LanguagesService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:Support语言表 控制器类
 */
@Controller
@RequestMapping("/support/catelog/languages/")
public class LanguagesController extends BaseController
{
	@Resource
	private LanguagesService languagesService;
	/**
	 * 添加或更新Support语言表。
	 * @param request
	 * @param response
	 * @param languages 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新Support语言表")
	public void save(HttpServletRequest request, HttpServletResponse response,Languages languages) throws Exception
	{
		String resultMsg=null;		
		try{
			if(languages.getId()==null){
				Long id=UniqueIdUtil.genId();
				languages.setId(id);
				languagesService.add(languages);
				resultMsg=getText("添加","Support语言表");
			}else{
			    languagesService.update(languages);
				resultMsg=getText("更新","Support语言表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得Support语言表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看Support语言表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Languages> list=languagesService.getAll(new QueryFilter(request,"languagesItem"));
		ModelAndView mv=this.getAutoView().addObject("languagesList",list);
		
		return mv;
	}
	
	/**
	 * 删除Support语言表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除Support语言表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			languagesService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除Support语言表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑Support语言表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑Support语言表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Languages languages=languagesService.getById(id);
		
		return getAutoView().addObject("languages",languages)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得Support语言表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看Support语言表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Languages languages=languagesService.getById(id);
		return getAutoView().addObject("languages", languages);
	}
	
}


package com.hotent.Xlkcs.controller.Xlkcs;

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

import com.hotent.Xlkcs.model.Xlkcs.Xlkcs;
import com.hotent.Xlkcs.service.Xlkcs.XlkcsService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:下拉框测试 控制器类
 */
@Controller
@RequestMapping("/Xlkcs/Xlkcs/xlkcs/")
public class XlkcsController extends BaseController
{
	@Resource
	private XlkcsService xlkcsService;
	/**
	 * 添加或更新下拉框测试。
	 * @param request
	 * @param response
	 * @param xlkcs 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新下拉框测试")
	public void save(HttpServletRequest request, HttpServletResponse response,Xlkcs xlkcs) throws Exception
	{
		String resultMsg=null;		
		try{
			if(xlkcs.getId()==null){
				Long id=UniqueIdUtil.genId();
				xlkcs.setId(id);
				xlkcsService.add(xlkcs);
				resultMsg=getText("添加","下拉框测试");
			}else{
			    xlkcsService.update(xlkcs);
				resultMsg=getText("更新","下拉框测试");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得下拉框测试分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看下拉框测试分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Xlkcs> list=xlkcsService.getAll(new QueryFilter(request,"xlkcsItem"));
		ModelAndView mv=this.getAutoView().addObject("xlkcsList",list);
		
		return mv;
	}
	
	/**
	 * 删除下拉框测试
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除下拉框测试")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			xlkcsService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除下拉框测试成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑下拉框测试
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑下拉框测试")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Xlkcs xlkcs=xlkcsService.getById(id);
		
		return getAutoView().addObject("xlkcs",xlkcs)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得下拉框测试明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看下拉框测试明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Xlkcs xlkcs=xlkcsService.getById(id);
		return getAutoView().addObject("xlkcs", xlkcs);
	}
	
}



package com.hotent.ywsjmk.controller.ywsjmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.form.BpmDataTemplateService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.ywsjmk.model.ywsjmk.Ywsjmb;
import com.hotent.ywsjmk.service.ywsjmk.YwsjmbService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:业务数据模板 控制器类
 */
@Controller
@RequestMapping("/ywsjmk/ywsjmk/ywsjmb/")
public class YwsjmbController extends BaseController
{
	@Resource
	private YwsjmbService ywsjmbService;
	@Resource
	private BpmDataTemplateService bpmDataTemplateService;
	
	/**
	 * 添加或更新业务数据模板。
	 * @param request
	 * @param response
	 * @param ywsjmb 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新业务数据模板")
	public void save(HttpServletRequest request, HttpServletResponse response,Ywsjmb ywsjmb) throws Exception
	{
		String resultMsg=null;		
		try{
			if(ywsjmb.getId()==null){
				ywsjmbService.save(ywsjmb);
				resultMsg=getText("添加","业务数据模板");
			}else{
			    ywsjmbService.save(ywsjmb);
				resultMsg=getText("更新","业务数据模板");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得业务数据模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看业务数据模板分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Ywsjmb> list=ywsjmbService.getAll(new QueryFilter(request,"ywsjmbItem"));
		List  categoryIdList=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Ywsjmb ywsjmb = list.get(i);
			String  categoryId=ywsjmb.getCategoryid();
			categoryIdList.add(categoryId);
			System.out.println("list ++ categoryId "+i+" :"+categoryIdList.get(i));
			}
		ModelAndView mv=this.getAutoView().addObject("ywsjmbList",list)
										  .addObject("categoryIdList", categoryIdList);
		return mv;
	}
	
	/**
	 * 删除业务数据模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除业务数据模板")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		
		
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			for(Long mid: lAryId){
			ywsjmbService.delById(mid);
			}
			
			message=new ResultMessage(ResultMessage.Success, "删除业务数据模板成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑业务数据模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑业务数据模板")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Ywsjmb ywsjmb=ywsjmbService.getById(id);
		
		return getAutoView().addObject("ywsjmb",ywsjmb)
							.addObject("returnUrl",returnUrl);
	}
	
	//复制业务数据模板
	
	
	@RequestMapping("copy")
	@Action(description = "复制业务数据模板")
	public void copy(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long []id=RequestUtil.getLongAryByStr(request, "id");
			for(Long mid: id){
			System.out.println("+++  ywsjmbcopy mid:"+mid);
			BpmDataTemplate ywsjmb=bpmDataTemplateService.getById(mid);
			BpmDataTemplate sjmb=new BpmDataTemplate();
			//Long id=UniqueIdUtil.genId();
			//sjmb.setId(id);
			sjmb.setTemplateAlias(ywsjmb.getTemplateAlias());
			sjmb.setPageSize(ywsjmb.getPageSize());
			sjmb.setNeedPage(ywsjmb.getNeedPage());
			sjmb.setStyle(ywsjmb.getStyle());
			sjmb.setTemplateHtml(ywsjmb.getTemplateHtml());
			sjmb.setAlias(ywsjmb.getAlias());
			sjmb.setName(ywsjmb.getName());
			sjmb.setVarField(ywsjmb.getVarField());
			sjmb.setFilterField(ywsjmb.getFilterField());
			sjmb.setTableId(ywsjmb.getTableId());
			System.out.println("+++copy sjmbtableid:"+mid);
			sjmb.setManageField(ywsjmb.getManageField());
			sjmb.setConditionField(ywsjmb.getConditionField());
			sjmb.setDisplayField(ywsjmb.getDisplayField());	
			sjmb.setSource(ywsjmb.getSource());
			sjmb.setDefId(ywsjmb.getDefId());		 
			sjmb.setIsQuery(ywsjmb.getIsQuery());
			sjmb.setSubject(ywsjmb.getSubject());
			//Long formk=UniqueIdUtil.genId();
			//String formky=String.valueOf(formk);
			sjmb.setFormkey(ywsjmb.getFormKey());
			sjmb.setName(ywsjmb.getName()+"副本");
			System.out.println("+++copy mid:"+mid);
			sjmb.setTablename(ywsjmb.getTablename());
			sjmb.setCategoryid(ywsjmb.getCategoryid()) ;
			sjmb.setExportField(ywsjmb.getExportField()) ;
			sjmb.setPrintField(ywsjmb.getPrintField()) ;
			bpmDataTemplateService.save(sjmb);
		}
		message=new ResultMessage(ResultMessage.Success, "复制业务数据模板成功!");}
		catch(Exception ex){
		message=new ResultMessage(ResultMessage.Fail, "复制失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	
	
	
	

	/**
	 * 取得业务数据模板明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看业务数据模板明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Ywsjmb ywsjmb=ywsjmbService.getById(id);
		return getAutoView().addObject("ywsjmb", ywsjmb);
	}
	
}
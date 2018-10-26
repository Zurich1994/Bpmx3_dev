
package com.hotent.support.controller.catelog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.support.model.catelog.FileCatalog;
import com.hotent.support.service.catelog.FileCatalogService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:文件目录 控制器类
 */
@Controller
@RequestMapping("/support/catelog/fileCatalog/")
public class FileCatalogController extends BaseController
{
	@Resource
	private FileCatalogService fileCatalogService;
	/**
	 * 添加或更新文件目录。
	 * @param request
	 * @param response
	 * @param fileCatalog 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新文件目录")
	public void save(HttpServletRequest request, HttpServletResponse response,FileCatalog fileCatalog) throws Exception
	{
		String resultMsg=null;		
		try{
			if(fileCatalog.getId()==null){
				Long id=UniqueIdUtil.genId();
				fileCatalog.setId(id);
				fileCatalogService.add(fileCatalog);
				resultMsg=getText("添加","文件目录");
			}else{
			    fileCatalogService.update(fileCatalog);
				resultMsg=getText("更新","文件目录");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得文件目录分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看文件目录分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<FileCatalog> list=fileCatalogService.getAll(new QueryFilter(request,"fileCatalogItem"));
		ModelAndView mv=this.getAutoView().addObject("fileCatalogList",list);
		
		return mv;
	}
	
	/**
	 * 删除文件目录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除文件目录")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			fileCatalogService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除文件目录成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑文件目录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑文件目录")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String id=RequestUtil.getString(request,"PRODUCTID");
		System.out.println(id);
/*		String returnUrl=RequestUtil.getPrePage(request);*/
	//	List<FileCatalog> fileCatalog=fileCatalogService.getCatalog(id);
		
		return getAutoView().addObject("id", id);
							
	}
	/**
	 * 	编辑文件目录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("doedit")
	@Action(description="编辑文件目录")
	public ArrayList<String> doedit(HttpServletRequest request) throws Exception
	{
		ArrayList<String> list=new ArrayList<String>();
		Long productid=RequestUtil.getLong(request,"productid");
		//String returnUrl=RequestUtil.getPrePage(request);
		List<FileCatalog> fc=fileCatalogService.getByIdFromFilecatalog(productid);
		for(int i=0;i<fc.size();i++){
			list.add(i, fc.get(i).getFILECATALOG());
			//System.out.print(fc.get(i).getFILECATALOG());
		}
		return  list;
	}

	/**
	 * 取得文件目录明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看文件目录明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		FileCatalog fileCatalog=fileCatalogService.getById(id);
		return getAutoView().addObject("fileCatalog", fileCatalog);
	}
	
}

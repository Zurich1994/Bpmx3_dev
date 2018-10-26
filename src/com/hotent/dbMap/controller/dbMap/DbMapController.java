
package com.hotent.dbMap.controller.dbMap;

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

import com.hotent.dbMap.model.dbMap.DbMap;
import com.hotent.dbMap.service.dbMap.DbMapService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:数据库中map.xml信息表 控制器类
 */
@Controller
@RequestMapping("/dbMap/dbMap/dbMap/")
public class DbMapController extends BaseController
{
	@Resource
	private DbMapService dbMapService;
	/**
	 * 添加或更新数据库中map.xml信息表。
	 * @param request
	 * @param response
	 * @param dbMap 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新数据库中map.xml信息表")
	public void save(HttpServletRequest request, HttpServletResponse response,DbMap dbMap) throws Exception
	{
		String resultMsg=null;		
		try{
			if(dbMap.getId()==null){
				Long id=UniqueIdUtil.genId();
				dbMap.setId(id);
				dbMapService.add(dbMap);
				resultMsg=getText("添加","数据库中map.xml信息表");
			}else{
			    dbMapService.update(dbMap);
				resultMsg=getText("更新","数据库中map.xml信息表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得数据库中map.xml信息表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看数据库中map.xml信息表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DbMap> list=dbMapService.getAll(new QueryFilter(request,"dbMapItem"));
		ModelAndView mv=this.getAutoView().addObject("dbMapList",list);
		
		return mv;
	}
	
	/**
	 * 删除数据库中map.xml信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除数据库中map.xml信息表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			dbMapService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除数据库中map.xml信息表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑数据库中map.xml信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑数据库中map.xml信息表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		DbMap dbMap=dbMapService.getById(id);
		
		return getAutoView().addObject("dbMap",dbMap)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得数据库中map.xml信息表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看数据库中map.xml信息表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DbMap dbMap=dbMapService.getById(id);
		return getAutoView().addObject("dbMap", dbMap);
	}
	
}

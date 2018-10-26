
package com.hotent.ImportData.controller.lc;

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

import com.hotent.HistoryData.model.lc.HistoryData;
import com.hotent.HistoryData.service.lc.HistoryDataService;
import com.hotent.ImportData.model.lc.ImportData;
import com.hotent.ImportData.service.lc.ImportDataService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:导入数据 控制器类
 */
@Controller
@RequestMapping("/ImportData/lc/importData/")
public class ImportDataController extends BaseController
{
	@Resource
	private ImportDataService importDataService;
	
	/**
	 * 添加或更新导入数据。
	 * @param request
	 * @param response
	 * @param importData 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新导入数据")
	public void save(HttpServletRequest request, HttpServletResponse response,ImportData importData) throws Exception
	{
		String resultMsg=null;		
		try{
			if(importData.getId()==null){
				Long id=UniqueIdUtil.genId();
				importData.setId(id);
				importDataService.add(importData);
				resultMsg=getText("添加","导入数据");
			}else{
			    importDataService.update(importData);
				resultMsg=getText("更新","导入数据");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得导入数据分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看导入数据分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ImportData> list=importDataService.getAll(new QueryFilter(request,"importDataItem"));
		ModelAndView mv=this.getAutoView().addObject("importDataList",list);
		
		return mv;
	}
	
	/**
	 * 删除导入数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除导入数据")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			importDataService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除导入数据成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑导入数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑导入数据")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");

		
		String returnUrl=RequestUtil.getPrePage(request);
		ImportData importData=importDataService.getById(id);
		
		return getAutoView().addObject("importData",importData)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得导入数据明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看导入数据明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ImportData importData=importDataService.getById(id);
		return getAutoView().addObject("importData", importData);
	}
	
}

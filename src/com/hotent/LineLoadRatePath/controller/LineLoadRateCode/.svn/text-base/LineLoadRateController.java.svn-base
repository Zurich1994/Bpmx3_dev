
package com.hotent.LineLoadRatePath.controller.LineLoadRateCode;

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

import com.hotent.LineLoadRatePath.model.LineLoadRateCode.LineLoadRate;
import com.hotent.LineLoadRatePath.service.LineLoadRateCode.LineLoadRateService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:线路负载值及利用率表 控制器类
 */
@Controller
@RequestMapping("/LineLoadRatePath/LineLoadRateCode/lineLoadRate/")
public class LineLoadRateController extends BaseController
{
	@Resource
	private LineLoadRateService lineLoadRateService;
	/**
	 * 添加或更新线路负载值及利用率表。
	 * @param request
	 * @param response
	 * @param lineLoadRate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新线路负载值及利用率表")
	public void save(HttpServletRequest request, HttpServletResponse response,LineLoadRate lineLoadRate) throws Exception
	{
		String resultMsg=null;		
		try{
			if(lineLoadRate.getId()==null){
				Long id=UniqueIdUtil.genId();
				lineLoadRate.setId(id);
				lineLoadRateService.add(lineLoadRate);
				resultMsg=getText("添加","线路负载值及利用率表");
			}else{
			    lineLoadRateService.update(lineLoadRate);
				resultMsg=getText("更新","线路负载值及利用率表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得线路负载值及利用率表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看线路负载值及利用率表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<LineLoadRate> list=lineLoadRateService.getAll(new QueryFilter(request,"lineLoadRateItem"));
		ModelAndView mv=this.getAutoView().addObject("lineLoadRateList",list);
		
		return mv;
	}
	
	/**
	 * 删除线路负载值及利用率表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除线路负载值及利用率表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			lineLoadRateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除线路负载值及利用率表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑线路负载值及利用率表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑线路负载值及利用率表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		LineLoadRate lineLoadRate=lineLoadRateService.getById(id);
		
		return getAutoView().addObject("lineLoadRate",lineLoadRate)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得线路负载值及利用率表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看线路负载值及利用率表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		LineLoadRate lineLoadRate=lineLoadRateService.getById(id);
		return getAutoView().addObject("lineLoadRate", lineLoadRate);
	}
	
}

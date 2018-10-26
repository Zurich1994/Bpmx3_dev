
package com.hotent.tpcc.controller.tpcc;

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

import com.hotent.tpcc.model.tpcc.District;
import com.hotent.tpcc.service.tpcc.DistrictService;
import com.hotent.core.web.ResultMessage;
import com.hotent.tpcc.model.tpcc.Customer;
/**
 * 对象功能:district 控制器类
 */
@Controller
@RequestMapping("/tpcc/tpcc/district/")
public class DistrictController extends BaseController
{
	@Resource
	private DistrictService districtService;
	/**
	 * 添加或更新district。
	 * @param request
	 * @param response
	 * @param district 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新district")
	public void save(HttpServletRequest request, HttpServletResponse response,District district) throws Exception
	{
	
		String resultMsg=null;		
		try{
			if(district.getD_id()==null){
				Long d_id=UniqueIdUtil.genId();
				district.setD_id(d_id);
				districtService.add(district);
				resultMsg=getText("添加","district");
			}else{
			    districtService.update(district);
				resultMsg=getText("更新","district");
			}
	
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得district分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看可用订单号")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		District district = new District();
		String D_ID = request.getParameter("Q_d_id_L");
		String W_ID = request.getParameter("Q_w_id_L");
		List<District> districtList = districtService.getorderMsg2(D_ID,W_ID);
		List<District> list=districtService.getAll(new QueryFilter(request,"districtItem"));
		if(districtList.size()!=0)
		    district = districtList.get(0);
		ModelAndView mv=this.getAutoView().addObject("district",district);
		
		return mv;
	}
	/**
	 * 跳入districtList
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
 
	@RequestMapping("query")
	@Action(description="查询")
	public ModelAndView query(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ModelAndView mv=this.getAutoView();
		return mv;
	}
	@RequestMapping("query2")
	@Action(description="查看可用订单号")
	public ModelAndView query1(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		District district = new District();
		String D_ID = request.getParameter("d_id");
		String W_ID = request.getParameter("w_id");
		String D_LEVEL = request.getParameter("d_level");
		List<District> districtList = districtService.getorderMsg2(D_ID,W_ID);
		District district0 = new District();
		district0.setD_id(RequestUtil.getLong(request, "d_id"));
		district0.setD_w_id(RequestUtil.getLong(request, "w_id"));
		if(districtList.size()!=0)
		    district = districtList.get(0);
    	ModelAndView mv=this.getAutoView().addObject("district",district)
   									      .addObject("district0",district0);
										  
			return mv;
	}
	/**
	 * 跳入districtItemno.jsp
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("itemno")
	@Action(description="商品号")
	public ModelAndView itemno(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String D_ID = request.getParameter("d_id");
		String W_ID = request.getParameter("w_id");
		String D_NEXT_O_ID = request.getParameter("d_next_o_id");
		String D_LEVEL = request.getParameter("d_level");
		Integer district1= districtService.itemno(D_ID,W_ID,D_NEXT_O_ID,D_LEVEL);
		ModelAndView mv=this.getAutoView().addObject("district1", district1)    ;		 
		return mv;
	}
	

	/**
	 * 删除district
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除district")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			districtService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除district成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑district
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑district")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long d_id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		District district=districtService.getById(d_id);
		
		return getAutoView().addObject("district",district)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得district明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看district明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long d_id=RequestUtil.getLong(request,"id");
		District district=districtService.getById(d_id);
		return getAutoView().addObject("district", district);
	}

}
	
	
	
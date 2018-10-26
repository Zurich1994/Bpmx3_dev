//
//package com.hotent.netWorkMap.controller.netWorkMap;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.hotent.core.annotion.Action;
//import org.springframework.web.servlet.ModelAndView;
//import com.hotent.core.util.UniqueIdUtil;
//import com.hotent.core.web.util.RequestUtil;
//import com.hotent.core.web.controller.BaseController;
//import com.hotent.core.util.BeanUtils;
//import com.hotent.core.web.query.QueryFilter;
//
//import com.hotent.netWorkMap.model.netWorkMap.Bpmnetworkmap;
//import com.hotent.netWorkMap.service.netWorkMap.BpmnetworkmapService;
//import com.hotent.core.web.ResultMessage;
///**
// * 对象功能:网络拓扑图表 控制器类
// */
//@Controller
//@RequestMapping("/netWorkMap/netWorkMap/bpmnetworkmap/")
//public class BpmnetworkmapController extends BaseController
//{
//	@Resource
//	private BpmnetworkmapService bpmnetworkmapService;
//	/**
//	 * 添加或更新网络拓扑图表。
//	 * @param request
//	 * @param response
//	 * @param bpmnetworkmap 添加或更新的实体
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("save")
//	@Action(description="添加或更新网络拓扑图表")
//	public void save(HttpServletRequest request, HttpServletResponse response,Bpmnetworkmap bpmnetworkmap) throws Exception
//	{
//		String resultMsg=null;		
//		try{
//			if(bpmnetworkmap.getId()==null){
//				Long id=UniqueIdUtil.genId();
//				bpmnetworkmap.setId(id);
//				bpmnetworkmapService.add(bpmnetworkmap);
//				resultMsg=getText("添加","网络拓扑图表");
//			}else{
//			    bpmnetworkmapService.update(bpmnetworkmap);
//				resultMsg=getText("更新","网络拓扑图表");
//			}
//			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
//		}catch(Exception e){
//			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
//		}
//	}
//	
//	/**
//	 * 取得网络拓扑图表分页列表
//	 * @param request
//	 * @param response
//	 * @param page
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("list")
//	@Action(description="查看网络拓扑图表分页列表")
//	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
//	{	
//		List<Bpmnetworkmap> list=bpmnetworkmapService.getAll(new QueryFilter(request,"bpmnetworkmapItem"));
//		ModelAndView mv=this.getAutoView().addObject("bpmnetworkmapList",list);
//		
//		return mv;
//	}
//	
//	/**
//	 * 删除网络拓扑图表
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("del")
//	@Action(description="删除网络拓扑图表")
//	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		String preUrl= RequestUtil.getPrePage(request);
//		ResultMessage message=null;
//		try{
//			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
//			bpmnetworkmapService.delByIds(lAryId);
//			message=new ResultMessage(ResultMessage.Success, "删除网络拓扑图表成功!");
//		}catch(Exception ex){
//			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
//		}
//		addMessage(message, request);
//		response.sendRedirect(preUrl);
//	}
//	
//	/**
//	 * 	编辑网络拓扑图表
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("edit")
//	@Action(description="编辑网络拓扑图表")
//	public ModelAndView edit(HttpServletRequest request) throws Exception
//	{
//		Long id=RequestUtil.getLong(request,"id");
//		
//		String returnUrl=RequestUtil.getPrePage(request);
//		Bpmnetworkmap bpmnetworkmap=bpmnetworkmapService.getById(id);
//		
//		return getAutoView().addObject("bpmnetworkmap",bpmnetworkmap)
//							.addObject("returnUrl",returnUrl);
//	}
//
//	/**
//	 * 取得网络拓扑图表明细
//	 * @param request   
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("get")
//	@Action(description="查看网络拓扑图表明细")
//	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		Long id=RequestUtil.getLong(request,"id");
//		Bpmnetworkmap bpmnetworkmap=bpmnetworkmapService.getById(id);
//		return getAutoView().addObject("bpmnetworkmap", bpmnetworkmap);
//	}
//	
//}

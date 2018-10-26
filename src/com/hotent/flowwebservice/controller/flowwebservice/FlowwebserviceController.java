
package com.hotent.flowwebservice.controller.flowwebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.data.Dictionary.MV;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.flowwebservice.model.flowwebservice.Flowwebservice;
import com.hotent.flowwebservice.service.flowwebservice.FlowwebserviceService;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.service.system.DictionaryService;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.system.Dictionary;
/**
 * 对象功能:flowWebService 控制器类
 */
@Controller
@RequestMapping("/flowwebservice/flowwebservice/flowwebservice/")
public class FlowwebserviceController extends BaseController
{
	@Resource
	private FlowwebserviceService flowwebserviceService;
	@Resource
	private DictionaryService dictionaryService;
	/**
	 * 添加或更新flowWebService。
	 * @param request
	 * @param response
	 * @param flowwebservice 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新flowWebService")
	public void save(HttpServletRequest request, HttpServletResponse response,Flowwebservice flowwebservice) throws Exception
	{
		String resultMsg=null;		
		try{
			if(flowwebservice.getId()==null){
				Long id=UniqueIdUtil.genId();
				flowwebservice.setId(id);
				flowwebserviceService.add(flowwebservice);
				resultMsg=getText("添加","flowWebService");
			}else{
			    flowwebserviceService.update(flowwebservice);
				resultMsg=getText("更新","flowWebService");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	
//	@RequestMapping("query1")
//	@Action(description="queryDictionaryService")
//	public ModelAndView query1(HttpServletRequest request, HttpServletResponse response,DictionaryService dictionaryService) throws Exception
//	{
////		String resultMsg=null;		
////		try{
////			if(flowwebservice.getId()==null){
////				Long id=UniqueIdUtil.genId();
////				flowwebservice.setId(id);
////				flowwebserviceService.add(flowwebservice);
////				resultMsg=getText("添加","flowWebService");
////			}else{
////			    flowwebserviceService.update(flowwebservice);
////				resultMsg=getText("更新","flowWebService");
////			}
////			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
////		}catch(Exception e){
////			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
////		}
//		Long typeId=RequestUtil.getLong(request, "typeId");
//		System.out.println("rrrrr"+typeId);
//		List<Dictionary> list=dictionaryService.getByTypeId2(typeId);	
//		return getAutoView();
//	}
	/**
	 * 取得flowWebService分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看flowWebService分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Flowwebservice> list=flowwebserviceService.getAll(new QueryFilter(request,"flowwebserviceItem"));
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i).getServiceType());
//			if(list.get(i).getServiceType().equals("A"))
//			{
//				list.get(i).setServiceType("服务类");
//				System.out.println(list.get(i).getServiceType()+"%%%");
//			}
//			else {
//				list.get(i).setServiceType("服务类");
//				System.out.println(list.get(i).getServiceType()+"%%%");
//			}
//		}
		ModelAndView mv=this.getAutoView().addObject("flowwebserviceList",list);
		
		return mv;
	}
	
	/**
	 * 删除flowWebService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除flowWebService")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			flowwebserviceService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除flowWebService成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑flowWebService
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑flowWebService")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{	
		Long defid=RequestUtil.getLong(request,"defId");
	
		String returnUrl=RequestUtil.getPrePage(request);
		
	

	
		
		
		List<Flowwebservice> list=flowwebserviceService.getAll(new QueryFilter(request,"flowwebserviceItem"));

		ModelAndView mv=this.getAutoView().addObject("flowwebserviceList",list);
		
		Long typeId=RequestUtil.getLong(request, "typeId");
        System.out.println("rrrrr"+typeId);
     	List<Dictionary> list2=dictionaryService.getByTypeId(typeId,false);
     	List<String> list3=new ArrayList<String>();
     	for(int i=0;i<list2.size();i++)
     	{
     		list3.add(list2.get(i).getItemName());
     	}
     	
     	  System.out.println("9999999"+list3.get(0));
		
		return mv.addObject("defid",defid)
		         .addObject("returnUrl",returnUrl)
		         .addObject("ServiceType",list3);
	}

	
	
	@RequestMapping("edit2")
	@Action(description="编辑flowWebService")
	public ModelAndView edit2(HttpServletRequest request) throws Exception
	{	
		
		Long id=RequestUtil.getLong(request,"id");
	
		System.out.println(id);
		
	
		String returnUrl=RequestUtil.getPrePage(request);

		List<Flowwebservice> list=flowwebserviceService.getAll(new QueryFilter(request,"flowwebserviceItem"));

		
		ModelAndView mv=this.getAutoView().addObject("flowwebserviceList",list);
		
		
		
		Flowwebservice 	flowwebservice=flowwebserviceService.getById(id);
		
		
		
		Long typeId=RequestUtil.getLong(request, "typeId");
        System.out.println("rrrrr"+typeId);
     	List<Dictionary> list2=dictionaryService.getByTypeId(typeId,false);
     	List<String> list3=new ArrayList<String>();
     	for(int i=0;i<list2.size();i++)
     	{
     		list3.add(list2.get(i).getItemName());
     	}
     	
     	  System.out.println("9999999"+list3.get(0));
	
		         
		return mv.addObject("flowwebservice",flowwebservice)
		
							.addObject("returnUrl",returnUrl)
		                    .addObject("ServiceType",list3);
		
	}

	/**
	 * 取得flowWebService明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看flowWebService明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Flowwebservice flowwebservice=flowwebserviceService.getById(id);
		return getAutoView().addObject("flowwebservice", flowwebservice);
	}
	
}

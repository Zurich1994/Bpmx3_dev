package com.hotent.extension.controller.bpm;

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
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;

import com.hotent.core.web.ResultMessage;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.extension.service.bpm.BpmNodeDataService;
/**
 *<pre>
 * 对象功能:BPM_NODE_DATA 控制器类
 * 开发公司:哈尔滨工程大学
 * 开发人员:ray
 * 
 *</pre>
 */
@Controller
@RequestMapping("/extension/bpm/bpmNodeData/")
public class BpmNodeDataController extends BaseController
{
	@Resource
	private BpmNodeDataService bpmNodeDataService;
	
	
	/**
	 * 添加或更新BPM_NODE_DATA。
	 * @param request
	 * @param response
	 * @param bpmNodeData 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新BPM_NODE_DATA")
	public void save(HttpServletRequest request, HttpServletResponse response,BpmNodeData bpmNodeData) throws Exception
	{
		String resultMsg=null;		
		try{
			if(bpmNodeData.getId()==null||bpmNodeData.getId()==0){
				bpmNodeData.setId(UniqueIdUtil.genId());
				bpmNodeDataService.add(bpmNodeData);
				resultMsg=getText("添加","BPM_NODE_DATA");
			}else{
			    bpmNodeDataService.update(bpmNodeData);
				resultMsg=getText("更新","BPM_NODE_DATA");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得BPM_NODE_DATA分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看BPM_NODE_DATA分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmNodeData> list=bpmNodeDataService.getAll(new QueryFilter(request,"bpmNodeDataItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmNodeDataList",list);
		
		return mv;
	}
	
	/**
	 * 删除BPM_NODE_DATA
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除BPM_NODE_DATA")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmNodeDataService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除BPM_NODE_DATA成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑BPM_NODE_DATA
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑BPM_NODE_DATA")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		BpmNodeData bpmNodeData=bpmNodeDataService.getById(id);
		
		return getAutoView().addObject("bpmNodeData",bpmNodeData)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得BPM_NODE_DATA明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看BPM_NODE_DATA明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		BpmNodeData bpmNodeData = bpmNodeDataService.getById(id);	
		return getAutoView().addObject("bpmNodeData", bpmNodeData);
	}
	
}

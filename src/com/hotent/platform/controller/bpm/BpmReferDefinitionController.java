package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmReferDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmReferDefinitionService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.SysOrgService;
/**
 *<pre>
 * 对象功能:流程定义引用 控制器类
 * 开发公司:SF
 * 开发人员:Raise
 * 创建时间:2013-05-14 10:28:06
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmReferDefinition/")
public class BpmReferDefinitionController extends BaseController
{
	@Resource
	private BpmReferDefinitionService bpmReferDefinitionService;
	
	@Resource 
	private ProcessRunService processRunService;
	@Resource
	private SysOrgService sysOrgService;
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	/**
	 * 添加或更新流程定义引用。
	 * @param request
	 * @param response
	 * @param bpmReferDefinition 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程定义引用")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		BpmReferDefinition bpmReferDefinition=getFormObject(request);
		try{
			if(bpmReferDefinition.getId()==null||bpmReferDefinition.getId()==0){
				bpmReferDefinition.setId(UniqueIdUtil.genId());
				bpmReferDefinitionService.add(bpmReferDefinition);
				resultMsg="添加流程定义引用成功";
			}else{
			    bpmReferDefinitionService.update(bpmReferDefinition);
				resultMsg="更新流程定义引用成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmReferDefinition 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected BpmReferDefinition getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		BpmReferDefinition bpmReferDefinition = (BpmReferDefinition)JSONObject.toBean(obj, BpmReferDefinition.class);
		
		return bpmReferDefinition;
    }
	
	/**
	 * 取得流程定义引用分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程定义引用分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmReferDefinition> list=bpmReferDefinitionService.getAll(new QueryFilter(request,"bpmReferDefinitionItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmReferDefinitionList",list);
		
		return mv;
	}
	
	/**
	 * 删除流程定义引用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程定义引用")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			bpmReferDefinitionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除流程定义引用成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑流程定义引用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑流程定义引用")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmReferDefinition bpmReferDefinition=bpmReferDefinitionService.getById(id);
		
		return getAutoView().addObject("bpmReferDefinition",bpmReferDefinition).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得流程定义引用明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程定义引用明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		BpmReferDefinition bpmReferDefinition = bpmReferDefinitionService.getById(id);	
		return getAutoView().addObject("bpmReferDefinition", bpmReferDefinition);
	}
	
	@RequestMapping("getByDefId")
	@ResponseBody
	@Action(description="")
	public Map<String,Object> getByDefId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		
		Long defId = RequestUtil.getLong(request, "defId", 0L);
		if(defId==0L){
			return map;
		}
		
		List<BpmReferDefinition> refers = bpmReferDefinitionService.getByDefId(defId);
		map.put("refers", refers);

		return map;
	}
	
	
	@RequestMapping("actInstDialog")
	public ModelAndView actInstDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long defId = RequestUtil.getLong(request, "defId", 0L);
		List<BpmReferDefinition> refers =null;
		if(defId==0L){
			refers = new ArrayList<BpmReferDefinition>();
		}else{
		 refers = bpmReferDefinitionService.getByDefId(defId);
		}
		return getAutoView().addObject("refers", refers)
		.addObject("isSingle",RequestUtil.getInt(request, "isSingle"));
	}
	
	@RequestMapping("actInstSelector")
	public ModelAndView actInstSelector(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String  referDefKey = RequestUtil.getString(request, "referDefKey");
		List<ProcessRun> list = null;
		QueryFilter filter = new QueryFilter(request, "processRunItem",10);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		filter.addFilter("defKey", referDefKey);
		list=processRunService.getByUserIdAndDefKey(filter);
		List<BpmDefinition> bpmDefinitionList=bpmDefinitionService.getByDefKey(referDefKey);
		List<ProcessRun> processRunList = new ArrayList<ProcessRun>();
		if (BeanUtils.isNotEmpty(bpmDefinitionList)) {
			for (BpmDefinition bpmDefinition:bpmDefinitionList) {
				for (ProcessRun processRun :list) {
					if (bpmDefinition.getDefId().equals(processRun.getDefId())) {
						processRunList.add(processRun);
					}
				}
			}
		}
		
		
		return this.getAutoView().addObject("processRunList", processRunList)
								.addObject("isSingle",RequestUtil.getInt(request, "isSingle"));
	}
}

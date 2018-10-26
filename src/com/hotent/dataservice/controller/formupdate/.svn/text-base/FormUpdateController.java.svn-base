
package com.hotent.dataservice.controller.formupdate;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.PageLoadPath.service.PageloadCode.PageloadService;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.dataservice.model.formupdate.FormUpdate;
import com.hotent.dataservice.service.formupdate.FormUpdateService;
import com.hotent.formQueryDefinition.model.com.Fqrelation;
import com.hotent.formQueryDefinition.service.com.FqrelationService;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.service.bpm.BpmFormQueryService;
import com.hotent.platform.service.system.SysDataSourceLService;
import com.hotent.redirection.service.redirection.RedirectionService;
import com.hotent.core.web.ResultMessage;


/**
 * 对象功能:原子操作-更新 控制器类
 */
@Controller
@RequestMapping("/dataservice/formupdate/formUpdate/")
public class FormUpdateController extends BaseController
{
	@Resource
	private FormUpdateService formUpdateService;
	@Resource
	private FqrelationService fqRelationService;
	@Resource
	private BpmFormQueryService bpmFormQueryService;
	@Resource
	private PageloadService pageloadService;
	@Resource
	RedirectionService redirectionService;
	/**
	 * 添加或更新原子操作-更新。
	 * @param request
	 * @param response
	 * @param formUpdate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新原子操作-更新")
	public void save(HttpServletRequest request, HttpServletResponse response,FormUpdate formUpdate) throws Exception
	{
		String resultMsg=null;		
		try{
			if(formUpdate.getId()==null){
				Long id=UniqueIdUtil.genId();
				formUpdate.setId(id);
				String fqId = String.valueOf(id);
				long fq_id =  UniqueIdUtil.genId();
				String nodeId = request.getParameter("nodeId");
				System.out.println("request-nodeId:"+nodeId);
				String defId = request.getParameter("defId");
				System.out.println("request-defId:"+defId);
				Fqrelation fqRelation = new Fqrelation();
				fqRelation.setId(fq_id);
				fqRelation.setDefID(defId);
				fqRelation.setFqID(fqId);
				fqRelation.setNodeID(nodeId);
				fqRelationService.add(fqRelation);
				formUpdateService.add(formUpdate);
				resultMsg=getText("添加","原子操作-更新");
			}else{
			    formUpdateService.update(formUpdate);
				resultMsg=getText("更新","原子操作-更新");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得原子操作-更新分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看原子操作-更新分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<FormUpdate> list=formUpdateService.getAll(new QueryFilter(request,"formUpdateItem"));
		ModelAndView mv=this.getAutoView().addObject("formUpdateList",list);
		
		return mv;
	}
	
	/**
	 * 删除原子操作-更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除原子操作-更新")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			Long fqId = RequestUtil.getLong(request, "id");
			String defid = RequestUtil.getString(request, "defId");
			String nodeid = RequestUtil.getString(request, "nodeId");
			fqRelationService.delByfqId(fqId);
			bpmFormQueryService.delByIds(lAryId);
			formUpdateService.delByIds(lAryId);
			pageloadService.delByIds(defid, nodeid);
			redirectionService.delByIds(defid, nodeid);
			message=new ResultMessage(ResultMessage.Success, "删除原子操作-更新成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑原子操作-更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑原子操作-更新")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		System.out.println("id:"+id);
		Map<String,Object> params = RequestUtil.getQueryMap(request);
		long canReturn  = RequestUtil.getLong(request, "canReturn",0);
		
		String returnUrl=RequestUtil.getPrePage(request);
		FormUpdate formUpdate = null;
		if(id !=0){
			 formUpdate=formUpdateService.getById(id);
			 System.out.println("Conditionfield:"+formUpdate.getConditionfield());
			 System.out.println("Updatefield:"+formUpdate.getUpdatefield());
			 System.out.println("id不等于0");
			 System.out.println(formUpdate.getId());
			 
		}else{
			formUpdate = new FormUpdate();
			System.out.println("id等于0");
			 System.out.println(formUpdate.getId());
			
		}
		List<SysDataSourceL> dsList =AppUtil.getBean(SysDataSourceLService.class).getAll();
		
	
		
		return getAutoView().addObject("formUpdate",formUpdate)
							.addObject("returnUrl",returnUrl).addObject("dsList", dsList).addObject("canReturn", canReturn);
	}

	/**
	 * 取得原子操作-更新明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看原子操作-更新明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		FormUpdate formUpdate=formUpdateService.getById(id);
		return getAutoView().addObject("formUpdate", formUpdate);
	}
	
	
	/**
	 * 设置字段
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		String dsName = "";
		String objectName = "";
		long istable = 0;
		ModelAndView mv = this.getAutoView();
		if (id == 0) {
			dsName = RequestUtil.getString(request, "dsName");
			istable = RequestUtil.getInt(request, "istable");
			objectName = RequestUtil.getString(request, "objectName");
		} else {
			FormUpdate formUpdate = formUpdateService.getById(id);
			dsName = formUpdate.getDsalias();
			objectName = formUpdate.getObjName();
			istable = formUpdate.getIsTable();

			mv.addObject("formUpdate", formUpdate);
		}

		TableModel tableModel=null;
		// 表
		if (istable == 1) {
			BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
			tableModel = meta.getTableByName(objectName);
		}
		// 视图处理
		else {
			IDbView dbView = TableMetaFactory.getDbView(dsName);
			tableModel = dbView.getModelByViewName(objectName);
		}

		mv.addObject("tableModel", tableModel);

		return mv;
	}
	
}

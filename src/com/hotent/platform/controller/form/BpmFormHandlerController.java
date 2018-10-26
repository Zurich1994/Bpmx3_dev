package com.hotent.platform.controller.form;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.fr.script.function.SQL;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.definedAtomForm.service.definedAtomForm.DefinedAtomFormService;
import com.hotent.platform.controller.system.ResourcesFormController;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.PkValue;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.FormDataUtil;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.ParseReult;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import com.hotent.core.exception.BusDataException;

/**
 * 对象功能:自定义表单数据处理
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy 
 * 创建时间:2011-12-22 11:07:56
 */
@Controller
@RequestMapping("/platform/form/bpmFormHandler/")
public class BpmFormHandlerController extends BaseController {
	@Resource
	private BpmFormHandlerService service;

	@Resource
	private BpmFormDefService bpmFormDefService;
	
	@Resource
	private BpmFormTableService bpmFormTableService;
	
	@Resource
	private TableParcelService tableParcelService;

	@Resource
    private DefinedAtomFormService definedAtomFormService;
	private static final Logger log = Logger.getLogger(BpmFormHandlerController.class); 
	/**
	 * 表单预览
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "表单预览")
	public ModelAndView edit(HttpServletRequest request) throws Exception {

		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String formKey= RequestUtil.getString(request, "formKey");
		log.info("进入表单预览 ");
		formDefId = null;
		if( RequestUtil.getLong(request, "formDefId")==0){
        
			Long id = RequestUtil.getLong(request, "id");
			 log.info("id="+id);
			
		    
		    String formkey = definedAtomFormService.getFormKeyById(id);
		    System.out.println("formkey="+formkey);
		   log.info("select formkey from definedAtomForm by id;formkey="+formkey);
            

			formDefId = bpmFormDefService.getFormDefIdByFormKey(formkey);   


	     	 System.out.println("formDefId="+formDefId);
	     	log.info("select formdefid from definedAtomForm by formkey; formDefId="+formDefId);
	     	
	     	 
	     	
		    
	     	}
		else {
		    formDefId = RequestUtil.getLong(request, "formDefId");
		    log.info("formDefId="+formDefId);
		  }
		String pkValue = request.getParameter("pkValue");
		String returnUrl = RequestUtil.getPrePage(request);
		String ctxPath=request.getContextPath();
		BpmFormDef bpmFormDef = null;

		//if (formDefId != 0 || StringUtil.isNotEmpty(formKey)) {
		if (formDefId != null || formKey!="") {
			if(formDefId>0){
				bpmFormDef = bpmFormDefService.getById(formDefId);	
			}
			else{
				bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(formKey);	
			}
			
			String html = service.obtainHtml(bpmFormDef, ContextUtil.getCurrentUserId(), pkValue, "", "#formPrev", "",ctxPath, "",false);
			Long tableId = bpmFormDef.getTableId();
			if(BeanUtils.isNotIncZeroEmpty(tableId) ){
				BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
				if(bpmFormTable!=null) html += service.getSubPermission(bpmFormTable, false);
			}
			bpmFormDef.setHtml(html);
		} else {
			String html = request.getParameter("html");
			Long tableId = RequestUtil.getLong(request, "tableId");
			String title = RequestUtil.getString(request, "title");
			bpmFormDef = new BpmFormDef();
			bpmFormDef.setSubject(RequestUtil.getString(request, "name"));
			bpmFormDef.setTabTitle(title);
			bpmFormDef.setFormDesc(RequestUtil.getString(request, "comment"));
			if(tableId>0){				
				// 读取表。
				BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
				ParseReult result = new ParseReult();
				result.setBpmFormTable(bpmFormTable);
				String template = FormUtil.getFreeMarkerTemplate(html, tableId);
				result.setTemplate(template);
				html = service.obtainHtml(title, result, null,true);
				html += service.getSubPermission(bpmFormTable, false);
				bpmFormDef.setHtml(html);
			}
			
		}	
		
		return getAutoView().addObject("bpmFormDef", bpmFormDef).addObject("returnUrl", returnUrl);
	}


	/**
	 * 数据包表单预览
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit1")
	@Action(description = "数据包表单预览")
	public ModelAndView edit1(HttpServletRequest request) throws Exception {
		log.info("进入表单预览 ");
		Long formDefId = null;
		if( RequestUtil.getLong(request, "formDefId")==0){
        
			Long id = RequestUtil.getLong(request, "id");
			 log.info("id="+id);
			
		    
		   
		    
		    String formkey = definedAtomFormService.getFormKeyById(id);
		    System.out.println("formkey="+formkey);
		   log.info("select formkey from definedAtomForm by id;formkey="+formkey);
            
		    

	     	formDefId =  bpmFormDefService.getFormDefIdByFormKey(formkey);

	     	 System.out.println("formDefId="+formDefId);
	     	log.info("select formdefid from definedAtomForm by formkey; formDefId="+formDefId);
	     	
	     	 
	     	
		    
	     	}
		else {
		    formDefId = RequestUtil.getLong(request, "formDefId");
		    log.info("formDefId="+formDefId);
		  }
		
		String pkValue = request.getParameter("pkValue");
		String returnUrl = RequestUtil.getPrePage(request);
		String ctxPath=request.getContextPath();
		BpmFormDef bpmFormDef = null;

		if (formDefId != null) {
			bpmFormDef = bpmFormDefService.getById(formDefId);	
			String html = service.obtainHtml(bpmFormDef, ContextUtil.getCurrentUserId(), pkValue, "", "#formPrev", "",ctxPath, "",false);
			Long tableId = bpmFormDef.getTableId();
			if(BeanUtils.isNotEmpty(tableId) && tableId>0){
				BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
				if(bpmFormTable!=null) html += service.getSubPermission(bpmFormTable, false);
			}
			bpmFormDef.setHtml(html);
		} else {
			String html = request.getParameter("html");
			//Long tableId = RequestUtil.getLong(request, "tableId");
			String tableId =RequestUtil.getString(request, "tableId");
			Long []tableid= new Long[tableId.length()/14];
			for(int i=0;i<tableId.length()/14;i++)
			{
				tableid[i]=Long.parseLong(tableId.substring(i*14,i*14+14));
				TableParcel q=tableParcelService.getById(tableid[i]);
			
				String table_name=q.getTable_name();
				Long table_id=bpmFormTableService.getByTableName(table_name).getTableId();
				String title = RequestUtil.getString(request, "title");
				bpmFormDef = new BpmFormDef();
				bpmFormDef.setSubject(RequestUtil.getString(request, "name"));
				bpmFormDef.setTabTitle(title);
				bpmFormDef.setFormDesc(RequestUtil.getString(request, "comment"));
				if(table_id>0){				
					// 读取表。
					BpmFormTable bpmFormTable = bpmFormTableService.getTableById(table_id);
					ParseReult result = new ParseReult();
					result.setBpmFormTable(bpmFormTable);
					String template = FormUtil.getFreeMarkerTemplate(html, table_id);
					result.setTemplate(template);
					html = service.obtainHtml(title, result, null,true);
					html += service.getSubPermission(bpmFormTable, false);
					bpmFormDef.setHtml(html);
			}
			
			}
			
		}		
		return getAutoView().addObject("bpmFormDef", bpmFormDef).addObject("returnUrl", returnUrl);
	}
	/**
	 * 业务表单。
	 * <pre>
	 * 1.输入表单key。
	 * 2.输入主键。
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bizForm")
	@Action(description = "显示业务表单。" )
	public ModelAndView bizForm(HttpServletRequest request) throws Exception {
		Long formKey = RequestUtil.getLong(request, "formKey");
		String id = request.getParameter("id");
		boolean hasPk=StringUtil.isNotEmpty(id);
		String returnUrl = RequestUtil.getPrePage(request);
		String ctxPath = request.getContextPath();
		BpmFormDef bpmFormDef = null;
		String tableName="";
		String pkField="";
		if (formKey != 0) {
			bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey.toString());
			BpmFormTable bpmFormTable= bpmFormTableService.getById(bpmFormDef.getTableId());
			tableName=bpmFormTable.getTableDesc();
			String html = service.obtainHtml(bpmFormDef, ContextUtil.getCurrentUserId(), id, "", "", "",ctxPath, "",false);
			pkField=bpmFormTable.getPkField();
			bpmFormDef.setHtml(html);
		} 
		return getAutoView()
				.addObject("bpmFormDef", bpmFormDef)
				.addObject("id", id)
				.addObject("pkField", pkField)
				.addObject("tableName", tableName)
				.addObject("returnUrl", returnUrl)
				.addObject("hasPk",hasPk);
	}
	
	/**
	 * 删除数据
	 * <pre>
	 * 1.输入表单key。
	 * 2.输入主键。
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除数据。" )
	public void del(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String returnUrl=RequestUtil.getPrePage(request);
		Long formKey = RequestUtil.getLong(request, "formKey");
		String id = request.getParameter("id");
		BpmFormDef bpmFormDef = null;
		try{
			if (formKey != 0) {
				bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey.toString());
				BpmFormTable bpmFormTable= bpmFormTableService.getById(bpmFormDef.getTableId());			
				service.delById(id,bpmFormTable);
				addMessage(new ResultMessage(ResultMessage.Success, "删除业务数据成功"), request);
			}else{
				addMessage(new ResultMessage(ResultMessage.Fail, "删除业务数据失败,没有取得表名"), request);
			}			
		}
		catch(Exception ex){
			ex.printStackTrace();
			addMessage(new ResultMessage(ResultMessage.Fail, "删除业务数据失败"), request);
		}
		response.sendRedirect(returnUrl);
	}
	

	/**
	 * 保存业务数据。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新" )
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("formData");
		Long tableId= RequestUtil.getLong(request, "tableId");
		//System.out.println("tableId:::"+tableId);
		BpmFormTable bpmFormTable=bpmFormTableService.getByTableId(tableId, 1);
		String alias=RequestUtil.getString(request,"alias");
		//System.out.println("alias:::"+alias);
		String id = request.getParameter("pkField");
		//System.out.println("id:::"+id);
		try{
			if(StringUtil.isEmpty(id)){
				BpmFormData bpmFormData=FormDataUtil.parseJson(data,bpmFormTable);
				service.handFormData(bpmFormData,alias,data);
			}
			else{
				PkValue pkValue=new PkValue(bpmFormTable.getPkField(), id);
				pkValue.setIsAdd(false);
				BpmFormData bpmFormData=FormDataUtil.parseJson(data,pkValue,bpmFormTable);
				service.handFormData(bpmFormData,alias,data);
			}
			
			writeResultMessage(response.getWriter(), "保存表单数据成功!", ResultMessage.Success);
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"保存表单数据失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	
	@RequestMapping("saveform")
	@Action(description = "添加或更新" )
	public void saveform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("formData");
		Long tableId= RequestUtil.getLong(request, "tableId");
		String actdefid= RequestUtil.getString(request, "actdefid");
		String nodeid=  RequestUtil.getString(request, "nodeid");
		String alias=RequestUtil.getString(request,"alias");
		
		BpmFormTable bpmFormTable=bpmFormTableService.getByTableId(tableId, 1);
		String id = request.getParameter(bpmFormTable.getPkField());
		System.out.println(data);
		System.out.println(actdefid+nodeid);
		System.out.println("\"actdefid\":\"\"");
		System.out.println("替换为");
		System.out.println("\"actdefid\":\""+actdefid+"\"");
		String st="\"actdefid\":\""+actdefid+"\"";
		data=data.replaceFirst("\"actdefid\":\"\"", st);
		data=data.replace("\"nodeid\":\"\"", "\"nodeid\":\""+nodeid+"\"");
		System.out.println(data);
		try{
			if(StringUtil.isEmpty(id)){
				
				BpmFormData bpmFormData=FormDataUtil.parseJson(data,bpmFormTable);
			
				
				//System.out.println(bpmFormData.getSubTableList().toString());
			
				service.handFormData(bpmFormData,alias,data);
			}
			else{
				PkValue pkValue=new PkValue(bpmFormTable.getPkField(), id);
				pkValue.setIsAdd(false);
				BpmFormData bpmFormData=FormDataUtil.parseJson(data,pkValue,bpmFormTable);
				service.handFormData(bpmFormData,alias,data);
			}
			
			writeResultMessage(response.getWriter(), "保存表单数据成功!", ResultMessage.Success);
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"保存表单数据失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	/**
	 * 表单预览
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editdef")
	@Action(description = "表单预览")
	public ModelAndView editdefinition(HttpServletRequest request) throws Exception {
		log.info("控制台输出 ");
		ModelAndView mv = new ModelAndView();
			Long defId = RequestUtil.getLong(request, "defId");
			String actDefId = RequestUtil.getString(request, "actDefId");
			String typeId = RequestUtil.getString(request, "typeId");
			System.out.println("actDefid是："+actDefId );
			mv.setViewName("/platform/form/bpmFormHandlerEditdef.jsp");
			return mv.addObject("defId", defId).addObject("actDefId", actDefId).addObject("typeId", typeId);
	}
}

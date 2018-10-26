
package com.hotent.tableParcel.controller.tableParcel;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:table_parcel 控制器类
 */
@Controller
@RequestMapping("/tableParcel/tableParcel/tableParcel/")
public class TableParcelController extends BaseController
{
	@Resource
	private TableParcelService tableParcelService;
	//@Resource
	//private TableParcel tableParcel;
	/**
	 * 添加或更新table_parcel。
	 * @param request
	 * @param response
	 * @param tableParcel 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新table_parcel")
	public void save(HttpServletRequest request, HttpServletResponse response,TableParcel tableParcel) throws Exception
	{
		String resultMsg=null;		
		try{
			if(tableParcel.getId()==null){
				Long id=UniqueIdUtil.genId();
				tableParcel.setId(id);
				tableParcelService.add(tableParcel);
				resultMsg=getText("添加","table_parcel");
			}else{
			    tableParcelService.update(tableParcel);
				resultMsg=getText("更新","table_parcel");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	/**
	 * 添加或更新table_parcel。
	 * @param request
	 * @param response
	 * @param tableParcel 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveParcel")
	@Action(description="添加或更新table_parcel")
	public void saveParcel(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		TableParcel tableParcel=new TableParcel();
		Long id=RequestUtil.getLong(request, "id");
		
		
		String msg="";
		if(id==0){
			//保存
			   
				String name=RequestUtil.getString(request, "name");
				String tablename=RequestUtil.getString(request, "tablename");
				if(isExt(name,tablename)){
					msg="数据包已存在！";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				}
				else {
					id=UniqueIdUtil.genId();
					String alias=RequestUtil.getString(request, "comment");
					String value=RequestUtil.getString(request, "value");
					
					
					
					tableParcel.setId(id);
					tableParcel.setParcel_alias(alias);
					tableParcel.setParcel_value(value);
					tableParcel.setTable_name(tablename);
					tableParcel.setParcel_name(name);
					
					tableParcelService.add(tableParcel);
					msg="保存成功！";
					
					writeResultMessage(response.getWriter(), msg,ResultMessage.Success);
				}
				
		}
		else {
			//更新
			
			String name=RequestUtil.getString(request, "name");
			String alias=RequestUtil.getString(request, "comment");
			String value=RequestUtil.getString(request, "value");
			String tablename=RequestUtil.getString(request, "tablename");
			
			tableParcel.setId(id);
			tableParcel.setParcel_alias(alias);
			tableParcel.setParcel_value(value);
			tableParcel.setTable_name(tablename);
			tableParcel.setParcel_name(name);
			
			tableParcelService.update(tableParcel);
			msg="更新成功！";
			writeResultMessage(response.getWriter(), msg,ResultMessage.Success);
		}
	   
	}

	private boolean isExt(String name, String tablename) {
		// TODO Auto-generated method stub
		List<TableParcel> parcels=tableParcelService.getParcelbyName(name,tablename);
			
		return !parcels.isEmpty();
	}
	/**
	 * 取得table_parcel分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看table_parcel分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<TableParcel> list=tableParcelService.getAll(new QueryFilter(request,"tableParcelItem"));
		ModelAndView mv=this.getAutoView().addObject("tableParcelList",list);
		
		return mv;
	}
	
	/**
	 * 删除table_parcel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除table_parcel")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			tableParcelService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除table_parcel成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑table_parcel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑table_parcel")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		TableParcel tableParcel=tableParcelService.getById(id);
		
		return getAutoView().addObject("tableParcel",tableParcel)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得table_parcel明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看table_parcel明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		TableParcel tableParcel=tableParcelService.getById(id);
		return getAutoView().addObject("tableParcel", tableParcel);
	}
	/**
	 * 取得table_parcel明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getbyJson")
	public Object getbyJson(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		TableParcel tableParcel=tableParcelService.getById(id);
		//创建 json 格式
		JSONObject chidren=new JSONObject();
		JSONArray fildarr=new JSONArray();
		
		String parcelname= tableParcel.getParcel_name();
		String values=tableParcel.getParcel_value();
		JSONArray jvalues=JSONArray.fromObject(values);
	
		//[{"field":"F_actDef_Id","comment":"流程ID"},{"field":"F_activity_id","comment":"活动ID"}]
		
		for(int j=0;j<jvalues.size();j++){
			JSONObject fild=new JSONObject();
			
			JSONObject jobj=(JSONObject) jvalues.get(j);
			String value=jobj.getString("comment")+"("+jobj.getString("field")+")";
	
			fild.accumulate("name", value);
			fildarr.add(fild);
			
		}
		chidren.accumulate("name", parcelname);
		chidren.accumulate("children", fildarr);
		System.out.println(chidren.toString());
		return chidren;
	}
	
}

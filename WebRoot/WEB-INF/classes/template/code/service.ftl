<#import "code/function.ftl" as func>
<#assign package=table.variable.package>
<#assign class=table.variable.class>
<#assign subtables=table.subTableList>
<#assign classVar=table.variable.classVar>
package com.hotent.${system}.service.${package};
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.${system}.model.${package}.${class};
import com.hotent.${system}.dao.${package}.${class}Dao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;

<#if subtables?size != 0>
	<#list subtables as subtable>
import com.hotent.${system}.model.${subtable.variable.package}.${subtable.variable.class};
import com.hotent.${system}.dao.${subtable.variable.package}.${subtable.variable.class}Dao;
	</#list>
</#if>

<#if flowKey?exists>
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.core.service.WfBaseService;
import com.hotent.core.annotion.WorkFlow;
import com.hotent.core.bpm.BpmAspectUtil;
import com.hotent.core.bpm.BpmResult;
<#else>
import com.hotent.core.service.BaseService;
</#if>


@Service
public class ${class}Service extends <#if flowKey?exists>WfBaseService<#else>BaseService</#if><${class}>
{
	@Resource
	private ${class}Dao dao;
	
	<#if subtables?size != 0>
		<#list subtables as subtable>
	@Resource
	private ${subtable.variable.class}Dao ${subtable.variable.classVar}Dao;
		</#list>
	</#if>
	<#if flowKey?exists>
	@Resource
	private ProcessRunService processRunService;
	</#if>
	public ${class}Service()
	{
	}
	
	@Override
	protected IEntityDao<${class},Long> getEntityDao() 
	{
		return dao;
	}
	
	<#if subtables?size != 0>
	/**
	 * 根据外键删除子表记录
	 * @param id
	 */
	private void delByPk(Long id){
	    <#list subtables as subtable>
		${subtable.variable.classVar}Dao.delByMainId(id);
	    </#list>
	}
	
	/**
	 * 删除数据 包含相应子表记录
	 * @param lAryId
	 */
	public void delAll(Long[] lAryId) {
		for(Long id:lAryId){	
			delByPk(id);
			dao.delById(id);	
			<#if func.supportFlow(table)>
			//删除草稿表。
			processRunService.delByBusinessNum(id);
			</#if>
		}	
	}
	
	/**
	 * 添加数据 
	 * @param ${classVar}
	 * @throws Exception
	 */
	public void addAll(${class} ${classVar}) throws Exception{
		super.add(${classVar});
		addSubList(${classVar});
	}
	
	/**
	 * 更新数据
	 * @param ${classVar}
	 * @throws Exception
	 */
	public void updateAll(${class} ${classVar}) throws Exception{
		super.update(${classVar});
		delByPk(${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>());
		addSubList(${classVar});
	}
	
	/**
	 * 添加子表记录
	 * @param ${classVar}
	 * @throws Exception
	 */
	public void addSubList(${class} ${classVar}) throws Exception{
	<#list subtables as subtable>
	<#assign vars=subtable.variable>
	<#assign foreignKey=func.convertUnderLine(subtable.relation) >
	<#assign subPk=func.getPk(subtable)>
	<#assign subPkVar=func.convertUnderLine(subPk)>
		
		List<${vars.class}> ${vars.classVar}List=${classVar}.get${vars.classVar?cap_first}List();
		if(BeanUtils.isNotEmpty(${vars.classVar}List)){
			for(${vars.class} ${vars.classVar}:${vars.classVar}List){
				${vars.classVar}.set<#if subtable.isExternal==0>RefId<#else>${subtable.relation?cap_first}</#if>(${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>());
				Long id=UniqueIdUtil.genId();
				${vars.classVar}.set<#if subtable.isExternal==0>Id<#else>${subtable.pkField?cap_first}</#if>(id);				
				${vars.classVar}Dao.add(${vars.classVar});
			}
		}
	</#list>
	}
	
	<#list subtables as subtable>
	<#assign vars=subtable.variable>
	/**
	 * 根据外键获得${subtable.tableDesc}列表
	 * @param id
	 * @return
	 */
	public List<${vars.class}> get${vars.classVar?cap_first}List(Long id) {
		return ${vars.classVar}Dao.getByMainId(id);
	}
	</#list>
	</#if>
	<#if flowKey?exists>
	/**
	 * 重写getAll方法绑定流程runId
	 * @param queryFilter
	 */
	public List<${class}> getAll(QueryFilter queryFilter){
		List<${class}> ${classVar}List=super.getAll(queryFilter);
		List<${class}> ${classVar}s=new ArrayList<${class}>();
		for(${class} ${classVar}:${classVar}List){
			ProcessRun processRun=processRunService.getByBusinessKey(${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>().toString());
			if(BeanUtils.isNotEmpty(processRun)){
				${classVar}.setRunId(processRun.getRunId());
			}
			${classVar}s.add(${classVar});
		}
		return ${classVar}s;
	}
	
	/**
	 * 重写getMyDraft方法绑定流程runId
	 * @param queryFilter
	 */
	public List<${class}> getMyDraft(Long userId,QueryFilter queryFilter){
		List<${class}> ${classVar}List=super.getAll(queryFilter);
		List<${class}> ${classVar}s=new ArrayList<${class}>();
		for(${class} ${classVar}:${classVar}List){
			ProcessRun processRun=processRunService.getByBusinessKey(${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>().toString());
			if(BeanUtils.isNotEmpty(processRun)){
				${classVar}.setRunId(processRun.getRunId());
			}
			${classVar}s.add(${classVar});
		}
		return ${classVar}s;
	}
		
	
	</#if>
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			${class} ${classVar}=get${class}(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				${classVar}.set<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>(genId);
				<#if subtables?exists && subtables?size != 0>
				this.addAll(${classVar});
				<#else>
				this.add(${classVar});
				</#if>
			}else{
				${classVar}.set<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>(Long.parseLong(cmd.getBusinessKey()));
				<#if subtables?exists && subtables?size != 0>
				this.updateAll(${classVar});
				<#else>
				this.update(${classVar});
				</#if>
			}
			cmd.setBusinessKey(${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>().toString());
		}
	}
	
	/**
	 * 根据json字符串获取${class}对象
	 * @param json
	 * @return
	 */
	public ${class} get${class}(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		<#if subtables?exists && subtables?size != 0>
		Map<String,  Class> map=new HashMap<String,  Class>();
		<#list subtables as subtable>
		<#assign vars=subtable.variable>
		map.put("${vars.classVar}List", ${vars.class}.class);
		</#list>
		${class} ${classVar} = (${class})JSONObject.toBean(obj, ${class}.class,map);
		<#else>
		${class} ${classVar} = (${class})JSONObject.toBean(obj, ${class}.class);
		</#if>
		return ${classVar};
	}
	/**
	 * 保存 ${table.tableDesc} 信息
	 * @param ${classVar}
	 */

    <#if flowKey?exists>
	@WorkFlow(flowKey="${flowKey}",tableName="${table.tableName}")
	</#if>
	public void save(${class} ${classVar}) throws Exception{
		Long id=${classVar}.get<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			${classVar}.set<#if table.isExternal==0>Id<#else>${table.pkField?cap_first}</#if>(id);
			<#if subtables?size != 0>
			this.addAll(${classVar});
			<#else>
		    this.add(${classVar});
		    </#if>
		}
		else{
		   <#if subtables?size != 0>
		   this.updateAll(${classVar});
		   <#else>
		   this.update(${classVar});
		   </#if>
		}
		<#if flowKey?exists>
		BpmResult result=new BpmResult();
		//添加流程变量
//		result.addVariable("", "");
		result.setBusinessKey(id.toString());
		BpmAspectUtil.setBpmResult(result);
		</#if>
	}


/***增删改查
**@param ${classVar}
****/
<#assign List=table.mapList>
<#list List as db_Func>                              
<#if "${db_Func.db_Func_Type}"=="1">
public void ${db_Func.db_Func_Name}(${class} ${classVar}){
	dao. ${db_Func.db_Func_Name}(${classVar});
    }
<#elseif "${db_Func.db_Func_Type}"=="2">                                     
public void ${db_Func.db_Func_Name}(${db_Func.db_Func_ParameterType} ${db_Func.fieldName}){
	dao.${db_Func.db_Func_Name} (${db_Func.fieldName});
	} 
<#elseif "${db_Func.db_Func_Type}"=="3">                                  
public void ${db_Func.db_Func_Name}(${class} ${classVar}){
	dao.${db_Func.db_Func_Name}(${classVar});
	}
<#else>
public List<${class}> ${db_Func.db_Func_Name}(${db_Func.db_Func_ParameterType} ${db_Func.fieldName}){
	dao.${db_Func.db_Func_Name}(${db_Func.fieldName});
	return dao.${db_Func.db_Func_Name}(${db_Func.fieldName});
	}     
</#if>
</#list>
}

<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign tableName=model.tableName>
<#assign subtables=model.subTableList>
<#assign classVar=model.variables.classVar>
<#assign table=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk)>
<#assign pkVarFistUp=pkVar?cap_first >
package ${vars.packagePre}.${system}.service.${package};
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import ${vars.packagePre}.core.web.query.QueryFilter;
import ${vars.packagePre}.platform.service.bpm.ProcessRunService;
import ${vars.packagePre}.platform.model.bpm.ProcessRun;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import ${vars.packagePre}.core.bpm.model.ProcessCmd;
import ${vars.packagePre}.core.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${vars.packagePre}.core.db.IEntityDao;
import ${vars.packagePre}.core.util.BeanUtils;
import ${vars.packagePre}.core.util.UniqueIdUtil;
import ${vars.packagePre}.${system}.model.${package}.${class};
import ${vars.packagePre}.${system}.dao.${package}.${class}Dao;
<#if func.isSubTableExist( subtables)>
	<#list subtables as table>
import ${vars.packagePre}.${system}.model.${table.variables.package}.${table.variables.class};
import ${vars.packagePre}.${system}.dao.${table.variables.package}.${table.variables.class}Dao;
	</#list>
</#if>
<#if func.supportFlow(model)>
import ${vars.packagePre}.platform.service.bpm.ProcessRunService;
import ${vars.packagePre}.core.bpm.model.ProcessCmd;
import ${vars.packagePre}.core.service.WfBaseService;
import ${vars.packagePre}.core.annotion.WorkFlow;
import ${vars.packagePre}.core.bpm.BpmAspectUtil;
import ${vars.packagePre}.core.bpm.BpmResult;
<#else>
import ${vars.packagePre}.core.service.BaseService;
</#if>

/**
 *<pre>
 * 对象功能:${model.tabComment} Service类
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 *</pre>
 */
@Service
public class ${class}Service extends  <#if func.supportFlow(model)>WfBaseService<#else>BaseService</#if><${class}>
{
	@Resource
	private ${class}Dao dao;
	
	<#if func.isSubTableExist( subtables)>
		<#list subtables as table>
	@Resource
	private ${table.variables.class}Dao ${table.variables.classVar}Dao;
		</#list>
	</#if>
	
	<#--直接绑定工作流生成-->
	<#if func.supportFlow(model)>
	@Resource
	private ProcessRunService processRunService;
	</#if>
	
	public ${class}Service()
	{
	}
	
	@Override
	protected IEntityDao<${class}, Long> getEntityDao() 
	{
		return dao;
	}
	
	<#if func.isSubTableExist( subtables)>
	/**
	 * 根据外键删除子表记录
	 * @param ${pkVar}
	 */
	private void delByPk(Long ${pkVar}){
	    <#list model.subTableList as table>
		${table.variables.classVar}Dao.delByMainId(${pkVar});
	    </#list>
	}
	
	/**
	 * 删除数据 包含相应子表记录
	 * @param lAryId
	 */
	@Override
	public void delByIds(Long[] lAryId) {
		for(Long id:lAryId){	
			delByPk(id);
			dao.delById(id);	
			//删除草稿表。
			<#if func.supportFlow(model)>
			processRunService.delByBusinessNum(id);
			</#if>
		}	
	}
	
	/**
	 * 添加数据 
	 * @param ${classVar}
	 * @throws Exception
	 */
	@Override
	public void add(${class} ${classVar}){
		super.add(${classVar});
		addSubList(${classVar});
	}
	
	/**
	 * 更新数据
	 * @param ${classVar}
	 * @throws Exception
	 */
	@Override
	public void update(${class} ${classVar}) {
		super.update(${classVar});
		delByPk(${classVar}.get${pkVar?cap_first}());
		addSubList(${classVar});
	}
	
	/**
	 * 添加子表记录
	 * @param ${classVar}
	 * @throws Exception
	 */
	private void addSubList(${class} ${classVar}) {
	<#list subtables as table>
	<#assign vars=table.variables>
	<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
	<#assign subPk=func.getPk(table)>
	<#assign subPkVar=func.convertUnderLine(subPk)>
		List<${vars.class}> ${vars.classVar}List=${classVar}.get${vars.classVar?cap_first}List();
		if(BeanUtils.isNotEmpty(${vars.classVar}List)){
			for(${vars.class} ${vars.classVar}:${vars.classVar}List){
				${vars.classVar}.set${foreignKey?cap_first}(${classVar}.get${pkVar?cap_first}());
				${vars.classVar}.set${subPkVar?cap_first}(UniqueIdUtil.genId());
				${vars.classVar}Dao.add(${vars.classVar});
			}
		}
	</#list>
	}
	
	<#list subtables as table>
	<#assign vars=table.variables>
	/**
	 * 根据外键获得${table.tabComment}列表
	 * @param ${pkVar}
	 * @return
	 */
	public List<${vars.class}> get${vars.classVar?cap_first}List(Long ${pkVar}) {
		return ${vars.classVar}Dao.getByMainId(${pkVar});
	}
	</#list>
	
	</#if>
	
	<#if func.supportFlow(model)>
	/**
	 * 重写getAll方法绑定流程runId
	 * @param queryFilter
	 */
	public List<${class}> getAll(QueryFilter queryFilter){
		List<${class}> ${classVar}List=super.getAll(queryFilter);
		List<${class}> ${classVar}s=new ArrayList<${class}>();
		for(${class} ${classVar}:${classVar}List){
			ProcessRun processRun=processRunService.getByBusinessKey(${classVar}.get${pkVar?cap_first}().toString());
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
			ProcessRun processRun=processRunService.getByBusinessKey(${classVar}.get${pkVar?cap_first}().toString());
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
				${classVar}.set${pkVar?cap_first}(genId);
				this.add(${classVar});
			}else{
				${classVar}.set${pkVar?cap_first}(Long.parseLong(cmd.getBusinessKey()));
				this.update(${classVar});
			}
			cmd.setBusinessKey(${classVar}.get${pkVar?cap_first}().toString());
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
		<#assign vars=subtable.variables>
		map.put("${vars.classVar}List", ${vars.class}.class);
		</#list>
		${class} ${classVar} = (${class})JSONObject.toBean(obj, ${class}.class,map);
		<#else>
		${class} ${classVar} = (${class})JSONObject.toBean(obj, ${class}.class);
		</#if>
		return ${classVar};
	}
	
	/**
	 * 保存 ${model.tabComment} 信息
	 * @param ${classVar}
	 */
	<#if func.supportFlow(model)>
	@WorkFlow(flowKey="${model.variables.flowKey}",tableName="${tableName}")
	</#if>
	public void save(${class} ${classVar}){
		Long id=${classVar}.get${pkVarFistUp}();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			${classVar}.set${pkVarFistUp}(id);
			this.add(${classVar});
		}
		else{
			this.update(${classVar});
		}
		<#if func.supportFlow(model)>
		BpmResult result=new BpmResult();
		//添加流程变量
//		result.addVariable("", "");
		result.setBusinessKey(id.toString());
		BpmAspectUtil.setBpmResult(result);
		</#if>
	}
	
}

<#assign package=table.variable.package>
<#assign class=table.variable.class>
<#assign classVar=table.variable.classVar>
<#assign fieldList=table.fieldList> 
<#assign List=table.mapList>
package com.hotent.${system}.dao.${package};

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
<#if table.isMain!=1>
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
</#if>

<#if flowKey?exists>
import com.hotent.core.db.WfBaseDao;
<#else>
import com.hotent.core.db.BaseDao;
</#if>

import com.hotent.${system}.model.${package}.${class};

@Repository
public class ${class}Dao extends <#if flowKey?exists>WfBaseDao<#else>BaseDao</#if><${class}>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ${class}.class;
	}

	<#if table.isMain!=1>
	/**
	 * 根据外键获取${table.tableDesc}列表
	 * @param refId
	 * @return
	 */
	public List<${class}> getByMainId(Long refId) {
		return this.getBySqlKey("get${class}List", refId);
	}
	
	/**
	 * 根据外键删除${table.tableDesc}
	 * @param refId
	 * @return
	 */
	public void delByMainId(Long refId) {
		this.delBySqlKey("delByMainId", refId);
	}
	</#if>	
	/**
	 * 添加  删除  修改  查询  
	 
	 * @param 
	 *fieldName
	 * @return
	 */

  <#list List as db_Func>                                         
	<#if "${db_Func.db_Func_Type}"=="1">
	  public void  ${db_Func.db_Func_Name}(${class} ${classVar}) {
	      this.insert("${db_Func.db_Func_Name}",${class?lower_case});
	   }
    <#elseif "${db_Func.db_Func_Type}"=="2">                                      
	   public void ${db_Func.db_Func_Name}(${db_Func.db_Func_ParameterType} ${db_Func.fieldName}) { 
		  this.delBySqlKey("${db_Func.db_Func_Name}",${db_Func.fieldName});
	   }
	<#elseif "${db_Func.db_Func_Type}"=="3">                                  
	   public void ${db_Func.db_Func_Name}(${class} ${classVar}) {
		  this.update("${db_Func.db_Func_Name}",${class?lower_case});
	   }
    <#else>
	   public List<${class}> ${db_Func.db_Func_Name}(${db_Func.db_Func_ParameterType} ${db_Func.fieldName}) {
		  return  this.getBySqlKey("${db_Func.db_Func_Name}", ${db_Func.fieldName});      
	   }
   </#if>
  </#list>
	
}
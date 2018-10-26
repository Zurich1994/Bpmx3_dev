<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign classVar=model.variables.classVar>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign subtables=model.subTableList>
package ${vars.packagePre}.${system}.dao.${package};

import java.util.List;
import org.springframework.stereotype.Repository;
<#if func.supportFlow(model)>
import ${vars.packagePre}.core.db.WfBaseDao;
<#else>
import ${vars.packagePre}.core.db.BaseDao;
</#if>
import ${vars.packagePre}.${system}.model.${package}.${class};
/**
 *<pre>
 * 对象功能:${model.tabComment} Dao类
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 *</pre>
 */
@Repository
public class ${class}Dao extends <#if func.supportFlow(model)>WfBaseDao<#else>BaseDao</#if><${class}>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ${class}.class;
	}

	
	
	<#if sub?exists && sub>
	/**
	 * 根据外键获取子表明细列表
	 * @param ${foreignKey}
	 * @return
	 */
	public List<${class}> getByMainId(Long ${foreignKey}) {
		return this.getBySqlKey("get${class}List", ${foreignKey});
	}
	/**
	 * 根据外键删除子表记录
	 * @param ${foreignKey}
	 * @return
	 */
	public void delByMainId(Long ${foreignKey}) {
		this.delBySqlKey("delByMainId", ${foreignKey});
	}
	</#if>	
	
	
}
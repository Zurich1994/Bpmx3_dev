<#-- 构造出tr内容，及获取数据的表达式 -->
<#if dataModel.hasData>
	<#if dataModel.isList><#-- 返回列表 -->
		<#assign trRecord=dataModel.returnList[0]>
	<#else><#-- 返回单条记录 -->
		<#assign trRecord=dataModel.returnObj>
	</#if>
	<#assign trTemplate>
		<#list trRecord?keys as key>
			<td><#noparse>${obj.</#noparse>${key}<#noparse>}</#noparse></td>
		</#list>
	</#assign>
</#if>
<table name="tableList" id="tableItem" class="table-grid">
	<#noparse>
	<#if dataModel.hasData>
		<#if dataModel.isList><!-- 返回列表 -->
			<#list dataModel.returnList as obj>
	</#noparse>
			<tr>${trTemplate}</tr>
	<#noparse>    		
			</#list>
			<#else><!-- 返回单条记录 -->
				<#assign obj=dataModel.returnObj>
	</#noparse>
				<tr>${trTemplate}</tr>
	<#noparse>
		</#if>
		<#else><!-- 未返回数据，显示原因 -->
		<tr><td>${dataModel.errorMessage!"无返回记录！"}</td></tr>
	</#if>
	</#noparse>
</table>

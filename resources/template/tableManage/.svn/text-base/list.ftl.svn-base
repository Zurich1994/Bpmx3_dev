<#-- 
Name:自定义表管理列表模板
Desc:自定义表管理列表模板
 -->
<#setting number_format="#">
<#noparse><#setting number_format="#"></#noparse>
<#macro genCondition field>
	<#if field.valueFrom==1 >
	<span class="label">${field.comment}:</span>
	<#switch field.fieldType>
		<#case "varchar">
			<input type="text" name="Q_${field.fieldName}_S" class="inputText"  />
		<#break>
		<#case "date">
			<#if field.condition=="=" >
			<input type="text" name="Q_${field.fieldName}_DL" class="Wdate inputText"  />
			<#elseif field.condition=="between" >
			从:
			<input type="text" name="Q_start${field.fieldName}_DL" class="Wdate inputText"  />
			到:
			<input type="text" name="Q_end${field.fieldName}_DG" class="Wdate inputText"  />
			<#else>
			<input type="text" name="Q_${field.fieldName}_DL" class="Wdate inputText"  />
			</#if>
		<#break>
		<#case "number">
			<input type="text" name="Q_${field.fieldName}_DB" class="inputText"  />
		<#break>
		<#default>
	</#switch>
	</#if>
</#macro>


<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${tbarTitle}</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<#if (conditionFields?size>0) >
					<div class="group"><a class="link search" >查询</a></div>
					<div class="l-bar-separator"></div>
				</#if>
			</div>
		</div>
		<#if (conditionFields?size>0) >
			<div class="panel-search">
<#noparse>
				<form name="searchForm" method="post" action="${searchFormURL}">
</#noparse>
					<div class="row">
						<#list conditionFields as field>
							<@genCondition field=field/>
						</#list>
<#noparse>
						<input type="hidden" name="${tableIdCode}__manageid__" value="${__manageid__}"/>
					<#if __pageid__??>
						<input type="hidden" name="__pageid__" value="${__pageid__}"/>
					</#if>
</#noparse>
					</div>
				</form>
			</div>
		</#if>
	</div>
	<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
<#--表头-->
				<tr>
					<#list displayFields as field>
					 	<#noparse><#assign fieldName='</#noparse>${field.fieldName}<#noparse>'></#noparse>
			 			<#noparse><#assign comment='</#noparse>${field.comment}<#noparse>'></#noparse>
					<th>
<#noparse>
						<a href="${pageURL}&${tableIdCode}__ns__=${fieldName}">
							${comment}<#if (sortField?? && sortField=="${fieldName}")><#if (orderSeq=="ASC")>↑<#else>↓</#if></#if>
						</a>
</#noparse>
					</th>
					</#list>
<#----------------------------------------------------------------------
					<th>管理</th>
------------------------------------------------------------------------>
				</tr>
<#--表体-->
				<#noparse><#list dataList as data></#noparse>
				<tr class="<#noparse><#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if></#noparse>">
					<#list displayFields as field>
						<#if (field.fieldType=="date")>
						<td><#noparse><#if data.</#noparse>${field.fieldName}<#noparse>??>${data.</#noparse>${field.fieldName}<#noparse>?string('yyyy-MM-dd HH:mm:ss')}</#if></#noparse></td>
						<#else>
						<td><#noparse>${data.</#noparse>${field.fieldName}<#noparse>}</#noparse></td>
						</#if>
					</#list>
<#----------------------------------------------------------------------
					<td>
						<a href="javascript:;" class="link detail">明细</a>
						<a href="javascript:;" class="link edit">编辑</a>
						<a href="javascript:;" class="link del">删除</a>
					</td>
---------------------------------------------------------------------------->
				</tr>
				<#noparse></#list></#noparse>
			</table>
			<#noparse>${pageHtml}</#noparse>
	</div>

</div>
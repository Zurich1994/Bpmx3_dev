<#setting number_format="#">
<#noparse><#setting number_format="#"></#noparse>
<#macro genCondition field>
	<span class="label">${field.fieldDesc}:</span>
	<#switch field.fieldType>
		<#case "varchar">
			<input type="text" name="Q_${field.fieldName}_S" value="<#noparse>${param.</#noparse>${field.fieldName}}" class="inputText"  />
		<#break>
		<#case "date">
			<#if field.getPropertyMap().condition=="=" >
			<input type="text" name="Q_${field.fieldName}_DL" class="Wdate inputText"   value="<#noparse>${param.</#noparse>${field.fieldName}}" />
			<#elseif field.getPropertyMap().condition=="between" >
			从:
			<input type="text" name="Q_start${field.fieldName}_DL" class="Wdate inputText"  value="<#noparse>${param.</#noparse>${field.fieldName}}" />
			到:
			<input type="text" name="Q_end${field.fieldName}_DG" class="Wdate inputText"   value="<#noparse>${param.</#noparse>${field.fieldName}}" />
			<#else>
			<input type="text" name="Q_${field.fieldName}_DL" class="Wdate inputText"   value="<#noparse>${param.</#noparse>${field.fieldName}}" />
			</#if>
		<#break>
		<#case "number">
			<input type="text" name="Q_${field.fieldName}_DB" class="inputText"   value="<#noparse>${param.</#noparse>${field.fieldName}}" />
		<#break>
		<#default>
	</#switch>
</#macro>

<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${titleStr}列表管理</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<#if (conditionFields?size>0) >
				<div class="group"><a class="link search" >查询</a></div>
				<div class="l-bar-separator"></div>
				</#if>
				<div class="group"><a class="link back" href="myList.ht">返回</a></div>
			</div>
		</div>
		<#if (conditionFields?size>0) >
			<div class="panel-search">
				<form id="searchForm" method="post" action="${ctxPath}/platform/form/bpmTableTemplate/get.ht">
					<div class="row">
						<#list conditionFields as field>
								<@genCondition field=field/>
						</#list>
						<input type="hidden" name="id" value="${templateId}"/>
					</div>
				</form>
			</div>
		</#if>
	</div>
	
	<div class="panel-body">
		
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
				<tr>
					<#list fields as field>
						<#if field.isList == 1 && field.isHidden == 0>
							<th><a href="<#noparse>${preUrl}</#noparse>&newSortField=${field.fieldName}">${field.fieldDesc}<#noparse><#if (sortField=="</#noparse>${field.fieldName}<#noparse>")><#if (orderSeq=="ASC")>↑<#else>↓</#if></#if></#noparse></a></th>
						</#if>
					</#list>
					<th>明细</th>
				</tr>
				<#noparse><#list list as data></#noparse>
				<tr class="<#noparse><#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if></#noparse>">
					<#list fields as field>
						<#if field.isList == 1 && field.isHidden == 0>
							<#if (field.fieldType=="date")>
							<td><#noparse><#if data.</#noparse>${field.fieldName}<#noparse>??>${data.</#noparse>${field.fieldName}<#noparse>?string('yyyy-MM-dd HH:mm:ss')}</#if></#noparse></td>
							<#else>
							<td><#noparse>${data.</#noparse>${field.fieldName}<#noparse>}</#noparse></td>
							</#if>
						</#if>
					</#list>
					<td>
						<a href="detail.ht?formKey=${formKey}&flowRunId=<#noparse>${data.flowrunid_}</#noparse>&id=<#noparse>${data.</#noparse>${pkField}}" class="link detail">明细</a>
						<#noparse><#if data.flowrunid_?? ></#noparse> 
							<a href="../../bpm/processRun/get.ht?runId=<#noparse>${data.flowrunid_}</#noparse>" target="_blank" class="link dataList">查看流程数据</a>
						<#noparse></#if></#noparse>
					</td>
				</tr>
				<#noparse></#list></#noparse>
			</table>
			<#noparse>${pageHtml}</#noparse>
		
	</div>
</div>
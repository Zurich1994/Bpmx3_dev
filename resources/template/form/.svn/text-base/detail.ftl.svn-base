<#setting number_format="#">
<#noparse><#setting number_format="#"></#noparse>
<div class="panel-data">
	<table cellpadding="1" cellspacing="1" class="table-detail">
		<#list fields as field>
		<#if field.isHidden == 0>
		<tr>
			<th width="20%">${field.fieldDesc}:</th>
			<td><#noparse>${data.</#noparse>${columnPrefix}${field.fieldName}<#noparse>}</#noparse></td>
		</tr>
		</#if>
		</#list>
	</table>
	<#list subTables as table>
	<#assign fields = subFields[table.tableName]/>
	<br />
	<table cellpadding="1" cellspacing="1" class="table-grid table-list">
		<tr>
			<th><span style="font-weight:bold;float:left;">${table.tableDesc}</span></th>
		</tr>
		<tr>		
			<td>
					<table cellpadding="1" cellspacing="1" class="table-grid table-list">
						<tr>
							<#list fields as field>
								<#if field.isList == 1 && field.isHidden == 0>
									<th>${field.fieldDesc}</th>
								</#if>
							</#list>
						</tr>
						<#noparse><#list subDatas.</#noparse>${table.tableName}<#noparse>.dataList as data></#noparse>
						<tr class="<#noparse><#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if></#noparse>">
							<#list fields as field>
								<#if field.isList == 1 && field.isHidden == 0>
									<td><#noparse>${data.</#noparse>${columnPrefix}${field.fieldName}<#noparse>}</#noparse></td>
								</#if>
							</#list>
						</tr>
						<#noparse></#list></#noparse>
					</table>
			</td>
		</tr>
	</#list>
</div>
<table style="border-collapse :collapse;width:100%;text-align:center;font-size: 12px;" border="0" cellspacing="0" cellpadding="2">
	<tbody>
		<#-- 展示头 -->
		<tr style="height:32px;text-align: left;background-color: #F0F1F1;padding:4px 4px 4px 4px;border:solid 1px #366092;">
			<#list subTable.fieldList as field>
				<th width="355" style="-ms-word-break:break-all;border:1px solid #666666;text-align: center;height: 25px;font-size:12px;background-color:#F1F6FF;font-weight:bold;">${field.fieldDesc}</th>
			</#list>
		</tr>
		<#-- 数据拼装 二次解释  -->
		<!--
		<#noparse> <#list </#noparse>${subTable.tableName}<#noparse>s as </#noparse>${subTable.tableName}<#noparse>> </#noparse>
		-->
		<tr style="border:1px solid #666666;text-align: center;height: 23px;padding-top: 2px;padding-bottom: 2px;">
			<#list subTable.fieldList as field>
			<td width="355" height="49" style="-ms-word-break:break-all;text-align: left;border:solid 1px #366092;padding:4px 4px 4px 4px;border:1px solid #666666;text-align: center;height: 23px;padding-top: 2px;padding-bottom: 2px;">
				<#noparse>${</#noparse>${subTable.tableName}<#noparse>.</#noparse>${(field.getDbFieldName(table.isExtTable())+"")?lower_case}<#noparse>}</#noparse>
			</td>
			</#list>
		</tr>
		<!--
		<#noparse></#list></#noparse>
		-->
	</tbody>
</table>
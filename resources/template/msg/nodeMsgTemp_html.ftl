
<table cellpadding="2" cellspacing="0" border="1" style="border:1px solid #366092;border-collapse :collapse;width:100%;text-align:center;font-size: 12px;"> 
	<tbody> 
		<tr> 
			<td colspan="2" colspan="${columnNum*2}" style="border:1px solid #666666;text-align: center;font-size: 16px;font-weight: bold;background-color: #888888;height: 32px;color: #fff;">${table.tableDesc}</td> 
		</tr>
		<#list table.fieldList as field>
		<#assign index=field_index/>
		<#if index%columnNum == 0>
		<tr>
		</#if>
			<td align="right" nowrap="nowarp" style="padding:2px;border:1px solid #666666;background-color:#F0F1F1;text-align: right;height: 23px;">${field.fieldDesc}:</td> 
			<td style="padding:2px;border:1px solid #666666;background-color:white;text-align: left;"><#noparse>$</#noparse>{${(field.getDbFieldName(table.isExtTable())+"")?lower_case}}</td> 
		<#if index%columnNum == columnNum-1>
		</tr>
		</#if>
		</#list>
 	</tbody> 
</table>

<#list table.subTableList as subTable>
	<#assign index=subTable_index/>
	
	<#include "nodeMsgTemp_subTable.ftl">

</#list>

<br>
<#function getFields fieldList>
    <#assign btn>
	 	<#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<td fieldName="${field.fieldName }" ></td>
			</#if>
		</#list>
	</#assign>	
	<#return btn>
</#function>
	
<#function getFieldsName fieldList>
    <#assign btn>
	    <#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<th ><span>${field.fieldDesc}</span> </th>
			</#if>
		</#list>
	</#assign>
	<#return btn>
</#function>
<div class="subTableToolBar l-tab-links">
   <a class="link add" href="javascript:;" onclick="return false;" >添加</a>
</div>
<table cellpadding="2" cellspacing="0" border="0" class="listTable" >
	<#assign teamcount=0 />
	<#list teamFields as team>
		<#list team.teamFields as teamfile>
			<#if teamfile.valueFrom != 2>
				<#assign teamcount=teamcount + 1>	
			</#if>
		</#list>
	</#list>
	<#assign count=0 />
	<#list fields as field>
		<#if field.valueFrom != 2>
			<#assign count=count + 1>	
		</#if>
	</#list>
	
    <tr>
    	<td colspan="${count+teamcount}"  class="formHead" >${table.tableDesc }</td>
    </tr>
    <#if teamFields??>
	    <tr>
			<#list teamFields as team>
				<#assign teamcount1=0>	
				<#list team.teamFields as teamfile>
					<#if teamfile.valueFrom != 2>
						<#assign teamcount1=teamcount1 + 1>	
					</#if>
				</#list>
				<#if teamcount1 !=0>
					<td colspan="${teamcount1}" class="teamHead">${team.teamName }</td>
				</#if>
			</#list>
			<#if fields?size != 0>
	    		<td colspan="${count}" class="teamHead">未分组</td>
			</#if>
	    </tr>
    </#if>
	<tr class="headRow">
		<#list teamFields as team>
			${getFieldsName(team.teamFields)}
		</#list>
		${getFieldsName(fields)}
	</tr>
	<tr class="listRow" formType="form">
		<#list teamFields as team>
			${getFields(team.teamFields)}
		</#list>
		${getFields(fields)}
	</tr>
</table>

<table formType="window">
	<#list fields as field>
		<#if field.valueFrom != 2 && field.isHidden == 0>
		<tr>
			<td width="30%" style="padding:2px;">${field.fieldDesc }<#if field.isRequired == 1><span class="required">*</span></#if>:</td>
			<td width="70%" style="padding:2px;"><@input field=field/></td>
		</tr>
		</#if>
	</#list>
	<#list teamFields as team>
			<#assign teamcount2=0>	
				<#list team.teamFields as teamfile>
					<#if teamfile.valueFrom != 2>
						<#assign teamcount2=teamcount2 + 1>	
					</#if>
				</#list>
		<#if teamcount2 !=0>
		<tr class="headRow">
		    <td colspan="2">${team.teamName}:</td>
		</tr>
		</#if>
		<#list team.teamFields as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<tr>
					<td width="30%" style="padding:2px;">${field.fieldDesc }<#if field.isRequired == 1><span class="required">*</span></#if>:</td>
					<td width="70%" style="padding:2px;"><@input field=field/></td>
				</tr>
			</#if>
		</#list>
	</#list>
</table>
<br>
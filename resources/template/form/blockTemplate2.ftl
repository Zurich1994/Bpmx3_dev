<br>
<#function getFieldList fieldList>
 	<#assign rtn>
 		<#assign index=0>
		<#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
			<#if index % 2 == 0>
			<tr>
			</#if>
				<td align="right" style="width:15%;" class="formTitle" nowrap="nowarp" ><span>${field.fieldDesc}</span>:</td>
				<td style="width:35%;" class="formInput">
					<@input field=field/>
				</td>
				<#if index % 2 == 0 && !field_has_next>
				<td style="width:15%;" class="formTitle"></td>
				<td style="width:35%;" class="formInput"></td>
				</#if>
			<#if index % 2 == 1 || !field_has_next>
			</tr>
			</#if>
			<#assign index=index+1>
			</#if>
		</#list>
 	</#assign>
	<#return rtn>
</#function>
<#function setTeamField teams>
 	<#assign rtn>
		 <#list teams as team>
			 <#assign count=0>
			 <#list team.teamFields as teamfile>
					<#if teamfile.valueFrom != 2>
						<#assign count=count + 1>	
					</#if>
				</#list>
				<#if count !=0>
				<tr>
					<td colspan="4" class="teamHead">${team.teamName}</td>
				</tr>
				${getFieldList(team.teamFields)}
				</#if>
				
		</#list>
	</#assign>
	<#return rtn>
</#function>
<div class="subTableToolBar l-tab-links" >
	<a class="link add" href="javascript:;" onclick="return false;" >添加</a>
</div>
<div  formType="edit" class="block">
	<table class="listTable" >
		<tr>
		<td colspan="4"  class="formHead" >${table.tableDesc }</td>
	</tr>
	<#if teamFields??>
		<#if isShow>
			<#if showPosition == 1>
				${setTeamField(teamFields)}
				${getFieldList(fields)}
			<#else>
				${getFieldList(fields)}
				${setTeamField(teamFields)}
			</#if>
		<#else>
			${setTeamField(teamFields)}
		</#if>
	<#else>
		${getFieldList(fields)}
	</#if>
	</table>
</div>
	
<br>

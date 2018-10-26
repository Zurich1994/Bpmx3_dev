<br>
<#function getFieldList fieldList>
 	<#assign rtn>
		<#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<tr>
					<td align="right" nowrap="nowarp" style="width:20%;" class="formTitle" ><span>${field.fieldDesc}</span>:</td>
					<td  class="formInput" style="width:80%;">
						<@input field=field/>
					</td>
				</tr>
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
					<td colspan="2" class="teamHead">${team.teamName}</td>
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
			<td colspan="2"  class="formHead" >${table.tableDesc }</td>
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

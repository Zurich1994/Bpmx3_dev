<script>
function handRowEvent(ev,table){
		$("td.tdNo",table).each(function(i){
			$(this).text(i+1);
		});
	}
</script>
<#setting number_format="0">
 <#function getFieldList fieldList>
	<#assign rtn>
			<#assign index=0>
			<#list fieldList as field>
				<#if  field.isHidden == 0 && field.controlType!= 2 
					&& field.controlType != 9 && field.controlType != 10 
					&& field.controlType != 12 && field.controlType != 20 
					&& field.controlType != 21 && field.controlType != 22>
				  <#if index % 4 == 0>
				  <tr>
				  </#if>
					<td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"  ><span>${field.fieldDesc}</span>:</td>
					<td style="width:15%;" class="formInput"><@input field=field/></td>
					<#if index % 4 == 0 && !field_has_next>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					</#if>			
					<#if index % 4 == 1 && !field_has_next>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					</#if>			
					<#if index % 4 == 2 && !field_has_next>
					<td style="width:10%;" class="formTitle"></td>
					<td style="width:15%;" class="formInput"></td>
					</#if>						
				  <#if index % 4 == 3 || !field_has_next>
				  </tr>
				  </#if>
				<#assign index=index+1>
				</#if>
			</#list>
			
			<#--保持一行：多行文本框(2)、文件上传(9)、富文本框ckeditor(10)、Office控件(12)、流程引用(20)、WebSign签章控件(21)、图片展示控件(22)-->
			<#list fieldList as field>
				<#if field.controlType == 2 || field.controlType == 9 
					|| field.controlType == 10 || field.controlType == 12 
					|| field.controlType == 20 || field.controlType == 21
					|| field.controlType == 22>
					<tr>
						<td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span>${field.fieldDesc}</span></td>
						<td style="width:15%;" class="formInput" colspan="7">
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
				<tr>
					<td colspan="8" class="teamHead">${team.teamName}</td>
				</tr>
				${getFieldList(team.teamFields)}
		</#list>
	</#assign>
	<#return rtn>
</#function>
<br />
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
	<tr>
		<td colspan="8"  class="formHead" >${table.tableDesc }</td>
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
<br />
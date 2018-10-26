<br>
<table class="listTable" >
    <tr class="toolBar">
    	<td colspan="${fields?size +1}" class="toolBar"><a class="link add" href="javascript:;" onclick="return false;">添加</a></td>
    </tr>
	<tr class="headRow">
		<#list fields as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
			<th >${field.fieldDesc } </th>
			</#if>
		</#list>
        <th style="background-color:#F1F6FF;font-weight:bold;">管理</th>
	</tr>
	<tr class="listRow" formType="edit">
		<#list fields as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
			<td >
				<@input field=field/>
			</td>
			</#if>
		</#list>
        <td ><a class="link del" href="javascript:;" title="删除">删除</a></td>
	</tr>
</table>

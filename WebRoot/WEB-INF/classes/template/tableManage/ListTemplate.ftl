<#-- 
Name:自定义表管理列表模板
Desc:自定义表管理列表模板

本模板需要通过2次解析才能得到最终的Html
第一次解析：
*************************************************************
*************************************************************
*数据模型:****************************************************
*************************************************************
*************************************************************

tbarTitle：Tool Bar 的标题

********************************************
conditionFields:条件字段
--joinType：	条件联合类型
--name：	列名
--name：完全指定名
--operate：条件类型: =|>=|<=|….
--comment：注释
--type：	类型
--value：值
--valueFrom：值来源

************************************************************
displayFields：显示字段
--name：列名
--name：完全指定名
--label：别名
--index：索引
--comment：注释
--type：类型

******************************************************
tableIdCode:Table ID Code

**************************************************
displayId: 自定义显示的ID

**************************************************
pageHtml：分页的Html 详见pageAjax.xml

*************************************************
pageURL：当前页面的URL

searchFormURL：搜索表单的Action


sortField：当前排序字段

orderSeq：当前的排序类型

***********************************************
pkcols:主键列

deleteBaseURL：删除一行数据的BaseURL
editBaseURL：编辑一行数据的BaseURL
 -->


<#setting number_format="#">
<#noparse><#setting number_format="#"></#noparse>

<#assign fields=sysTableManage.displayField?eval>
<#assign parameters=sysTableManage.parameters?eval>
<#assign VarMap=sysTableManage.parameterMap>

<#noparse>
<#assign fields=sysTableManage.displayField?eval>
<#assign parameters=sysTableManage.parameters?eval>
<#assign VarMap=sysTableManage.parameterMap>
</#noparse>

<#assign hascond=0>
<#if (parameters?size>0) >
	<#list parameters as param>
		<#if param.vf=="1" >
			<#assign hascond=1>
		</#if>
	</#list>
</#if>

<#macro genCondition field>
	<#if field.vf=="1" >
	<span class="label">${field.cm}:</span>
		<#switch field.ty>
			<#case "D">
				<input type="text" name="Q_${field.na}_D" onfocus="datePicker(this)" class="Wdate inputText"  />
			<#break>
			<#case "DL">
				<input type="text" name="Q_${field.na}_DL" onfocus="datePicker(this)" class="Wdate inputText"  />
			<#break>
			<#case "DR">
				从:
				<input type="text" name="Q_START${field.na}_DL" onfocus="datePicker(this)" class="Wdate inputText"  />
				到:
				<input type="text" name="Q_END${field.na}_DG" onfocus="datePicker(this)" class="Wdate inputText"  />
			<#break>
			<#default>
				<input type="text" name="Q_${field.na}_${field.ty}" class="inputText"  />
			<#break>
		</#switch>
	</#if>
</#macro>

<div class="panel" ajax="ajax" <#noparse> displayId="${sysTableManage.id}" </#noparse> >
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysTableManage.name}</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<#if hascond==1 >
					<div class="group"><a class="link ajaxSearch" href="javascript:;" onclick="handlerSearchAjax(this)">查询</a></div>
					<div class="l-bar-separator"></div>
				</#if>
			</div>
		</div>
		<#if hascond==1 >
			<div class="panel-search">
<#noparse>
				<form name="searchForm" method="post" action="${searchFormURL}">
</#noparse>
					<div class="row">
						<#list parameters as field>
							<@genCondition field=field/>
						</#list>
					</div>
				</form>
			</div>
		</#if>
	</div>
	<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
<#--表头-->
				<tr>
					<#list fields as field>
						<#if field.ds>
						 	<#noparse><#assign name="</#noparse>${field.na}<#noparse>"></#noparse>
				 			<#noparse><#assign comment="</#noparse>${field.cm}<#noparse>"></#noparse>
							<th>
								<#if field.ty!="clob">
									<#noparse>
									<a href="javascript:;" onclick="linkAjax(this)" action="${pageURL}&${tableIdCode}__ns__=${name}">
										${comment}<#if (sortField?? && sortField=="${name}")><#if (orderSeq=="ASC")>↑<#else>↓</#if></#if>
									</a>
									</#noparse>
								<#else>
								   ${field.cm}
								</#if>
							</th>
						</#if>
					</#list>
					<#if (editable && pkcols?? && pkcols?size>0)>
					<th>管理</th>
					</#if>
				</tr>
<#--表体-->
				<#noparse><#list sysTableManage.list as data></#noparse>
				<tr class="<#noparse><#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if></#noparse>">
					<#list fields as field>
						<#if (field.ds)>
							<#if (field.ty=="date")>
								<td><#noparse><#if data.</#noparse>${field.na}<#noparse>??>${data.</#noparse>${field.na}<#noparse>?string("yyyy-MM-dd HH:mm:ss")}</#if></#noparse></td>
							<#else>
								<td><#noparse>${data.</#noparse>${field.na}<#noparse>}</#noparse></td>
							</#if>
						</#if>
					</#list>
					<#if (editable && pkcols?? && pkcols?size>0)>
					<td>
						<a href="javascript:;" onclick="openLinkDialog({scope:this,isFull:true})" action="${editBaseURL}?&__displayId__=<#noparse>${sysTableManage.id}</#noparse><#list pkcols as col>&__pk__${col.name}=<#noparse>${data.</#noparse>${col.name}<#noparse>}</#noparse></#list>" class="link edit">
							编辑
						</a>
						<a href="${deleteBaseURL}?&__displayId__=<#noparse>${displayId}</#noparse><#list pkcols as col>&__pk__${col.name}=<#noparse>${data.</#noparse>${col.name}<#noparse>}</#noparse></#list>" class="link del">
							删除
						</a>
					</td>
					</#if>
				</tr>
				<#noparse></#list></#noparse>
			</table>
			<#noparse>
				<#if sysTableManage.list?size==0>
					<div style="padding:6px 0px 12px 0px">当前没有记录。<div>
				</#if>
			</#noparse>
			<#noparse>${pageHtml}</#noparse>
	</div>

</div>
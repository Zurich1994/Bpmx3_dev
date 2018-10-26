<#-- 
Name: 数据列表模板
Desc:数据列表模板

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
<#assign displayFields=sysQuerySetting.displayField?eval>
<#assign conditionFields=sysQuerySetting.conditionField?eval>
<#assign filterFields=sysQuerySetting.filterField?eval>
<#assign manageFields=manageJsonAry?eval>
<#noparse>
<#setting number_format="#">
<#assign displayFields=sysQuerySetting.displayField?eval>
<#assign conditionFields=sysQuerySetting.conditionField?eval>
<#assign filterFields=sysQuerySetting.filterField?eval>
</#noparse>
<#--日期选择器 -->
<#macro genQueryDate field>
		<#switch field.qt>
			<#case "D">
				<input type="text" name="Q_${field.na}_D" readonly="readonly"  class="wdateTime inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_D'<#noparse>]}"</#noparse>  />
			<#break>
			<#case "DL">
				<input type="text" name="Q_${field.na}_DL" readonly="readonly"  class="wdateTime inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_DL'<#noparse>]}"</#noparse> />
			<#break>
			<#case "DG">
				<input type="text" name="Q_${field.na}_DG"  readonly="readonly" class="wdateTime inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_DG'<#noparse>]}"</#noparse>  />
			<#break>
			<#case "DR">
				从:
				<input type="text" name="Q_begin${field.na}_DL" readonly="readonly" class="wdateTime inputText" value="<#noparse>${param[</#noparse>'Q_begin${field.na}_DL'<#noparse>]}"</#noparse>  />
				</li><li>到:
				<input type="text" name="Q_end${field.na}_DG" readonly="readonly" class="wdateTime inputText" value="<#noparse>${param[</#noparse>'Q_end${field.na}_DG'<#noparse>]}"</#noparse> />
			<#break>
			<#default>
				<input type="text" name="Q_${field.na}_${field.qt}" class="inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse> />
			<#break>
		</#switch>
</#macro>
<#--用户选择器 -->
<#macro genQuerySelector field>
		<#switch field.ct>
			<#case "4"><#--用户单选选择器 -->
				<input type="hidden" name="Q_${field.na}ID_L" class="inputText" id="${field.na}ID"  value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" readonly="readonly" class="inputText" name="Q_${field.na}~_SL" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectUser({self:this});" value="...">
			<#break>
			<#case "5"><#--角色多选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_S" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="Q_${field.na}~_S" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectRole({self:this,isSingle:false});" value="...">
			<#break>
			<#case "6"><#--组织多选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_S" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="Q_${field.na}~_S" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectOrg({self:this,isSingle:false});" value="...">
			<#break>
			<#case "7"><#--岗位多选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_S" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="Q_${field.na}~_S" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectPos({self:this,isSingle:false});" value="...">
			<#break>
			<#case "8"><#--人员多选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_S" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" style="width: 250px" readonly="readonly" class="inputText" name="Q_${field.na}~_S" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectUser({self:this,isSingle:false});" value="...">
			<#break>
			<#case "17"><#--角色单选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_L" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" readonly="readonly" class="inputText" name="Q_${field.na}~_SL" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectRole({self:this});" value="...">
			<#break>
			<#case "18"><#--组织单选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_L" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" readonly="readonly" class="inputText" name="Q_${field.na}~_SL" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectOrg({self:this});" value="...">
			<#break>
			<#case "19"><#--岗位单选选择器 -->
				<input  type="hidden" name="Q_${field.na}ID_L" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" readonly="readonly" class="inputText" name="Q_${field.na}~_SL" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectPos({self:this});" value="...">
			<#break>
			<#default>
				<input  type="hidden" name="Q_${field.na}ID_L" id="${field.na}ID" value="<#noparse>${param[</#noparse>'Q_${field.na}ID_L'<#noparse>]}"</#noparse>>
				<input type="text" readonly="readonly" class="inputText" name="Q_${field.na}~_SL" id="${field.na}" value="<#noparse>${param[</#noparse>'Q_${field.na}~_SL'<#noparse>]}"</#noparse>>
				<input type="button" onclick="__Selector__.selectUser({self:this});" value="...">
			<#break>
		</#switch>
</#macro>
<#-- 选择器 -->
<#macro genQuerySelect field formatData>
	<select  name="Q_${field.na}_${field.qt}">
		<option value="">全部</option>
	<#if formatData?if_exists >
		<#list formatData?keys as key>
		<option value="${key}" <#noparse><#if param['</#noparse>Q_${field.na}_${field.qt}'] == '${key}'>selected<#noparse></#if></#noparse>>${formatData[key]}</option>
		</#list>
	</#if>
	</select>
</#macro>


<#--生成查询条件宏 -->
<#macro genCondition field formatData>
	<#if field.vf=="1" >
			<span class="label">${field.cm}:</span>		
		<#switch field.ct>
			<#case "1">
				<#if field.ty == 'number'>
				<input type="text" name="Q_${field.na}_${field.qt}" class="inputText"  value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse>  validate="{number:true}" />
				<#else>
				<input type="text" name="Q_${field.na}_${field.qt}" class="inputText"  value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse>  />
				</#if>
			<#break>
			<#-- 选择器 -->
			<#case "3"><#--数据字典-->
				<#if formatData[field.na]?if_exists >
					<@genQuerySelect field=field formatData=formatData[field.na]/>
				<#else>
					<input type="text" name="Q_${field.na}_${field.qt}" class="inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse> />
				</#if>
			<#break>
			<#case "4">
			<#case "5">
			<#case "6">
			<#case "7">
			<#case "8">
			<#case "17">
			<#case "18">
			<#case "19">
				<@genQuerySelector field=field/>
			<#break>
			<#case "15"><#--日期选择器 -->
				<@genQueryDate field=field/>
			<#break>
			<#case "11"><#--下拉选项-->
				<#if formatData[field.na]?if_exists >
					<@genQuerySelect field=field formatData=formatData[field.na]/>
				<#else>
					<input type="text" name="Q_${field.na}_${field.qt}" class="inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse> />
				</#if>
			<#break>
			<#default>
				<input type="text" name="Q_${field.na}_${field.qt}" class="inputText" value="<#noparse>${param[</#noparse>'Q_${field.na}_${field.qt}'<#noparse>]}"</#noparse> />
			<#break>
		</#switch>
	</#if>
</#macro>

<#--管理列-->
<#macro genManage manageFields >
	<#--按钮-->
	<#if manageFields?if_exists>
		<#list manageFields as manage>
			<#if manage.name != 'exportButton' >
				<a action="${manage.urlAndParam ?replace('[','${')?replace(']','}') }" onclick="openLinkDialog({scope:this,isFull:true})" href="javascript:;">${manage.desc}</a>
			</#if>
		</#list>
	</#if>
</#macro>
<#--顶部按钮-->
<#macro genToolBar manageFields>
	<#--导出-->
	<#if manageFields?if_exists>
		<#list manageFields as manage>
			<#if manage.name == 'exportButton' >
				<div class="group"> <div class="exportMenu"></div> </div>
				<div class="l-bar-separator"></div>
			</#if>
		</#list>
	</#if>
</#macro>
<#--过滤条件-->
<#noparse><#if filterFields?if_exists>
<div class="panel" ajax="ajax"  displayId="${sysQuerySetting.id}" filterKey="${filterKey}" >
<#if filterFields?size gt 1>
<div class='panel-nav'>
	<div class="l-tab-links">
		<ul style="left: 0px; ">
			<#list filterFields as field>
			<li tabid="${field.key}" <#if field.key ==filterKey> class="l-selected"</#if>>
				<a href="${field.url}" title="${field.name}">${field.desc}</a>
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>
</#noparse> 
<#--查询头-->
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysQuerySetting.name}</span>
		</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<#if hasCondition>
						<div class="group"><a class="link ajaxSearch" href="javascript:;" onclick="handlerSearchAjax(this)"><span></span>查询</a></div>
						<div class="l-bar-separator"></div>
					</#if>
					<@genToolBar manageFields=manageFields />
				</div>
			</div>
		<#if hasCondition >
			<div class="panel-search">
				<form id="searchForm" name="searchForm" method="post" action="<#noparse>${searchFormURL}</#noparse>">
				<#--查询条件-->
					<#if conditionFields?if_exists>
						<ul class="row">
						<#list conditionFields as field>
							<li>
								<@genCondition field=field formatData=formatData/>
							</li>
						</#list>
						</ul>
					</#if>
				</form>
			</div>
		</#if>
	</div>
<#--表头-->	
	<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
				<tr>
              		<#-- 显示字段-->
					<#list displayFields as field>
				 			<#noparse><#if permission.</#noparse>${field.name}<#noparse>></#noparse>
				 				<th>
							<#if field.type!="clob">
								<#noparse>
								<a href="javascript:;" onclick="linkAjax(this)" action="${pageURL}&${tableIdCode}__ns__=</#noparse>${field.name}<#noparse>"></#noparse>
									${field.desc}<#noparse><#if (sortField?? && sortField=="</#noparse>${field.name}<#noparse>")><#if (orderSeq=="ASC")>↑<#else>↓</#if></#if>
								</a>
								</#noparse>
							<#else>
									${field.desc}	
							</#if>
								</th>
								<#noparse></#if></#noparse>
					</#list>
					<#if hasManageButton>
						<th width="150px">管理</th>
					</#if>
				</tr>
<#--表体-->				
				<#noparse>
					<#list sysQuerySetting.list as data>
					<tr class="<#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if>">
				</#noparse>
					<#list displayFields as field>
						<#noparse><#if permission.</#noparse>${field.name}<#noparse>></#noparse>
							<td>
								<#noparse>${data.</#noparse>${field.name}<#noparse>}</#noparse>
							</td>	
						<#noparse></#if></#noparse>
					</#list>
					
					<#if hasManageButton>
						<td  class="rowOps">
							<@genManage manageFields=manageFields />
						</td>
					</#if>
					</tr>
				<#noparse></#list></#noparse>
			</table>
			<#noparse>
				<#if sysQuerySetting.list?size==0>
					<div class="panel-norecord">
						<div class="l-panel-bbar-inner">
							<div style="padding:6px 0px 12px 0px;">当前没有记录。</div>
						</div>
					</div>
				</#if>
			${pageHtml}</#noparse>
	</div>
</div>

<form id="exportForm" name="exportForm" method="post" target="download" action="<#noparse>${ctx}</#noparse>/platform/system/sysQuerySetting/exportData.ht" style="display: none;"></form>  
<iframe id="download" name="download" height="0px" width="0px" style="display: none;"></iframe>  	 

<#noparse>
<#else>
   <div style="padding:6px 0px 12px 0px;">当前用户没有满足的过滤条件,请设置过滤条件。<div>
</#if>
</#noparse> 
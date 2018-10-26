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
--label：完全指定名
--operate：条件类型: =|>=|<=|….
--comment：注释
--type：	类型
--value：值
--valueFrom：值来源

************************************************************
displayFields：显示字段
--name：列名
--label：完全指定名
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


 -->
<#setting number_format="#">
<#noparse><#setting number_format="#"></#noparse>

<#assign fields=sysCustomDisplay.fieldSetting?eval>
<#assign conditions=sysCustomDisplay.conditionField?eval>
<#assign setting=sysCustomDisplay.setting?eval>

<#noparse>
<#assign displayURL="${ctx}/platform/system/sysCustomDisplay/getDisplay.ht">
<#if pageParams?length \gt 0 >
<#assign displayURLP="${ctx}/platform/system/sysCustomDisplay/getDisplay.ht?${pageParams}">
<#else>
<#assign displayURLP="${ctx}/platform/system/sysCustomDisplay/getDisplay.ht">
</#if>
</#noparse>
<#macro genCondition field>
	<#if field.valueFrom==1 >
	<span class="label">${field.comment}:</span>
	<#switch field.type>
		<#case "varchar">
			<input type="text" name="Q_${field.label}_S" class="inputText"  />
		<#break>
		<#case "date">
			<#if field.operate=="=" >
			<input type="text" name="Q_${field.label}_DL" class="Wdate inputText"  />
			<#elseif field.oprerate=="between" >
			从:
			<input type="text" name="Q_start${field.label}_DL" class="Wdate inputText"  />
			到:
			<input type="text" name="Q_end${field.label}_DG" class="Wdate inputText"  />
			<#else>
			<input type="text" name="Q_${field.label}_DL" class="Wdate inputText"  />
			</#if>
		<#break>
		<#case "number">
			<input type="text" name="Q_${field.label}_DB" class="inputText"  />
		<#break>
		<#case "int">
			<input type="text" name="Q_${field.label}_DB" class="inputText"  />
		<#break>
		<#default>
	</#switch>
	</#if>
</#macro>


<div class="panel" ajax='ajax' displayId="${sysCustomDisplay.id}">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysCustomDisplay.name}</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<#if (conditions?size>0) >
					<div class="group"><a class="link ajaxSearch" href="javascript:;" onclick="handlerSearchAjax(this)">查询</a></div>
					<div class="l-bar-separator"></div>
				</#if>
			</div>
		</div>
		<#if (conditions?size>0) >
			<div class="panel-search">
<#noparse>
				<form name="searchForm" method="post" action="${displayURL}">
</#noparse>
					<div class="row">
						<#list conditions as field>
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
					<#list setting as field>
					 	<#noparse><#assign name='</#noparse>${field.label}<#noparse>'></#noparse>
			 			<#noparse><#assign comment='</#noparse>${field.comment}<#noparse>'></#noparse>
					<th>
					<#if field.type!="clob">
<#noparse>
						<a href="javascript:;" onclick="linkSortAjax(this,'${tableIdCode}')" action="${displayURLP}" sort="${name}">
							${comment}<#if (sortField?? && sortField=="${name}")><#if (orderSeq=="ASC")>↑<#else>↓</#if></#if>
						</a>
</#noparse>
					<#else>
					   ${field.comment}
					</#if>

					</th>
					</#list>
				</tr>
<#--表体-->
				<#noparse><#list dataMaps as data></#noparse>
				<tr class="<#noparse><#if data_index % 2 == 0>odd</#if><#if data_index % 2 == 1>even</#if></#noparse>">
					<#list setting as field>
						<#if (field.type=="date")>
						<td><#noparse><#if data['</#noparse>${field.label}<#noparse>']??>${data['</#noparse>${field.name}<#noparse>']?string('yyyy-MM-dd HH:mm:ss')}</#if></#noparse></td>
						<#else>
						<td><#noparse>${data['</#noparse>${field.label}<#noparse>']}</#noparse></td>
						</#if>
					</#list>
				</tr>
				<#noparse></#list></#noparse>
			</table>
			<#noparse>
			<input type="hidden" id="sortField${tableIdCode}" name="sortField" value="${sortField!''}"/>
			<input type="hidden" id="orderSeq${tableIdCode}" name="orderSeq" value="${orderSeq!''}"/>
			</#noparse>
			<#noparse>
				<#if paged>
					<div class="panel-page">		
						<div class="l-panel-bbar-inner">
							<div class="l-bar-group l-bar-selectpagesize">
								每页记录&nbsp;<select id="pageSize" name="pageSize" onchange="changePageSizeAjax(this,'${tableIdCode}');" class="select_short">
									<option value="5" <#if pageBean.pageSize==5> selected="selected" </#if>>5</option>
									<option value="10" <#if pageBean.pageSize==10> selected="selected" </#if>>10</option>
									<option value="15" <#if pageBean.pageSize==15> selected="selected" </#if>>15</option>
									<option value="20" <#if pageBean.pageSize==20> selected="selected" </#if>>20</option>
									<option value="50" <#if pageBean.pageSize==50> selected="selected" </#if>>50</option>
									<option value="100" <#if pageBean.pageSize==100> selected="selected" </#if>>100</option>
								</select>
							</div>
							<div class="l-bar-separator"></div>
							<div class="l-bar-group">
								<div class="l-bar-button l-bar-btnfirst">
									<a href="javascript:;" onclick="firstAjax(this,'${tableIdCode}')" title="首页">
										<span class=""></span>
									</a> 
								</div>
								<div class="l-bar-button l-bar-btnprev">
									<a href="javascript:;" onclick="previousAjax(this,'${tableIdCode}');" title="上一页">
										<span class=""></span>
									</a>
								</div>
							</div>
							<div class="l-bar-separator"></div>
							<div class="l-bar-group">
								<span class="pcontrol"> 
									<input size="4" value="${pageBean.currentPage}" style="width: 20px;text-align:center" maxlength="3" class="inputText" type="text" id="navNum${tableIdCode}" name="navNum"/>/ <span>${pageBean.totalPage}</span>
								</span>
							</div>
							<div class="l-bar-separator"></div>
							<div class="l-bar-group">
								<div class="l-bar-button l-bar-btnnext">
									<a href="javascript:;" onclick="nextAjax(this,'${tableIdCode}')" title="下一页">
										<span></span>
									</a>
								</div>
								<div class="l-bar-button l-bar-btnlast">
									<a href="javascript:;" onclick="lastAjax(this,'${tableIdCode}')" title="尾页">
										<span></span>
									</a>
								</div>
							</div>
							<div class="l-bar-separator"></div>
							<div class="l-bar-group">
								<span>
									<input type="button" id="btnGo" value="GO" class="btn-go" onclick="jumpToAjax(this,'${tableIdCode}');"/>
								</span>
							</div>
							<div class="l-bar-separator"></div>
							<div class="l-bar-group">
								<div class="l-bar-button l-bar-btnload">
									<a href="javascript:;" onclick="refreshAjax(this,'${displayURLP}')">
										<span class=""></span>
									</a>
								</div>
							</div>
								<div class="l-bar-separator"></div>
								<div class="l-bar-group" >
									<span class="l-bar-text">显示记录从${pageBean.first+1}到${pageBean.last}，总数 ${pageBean.totalCount}条</span>
								</div>
								<div class="l-clear"></div>
							<input type="hidden" id="currentPage${tableIdCode}" name="currentPage" value="${pageBean.currentPage}"/>
							<input type="hidden" id="totalPage${tableIdCode}" name="totalPage" value="${pageBean.totalPage}"/>
							<input type="hidden" id="oldPageSize${tableIdCode}" name="oldPageSize" value="${pageBean.pageSize}"/>
							<a id="_nav${tableIdCode}" href="${displayURLP}" style="display:none" ></a>
						</div>
					</div>
				</#if>
			</#noparse>
	</div>

</div>
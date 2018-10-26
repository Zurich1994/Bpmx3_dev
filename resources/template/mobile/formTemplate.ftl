<#setting number_format="0">
<#--获取字段信息 -->

<#function getSubFiledNames fieldList>
	<#assign rtn>
		<#list fieldList as field>
			'${field.fieldName?lower_case}'<#if field_has_next>,</#if>
		</#list>
	</#assign>
	<#return rtn>
</#function>
<#function getSubFiledDesc fieldList>
	<#assign rtn>
		<#list fieldList as field>
			'${field.fieldDesc}'<#if field_has_next>,</#if>
		</#list>
	</#assign>
	<#return rtn>
</#function>
<#function getFieldList fieldList>
 	<#assign rtn>
		<#list fieldList as field>
			<#if  field.isHidden == 0>
					<#if  field.controlType== 13 ||  field.controlType== 14>
					   <div class="ub ub-ver ub-f1">
		               	 <div class="uba b-gra2">
		                    <div class="uba_03">
		                        <div class="uinn-a6 t-gra3 ulev-app3 mwd10">
		                            <span i18nkey="${field.fieldName}">${field.fieldDesc}</span>
		                        </div>
					           	<@input field=field/> 
					         </div>
					      </div>
					   </div>
					<#else>
               		 <div class="b-gra2">
	                    <ul class="ub ubb b-ddd t-bla ub-ac uinn-a9 c-wh">
	                        <li class="f-sz10625 t-111 mwd10 ">
								<span i18nkey="${field.fieldName}">${field.fieldDesc}</span>
			        		</li>
			        		<li class="ub-f1 ulev-app1 t-888 t-right umar-l1 mar-t-03">
								<@input field=field/>
							</li>
	                    </ul>
                  </div>
                </#if>
			</#if>
		</#list>
 	</#assign>
	<#return rtn>
</#function>

<#--获取分组信息 -->
<#function getTeam teams>
 	<#assign rtn>
		 <#list teams as team>
		 	<#if team.teamName?if_exists>
            	 <div class="title">
                    	  <div class="form-tit ut-s">
           	  ${team.teamName}
               			  </div>
                 </div>
			</#if>
			 <div class="mar-lr0625 <#if team.teamName?if_exists><#else>mar-t0625</#if>">
				${getFieldList(team.teamFields)}
		 	<#if team.teamName?if_exists>
		 	  	</div>
		 	</#if>
		</#list>
	</#assign>
	<#return rtn>
</#function>
<div class="wrapper c-eee" id="mainWindow">
     <div class="c-eee"> 
			 <div class="f-sz10625 center t-111  c-ca w100 pad-t0625  pad-b0625" >${title}</div>
			<#--设置主表分组-->
			<#if teamFields??>
				${getTeam(teamFields)}
			</#if>
			<#--设置子表-->
			<#if subTables??>
						<#list subTables as subTable>
							 <div class="subTable" tablename="${subTable.tableName?lower_case}"  permission-bind="permission.table.${subTable.tableName}" formtype="subTable" >
	                            <div class="t-111 c-ca  ub ub-ac " ng-click="openSubPopver('${subTable.tableName}');" ontouchstart="zy_touch('')">
	                                <div class="ub-f1 mar0625 t-111 f-s10625 ">${subTable.tableDesc }</div>
	                                 <div class="ub" ontouchstart="zy_touch('c-for')" ng-click="rtClick();">
				                        <div class="t-right" >
				                            <li class="f-sz16 icon-plus-square umar-r05 t-51aeee" >
				                            </li>
				                        </div>
				                    </div>
	                            </div>
							 <div class="ub ub-f1 uba3px b-e1e1e1 uof-x scrollers" formtype="subTable">
	                            <table class="ub scrollerW">
	                                <tr class="ub ub-f1">
	                                	<td class="ubr uba ub b-ddd t-bla ub-ac uinn-a9 c-gra2 mwd10">
	                                      	  序号 
	                                    </td>
	                                	<td class="ubr ub-trb ub b-ddd t-bla ub-ac uinn-a9 c-gra2 mwd10"  ng-repeat="filedName in [${getSubFiledDesc(subTable.fieldList)}]">
	                                		{{filedName}}
	                                	</td>
									</tr>
									 <tr class="ub ub-f1" ng-repeat="trs in sub['${subTable.tableName?lower_case}']" ng-click="editSubTableCol('${subTable.tableName?lower_case}',$index)">
									    <td class="ubr ub-rbl ub b-ddd t-bla ub-ac uinn-a9 c-wh mwd10" >
	                                        		{{$index+1}}
	                                    </td>
									 	<td class="ubr ub-rb ub b-ddd t-bla ub-ac uinn-a9 c-wh mwd10 ut-s"  ng-repeat="filedName in  [${getSubFiledNames(subTable.fieldList)}]">
	                                        		<span ng-bind-html="trs[filedName] | turn_to_subdata" class="mar-r0625" filedName="item.${filedName}"></span>
	                                    </td>
	                                 </tr>
								</table>
							 </div>
						</#list>
			</#if>
	</div>
</div>
</div>
</div>
</div>
<#if subTables??>
<div ng-controller="subTableCtrl">
<#list subTables as subTable>
 <div id="${subTable.tableName?lower_case}" class="wrapper uhide z-index9999" >
 	<div class="scrollerH h100 mar-t3375">
 		<div class="mar-lr0625">
 			 ${getFieldList(subTable.fieldList)}
	 	</div>
 	</div>
 	<div class="uh t-bla ub ub-ac b-e1 ubb h-275 c-wh subAddTitle z-index9999">
         <div class="ub h100 w44 t-default" ontouchstart="zy_touch('c-for')" onclick="HT.hideSubTableIframe(event,'0');">
                        <div class="ub ub-f1 ub-ac  ">
                             <li class="icon-chevron-left2 f-sz1 t-50A1FF mar-l125">
                            </li>
                        </div>
         </div>
        <div class="ut ub-f4 ulev0 ut-s tx-c" title="${subTable.tableDesc}">添加${subTable.tableDesc}</div>
        <div class="ub t-default h100 w44" ontouchstart="zy_touch('c-foc')" onclick="HT.saveSubtableDate(event);">
            <div class="ub ub-f1 ub-ac center">
                <li class="f-sz1 t-50A1FF">
                    确定
                </li>
            </div>
        </div>
    </div>
 </div>
 </#list>
 </div>
</#if>

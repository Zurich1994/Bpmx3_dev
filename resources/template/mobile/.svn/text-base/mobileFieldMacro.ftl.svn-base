<#--字段宏-->
<#setting number_format="0">
<#function getSelectOptionArrs field>
	<#assign rtn>
		{
		<#list field.aryOptions?keys as optkey>
			'${optkey}':'${field.aryOptions[optkey]}'<#if optkey>,</#if>
		</#list>
		}
	</#assign>
	<#return rtn>
</#function>
<#macro input field>
		<#if field.fieldType == "varchar"><#---字符串类型-->
			<#switch field.controlType>
				<#case 1><#--单行文本框-->
                     <textarea controltype="input"  type="text" ng-model="${field.ngModel}" permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}" lablename="${field.fieldDesc}"   validate="{<#if field.isRequired == 1>required:true</#if>}" class="uc-a1 mr42"></textarea>
				<#break>
				<#case 2><#--多行文本框-->		
                     <textarea controltype="textarea" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"   validate="{<#if field.isRequired == 1>required:true</#if>}"  class="uc-a1 mr42 h4em"></textarea>
				<#break>
				<#case 3><#--数据字典-->
					 <input controltype="dicCombo" class="dicComboTree" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"  nodeKey="${field.dictType}" value="" validate="{<#if field.isRequired == 1>required:true</#if>}" height="200" width="125"/>
				<#break>
				<#case 4><#--人员选择器(单选)-->
                     <selector type="user" multi="false" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}" refid="${field.ngModel}id" validate="{<#if field.isRequired == 1>required:true</#if>}" write="true" />	
				<#break>
				<#case 5><#--角色选择器(多选)-->
                     <selector type="role" multi="true"  ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  refid="${field.ngModel}id" validate="{<#if field.isRequired == 1>required:true</#if>}" write="true" multi="true"/>
				<#break>
				<#case 6><#--组织选择器(多选)-->
                     <selector type="org"  multi="true"  ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"   refid="${field.ngModel}id" validate="{<#if field.isRequired == 1>required:true</#if>}" write="true" multi="true"/>
				<#break>
				<#case 7><#--岗位选择器(多选)-->
                     <selector type="pos"  multi="true"  ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" refid="${field.ngModel}id" validate="{<#if field.isRequired == 1>required:true</#if>}" write="true" multi="true"/>		
                 <#break>
				<#case 8><#--人员选择器(多选)-->
                     <selector type="user" multi="true"  ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" refid="${field.ngModel}id" validate="{<#if field.isRequired == 1>required:true</#if>}" write="true" multi="true"/>
				<#break>
				<#case 9><#--文件上传-->
                     <file   name="${field.newName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  ng-model="${field.ngModel}" permission-bind="permission.field.${field.fieldName}" validate="{<#if field.isRequired == 1>required:true</#if>}"  readonly="readonly"></file>	
				<#break>
				<#case 10><#--富文本框ckeditor-->
				     <textarea controltype="ckeditor" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"   validate="{<#if field.isRequired == 1>required:true</#if>}"  class="uc-a1 mr42 h4em"></textarea>
				<#break>
				<#case 11><#--下拉选项-->
					<select  controltype="select" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" select-arrs="${getSelectOptionArrs(field)}" >
						<#list field.aryOptions?keys as optkey>
						<option value="${optkey}">${field.aryOptions[optkey]}</option>
						</#list>
					</select>
				<#break>
				<#case 12><#--Office控件-->
					<input controltype="office"  type="hidden" class="hidden" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  value="" />
				<#break>
				<#case 13><#--复选框-->
				      <div class="ub ub-ver ub-f1 pad-t03">
                        <div class="uba b-gra2">
							<#list field.aryOptions?keys as optkey>
					          <ul class="ubb ub b-gra2 t-bla ub-ac uinn-a9  c-wh">
                                <li class="ut-s t-gra3 ulev-app2 mwd10">
  									${field.aryOptions[optkey]}
                                </li>
                                <li class="ub-f1 ulev-app1 t-888 t-right umar-l1 mar-t-03">
                                       <input controltype="checkbox" type="checkbox" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" check-value-bind="${field.ngModel}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"   value="${optkey}" >
                                </li>
                              </ul>
							</#list>
						 </div>
            		</div>
				<#break>
				<#case 14><#--单选按钮-->
			      	<div class="ub ub-ver ub-f1 pad-t03">
                      <div class="uba b-gra2">
						<#list field.aryOptions?keys as optkey>
						  <ul class="ubb ub b-gra2 t-bla ub-ac uinn-a9 c-wh">
                                <li class="ut-s t-gra3 ulev-app2 mwd10">
                                  ${field.aryOptions[optkey]}
                                </li>
                                <li class="ub-f1 ulev-app1 t-888 t-right umar-l1 mar-t-03">
                                    <input  controltype="radio" type="radio" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  value="${optkey}" >
                                </li>
                            </ul>
						</#list>
						</div>
					</div>
				<#break>
				<#case 15><#--日期控件-->
                   <input controltype="date" type="text"  class="uc-a1 mr42" readonly="readonly" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>"  dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>"  readonly="readonly">                 
				<#break>
				<#case 16><#--隐藏域-->
					<input controltype="hidden" type="hidden" ng-model="${field.ngModel}" name="${field.newName}" permission-bind="permission.field.${field.fieldName?lower_case}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  value="" >
				<#break>
				<#case 17><#--角色选择器(单选)-->
                    <selector type="role" multi="false" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" refid="${field.ngModel}id" write="true"/>	
               	<#break>
				<#case 18><#---组织选择器(单选)-->
                   <selector type="org"   multi="false" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" refid="${field.ngModel}id" write="true"/>	
				<#break>
				<#case 19><#--岗位选择器(单选)-->
                   <selector type="pos"   multi="false" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" refid="${field.ngModel}id" write="true"/>
                 <#break>
				<#case 20><#--流程引用-->
					<div>
						<input name="${field.newName}ID" fieldname="${field.fieldName}"  type="hidden" class="hidden" lablename="${field.fieldDesc}ID" value="">
						<input name="${field.newName}" fieldname="${field.fieldName}"  type="text" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.ngModel}id"</#if> />
						<a href="javascript:;" class="link actInsts" atype="select" name="${field.fieldName}">选择</a>
						<a href="javascript:;" class="link reset" atype="reset" name="${field.fieldName}" >重置</a>
					</div>
				<#break>
			</#switch>
		<#elseif field.fieldType == "number"><#---数字类型-->
			<#if  field.controlType == 16><#--隐藏域-->
				<input controltype="hidden" type="hidden" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"  value="" >
			<#else><#--否则数字输入-->
			   	<textarea controltype="input"  type="text" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  showType="${field.ctlProperty}"  validate="{number:true,maxIntLen:${field.intLen},maxDecimalLen:${field.decimalLen}<#if field.isRequired == 1>,required:true</#if>}" class="uc-a1 mr42"></textarea>
			</#if>
		<#elseif field.fieldType == "date"><#---日期类型-->
			<#if  field.controlType == 16><#--隐藏域-->
				<input controltype="date" type="hidden" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  value="" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>"  dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>">		
			<#else>
              	<input controltype="date" type="text" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"   name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>"  dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>"  readonly="readonly"  class="uc-a1 mr42">                 
			</#if>
		<#else>
			<#if  field.controlType == 16><#---隐藏域-->
				<input controltype="hidden" type="hidden" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}" name="${field.newName}" fieldname="${field.fieldName}"  lablename="${field.fieldDesc}"  value="" >
			<#elseif field.controlType == 10><#--富文本框ckeditor-->
				<textarea controltype="ckeditor" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"   validate="{<#if field.isRequired == 1>required:true</#if>}"  class="uc-a1 mr42 h4em"></textarea>
			<#else><#--否则多文本框-->
				<textarea controltype="textarea" ng-model="${field.ngModel}"  permission-bind="permission.field.${field.fieldName}"  name="${field.newName}" fieldname="${field.fieldName}"   lablename="${field.fieldDesc}"   validate="{<#if field.isRequired == 1>required:true</#if>}"  class="uc-a1 mr42 h4em"></textarea>
			</#if>
		</#if>

</#macro>
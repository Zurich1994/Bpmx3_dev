<#setting number_format="0">
<#macro input field>
		<#if field.fieldType == "varchar"><#---字符串类型-->
			<#switch field.controlType>
				<#case 1><#--单行文本框-->
				<span name="editable-input"  style="display:inline-block;" isflag="tableflag">
					<input  type="text"  name="${field.fieldName}" lablename="${field.fieldDesc}" class="inputText" value="" validate="{maxlength:${field.charLen}<#if field.isRequired == 1>,required:true</#if><#if field.validRule != "">,${field.validRule}:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" isflag="tableflag"/>
				</span>
				<#break>
				<#case 2><#--多行文本框-->
				<span name="editable-input"  style="display:inline-block;" isflag="tableflag">
						<textarea name="${field.fieldName}" lablename="${field.fieldDesc}" class="l-textarea" rows="5" cols="40" validate="{maxlength:${field.charLen}<#if field.isRequired == 1>,required:true</#if><#if field.validRule != "">,${field.validRule}:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" isflag="tableflag"></textarea>
				</span>
				<#break>
				<#case 3><#--数据字典-->
						<input lablename="${field.fieldDesc}" class="dicComboTree" nodeKey="${field.dictType}" value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" name="${field.fieldName}" height="200" width="125"/>
				<#break>
				<#case 4><#--人员选择器(单选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="user" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  scope='${field.getPropertyMap().scope}'  showCurUser="<#if (field.getPropertyMap().showCurUser==null)>0<#else>${field.getPropertyMap().showCurUser}</#if>" />
				<#break>
				<#case 5><#--角色选择器(多选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="roles" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  />
				<#break>
				<#case 6><#--组织选择器(多选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="orgs" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  scope='${field.getPropertyMap().scope}' />
				<#break>
				<#case 7><#--岗位选择器(多选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="positions" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  scope='${field.getPropertyMap().scope}' />
				<#break>
				<#case 8><#--人员选择器(多选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="users" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  scope='${field.getPropertyMap().scope}'/>
				<#break>
				<#case 9><#--文件上传-->
						<div  name="div_attachment_container"  >
							<div  class="attachement" ></div>
							<textarea style="display:none" controltype="attachment" name="${field.fieldName}" lablename="${field.fieldDesc}"></textarea>
							<a href="javascript:;" field="${field.fieldName}" class="link selectFile" atype="select"  onclick="{<#if field.getPropertyMap().isDirectUpLoad==1>AttachMent.directUpLoadFile(this);<#else>AttachMent.addFile(this);</#if>}" validate="{<#if field.isRequired == 1>required:true</#if>}">选择</a>
						</div>	
				<#break>
				<#case 10><#--富文本框ckeditor-->
						<span name="editable-input"  style="display:inline-block;" >
						<textarea class="ckeditor"  name="${field.fieldName}" lablename="${field.fieldDesc}" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.validRule != "">,${field.validRule}:true</#if>}"></textarea>
						</span>
				<#break>
				<#case 11><#--下拉选项-->
					<span name="editable-input"  style="display:inline-block;padding:2px;" class="selectinput">
						<select name="${field.fieldName}" lablename="${field.fieldDesc}" controltype="select" validate='{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}'>
							<#list field.aryOptions?keys as optkey>
							<option value="${optkey}">${field.aryOptions[optkey]}</option>
							</#list>
						</select>
					</span>
				<#break>
				<#case 12><#--Office控件-->
						<input type="hidden" class="hidden" name="${field.fieldName}" lablename="${field.fieldDesc}" controltype="office"  value="" />
				<#break>
				<#case 13><#--复选框-->
						<#list field.aryOptions?keys as optkey>
							<label><input type="checkbox" name="${field.fieldName}" value="${optkey}" validate="{<#if field.isRequired == 1>required:true</#if>}"/>${field.aryOptions[optkey]}</label>
						</#list>
				<#break>
				<#case 14><#--单选按钮-->
						<#list field.aryOptions?keys as optkey>
							<label><input type="radio" name="${field.fieldName}" value="${optkey}" lablename="${field.fieldDesc}" validate="{<#if field.isRequired == 1>required:true</#if>}"/>${field.aryOptions[optkey]}</label>
						</#list>
				<#break>
				<#case 15><#--日期控件-->
						<input name="${field.fieldName}" type="text" class="Wdate" lablename="${field.fieldDesc}" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>" dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
				<#break>
				<#case 16><#--隐藏域-->
						<input name="${field.fieldName}" type="hidden" lablename="${field.fieldDesc}"  value="" validate="{<#if field.isRequired == 1>required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
				<#break>
				<#case 17><#--角色选择器(单选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="role" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if>  />
				<#break>
				<#case 18><#---组织选择器(单选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="org" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly"  <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if> showCurOrg="<#if (field.getPropertyMap().showCurOrg==null)>0<#else>${field.getPropertyMap().showCurOrg}</#if>" scope='${field.getPropertyMap().scope}'/>
				<#break>
				<#case 19><#--岗位选择器(单选)-->
						<input name="${field.fieldName}" type="text" ctlType="selector" class="position" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly"  <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if> showCurPos="<#if (field.getPropertyMap().showCurPos==null)>0<#else>${field.getPropertyMap().showCurPos}</#if>" scope='${field.getPropertyMap().scope}'/>
				<#break>
				<#case 20><#--流程引用-->
						<div>
							<input name="${field.fieldName}ID" type="hidden" class="hidden" lablename="${field.fieldDesc}ID" value="">
							<input name="${field.fieldName}" type="text" lablename="${field.fieldDesc}"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}" readonly="readonly" <#if 1==field.isReference>linktype="${field.controlType}"  refid="${field.fieldName}ID"</#if> />
							<a href="javascript:;" class="link actInsts" atype="select" name="${field.fieldName}">选择</a>
							<a href="javascript:;" class="link reset" atype="reset" name="${field.fieldName}" >重置</a>
						</div>
				<#break>
				<#case 21><#--WebSign签章控件-->
						<input type="hidden" class="hidden" name="${field.fieldName}" lablename="${field.fieldDesc}" controltype="webSign"  value="" />
						<div id="div_${field.fieldName?replace(":","_")}" class="webSign-div"></div>
				<#break>
				<#case 22><#--图片展示控件-->
						<div id="div_${field.fieldName?replace(":","_")}" style="width:400px;height:340px" class="pictureShow-div" > 
					        <div id="div_${field.fieldName?replace(":","_")}_container" ></div> 
					        <table id="pictureShow_${field.fieldName?replace(":","_")}_Toolbar">
					           <tr>
				                 <td width="80">
				                 	<a href="javascript:;" field="${field.fieldName}" class="link selectFile" atype="uploadPicture" onclick="{PictureShowPlugin.upLoadPictureFile(this);}">上传图片</a> 
				                 </td>
				                 <td width="80">
				                 	<a href="javascript:;" field="${field.fieldName}" class="link del" atype="delPicture" onclick="{PictureShowPlugin.deletePictureFile(this);}">删除图片</a> 				              
				                 </td>
				               </tr>
				            </table>   
					    </div>
					    <input type="hidden" class="hidden"  name="${field.fieldName}" lablename="${field.fieldDesc}"  controltype="pictureShow" value="" right="w" />
				<#break>
			</#switch>
		<#elseif field.fieldType == "number"><#---数字类型-->
			<#if  field.controlType == 16><#--隐藏域-->
				<input name="${field.fieldName}" type="hidden"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
			<#elseif field.controlType == 11><#--下拉选项-->
					<span name="editable-input"  style="display:inline-block;padding:2px;" class="selectinput">
						<select name="${field.fieldName}" lablename="${field.fieldDesc}" controltype="select" validate='{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}'>
							<#list field.aryOptions?keys as optkey>
							<option value="${optkey}">${field.aryOptions[optkey]}</option>
							</#list>
						</select>
					</span>
			<#else><#--否则数字输入-->
				<input name="${field.fieldName}" type="text" value="" showType=${field.ctlProperty}  validate="{number:true,maxIntLen:${field.intLen},maxDecimalLen:${field.decimalLen}<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
			</#if>
		<#elseif field.fieldType == "date"><#---日期类型-->
			<#if  field.controlType == 16><#--隐藏域-->
				<input name="${field.fieldName}" type="hidden" class="hidden" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>"  dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>" value="">
			<#else>
				<input name="${field.fieldName}" type="text" class="Wdate" displayDate="<#if (field.getPropertyMap().displayDate==null)>0<#else>${field.getPropertyMap().displayDate}</#if>"  dateFmt="<#if (field.getPropertyMap().format==null)>yyyy-MM-dd<#else>${field.getPropertyMap().format}</#if>"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
			</#if>
		<#else>
			<#if  field.controlType == 16><#---隐藏域-->
				<input name="${field.fieldName}" type="hidden"  value="" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.isWebSign == 1>,isWebSign:true</#if>}">
			<#elseif field.controlType == 10><#--富文本框ckeditor-->
				<textarea class="ckeditor"  name="${field.fieldName}" validate="{empty:false<#if field.isRequired == 1>,required:true<#else>,required:false</#if><#if field.validRule != "">,${field.validRule}:true</#if>}"></textarea>
			<#else><#--否则多文本框-->
				<textarea  name="${field.fieldName}" validate="{empty:false<#if field.isRequired == 1>,required:true</#if><#if field.validRule != ""><#if field.isRequired == 1>,</#if>${field.validRule}:true</#if>}"<#if field.isWebSign == 1>,isWebSign:true</#if>></textarea>
			</#if>
		</#if>

</#macro>
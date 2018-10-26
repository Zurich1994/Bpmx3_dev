<#macro input field>
<#if field.fieldType == "varchar">
<input name="${field.fieldName}"  style="width:100%;font:20px;border:2px blue solid" validate="{<#if field.isRequired == 1>required:true</#if>}"></input>
<#elseif field.fieldType == "number">
<input name="${field.fieldName}"  style="width:100%;" validate="{<#if field.isRequired == 1>required:true</#if>}"></input>
<#elseif field.fieldType == "date">
<input name="${field.fieldName}"  style="width:100%;" validate="{<#if field.isRequired == 1>required:true</#if>}"></input>
<#elseif field.fieldType == "dict">
<select class="dicCombo" nodeKey="${field.dictType}" valueFieldID="${field.fieldName}ID" name="${field.fieldName}"  style="width:100%;" validate="{<#if field.isRequired == 1>required:true</#if>}"></select>
</#if>
</#macro>
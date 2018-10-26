<#list table.fieldList as field>
${field.fieldDesc}:<#noparse>$</#noparse>{${(field.getDbFieldName(table.isExtTable())+"")?lower_case}}
</#list>
<#list table.subTableList as subTable>
子表:${subTable.tableName}
<#list subTable.fieldList as field>${field.fieldDesc},</#list>
<#noparse> <#list </#noparse>${subTable.tableName}<#noparse>s as </#noparse>${subTable.tableName}<#noparse>> </#noparse>
<#list subTable.fieldList as field>
<#noparse>${</#noparse>${subTable.tableName}<#noparse>.</#noparse>${(field.getDbFieldName(table.isExtTable())+"")?lower_case}<#noparse>},</#noparse></#list>
<#noparse></#list></#noparse>
</#list>
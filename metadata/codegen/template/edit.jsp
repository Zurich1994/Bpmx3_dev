<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign tabcomment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign package=model.variables.package>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign subtables=model.subTableList>
<%--
	time:${date?string("yyyy-MM-dd HH:mm:ss")}
	desc:edit the ${tabcomment}
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 ${tabcomment}</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/CustomValid.js"></script>
	<#if subtables?exists && subtables?size!=0>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/subform.js"></script>
	</#if>
	<#if model.variables.flowKey?exists>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
	</#if>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#${classVar}Form").attr("action","save.ht");
				$("#saveData").val(1);
				submitForm();
			});
			<#if model.variables.flowKey?exists>
			$("a.run").click(function() {
				$("#${classVar}Form").attr("action","run.ht");
				$("#saveData").val(0);
				submitForm();
			});
			</#if>
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#${classVar}Form').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				<#if subtables?exists && subtables?size!=0>
				frm.sortList();
				</#if>
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = "<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="<#noparse>${</#noparse>${classVar}.${pkVar} !=null}">
			        <span class="tbar-label"><span></span>编辑${tabcomment}</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加${tabcomment}</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<#if model.variables.flowKey?exists>
				<c:if test="<#noparse>${</#noparse>runId==0}">
				<div class="group"><a class="link run"  href="#"><span></span>提交</a></div>
				<div class="l-bar-separator"></div>
				</c:if>
				<c:if test="<#noparse>${</#noparse>runId!=0}">
				<div class="group"><a class="link detail" onclick="FlowDetailWindow({runId:<#noparse>${</#noparse>runId}})"" href="#"><span></span>流程明细</a></div>
				<div class="l-bar-separator"></div>
				</c:if>
				</#if>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="${classVar}Form" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<#list commonList as col>
				<#assign colName=func.convertUnderLine(col.columnName)>
				<#if (col.colType=="java.util.Date") >
				<tr>
					<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
					<td><input type="text" id="${colName}" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${classVar}.${colName}}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true<#if col.isNotNull>,required:true</#if>}" /></td>
				</tr>
				<#else>
				<tr>
					<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
					<td><input type="text" id="${colName}" name="${colName}" value="<#noparse>${</#noparse>${classVar}.${colName}}"  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String'&& col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true<#if col.scale!=0>,maxDecimalLen:${col.scale}</#if><#if col.precision!=0>,maxIntLen:${col.precision}</#if></#if>}"  /></td>
				</tr>
				</#if>
				</#list>
			</table>
			<#if subtables?exists && subtables?size != 0>
			<#list subtables as table>
			<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
			<table class="table-grid table-list" cellpadding="1" cellspacing="1" id="${table.variables.classVar}" formtype="form" type="subtable">
				<tr>
					<td colspan="${table.columnList?size-1}">
						<div class="group" align="left">
				   			<a id="btnAdd" class="link add">添加</a>
			    		</div>
			    		<div align="center">
						<#if table.tabComment?exists>${table.tabComment}<#else>${table.tableName}</#if>
			    		</div>
		    		</td>
				</tr>
				<tr>
					<#list table.columnList as col>
					<#assign colName=func.convertUnderLine(col.columnName?lower_case)>
					<#if !(col.isPK)&& colName?lower_case!=(foreignKey)?lower_case>							                              
					<th>${col.comment}</th>
					</#if>									
					</#list>
				</tr>
				<c:forEach items="<#noparse>${</#noparse>${table.variables.classVar}List}" var="${table.variables.classVar}Item" varStatus="status">
				    <tr type="subdata">
				        <#list table.columnList as col>												
					    <#assign colName=func.convertUnderLine(col.columnName)>
					    <#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
					    <#if (col.colType=="java.util.Date")>
						<td style="text-align: center" name="${colName}"><fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}' pattern='yyyy-MM-dd'/></td>								
					    <#else >
					    <td style="text-align: center" name="${colName}"><#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}</td>
					    </#if>
					  	</#if>
					    </#list>
					     <#list table.columnList as col>												
					    <#assign colName=func.convertUnderLine(col.columnName)>
					    <#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
					    <#if (col.colType=="java.util.Date")>
					    <input type="hidden" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}' pattern='yyyy-MM-dd'/>" class="inputText date"/>
					    <#else >
						<input type="hidden" name="${colName}" value="<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}"/>
					    </#if>
					  	</#if>
					    </#list>
				    </tr>
				</c:forEach>
				<tr type="append">
		        <#list table.columnList as col>												
			    <#assign colName=func.convertUnderLine(col.columnName)>
			    <#assign foreignKey=func.convertUnderLine(table.foreignKey) >
		   		<#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
		    	<#if (col.colType=="java.util.Date")>
					<td style="text-align: center" name="${colName}"></td>								
			    <#else >
			    	<td style="text-align: center" name="${colName}"></td>
			    </#if>
			  	</#if>
			    </#list>
			    	<#list table.columnList as col>												
			    <#assign colName=func.convertUnderLine(col.columnName)>
			    <#assign foreignKey=func.convertUnderLine(table.foreignKey) >
		   		<#if ( !col.isPK && colName?lower_case!=foreignKey?lower_case)>
		    	<#if (col.colType=="java.util.Date")>
			    	<input type="hidden" name="${colName}" value="" class="inputText date"/>
			    <#else >
			    	<input type="hidden" name="${colName}" value=""/>
			    </#if>
			  	</#if>
			    </#list>
		 		</tr>
		    </table>
			</#list>
			</#if>
			<input type="hidden" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}.${pkVar}<#noparse>}</#noparse>" />
		    <input type="hidden" name="saveData" id="saveData" />
		    <input type="hidden" name="executeType"  value="start" />
		</form>
		
	</div>
	<#if subtables?exists && subtables?size != 0>
	<#list subtables as table>
	<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
	<form id="${table.variables.classVar}Form" style="display:none" title="<#if table.tabComment?exists>${table.tabComment }<#else>${table.tableName }</#if>">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<#list table.columnList as col>	
			<#assign colName=func.convertUnderLine(col.columnName)>
			<#if !col.isPK && colName?lower_case!=foreignKey?lower_case>
			<#if (col.colType=="java.util.Date")>
			<tr>
				<th width="20%">${col.comment}: <#if col.isNotNull> <span class="required">*</span></#if></th>
				<td><input type="text" name="${colName}" value="" class="inputText date" validate="{date:true<#if col.isNotNull>,required:true</#if>}"/></td>
			</tr>
			
			<#else>
			<tr>
				<th width="20%">${col.comment}: <#if col.isNotNull> <span class="required">*</span></#if></th>
				<td><input type="text" name="${colName}" value=""  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true<#if col.scale!=0>,maxDecimalLen:${col.scale}</#if><#if col.precision!=0>,maxIntLen:${col.precision}</#if> </#if>}"/></td>
			</tr>
			</#if>
			</#if>
			</#list>
		</table>
	</form>
	</#list>
	</#if>
</div>
</body>
</html>

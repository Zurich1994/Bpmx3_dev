<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign tabcomment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign package=model.variables.package>
<#assign commonList=model.commonList>
<#assign system=vars.system>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign subtables=model.subTableList>
<%--
	time:${date?string("yyyy-MM-dd HH:mm:ss")}
	desc:edit the ${tabcomment}
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 ${tabcomment}</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/CustomValid.js"></script>
	<#if func.isSubTableExist( subtables)>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/subform.js"></script>
	</#if>
	<#if func.supportFlow(model)>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/platform/bpm/BpmImageDialog.js"></script>
	</#if>
	<script type="text/javascript">
		var returnUrl="<#noparse>${returnUrl}</#noparse>";	
		
		$(function() {
			<#--支持工作流 -->
			<#if func.supportFlow(model)>
				$("a.save").click(function() {
					$("#saveData").val(1);
					submitForm();
				});
				
				$("a.run").click(function() {
					$("#saveData").val(0);
					submitForm();
				});
			<#else>
				$("a.save").click(function() {
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
			var frm=$('#frmSubmit').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				<#if func.isSubTableExist( subtables)>
				frm.sortList();
				</#if>
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.topCall.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							window.close();
						}else{
							this.close();
							window.location.href="list.ht";
						}
					}
				});
			} else {
				$.topCall.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<form id="frmSubmit" method="post" action="save.ht">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="<#noparse>${</#noparse>${classVar}.${pkVar} !=null}">
			        <span class="tbar-label">编辑${tabcomment}</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加${tabcomment}</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<ht:incToolBar ></ht:incToolBar>
				<div class="group"><a class="link back" href="history.back(-1);"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
			<#--主表部分-->
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<#list commonList as col>
					<#assign colName=func.convertUnderLine(col.columnName)>
					<#if (col.colType=="java.util.Date") >
					<tr>
						<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
						<td><input type="text" id="${colName}" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${classVar}.${colName}}' pattern='yyyy-MM-dd'/>" validate="{<#if col.isNotNull>required:true<#else>required:false</#if>,date:true}" class="inputText date"/></td>
					</tr>
					<#else>
					<tr>
						<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
						<td><input type="text" id="${colName}" name="${colName}" value="<#noparse>${</#noparse>${classVar}.${colName}}" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true<#if col.scale!=0>,maxDecimalLen:${col.scale}</#if><#if col.precision!=0>,maxIntLen:${col.precision}</#if> </#if>}" class="inputText"/></td>
					</tr>
					</#if>
				</#list>
			</table>
			<#--子表部分-->
			<#if func.isSubTableExist( subtables)>
			<#list subtables as table>
			<table class="table-grid table-list" cellpadding="1" cellspacing="1" type="subtable" formtype="page" id="${table.variables.classVar}">
				<tr>
					<td colspan="${table.columnList?size-1}">
						<div class="group" align="left">
				   			<a id="btnAdd" class="link add">添加</a>
			    		</div>
			    		<div align="center">
			    		${func.getComment(table) }
			    		</div>
		    		</td>
				</tr>
				<tr>
				<#list table.columnList as col>
					<#assign colName=func.convertUnderLine(col.columnName?lower_case)>
					<#assign foreignKey=func.convertUnderLine(table.foreignKey)>
					<#if !(col.isPK)&& colName?lower_case!=foreignKey?lower_case>							                              
						<th>${col.comment}</th>
					</#if>									
				</#list>
				</tr>
				<c:forEach items="<#noparse>${</#noparse>${table.variables.classVar}List}" var="${table.variables.classVar}Item" varStatus="status">
				    <tr type="subdata">
			        <#list table.columnList as col>												
					    <#assign colName=func.convertUnderLine(col.columnName)>
					    <#assign foreignKey=func.convertUnderLine(table.foreignKey)>
				    	<#if  !(col.isPK)&&colName?lower_case!=foreignKey?lower_case>
				    		<#if (col.colType=="java.util.Date")>
								<td style="text-align: center"><input type="text" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName?lower_case}}' pattern='yyyy-MM-dd'/>" validate="{<#if col.isNotNull>required:true<#else>required:false</#if>,date:true}" class="inputText date"/></td>								
				    		<#else>
				    			<td style="text-align: center"><input type="text" name="${colName}" value="<#noparse>${</#noparse>${table.variables.classVar}Item.${colName}}" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true </#if>}" class="inputText"/></td>
				    		</#if>
				    	</#if>
				    </#list>
				    </tr>
				</c:forEach>
				<#--子表添加模版-->
				<tr type="append" style="display:none;">
			        <#list table.columnList as col>												
					    <#assign colName=func.convertUnderLine(col.columnName)>
					    <#assign foreignKey=func.convertUnderLine(table.foreignKey)>
				    	<#if  !(col.isPK)&&colName?lower_case!=foreignKey?lower_case>
					   		<#if (col.colType=="java.util.Date")>
							<td style="text-align: center"><input type="text" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${table.variables.classVar}Item.${colName?lower_case}}' pattern='yyyy-MM-dd'/>" validate="{<#if col.isNotNull>required:true<#else>required:false</#if>,date:true}" class="inputText date"/></td>								
					    	<#else>
					    	<td style="text-align: center"><input type="text" name="${colName}" value="" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true<#if col.scale!=0>,maxDecimalLen:${col.scale}</#if><#if col.precision!=0>,maxIntLen:${col.precision}</#if> </#if>}" class="inputText"/></td>
					    	</#if>
				    	</#if>
				    </#list>
			    </tr>
		    </table>
			</#list>
			</#if>
			<input type="hidden" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}.${pkVar}<#noparse>}</#noparse>" />					
	</div>
</div>
</form>
</body>
</html>

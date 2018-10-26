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
<#assign flowRunId="">
<#if model.variables.flowDefKey?exists>
<#assign flowRunId=func.convertUnderLine(model.variables.flowRunId)>
</#if>
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
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/formdata.js"></script>
	<#if model.variables.flowDefKey?exists>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/platform/bpm/TaskImageUserDialog.js"></script>
	</#if>
	<#if subtables?exists && subtables?size!=0>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/subform.js"></script>
	</#if>
	<script type="text/javascript">
		$(function() {
			$("#formInfo").ligerTab();
			$("a.save").click(function() {
				$("#${classVar}Form").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#${classVar}Form').form();
			frm.setData();
			frm.ajaxForm(options);
			if(frm.valid()){
				form.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
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
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		<#if model.variables.flowDefKey?exists >
		var taskId="<#noparse>${</#noparse>taskId}";
		//启动流程
		function startFlow(){
			$("#${classVar}Form").attr("action","startFlow.ht");
			submitForm();
		}
		//办理任务
		function complete(voteAgree){
			$("#voteAgree").val(voteAgree);
			$("#${classVar}Form").attr("action","complete.ht");
			submitForm();
		}
		//显示流程图
		function showTaskUserDlg(){
			TaskImageUserDialog({taskId:taskId});
		}
		//显示审批历史
		function showTaskOpinions(){
			var winArgs="dialogWidth=800px;dialogHeight=600px;help=1;status=1;scroll=1;center=1;resizable:1";				
			var url='<#noparse>${</#noparse>ctx}/platform/bpm/taskOpinion/list.ht?taskId='+taskId+"&isOpenDialog=1";
			url=url.getNewUrl();
			window.showModalDialog(url,"",winArgs);
		}
		</#if>
	</script>
</head>
<body>
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
				<#if model.variables.flowDefKey?exists>
				<c:choose>
					<c:when test="<#noparse>${</#noparse>empty taskId}">
						<div class="group"><a class="link save" id="dataFormSave" href="#">保存</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link run" id="dataFormStart"  onclick="startFlow()">启动</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link back" href="list.ht">返回</a></div>
					</c:when>
					<c:otherwise>
					<div class="group"><a id="btnAgree" class="link agree" onclick="complete(1)">提交</a></div>
					<div class="l-bar-separator"></div>
					<c:if test="<#noparse>${</#noparse>isAllowBack}">
					<div class="l-bar-separator"></div>
					<div class="group"><a id="btnReject" class="link reject"  onclick="complete(3)" >驳回</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="btnRejectToStart" class="link rejectToStart" onclick="complete(4)">驳回到发起人</a></div>
					</c:if>
					<c:if test="<#noparse>${</#noparse>isSignTask}">
					<div class="l-bar-separator"></div>
					<div class="group"><a id="btnNotAgree" class="link notAgree" onclick="complete(2)" >反对</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="btnAbandon" class="link abandon" onclick="complete(0)" >弃权</a></div>
					</c:if>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link setting" onclick="showTaskUserDlg()">流程执行示意图</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link search" onclick="showTaskOpinions()">审批历史</a></div>
					</c:otherwise>
				</c:choose>
				<#else>
				<div class="group"><a class="link save" id="dataFormSave" href="#">保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht">返回</a></div>
				</#if>
			</div>
		</div>
	</div>
	<div class="panel-body" type="custform">
		<form id="${classVar}Form" method="post" action="save.ht">
			<div id="formInfo" >
				<div title="${tabcomment}主表明细">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
						<input type="hidden" name="${pkVar}" value="<#noparse>${</#noparse>${classVar}.${pkVar}<#noparse>}</#noparse>" />   <!-- id放到主表TABLE里面,生成的内容才能获取主表提交的数据的ID ??? -->
						<#list commonList as col>
						<#assign colName=func.convertUnderLine(col.columnName)>
						<#if colName!=flowRunId>
						<#if (col.colType=="java.util.Date") >
						<tr>
							<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
							<td><input type="text" id="${colName}" name="${colName}" value="<fmt:formatDate value='<#noparse>${</#noparse>${classVar}.${colName}}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true<#if col.isNotNull>,required:true</#if>}" /></td>
						</tr>
						<#else>
						<tr>
							<th width="20%">${col.comment}: <#if (col.isNotNull) > <span class="required">*</span></#if></th>
							<td><input type="text" id="${colName}" name="${colName}" value="<#noparse>${</#noparse>${classVar}.${colName}}"  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String'&& col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true </#if>}"  /></td>
						</tr>
						</#if>
						</#if>
						</#list>
					</table>
				</div>
				<#if subtables?exists && subtables?size != 0>
				<#list subtables as table>
				 <#assign foreignKey=func.convertUnderLine(table.foreignKey) >
				 <div title="${table.tabComment }子表明细">
					<table class="table-grid table-list" cellpadding="1" cellspacing="1" id="${table.variables.classVar}" formType="window" type="sub">
						<tr>
							<td colspan="${table.columnList?size}">
								<div class="group" align="left">
						   			<a id="btnAdd" class="link add">添加</a>
					    		</div>
					    		<div align="center">
								${table.tableName } : ${table.tabComment }
					    		</div>
				    		</td>
						</tr>
						<tr>
							<th><input id="chkall" type="checkbox" /></th>
							<#list table.columnList as col>
							<#assign colName=func.convertUnderLine(col.columnName?lower_case)>
							<#if !(col.isPK)&& colName?lower_case!=(foreignKey)?lower_case>							                              
							<th>${col.comment}</th>
							</#if>									
							</#list>
							<th>操作</th>
						</tr>
						<c:forEach items="<#noparse>${</#noparse>${table.variables.classVar}List}" var="${table.variables.classVar}Item" varStatus="status">
						    <tr type="subdata">
						        <td style="text-align: center" name="pkName"><input id="pkName" type="checkbox" class="pk"/></td>
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
							    <td style="text-align: center">
							    	<a href="#" class="link del">删除</a>
							    	<a href="#" class="link edit">编辑</a>
							    </td>
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
				        <td style="text-align: center" name="pkName"><input id="pkName" type="checkbox" class="pk"/></td>
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
					    	<td style="text-align: center">
					    		<a href="#" class="link del">删除</a>
					    		<a href="#" class="link edit">编辑</a>
					    	</td>
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
				   </div>
				</#list>
				</#if>
				<#if model.variables.flowDefKey?exists>
				<#assign flowRunId=func.convertUnderLine(model.variables.flowRunId)>
				<input type="hidden" name="${flowRunId}" value="<#noparse>${</#noparse>${classVar}.${flowRunId}}"
				<input type="hidden" name="formData" id="formData" />
				<input type="hidden" id="voteAgree" name="voteAgree" value="1"/> 
				<input type="hidden" id="taskId" name="taskId" value="<#noparse>${</#noparse>taskId}"/>
				</#if>	
			</div>		
		</form>
		
	</div>
	<#if subtables?exists && subtables?size != 0>
	<#list subtables as table>
	<#assign foreignKey=func.convertUnderLine(table.foreignKey) >
	<form id="${table.variables.classVar}Form" style="display:none">
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
				<td><input type="text" name="${colName}" value=""  class="inputText" validate="{<#if col.isNotNull>required:true<#else>required:false</#if><#if col.colType=='String' && col.length<1000>,maxlength:${col.length}</#if><#if col.colType=='Integer'|| col.colType=='Long'||col.colType=='Float'>,number:true </#if>}"/></td>
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

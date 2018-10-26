<%--
	time:2015-06-08 16:02:04
	desc:edit the 视图自定义设定
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 视图自定义设定</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		var returnUrl="${returnUrl}";	
		
		$(function() {
				$("a.save").click(function() {
					submitForm();
				});
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
			    <c:when test="${sysQueryFieldSetting.id !=null}">
			        <span class="tbar-label">编辑视图自定义设定</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加视图自定义设定</span>
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
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
					<tr>
						<th width="20%">视图ID: </th>
						<td><input type="text" id="viewId" name="viewId" value="${sysQueryFieldSetting.viewId}" validate="{required:false,number:true,maxIntLen:19 }" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">字段ID: </th>
						<td><input type="text" id="fieldId" name="fieldId" value="${sysQueryFieldSetting.fieldId}" validate="{required:false,number:true,maxIntLen:19 }" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">允许排序: </th>
						<td><input type="text" id="sortAble" name="sortAble" value="${sysQueryFieldSetting.sortAble}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">默认排序: </th>
						<td><input type="text" id="defaultSort" name="defaultSort" value="${sysQueryFieldSetting.defaultSort}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">默认排序方向: </th>
						<td><input type="text" id="sortSeq" name="sortSeq" value="${sysQueryFieldSetting.sortSeq}" validate="{required:false,maxlength:30}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">对齐方式: </th>
						<td><input type="text" id="align" name="align" value="${sysQueryFieldSetting.align}" validate="{required:false,maxlength:30}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">是否冻结: </th>
						<td><input type="text" id="frozen" name="frozen" value="${sysQueryFieldSetting.frozen}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">列连接地址: </th>
						<td><input type="text" id="url" name="url" value="${sysQueryFieldSetting.url}" validate="{required:false,maxlength:300}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">计算类型: </th>
						<td><input type="text" id="summaryType" name="summaryType" value="${sysQueryFieldSetting.summaryType}" validate="{required:false,maxlength:30}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">计算模版: </th>
						<td><input type="text" id="summaryTemplate" name="summaryTemplate" value="${sysQueryFieldSetting.summaryTemplate}" validate="{required:false,maxlength:600}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">报警设定: </th>
						<td><input type="text" id="alertSetting" name="alertSetting" value="${sysQueryFieldSetting.alertSetting}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">自定义格式函数: </th>
						<td><input type="text" id="formatter" name="formatter" value="${sysQueryFieldSetting.formatter}" validate="{required:false}" class="inputText"/></td>
					</tr>
			</table>
			<input type="hidden" name="id" value="${sysQueryFieldSetting.id}" />					
	</div>
</div>
</form>
</body>
</html>

<%--
	time:2015-03-18 15:39:18
	desc:edit the 首页布局
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 首页布局</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<f:link href="codemirror/lib/codemirror.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/xml/xml.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script type="text/javascript">
		var returnUrl="${returnUrl}";	
		
		$(function() {
			var width = $("#templateHtml").width();
			var height = $("#templateHtml").height();
			editor = CodeMirror.fromTextArea(document.getElementById("templateHtml"), {
				mode: "text/html",
				tabMode: "indent",
				lineNumbers: true
			 });
			
			editor.setSize(width,height);
				$("a.save").click(function() {
					$("#templateHtml").val(editor.getValue());
					if(editor.getValue() == ''){
						$.ligerDialog.alert("请填写模版","提示信息");
						return;
					}
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
				$.ligerDialog.success("保存成功！","提示信息", function(rtn) {
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
	</script>
</head>
<body>
<form id="frmSubmit" method="post" action="save.ht">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysIndexLayout.id !=null}">
			        <span class="tbar-label">编辑首页布局</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加首页布局</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="${returnUrl}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
						<th width="20%">布局名称:  <span class="required">*</span></th>
						<td><input type="text" name="name" id="name"  value="${sysIndexLayout.name}"  class="inputText"  validate="{required:true,maxlength:600}" /></td>
					</tr>
					<tr>
						<th width="20%">排序: <span class="required">*</span></th>
						<td><input type="text" name="sn" id="sn"  value="${sysIndexLayout.sn}"  class="inputText"  validate="{required:true,number:true}" /></td>
					</tr>
					<tr>
						<th width="20%">描述: </th>
						<td><textarea id="memo" name="memo" class="inputText">${fn:escapeXml(sysIndexLayout.memo)}</textarea></td>
					</tr>
					<tr>
						<th width="20%">模版:  <span class="required">*</span></th>
						<td><textarea id="templateHtml" name="templateHtml" style="width: 90%;height: 220px;"  validate="{required:true}" >${fn:escapeXml(sysIndexLayout.templateHtml)}</textarea></td>
					</tr>
			</table>
			<input type="hidden" name="id" value="${sysIndexLayout.id}" />					
	</div>
</div>
</form>
</body>
</html>

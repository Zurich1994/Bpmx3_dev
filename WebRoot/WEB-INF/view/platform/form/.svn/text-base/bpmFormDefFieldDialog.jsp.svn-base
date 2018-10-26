<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	function sure() {
		var fieldName = $("input[name='fieldName']:checked").val();
		var call=dialog.get("sucCall");
		call(fieldName);
		dialog.close();
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">表单字段</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add"  onclick="sure();"><span></span>确定</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link cancel" onclick="dialog.close();"  ><span></span>取消</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
		    <display:table name="bpmFormFieldList" id="bpmFormFieldItem" cellpadding="1" cellspacing="1" class="table-grid">
		    	<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="radio" class="pk" id="fieldName" name="fieldName" value="${bpmFormFieldItem.fieldName}" >
				</display:column>
				<display:column property="fieldDesc" titleKey="字段描述" sortable="false" ></display:column>
				<display:column property="fieldName" titleKey="字段代码" sortable="false" ></display:column>
				<display:column property="fieldType" titleKey="字段类型" sortable="false" ></display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>



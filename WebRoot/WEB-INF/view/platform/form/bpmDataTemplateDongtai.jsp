
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>新建表单</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDef"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<style type="text/css">
	html,body{height:100%;width:100%; overflow: hidden;}
</style>
</head>
<body >
<%String[] na = (String[])request.getAttribute("na"); 
  Long id = (Long)request.getAttribute("__displayId__");
 %>
		<div class="panel-detail">
			<form  id="bpmFormDefForm" method="post" action="preview.ht" target="_blank">		 
				 <table cellpadding="1" cellspacing="1" class="table-detail">	
				    <%for(int i=0;i<na.length;i++){ %>														
					<tr>
						<th width="150"><%=na[i] %>:</th>
						<td style="padding-top: 5px;">	
						   				
							<input type="text" id="<%=na[i] %>" class="inputText" name="<%=na[i] %>" >
				
						</td>
					</tr>
					<tr><td>
					  
					</td></tr>
					<%} %>
					<input type="hidden" id="__displayId__"  name ="__displayId__" value="<%=id%>">  
					<tr><td><input type="submit" value="查询"></td></tr>
				</table>
				
			</form>
		</div><!-- end of panel-body -->				

</body>
</html>



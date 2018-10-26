<%--
	time:2012-03-20 16:39:01
	desc:edit the 桌面栏目
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 桌面栏目</title>
	<%@include file="/commons/include/form.jsp" %>
	<%-- <link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" > --%>
	<f:link href="codemirror/lib/codemirror.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/xml/xml.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopColumn"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmQueryDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var width = $("#html").width();
			var height = $("#html").height();
			editor = CodeMirror.fromTextArea(document.getElementById("html"), {
				mode: "text/html",
				tabMode: "indent",
				lineNumbers: true
			 });
			
			editor.setSize(width,height);
			
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${desktopColumn.id ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$("#html").val(editor.getValue());
				$('#desktopColumnForm').submit(); 
			});
			
			$(":radio").click(function(){
				var  checked=$(this).attr('checked');
				var val=$(this).val();
				if(checked&&val==1){
					$("#queryAlias").show();
					$("#serviceMethod").hide();
				}else{
					$("#queryAlias").hide();
					$("#serviceMethod").show();
				}
			});
		});
		
		function selectQuery(){
			BpmQueryDialog({callback:function(alias){
				$("#queryAlias").find(':input').val(alias);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopColumn.id !=null }">
			            <span class="tbar-label">编辑桌面栏目</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加桌面栏目</span>
			        </c:otherwise>
			    </c:choose>
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="desktopColumnForm" method="post" action="save.ht">
				
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">栏目名称: </th>
								<td>
								<input type="text" id="columnName" name="columnName" style="width: 20%;" value="${desktopColumn.columnName}"  class="inputText" style="width: 80%;"/>
								</td>
							</tr>
							<tr>
								<th width="20%">栏目别名: </th>
								<td>
									<input type="text" id="templateId" name="templateId" style="width: 20%;" value="${desktopColumn.templateId}"  class="inputText" style="width: 80%;"/>
									<a href="javascript:;" class="tipinfo"><span>别名在系统中不能重复</span></a>
								</td>
							</tr>
							<tr>
								<th width="20%">更多路径: </th>
								<td>
									<input type="text" id="columnUrl" name="columnUrl" style="width: 40%;" value="${desktopColumn.columnUrl}"  class="inputText" style="width: 80%;"/>
								</td>
							</tr>
							<tr>
								<th width="20%">数据加载方式: </th>
								<td>
									<input type="radio"  name="methodType"  value="0" <c:if test="${desktopColumn.methodType!=1}">checked="checked"</c:if> />Service方法
									<input type="radio"  name="methodType" value="1" <c:if test="${desktopColumn.methodType==1}">checked="checked"</c:if> />自定义查询方式
								</td>
							</tr>
							<tr id="serviceMethod" <c:if test="${desktopColumn.methodType!=0}">style="display:none"</c:if>>
								<th width="20%">方法路径: </th>
								<td><input type="text"  name="serviceMethod" style="width: 40%;" value="${desktopColumn.serviceMethod}"  class="inputText" style="width: 80%;"/>
									<a href="javascript:;" class="tipinfo"><span>填写格式:service+"."+方法名,service为spring的service名称。</span></a>
								</td>
							</tr>
							<tr id="queryAlias" <c:if test="${desktopColumn.methodType!=1}">style="display:none"</c:if> >
								<th width="20%">自定义查询: </th>
								<td>
									<input type="text"  name="queryAlias" style="width: 20%;" value="${desktopColumn.queryAlias}"  readonly="readonly" class="inputText" />&nbsp;<a  href="javascript:;" onclick="selectQuery()"  class="button"><span>选 择...</span></a>
								</td>
							</tr>
							<tr>
								<th width="20%">模板html: </th>
								<td >
									<textarea id="html" name="html" style="width: 90%;height: 220px;">${fn:escapeXml(desktopColumn.html)}</textarea>
								</td>
							</tr>
						</table>
					<input type="hidden" name="id" value="${desktopColumn.id}" />
				</form>
		</div>
</div>
</body>
</html>

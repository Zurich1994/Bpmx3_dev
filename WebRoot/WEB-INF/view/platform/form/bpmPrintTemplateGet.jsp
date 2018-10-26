
<%--
	update 2013-1-1
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/customForm.jsp" %>
<title>表单打印</title>
<script type="text/javascript">
	$(function(){
		$(".taskopinion").each(function(){
			$(this).removeClass("taskopinion");
			var actInstId=$(this).attr("instanceId");
			$(this).load("${ctx}/platform/bpm/taskOpinion/listform.ht?actInstId="+actInstId);
		});
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link close" href="javascript:;" onclick="window.close();"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form >
				<div type="custform">
					<div class="panel-detail">
						${html}
					</div>
				</div>
		
			</form>
		</div>
		
	</div>
</body>
</html>


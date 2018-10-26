<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>表单明细</title>
		<%@include file="/commons/include/customForm.jsp" %>
		<script type="text/javascript">
			$().ready(function (){
				hideExtend()
			});
		
			function hideExtend(){
				$("a.extend").remove();
			};
			
		</script>
	</head>
	<body >
		<div class="panel">
			<div class="l-layout-header">表单明细</div>
		</div>
		<div class="panel-body" style="overflow:auto;width: 80%;margin: auto;">
			${form}
		</div>
	</div>
	</body>
</html>
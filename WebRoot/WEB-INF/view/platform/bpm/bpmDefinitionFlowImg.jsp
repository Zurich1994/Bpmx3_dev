
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
	var _height=${shapeMeta.height};
	function setIframeHeight(){
		var mainIFrame = window.parent.document.getElementById("flowchart");
		if(!mainIFrame)return;
		mainIFrame.style.height=_height+100;
	};
	
	$(function(){
		if(self!=top){
			setIframeHeight();
		}
	});
	</script>
	<title>流程示意图</title>
</head>
<body >
	<div style="padding-top:40px;background-color: white;">
		<div style="position: relative;background:url('${ctx}/bpmImage?definitionId=${actDefId}') no-repeat;width:${shapeMeta.width}px;height:${shapeMeta.height}px;">
			 
		</div>
	</div>
</body>
</html>
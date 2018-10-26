<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>WORD表单套打</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/WordTemplate.js"></script>

<style type="text/css">
	.panel-body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	
	
	$(window).bind("load",function(){
		//Office控件初始化。
		OfficePlugin.init();
		if($.isIE()){
			documentOpenedOnCompleteCallback();
		}
	});
	
	function documentOpenedOnCompleteCallback(){
		var dataObj=$.parseJSON('${dataJson}');
		var main=dataObj.main;
		var subTables = dataObj.subTable;
		
		
		initMainTable(main);
		//enumSubTables(subTables);
		//填充子表
		fillSubtables(subTables);
	}
	
	
	
</script>
</head>
<body style="overflow:hidden">
	<div>
		<div class="tbar-title">
			<span class="tbar-label">word模板套打</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link print" id="print" href="javascript:;" onclick="printWord();"><span></span>打印</a>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" class="hidden" name="wordTemplate" controltype="office"  value="${sysWordTemplate.fileId}" style="height:100%;" menuRight="{}"/>
</body>
</html>


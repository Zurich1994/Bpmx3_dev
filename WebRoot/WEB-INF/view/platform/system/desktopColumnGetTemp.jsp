<%--
	time:2012-03-20 16:39:01
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>桌面栏目模板</title>
<%@include file="/commons/include/form.jsp" %>
<%-- <link rel="stylesheet" href="${ctx}/js/jquery-ui-portlet/lib/themes/1.8/start/jquery-ui-1.8.5.custom.css" />
<link rel="stylesheet" href="${ctx}/js/jquery-ui-portlet/css/jquery.portlet.css?v=1.1.3" /> --%>
<f:link href="jquery-ui-portlet/jquery.portlet.css"></f:link>
<f:link href="jquery-ui-portlet/jquery-ui-1.8.5.custom.css"></f:link>
<script src="${ctx}/js/jquery-ui-portlet/lib/jquery-ui-1.8.14.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-ui-portlet/script/jquery.portlet.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js "></script>
<script type="text/javascript">
	$(function(){
		var columnName="${column.columnName}";
		$("#column").portlet({
			 sortable: true,
			 columns: [{
	                width: 400,
	                portlets: [
	                 {
	                    title: columnName,
	                    icon: 'ui-icon-signal-diag',
	                    content: {
	                        //设置区域内容属性
	                        style: {
	                            height: '300px'
	                        },
	                        type: 'ajax',
	                        dataType: 'json',
	                        url: 'getColumnData.ht?id=${column.id}',
	                        formatter: function(o, pio, data) {
	                        	var html='';
	                           	if(data.html){
	                        	   html=data.html;
	                          	}else{
	                          		var template=$("#template").val();
	                          		var json=data.result;
	                        	  	html=easyTemplate(template,json).toString();
	                           	}
	                           	return html;
	                        }
	                    }
	                }]
			 }]
			});
	});
</script>
</head>
<body style="overflow:hidden;">
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">查看栏目</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link close" onclick="frameElement.dialog.close();"><span></span>关闭</a></div>
				</div>
			</div>
		</div>
</div>
<div class="panel-body">
<div id="column"></div>
<textarea id="template" style="display: none;">${column.html}</textarea>
</div>
</body>
</html>


<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px" ng-app="baseApp" >
    </head>
     
		<title><spr:message code="bpmFormDef.preview"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-res.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-input.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-box.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-color.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-base.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/style.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ui-on.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/main.css">
        <link rel="stylesheet" href="${ctx}/mobileapp/css/ht.css">
        <script src="${ctx}/mobileapp/js/util/json2.js">
        </script>
        <script src="${ctx}/mobileapp/js/config.js">
        </script>
        <script src="${ctx}/mobileapp/js/ng/i18n/i18n.js">
        </script>
        <script src="${ctx}/mobileapp/js/ng/base/angular.min.js">
        </script>
        <script src="${ctx}/mobileapp/js/ng/custom/components.js">
        </script>
        <script src="${ctx}/mobileapp/js/ng/custom/app.js">
        </script>
        <script src="${ctx}/mobileapp/js/ng/base/angular-translate.min.js">
        </script>
        <script src="${ctx}/mobileapp/js/zy/zy_control.js">
        </script>
        <script src="${ctx}/mobileapp/js/zy/zy_click.js">
        </script>
        <script src="${ctx}/mobileapp/js/zy/zy_event.js">
        </script>
        <script src="${ctx}/mobileapp/js/util/util.js">
        </script>
        <script type="text/javascript" src="${ctx}/mobileapp/js/iscroll/iscroll.js">
        </script>
		<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
		<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>

<script type="text/javascript">
$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	
	var win;
	$("a.save").click(function() {
		var rtn=CustomForm.validate();
		if(rtn){
			var data=CustomForm.getData();
			//设置表单数据
			$("#txtJsonData").val(data);
			if(!win){
				var obj=$("#divJsonData");
				win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
			}
			win.show();
		}
		
	});
});

function closeWin(){
	
	window.close();
}
</script>


</head>
<body class="um-vp c-gra1">

				${html}

	
</body>
</html>


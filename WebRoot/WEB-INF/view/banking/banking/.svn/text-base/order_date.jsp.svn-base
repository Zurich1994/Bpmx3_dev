<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<%@include file="/commons/include/get.jsp" %>
    <title>My JSP 'order_date.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRightDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript">
function putData(){
	$('#form1').attr('action',__ctx+'/banking/banking/billPay/getdate.ht');
	$('#form1').submit();
} 
</script>
  </head>
  
  <body>
  <form id="form1" action="">
  Start Date (yyyy-mm-dd):  <input type="text" name="start_time_text" ><br>
  End Date (yyyy-mm-dd):  <input type="text" name="end_time_text" ><br>
  <input type="button" onclick="putData()" value="提交"/>
  </form>
  </body>
</html>
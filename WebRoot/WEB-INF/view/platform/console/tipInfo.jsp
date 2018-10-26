<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title>友情提示</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 180px;
	margin-right: 0px;
	margin-bottom: 0px;
}body,td,th {
	font-size: 12px;
}
.STYLE1 {
	color: #354D79;
	line-height: 15px;
}
-->
</style>

</head>
<body>
<table align="center"  height="111" width="457" border="0" cellpadding="0" cellspacing="0">
  <tbody><tr valign="middle" >
    <td height="111" valign="middle" width="480" background="${ctx}/styles/default/images/warm.gif"><table height="89" width="449" border="0" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td height="12" width="67">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td rowspan="2" height="45">&nbsp;</td>
        <td height="44" valign="top" width="390">&nbsp;</td>
        <td valign="middle" width="50">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" valign="top"><span class="STYLE1">${content}</span></td>
        <td valign="bottom" width="50"><a href="javascript:;"></a></td>
      </tr>
    </tbody></table></td>
  </tr>
</tbody></table>
</body>
</html>

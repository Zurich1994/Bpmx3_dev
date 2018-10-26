<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>账户概要管理</title>
<%@include file="/commons/include/get.jsp" %>
<script>
function checkform(){
if(document.form.number.value=="")
{
   alert("Number of checks不能为空");
   return false;
}else if (document.form.account_no.value==""){
	alert("You did not specify account");
    return false;
}else if(document.form.check_design.value=="")
{
	alert("You did not select check design");
    return false;
}

return true;
}
</script>
</head>
<body>
<%String name=request.getParameter("name");  %>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">账户概要管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">	
				<a class="link back" href="/mas/banking/banking/userTab/success.ht?name=<%=name %>"><span></span>返回</a>				
				</div>	
			</div>
			<h1 align="center" >欢迎您  <%=name %></h1>
		</div>
		<form  name="form" action="2Edit.ht?name=<%=name%>" method="post" onsubmit="return checkform();">
		<div class="panel-body" align="center">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="checkaccountList" id="checkaccountItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${checkaccountItem.id}">
				</display:column>
			
				<display:column property="ACCOUNTNO" title="账号" sortable="true" sortName="F_ACCOUNTNO"></display:column>
				<display:column property="TYPE" title="类型" sortable="true" sortName="F_TYPE" ></display:column>
				<display:column property="BALANCE" title="金额" sortable="true" sortName="F_BALANCE"></display:column>
				
				<display:column title="管理" media="html" style="width:220px">
					
					<a href="get.ht?id=${checkaccountItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			
			<hotent:paging tableId="checkaccountItem"/>
		</div>
		
				
				<h3>Please choose the check design below:</h3>
				
			<table cellpadding="5" border=3 align="center" bgcolor="#00ff00" height="30px" style="color: red" >
      <tr height="50px" >
        <td><input type="radio" name="check_design" value="1"> 
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 1&nbsp;&nbsp;</td>
        <td><input type="radio" name="check_design" value="2">
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 2&nbsp;&nbsp;</td>
      <tr height="50px">
        <td><input type="radio" name="check_design" value="3"> 
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 3&nbsp;&nbsp;</td>
        <td><input type="radio" name="check_design" value="4"> 
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 4&nbsp;&nbsp;</td>
      <tr height="50px">
        <td><input type="radio" name="check_design" value="5"> 
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 5&nbsp;&nbsp;</td>
        <td><input type="radio" name="check_design" value="6"> 
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 6&nbsp;&nbsp;</td>
      <tr height="50px">
        <td><input type="radio" name="check_design" value="7">
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 7&nbsp;&nbsp;</td>
        <td><input type="radio" name="check_design" value="8">
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 8&nbsp;&nbsp;</td>
      <tr height="50px">
        <td><input type="radio" name="check_design" value="9">
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 9&nbsp;&nbsp;</td>
        <td><input type="radio" name="check_design" value="10">
            检&nbsp; 查&nbsp; 设&nbsp; 计&nbsp; 10&nbsp;&nbsp;</td>
      </table>
			<b>Number of checks: </b><input type="text" name="number"><br>
      <b>Charge account:&nbsp; &nbsp;&nbsp; </b><input type="text" name="account_no"><br>
     
					
					<div class="l-bar-separator"></div>
					<input type="submit" value="提交" name="submit" >					
										
				 </form>
		<!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>



<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userTabSave.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
 <table>
 <tr>
 <td>欢迎登录网上银行！</td>
 </tr>
 <tr>
 <td>
<%  
String name=request.getParameter("name");  
out.println("尊贵的"+name+"用户");  
%>  
</td>  
</tr>
<tr>
   
   <td>  <a href="/mas/banking/banking/account1/list.ht?name=<%=request.getParameter("name")%>">账户概要</a></td> 
   </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/checkyan/list.ht?name=<%=request.getParameter("name")%>">核查明细</a></td> 
     </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/billPay/list.ht?name=<%=request.getParameter("name")%>">账单支付</a></td>
     </tr>   

     <tr>   
   <tr>  
   <td>   <a href="/mas/banking/banking/add_payee/add.ht?name=<%=request.getParameter("name")%>">添加支付者信息</a></td>
     </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/payee/get.ht?name=<%=request.getParameter("name")%>">显示支付者信息</a></td>
     </tr>   
    
   <tr>  
   <td>  <a href="/mas/banking/banking/order_date.ht?name=<%=request.getParameter("name")%>">账单状态 </a></td>
    </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/update/list.ht?name=<%=request.getParameter("name")%>">更新信息</a></td>
    </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/account/listb.ht?name=<%=request.getParameter("name")%>">加载账户信息</a></td>
    </tr>   
   <tr>  
   <td>   <a href="/mas/banking/banking/checkaccount/list.ht?name=<%=request.getParameter("name")%>">账单审查</a></td>
    </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/orderCheck/list.ht?name=<%=request.getParameter("name")%>">加载订单</a></td>
    </tr>   
   <tr>  
   <td>  <a href="/mas/banking/banking/account/list.ht?name=<%=request.getParameter("name")%>">转帐 </a></td>
    </tr>   


<tr>
  <td> <a href="/mas/banking/banking/userTab/Edit.ht">退出</a> </td>
</tr>
</table>
  </body>
</html>

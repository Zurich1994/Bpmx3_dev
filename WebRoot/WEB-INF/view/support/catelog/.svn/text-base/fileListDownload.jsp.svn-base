<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@include file="/commons/include/html_doctype.html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fileListDownload.jsp' starting page</title>
    <%@include file="/commons/include/customForm.jsp"%>   
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
    <%
    String fileName = "abc.txt";
    String filePath = "c:\\";
 
    out.clear();
    response.reset();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
    OutputStream os = response.getOutputStream();
    try {
        FileInputStream fis = new FileInputStream(filePath + fileName);
        try {
            byte[] buffer = new byte[1024 * 10];
            for (int read; (read = fis.read(buffer)) != -1;) {
                os.write(buffer, 0, read);
            }
        } finally {
            fis.close();
        }
    } finally {
        os.close();
    }
%>
  </body>
</html>

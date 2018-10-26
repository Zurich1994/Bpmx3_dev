<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ page import="com.hotent.order_date.OrderDate.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'validate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head> 
  <%!public String time_start[],time_end[]; %>
  <%!public int year_start,year_end,month_start,month_end,day_start,day_end; %>
  <%!int month_max[]={31,28,31,30,31,30,31,31,30,31,30,31}; %>
  <%!public void february_max(int year){
  	month_max[1] = 28;
  	if((year%400==0)||(year%4==0&&year%400!=0))
  	month_max[1] = 29;
  } %>
  <%!public boolean format_judge(String s) {
	int i;
  	if(s.length()==10)
  	{
  		for(i=0;i<10;i++) {
  			if(i==4||i==7)
  			{	if(s.charAt(i)!='-')
  					return false;	}
  			else	
  			{	if((s.charAt(i)>'9')||(s.charAt(i)<'0'))
  					return false;	}
  		}
  		return true;
  	}
  	else
  		return false;
  } %>
  <%!public void date_parting(){
    year_start = Integer.parseInt(time_start[0]);
    month_start = Integer.parseInt(time_start[1]);
    day_start = Integer.parseInt(time_start[2]);
    year_end = Integer.parseInt(time_end[0]);
    month_end = Integer.parseInt(time_end[1]);
    day_end = Integer.parseInt(time_end[2]);};
   %>
  <body>
    <%
    String start_time = request.getParameter("start_time_text");
    String end_time = request.getParameter("end_time_text");
	if((!format_judge(start_time))||(!format_judge(end_time)))
    {
    	request.setAttribute("error","日期格式有误");
   		request.getRequestDispatcher("order_date.jsp").forward(request,response);
    } 
	else {
		time_start = start_time.split("-");
    	time_end = end_time.split("-");
    	date_parting();
    	if(year_start<1000||year_end<1000)
    	{
    		request.setAttribute("error","年有误");
    		request.getRequestDispatcher("order_date.jsp").forward(request,response);
    	}
    	else {
    		if(month_start<1||month_end<1||month_start>12||month_end>12)
    		{
    			request.setAttribute("error","月有误");
    			request.getRequestDispatcher("order_date.jsp").forward(request,response);
    		}
    		else {
    			february_max(year_start);
    			if(day_start>month_max[month_start-1]||day_start<1)
    			{
    				request.setAttribute("error","日有误");
    				request.getRequestDispatcher("order_date.jsp").forward(request,response);
    			}
    			else {
    				february_max(year_end);
    				if(day_end>month_max[month_end-1]||day_end<1)
    				{
    					request.setAttribute("error","日有误");
    					request.getRequestDispatcher("order_date.jsp").forward(request,response);
    				}
    				else {
    					if(year_start>year_end)
    					{
    						request.setAttribute("error","日期顺序有误");
    						request.getRequestDispatcher("order_date.jsp").forward(request,response);
    					}
    					else {
    						if(month_start>month_end)
    						{
    							request.setAttribute("error","日期顺序有误");
    							request.getRequestDispatcher("order_date.jsp").forward(request,response);
    						}
    						else {	
    							if(day_start>day_end)
    							{
    								request.setAttribute("error","日期顺序有误");
    								request.getRequestDispatcher("order_date.jsp").forward(request,response);
    							}
    							else {
    									session.setAttribute("start__time",start_time);
									session.setAttribute("end__time",end_time);
    									response.sendRedirect("message.jsp");
    								}}}}}}}} %> <br>
  </body>
</html>


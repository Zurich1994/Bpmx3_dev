<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*,
		org.quartz.JobDetail,
		org.quartz.Trigger,
		org.quartz.CronTrigger"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<title>计划列表</title>
		<%@include file="/commons/include/get.jsp" %>
	</head>
	<body>
		<div class="panel">
		<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">计划列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link add" href="addTrigger1.ht?jobName=${jobName}"><span></span>添加</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link back"  href="getJobList.ht"><span></span>返回</a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					    <table  cellpadding="1" cellspacing="1"   class="table-grid">
						    <thead>
							    <tr>
								    <th>序号</th>
								    <th>任务名称</th>
								    <th>计划名称</th>
								    <th>计划描述</th>
								    <th>状态</th>
								    <th>管理</th>
							    </tr>
						    </thead>
						    <tbody>
						    <%
								List list=(List)request.getAttribute("list");
								HashMap mapState=(HashMap)request.getAttribute("mapState");
								for(int i=0;i<list.size();i++)
								{
									Trigger trig=(Trigger)list.get(i);
									String trigName=trig.getKey().getName();
									String jobName=trig.getJobKey().getName();
									Trigger.TriggerState state=(Trigger.TriggerState)mapState.get(trigName);
									
									String toggleUrl="toggleTriggerRun.ht?name=" + trigName ;
									String delTrigger="delTrigger.ht?name="+trigName ;
									String logList="getLogList.ht?trigName="+trigName+"&jobName="+jobName;
				 			%>
					           <tr class="${status.index%2==0?'odd':'even'}">
					           	<td>
						  	     <%=i+1 %>
					            </td>
					           <td><%=jobName %></td>
					           <td><%=trigName  %></td>
					           <td><%=trig.getDescription() %></td>
					           <td>
					           <%if(state==Trigger.TriggerState.NORMAL){
									out.println("<font color='green'><b>启用</b></font>");
								}
								else if(state==Trigger.TriggerState.PAUSED){
									out.println("<font color='red'><b>禁用</b></font>");
								}
								else if(state==Trigger.TriggerState.ERROR){
									out.println("执行出错");
								}
								else if(state==Trigger.TriggerState.COMPLETE){
									out.println("完成");
								}
								else if(state==Trigger.TriggerState.BLOCKED){
									out.println("正在执行");
								}
								else if(state==Trigger.TriggerState.NONE){
									out.println("未启动");
								}
							%>
					           </td>
					           <td>
					               <%if(state==Trigger.TriggerState.NORMAL){%>
									<a   href="<%=toggleUrl%>" class="link edit">禁用</a>
									<%}
									else if(state==Trigger.TriggerState.PAUSED){%>
									<a  href="<%=toggleUrl%>" class="link edit">启用</a>
									<%} %>
									<a class="link del"   href="<%=delTrigger  %>" >删除</a>
									
									<a  href="<%=logList %>" class="link flowDesign" >日志</a>
					           </td>
					           </tr>
					       <%
				             }
				           %>
						    </tbody>
					    </table>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
	</body>
</html>
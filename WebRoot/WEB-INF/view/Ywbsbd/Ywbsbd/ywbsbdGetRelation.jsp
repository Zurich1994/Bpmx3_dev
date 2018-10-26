
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hotent.Ywbsbd.model.Ywbsbd.Ywbsbd" %>
<html>
<head>
<title>子系统设备关联表</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统设备关联表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht?defId=${defId}&nodeId=${nodeId}"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>"
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">子系统设备关联表</td>
  </tr>
  <tr ><%List<Ywbsbd> list=(List<Ywbsbd>)request.getAttribute("list");%>
   <td style="width:10%;" class="formTitle" nowrap="nowarp">子系统名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input type="text"  value="<%= list.get(0).getSysname()%>"/>
   </span></td>
   <td style="width:10%;" class="formTitle" nowrap="nowarp">流程名</td>
   <td style="width:15%;" class="formInput">
   <% List<String> listSubject=(List<String>)request.getAttribute("listSubject");for(String subject:listSubject){%>	<input type="text"  value="<%=subject%>"/><%} %>
   </td>
   <td style="width:10%;" class="formTitle" nowrap="nowarp">节点id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <%for(Ywbsbd ywbsbd:list){ %><input type="text"   value="<%=ywbsbd.getNodeId()%>"/><%} %>
   </span></td>
   <td style="width:10%;" class="formTitle" nowrap="nowarp">服务:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <%for(Ywbsbd ywbsbd:list){ %><input type="text"   value="<%=ywbsbd.getService()%>"/><%} %>
   </span></td>
  </tr>
 </tbody>
</table>
</body>
</html>


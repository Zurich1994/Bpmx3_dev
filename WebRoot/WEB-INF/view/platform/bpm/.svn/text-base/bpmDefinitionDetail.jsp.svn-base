<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
    <title>流程定义明细</title>
	<%@include file="/commons/include/form.jsp" %>
	<style type="text/css"> 
	    
	    div.flowNode{cursor:pointer;}
    </style>
</head>
<body>
		<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
				
				
		        <jsp:include page="incDefinitionHead.jsp">
			    	<jsp:param value="流程明细" name="title"/>
			    </jsp:include>

			 <div class="panel-container">
				<f:tab curTab="detail" tabName="flow"/>
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">分类</th>
						<td>${globalType.typeName}</td>
					</tr>
					<tr>
						<th width="20%">流程标题:</th>
						<td>${bpmDefinition.subject}</td>
					</tr>
					<tr>
						<th width="20%">流程定义Key:</th>
						<td>${bpmDefinition.defKey}</td>
					</tr>
					<tr>
						<th width="20%">任务标题生成规则:</th>
						<td>${bpmDefinition.taskNameRule}</td>
					</tr>
					<tr>
						<th width="20%">流程描述:</th>
						<td>${bpmDefinition.descp}</td>
					</tr>
					<tr>
						<th width="20%">创建人:</th>
						<td><f:userName userId="${bpmDefinition.createBy}"/></td>
					</tr>
					<tr>
						<th width="20%">创建时间:</th>
						<td><fmt:formatDate value="${bpmDefinition.createtime}" pattern="yyyy-MM-dd HH:mm"/> </td>
					</tr>
					<tr>
						<th width="20%">更新人:</th>
						<td><f:userName userId="${bpmDefinition.updateBy}"/></td>
					</tr>
					<tr>
						<th>更新原因:</th>
						<td>${bpmDefinition.reason}</td>
					</tr>
					<tr>
						<th width="20%">更新时间:</th>
						<td><fmt:formatDate value="${bpmDefinition.updatetime}" pattern="yyyy-MM-dd HH:mm"/> </td>
					</tr>
					<tr>
						<th width="20%">流程状态:</th>
						<td>
							<c:choose>
								<c:when test="${bpmDefinition.status==1}">
									<font color='green'>启用</font>
								</c:when>
								<c:when test="${bpmDefinition.status==2}">
									<font color='red'>禁用</font>
								</c:when>								
								<c:when test="${bpmDefinition.status==3}">
									<font color='red'>禁用(实例)</font>
								</c:when>
								<c:when test="${bpmDefinition.status==4}">
									<font color='green'>测试</font>
								</c:when>
								<c:otherwise><font color='red'>未知
								</font></c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%">版本号:</th>
						<td>${bpmDefinition.versionNo}</td>
					</tr>
					<tr>
						<th width="20%">activiti流程定义ID:</th>
						<td>${bpmDefinition.actDefId}</td>
					</tr>
					<tr>
						<th width="20%">act流程定义Key:</th>
						<td>${bpmDefinition.actDefKey}</td>
					</tr>
				</table>
				</div>
			</div>
		</div>

</body>
</html>

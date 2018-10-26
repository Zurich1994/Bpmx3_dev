<%--
	time:2011-11-09 11:20:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>组织架构明细</title>
	<%@include file="/commons/include/get.jsp" %>
	<style type="text/css"> 
		html{scroll:no;height:100%}
	    body {scroll:no;height:100%; padding:0px; margin:0;overflow:auto !important}
	   
    </style>
</head>
<body>
 <div class="panel" id="toppanel">	
 <c:if test="${isOtherLink==0}">
 		<c:choose>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="info" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="info" tabName="sysOrgGrade"/>
		  	</c:otherwise>
		  </c:choose>
		<c:if test="${flag== 1}">
		    <div class="panel-toolbar" id="pToolbar">
				<div class="toolBar">
						<div class="group"><a class="link back"  href="slistById.ht?orgId=${sysOrg.orgSupId}&path=${path}" >返回</a></div>	
				</div>	
		    </div>
	    </c:if>
	   </c:if>
		<div class="panel-body" id="pbody">	
			<c:choose>
				<c:when test="${empty sysOrg}">
					<div style="text-align: center;margin-top: 10%;">尚未指定具体组织!</div>
				</c:when>
				<c:otherwise>
					
						<table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">所属维度:</th>
								<td width="30%">${sysOrg.demName}</td>
								<th>组织代码:</th>
								<td><span class="green">${sysOrg.code}</span></td>
							</tr>
							<tr>
								<th width="20%">组织名称:</th>
								<td width="30%"><span class="green">${sysOrg.orgName}</span></td>
								<th width="20%">建立人:</th>
								<td width="30%">${sysOrg.createName}</td>
							</tr>
							<tr>
								<th>上级组织:</th>
								<td>${sysOrg.orgSupName}</td>
								<th>修改人:</th>
								<td>${sysOrg.updateName}</td>
							</tr>
							
							<tr>
								<th>组织类型:</th>
								<td>							
						 			<c:forEach items="${sysOrgTypelist}" var="org" >
						 					<c:if test="${sysOrg.orgType eq org.id}"><span class="green">${org.name}</span></c:if>													
									</c:forEach> 				
								</td>
								<th>建立时间:</th>
								<td>${f:shortDate(sysOrg.createtime)}</td>
							</tr>
							<tr>
								<th>组织负责人:</th>
								<td>${userNameCharge}</td>
								<th>修改时间:</th>
								<td>${f:shortDate(sysOrg.updatetime)}</td>
							</tr>
							<tr>
							    <th>组织全路径:</th>
								<td><span class="green">${sysOrg.orgPathname}</span></td>
							    <th>编制人数:</th>
							    <td colspan="3">${sysOrg.orgStaff}</td>
							 </tr>
							 <tr>
							    <th>组织描述:</th>
							    <td colspan="3">${sysOrg.orgDesc}</td>
							 </tr>
					    </table>
				</c:otherwise>
			</c:choose>	
		    
	    </div>   
 </div>
</body>
</html>

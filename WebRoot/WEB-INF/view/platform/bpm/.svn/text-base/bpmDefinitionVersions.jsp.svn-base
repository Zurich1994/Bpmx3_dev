<%
	//作用:查看某一流程所有版本历史
	//作者:csx
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程定义扩展管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
            <jsp:include page="incDefinitionHead.jsp">
		   		<jsp:param value="流程历史版本" name="title"/>
			</jsp:include>
		 <div class="panel-container">
            <f:tab curTab="history" tabName="flow"/>
			<div class="panel">
				
				<div class="panel-body">
					
					    <display:table name="bpmDefinitionList" id="bpmDefinitionItem" requestURI="versions.ht"  sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="序号" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="defId" value="${bpmDefinitionItem.defId}">
							</display:column>
							<display:column property="subject" title="标题" sortable="false" sortName="subject"></display:column>
							<display:column property="defKey" title="流程定义Key" sortable="false" sortName="defKey"></display:column>
							<display:column title="分类名称" sortable="false" sortName="typeId">
								<c:out value="${bpmDefinitionItem.typeName}"></c:out>
							</display:column>
							<display:column property="versionNo" title="版本号" sortable="false" sortName="versionNo"></display:column>
							<display:column property="reason" title="修改原因" sortable="false" sortName="reason"></display:column>
							<display:column title="创建时间" sortable="false" sortName="createtime">
								<fmt:formatDate value="${bpmDefinitionItem.createtime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
							<display:column title="修改时间" sortable="false">
								<fmt:formatDate value="${bpmDefinitionItem.updatetime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
							<display:column property="descp" title="描述" sortable="false" sortName="descp"></display:column>
							<display:column title="管理" media="html" style="width:220px;">
								<a href="del.ht?defId=${bpmDefinitionItem.defId}&isOnlyVersion=true" class="link del" title="删除">删除</a>
								<a href="design.ht?defId=${bpmDefinitionItem.defId}" target="_blank" class="link flowDesign" title="在线流程设计">查看设计</a>
								<a href="detail.ht?defId=${bpmDefinitionItem.defId}&defIdForReturn=${bpmDefinition.defId}" class="link detail" title="在线流程设计">明细</a>
							</display:column>
						</display:table>
					
				</div><!-- end of panel-body -->				
		</div>	
		</div> <!-- end of panel -->
</body>
</html>



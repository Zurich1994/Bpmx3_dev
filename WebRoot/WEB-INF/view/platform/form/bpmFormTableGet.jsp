<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>自定义表明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义表明细</span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:choose>
						<c:when test="${canClose==1}">
							<div class="group">
								<a class="link close" href="###" onclick="dialog.close()"><span></span>关闭</a>
							</div>						
						</c:when>
						<c:otherwise>
							<div class="group">
								<a class="link back" href="list.ht"><span></span>返回</a>
							</div>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			</c:if>
		</div>

		<div class="panel-body">
				  <div class="table-top">
							<div class="table-top-left">
										<span class="label"> 表名:&nbsp;<b>${table.tableName}</b> &nbsp;&nbsp;&nbsp;注释:&nbsp;<b>${table.tableDesc }</b>
												&nbsp;&nbsp;&nbsp;是否主表:<b> <c:choose>
														<c:when test="${table.isMain == 1 }">
													主表
												</c:when>
														<c:otherwise>
													子表&nbsp;&nbsp;&nbsp;所属主表:${mainTable}
												</c:otherwise>
													</c:choose>
											</b> 是否外部表:<b> <c:choose>
														<c:when test="${table.isExternal == 1 }">
													是
												</c:when>
														<c:otherwise>
													否
												</c:otherwise>
													</c:choose>
											</b>
										</span>
						</div>												
					</div>						
				<table id="list" cellpadding="1" cellspacing="1" class="table-grid">
					<tr>
						<th >序号</th>
						<th >字段名称</th>
						<th>字段注释</th>
						<th>字段类型</th>
						<th>必填</th>
						<th>显示到列表</th>
						<th>作为查询条件</th>
						<th>是否流程变量</th>
					</tr>
					<c:if test="${fn:length(fields) == 0}">
						<tr>
							<td colspan="8">无</td>
						</tr>
					</c:if>
					<c:forEach items="${fields}" var="field" varStatus="status">
						<c:if test="${field.isHidden == 0 }">
							<tr>
								<td>${status.index + 1}</td>
								<td>${field.fieldName }</td>
								<td>${field.fieldDesc }</td>
								<td><c:if test="${field.fieldType == 'varchar'}">文字(${field.charLen })</c:if>
									<c:if test="${field.fieldType == 'number'}">数字(${field.intLen }, ${field.decimalLen })</c:if>
									<c:if test="${field.fieldType == 'date'}">日期</c:if> <c:if
										test="${field.fieldType == 'clob'}">大文本</c:if></td>
								<td><c:if test="${field.isRequired == 1 }">√</c:if></td>
								<td><c:if test="${field.isList == 1 }">√</c:if></td>
								<td><c:if test="${field.isQuery == 1 }">√</c:if></td>
								<td><c:if test="${field.isFlowVar == 1 }">√</c:if></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
					
				<c:if test="${table.isMain == 1 && fn:length(subList)>0}">
					<br>
					<table  cellpadding="1" cellspacing="1" class="table-grid">
						<tr>
							<td colspan="4">子表列表</td>
						</tr>
						<tr>
							<th >序号</th>
							<th >表名</th>
							<th >注释</th>
							<th>明细</th>
						</tr>
						<c:forEach items="${subList}" var="table" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${table.tableName }</td>
								<td>${table.tableDesc }</td>
								<td><a href="get.ht?tableId=${table.tableId}&canClose=${canClose}" class="link detail">明细</a></td>
							</tr>
						</c:forEach>
					</table>
					
				</c:if>
				
			
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>
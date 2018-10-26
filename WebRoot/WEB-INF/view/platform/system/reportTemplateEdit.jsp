<%--
	time:2012-04-12 09:59:47
	desc:edit the 报表模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 报表模板</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=reportTemplate"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			}
			if(${reportTemplate.reportId ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				var path = $("#file").val();
				var extName = path.substring(path.length-3,path.length);
				if(path==''){
					$.ligerDialog.warn("请上传模板文件");
				}else if(extName!='cpt'){
					$.ligerDialog.warn("请选择*.cpt报表模板文件");
				}else{
					$("#reportTemplateForm").submit();
				}
			});
			
			function showResponse(responseText){
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){
					$.ligerDialog.success(obj.getMessage(),"提示",function(){
						window.location.href="list.ht";
					});
				}else{
					$.ligerDialog.err('出错信息',"保存报表模板失败",obj.getMessage());
				}
			}
			
		});

	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${reportTemplate.reportId !=null }">
		            <span class="tbar-label">编辑报表模板</span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label">添加报表模板</span>
		        </c:otherwise>
		    </c:choose>				
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="reportTemplateForm" method="post" action="save.ht" enctype="multipart/form-data">
		
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">标题:  <span class="required">*</span></th>
						<td>
							<input type="text" id="title" name="title" 
								value="${reportTemplate.title}" class="inputText"/>
						</td>
					</tr>
					<tr>
						<th width="20%">描述:  <span class="required">*</span></th>
						<td>
							<textarea rows="5" cols="60" id="descp" name="descp"
								class="textarea">${reportTemplate.descp}</textarea>
						</td>
					</tr>
					<tr>
						<th width="20%">报表模板路径:  <span class="required">*</span></th>
						<td>
							<input id="file" name="file" style="width: 200px;" class="inputText" type="file" 
								value="${reportTemplate.reportLocation}" />
						</td>
					</tr>
					<tr>
						<th width="20%">标识key: </th>
						<td>
							<input type="text" id="reportKey" name="reportKey" 
								value="${reportTemplate.reportKey}" class="inputText"/>
						</td>
					</tr>
					<tr>
						<th width="20%">是否缺省: </th>
						<td>
							<select id="isDefaultIn" name="isDefaultIn">
								<c:if test="${reportTemplate.isDefaultIn==1||reportTemplate.isDefaultIn==null}">
									<option value="1" selected="selected">缺省</option>
									<option value="0">非缺省</option>
								</c:if>
								<c:if test="${reportTemplate.isDefaultIn==0}">
									<option value="1">缺省</option>
									<option value="0" selected="selected">非缺省</option>
								</c:if>
							</select>
						</td>
					</tr>
					
					<tr>
						<th width="20%">报表类型: </th>
						<td>
							<select id="typeId" name="typeId">
								<c:forEach var="type" items="${typelist}">
									<c:if test="${type.parentId!=0}">
										<c:if test="${reportTemplate.typeId==type.typeId}">
											<option selected="selected" value="${type.typeId}">${type.typeName}</option>
										</c:if>
										<c:if test="${reportTemplate.typeId!=type.typeId}">
											<option value="${type.typeId}">${type.typeName}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					
				</table>
		
			<input type="hidden" name="reportId" value="${reportTemplate.reportId}" />
			<input type="hidden" name="tmpCreateTime" value="${reportTemplate.createTime}">
			
		</form>
	</div>
</div>
</body>
</html>

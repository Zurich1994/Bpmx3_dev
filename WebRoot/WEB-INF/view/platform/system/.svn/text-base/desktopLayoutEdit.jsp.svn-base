<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 桌面布局</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopLayout"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			$(document).ready(function(){
				 if($(".cols").length==1)
					 $(".cols").attr("value","100").attr('disabled', 'disabled');
			});
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#desktopLayoutForm').submit(); 
			});
			showWidth();
		});
		
		function showWidth(){
			$('input[name=cols]:radio').click(function(){
				var cols=$(this).val();
				if(cols==1){
					$('#width2').hide();
					$('#width3').hide();
					$('#width1').val(100).attr('readOnly','readOnly');
				}else if(cols==2){
					$('#width1').val('').removeAttr("readOnly");
					$('#width2').show();
					$('#width3').hide();
				}else{
					$('#width1').val('').removeAttr("readOnly");
					$('#width2').show();
					$('#width3').show();
				}
			});
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopLayout.id !=null }">
			            <span class="tbar-label">编辑桌面布局</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加桌面布局</span>
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
				<form id="desktopLayoutForm" method="post" action="save.ht">
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">布局名: </th>
								<td><input type="text" id="name" name="name"   value="${desktopLayout.name}"  class="inputText"/></td>
							</tr>
							
							<tr>
								<th width="20%">列数: </th>
								<td>
								   <c:choose>
										<c:when test="${desktopLayout.cols==null}">
											<input type="radio" name="cols" value="1" />一列 
											<input type="radio" name="cols" value="2" />二列
											<input type="radio" name="cols" value="3" checked="checked"/>三列
										</c:when>
										<c:otherwise>
											${desktopLayout.cols }列
										</c:otherwise>
									</c:choose>
							</tr>
							<tr>
								<th width="20%">宽度: </th>
								<td>
								<c:choose>
									<c:when test="${desktopLayout.cols==null}">
										<input type="text" id="width1" name="width" digitsSum="100"  value="" style="width:25px"  class="inputText cols" />
										<input type="text" id="width2" name="width" digitsSum="100" value="" style="width:25px;"  class="inputText cols"/>
										<input type="text" id="width3" name="width" digitsSum="100" value=""  style="width:25px;" class="inputText cols" />
									</c:when>
									<c:otherwise>
										<%int idx=0; %>
										<c:forEach var="width" items="${aryWidth}">
											<%idx++; %>
											<input type="text" id="width<%=idx %>" name="width" digitsSum="100" value="${width}" style="width:25px" class="inputText cols" />
										</c:forEach>
									</c:otherwise>
								</c:choose>
										<a href="javascript:;" class="tipinfo"><span>提示：(只能填写0-100之间的数字，代表每一列的在首页桌面的百分比)</span></a>
								</td>
							</tr>
							<tr>
								<th width="20%">备注: </th>
								<td><input type="text" id="memo" name="memo" value="${desktopLayout.memo}" style="width:300px"  class="inputText"/></td>
							</tr>
						</table>
						<input type="hidden" name="id" value="${desktopLayout.id}" />
					
				</form>
		</div>
</div>
</body>
</html>

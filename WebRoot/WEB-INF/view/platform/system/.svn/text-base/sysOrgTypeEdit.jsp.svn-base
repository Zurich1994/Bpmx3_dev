<%--
	time:2012-11-27 09:55:21
	desc:edit the 组织结构类型
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 组织结构类型</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/IconDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var valid =$("#sysOrgTypeForm").form();
			
			var frm=$('#sysOrgTypeForm').form();
			$("a.save").click(function() {
				if($('#name').val() == "") alert("请输入名称");
				frm.setData();
				frm.ajaxForm(options);
				if(frm.valid()){
					form.submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						$('#levels').val(parseInt($('#levels').val())+1);
						$('#name').val('');
					}else{
						window.location.href = "${returnUrl}";
					}
				});
			} else {
				$.ligerDialog.err('出错信息',"编辑组织结构类型失败",obj.getMessage());
			}
		}
		
		function selectIcon(){
			 IconDialog({callback:function(src){
				 var temp=src.indexOf('//')+1;
				$("#icon").val(src.substring(temp,src.length));
				$("#iconImg").attr("src",src);
				$("#iconImg").show();
			}});
		};
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysOrgType.id !=null}">
			        <span class="tbar-label">编辑组织结构类型</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加组织结构类型</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href=" ${returnUrl}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysOrgTypeForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			
				<tr>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${sysOrgType.name}"  class="inputText" validate="{required:true,maxlength:100}"  />
						<c:choose>
							    <c:when test="${demId != null}">		
							    		<input type="hidden"  id="demId" name="demId" value="${demId}"  class="inputText" validate="{required:false,maxlength:100}"  />						
							    </c:when>
							      <c:when test="${demId == null}">							
							     		<input type="hidden"  id="demId" name="demId" value="${sysOrgType.demId}"  class="inputText" validate="{required:false,maxlength:100}"  />	
							    </c:when>
							  </c:choose>			
					
					</td>
				</tr>
			
				<tr>
						<th width="20%">显示图标: </th>
						<td>
							<input type="hidden" id="icon" name="icon" value="${sysOrgType.icon}"  class="inputText"/>
							<img id="iconImg" alt="" src="${ctx }${sysOrgType.icon}" <c:if test="${sysOrgType.icon==null}">style="display:none;"</c:if>>
							<a class="link detail" href="javascript:selectIcon();">选择</a>
						</td>
				</tr>
				<tr>
					<th width="20%">级别: </th>
					<td>
							<c:choose>
							    <c:when test="${levels != null}">							
							     		<input type="text" id="levels"  readonly="readonly"  name="levels"  value=" ${levels}"  class="inputText" validate="{required:false,maxlength:22,number:true }"  />	
							    </c:when>
							      <c:when test="${levels == null}">							
							     		<input type="text" id="levels"  readonly="readonly"  name="levels"  value=" ${sysOrgType.levels}"  class="inputText" validate="{required:false,maxlength:22,number:true }"  />					
							    </c:when>
							  </c:choose>			
					</td>
				</tr>
				<tr>
					<th width="20%">备注: </th>
					<td><input type="text" id="memo" name="memo" value="${sysOrgType.memo}"  class="inputText" validate="{required:false,maxlength:200}"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${sysOrgType.id}" />					
		</form>
		
	</div>
</div>
</body>
</html>

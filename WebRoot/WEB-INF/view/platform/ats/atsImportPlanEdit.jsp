<%--
	time:2015-05-26 13:50:13
	desc:edit the 打卡导入方案
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 打卡导入方案</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsImportPlanForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsImportPlanForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = "${ctx}/platform/ats/atsImportPlan/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsImportPlan/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		
		function addRow(){
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			$("#trContainer").append(tr);
		}
		
		function delTr(obj) {
			$(obj).closest("tr").remove();
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsImportPlan.id !=null}">
			        <span class="tbar-label"><span></span>编辑打卡导入方案</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加打卡导入方案</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsImportPlanForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsImportPlan.code}"  class="inputText" validate="{required:false,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsImportPlan.name}"  class="inputText" validate="{required:false,maxlength:384}"  /></td>
				</tr>
				<tr>
					
					<th width="20%">分割符: </th>
					<td>
						<select id="separator" name="separator">
							<option value="0">逗号</option>
							<option value="1">Tab</option>
							<option value="2">#</option>
						</select>
					</td>
					<th width="20%">描述: </th>
					<td><input type="text" id="memo" name="memo" value="${atsImportPlan.memo}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
			</table>
			<textarea style="display: none" id="pushCardMap" name="pushCardMap">${fn:escapeXml(atsImportPlan.pushCardMap)}</textarea>	
			<input type="hidden" name="id" value="${atsImportPlan.id}" />
		</form>
		<div class="panel-detail">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a onclick="addRow()" class="link add"><span></span>添加</a></div>
					<div class="group"><a onclick="delSubRow()" class="link del"><span></span>删除</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
				<table cellpadding="1" cellspacing="1"  class="table-grid">
					<tr>
					  <th align="center" width="50px">选择</th>
					  <th align="center">对应字段</th>
					  <th align="center">源文件字段</th>
					  <th align="center">删除</th>
					</tr>
					<tbody id="trContainer">
				    </tbody>
				</table>
		</div>
		
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr" onclick="checkTr(this)">
					<td ><input class="pk" type="checkbox" name="select"/>
					<td>
						<select var="correspondFiled" validate="{required:true}">
							<option value="cardNumber">考勤卡号</option>
							<option value="cardDate">打卡日期</option>
						</select>
					</td>
					<td>
						<input type="text" class="inputText " var="filedName" />
					</td>>
					<td>
						<a class="link del " href="javascript:;"  title="删除" onclick="delTr(this)"></a>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		
	</div>
</div>
</body>
</html>

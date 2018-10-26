<%--
	time:2015-05-26 10:07:27
	desc:edit the 考勤组
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 考勤组</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsAttenceGroupForm").attr("action","save.ht");
				submitForm();
			});
			initDetailList();
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttenceGroupForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				$('#detailList').val(getDetailList());
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsAttenceGroup/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		function initDetailList() {
			var detailList = $('#detailList').val();
			if ($.isEmpty(detailList))
				return;
			var detailLists = $.parseJSON(detailList);
			add(detailLists);
		}
		
		function getDetailList(isJson){
			var json =  [];
			$("#trContainer tr[var='templTr']").each(function(){
				var me = $(this),obj={};
				obj.id =$("[var='id']",me).val();
				obj.account =$("[var='account']",me).html();
				obj.userName =$("[var='userName']",me).html();
				obj.orgName =$("[var='orgName']",me).html();
				json.push(obj);
			});
			return  isJson?json:JSON2.stringify(json);
		
		}
		
		
		function addAttendanceFile(){
			AtsAttendanceFileDialog({
				params:getDetailList(true),
				callback:function(data){
					var ids =data.id.split(","),
						accounts =data.account.split(","),
						userNames =data.userName.split(","),
						orgNames =data.orgName.split(","),
						d = [];
					for (var i = 0; i < ids.length; i++) {
						var obj ={
							id:ids[i],
							account:accounts[i],
							userName:userNames[i],
							orgName:orgNames[i]
						};
						d.push(obj);
					}
					$('#trContainer').empty();
					add(d);
				}
			});
		}
		
		function add(data) {
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			for (var i = 0, c; c = data[i++];) {
				$("[var='id']", tr).val(c.id);
				$("[var='account']", tr).html(c.account);
				$("[var='userName']", tr).html(c.userName);
				$("[var='orgName']", tr).html(c.orgName);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
			}
		}
		
		function selectOrg(){
			OrgDialog({isSingle:true,callback:function(orgId,orgName){
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsAttenceGroup.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤组</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤组</span>
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
		<form id="atsAttenceGroupForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td width="30%"><input type="text" id="code" name="code" value="${atsAttenceGroup.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsAttenceGroup.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				
				</tr>
				<tr>
					<th width="20%">所属组织: </th>
					<td>
						<input type="hidden" id="orgId" name="orgId" value="${atsAttenceGroup.orgId}" />
						<input type="text" id="orgName"  value="${atsAttenceGroup.orgName}"  class="inputText"  validate="{required:true}"  readonly="readonly"/>
						<a href="javascript:;" onclick="selectOrg()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">描述: </th>
					<td><input type="text" id="memo" name="memo" value="${atsAttenceGroup.memo}"  class="inputText" validate="{required:false}"  /></td>
				
				</tr>
			</table>
						<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsAttenceGroup.detailList)}</textarea>
			<input type="hidden" name="id" value="${atsAttenceGroup.id}" />
		</form>
		<div class="panel-detail">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a onclick="addAttendanceFile()" class="link add"><span></span>添加</a></div>
					<div class="group"><a onclick="delSubRow()" class="link del"><span></span>删除</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
			<table cellpadding="1" cellspacing="1"  class="table-grid">
				<tr>
				  <th align="center" width="50px">选择</th>
				  <th align="center">员工编号</th>
				  <th align="center">姓名</th>
				  <th align="center">组织</th>
				</tr>
				<tbody id="trContainer">
			    </tbody>
			</table>
		</div>
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr"  onclick="checkTr(this)">
					<td ><input class="pk" type="checkbox" name="select"/>
						<input  type="hidden" var="id"/></td>
					<td var="account"></td>
					<td  var="userName"></td>
					<td var="orgName"></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>

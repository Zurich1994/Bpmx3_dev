<%--
	time:2015-05-17 15:48:22
	desc:edit the 法定节假日
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 法定节假日</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsLegalHolidayForm").attr("action","save.ht");
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
			var frm=$('#atsLegalHolidayForm').form();
			var subFrm=$("#trContainer").form();
			frm.ajaxForm(options);
			if(frm.valid()&&subFrm.valid()){
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
						window.location.href = "${ctx}/platform/ats/atsLegalHoliday/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		
		function getDetailList(){
			var json =  [];
			$("#trContainer tr[var='templTr']").each(function(){
				var me = $(this),obj={};
				obj.name =$("[var='name']",me).val();
				obj.startTime =$("[var='startTime']",me).val();
				obj.endTime =$("[var='endTime']",me).val();
				json.push(obj);
			});
			return JSON2.stringify(json)
		}
		
		function initDetailList() {
			var detailList = $('#detailList').val();
			if ($.isEmpty(detailList))
				return;
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			var detailLists = $.parseJSON(detailList);
			for (var i = 0, c; c = detailLists[i++];) {
				$("input[var='name']", tr).val(c.name);
				$("input[var='startTime']", tr).val(c.startTime);
				$("input[var='endTime']", tr).val(c.endTime);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
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
			    <c:when test="${atsLegalHoliday.id !=null}">
			        <span class="tbar-label"><span></span>编辑法定节假日</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加法定节假日</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsLegalHolidayForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsLegalHoliday.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsLegalHoliday.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					
				</tr>
				<tr>
					<th width="20%">年度: </th>
					<td><input type="text" id="year" name="year" value="${atsLegalHoliday.year}"  class="inputText wdateTime" dateFmt="yyyy"  validate="{required:true,number:true,maxIntLen:10}"  /></td>
					<th width="20%">描述: </th>
					<td>
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsLegalHoliday.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsLegalHoliday.id}" />
			<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsLegalHoliday.detailList)}</textarea>
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
						  <th align="center">假日</th>
						  <th align="center">开始时间</th>
						  <th align="center">结束时间</th>
						  <th align="center">删除</th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
			</div>
		</div>
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr" onclick="checkTr(this)">
					<td ><input class="pk" type="checkbox" name="select"/>
					<td width="30%">
						<input class="inputText" var="name" validate="{required:true}">
					</td>
					<td width="30%">  
						<input class="inputText date" var="startTime" validate="{required:true}">
					</td>
					<td width="30%">
						<input class="inputText date" var="endTime" validate="{required:true}">
					</td>
					<td>
						<a class="link del" href="javascript:;"  title="删除" onclick="delTr(this)"></a>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>

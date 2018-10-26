<%--
	time:2015-06-03 14:46:20
	desc:edit the 考勤计算设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>显示设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function() {
			$("a.save").click(function() {
				$("#atsAttenceCalculateSetForm").attr("action","save.ht");
				submitForm();
			});
			initDetail();
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttenceCalculateSetForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				$('#detail').val(getDetail());
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.alert("保存成功！","提示信息", function(rtn) {
					dialog.get('sucCall')(true);
					dialog.close();
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		
		function addRow(){
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			$("#trContainer").append(tr);
		}
		
		function initDetail() {
			var detail = $('#detail').val();
			if ($.isEmpty(detail))
				return;
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			var details = $.parseJSON(detail);
			for (var i = 0, c; c = details[i++];) {
				$("select[var='name']", tr).val(c.name);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
			}
		}
		
		function getDetail(){
			var json =  [];
			$("#trContainer tr[var='templTr']").each(function(){
				var me = $(this),obj={};
				obj.name =$("[var='name']",me).val();
				obj.lable =$("[var='name']",me).find("option:selected").text(); ;
				json.push(obj);
			});
			return JSON2.stringify(json)
		}
		function delTr(obj) {
			$(obj).closest("tr").remove();
		}
		//绑定上下移动	
		function move(me){
		 	var obj=$(me);
		 	var direct=obj.hasClass("moveup");
		 	var objFieldset=obj.closest('[var="templTr"]');
			if(direct){
				var prevObj=objFieldset.prev();
				if(prevObj.length>0){
					objFieldset.insertBefore(prevObj);	
				}
			}
			else{
				var nextObj=objFieldset.next();
				if(nextObj.length>0){
					objFieldset.insertAfter(nextObj);
				}
			}
		};
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsAttenceCalculateSet.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤计算设置</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤计算设置</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link add" id="dataFormAdd" href="javaScript:addRow()"><span></span>添加</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javaScript:dialog.close();"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsAttenceCalculateSetForm" method="post" action="save.ht">
			<textarea style="display: none" id="detail" name="detail">${fn:escapeXml(atsAttenceCalculateSet.detail)}</textarea>
			<input type="hidden" name="type" value="${type}"/>
		</form>
			<div class="panel-body">
					<table cellpadding="1" cellspacing="1"  class="table-grid">
						<tr>
						  <th align="center" width="50%">项目名称</th>
						  <th align="center">操作</th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
			</div>
			<div  id="templ"  style="display: none;">
				<table cellpadding="1" cellspacing="1"  class="table-detail">
					<tbody>
					<tr var="templTr">
						<td>
							 <select var="name" class="inputText"> 
							 	<option value=""></option>
							 	<option value="S11">应出勤时数</option>
							 	<option value="S12">实际出勤时数</option>
							 	<option value="S21">旷工次数</option>
							 	<option value="S22">旷工时数</option>
							 	<option value="S31">迟到次数</option>
							 	<option value="S32">迟到分钟</option>
							 	<option value="S41">早退次数</option>
							 	<option value="S42">早退分钟</option>
							 </select>
						</td>
						<td>
							<a class="link moveup" href="javascript:;"  title="上移" onclick="move(this)"></a>
							<a class="link movedown" href="javascript:;"  title="下移" onclick="move(this)"></a>
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

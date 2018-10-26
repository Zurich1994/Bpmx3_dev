<%--
	time:2015-05-16 21:58:02
	desc:edit the 日历模版
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 日历模版</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsCalendarTemplForm").attr("action","save.ht");
				submitForm();
			});
			initDetailList()
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsCalendarTemplForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				//TODO 日历模板中的条目必须为整周数！
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
						window.location.href = "${ctx}/platform/ats/atsCalendarTempl/list.ht";
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
				obj.week =$("[var='week']",me).val();
				obj.dayType =$("[var='dayType']",me).val();
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
				$("select[var='week']", tr).val(c.week);
				$("select[var='dayType']", tr).val(c.dayType);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
			}
		}
		
		function addRow(){
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			$("#trContainer").append(tr);
		}
		function genRow(){
			for(var i = 1 ; i < 8 ;i++){
				var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
				if(i==6||i==7)
					$("select[var='dayType']",tr).val(1);
				else
					$("select[var='dayType']",tr).val(0);
				if(i==7)
					$("select[var='week']",tr).val(0);
				else
					$("select[var='week']",tr).val(i);
				$("#trContainer").append(tr);
			}
		}
		function delTr(obj) {
			$(obj).closest("tr").remove();
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title" >
		    <c:choose>
			    <c:when test="${atsCalendarTempl.id !=null}">
			        <span class="tbar-label"><span></span>编辑日历模版</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加日历模版</span>
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
		<form id="atsCalendarTemplForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsCalendarTempl.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsCalendarTempl.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
		
				</tr>
				<tr>
					<th width="20%">是否启用: </th>
					<td>
						<select name="status" id="" value="${atsShiftType.status}" validate="{required:true,maxlength:384}"  >
							<option value="1" <c:if test="${atsShiftType.status==1}">selected </c:if>  >启用</option>
							<option value="0" <c:if test="${atsShiftType.status==0}">selected </c:if>  > 禁用</option>
						</select>
					</td>
					<th width="20%">描述: </th>
					<td colspan="1">
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsCalendarTempl.memo}</textarea>
					</td>
				</tr>
			</table>
			
			<input type="hidden" name="id" value="${atsCalendarTempl.id}" />
			<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsCalendarTempl.detailList)}</textarea>
			<input type="hidden" id="isSys" name="isSys" value="${atsCalendarTempl.isSys}" />
		</form>
			<div class="panel-detail">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a onclick="addRow()" class="link add"><span></span>添加</a></div>
						<div class="group"><a onclick="genRow()" class="link add"><span></span>生成</a></div>
						<div class="group"><a onclick="delSubRow()" class="link del"><span></span>删除</a></div>
					</div>	
				</div>
			</div>
			<div class="panel-body">
					<table cellpadding="1" cellspacing="1"  class="table-grid">
						<tr>
						  <th align="center" width="50px">选择</th>
						  <th align="center">星期</th>
						  <th align="center">日期类型</th>
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
					<td><select var="week">
							<option value="1">周一</option>
							<option value="2">周二</option>
							<option value="3">周三</option>
							<option value="4">周四</option>
							<option value="5">周五</option>
							<option value="6">周六</option>
							<option value="0">周日</option>
						</select>
					</td>
					<td>
						<select var="dayType" >
							<option value="0">工作日</option>
							<option value="1">休息日</option>
						</select>
					</td>
					<td>
						<a class="link del" href="javascript:;"  title="删除" onclick="delTr(this)"></a>
					</td>
				</tr>
				</tbody>
			</table>
		</div>

</body>
</html>

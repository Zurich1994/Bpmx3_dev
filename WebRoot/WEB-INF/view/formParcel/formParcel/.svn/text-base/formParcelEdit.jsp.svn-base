<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 表单数据包对应关系表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#formParcelForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#formParcelForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/formParcel/formParcel/formParcel/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty formParcelItem.id}">
			        <span class="tbar-label"><span></span>编辑表单数据包对应关系表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加表单数据包对应关系表</span>
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
	<form id="formParcelForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">表单数据包对应关系表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">表单名:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="form_name" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="表单名" value="${formParcel.form_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">数据包ID:</td>
   <td class="formInput" style="width:80%;"><input name="parcelID" type="text" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${formParcel.parcelID}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">数据包名:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="parcel_name" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="数据包名" value="${formParcel.parcel_name}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${formParcel.id}"/>
	</form>
</body>
</html>

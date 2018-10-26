<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 newjsprelation</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#newjsprelationForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#newjsprelationForm').submit();
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
						window.location.href = "${ctx}/Newjsprelation/Newjsprelation/newjsprelation/list.ht";
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
			    <c:when test="${not empty newjsprelationItem.id}">
			        <span class="tbar-label"><span></span>编辑newjsprelation</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加newjsprelation</span>
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
	<form id="newjsprelationForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="6">newjsprelation</td>
  </tr>
  <tr style="height:23px;">
   <td class="formTitle" nowrap="nowarp" style="width:13%;">FORMDEFID:</td>
   <td class="formInput" style="width:20%;"><input name="FORMDEFID" type="text" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${newjsprelation.FORMDEFID}" /></td>
   <td class="formTitle" nowrap="nowarp" style="width:13%;">nameandvalue:</td>
   <td class="formInput" style="width:20%;"><textarea name="nameandvalue" validate="{empty:false}">${newjsprelation.nameandvalue}</textarea></td>
   <td class="formTitle" nowrap="nowarp" style="width:13%;">name:</td>
   <td class="formInput" style="width:20%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="name" class="inputText" type="text" validate="{maxlength:100}" isflag="tableflag" lablename="name" value="${newjsprelation.name}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${newjsprelation.id}"/>
	</form>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 warehouse</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#warehouseForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#warehouseForm').submit();
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
						window.location.href = "${ctx}/warehouse/warehouse/warehouse/list.ht";
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
			    <c:when test="${not empty warehouseItem.id}">
			        <span class="tbar-label"><span></span>编辑warehouse</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加warehouse</span>
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
	<form id="warehouseForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">warehouse</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_name:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_name" lablename="w_name" class="inputText" validate="{maxlength:10}" isflag="tableflag" type="text" value="${warehouse.w_name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_street_1:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_street_1" lablename="w_street_1" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_street_1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_street_2:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_street_2" lablename="w_street_2" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_street_2}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_city:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_city" lablename="w_city" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_city}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_state:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_state" lablename="w_state" class="inputText" validate="{maxlength:2}" isflag="tableflag" type="text" value="${warehouse.w_state}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_zip:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_zip" lablename="w_zip" class="inputText" validate="{maxlength:9}" isflag="tableflag" type="text" value="${warehouse.w_zip}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_tax:</td>
   <td class="formInput" style="width:80%;"><input name="w_tax" showtype="validate='{number:true,maxIntLen:2,maxDecimalLen:2}'" type="text" value="${warehouse.w_tax}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_ytd:</td>
   <td class="formInput" style="width:80%;"><input name="w_ytd" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:2}'" type="text" value="${warehouse.w_ytd}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="w_id" value="${warehouse.w_id}"/>
	</form>
</body>
</html>

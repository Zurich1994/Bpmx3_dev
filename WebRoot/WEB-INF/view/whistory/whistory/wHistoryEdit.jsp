<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 w_history</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#wHistoryForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#wHistoryForm').submit();
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
						window.location.href = "${ctx}/whistory/whistory/wHistory/list.ht";
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
			    <c:when test="${not empty wHistoryItem.id}">
			        <span class="tbar-label"><span></span>编辑w_history</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加w_history</span>
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
	<form id="wHistoryForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">w_history</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_c_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_c_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${wHistory.h_c_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_c_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_c_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${wHistory.h_c_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${wHistory.h_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${wHistory.h_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_date:</td>
   <td class="formInput" style="width:80%;"><input name="h_date" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${wHistory.h_date}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_amount:</td>
   <td class="formInput" style="width:80%;"><input name="h_amount" showtype="validate='{number:true,maxIntLen:4,maxDecimalLen:2}'" type="text" value="${wHistory.h_amount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_data:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="h_data" lablename="h_data" class="inputText" validate="{maxlength:48}" isflag="tableflag" type="text" value="${wHistory.h_data}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="h_c_id" value="${wHistory.h_c_id}"/>
	</form>
</body>
</html>

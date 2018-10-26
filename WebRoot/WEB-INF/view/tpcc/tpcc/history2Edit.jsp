<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 history2</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#history2Form').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#history2Form').submit();
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
						window.location.href = "${ctx}/history/history/history2/list.ht";
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
			    <c:when test="${not empty history2Item.id}">
			        <span class="tbar-label"><span></span>编辑history2</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加history2</span>
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
	<form id="history2Form" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">history2</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:11,maxDecimalLen:0}" type="text" value="${history2.h_c_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_d_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_d_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:4,maxDecimalLen:0}" type="text" value="${history2.h_c_d_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_d_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_d_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_d_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_w_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_w_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_date:</td>
   <td style="width:15%;" class="formInput"><input name="h_date" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${history2.h_date}' pattern='yyyy-MM-dd HH:mm:ss'/>" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_amount:</td>
   <td style="width:15%;" class="formInput"><input name="h_amount" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_amount}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_data:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="h_data" lablename="h_data" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${history2.h_data}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_w_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_w_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_c_w_id}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${history2.id}"/>
	</form>
</body>
</html>

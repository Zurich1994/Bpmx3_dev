<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 customer</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#customerForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#customerForm').submit();
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
						window.location.href = "${ctx}/customer/customer/customer/list.ht";
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
			    <c:when test="${not empty customerItem.id}">
			        <span class="tbar-label"><span></span>编辑customer</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加customer</span>
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
	<form id="customerForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">customer</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="c_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${customer.c_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="c_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${customer.c_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_first:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_first" lablename="c_first" class="inputText" validate="{maxlength:16}" isflag="tableflag" type="text" value="${customer.c_first}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_middle:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_middle" lablename="c_middle" class="inputText" validate="{maxlength:2}" isflag="tableflag" type="text" value="${customer.c_middle}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_last:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_last" lablename="c_last" class="inputText" validate="{maxlength:16}" isflag="tableflag" type="text" value="${customer.c_last}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_street_1:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_street_1" lablename="c_street_1" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${customer.c_street_1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_street_2:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_street_2" lablename="c_street_2" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${customer.c_street_2}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_city:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_city" lablename="c_city" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${customer.c_city}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_state:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_state" lablename="c_state" class="inputText" validate="{maxlength:2}" isflag="tableflag" type="text" value="${customer.c_state}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_zip:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_zip" lablename="c_zip" class="inputText" validate="{maxlength:9}" isflag="tableflag" type="text" value="${customer.c_zip}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_phone:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_phone" lablename="c_phone" class="inputText" validate="{maxlength:16}" isflag="tableflag" type="text" value="${customer.c_phone}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_since:</td>
   <td class="formInput" style="width:80%;"><input name="c_since" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${customer.c_since}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_credit:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="c_credit" lablename="c_credit" class="inputText" validate="{maxlength:2}" isflag="tableflag" type="text" value="${customer.c_credit}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_credit_lim:</td>
   <td class="formInput" style="width:80%;"><input name="c_credit_lim" showtype="validate='{number:true,maxIntLen:19,maxDecimalLen:0}'" type="text" value="${customer.c_credit_lim}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_discount:</td>
   <td class="formInput" style="width:80%;"><input name="c_discount" showtype="validate='{number:true,maxIntLen:2,maxDecimalLen:2}'" type="text" value="${customer.c_discount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_balance:</td>
   <td class="formInput" style="width:80%;"><input name="c_balance" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:2}'" type="text" value="${customer.c_balance}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_ytd_payment:</td>
   <td class="formInput" style="width:80%;"><input name="c_ytd_payment" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:2}'" type="text" value="${customer.c_ytd_payment}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_payment_cnt:</td>
   <td class="formInput" style="width:80%;"><input name="c_payment_cnt" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${customer.c_payment_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_delivery_cnt:</td>
   <td class="formInput" style="width:80%;"><input name="c_delivery_cnt" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${customer.c_delivery_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">c_data:</td>
   <td class="formInput" style="width:80%;"><textarea name="c_data" validate="{empty:false}">${customer.c_data}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="c_id" value="${customer.c_id}"/>
	</form>
</body>
</html>

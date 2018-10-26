<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 用户ID</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
	function save() {var falg=prompt(" 验证码：3785");
				if(falg=="3785")
				{
					var url = __ctx + "/banking/banking/billPay/save.ht";
					var para = $('#billPayForm').serialize();
					$.post(url, para, showResult);
					
				}
				else
				{
					alert("验证码错误 ！");
				}
		}
	
		function showResult(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (!obj.isSuccess()) {
				$.ligerDialog.err('输入类型不对',"输入类型不对",obj.getMessage());
				return;
			} else {
				$.ligerDialog.success('支付 成功!','提示信息',function() {
					dialog.close();
				});
				var userid=document.getElementById("userid").value;
				window.location.href = "${ctx}/banking/banking/billPay/list.ht?name="+userid;
			}
		}
	    
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#billPayForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#billPayForm').submit();
				}
			});
		});
		
		var num1=parseString(num1);
		function calc(num1){
   				if(num1=="sa")
   				{
					var frm=$('#billPayForm').form();
					var options={};
			if(showResponse){
				options.success=showResponse;
			}
		
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#billPayForm').submit();
				}
			});
					alert('支付成功！');
				}
  		}
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						var userid=document.getElementById("userid").value;
						window.location.href = "${ctx}/banking/banking/billPay/list.ht?name="+userid;
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
			    <c:when test="${not empty billPayItem.id}">
			        <span class="tbar-label"><span></span>编辑用户ID</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户ID</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">  <!--<a class="link save" id="dataFormSave" href="javascript:;"><span></span>
			--><div class="toolBar">
				<div class="group"> <a  class="button" onclick="save()" ><span></span>快捷支付</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht?name=${userid}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="billPayForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="6" class="formHead">用户ID</td>
  </tr>
  <tr style="height:23px;"><!--${billPay.USERID}
   <td style="width:13%;" class="formTitle" nowrap="nowarp">用户名:</td>
   <td style="width:20%;" class="formInput">--><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input id="userid" name="USERID" lablename="用户名" class="inputText" validate="{maxlength:10}" isflag="tableflag" type="hidden" value="${userid}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">收款人:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PAYEEID" lablename="收款人" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${billPay.PAYEEID}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">日期:</td>
   <td style="width:20%;" class="formInput"><input name="DATA" class="Wdate" displaydate="0" validate="{empty:false,required:true}" type="text" value="<fmt:formatDate value='${billPay.DATA}' pattern='yyyy-MM-dd'/>" /></td>
    <td style="width:13%;" class="formTitle" nowrap="nowarp">付款金额:</td>
   <td style="width:20%;" class="formInput"><input name="PAYMENT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${billPay.PAYMENT}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${billPay.id}"/>
	</form>
</body>
</html>

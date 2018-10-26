<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 用户ID</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#quickpayForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#quickpayForm').submit();
				}
			});
		});
	
	var num1=parseString(num1);
		function calc(num1){
   				if(num1=="abcd")
   				{
   					
                 window.location.href = "${ctx}/banking/banking/billPay/list.ht?name=<%=request.getParameter("name")%>";		
				}
				else if(num1!=null&&num1!="abcd")
				    alert('密码错误');
  		}	 
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						window.location.href = "${ctx}/banking/banking/quickpay/list.ht";
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
			    <c:when test="${not empty quickpayItem.id}">
			        <span class="tbar-label"><br><span></span>编辑用户ID</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户ID</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><span></span> <input type="button" value="快捷支付" onclick="calc(prompt('请输入验证码：abcd'))"/></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="quickpayForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">用户ID</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名" class="inputText" validate="{maxlength:10}" isflag="tableflag" type="text" value="${quickpay.USERID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">收款人:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PAYEEID" lablename="收款人" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${quickpay.PAYEEID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">日期:</td>
   <td class="formInput" style="width:80%;"><input name="DATA" class="Wdate" displaydate="0" validate="{empty:false,required:true}" type="text" value="<fmt:formatDate value='${quickpay.DATA}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">付款金额:</td>
   <td class="formInput" style="width:80%;"><input name="PAYMENT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${quickpay.PAYMENT}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${quickpay.id}"/>
	</form>
</body>
</html>


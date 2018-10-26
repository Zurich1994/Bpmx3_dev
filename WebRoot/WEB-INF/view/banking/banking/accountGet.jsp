
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>账户信息表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
	function postMessage(){
		alert('确认转账？');
		$('#nowaccount1').attr('value',${account.ACCOUNTNO});
		$('#tansferform').submit();
	}
	
	
	function showResponse(responseText) {
	       alert("a");
			var obj = new com.hotent.form.ResultMessage(responseText);
			var name=${account.ACCOUNTNO} ;
			var a=obj.getMessage();
			alert(name);
			alert(a);
			if(a=="1"){
			
			url="${ctx}/banking/banking/account/list.ht?name="+name;
			
     		 window.location.href = url;
		}
	}
</script>
</head>
<body>
<form id="tansferform" action="transfer.ht" method="post" name="tansferform">
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">账户信息表详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
  <tr>
   <td colspan="2" class="formHead">银行转账</td>
  </tr>

   <td style="width:20%;" class="formTitle" align="left" nowrap="nowarp">当前用户:</td>
   <td class="formInput"  style="width:80%;"><span id="nowaccount" name="nowaccount"  style="display:inline-block;" isflag="tableflag">${account.ACCOUNTNO}</span></td>
  <tr>
   <td style="width:20%;" class="formTitle" align="left" nowrap="nowarp">当前余额:</td>
   <td class="formInput" style="width:80%;">${account.BALANCE}</td>
  </tr>
   <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp">转出帐户：<input id="toaccount" type="inputText" name="toaccount" /><br/></td>
 
   <td style="width:20%;" class="formTitle" align="left" nowrap="nowarp">转出金额：<input id="amount" type="inputText" name="amount"  />
   
   <input type="button" onClick="postMessage()" value="确认转账"/>
   <input type="hidden" id="nowaccount1" name="nowaccount1" />"
   <input type="hidden" id="nowaccount2" name="nowaccount2" value="${account.USERID}"/>
   </td>
  </tr>
</table>
</form>
</body>
</html>


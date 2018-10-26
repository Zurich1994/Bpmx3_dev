
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>用户ID明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">用户ID详细信息</span>
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
 <tbody>
  <tr>
   <td colspan="6" class="formHead">用户ID</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">用户名:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${billPay.USERID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">收款人:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${billPay.PAYEEID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">日期:</td>
   <td style="width:20%;" class="formInput"><fmt:formatDate value='${billPay.DATA}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">付款金额:</td>
   <td style="width:20%;" class="formInput">${billPay.PAYMENT}</td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
</body>
</html>


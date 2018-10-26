<%@page language="java" pageEncoding="UTF-8"%><%@page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>订单查询界面</title>
<%@include file="/commons/include/get.jsp"%>
<style type="text/css">
	.block{
		display:block;
		margin:10px 0;
	}
</style>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	function find(){
		var url;
		id = $("input[name='customNumber']").val();
		customStoreNumber = $("input[name='customStoreNumber']").val();
		customAreaNumber = $("input[name='customAreaNumber']").val();
		if(document.getElementsByName("radiobutton")[1].checked)
			url = __ctx+'/tpcc/tpcc/orders/search1.ht?id=' + id + "&customStoreNumber=" + customStoreNumber + "&customAreaNumber=" + customAreaNumber
			+ "&style=" + "name";
		else
			url = __ctx+'/tpcc/tpcc/orders/search1.ht?id=' + id + "&customStoreNumber=" + customStoreNumber + "&customAreaNumber=" + customAreaNumber
			+ "&style=" + "number";
		location.href=url;
	}
</script>
</head>
<body>
	<div class="panel">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">客户仓库号:</th>
				<td>
					<input type="text" name="customStoreNumber"/>
				</td>
				<th width="20%">客户地区号:</th>
				<td>
					<input type="text" name="customAreaNumber"/>
				</td>
				<th width="20%">客户号或客户姓氏:</th>
				<td>
					<input type="text" name="customNumber"/>
				</td>
			</tr>
			<tr>
				<th width="20%">选择客户查询方式:</th>
				<td>
					<input type="radio" name="radiobutton" value="customNumber">客户号  
					<input type="radio" name="radiobutton" value="customName">客户姓氏
				</td>
				<td>
				<button onclick="find()">查询</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


<%@page language="java" pageEncoding="UTF-8"%><%@page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<!--<title>查询订单界面1</title>-->
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
		var customStyle;
		if(document.getElementsByName("radiobutton")[0].checked=="checked"){
			customStyle = "customNumber";
		}
		else
			customStyle = "customName";
		id = $("input[name='customNumber']").val()
		var url = __ctx+'/tpcc/tpcc/orders/search1.ht?id=' + id + "&customStyle=" + customStyle;
		$.post(url);
	}
</script>
</head>
<body>
	<div class="panel">
		<form action="searchorder.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">客户号:</th>
					<td>
						<input type="text" name="customNumber" value="${customerList.c_id}"/>
					</td>
					<th width="20%">客户剩余金额:</th>
					<td>
						<input type="text" name="customRemainNumber" value="${customerList.c_balance}"/>
					</td>
					<th width="20%">客户名:</th>
					<td>
						<input type="text" name="customFirstName" value="${customerList.c_first}"/>
					</td>
				</tr>
				<tr>
					<th width="20%">客户middle名:</th>
					<td>
						<input type="text" name="customMiddleName"  value="${customerList.c_middle}"/>
					</td>
					<th width="20%">客户姓氏:</th>
					<td>
						<input type="text" name="customLastName"  value="${customerList.c_last}"/>
					</td>
					<th width="20%">客户仓库号:</th>
					<td>
						<input type="text" name="customStoreNumber"  value="${customer2.c_w_id}"/>
					</td>
					<th width="20%">客户地区号:</th>
					<td>
						<input type="text" name="customAreaNumber"  value="${customer2.c_d_id}"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="查询最近订单">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>


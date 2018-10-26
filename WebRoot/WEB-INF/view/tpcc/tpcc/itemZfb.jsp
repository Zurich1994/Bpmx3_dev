<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>支付界面B</title>
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

	

</script>
</head>
<body>

	<div class="panel">
		<form id="form1" action="zfc.ht" >
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">客户号:</th>
					<td>
						<input type="text" id="customNumber1" name="customNumber" value="${customerNumber}"/>
						
					</td>
					<th width="20%">客户仓库号:</th>
					<td>
						<input type="text" id="customStoreNumber1" name="customStoreNumber" value="${customerStore}"/>
						
					</td>
					<th width="20%">客户地区号:</th>
					<td>
						<input type="text" id="customAreaNumber1" name="customAreaNumber" value="${customerArea}"/>
						
					</td>
				</tr>
				<tr>
					<th width="20%">库存仓库号:</th>
					<td>
						<input type="text" name="stockStoreNumber" id="stockStoreNumber" value=""/>
					</td>
					<th width="20%">库存地区号:</th>
					<td>
						<input type="text" name="stockAreaNumber" id="stockAreaNumber" value=""/>
					</td>
					<th width="20%">订单金额:</th>
					<td>
						<input type="text" name="orderValue" id="orderValue" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="支付">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>


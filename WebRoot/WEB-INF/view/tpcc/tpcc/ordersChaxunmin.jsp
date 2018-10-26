<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>库存查询</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<form id="districtList" action="/mas/tpcc/tpcc/newOrders/list.ht" method="post">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					
					<th width="20%">最小订单号：</th>
					<td>
  <input type="text" name="no_o_id" value=${orders3.no_o_id} />
					</td>
				   
				</tr>
				<tr>
					<td>
						<input type="submit" value="查询新订单">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>



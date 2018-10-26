<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>订单查询界面2</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<form id="orders1" action="cxddmx.ht" method="post">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					
					<th width="20%">订单号：</th>
					<td>
  						<input type="text" name="o_id" value=${orders1.o_id} />
					</td>
				    <th width="20%">日期和时间：</th>
					<td>
  				        <input type="text" name="o_entry_d" value=${o_entry_d} />
					</td><th width="20%">发货号：</th>
					<td>
  					    <input type="text" name="o_carrier_id" value=${orders1.o_carrier_id} />
					</td>
					<th width="20%">客户仓库号:</th>
					<td>
						<input type="text" name="customStoreNumber"  value="${customer3.c_w_id}"/>
					</td>
					<th width="20%">客户地区号:</th>
					<td>
						<input type="text" name="customAreaNumber"  value="${customer3.c_d_id}"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="查询订单明细">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>



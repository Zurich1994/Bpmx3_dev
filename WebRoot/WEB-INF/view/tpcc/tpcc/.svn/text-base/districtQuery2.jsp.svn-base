<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>库存查询</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<form id="districtList" action="itemno.ht" method="post">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					
					<th width="20%">可用订单号：</th>
					<td>
  <input type="text" name="d_next_o_id" value=${district.d_next_o_id} />
					</td>
				    <th width="20%">仓库号：</th>
					<td>
  <input type="text" name="w_id" value=${district0.d_w_id} />
					</td><th width="20%">地区号：</th>
					<td>
  <input type="text" name="d_id" value=${district0.d_id} />
					</td>
					 <th width="20%">库存最低值：</th>
					<td>
  <input type="text" name="d_level" id="d_level"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="查询最近20笔订单">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>



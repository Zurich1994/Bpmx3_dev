<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>customer管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
function funs()
{
	
}
</script>
</head>
<%String c_d_id = request.getParameter("c_d_id"); 
String c_w_id = request.getParameter("c_w_id"); 
String c_id = request.getParameter("c_id"); 

String d_next_o_id = request.getParameter("d_next_o_id");

%>
<body>
	<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">customer管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="Query.ht"><span></span>返回</a></div>
					<div class="group"><a class="link save" id="dataFormSave" href="${ctx}/tpcc/tpcc/orderyan/edit.ht?c_d_id=<%=c_d_id%>&c_w_id=<%=c_w_id %>&c_id=<%=c_id %>&d_next_o_id=${district.d_next_o_id}&d_tax=${district.d_tax}"><span></span>生成订单</a></div>
				</div>	
				
			</div>
		</div>
		
		<form id="customerList" action="orderno.ht" method="post">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">客户姓氏：</th>
					<td>	
  <input type="text" name="c_last" value=${customer.c_last} />
					</td>
					<th width="20%">客户号：</th>
					<td>
  <input type="text" name="c_id" value=${customer.c_id} />
					</td>
					<th width="20%">客户所在地区号：</th>
					<td>
  <input type="text" name="c_d_id" value=${customer.c_d_id} />
					</td>
				
					<th width="20%">客户所在仓库号：</th>
					<td>		
  <input type="text" name="c_w_id" value=${customer.c_w_id} />
					</td>
					<th width="20%">客户折扣：</th>
					<td>
  <input type="text" name="c_discount" value=${customer.c_discount} />
					</td>
					</tr>
				<tr>
					<th width="20%">客户信用评价：</th>
					<td>
  <input type="text" name="c_credit" value=${customer.c_credit} /> 
					</td>
					<th width="20%">仓库税率：</th>
					<td>
  <input type="text" name="w_tax" value=${customer.w_tax} />
					</td>
					<th width="20%">销售税率：</th>
					<td> 
  <input type="text" name="d_tax" value=${district.d_tax} />
					</td><th width="20%">订单号：</th>
					<td>
  <input type="text" name="d_next_o_id" value=${district.d_next_o_id} />
					</td>
					
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>



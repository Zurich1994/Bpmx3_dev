
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>orders明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">orders详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
						<a class="link back" href="edit.ht?id=${orders.o_id}"><span></span>更新订单</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">orders</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">地区号:</td>
   <td class="formInput" style="width:80%;">${orders.o_d_id}</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">仓库号:</td>
   <td class="formInput" style="width:80%;">${orders.o_w_id}</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户号:</td>
   <td class="formInput" style="width:80%;">${orders.o_c_id}</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">日期和时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${orders.o_entry_d}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_carrier_id:</td>
   <td class="formInput" style="width:80%;">${orders.o_carrier_id}</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">定单行数量:</td>
   <td class="formInput" style="width:80%;">${orders.o_ol_cnt}</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">商品地:</td>
   <td class="formInput" style="width:80%;">${orders.o_all_local}</td>
  </tr>
 </tbody>
</table>
</body>
</html>


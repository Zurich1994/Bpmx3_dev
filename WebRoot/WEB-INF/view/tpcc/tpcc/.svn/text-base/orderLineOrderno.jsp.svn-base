<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

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
	function doAjax(){
		var i_id = document.getElementById("i_id").value;
		var s_w_id = document.getElementById("s_w_id").value;
		var amount = document.getElementById("amount").value;
		var url = __ctx+'/orderline/orderline/orderLine/mingxi.ht',json = [];
		
		json.push({"c_id":${customer.c_id},"c_d_id":${customer.c_d_id},"c_w_id":${customer.c_w_id},"c_last":${customer.c_last},"c_credit":${customer.c_credit},
		"c_discount":${customer.c_discount},"w_tax":${customer.w_tax},"d_tax":${district2.d_tax},"d_next_o_id":${district2.d_next_o_id},"i_id":i_id,"s_w_id":s_w_id,"amount":amount});
		json = JSON2.stringify(json);
		$.post(url,{json:json},function(d)
		{
				$.ligerDialog.closeWaitting();
				var data = $.parseJSON(d);
			  var newTableRow = mytable.insertRow(mytable.rows.length); 
			newTableRow.insertCell(0).innerText =document.getElementById("i_id").value; 
			newTableRow.insertCell(1).innerText =data.item.I_NAME; 
			newTableRow.insertCell(2).innerText = data.item.I_PRICE;  
			newTableRow.insertCell(3).innerText = document.getElementById("amount").value;  
			newTableRow.insertCell(4).innerText = data.orderLine.oLAMOUNT;
		});
	}

		
	function order(){
	location.href="order.ht?d_next_o_id="+${district2.d_next_o_id}+"&c_d_id="+${customer.c_d_id}+"&c_w_id="+${customer.c_w_id}+"&c_id="+${customer.c_id};
	
	
	}	
	
</script>
</head>
  <body>
  <table id = "mytable" class="table-detail" cellpadding="0" cellspacing="0" border="0"> 
<tr><td>商品号</td><td>商品名</td><td>单价</td><td>数量</td><td>金额</td></tr> 
</table> 
<input type="button" name="Submit" value="生成订单 " onclick="order()" />

  
  
  
  
  <h1>请输入</h1> 
  <div class="panel" >
  <form method="post">
  <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
  <tr>
<th width="20%">商品号:</th>
<td><input id="i_id" name = "i_id" type = "text" ><br/> </td>
<th width="20%">商品所在仓库号:</th>
 <td><input id="s_w_id" name = "s_w_id" type = "text" ><br/></td>
 <th width="20%">数量:</th>
 <td><input id="amount" name = "amount" type = "text" ><br/> </td>
<td><input type = "button" value ="生成明细" onclick ="doAjax()" /></td>
</table>
 </form>
 </div>
</body> 
</html>

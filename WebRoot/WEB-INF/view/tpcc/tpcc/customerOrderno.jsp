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
		
		var data;
		$.ajax({
	        url: __ctx + "/orderline/orderline/orderLine/mingxi.ht",
	        async: false,//改为同步方式
	        type: "POST",
	        data: {"c_id":${customer.c_id},"c_d_id":${customer.c_d_id},"c_w_id":${customer.c_w_id},"c_last":"${customer.c_last}","c_credit":"${customer.c_credit}",
		"c_discount":${customer.c_discount},"w_tax":${customer.w_tax},"d_tax":${district2.d_tax},"d_next_o_id":${district2.d_next_o_id},"i_id":i_id,"s_w_id":s_w_id,"amount":amount},
	        dataType: "json",
	        success: function (data) {
	            var data = $.parseJSON(data);
	            $("#mytable").append("<tr><td>"+document.getElementById("i_id").value+"</td><td>"+data.item.i_name+"</td><td>"
	            	+data.item.i_price+"</td><td>"+document.getElementById("amount").value+"</td><td>"+data.orderLine.ol_amount+"</td></tr>");
	        },
	        error:  function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status+"  "+XMLHttpRequest.readyState+"  "+textStatus);
            },
            complete: function(XMLHttpRequest, textStatus) {
                        this; // 调用本次AJAX请求时传递的options参数
            }
    	});
	};

		
	function order(){
	location.href="order.ht?d_next_o_id="+${district2.d_next_o_id}+"&c_d_id="+${customer.c_d_id}+"&c_w_id="+${customer.c_w_id}+"&c_id="+${customer.c_id};
	};	
	
</script>
</head>
  <body>
  <form id="customerList" action="orderno.ht" method="post">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>					
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
					
					<th width="20%">销售税率：</th>
					<td> 
  <input type="text" name="d_tax" value=${district.d_tax} />
					</td><th width="20%">订单号：</th>
					<td>
  <input type="text" name="d_next_o_id" value=${district.d_next_o_id} />
					</td>
					
				</tr>
				<tr>
					<td>
						<input type="submit" value="生成订单">
					</td>
				</tr>
			</table>
		</form>
<input type="button" name="Submit" value="生成订单 " onclick="order()" />

  
  
  
  
  <h1>请输入</h1> 
  <div class="panel" >
  <form method="post">
  <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
  <tr>
<th width="20%">商品号:</th>
<td><input id="i_id" name = "i_id" type = "text" ><br/> </td>
<th width="20%">商品所在仓库号:</th>
 <td><input id="s_w_id" name = "s_w_id" type = "text"><br/></td>
 <th width="20%">数量:</th>
 <td><input id="amount" name = "amount" type = "text" ><br/> </td>
<td><input type = "button" value ="生成明细" onclick ="doAjax()" /></td>
</table>
 </form>
 </div>
</body> 
</html>

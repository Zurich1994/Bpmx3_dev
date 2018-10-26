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
	function find(){
	
		var c_id = document.getElementById("c_id").value;
	    var c_d_id = document.getElementById("c_d_id").value;
	    var c_w_id = document.getElementById("c_w_id").value;
	    if(c_id=="")
	    {
	    	alert("客户号不能为空");
   			return false;
	    }else if(c_d_id=="")
	    {
	    	alert("客户地区号不能为空");
   			return false;
	    }else if(c_w_id=="")
	    {
	    	alert("客户仓库号不能为空");
   			return false;
	    }else{
		var url = __ctx + "/tpcc/tpcc/customer/query2.ht?c_id="+c_id+"&c_d_id="+c_d_id+"&c_w_id="+c_w_id;
		location.href=url;
		}
	}
</script>
</head>
<body>
	<div class="panel">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">客户号:</th>
				<td>
					<input type="text" name="c_id" id="c_id" />
				</td>
				<th width="20%">客户地区号:</th>
				<td>
					<input type="text" name="c_d_id" id="c_d_id" />
				</td>
				<th width="20%">客户仓库号:</th>
				<td>
					<input type="text" name="c_w_id" id="c_w_id"/>
				</td>
			</tr>
			<tr>
				<td>
				<button onclick="find()">查询</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


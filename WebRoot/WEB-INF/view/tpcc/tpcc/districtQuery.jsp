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
		var w_id = document.getElementById("w_id").value;
	    var d_id = document.getElementById("d_id").value;
		var url = __ctx + "/tpcc/tpcc/district/query2.ht?w_id="+w_id+"&d_id="+d_id;
		location.href=url;
	}
</script>
</head>
<body>
	<div class="panel">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">仓库号:</th>
				<td>
					<input type="text" name="w_id" id="w_id" />
				</td>
				<th width="20%">地区号:</th>
				<td>
					<input type="text" name="d_id" id="d_id" />
				</td>
				<td>
				<button onclick="find()">查询</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>



<%--
	time:2015-05-18 16:16:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>取卡规则明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">取卡规则详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">编码:</th>
				<td>${atsCardRule.code}</td>
			</tr>
			<tr>
				<th width="20%">名称:</th>
				<td>${atsCardRule.name}</td>
			</tr>
			<tr>
				<th width="20%">上班取卡提前(小时):</th>
				<td>${atsCardRule.startNum}</td>
			</tr>
			<tr>
				<th width="20%">下班取卡延后(小时):</th>
				<td>${atsCardRule.endNum}</td>
			</tr>
			<tr>
				<th width="20%">最短取卡间隔(分钟）:</th>
				<td>${atsCardRule.timeInterval}</td>
			</tr>
			<tr>
				<th width="20%">适用段次:</th>
				<td>${atsCardRule.segmentNum}</td>
			</tr>
			<tr>
				<th width="20%">是否默认:</th>
				<td>${atsCardRule.isDefault}</td>
			</tr>
			<tr>
				<th width="20%">第一次上班取卡范围开始时数:</th>
				<td>${atsCardRule.segBefFirStartNum}</td>
			</tr>
			<tr>
				<th width="20%">第一次上班取卡范围结束时数:</th>
				<td>${atsCardRule.segBefFirEndNum}</td>
			</tr>
			<tr>
				<th width="20%">第一次上班取卡方式:</th>
				<td>${atsCardRule.segBefFirTakeCardType}</td>
			</tr>
			<tr>
				<th width="20%">第一次下班取卡范围开始时数:</th>
				<td>${atsCardRule.segAftFirStartNum}</td>
			</tr>
			<tr>
				<th width="20%">第一次下班取卡范围结束时数:</th>
				<td>${atsCardRule.segAftFirEndNum}</td>
			</tr>
			<tr>
				<th width="20%">第一次下班取卡方式:</th>
				<td>${atsCardRule.segAftFirTakeCardType}</td>
			</tr>
			<tr>
				<th width="20%">第二次上班取卡范围开始时数:</th>
				<td>${atsCardRule.segBefSecStartNum}</td>
			</tr>
			<tr>
				<th width="20%">第二次上班取卡范围结束时数:</th>
				<td>${atsCardRule.segBefSecEndNum}</td>
			</tr>
			<tr>
				<th width="20%">第二次上班取卡方式:</th>
				<td>${atsCardRule.segBefSecTakeCardType}</td>
			</tr>
			<tr>
				<th width="20%">第二次下班取卡范围开始时数:</th>
				<td>${atsCardRule.segAftSecStartNum}</td>
			</tr>
			<tr>
				<th width="20%">第二次下班取卡范围结束时数:</th>
				<td>${atsCardRule.segAftSecEndNum}</td>
			</tr>
			<tr>
				<th width="20%">第二次下班取卡方式:</th>
				<td>${atsCardRule.segAftSecTakeCardType}</td>
			</tr>
			<tr>
				<th width="20%">第一段间分配类型:</th>
				<td>${atsCardRule.segFirAssignType}</td>
			</tr>
			<tr>
				<th width="20%">第一段间分配段次:</th>
				<td>${atsCardRule.segFirAssignSegment}</td>
			</tr>
			<tr>
				<th width="20%">第二段间分配类型:</th>
				<td>${atsCardRule.segSecAssignType}</td>
			</tr>
			<tr>
				<th width="20%">第二段间分配段次:</th>
				<td>${atsCardRule.segSecAssignSegment}</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>


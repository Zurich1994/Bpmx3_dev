<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备监控指标表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	function openQuotaDialog() {
		CommonDialog("jkzbxz",function(data){
			var param = "";
			var i = 0;
			for(i=0;i<data.length-1;i++) {
				param = param + data[i].ID + ",";
			}
			param = param + data[i].ID
			$.post("${ctx}/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/saveMulti.ht",{deviceid:${deviceid},quotaids:param},function(result){window.location.href = "${ctx}/monitorQuota/monitorQuotaPac/monitorQuota/list2.ht?id=${deviceid}";});
		},"");						
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备监控指标表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
			<!--      	<div class="group"><a class="link add" href="${ctx}/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/edit.ht?deviceid=${deviceid}" ><span></span>添加</a></div>  -->
			 	<div class="group"><a class="link add" href="javascript:openQuotaDialog()"><span></span>添加</a></div>   
					<div class="l-bar-separator"></div>
			<!--  		<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>  -->
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">指标名称:</span><input type="text" name="Q_name_S"  class="inputText" />
						<span class="label">指标单位:</span><input type="text" name="Q_unit_S"  class="inputText" />
						<span class="label">监控类型:</span><input type="text" name="Q_monitorType_S"  class="inputText" />
						<span class="label">对象标识符:</span><input type="text" name="Q_OID_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="monitorQuotaList" id="monitorQuotaItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${monitorQuotaItem.id}">
				</display:column>
				<display:column property="name" title="指标名称" sortable="true" sortName="F_NAME"></display:column>
				<display:column property="unit" title="指标单位" sortable="true" sortName="F_UNIT"></display:column>
				<display:column property="monitorType" title="监控类型" sortable="true" sortName="F_MONITORTYPE"></display:column>
				<display:column property="OID" title="对象标识符" sortable="true" sortName="F_OID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="${ctx}/deviceQuotaRel/deviceQuotaRelPac/deviceQuotaRel/del1.ht?deviceid=${deviceid}&mqid=${monitorQuotaItem.id}" class="link del"><span></span>删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="monitorQuotaItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel --><div><br></div>
</body>
</html>



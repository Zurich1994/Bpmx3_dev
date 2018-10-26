<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>设备基本信息表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设备基本信息表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">设备编号:</span><input type="text" name="Q_dev_id_S"  class="inputText" />
						<span class="label">机房编号:</span><input type="text" name="Q_room_ID_S"  class="inputText" />
						<span class="label">设备类型:</span><input type="text" name="Q_dev_type_S"  class="inputText" />
						<span class="label">设备品牌:</span><input type="text" name="Q_dev_brand_S"  class="inputText" />
						<span class="label">设备型号:</span><input type="text" name="Q_dev_model_S"  class="inputText" />
						<br/>
						<span class="label">设备使用状态:</span><input type="text" name="Q_state_S"  class="inputText" />
						<span class="label">设备出厂日期 从:</span> <input  name="Q_beginexFactory_Date_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endexFactory_Date_DG" class="inputText date" />
						<span class="label">设备投入使用日期 从:</span> <input  name="Q_beginusing_Date_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endusing_Date_DG" class="inputText date" />
						<br/>
						<span class="label">项目名称:</span><input type="text" name="Q_project_Name_S"  class="inputText" />	
						<span class="label">设备报废日期 从:</span> <input  name="Q_beginretirement_Date_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endretirement_Date_DG" class="inputText date" />
						<span class="label">录入时间 从:</span> <input  name="Q_begininsert_Time_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endinsert_Time_DG" class="inputText date" />
						<br/>
						<span class="label">流程定义ID:</span><input type="text" name="Q_actdefid_S"  class="inputText" />
						<span class="label">流程节点ID:</span><input type="text" name="Q_nodeid_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="deviceInfomationList" id="deviceInfomationItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${deviceInfomationItem.id}">
				</display:column>
				<display:column property="dev_id" title="设备编号" sortable="true" sortName="F_DEV_ID"></display:column>
				<display:column property="dev_Name" title="设备名称" sortable="true" sortName="F_DEV_NAME"></display:column>
				<display:column property="room_ID" title="机房编号" sortable="true" sortName="F_ROOM_ID"></display:column>
				<display:column property="cabinet_num" title="机柜编号" sortable="true" sortName="F_CABINET_NUM"></display:column>
				<display:column property="sbgs" title="设备归属" sortable="true" sortName="F_SBGS"></display:column>
				<display:column property="dev_type" title="设备类型" sortable="true" sortName="F_DEV_TYPE"></display:column>
				<display:column property="dev_brand" title="设备品牌" sortable="true" sortName="F_DEV_BRAND"></display:column>
				<display:column property="dev_model" title="设备型号" sortable="true" sortName="F_DEV_MODEL"></display:column>
				<display:column property="dev_sequence" title="设备序列号" sortable="true" sortName="F_DEV_SEQUENCE"></display:column>
				<display:column property="dev_config" title="设备配置" sortable="true" sortName="F_DEV_CONFIG"></display:column>
				<display:column property="fun_Info" title="功能描述" sortable="true" sortName="F_FUN_INFO"></display:column>
				<display:column property="state" title="设备使用状态" sortable="true" sortName="F_STATE"></display:column>
				<display:column  title="设备出厂日期" sortable="true" sortName="F_EXFACTORY_DATE">
					<fmt:formatDate value="${deviceInfomationItem.exFactory_Date}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="设备投入使用日期" sortable="true" sortName="F_USING_DATE">
					<fmt:formatDate value="${deviceInfomationItem.using_Date}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="设备报废日期" sortable="true" sortName="F_RETIREMENT_DATE">
					<fmt:formatDate value="${deviceInfomationItem.retirement_Date}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="responsible_Person" title="使用人" sortable="true" sortName="F_RESPONSIBLE_PERSON"></display:column>
				<display:column property="contact" title="使用人联系方式" sortable="true" sortName="F_CONTACT"></display:column>
				<display:column property="project_Name" title="项目名称" sortable="true" sortName="F_PROJECT_NAME"></display:column>
				<display:column  title="录入时间" sortable="true" sortName="F_INSERT_TIME">
					<fmt:formatDate value="${deviceInfomationItem.insert_Time}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="remark" title="备注" sortable="true" sortName="F_REMARK"></display:column>
				<display:column property="actdefid" title="流程定义ID" sortable="true" sortName="F_ACTDEFID"></display:column>
				<display:column property="nodeid" title="流程节点ID" sortable="true" sortName="F_NODEID"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${deviceInfomationItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${deviceInfomationItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${deviceInfomationItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="deviceInfomationItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>



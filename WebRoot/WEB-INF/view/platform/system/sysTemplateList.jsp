<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>模板管理列表</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"  charset="UTF-8"></script>
<script type="text/javascript">
//导出自定义表
function ExportXml(){
	var ids = "";
	$("input[type='checkbox'][class='pk']:checked").each(function(){ 
			var obj=$(this);
			ids+= obj.val()+",";
	
	});
	if(ids==''){
		$.ligerDialog.warn("请选择一条记录进行导出！");
		return;
	}else{
		ids=ids.substring(0,ids.length-1);
	}
	var url = __ctx + "/platform/system/sysTemplate/exportXml.ht";
	$("#exportIds").val(ids);
	$("#exportXmlForm").submit();
};
//导入自定义表
function ImportXml(){
	var url= __ctx + "/platform/system/sysTemplate/import.ht";
	url=url.getNewUrl();
	var winArgs="dialogWidth:600px;dialogHeight:400px;help:0;status:1;scroll:1;center:1";
	/* var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn){
		window.location.reload(true);
	} */
	
	/*KILLDIALOG*/
	DialogUtil.open({
		height:400,
		width: 600,
		title : '导入自定义表',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn){
				window.location.reload(true);
			}
		}
	});
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">模板管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link add" href="edit.ht"><span></span>添加</a>
					</div>
					<div class="group">
						<a class="link download"  href="javascript:;" onclick="ExportXml()"><span></span>导出</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link upload"  href="javascript:;" onclick="ImportXml()"><span></span>导入</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
					    <a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
					<li>
						<span class="label">模版名称:</span>
						<input type="text" name="Q_name_SL" class="inputText" value="${param['Q_name_SL']}"/>
					</li>
					<li>
						<span class="label">模版用途:</span>
						<select name="Q_useType_L">
							<option value="">------全部------</option>
							<option value="1"<c:if test="${param['Q_useType_L'] eq 1}"> selected="selected"</c:if>>终止提醒</option>
							<option value="2"<c:if test="${param['Q_useType_L'] eq 2}"> selected="selected"</c:if>>催办提醒</option>
							<option value="3"<c:if test="${param['Q_useType_L'] eq 3}"> selected="selected"</c:if>>审批提醒</option>
							<option value="4"<c:if test="${param['Q_useType_L'] eq 4}"> selected="selected"</c:if>>撤销提醒</option>
							<option value="5"<c:if test="${param['Q_useType_L'] eq 5}"> selected="selected"</c:if>>取消转办</option>
							<option value="6"<c:if test="${param['Q_useType_L'] eq 6}"> selected="selected"</c:if>>沟通提醒</option>
							<option value="7"<c:if test="${param['Q_useType_L'] eq 7}"> selected="selected"</c:if>>归档提醒</option>
							<option value="8"<c:if test="${param['Q_useType_L'] eq 8}"> selected="selected"</c:if>>转办提醒</option>
							<option value="9"<c:if test="${param['Q_useType_L'] eq 9}"> selected="selected"</c:if>>退回提醒</option>
							<option value="10"<c:if test="${param['Q_useType_L'] eq 10}"> selected="selected"</c:if>>沟通反馈</option>
							<option value="11"<c:if test="${param['Q_useType_L'] eq 11}"> selected="selected"</c:if>>取消代理</option>
							<option value="12"<c:if test="${param['Q_useType_L'] eq 12}"> selected="selected"</c:if>>抄送提醒</option>
							<option value="19"<c:if test="${param['Q_useType_L'] eq 19}"> selected="selected"</c:if>>逾期提醒</option>
							<option value="22"<c:if test="${param['Q_useType_L'] eq 22}"> selected="selected"</c:if>>代理提醒</option>
							<option value="23"<c:if test="${param['Q_useType_L'] eq 23}"> selected="selected"</c:if>>消息转发</option>
							<option value="24"<c:if test="${param['Q_useType_L'] eq 24}"> selected="selected"</c:if>>重启任务</option>
							<option value="25"<c:if test="${param['Q_useType_L'] eq 25}"> selected="selected"</c:if>>通知任务所属人(代理)</option>
							<option value="26"<c:if test="${param['Q_useType_L'] eq 26}"> selected="selected"</c:if>>加签提醒</option>
							<option value="27"<c:if test="${param['Q_useType_L'] eq 27}"> selected="selected"</c:if>>加签反馈</option>
							<option value="28"<c:if test="${param['Q_useType_L'] eq 28}"> selected="selected"</c:if>>取消流转</option>
							<option value="29"<c:if test="${param['Q_useType_L'] eq 29}"> selected="selected"</c:if>>补签提醒</option>
						</select>
					</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			<display:table name="sysTemplateList" id="sysTemplateItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="templateId" value="${sysTemplateItem.templateId}">
				</display:column>

				<display:column property="name" title="模版名称" sortable="true" sortName="name"></display:column>
					
				<display:column titleKey="模版用途">
					<c:choose>
						<c:when test="${sysTemplateItem.useType eq 1}">终止提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 2}">催办提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 3}">审批提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 4}">撤销提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 5}">取消转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 6}">沟通提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 7}">归档提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 8}">转办提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 9}">退回提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 10}">沟通反馈</c:when>
						<c:when test="${sysTemplateItem.useType eq 11}">取消代理</c:when>
						<c:when test="${sysTemplateItem.useType eq 12}">抄送提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 13}">流程节点无人员</c:when>
						<c:when test="${sysTemplateItem.useType eq 14}">跟进事项预警</c:when>
						<c:when test="${sysTemplateItem.useType eq 15}">跟进事项到期</c:when>
						<c:when test="${sysTemplateItem.useType eq 16}">跟进事项完成</c:when>
						<c:when test="${sysTemplateItem.useType eq 17}">跟进事项通知</c:when>
						<c:when test="${sysTemplateItem.useType eq 18}">跟进事项已评价知会</c:when>
						<c:when test="${sysTemplateItem.useType eq 19}">逾期提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 22}">代理提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 23}">消息转发</c:when>
						<c:when test="${sysTemplateItem.useType eq 24}">重启任务</c:when>
						<c:when test="${sysTemplateItem.useType eq 25}">通知任务所属人(代理)</c:when>
						<c:when test="${sysTemplateItem.useType eq 26}">加签提醒</c:when>
						<c:when test="${sysTemplateItem.useType eq 27}">加签反馈</c:when>
						<c:when test="${sysTemplateItem.useType eq 28}">取消流转</c:when>
						<c:when test="${sysTemplateItem.useType eq 29}">补签提醒</c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="是否默认">
					<c:choose>
						<c:when test="${sysTemplateItem.isDefault==1}"><span class="green">是</span></c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作" media="html" style="width:50px;text-align:center" class="rowOps">	
					<c:choose>
						<c:when test="${sysTemplateItem.isDefault==1}">
							<a class="link setting disabled">设置默认</a>
						</c:when>
						<c:otherwise>
							<a class="link setting" href="setDefault.ht?templateId=${sysTemplateItem.templateId}">设置默认</a>
						</c:otherwise>
					</c:choose>
					<a href="del.ht?templateId=${sysTemplateItem.templateId}"  class="link del">删除</a>
					<a href="edit.ht?templateId=${sysTemplateItem.templateId}" class="link edit">编辑</a>
					<a href="get.ht?templateId=${sysTemplateItem.templateId}" class="link detail">明细  </a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysTemplateItem" />
		</div>
		<div style="display: none">
			<form  id="exportXmlForm" target="download" action="exportXml.ht">
				<input id="exportIds" name="ids">
			</form>
			<iframe id="download" name="download" height="0px" width="0px"></iframe>
		</div>
	</div>
</body>
</html>



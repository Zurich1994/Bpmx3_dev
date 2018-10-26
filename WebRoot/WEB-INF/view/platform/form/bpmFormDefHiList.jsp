<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
function preView(hisId){
	var $selId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
	if(hisId == '' && $selId.length == 0){
		$.ligerDialog.warn("请选择记录！");
		return ;
	}
	if(hisId == '' && $selId.length > 1){
		$.ligerDialog.warn("只能选择一条记录！");
		return ;
	}
	if(hisId == null || hisId == ''){
		hisId = $($selId[0]).val();
	}
	// 显示历史记录
	DialogUtil.open({
       height:800,
       width: 1000,
       showMax: true,
       title : '查看历史',
       url: "${ctx}/platform/form/bpmFormDefHi/get.ht?hisId="+hisId, 
       isResize: true
   });
}

var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
function restore(hisId){
	var $selId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
	if(hisId == '' && $selId.length == 0){
		$.ligerDialog.warn("请选择记录！");
		return ;
	}
	if(hisId == '' && $selId.length > 1){
		$.ligerDialog.warn("只能选择一条记录！");
		return ;
	}
	if(hisId == null || hisId == ''){
		hisId = $($selId[0]).val();
	}
	$.ligerDialog.confirm('确认要恢复数据吗？','提示信息',function(rtn) {
		if(rtn) {
			var call=dialog.get("sucCall"); // 调用回调
			call(hisId);
			dialog.close();
		}
	});
}


</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link run" id="btnSearch" href="javascript:restore('')"><span></span>恢复</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div> 
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?formDefId=${formDefId }" style="display:block;">
					<ul class="row">
						<li><span class="label">表单标题:</span><input type="text" name="Q_subject_SL" value="${param['Q_subject_SL']}" class="inputText" /></li>
						<li><span class="label">描述:</span><input type="text" name="Q_formDesc_SL" value="${param['Q_formDesc_SL']}"  class="inputText" /></li>
						<li><span class="label">创建人:</span><input type="text" name="Q_createby_SL" value="${param['Q_createby_SL']}" class="inputText" /></li>
						<li><span class="label">创建时间 从:</span> <input  name="Q_begincreatetime_DL" value="${param['Q_begincreatetime_DL']}"  class="inputText date" /></li>
						<li><span class="label">至: </span><input  name="Q_endcreatetime_DG" value="${param['Q_endcreatetime_DG']}" class="inputText date" /></li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmFormDefHiList" id="bpmFormDefHiItem" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="hisId" value="${bpmFormDefHiItem.hisId}">
				</display:column>
				<display:column property="subject" title="表单标题" sortable="true" sortName="SUBJECT"></display:column>
				<display:column property="formDesc" title="描述" sortable="true" sortName="FORMDESC"></display:column>

				<display:column property="creator" title="创建人" sortable="true" sortName="CREATOR"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="CREATETIME">
					<fmt:formatDate value="${bpmFormDefHiItem.createtime}" pattern="yyyy-MM-dd HH:mm"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?hisId=${bpmFormDefHiItem.hisId}" class="link del">删除</a>
					<a href="javascript:restore('${bpmFormDefHiItem.hisId}');" class="link edit">恢复</a>
					<a href="javascript:preView('${bpmFormDefHiItem.hisId}');" class="link detail">查看</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormDefHiItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>
